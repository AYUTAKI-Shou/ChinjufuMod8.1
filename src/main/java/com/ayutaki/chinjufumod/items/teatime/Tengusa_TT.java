package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.base.IG_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GravelBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.CollisionContext;

public class Tengusa_TT extends IG_Teatime {
	/* Since I want to use Blocks.CAULDRON, I won't choose BlockItem. */
	private final Block block;
	
	public Tengusa_TT(Block blockIn, Item.Properties props) {
		super(props);
		this.block = blockIn;
	}
	
	private void TENGUSA_Wash(Level worldIn, BlockPos pos, ItemStack hStack, Player playerIn) {
		worldIn.playSound(playerIn, pos, SoundEvents_CM.WATER_SPLASH.get(), SoundSource.BLOCKS, 0.5F, 1.2F);
		
		ItemStack take = new ItemStack(Items_Teatime.TENGUSA_WASH.get(), 1);
		if (!playerIn.getInventory().add(take)) { playerIn.drop(take, false); }
		
		boolean mode = playerIn.getAbilities().instabuild;
		if (mode) { }
		else { hStack.shrink(2); } }
	
	@Override
	 public InteractionResult useOn(UseOnContext context) {
		Player playerIn = context.getPlayer();
		ItemStack hStack = context.getItemInHand();
		
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		
		if (block == Blocks.WATER_CAULDRON) {
			int gHC = hStack.getCount();
			
			if (gHC >= 2) {
				int cauldron = state.getValue(LayeredCauldronBlock.LEVEL);
				if (cauldron == 1) {
					worldIn.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 3); }
				else { //cauldron != 1
					worldIn.setBlock(pos, state.setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(cauldron - 1)), 3); }
				
				this.TENGUSA_Wash(worldIn, pos, hStack, playerIn);
				return InteractionResult.SUCCESS; }

			else { 
				CMEvents.textNotEnough_Items(worldIn, pos, playerIn);
				return InteractionResult.FAIL; }
		}
		
		else {
			boolean canPLACE = (block instanceof SandBlock || block instanceof GravelBlock) && (context.getClickedFace() == Direction.UP);
			if (canPLACE) { return this.place(new BlockPlaceContext(context)); }

			else { return InteractionResult.FAIL; } 
		}
	 }
	 
	//////* BlockItem *///////////////////////////////////////////////
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

					worldIn.gameEvent(playerIn, GameEvent.BLOCK_PLACE, pos);
					SoundType soundtype = state1.getSoundType(worldIn, pos, context.getPlayer());
					worldIn.playSound(playerIn, pos, this.getPlaceSound(state1, worldIn, pos, context.getPlayer()), SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
					if (playerIn == null || !playerIn.getAbilities().instabuild) {
						stack.shrink(1);
					}

					return InteractionResult.sidedSuccess(worldIn.isClientSide);
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

	private BlockState updateBlockStateFromTag(BlockPos pos, Level worldIn, ItemStack stack, BlockState state) {
		BlockState state1 = state;
		CompoundTag compoundtag = stack.getTag();
		if (compoundtag != null) {
			CompoundTag compoundtag1 = compoundtag.getCompound("BlockStateTag");
			StateDefinition<Block, BlockState> statedefinition = state.getBlock().getStateDefinition();

			for(String s : compoundtag1.getAllKeys()) {
				Property<?> property = statedefinition.getProperty(s);
				if (property != null) {
					String s1 = compoundtag1.get(s).getAsString();
					state1 = updateState(state1, property, s1);
				}
			}
		}

		if (state1 != state) { worldIn.setBlock(pos, state1, 2); }

		return state1;
	}

	private static <T extends Comparable<T>> BlockState updateState(BlockState state, Property<T> propertyIn, String string) {
		return propertyIn.getValue(string).map((p_40592_) -> {
			return state.setValue(propertyIn, p_40592_); } ).orElse(state);
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

	public static boolean updateCustomBlockEntityTag(Level worldIn, @Nullable Player playerIn, BlockPos pos, ItemStack stack) {
		MinecraftServer minecraftserver = worldIn.getServer();
		if (minecraftserver == null) { return false; } 
		
		else {
			CompoundTag compoundtag = getBlockEntityData(stack);
			if (compoundtag != null) {
				BlockEntity blockentity = worldIn.getBlockEntity(pos);
				if (blockentity != null) {
					if (!worldIn.isClientSide && blockentity.onlyOpCanSetNbt() && (playerIn == null || !playerIn.canUseGameMasterBlocks())) {
						return false; }

					CompoundTag compoundtag1 = blockentity.saveWithoutMetadata();
					CompoundTag compoundtag2 = compoundtag1.copy();
					compoundtag1.merge(compoundtag);
					if (!compoundtag1.equals(compoundtag2)) {
						blockentity.load(compoundtag1);
						blockentity.setChanged();
						return true;
					}
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

	public Block getBlock() {
		return this.getBlockRaw() == null ? null : this.getBlockRaw().delegate.get();
	}

	private Block getBlockRaw() {
		return this.block;
	}

	public void registerBlocks(Map<Block, Item> blockItem, Item itemIn) {
		blockItem.put(this.getBlock(), itemIn);
	}

	public void removeFromBlockToItemMap(Map<Block, Item> blockToItemMap, Item itemIn) {
		blockToItemMap.remove(this.getBlock());
	}

 @Nullable
 public static CompoundTag getBlockEntityData(ItemStack stack) {
		return stack.getTagElement("BlockEntityTag");
 }

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(new TranslatableComponent("tips.item_crop_tengusa").withStyle(ChatFormatting.GRAY));
		itemTip.add(new TranslatableComponent("tips.item_crop_tengusa2").withStyle(ChatFormatting.GRAY));
	}
}
