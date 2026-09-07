package com.ayutaki.chinjufumod.blocks.slidedoor;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.Config_CM;
import com.ayutaki.chinjufumod.blocks.base.BaseStage3_FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.fuel.ItemCurtain;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class ShoujiWindow extends BaseStage3_FaceWater {
	/* Collision */
	private static final VoxelShape FRAME_SOUTH = Block.makeCuboidShape(0.0D, 0.0D, 7.0D, 16.0D, 0.01D, 9.0D);
	private static final VoxelShape FRAME_WEST = Block.makeCuboidShape(7.0D, 0.0D, 0.0D, 9.0D, 0.01D, 16.0D);
	private static final VoxelShape FRAME_NORTH = Block.makeCuboidShape(0.0D, 0.0D, 7.0D, 16.0D, 0.01D, 9.0D);
	private static final VoxelShape FRAME_EAST = Block.makeCuboidShape(7.0D, 0.0D, 0.0D, 9.0D, 0.01D, 16.0D);
	
	private static final VoxelShape CLOSE_SOUTH = VoxelShapes.or(FRAME_SOUTH, Block.makeCuboidShape(0.0D, 0.0D, 6.5D, 16.0D, 16.0D, 9.5D));
	private static final VoxelShape CLOSE_WEST = VoxelShapes.or(FRAME_WEST, Block.makeCuboidShape(6.5D, 0.0D, 0.0D, 9.5D, 16.0D, 16.0D));
	private static final VoxelShape CLOSE_NORTH = VoxelShapes.or(FRAME_NORTH, Block.makeCuboidShape(0.0D, 0.0D, 6.5D, 16.0D, 16.0D, 9.5D));
	private static final VoxelShape CLOSE_EAST = VoxelShapes.or(FRAME_EAST, Block.makeCuboidShape(6.5D, 0.0D, 0.0D, 9.5D, 16.0D, 16.0D));

	private static final VoxelShape OPENR_SOUTH = VoxelShapes.or(FRAME_SOUTH, Block.makeCuboidShape(7.5D, 0.0D, 6.5D, 16.0D, 16.0D, 9.5D));
	private static final VoxelShape OPENR_WEST = VoxelShapes.or(FRAME_WEST, Block.makeCuboidShape(6.5D, 0.0D, 7.5D, 9.5D, 16.0D, 16.0D));
	private static final VoxelShape OPENR_NORTH = VoxelShapes.or(FRAME_NORTH, Block.makeCuboidShape(0.0D, 0.0D, 6.5D, 8.5D, 16.0D, 9.5D));
	private static final VoxelShape OPENR_EAST = VoxelShapes.or(FRAME_EAST, Block.makeCuboidShape(6.5D, 0.0D, 0.0D, 9.5D, 16.0D, 8.5D));

	private static final VoxelShape OPENL_SOUTH = VoxelShapes.or(FRAME_SOUTH, Block.makeCuboidShape(0.0D, 0.0D, 6.5D, 8.5D, 16.0D, 9.5D));
	private static final VoxelShape OPENL_WEST = VoxelShapes.or(FRAME_WEST, Block.makeCuboidShape(6.5D, 0.0D, 0.0D, 9.5D, 16.0D, 8.5D));
	private static final VoxelShape OPENL_NORTH = VoxelShapes.or(FRAME_NORTH, Block.makeCuboidShape(7.5D, 0.0D, 6.5D, 16.0D, 16.0D, 9.5D));
	private static final VoxelShape OPENL_EAST = VoxelShapes.or(FRAME_EAST, Block.makeCuboidShape(6.5D, 0.0D, 7.5D, 9.5D, 16.0D, 16.0D));

	public ShoujiWindow(Block.Properties props) {
		super(props);
	}

	/* Anti Shadow */
	public int getLightValue(BlockState state) {
		return (Config_CM.getInstance().antiShadow() == true)? 1 : 0;
	}

	/* RightClick Action */
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		/** 1=Close, 2=Open Left, 3=Open Right **/
		int i = state.get(STAGE_1_3);
		Direction direction = state.get(H_FACING);
		Direction facing = playerIn.getHorizontalFacing();
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		if (hItem instanceof ItemCurtain) { return ActionResultType.PASS; }

		else {
			switch (i) {
			case 1:
			default:
				
				switch (direction) {
				case NORTH:
				default:
					if ((facing == Direction.NORTH && (hit.getHitVec().x - (double)pos.getX() < 0.5D)) || (facing == Direction.SOUTH && (hit.getHitVec().x - (double)pos.getX() < 0.5D))) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.with(STAGE_1_3, Integer.valueOf(i + 1))); }
	
					if ((facing == Direction.NORTH && (hit.getHitVec().x - (double)pos.getX() > 0.5D)) || (facing == Direction.SOUTH && (hit.getHitVec().x - (double)pos.getX() > 0.5D))) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.with(STAGE_1_3, Integer.valueOf(i + 2))); }
					break;
	
				case SOUTH:
					if ((facing == Direction.NORTH && (hit.getHitVec().x - (double)pos.getX() < 0.5D)) || (facing == Direction.SOUTH && (hit.getHitVec().x - (double)pos.getX() < 0.5D))) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.with(STAGE_1_3, Integer.valueOf(i + 2))); }
	
					if ((facing == Direction.NORTH && (hit.getHitVec().x - (double)pos.getX() > 0.5D)) || (facing == Direction.SOUTH && (hit.getHitVec().x - (double)pos.getX() > 0.5D))) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.with(STAGE_1_3, Integer.valueOf(i + 1))); }
					break;
	
				case EAST:
					if ((facing == Direction.EAST && (hit.getHitVec().z - (double)pos.getZ() < 0.5D)) || (facing == Direction.WEST && (hit.getHitVec().z - (double)pos.getZ() < 0.5D))) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.with(STAGE_1_3, Integer.valueOf(i + 1))); }
	
					if ((facing == Direction.EAST && (hit.getHitVec().z - (double)pos.getZ() > 0.5D)) || (facing == Direction.WEST && (hit.getHitVec().z - (double)pos.getZ() > 0.5D))) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.with(STAGE_1_3, Integer.valueOf(i + 2))); }
					break;
					
				case WEST:
					if ((facing == Direction.EAST && (hit.getHitVec().z - (double)pos.getZ() < 0.5D)) || (facing == Direction.WEST && (hit.getHitVec().z - (double)pos.getZ() < 0.5D))) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.with(STAGE_1_3, Integer.valueOf(i + 2))); }
	
					if ((facing == Direction.EAST && (hit.getHitVec().z - (double)pos.getZ() > 0.5D)) || (facing == Direction.WEST && (hit.getHitVec().z - (double)pos.getZ() > 0.5D))) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.with(STAGE_1_3, Integer.valueOf(i + 1))); }
					break;
				} // switch
				break;
	
			case 2:
				CMEvents.soundFusumaS(worldIn, pos);
				worldIn.setBlockState(pos, state.with(STAGE_1_3, Integer.valueOf(1)));
				break;
	
			case 3:
				CMEvents.soundFusumaS(worldIn, pos);
				worldIn.setBlockState(pos, state.with(STAGE_1_3, Integer.valueOf(1)));
				break;
			} // switch
			return ActionResultType.SUCCESS;
		}
	}

	/* Gives a value when placed. */
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		PlayerEntity playerIn = context.getPlayer();
		ItemStack hStack = playerIn.getHeldItem(Hand.MAIN_HAND);
		
		if (playerIn.isSneaking()) {
			return takeBlock(hStack).getDefaultState().with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
					.with(STAGE_1_3, Integer.valueOf(1)).with(WATERLOGGED, fluid.getFluid() == Fluids.WATER); }

		else { // !Crouching
			return this.getDefaultState().with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
					.with(STAGE_1_3, Integer.valueOf(1)).with(WATERLOGGED, fluid.getFluid() == Fluids.WATER); }
	}
	
	private Block takeBlock(ItemStack hStack) {
		Item hItem = hStack.getItem();
		if (hItem == Items_Wadeco.SHOUJI_WIN_SPRU) { return Slidedoor_Blocks.SHOUJI_WINR_SPRU; }
		if (hItem == Items_Wadeco.SHOUJI_WIN_BIR) { return Slidedoor_Blocks.SHOUJI_WINR_BIR; }
		if (hItem == Items_Wadeco.SHOUJI_WIN_JUN) { return Slidedoor_Blocks.SHOUJI_WINR_JUN; }
		if (hItem == Items_Wadeco.SHOUJI_WIN_ACA) { return Slidedoor_Blocks.SHOUJI_WINR_ACA; }
		if (hItem == Items_Wadeco.SHOUJI_WIN_DOAK) { return Slidedoor_Blocks.SHOUJI_WINR_DOAK; }
		if (hItem == Items_Seasonal.SHOUJI_WIN_SAKU) { return Slidedoor_Blocks.SHOUJI_WINR_SAKU; }
		if (hItem == Items_Seasonal.SHOUJI_WIN_KAE) { return Slidedoor_Blocks.SHOUJI_WINR_KAE; }
		if (hItem == Items_Seasonal.SHOUJI_WIN_ICH) { return Slidedoor_Blocks.SHOUJI_WINR_ICH; }
		return Slidedoor_Blocks.SHOUJI_WINR;
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(H_FACING);
		int i = state.get(STAGE_1_3);

		switch (direction) {
		case NORTH:
		default:
			return (i == 1)? CLOSE_NORTH : ((i == 2)? OPENL_NORTH : OPENR_NORTH);
		case SOUTH:
			return (i == 1)? CLOSE_SOUTH : ((i == 2)? OPENL_SOUTH : OPENR_SOUTH);
		case WEST:
			return (i == 1)? CLOSE_WEST : ((i == 2)? OPENL_WEST : OPENR_WEST);
		case EAST:
			return (i == 1)? CLOSE_EAST : ((i == 2)? OPENL_EAST : OPENR_EAST);
		}
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(cloneItem(), 1);
	}
	
	private Item cloneItem() {
		if (this == Slidedoor_Blocks.SHOUJI_WIN_SPRU || this == Slidedoor_Blocks.SHOUJI_WINR_SPRU) { return Items_Wadeco.SHOUJI_WIN_SPRU; }
		if (this == Slidedoor_Blocks.SHOUJI_WIN_BIR || this == Slidedoor_Blocks.SHOUJI_WINR_BIR) { return Items_Wadeco.SHOUJI_WIN_BIR; }
		if (this == Slidedoor_Blocks.SHOUJI_WIN_JUN || this == Slidedoor_Blocks.SHOUJI_WINR_JUN) { return Items_Wadeco.SHOUJI_WIN_JUN; }
		if (this == Slidedoor_Blocks.SHOUJI_WIN_ACA || this == Slidedoor_Blocks.SHOUJI_WINR_ACA) { return Items_Wadeco.SHOUJI_WIN_ACA; }
		if (this == Slidedoor_Blocks.SHOUJI_WIN_DOAK || this == Slidedoor_Blocks.SHOUJI_WINR_DOAK) { return Items_Wadeco.SHOUJI_WIN_DOAK; }
		if (this == Slidedoor_Blocks.SHOUJI_WIN_SAKU || this == Slidedoor_Blocks.SHOUJI_WINR_SAKU) { return Items_Seasonal.SHOUJI_WIN_SAKU; }
		if (this == Slidedoor_Blocks.SHOUJI_WIN_KAE || this == Slidedoor_Blocks.SHOUJI_WINR_KAE) { return Items_Seasonal.SHOUJI_WIN_KAE; }
		if (this == Slidedoor_Blocks.SHOUJI_WIN_ICH || this == Slidedoor_Blocks.SHOUJI_WINR_ICH) { return Items_Seasonal.SHOUJI_WIN_ICH; }
		else { return Items_Wadeco.SHOUJI_WIN; }
	}
	
	/* Can't breathe. */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Block is a cube. */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Mobs spawn. */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_shoujihalf").applyTextStyle(TextFormatting.GRAY));
	}
}
