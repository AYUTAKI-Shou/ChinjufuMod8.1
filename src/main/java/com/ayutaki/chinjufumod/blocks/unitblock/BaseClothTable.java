package com.ayutaki.chinjufumod.blocks.unitblock;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BaseClothTable extends Regi_addState {
	/* Property */
	public static final PropertyInteger STAGE_0_15 = PropertyInteger.create("color", 0, 15);
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	
	public BaseClothTable(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(NORTH, false)
				.withProperty(EAST, false)
				.withProperty(SOUTH, false)
				.withProperty(WEST, false)
				.withProperty(STAGE_0_15, Integer.valueOf(0)));
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
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
		
		else { return false; }
	}

	
	/* Connect the blocks. */
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		boolean south = worldIn.getBlockState(pos.south()).getBlock() == this;
		boolean north = worldIn.getBlockState(pos.north()).getBlock() == this;
		boolean west = worldIn.getBlockState(pos.west()).getBlock() == this;
		boolean east = worldIn.getBlockState(pos.east()).getBlock() == this;
		return state.withProperty(NORTH, north).withProperty(EAST, east).withProperty(SOUTH, south).withProperty(WEST, west);
	}
	
	/* Data value */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_15)).intValue();
	}

	@Override
	public boolean isSideSolid(IBlockState baseState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		if (side == EnumFacing.UP) { return true; }
		return false;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, SOUTH, WEST, STAGE_0_15 });
	}

	/* A torch can be placed on the side. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
	
	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return true;
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

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		stack.add(new ItemStack(Item.getItemFromBlock(Blocks.CARPET), 1, i));
		stack.add(new ItemStack(Items_Chinjufu.CAFETABLE_item, 1, takeItemMeta()));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Chinjufu.CAFETABLE_item, 1, takeItemMeta());
	}
	
	private int takeItemMeta() {
		if ((this == Unit_Blocks.CLOTHTABLE_oak) || (this == Unit_Blocks.CLOTHTABLE_oaksub)) { return 0; }
		if ((this == Unit_Blocks.CLOTHTABLE_spruce) || (this == Unit_Blocks.CLOTHTABLE_sprucesub)) { return 1; }
		if ((this == Unit_Blocks.CLOTHTABLE_birch) || (this == Unit_Blocks.CLOTHTABLE_birchsub)) { return 2; }
		if ((this == Unit_Blocks.CLOTHTABLE_jungle) || (this == Unit_Blocks.CLOTHTABLE_junglesub)) { return 3; }
		if ((this == Unit_Blocks.CLOTHTABLE_acacia) || (this == Unit_Blocks.CLOTHTABLE_acaciasub)) { return 4; }
		if ((this == Unit_Blocks.CLOTHTABLE_darkoak) || (this == Unit_Blocks.CLOTHTABLE_darkoaksub)) { return 5; }
		if ((this == Unit_Blocks.CLOTHTABLE_sakura) || (this == Unit_Blocks.CLOTHTABLE_sakurasub)) { return 6; }
		if ((this == Unit_Blocks.CLOTHTABLE_kaede) || (this == Unit_Blocks.CLOTHTABLE_kaedesub)) { return 7; }
		else { return 8; }
	}
}
