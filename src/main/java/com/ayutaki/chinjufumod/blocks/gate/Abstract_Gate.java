package com.ayutaki.chinjufumod.blocks.gate;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.doors.Gate_Blocks;
import com.ayutaki.chinjufumod.state.HalfState;
import com.ayutaki.chinjufumod.state.HingeState;

import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Abstract_Gate extends Regi_addState {
	/* Property */
	public static final PropertyDirection H_FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool OPEN = PropertyBool.create("open");
	public static final PropertyEnum<HingeState> HINGE = PropertyEnum.<HingeState>create("hinge", HingeState.class);
	public static final PropertyEnum<HalfState> HALF = PropertyEnum.<HalfState>create("half", HalfState.class);
	public static final PropertyBool POWERED = PropertyBool.create("powered");
	
	public Abstract_Gate(String name) {
		super(name);
		
		setDefaultState(this.blockState.getBaseState().withProperty(H_FACING, EnumFacing.NORTH)
				.withProperty(OPEN, Boolean.valueOf(false))
				.withProperty(HINGE, HingeState.RIGHT)
				.withProperty(POWERED, Boolean.valueOf(false))
				.withProperty(HALF, HalfState.UPPER));
	}
	
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		return isOpen(combineMetadata(worldIn, pos));
	}

	public static boolean isOpen(IBlockAccess worldIn, BlockPos pos) {
		return isOpen(combineMetadata(worldIn, pos));
	}

	public static EnumFacing getFacing(IBlockAccess worldIn, BlockPos pos) {
		return getFacing(combineMetadata(worldIn, pos));
	}

	public static int combineMetadata(IBlockAccess worldIn, BlockPos pos) {

		IBlockState state = worldIn.getBlockState(pos);
		int i = state.getBlock().getMetaFromState(state);
		boolean flag = isTop(i);
		IBlockState downState = worldIn.getBlockState(pos.down());
		int j = downState.getBlock().getMetaFromState(downState);
		int k = flag? j : i;
		IBlockState upState = worldIn.getBlockState(pos.up());
		int l = upState.getBlock().getMetaFromState(upState);
		int i1 = flag? i : l;
		boolean flag1 = (i1 & 1) != 0;
		boolean flag2 = (i1 & 2) != 0;
		return removeHalfBit(k) | (flag? 8 : 0) | (flag1 ? 16 : 0) | (flag2 ? 32 : 0);
	}

	protected static boolean isOpen(int combinedMeta) {
		return (combinedMeta & 4) != 0;
	}

	public static EnumFacing getFacing(int combinedMeta) {
		return EnumFacing.getHorizontal(combinedMeta & 3).rotateYCCW();
	}

	protected static boolean isTop(int meta) {
		return (meta & 8) != 0;
	}

	protected static int removeHalfBit(int meta) {
		return meta & 7;
	}

	public EnumPushReaction getMobilityFlag(IBlockState state) {
		return EnumPushReaction.DESTROY;
	}

	/* Play Sound */
	protected void moveSound(World worldIn, BlockPos pos, boolean open) {
		boolean WOOD = (this == Gate_Blocks.GATE_SPRUCE || this == Gate_Blocks.GATE_SPRUCE2 || this == Gate_Blocks.GATE_SPRUCE_B || this == Gate_Blocks.GATE_SPRUCE_B2);
		boolean THICK = (this == Gate_Blocks.GATE_IRON || this == Gate_Blocks.GATE_IRON2);
		
		if (open) { 
			worldIn.playSound(null, pos, WOOD? SoundEvents_CM.GATE_WOOD : SoundEvents_CM.GATE_IRON_OPEN, SoundCategory.BLOCKS, 1.0F, 1.0F); }
		
		else { 
			worldIn.playSound(null, pos, WOOD? SoundEvents_CM.GATE_WOOD : (THICK? SoundEvents_CM.GATE_IRON_CLOSE : SoundEvents_CM.GATE_IRON_OPEN), SoundCategory.BLOCKS, 1.0F, 1.0F); }
	}

	/* A place where you can put it. */
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getMaterial().isReplaceable() && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable();
	}
	
	/* Methods for BlockState. */
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {

		if (state.getValue(HALF) == HalfState.LOWER) {
			IBlockState upState = worldIn.getBlockState(pos.up());

			if (upState.getBlock() == this) { 
				state = state.withProperty(HINGE, upState.getValue(HINGE)).withProperty(POWERED, upState.getValue(POWERED)); }
		}

		else {
			IBlockState downState = worldIn.getBlockState(pos.down());

			if (downState.getBlock() == this) {
				state = state.withProperty(H_FACING, downState.getValue(H_FACING)).withProperty(OPEN, downState.getValue(OPEN)); }
		}
		return state;
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.getValue(HALF) != HalfState.LOWER ? state : state.withProperty(H_FACING, rot.rotate((EnumFacing)state.getValue(H_FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return mirrorIn == Mirror.NONE ? state : state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(H_FACING))).cycleProperty(HINGE);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return (meta & 8) > 0 ? this.getDefaultState().withProperty(HALF, HalfState.UPPER)
				.withProperty(HINGE, (meta & 1) > 0 ? HingeState.RIGHT : HingeState.LEFT)
				.withProperty(POWERED, Boolean.valueOf((meta & 2) > 0)) : this.getDefaultState()
				.withProperty(HALF, HalfState.LOWER)
				.withProperty(H_FACING, EnumFacing.getHorizontal(meta & 3).rotateYCCW())
				.withProperty(OPEN, Boolean.valueOf((meta & 4) > 0));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;

		if (state.getValue(HALF) == HalfState.UPPER) {
			i = i | 8;

			if (state.getValue(HINGE) == HingeState.RIGHT) { i |= 1; }

			if (((Boolean)state.getValue(POWERED)).booleanValue()) { i |= 2; }
		}

		else {
			i = i | ((EnumFacing)state.getValue(H_FACING)).rotateY().getHorizontalIndex();

			if (((Boolean)state.getValue(OPEN)).booleanValue()) { i |= 4; }
		}
		return i;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { HALF, H_FACING, OPEN, HINGE, POWERED });
	}

	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* Rendering */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	/* Control the drop item. */
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		
		if (state.getValue(HALF) != HalfState.LOWER) { stack.add(new ItemStack(Items.AIR, 1, 0)); }
		if (state.getValue(HALF) == HalfState.LOWER) { stack.add(cloneStack()); }
		return stack;
	}
	
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return cloneStack();
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		if (this == Gate_Blocks.GATE_SPRUCE_B || this == Gate_Blocks.GATE_SPRUCE_B2) { return new ItemStack(Items_Wadeco.GATE, 1 ,0); }
		if (this == Gate_Blocks.GATE_SPRUCE || this == Gate_Blocks.GATE_SPRUCE2) { return new ItemStack(Items_Wadeco.GATE, 1 ,1); }
		if (this == Gate_Blocks.GATE_IRON || this == Gate_Blocks.GATE_IRON2) { return new ItemStack(Items_Wadeco.GATE, 1 ,2); }
		else { return new ItemStack(Items_Wadeco.GATE,1 ,3); }
	}
}
