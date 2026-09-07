package com.ayutaki.chinjufumod.blocks.pantry;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlabW;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Pantry_Blocks;
import com.ayutaki.chinjufumod.state.SlabHalf;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Pantry_Sack extends BaseFacingSlabW {
	/* Collision */
	private static final AxisAlignedBB DOUBLE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	private static final AxisAlignedBB BOTTOM_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
	private static final AxisAlignedBB TOP_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	private static final AxisAlignedBB TOP_COLL = new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);

	public Pantry_Sack(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.TEATIME);
		
		setSoundType(SoundType.PLANT);
		setHardness(1.0F);
		setResistance(5.0F);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		boolean block2Item = ((this == Pantry_Blocks.BOX_H_COCO && hItem == Items_Teatime.BOX_H_COCO) ||
				(this == Pantry_Blocks.BOX_H_FLOUR && hItem == Items_Teatime.BOX_H_FLOUR) ||
				(this == Pantry_Blocks.BOX_H_AZUKI && hItem == Items_Teatime.BOX_H_AZUKI) ||
				(this == Pantry_Blocks.BOX_H_RICE && hItem == Items_Teatime.BOX_H_RICE) ||
				(this == Pantry_Blocks.BOX_H_SOY && hItem == Items_Teatime.BOX_H_SOY) ||
				(this == Pantry_Blocks.BOX_H_TGREEN && hItem == Items_Teatime.BOX_H_TGREEN) ||
				(this == Pantry_Blocks.BOX_H_TRED && hItem == Items_Teatime.BOX_H_TRED) ||
				(this == Pantry_Blocks.BOX_H_KURI && hItem == Items_Teatime.BOX_H_KURI) ||
				
				(this == Pantry_Blocks.BOX_H_BPEPPER && hItem == Items_Teatime.BOX_H_BPEPPER) ||
				(this == Pantry_Blocks.BOX_H_CHILI && hItem == Items_Teatime.BOX_H_CHILI) ||
				(this == Pantry_Blocks.BOX_H_CUMIN && hItem == Items_Teatime.BOX_H_CUMIN) ||
				(this == Pantry_Blocks.BOX_H_TURMERIC && hItem == Items_Teatime.BOX_H_TURMERIC));
				
		if (hStack.isEmpty()) {
			boolean stateW = state.getValue(DOUBLE);
			int gHC = stateW? 2 : 1;
			CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, this.takeItem(), 8 * gHC, this.takeMeta());
			
			worldIn.setBlockState(pos, Pantry_Blocks.BOX_H_EMPTY3.getDefaultState()
					.withProperty(BaseFacingSlabW.H_FACING, state.getValue(H_FACING))
					.withProperty(BaseFacingSlabW.DOUBLE, state.getValue(DOUBLE))
					.withProperty(BaseFacingSlabW.HALF, state.getValue(HALF)));
			return true; }
		
		if (hItem instanceof ItemBlock) { 
			if (block2Item) {
				if (!state.getValue(DOUBLE)) {
					if (state.getValue(HALF) != SlabHalf.TOP && facing == EnumFacing.UP) {
						CMEvents.ItemBlock_Grass(worldIn, pos, playerIn, hand);
						worldIn.setBlockState(pos, state.cycleProperty(DOUBLE), 2);
						return true; }
					
					if (state.getValue(HALF) == SlabHalf.TOP && (double)hitY <= 0.6D) {
						CMEvents.ItemBlock_Grass(worldIn, pos, playerIn, hand);
						worldIn.setBlockState(pos, state.cycleProperty(DOUBLE), 2);
						return true; } } }
			return false; }

		return true;
	}

	private Item takeItem() {
		if (this == Pantry_Blocks.BOX_H_COCO) { return Items.DYE; }
		if (this == Pantry_Blocks.BOX_H_FLOUR) { return Items.WHEAT; }
		
		if (this == Pantry_Blocks.BOX_H_AZUKI) { return Items_Teatime.SEEDS_AZUKI; }
		if (this == Pantry_Blocks.BOX_H_RICE) { return Items_Teatime.SEEDS_RICE; }
		if (this == Pantry_Blocks.BOX_H_SOY) { return Items_Teatime.SEEDS_SOY; }
		if (this == Pantry_Blocks.BOX_H_TGREEN) { return Items_Teatime.CHADUTSU; }
		if (this == Pantry_Blocks.BOX_H_TRED) { return Items_Teatime.CANTEA; }
		if (this == Pantry_Blocks.BOX_H_KURI) { return Items_Seasonal.KURI; }
		
		if (this == Pantry_Blocks.BOX_H_BPEPPER) { return Items_Teatime.SPICE; }
		if (this == Pantry_Blocks.BOX_H_CHILI) { return Items_Teatime.SPICE; }
		if (this == Pantry_Blocks.BOX_H_CUMIN) { return Items_Teatime.SPICE_NAE; }
		else { return Items_Teatime.SPICE_NAE; }
	}
	
	private int takeMeta() {
		if (this == Pantry_Blocks.BOX_H_COCO) { return 3; }
		if (this == Pantry_Blocks.BOX_H_BPEPPER || this == Pantry_Blocks.BOX_H_CUMIN) { return 1; }
		if (this == Pantry_Blocks.BOX_H_CHILI || this == Pantry_Blocks.BOX_H_TURMERIC) { return 2; }
		else { return 0; }
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (state.getValue(DOUBLE)) { return DOUBLE_AABB; }

		else { return (state.getValue(HALF) == SlabHalf.TOP)? TOP_AABB : BOTTOM_AABB; }
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean t_f) {
		boolean flag = state.getValue(DOUBLE);
		SlabHalf blockhalf = state.getValue(HALF);

		switch(blockhalf) {
		case TOP :
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, flag? DOUBLE_AABB : TOP_COLL); /** flag? true : false; **/
			break;
			
		case BOTTOM :
		default:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, flag? DOUBLE_AABB : BOTTOM_AABB);
			break;
		}
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
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}

	/*Drop Item and Clone Item.*/
	@Override
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return true;
	}
	
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		int gHC = state.getValue(DOUBLE)? 2 : 1;
		return new ItemStack(cloneItem(), gHC, 0);
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int gHC = state.getValue(DOUBLE)? 2 : 1;

		if (this == Pantry_Blocks.BOX_H_COCO) { stack.add(new ItemStack(Items.DYE, 8 * gHC, 3)); }
		if (this == Pantry_Blocks.BOX_H_FLOUR) { stack.add(new ItemStack(Items.WHEAT, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_AZUKI) { stack.add(new ItemStack(Items_Teatime.SEEDS_AZUKI, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_RICE) { stack.add(new ItemStack(Items_Teatime.SEEDS_RICE, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_SOY) { stack.add(new ItemStack(Items_Teatime.SEEDS_SOY, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_TGREEN) { stack.add(new ItemStack(Items_Teatime.CHADUTSU, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_TRED) { stack.add(new ItemStack(Items_Teatime.CANTEA, 8 * gHC, 0)); }
		if (this == Pantry_Blocks.BOX_H_KURI) { stack.add(new ItemStack(Items_Seasonal.KURI, 8 * gHC, 0)); }
		
		if (this == Pantry_Blocks.BOX_H_BPEPPER) { stack.add(new ItemStack(Items_Teatime.SPICE, 8 * gHC, 1)); }
		if (this == Pantry_Blocks.BOX_H_CUMIN) { stack.add(new ItemStack(Items_Teatime.SPICE_NAE, 8 * gHC, 1)); }
		if (this == Pantry_Blocks.BOX_H_TURMERIC) { stack.add(new ItemStack(Items_Teatime.SPICE_NAE, 8 * gHC, 2)); }
		if (this == Pantry_Blocks.BOX_H_CHILI) { stack.add(new ItemStack(Items_Teatime.SPICE, 8 * gHC, 2)); }
		
		stack.add(new ItemStack(Items_Teatime.BOX_H_EMPTY, gHC, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}
	
	private Item cloneItem() {
		if (this == Pantry_Blocks.BOX_H_COCO) { return Items_Teatime.BOX_H_COCO; }
		if (this == Pantry_Blocks.BOX_H_FLOUR) { return Items_Teatime.BOX_H_FLOUR; }
		if (this == Pantry_Blocks.BOX_H_AZUKI) { return Items_Teatime.BOX_H_AZUKI; }
		if (this == Pantry_Blocks.BOX_H_RICE) { return Items_Teatime.BOX_H_RICE; }
		if (this == Pantry_Blocks.BOX_H_SOY) { return Items_Teatime.BOX_H_SOY; }
		if (this == Pantry_Blocks.BOX_H_TGREEN) { return Items_Teatime.BOX_H_TGREEN; }
		if (this == Pantry_Blocks.BOX_H_TRED) { return Items_Teatime.BOX_H_TRED; }
		if (this == Pantry_Blocks.BOX_H_KURI) { return Items_Teatime.BOX_H_KURI; }
		
		if (this == Pantry_Blocks.BOX_H_BPEPPER) { return Items_Teatime.BOX_H_BPEPPER; }
		if (this == Pantry_Blocks.BOX_H_CUMIN) { return Items_Teatime.BOX_H_CUMIN; }
		if (this == Pantry_Blocks.BOX_H_TURMERIC) { return Items_Teatime.BOX_H_TURMERIC; }
		else { return Items_Teatime.BOX_H_CHILI; }
	}
}
