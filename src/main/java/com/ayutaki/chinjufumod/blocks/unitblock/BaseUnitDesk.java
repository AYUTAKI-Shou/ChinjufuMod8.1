package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BaseUnitDesk extends Regi_addState {
	/* Property */
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");

	public static final PropertyInteger STAGE_0_8 = PropertyInteger.create("color", 0, 8);

	public BaseUnitDesk(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(NORTH, false)
				.withProperty(EAST, false)
				.withProperty(SOUTH, false)
				.withProperty(WEST, false)
				.withProperty(STAGE_0_8, Integer.valueOf(0)));
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		int i = ((Integer)state.getValue(STAGE_0_8)).intValue();
		
		EnumFacing playerFacing = playerIn.getHorizontalFacing().getOpposite();
		IBlockState upState = worldIn.getBlockState(pos.up());
		
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		boolean upAble = upState.getMaterial().isReplaceable();
		boolean success = (hItem == Items_Chinjufu.SHOUHOU_empty || hItem == Items.BOOK);
		
		if (success) {
			if (upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				Block bookBlock = (hItem == Items.BOOK)? Chinjufu_Blocks.DESKBOOK_1 : Chinjufu_Blocks.NOTEBOOK;
				
				worldIn.setBlockState(pos.up(), bookBlock.getDefaultState()
						.withProperty(BaseStage4_FaceDown.H_FACING, playerFacing)
						.withProperty(BaseStage4_FaceDown.STAGE_1_4, Integer.valueOf(1))); }
			return true; }
		
		if (hStack.isEmpty()) {
			if (playerIn.isSneaking()) {
				CMEvents.soundWoodPlace(worldIn, pos);
				worldIn.setBlockState(pos, takeBlock().getDefaultState()
						.withProperty(BaseUnitDesk.STAGE_0_8, Integer.valueOf(i))); }
			return true;
		}
		
		else { return false; }
	}
	
	private Block takeBlock() {
		if (this == Unit_Blocks.UNITDESK) { return Unit_Blocks.UNITDESK_sub; }
		if (this == Unit_Blocks.UNITDESK_sub) { return Unit_Blocks.UNITDESK; }
		if (this == Unit_Blocks.CAFETABLE) { return Unit_Blocks.CAFETABLE_sub; }
		if (this == Unit_Blocks.CAFETABLE_sub) { return Unit_Blocks.CAFETABLE; }
		
		if (this == Unit_Blocks.LOWDESK) { return Unit_Blocks.LOWDESK_sub; }
		if (this == Unit_Blocks.LOWDESK_sub) { return Unit_Blocks.LOWDESK; }
		if (this == Unit_Blocks.CHABUDAI) { return Unit_Blocks.CHABUDAI_sub; }
		if (this == Unit_Blocks.CHABUDAI_sub) { return Unit_Blocks.CHABUDAI; }
		if (this == Unit_Blocks.KOTATSU) { return Unit_Blocks.KOTATSU_sub; }
		else { return Unit_Blocks.KOTATSU; }
	}
	
	/* Connect the blocks. */
	private boolean canConnectTo(IBlockAccess worldIn, BlockPos source, EnumFacing direction, int i) {
		IBlockState state = worldIn.getBlockState(source.offset(direction));
		return state.getBlock() == this && ((Integer)state.getValue(STAGE_0_8)).intValue() == i;
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_0_8)).intValue();
		boolean south = canConnectTo(worldIn, pos, EnumFacing.SOUTH, i);
		boolean north = canConnectTo(worldIn, pos, EnumFacing.NORTH, i);
		boolean west = canConnectTo(worldIn, pos, EnumFacing.WEST, i);
		boolean east = canConnectTo(worldIn, pos, EnumFacing.EAST, i);
		return state.withProperty(NORTH, north).withProperty(EAST, east).withProperty(SOUTH, south).withProperty(WEST, west);
	}

	/* Data value */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_8, Integer.valueOf(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_8)).intValue();
	}

	@Override
	public boolean isSideSolid(IBlockState baseState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		if (side == EnumFacing.UP) { return true; }
		return false;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, SOUTH, WEST, STAGE_0_8 });
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

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}
}
