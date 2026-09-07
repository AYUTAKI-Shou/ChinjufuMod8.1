package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.base.IG_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.GravelBlock;
import net.minecraft.block.SandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Tengusa_TT extends IG_Teatime {
	/* Since I want to use Blocks.CAULDRON, I won't choose BlockItem. */
	private final Block block;
	
	public Tengusa_TT(Block blockIn, Item.Properties props) {
		super(props);
		this.block = blockIn;
	}

	private void TENGUSA_Wash(IWorld iworld, BlockPos pos, ItemStack hStack, PlayerEntity playerIn) {
		iworld.playSound(playerIn, pos, SoundEvents_CM.WATER_SPLASH, SoundCategory.BLOCKS, 0.5F, 1.2F);
		
		ItemStack take = new ItemStack(Items_Teatime.TENGUSA_WASH, 1);
		if (!playerIn.inventory.add(take)) { playerIn.drop(take, false); }
		
		boolean mode = playerIn.abilities.instabuild;
		if (mode) { }
		else { hStack.shrink(2); } }
	
	private void textNotEnough_Items(IWorld iworld, BlockPos pos, PlayerEntity playerIn) {
		iworld.playSound(null, pos, SoundEvents_CM.TOUCH_BLOCK, SoundCategory.BLOCKS, 1.0F, 0.75F);
		playerIn.displayClientMessage(new TranslationTextComponent("text.chinjufumod.rightclick.notenough_items"), true); }
	
	@Override
	public ActionResultType useOn(ItemUseContext context) {
		PlayerEntity playerIn = context.getPlayer();
		ItemStack hStack = context.getItemInHand();
		
		IWorld iworld = context.getLevel();
		BlockPos pos = context.getClickedPos();
		BlockState state = iworld .getBlockState(pos);
		Block block = state.getBlock();

		if (block == Blocks.CAULDRON) {
			int gHC = hStack.getCount();
			
			if (gHC >= 2) {
				int level = state.getValue(CauldronBlock.LEVEL);
				if (level != 0) {
					iworld.setBlock(pos, state.setValue(CauldronBlock.LEVEL, Integer.valueOf(level - 1)), 3);
					this.TENGUSA_Wash(iworld, pos, hStack, playerIn);
					return ActionResultType.SUCCESS; }
				
				else { return ActionResultType.FAIL; } }
			
			else {
				this.textNotEnough_Items(iworld, pos, playerIn);
				return ActionResultType.FAIL; }
		}
		
		else {
			boolean canPLACE = (block instanceof SandBlock || block instanceof GravelBlock) && (context.getClickedFace() == Direction.UP);
			if (canPLACE) { return this.place(new BlockItemUseContext(context)); }
			
			else { return ActionResultType.FAIL; }
		}
	}
	
	//////* BlockItem *///////////////////////////////////////////////
	public ActionResultType place(BlockItemUseContext context) {

		if (!context.canPlace()) { return ActionResultType.FAIL; }

		else {
			BlockItemUseContext blockcontext = this.updatePlacementContext(context);

			if (blockcontext == null) { return ActionResultType.FAIL; }

			else {
				BlockState stateIn = this.getPlacementState(blockcontext);

				if (stateIn == null) { return ActionResultType.FAIL; }

				else if (!this.placeBlock(blockcontext, stateIn)) { return ActionResultType.FAIL; }

				else {
					BlockPos pos1 = blockcontext.getClickedPos();
					World worldIn = blockcontext.getLevel();
					PlayerEntity playerIn = blockcontext.getPlayer();
					ItemStack stackIn = blockcontext.getItemInHand();
					BlockState stateIn1 = worldIn.getBlockState(pos1);
					Block block = stateIn1.getBlock();
					if (block == stateIn.getBlock()) {
						stateIn1 = this.updateBlockStateFromTag(pos1, worldIn, stackIn, stateIn1);
						this.updateCustomBlockEntityTag(pos1, worldIn, playerIn, stackIn, stateIn1);
						block.setPlacedBy(worldIn, pos1, stateIn1, playerIn, stackIn);
						if (playerIn instanceof ServerPlayerEntity) {
							CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerIn, pos1, stackIn);
						}
					}

					SoundType soundtype = stateIn1.getSoundType(worldIn, pos1, context.getPlayer());
					worldIn.playSound(playerIn, pos1, this.getPlaceSound(stateIn1, worldIn, pos1, context.getPlayer()), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

					if (playerIn == null || !playerIn.abilities.instabuild) { stackIn.shrink(1); }

					return ActionResultType.sidedSuccess(worldIn.isClientSide);
				}
			}
		}
	}

	@Deprecated //Forge: Use more sensitive version {@link BlockItem#getPlaceSound(BlockState, IBlockReader, BlockPos, Entity) }
	protected SoundEvent getPlaceSound(BlockState state) {
		return state.getSoundType().getPlaceSound();
	}

	protected SoundEvent getPlaceSound(BlockState state, World worldIn, BlockPos pos, PlayerEntity entity) {
		return state.getSoundType(worldIn, pos, entity).getPlaceSound();
	}

	@Nullable
	public BlockItemUseContext updatePlacementContext(BlockItemUseContext context) {
		return context;
	}

	protected boolean updateCustomBlockEntityTag(BlockPos pos, World worldIn, @Nullable PlayerEntity playerIn, ItemStack stack, BlockState state) {
		return updateCustomBlockEntityTag(worldIn, playerIn, pos, stack);
	}

	@Nullable
	protected BlockState getPlacementState(BlockItemUseContext context) {
		BlockState stateIn = this.getBlock().getStateForPlacement(context);
		return stateIn != null && this.canPlace(context, stateIn) ? stateIn : null;
	}

	private BlockState updateBlockStateFromTag(BlockPos pos, World worldIn, ItemStack stack, BlockState state) {
		BlockState stateIn = state;
		CompoundNBT compoundnbt = stack.getTag();
		if (compoundnbt != null) {
			CompoundNBT compoundnbt1 = compoundnbt.getCompound("BlockStateTag");
			StateContainer<Block, BlockState> statecontainer = state.getBlock().getStateDefinition();

			for(String s : compoundnbt1.getAllKeys()) {
				Property<?> iproperty = statecontainer.getProperty(s);
				if (iproperty != null) {
					String s1 = compoundnbt1.get(s).getAsString();
					stateIn = updateState(stateIn, iproperty, s1);
				}
			}
		}

		if (stateIn != state) {
			worldIn.setBlock(pos, stateIn, 2);
		}

		return stateIn;
	}

	private static <T extends Comparable<T>> BlockState updateState(BlockState state, Property<T> property, String string) {
		return property.getValue(string).map((mapper) -> {
			return state.setValue(property, mapper);
		}).orElse(state);
	}

	protected boolean canPlace(BlockItemUseContext context, BlockState state) {
		PlayerEntity playerIn = context.getPlayer();
		ISelectionContext iselectioncontext = playerIn == null ? ISelectionContext.empty() : ISelectionContext.of(playerIn);
		return (!this.mustSurvive() || state.canSurvive(context.getLevel(), context.getClickedPos())) && context.getLevel().isUnobstructed(state, context.getClickedPos(), iselectioncontext);
	}

	protected boolean mustSurvive() {
		return true;
	}

	protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
		return context.getLevel().setBlock(context.getClickedPos(), state, 11);
	}

	public static boolean updateCustomBlockEntityTag(World worldIn, @Nullable PlayerEntity playerIn, BlockPos pos, ItemStack stackIn) {
		MinecraftServer minecraftserver = worldIn.getServer();
		if (minecraftserver == null) {
			return false;
		}

		else {
			CompoundNBT compoundnbt = stackIn.getTagElement("BlockEntityTag");
			if (compoundnbt != null) {
				TileEntity tileentity = worldIn.getBlockEntity(pos);
				if (tileentity != null) {
					if (!worldIn.isClientSide && tileentity.onlyOpCanSetNbt() && (playerIn == null || !playerIn.canUseGameMasterBlocks())) {
						return false;
					}

					CompoundNBT compoundnbt1 = tileentity.save(new CompoundNBT());
					CompoundNBT compoundnbt2 = compoundnbt1.copy();
					compoundnbt1.merge(compoundnbt);
					compoundnbt1.putInt("x", pos.getX());
					compoundnbt1.putInt("y", pos.getY());
					compoundnbt1.putInt("z", pos.getZ());
					if (!compoundnbt1.equals(compoundnbt2)) {
						tileentity.load(worldIn.getBlockState(pos), compoundnbt1);
						tileentity.setChanged();
						return true;
					}
				}
			}
			return false;
		}
	}

	/* getNameTextComponent に影響するためコメントアウト
	public String getTranslationKey() {
		return this.getBlock().getTranslationKey();
	}

	ItemGroup に影響するためコメントアウト
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.isInGroup(group)) {
			this.getBlock().fillItemGroup(group, items);
		}
	}*/

	public Block getBlock() {
		return this.getBlockRaw() == null ? null : this.getBlockRaw().delegate.get();
	}

	private Block getBlockRaw() {
		return this.block;
	}

	public void registerBlocks(Map<Block, Item> blockToItemMap, Item itemIn) {
		blockToItemMap.put(this.getBlock(), itemIn);
	}

	public void removeFromBlockToItemMap(Map<Block, Item> blockToItemMap, Item itemIn) {
		blockToItemMap.remove(this.getBlock());
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_crop_tengusa").withStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_crop_tengusa2").withStyle(TextFormatting.GRAY));
	}
}
