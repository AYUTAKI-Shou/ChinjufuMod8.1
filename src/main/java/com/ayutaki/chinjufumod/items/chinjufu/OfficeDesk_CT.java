package com.ayutaki.chinjufumod.items.chinjufu;

import com.ayutaki.chinjufumod.blocks.furniture.OfficeDesk;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Chinjufu;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OfficeDesk_CT extends IR_Chinjufu {

	public OfficeDesk_CT(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 300;
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_officedesk_oak";
		case 1:
			return "item." + "block_officedesk_spruce";
		case 2:
			return "item." + "block_officedesk_birch";
		case 3:
			return "item." + "block_officedesk_jungle";
		case 4:
			return "item." + "block_officedesk_acacia";
		case 5:
			return "item." + "block_officedesk_darkoak";
		case 6:
			return "item." + "block_officedesk_sakura";
		case 7:
			return "item." + "block_officedesk_kaede";
		case 8:
			return "item." + "block_officedesk_ichoh";
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

	/* Place block */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		EnumFacing direction = EnumFacing.fromAngle((double)playerIn.rotationYaw);
		
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		/** Put "this.block". **/
		if (playerIn.canPlayerEdit(pos, facing, hStack) && this.int2Block(k).canPlaceBlockAt(worldIn, pos)) {

			IBlockState State1 = this.int2Block(k).getDefaultState().withProperty(OfficeDesk.H_FACING, direction.getOpposite())
					.withProperty(OfficeDesk.STAGE_1_3, Integer.valueOf(1));
			IBlockState State2 = this.int2Block(k).getDefaultState().withProperty(OfficeDesk.H_FACING, direction.getOpposite())
					.withProperty(OfficeDesk.STAGE_1_3, Integer.valueOf(2));
			IBlockState State3 = this.int2Block(k).getDefaultState().withProperty(OfficeDesk.H_FACING, direction.getOpposite())
					.withProperty(OfficeDesk.STAGE_1_3, Integer.valueOf(3));
					
			boolean WE = block.isReplaceable(worldIn, new BlockPos(x + 1, y, z)) && block.isReplaceable(worldIn, new BlockPos(x - 1, y, z));
			boolean NS = block.isReplaceable(worldIn, new BlockPos(x, y, z + 1)) && block.isReplaceable(worldIn, new BlockPos(x, y, z - 1));
			
			if (direction == EnumFacing.NORTH && WE) {
				worldIn.setBlockState(new BlockPos(x - 1, y, z), State2, 3);
				worldIn.setBlockState(pos, State1, 3);
				worldIn.setBlockState(new BlockPos(x + 1, y, z), State3, 3);
				
				CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
				return EnumActionResult.SUCCESS; }
			
			if (direction == EnumFacing.SOUTH && WE) {
				worldIn.setBlockState(new BlockPos(x + 1, y, z), State2, 3);
				worldIn.setBlockState(pos, State1, 3);
				worldIn.setBlockState(new BlockPos(x - 1, y, z), State3, 3);
				
				CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
				return EnumActionResult.SUCCESS; }
			
			if (direction == EnumFacing.EAST && NS) {
				worldIn.setBlockState(new BlockPos(x, y, z - 1), State2, 3);
				worldIn.setBlockState(pos, State1, 3);
				worldIn.setBlockState(new BlockPos(x, y, z + 1), State3, 3);
				
				CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
				return EnumActionResult.SUCCESS; }
			
			if (direction == EnumFacing.WEST && NS) {
				worldIn.setBlockState(new BlockPos(x, y, z + 1), State2, 3);
				worldIn.setBlockState(pos, State1, 3);
				worldIn.setBlockState(new BlockPos(x, y, z - 1), State3, 3);
				
				CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
				return EnumActionResult.SUCCESS; }

			else { 
				CMEvents.textNoPlace(worldIn, pos, playerIn);
				return EnumActionResult.FAIL; }
		}

		else { return EnumActionResult.FAIL; }
	}

	private Block int2Block(int k) {
		if (k == 0) { return Chinjufu_Blocks.OFFICEDESK_oak; }
		if (k == 1) { return Chinjufu_Blocks.OFFICEDESK_spruce; }
		if (k == 2) { return Chinjufu_Blocks.OFFICEDESK_birch; }
		if (k == 3) { return Chinjufu_Blocks.OFFICEDESK_jungle; }
		if (k == 4) { return Chinjufu_Blocks.OFFICEDESK_acacia; }
		if (k == 5) { return Chinjufu_Blocks.OFFICEDESK_darkoak; }
		if (k == 6) { return Chinjufu_Blocks.OFFICEDESK_sakura; }
		if (k == 7) { return Chinjufu_Blocks.OFFICEDESK_kaede; }
		else { return Chinjufu_Blocks.OFFICEDESK_ichoh; }
	}
}
