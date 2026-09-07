package com.ayutaki.chinjufumod.blocks.jpdeco;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.fuel.Seasonal_Slab150;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;
import com.ayutaki.chinjufumod.state.SlabHalf;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Tatami_Y extends BaseTatami {

	public Tatami_Y(String name) {
		super(name);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		EnumFacing direction = state.getValue(H_FACING);
		
		boolean block2Item = ((this == JPDeco_Blocks.TATAMIY && hItem == Items_Wadeco.TATAMIY) ||
				(this == JPDeco_Blocks.TATAMIY_white && hItem == Items_Wadeco.TATAMIY_white) ||
				(this == JPDeco_Blocks.TATAMIY_orange && hItem == Items_Wadeco.TATAMIY_orange) ||
				(this == JPDeco_Blocks.TATAMIY_magenta && hItem == Items_Wadeco.TATAMIY_magenta) ||
				(this == JPDeco_Blocks.TATAMIY_yellow && hItem == Items_Wadeco.TATAMIY_yellow) ||
				(this == JPDeco_Blocks.TATAMIY_lime && hItem == Items_Wadeco.TATAMIY_lime) ||
				(this == JPDeco_Blocks.TATAMIY_pink && hItem == Items_Wadeco.TATAMIY_pink) ||
				(this == JPDeco_Blocks.TATAMIY_gray && hItem == Items_Wadeco.TATAMIY_gray) ||
				(this == JPDeco_Blocks.TATAMIY_lightb && hItem == Items_Wadeco.TATAMIY_lightb) ||
				(this == JPDeco_Blocks.TATAMIY_lightg && hItem == Items_Wadeco.TATAMIY_lightg) ||
				(this == JPDeco_Blocks.TATAMIY_cyan && hItem == Items_Wadeco.TATAMIY_cyan) ||
				(this == JPDeco_Blocks.TATAMIY_purple && hItem == Items_Wadeco.TATAMIY_purple) ||
				(this == JPDeco_Blocks.TATAMIY_blue && hItem == Items_Wadeco.TATAMIY_blue) ||
				(this == JPDeco_Blocks.TATAMIY_brown && hItem == Items_Wadeco.TATAMIY_brown) ||
				(this == JPDeco_Blocks.TATAMIY_green && hItem == Items_Wadeco.TATAMIY_green) ||
				(this == JPDeco_Blocks.TATAMIY_red && hItem == Items_Wadeco.TATAMIY_red) ||
				(this == JPDeco_Blocks.TATAMIY_black && hItem == Items_Wadeco.TATAMIY_black));
		
		boolean blockSlab = (hItem == new ItemStack(Blocks.WOODEN_SLAB).getItem() || hItem instanceof Seasonal_Slab150);
				
		/* Slab */
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
						return true; } }
			} //block2Item
			
			if (blockSlab) {
				if (state.getValue(HALF) == SlabHalf.TOP && (double)hitY <= 0.6D) {
					int k = hStack.getMetadata();
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					
					if (this == JPDeco_Blocks.TATAMIY) {
						switch (direction) {
						case NORTH :
						default :
						case SOUTH :
							worldIn.setBlockState(pos, JPDeco_Blocks.TATAMIY_WOOD_ns.getDefaultState().withProperty(TatamiWood.STAGE_0_8, takeMeta(hItem, k)));
							break;
						case EAST :
						case WEST :
							worldIn.setBlockState(pos, JPDeco_Blocks.TATAMIY_WOOD_ew.getDefaultState().withProperty(TatamiWood.STAGE_0_8, takeMeta(hItem, k)));
							break; } }
					
					if (this != JPDeco_Blocks.TATAMIY) {
						switch (direction) {
						case NORTH :
						default :
						case SOUTH :
							worldIn.setBlockState(pos, takeNS(hItem, k).getDefaultState().withProperty(BaseTatamiWood.STAGE_0_15, takeColor()));
							break;
						case EAST :
						case WEST :
							worldIn.setBlockState(pos, takeEW(hItem, k).getDefaultState().withProperty(BaseTatamiWood.STAGE_0_15, takeColor()));
							break; } }
					return true; } //SlabHalf.TOP
			} //blockSlab
			return false; }
		/** 側面で設置可能にするため false **/
		return false;
	}

	private Block takeNS(Item hItem, int k) {
		if (hItem == new ItemStack(Blocks.WOODEN_SLAB).getItem()) {

			if (k == 0) { return JPDeco_Blocks.TATAMIY_OAK_ns; }
			if (k == 1) { return JPDeco_Blocks.TATAMIY_SPRUCE_ns; }
			if (k == 2) { return JPDeco_Blocks.TATAMIY_BIRCH_ns; }
			if (k == 3) { return JPDeco_Blocks.TATAMIY_JUNGLE_ns; }
			if (k == 4) { return JPDeco_Blocks.TATAMIY_ACACIA_ns; }
			if (k == 5) { return JPDeco_Blocks.TATAMIY_DOAK_ns; } }
		
		if (hItem == Items_Seasonal.SAKURA_slabhalf) { return JPDeco_Blocks.TATAMIY_SAKURA_ns; }
		if (hItem == Items_Seasonal.KAEDE_slabhalf) { return JPDeco_Blocks.TATAMIY_KAEDE_ns; }
		else { return JPDeco_Blocks.TATAMIY_ICHOH_ns; }
	}
	
	private Block takeEW(Item hItem, int k) {
		if (hItem == new ItemStack(Blocks.WOODEN_SLAB).getItem()) {
	
			if (k == 0) { return JPDeco_Blocks.TATAMIY_OAK_ew; }
			if (k == 1) { return JPDeco_Blocks.TATAMIY_SPRUCE_ew; }
			if (k == 2) { return JPDeco_Blocks.TATAMIY_BIRCH_ew; }
			if (k == 3) { return JPDeco_Blocks.TATAMIY_JUNGLE_ew; }
			if (k == 4) { return JPDeco_Blocks.TATAMIY_ACACIA_ew; }
			if (k == 5) { return JPDeco_Blocks.TATAMIY_DOAK_ew; } }
		
		if (hItem == Items_Seasonal.SAKURA_slabhalf) { return JPDeco_Blocks.TATAMIY_SAKURA_ew; }
		if (hItem == Items_Seasonal.KAEDE_slabhalf) { return JPDeco_Blocks.TATAMIY_KAEDE_ew; }
		else { return JPDeco_Blocks.TATAMIY_ICHOH_ew; }
	}
	
	private int takeMeta(Item hItem, int k) {
		if (hItem == new ItemStack(Blocks.WOODEN_SLAB).getItem() && k <= 5) { return k; }
		if (hItem == Items_Seasonal.SAKURA_slabhalf) { return 6; }
		if (hItem == Items_Seasonal.KAEDE_slabhalf) { return 7; }
		else { return 8; }
	}
	
	private int takeColor() {
		if (this == JPDeco_Blocks.TATAMIY_white) { return 0; }
		if (this == JPDeco_Blocks.TATAMIY_orange) { return 1; }
		if (this == JPDeco_Blocks.TATAMIY_magenta) { return 2; }
		if (this == JPDeco_Blocks.TATAMIY_lightb) { return 3; }
		if (this == JPDeco_Blocks.TATAMIY_yellow) { return 4; }
		if (this == JPDeco_Blocks.TATAMIY_lime) { return 5; }
		if (this == JPDeco_Blocks.TATAMIY_pink) { return 6; }
		if (this == JPDeco_Blocks.TATAMIY_gray) { return 7; }
		if (this == JPDeco_Blocks.TATAMIY_lightg) { return 8; }
		if (this == JPDeco_Blocks.TATAMIY_cyan) { return 9; }
		if (this == JPDeco_Blocks.TATAMIY_purple) { return 10; }
		if (this == JPDeco_Blocks.TATAMIY_blue) { return 11; }
		if (this == JPDeco_Blocks.TATAMIY_brown) { return 12; }
		if (this == JPDeco_Blocks.TATAMIY_green) { return 13; }
		if (this == JPDeco_Blocks.TATAMIY_red) { return 14; }
		else { return 15; }
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int w = state.getValue(DOUBLE)? 2 : 1;
		stack.add(new ItemStack(cloneItem(), w, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}
	
	private Item cloneItem() {
		if (this == JPDeco_Blocks.TATAMIY) { return Items_Wadeco.TATAMIY; }
		if (this == JPDeco_Blocks.TATAMIY_white) { return Items_Wadeco.TATAMIY_white; }
		if (this == JPDeco_Blocks.TATAMIY_orange) { return Items_Wadeco.TATAMIY_orange; }
		if (this == JPDeco_Blocks.TATAMIY_magenta) { return Items_Wadeco.TATAMIY_magenta; }
		if (this == JPDeco_Blocks.TATAMIY_lightb) { return Items_Wadeco.TATAMIY_lightb; }
		if (this == JPDeco_Blocks.TATAMIY_yellow) { return Items_Wadeco.TATAMIY_yellow; }
		if (this == JPDeco_Blocks.TATAMIY_lime) { return Items_Wadeco.TATAMIY_lime; }
		if (this == JPDeco_Blocks.TATAMIY_pink) { return Items_Wadeco.TATAMIY_pink; }
		if (this == JPDeco_Blocks.TATAMIY_gray) { return Items_Wadeco.TATAMIY_gray; }
		if (this == JPDeco_Blocks.TATAMIY_lightg) { return Items_Wadeco.TATAMIY_lightg; }
		if (this == JPDeco_Blocks.TATAMIY_cyan) { return Items_Wadeco.TATAMIY_cyan; }
		if (this == JPDeco_Blocks.TATAMIY_purple) { return Items_Wadeco.TATAMIY_purple; }
		if (this == JPDeco_Blocks.TATAMIY_blue) { return Items_Wadeco.TATAMIY_blue; }
		if (this == JPDeco_Blocks.TATAMIY_brown) { return Items_Wadeco.TATAMIY_brown; }
		if (this == JPDeco_Blocks.TATAMIY_green) { return Items_Wadeco.TATAMIY_green; }
		if (this == JPDeco_Blocks.TATAMIY_red) { return Items_Wadeco.TATAMIY_red; }
		else { return Items_Wadeco.TATAMIY_black; }
	}
}
