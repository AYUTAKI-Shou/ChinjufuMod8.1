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
import net.minecraft.state.IProperty;
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
		if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }
		
		boolean mode = playerIn.abilities.isCreativeMode;
		if (mode) { }
		else { hStack.shrink(2); } }
	
	private void textNotEnough_Items(IWorld iworld, BlockPos pos, PlayerEntity playerIn) {
		iworld.playSound(null, pos, SoundEvents_CM.TOUCH_BLOCK, SoundCategory.BLOCKS, 1.0F, 0.75F);
		playerIn.sendStatusMessage(new TranslationTextComponent("text.chinjufumod.rightclick.notenough_items"), true); }
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity playerIn = context.getPlayer();
		ItemStack hStack = context.getItem();
		
		IWorld iworld = context.getWorld();
		BlockPos pos = context.getPos();
		BlockState state = iworld.getBlockState(pos);
		Block block = state.getBlock();
		
		if (block == Blocks.CAULDRON) {
			int gHC = hStack.getCount();
			
			if (gHC >= 2) {
				int level = state.get(CauldronBlock.LEVEL);
				if (level != 0) {
					iworld.setBlockState(pos, state.with(CauldronBlock.LEVEL, Integer.valueOf(level - 1)), 3);
					this.TENGUSA_Wash(iworld, pos, hStack, playerIn);
					return ActionResultType.SUCCESS; }
				
				else { return ActionResultType.FAIL; } }
			
			else {
				this.textNotEnough_Items(iworld, pos, playerIn);
				return ActionResultType.FAIL; }
		}
		
		else {
			boolean canPLACE = (block instanceof SandBlock || block instanceof GravelBlock) && (context.getFace() == Direction.UP);
			if (canPLACE) { return this.tryPlace(new BlockItemUseContext(context)); }

			else { return ActionResultType.FAIL; }
		}
	}

	//////* BlockItem *///////////////////////////////////////////////
	public ActionResultType tryPlace(BlockItemUseContext context) {
		if (!context.canPlace()) { return ActionResultType.FAIL; }

		else {
			BlockItemUseContext blockitemusecontext = this.getBlockItemUseContext(context);
			if (blockitemusecontext == null) { return ActionResultType.FAIL; }

			else {
				BlockState stateIn = this.getStateForPlacement(blockitemusecontext);
				if (stateIn == null) { return ActionResultType.FAIL; }

				else if (!this.placeBlock(blockitemusecontext, stateIn)) { return ActionResultType.FAIL; }

				else {
					BlockPos pos = blockitemusecontext.getPos();
					World world = blockitemusecontext.getWorld();
					PlayerEntity playerIn = blockitemusecontext.getPlayer();
					ItemStack hStack = blockitemusecontext.getItem();
					BlockState stateIn1 = world.getBlockState(pos);
					Block block = stateIn1.getBlock();
					if (block == stateIn.getBlock()) {
						stateIn1 = this.getBlockStateTag(pos, world, hStack, stateIn1);
						this.onBlockPlaced(pos, world, playerIn, hStack, stateIn1);
						block.onBlockPlacedBy(world, pos, stateIn1, playerIn, hStack);
						if (playerIn instanceof ServerPlayerEntity) {
							CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerIn, pos, hStack);
						}
					}

					SoundType soundtype = stateIn1.getSoundType(world, pos, context.getPlayer());
					world.playSound(playerIn, pos, this.getPlaceSound(stateIn1, world, pos, context.getPlayer()), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
					hStack.shrink(1);
					return ActionResultType.SUCCESS;
				}
			}
		}
	}

	@Deprecated //Forge: Use more sensitive version {@link BlockItem#getPlaceSound(BlockState, IBlockReader, BlockPos, Entity) }
	protected SoundEvent getPlaceSound(BlockState state) {
		return state.getSoundType().getPlaceSound();
	}

	protected SoundEvent getPlaceSound(BlockState state, World world, BlockPos pos, PlayerEntity entity) {
		return state.getSoundType(world, pos, entity).getPlaceSound();
	}

	@Nullable
	public BlockItemUseContext getBlockItemUseContext(BlockItemUseContext context) {
		return context;
	}

	protected boolean onBlockPlaced(BlockPos pos, World worldIn, @Nullable PlayerEntity playerIn, ItemStack stack, BlockState state) {
		return setTileEntityNBT(worldIn, playerIn, pos, stack);
	}

	@Nullable
	protected BlockState getStateForPlacement(BlockItemUseContext context) {
		BlockState stateIn = this.getBlock().getStateForPlacement(context);
		return stateIn != null && this.canPlace(context, stateIn) ? stateIn : null;
	}

	private BlockState getBlockStateTag(BlockPos pos, World worldIn, ItemStack stack, BlockState state) {
		BlockState stateIn = state;
		CompoundNBT compoundnbt = stack.getTag();
		if (compoundnbt != null) {
			CompoundNBT compoundnbt1 = compoundnbt.getCompound("BlockStateTag");
			StateContainer<Block, BlockState> statecontainer = state.getBlock().getStateContainer();

			for(String s : compoundnbt1.keySet()) {
				IProperty<?> iproperty = statecontainer.getProperty(s);
				if (iproperty != null) {
					String s1 = compoundnbt1.get(s).getString();
					stateIn = comBlockState(stateIn, iproperty, s1);
				}
			}
		}

		if (stateIn != state) {
			worldIn.setBlockState(pos, stateIn, 2);
		}

		return stateIn;
	}

	private static <T extends Comparable<T>> BlockState comBlockState(BlockState state, IProperty<T> property, String string) {
		return property.parseValue(string).map((mapper) -> {
			return state.with(property, mapper);
		}).orElse(state);
	}

	protected boolean canPlace(BlockItemUseContext context, BlockState state) {
		PlayerEntity playerIn = context.getPlayer();
		ISelectionContext iselectioncontext = playerIn == null ? ISelectionContext.dummy() : ISelectionContext.forEntity(playerIn);
		return (!this.checkPosition() || state.isValidPosition(context.getWorld(), context.getPos())) && context.getWorld().func_226663_a_(state, context.getPos(), iselectioncontext);
	}

	protected boolean checkPosition() {
		return true;
	}

	protected boolean placeBlock(BlockItemUseContext context, BlockState state) {
		return context.getWorld().setBlockState(context.getPos(), state, 11);
	}

	public static boolean setTileEntityNBT(World worldIn, @Nullable PlayerEntity playerIn, BlockPos pos, ItemStack stack) {
		MinecraftServer minecraftserver = worldIn.getServer();
		if (minecraftserver == null) {
			return false;
		}

		else {
			CompoundNBT compoundnbt = stack.getChildTag("BlockEntityTag");
			if (compoundnbt != null) {
				TileEntity tileentity = worldIn.getTileEntity(pos);
				if (tileentity != null) {
					if (!worldIn.isRemote && tileentity.onlyOpsCanSetNbt() && (playerIn == null || !playerIn.canUseCommandBlock())) {
						return false;
					}

					CompoundNBT compoundnbt1 = tileentity.write(new CompoundNBT());
					CompoundNBT compoundnbt2 = compoundnbt1.copy();
					compoundnbt1.merge(compoundnbt);
					compoundnbt1.putInt("x", pos.getX());
					compoundnbt1.putInt("y", pos.getY());
					compoundnbt1.putInt("z", pos.getZ());
					if (!compoundnbt1.equals(compoundnbt2)) {
						tileentity.read(compoundnbt1);
						tileentity.markDirty();
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

	public void addToBlockToItemMap(Map<Block, Item> blockToItemMap, Item itemIn) {
		blockToItemMap.put(this.getBlock(), itemIn);
	}

	public void removeFromBlockToItemMap(Map<Block, Item> blockToItemMap, Item itemIn) {
		blockToItemMap.remove(this.getBlock());
	}

	/* ToolTip*/
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.item_crop_tengusa").applyTextStyle(TextFormatting.GRAY));
		itemTip.add(new TranslationTextComponent("tips.item_crop_tengusa2").applyTextStyle(TextFormatting.GRAY));
	}
}
