package com.ayutaki.chinjufumod.items.hakkou;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.crop.Enden;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;

public class MizuokeFull_Item extends BucketItem {

	private final Fluid content;
	private final Block block;
	
	public MizuokeFull_Item(Fluid containedFluidIn, Block blockIn, Item.Properties props) {
		super(containedFluidIn, props.craftRemainder(Items_Teatime.MIZUOKE.get()));
		this.block = blockIn;
		this.content = containedFluidIn;
	}
	
	/* from IForgeItem. BurnTime in a Furnace */
	public int getBurnTime(ItemStack hStack, @Nullable RecipeType<?> recipeType) {
		Item hItem = hStack.getItem();

		if (hItem == Items_Teatime.MIZUOKE.get()) { return 100; }
		else { return 0; }
	}
	
	/* BucketItem ...Changed the method of collecting LAVA and WATER. */
	@Override
	public InteractionResult use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		BlockHitResult blockResult = getPlayerPOVHitResult(worldIn, playerIn, this.content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
		BlockPos pos = blockResult.getBlockPos();

		if (blockResult.getType() == HitResult.Type.MISS) { return InteractionResult.PASS; }

		if (blockResult.getType() != HitResult.Type.BLOCK) { return InteractionResult.PASS; }

		else {
			Direction direction = blockResult.getDirection();
			BlockPos posDirect = pos.relative(direction);
			BlockState state = worldIn.getBlockState(pos);
			Block block = state.getBlock();
			
			if (!playerIn.isCrouching()) {
				if (worldIn.mayInteract(playerIn, pos) && playerIn.mayUseItemAt(posDirect, direction, hStack)) {

					/** Turn SAND into ENDEN. **/
					if (block == Blocks.SAND && direction == Direction.UP) {
						worldIn.setBlock(pos, Crop_Blocks.ENDEN.get().defaultBlockState().setValue(Enden.WET_1_9, Integer.valueOf(1)), 3);
						
						CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
						return InteractionResult.SUCCESS;
					}

					/** To CAULDRON. **/
					if (block == Blocks.CAULDRON) {
						playerIn.awardStat(Stats.FILL_CAULDRON);
						worldIn.setBlock(pos, Blocks.WATER_CAULDRON.defaultBlockState()
								.setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3)), 3);
						
						CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
						return InteractionResult.SUCCESS;
					}

					if (block == Blocks.WATER_CAULDRON) {
						int cauldron = state.getValue(LayeredCauldronBlock.LEVEL);

						if (cauldron != 3) {
							playerIn.awardStat(Stats.FILL_CAULDRON);
							worldIn.setBlock(pos, Blocks.WATER_CAULDRON.defaultBlockState()
									.setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3)), 3);

							CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
							return InteractionResult.SUCCESS;
						}
						return InteractionResult.PASS;
					}
					
					else {
						BlockState stateFluid = worldIn.getBlockState(pos);
						BlockPos posFluid = canBlockContainFluid(worldIn, pos, stateFluid) ? pos : posDirect;

						if (this.emptyContents(playerIn, worldIn, posFluid, blockResult, hStack)) {
							this.checkExtraContent(playerIn, worldIn, hStack, posFluid);

							if (playerIn instanceof ServerPlayer) { CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)playerIn, posFluid, hStack); }
							playerIn.awardStat(Stats.ITEM_USED.get(this));

							CMEvents.mode1Through_Consume(playerIn, hand, Items_Teatime.MIZUOKE.get());
							return InteractionResult.SUCCESS;
						}
						else { return InteractionResult.PASS; }
					}
				}
			}//!sneaking

			return InteractionResult.PASS;
		}
	}//for 1.21.4

	protected boolean canBlockContainFluid(Level worldIn, BlockPos pos, BlockState state) {
		return state.getBlock() instanceof LiquidBlockContainer liquid && liquid.canPlaceLiquid(null, worldIn, pos, state, this.content);
	} // for 20.2
	
	@SuppressWarnings("deprecation")
	public boolean emptyContents(@Nullable Player playerIn, Level worldIn, BlockPos pos, @Nullable BlockHitResult blockResult, @Nullable ItemStack container) {
		if (!(this.content instanceof FlowingFluid)) { return false; }

		else {
			BlockState state = worldIn.getBlockState(pos);
			Block block = state.getBlock();
			boolean flag = state.canBeReplaced(this.content);
			boolean flag1 = state.isAir() || flag || block instanceof LiquidBlockContainer liquid && liquid.canPlaceLiquid(playerIn, worldIn, pos, state, this.content);
			java.util.Optional<net.neoforged.neoforge.fluids.FluidStack> fluidStack = java.util.Optional.ofNullable(container).flatMap(net.neoforged.neoforge.fluids.FluidUtil::getFluidContained);
			
			if (!flag1) {
				return blockResult != null && this.emptyContents(playerIn, worldIn, blockResult.getBlockPos().relative(blockResult.getDirection()), (BlockHitResult)null, container); } 
			
			else if (fluidStack.isPresent() && this.content.getFluidType().isVaporizedOnPlacement(worldIn, pos, fluidStack.get())) {
				this.content.getFluidType().onVaporize(playerIn, worldIn, pos, fluidStack.get());
				return true;
			}
			
			/** in Nether **/
			else if (worldIn.dimensionType().ultraWarm() && this.content.is(FluidTags.WATER)) {
				int i = pos.getX();
				int j = pos.getY();
				int k = pos.getZ();
				worldIn.playSound(playerIn, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (worldIn.random.nextFloat() - worldIn.random.nextFloat()) * 0.8F);

				for(int l = 0; l < 8; ++l) {
					worldIn.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D); }

				return true; } 
			
			/** WATERLOGGED **/
			else if (block instanceof LiquidBlockContainer liquid && liquid.canPlaceLiquid(playerIn, worldIn, pos, state, content)) {
				((LiquidBlockContainer)block).placeLiquid(worldIn, pos, state, ((FlowingFluid)this.content).getSource(false));
				CMEvents.soundBucketEmpty(worldIn, pos, playerIn);
				return true; } 
			
			else {
				if (!worldIn.isClientSide && flag && !state.liquid()) { worldIn.destroyBlock(pos, true); }

				if (!worldIn.setBlock(pos, this.content.defaultFluidState().createLegacyBlock(), 11) && !state.getFluidState().isSource()) { return false; } 
				
				else { CMEvents.soundBucketEmpty(worldIn, pos, playerIn);
					return true; }
			}
		}
	} // for 20.2
	
	
	//////* BlockItem *///////////////////////////////////////////////
	/* Branch the process. */
	@Override
	 public InteractionResult useOn(UseOnContext context) {
		Player playerIn = context.getPlayer();

		if (context.getClickedFace() == Direction.UP && (playerIn.isCrouching() || playerIn.isPassenger())) {
			return this.place(new BlockPlaceContext(context)); }

		else {
			return this.use(context.getLevel(), context.getPlayer(), context.getHand()); } //for 1.21.4
	 }
	 
	public InteractionResult place(BlockPlaceContext context) {
		if (!context.canPlace()) { return InteractionResult.FAIL; } 
		
		else {
			BlockPlaceContext blockplacecontext = this.updatePlacementContext(context);
			if (blockplacecontext == null) { return InteractionResult.FAIL; } 
			
			else {
				BlockState state = this.getPlacementState(blockplacecontext);
				if (state == null) { return InteractionResult.FAIL; } 
				
				else if (!this.placeBlock(blockplacecontext, state)) { return InteractionResult.FAIL; } 
				
				else {
					BlockPos pos = blockplacecontext.getClickedPos();
					Level worldIn = blockplacecontext.getLevel();
					Player playerIn = blockplacecontext.getPlayer();
					ItemStack stack = blockplacecontext.getItemInHand();
					BlockState state1 = worldIn.getBlockState(pos);
					if (state1.is(state.getBlock())) {
						state1 = this.updateBlockStateFromTag(pos, worldIn, stack, state1);
						this.updateCustomBlockEntityTag(pos, worldIn, playerIn, stack, state1);
						//updateBlockEntityComponents(worldIn, pos, stack);
						state1.getBlock().setPlacedBy(worldIn, pos, state1, playerIn, stack);
						if (playerIn instanceof ServerPlayer) {
							CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)playerIn, pos, stack);
						}
					}

					SoundType soundtype = state1.getSoundType(worldIn, pos, context.getPlayer());
					worldIn.playSound(playerIn, pos, this.getPlaceSound(state1, worldIn, pos, context.getPlayer()), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

					worldIn.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(playerIn, state1)); //for 1.20.6
					stack.consume(1, playerIn); //for 1.20.6
					return InteractionResult.SUCCESS;
				}
			}
		}
	}
	
	protected SoundEvent getPlaceSound(BlockState state, Level world, BlockPos pos, Player entity) {
		return state.getSoundType(world, pos, entity).getPlaceSound();
	}

	@Nullable
	public BlockPlaceContext updatePlacementContext(BlockPlaceContext context) {
		return context;
	}

	protected boolean updateCustomBlockEntityTag(BlockPos pos, Level worldIn, @Nullable Player playerIn, ItemStack stack, BlockState state) {
		return updateCustomBlockEntityTag(worldIn, playerIn, pos, stack);
	}

	@Nullable
	protected BlockState getPlacementState(BlockPlaceContext context) {
		BlockState state = this.getBlock().getStateForPlacement(context);
		return state != null && this.canPlace(context, state) ? state : null;
	}

	/** for 1.20.6 **/
	private BlockState updateBlockStateFromTag(BlockPos pos, Level worldIn, ItemStack stack, BlockState state) {
		BlockItemStateProperties itemState = stack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
		
		if (itemState.isEmpty()) { return state; }
		
		else {
			BlockState state1 = itemState.apply(state);
			if (state1 != state) { worldIn.setBlock(pos, state1, 2); }
			
			return state1;
		}
	}

	protected boolean canPlace(BlockPlaceContext context, BlockState state) {
		Player playerIn = context.getPlayer();
		CollisionContext collisioncontext = playerIn == null ? CollisionContext.empty() : CollisionContext.of(playerIn);
		return (!this.mustSurvive() || state.canSurvive(context.getLevel(), context.getClickedPos())) && context.getLevel().isUnobstructed(state, context.getClickedPos(), collisioncontext);
	}

	protected boolean mustSurvive() {
		return true;
	}

	protected boolean placeBlock(BlockPlaceContext context, BlockState state) {
		return context.getLevel().setBlock(context.getClickedPos(), state, 11);
	}

	 /** for 1.21.4 **/
	public static boolean updateCustomBlockEntityTag(Level worldIn, @Nullable Player plaerIn, BlockPos pos, ItemStack hStack) {
		if (worldIn.isClientSide) { return false; } 
		
		else {
			CustomData customdata = hStack.getOrDefault(DataComponents.BLOCK_ENTITY_DATA, CustomData.EMPTY);
			if (!customdata.isEmpty()) {
				BlockEntityType<?> blockentitytype = customdata.parseEntityType(worldIn.registryAccess(), Registries.BLOCK_ENTITY_TYPE);
				if (blockentitytype == null) { return false; }

				BlockEntity blockentity = worldIn.getBlockEntity(pos);
				if (blockentity != null) {
					BlockEntityType<?> blockentitytype1 = blockentity.getType();
					if (blockentitytype1 != blockentitytype) { return false; }

					if (!blockentitytype1.onlyOpCanSetNbt() || plaerIn != null && plaerIn.canUseGameMasterBlocks()) {
						return customdata.loadInto(blockentity, worldIn.registryAccess()); }

					return false;
				}
			}
			return false;
		}
	}

	/* getNameTextComponent に影響するためコメントアウト
	public String getDescriptionId() {
		return this.getBlock().getDescriptionId();
	}

	ItemGroup に影響するためコメントアウト
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> stack) {
		if (this.allowdedIn(tab)) {
			this.getBlock().fillItemCategory(tab, stack);
		}
	}*/

	/** for 1.20.6 **/
	public Block getBlock() {
		return this.block;
	}

	public void registerBlocks(Map<Block, Item> blockItem, Item itemIn) {
		blockItem.put(this.getBlock(), itemIn);
	}

	public void removeFromBlockToItemMap(Map<Block, Item> blockToItemMap, Item itemIn) {
		blockToItemMap.remove(this.getBlock());
	}

	/** for 1.20.6 **/
	public static void setBlockEntityData(ItemStack stack, BlockEntityType<?> entityType, CompoundTag compound) {
		compound.remove("id");
		if (compound.isEmpty()) { stack.remove(DataComponents.BLOCK_ENTITY_DATA); } 
		
		else {
			BlockEntity.addEntityType(compound, entityType);
			stack.set(DataComponents.BLOCK_ENTITY_DATA, CustomData.of(compound)); }
	}
	
	@Override
	public FeatureFlagSet requiredFeatures() {
		return this.getBlock().requiredFeatures();
	}
		
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.block_mizuoke").withStyle(ChatFormatting.GRAY));
		itemTip.add(Component.translatable("tips.block_simpledish").withStyle(ChatFormatting.GRAY));
	}
}
