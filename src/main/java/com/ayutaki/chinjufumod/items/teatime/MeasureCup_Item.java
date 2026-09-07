package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
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
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;

public class MeasureCup_Item extends BucketItem {

	private final Fluid content;
	private final Block block;
	
	public MeasureCup_Item(Fluid containedFluidIn, Block blockIn, Item.Properties props) {
		super(containedFluidIn, props);
		this.block = blockIn;
		this.content = containedFluidIn;
	}
	
	private void MeasureCup_toFull(Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand) {
		CMEvents.take1Item(playerIn, hand, Items_Teatime.KEIRYO_CUP_full.get());
		CMEvents.consume1_seBottle(worldIn, pos, playerIn, hand); }
	
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

					if (this.content == Fluids.EMPTY) {
						
						/** From CAULDRON. **/
						if (block == Blocks.WATER_CAULDRON) {
							int cauldron = state.getValue(LayeredCauldronBlock.LEVEL);

							if (cauldron > 1) {
								playerIn.awardStat(Stats.USE_CAULDRON);
								this.MeasureCup_toFull(worldIn, pos, playerIn, hand);
								
								worldIn.setBlock(pos, Blocks.WATER_CAULDRON.defaultBlockState()
										.setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(cauldron - 1)), 3);
								return InteractionResult.SUCCESS;
							}

							if (cauldron == 1) {
								playerIn.awardStat(Stats.USE_CAULDRON);
								this.MeasureCup_toFull(worldIn, pos, playerIn, hand);
								
								worldIn.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3);
								return InteractionResult.SUCCESS;
							}
							return InteractionResult.PASS;
						}

						/** WATER **/
						if (worldIn.getFluidState(pos).is(FluidTags.WATER)) {
							this.MeasureCup_toFull(worldIn, pos, playerIn, hand);
							return InteractionResult.SUCCESS;
						}
						
						return InteractionResult.PASS;
					}// MIZUOKE is empty.

				}
			}//!sneaking

			return InteractionResult.PASS;
		}
	}

	
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
		itemTip.add(Component.translatable("tips.block_measurecup").withStyle(ChatFormatting.GRAY));
		itemTip.add(Component.translatable("tips.block_simpledish").withStyle(ChatFormatting.GRAY));
	}
}
