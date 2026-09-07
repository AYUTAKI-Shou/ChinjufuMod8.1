package com.ayutaki.chinjufumod.blocks.amado;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage2_Face;
import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TobukuroWindowR extends BaseStage3_Face {
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, -0.03125, -0.03125, 0.1875, 1.03125, 1.03125);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, -0.03125, -0.03125, 0.1875, 1.03125, 1.03125);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, -0.03125, -0.03125, 0.1875, 1.03125, 1.03125);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, -0.03125, -0.03125, 0.1875, 1.03125, 1.03125);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	/** 1=2 sheets, 2=1 sheet, 3=Zero **/
	public TobukuroWindowR(String name) {
		super(name);
		setHardness(2.0F);
		setResistance(5.0F);
		setSoundType(SoundType.WOOD);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		/** 1=2 sheets, 2=1 sheet, 3=Zero **/
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		EnumFacing direction = state.getValue(H_FACING);

		IBlockState northState = worldIn.getBlockState(pos.north());
		IBlockState southState = worldIn.getBlockState(pos.south());
		IBlockState eastState = worldIn.getBlockState(pos.east());
		IBlockState westState = worldIn.getBlockState(pos.west());

		if (i == 3) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 3
			CMEvents.soundAmadoWin(worldIn, pos);
			IBlockState AMADO_FACE_1 = this.takeBlock().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING))
					.withProperty(BaseStage2_Face.STAGE_1_2, Integer.valueOf(1));
			
			switch (direction) {
			case NORTH :
			default :
				if (westState.getMaterial().isReplaceable()) {
					worldIn.setBlockState(pos.west(), AMADO_FACE_1);
					this.updateTOBUKURO(state, worldIn, pos); }
				
				else { } //!Replaceable
				break;

			case SOUTH :
				if (eastState.getMaterial().isReplaceable()) {
					worldIn.setBlockState(pos.east(), AMADO_FACE_1);
					this.updateTOBUKURO(state, worldIn, pos); }
				
				else { } //!Replaceable
				break;

			case EAST :
				if (northState.getMaterial().isReplaceable()) {
					worldIn.setBlockState(pos.north(), AMADO_FACE_1);
					this.updateTOBUKURO(state, worldIn, pos); }
				
				else { } //!Replaceable
				break;
				
			case WEST :
				if (southState.getMaterial().isReplaceable()) {
					worldIn.setBlockState(pos.south(), AMADO_FACE_1);
					this.updateTOBUKURO(state, worldIn, pos); }
				
				else { } //!Replaceable
				break;
			} // switch
		}
		return true;
	}

	private void updateTOBUKURO(IBlockState state, World worldIn, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		CMEvents.soundFusumaS(worldIn, pos);
		worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)));
	}
	
	private Block takeBlock() {
		if (this == Slidedoor_Blocks.TOBUKURO_WINR) { return Slidedoor_Blocks.AMADO_WIN; }
		else { return Slidedoor_Blocks.AMADOS_WIN; }
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}

	/* A torch can be placed on top. true or false */
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(cloneItem(), 1, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}
	
	private Item cloneItem() {
		if (this == Slidedoor_Blocks.TOBUKURO_WINR) { return Items_Wadeco.TOBUKURO_WINR; }
		else { return Items_Wadeco.TOBUKUROS_WINR; }
	}
}
