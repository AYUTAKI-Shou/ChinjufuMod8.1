package com.ayutaki.chinjufumod.blocks.furniture;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Base_6FaceWater;
import com.ayutaki.chinjufumod.handler.CMEvents;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LampMarine extends Base_6FaceWater {
	/* Property */
	public static final BooleanProperty LIT = BooleanProperty.create("lit");

	/* Collision */
	private static final VoxelShape AABB_UP = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 7.0D, 10.0D);
	private static final VoxelShape AABB_DOWN = Block.makeCuboidShape(6.0D, 9.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	private static final VoxelShape AABB_SOUTH = Block.makeCuboidShape(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 7.0D);
	private static final VoxelShape AABB_WEST = Block.makeCuboidShape(9.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D);
	private static final VoxelShape AABB_NORTH = Block.makeCuboidShape(6.0D, 6.0D, 9.0D, 10.0D, 10.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.makeCuboidShape(0.0D, 6.0D, 6.0D, 7.0D, 10.0D, 10.0D);

	public LampMarine(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.UP)
				.with(LIT, Boolean.valueOf(false))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	public int getLightValue(BlockState state) {
		return state.get(LIT) ? 15 : 0;
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {

		if (state.get(LIT)) {
			CMEvents.soundStoneButton_Off(worldIn, pos);
			worldIn.setBlockState(pos, state.with(LIT, Boolean.valueOf(false))); }

		else { //!LIT
			CMEvents.soundStoneButton_On(worldIn, pos);
			worldIn.setBlockState(pos, state.with(LIT, Boolean.valueOf(true))); }

		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, LIT, WATERLOGGED);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.get(FACING);

		switch (direction) {
		default:
		case UP: return AABB_UP;
		case DOWN: return AABB_DOWN;
		case NORTH: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		}
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_lamp").applyTextStyle(TextFormatting.GRAY));
	}
}
