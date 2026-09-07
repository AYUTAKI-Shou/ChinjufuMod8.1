package com.ayutaki.chinjufumod.items.chinjufu;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.chair.DiningChair;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Chinjufu;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DiningChair_CT extends IR_Chinjufu {

	public DiningChair_CT(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 150;
	}
	
	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_diningchair";
		case 1:
			return "item." + "block_diningchair_s";
		case 2:
			return "item." + "block_diningchair_b";
		case 3:
			return "item." + "block_diningchair_j";
		case 4:
			return "item." + "block_diningchair_a";
		case 5:
			return "item." + "block_diningchair_d";
		case 6:
			return "item." + "block_diningchair_saku";
		case 7:
			return "item." + "block_diningchair_kae";
		case 8:
			return "item." + "block_diningchair_ich";
		}
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 6));
			items.add(new ItemStack(this, 1, 7));
			items.add(new ItemStack(this, 1, 8));
		}
	}
	
	/* Call this when you use the item. ex) Place a block. */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (facing != EnumFacing.UP) { return EnumActionResult.FAIL; }

		else {
			IBlockState state = worldIn.getBlockState(pos);
			Block block = state.getBlock();

			if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

			/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
			int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			EnumFacing direction = EnumFacing.getHorizontal(i);
			ItemStack hStack = playerIn.getHeldItem(hand);
			int k = hStack.getMetadata();
			IBlockState putState = this.int2Block(k).getDefaultState().withProperty(DiningChair.H_FACING, direction);
			
			/** Put "this.block". **/
			if (playerIn.canPlayerEdit(pos, facing, hStack) && this.int2Block(k).canPlaceBlockAt(worldIn, pos)) {
				
				worldIn.setBlockState(pos, putState.withProperty(DiningChair.HALF, DiningChair.Half.LOWER), 2);
				worldIn.setBlockState(pos.up(), putState.withProperty(DiningChair.HALF, DiningChair.Half.UPPER), 2);
				CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
				
				return EnumActionResult.SUCCESS; }

			else { return EnumActionResult.FAIL; }
		}
	}
	
	private Block int2Block(int k) {
		if (k == 0) { return Furniture_Blocks.DININGCHAIR; }
		if (k == 1) { return Furniture_Blocks.DININGCHAIR_s; }
		if (k == 2) { return Furniture_Blocks.DININGCHAIR_b; }
		if (k == 3) { return Furniture_Blocks.DININGCHAIR_j; }
		if (k == 4) { return Furniture_Blocks.DININGCHAIR_a; }
		if (k == 5) { return Furniture_Blocks.DININGCHAIR_d; }
		if (k == 6) { return Furniture_Blocks.DININGCHAIR_saku; }
		if (k == 7) { return Furniture_Blocks.DININGCHAIR_kae; }
		else { return Furniture_Blocks.DININGCHAIR_ich; }
	}
	
	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_isu.name"));
	}
}
