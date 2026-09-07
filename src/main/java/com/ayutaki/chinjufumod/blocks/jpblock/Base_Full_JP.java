package com.ayutaki.chinjufumod.blocks.jpblock;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.JPBlock_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Base_Full_JP extends Regi_addState {
	/* Property */
	public static final PropertyInteger STAGE_0_15 = PropertyInteger.create("stage", 0, 15);

	public Base_Full_JP(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(2.0F);
		setResistance(10.0F);

		setDefaultState(this.blockState.getBaseState().withProperty(STAGE_0_15, Integer.valueOf(0)));
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		if (i == 0) { return MapColor.SNOW; }
		if (i == 1) { return MapColor.ADOBE; }
		if (i == 2) { return MapColor.MAGENTA; }
		if (i == 3) { return MapColor.LIGHT_BLUE; }
		if (i == 4) { return MapColor.YELLOW; }
		if (i == 5) { return MapColor.LIME; }
		if (i == 6) { return MapColor.PINK; }
		if (i == 7) { return MapColor.GRAY; }
		if (i == 8) { return MapColor.SILVER; }
		if (i == 9) { return MapColor.CYAN; }
		if (i == 10) { return MapColor.PURPLE; }
		if (i == 11) { return MapColor.BLUE; }
		if (i == 12) { return MapColor.BROWN; }
		if (i == 13) { return MapColor.GREEN; }
		if (i == 14) { return MapColor.RED; }
		else { return MapColor.BLACK; }
	} //Because it's exterior, add this.
	
	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();

		if (hItem instanceof Base_Hake) { return false; }
		
		else {
			if (hStack.isEmpty()) {
				if (playerIn.isSneaking()) {
					CMEvents.soundStonePlace(worldIn, pos);
					worldIn.setBlockState(pos, this.takeBlock().getDefaultState()
							.withProperty(Base_Full_JP.STAGE_0_15, Integer.valueOf(i))); }
				return true;
			}
		}
		return false;
	}
	
	private Block takeBlock() {
		if (this instanceof Kawara_Crash) { return JPBlock_Blocks.KAWARA; }
		if (this instanceof Plaster) { return JPBlock_Blocks.SHIKKUI_CR; }
		if (this instanceof Plaster_Crash) { return JPBlock_Blocks.SHIKKUI; }
		if (this instanceof Namako) { return JPBlock_Blocks.NAMAKO_CR; }
		if (this instanceof Namako_Crash) { return JPBlock_Blocks.NAMAKO; }
		if (this instanceof NamakoB) { return JPBlock_Blocks.NAMAKOB_CR; }
		if (this instanceof NamakoB_Crash) { return JPBlock_Blocks.NAMAKOB; }
		else { return JPBlock_Blocks.KAWARA_CR; }
	}
	
	/* Data value */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_15)).intValue();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE_0_15 });
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
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
		stack.add(new ItemStack(this.cloneItem(), 1, i));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		return new ItemStack(this.cloneItem(), 1, i);
	}
	
	private Item cloneItem() {
		if (this instanceof Plaster || this instanceof Plaster_Crash) { return Items_Wablock.SHIKKUI; }
		if (this instanceof Namako || this instanceof Namako_Crash) { return Items_Wablock.NAMAKO; }
		if (this instanceof NamakoB || this instanceof NamakoB_Crash) { return Items_Wablock.NAMAKOB; }
		else { return Items_Wablock.KAWARA; }
	}
}
