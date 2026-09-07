package com.ayutaki.chinjufumod.items.chinjufu;

import com.ayutaki.chinjufumod.blocks.school.TeacherDesk;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Chinjufu;
import com.ayutaki.chinjufumod.registry.School_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TeacherDesk_CT extends IR_Chinjufu {

	public TeacherDesk_CT(String name) {
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
			return "item." + "block_teacherdesk";
		case 1:
			return "item." + "block_teacherdesk_s";
		case 2:
			return "item." + "block_teacherdesk_b";
		case 3:
			return "item." + "block_teacherdesk_j";
		case 4:
			return "item." + "block_teacherdesk_a";
		case 5:
			return "item." + "block_teacherdesk_d";
		case 6:
			return "item." + "block_teacherdesk_saku";
		case 7:
			return "item." + "block_teacherdesk_kae";
		case 8:
			return "item." + "block_teacherdesk_ich";
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
		
		boolean mode = playerIn.capabilities.isCreativeMode;
		
		/** Put "this.block". **/
		if (playerIn.canPlayerEdit(pos, facing, hStack) && this.int2Block(k).canPlaceBlockAt(worldIn, pos)) {

			IBlockState State1 = this.int2Block(k).getDefaultState().withProperty(TeacherDesk.H_FACING, direction.getOpposite())
					.withProperty(TeacherDesk.STAGE_1_3, Integer.valueOf(1));
			IBlockState State2 = this.int2Block(k).getDefaultState().withProperty(TeacherDesk.H_FACING, direction.getOpposite())
					.withProperty(TeacherDesk.STAGE_1_3, Integer.valueOf(2));
			IBlockState State3 = this.int2Block(k).getDefaultState().withProperty(TeacherDesk.H_FACING, direction.getOpposite())
					.withProperty(TeacherDesk.STAGE_1_3, Integer.valueOf(3));

			boolean WE = block.isReplaceable(worldIn, new BlockPos(x + 1, y, z)) && block.isReplaceable(worldIn, new BlockPos(x - 1, y, z));
			boolean NS = block.isReplaceable(worldIn, new BlockPos(x, y, z + 1)) && block.isReplaceable(worldIn, new BlockPos(x, y, z - 1));
			
			if (direction == EnumFacing.NORTH && WE) {
				worldIn.setBlockState(new BlockPos(x - 1, y, z), State2, 3);
				worldIn.setBlockState(pos, State1, 3);
				worldIn.setBlockState(new BlockPos(x + 1, y, z), State3, 3);
				
				worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F);
				if (!mode) { hStack.shrink(1); }
				return EnumActionResult.SUCCESS; }
			
			if (direction == EnumFacing.SOUTH && WE) {
				worldIn.setBlockState(new BlockPos(x + 1, y, z), State2, 3);
				worldIn.setBlockState(pos, State1, 3);
				worldIn.setBlockState(new BlockPos(x - 1, y, z), State3, 3);
				
				worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F);
				if (!mode) { hStack.shrink(1); }
				return EnumActionResult.SUCCESS; }
			
			if (direction == EnumFacing.EAST && NS) {
				worldIn.setBlockState(new BlockPos(x, y, z - 1), State2, 3);
				worldIn.setBlockState(pos, State1, 3);
				worldIn.setBlockState(new BlockPos(x, y, z + 1), State3, 3);
				
				worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F);
				if (!mode) { hStack.shrink(1); }
				return EnumActionResult.SUCCESS; }
			
			if (direction == EnumFacing.WEST && NS) {
				worldIn.setBlockState(new BlockPos(x, y, z + 1), State2, 3);
				worldIn.setBlockState(pos, State1, 3);
				worldIn.setBlockState(new BlockPos(x, y, z - 1), State3, 3);
				
				worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F);
				if (!mode) { hStack.shrink(1); }
				return EnumActionResult.SUCCESS; }

			else { 
				CMEvents.textNoPlace(worldIn, pos, playerIn);
				return EnumActionResult.FAIL; }
		}

		else { return EnumActionResult.FAIL; }
	}

	private Block int2Block(int k) {
		if (k == 0) { return School_Blocks.TEACHERDESK; }
		if (k == 1) { return School_Blocks.TEACHERDESK_s; }
		if (k == 2) { return School_Blocks.TEACHERDESK_b; }
		if (k == 3) { return School_Blocks.TEACHERDESK_j; }
		if (k == 4) { return School_Blocks.TEACHERDESK_a; }
		if (k == 5) { return School_Blocks.TEACHERDESK_d; }
		if (k == 6) { return School_Blocks.TEACHERDESK_saku; }
		if (k == 7) { return School_Blocks.TEACHERDESK_kae; }
		else { return School_Blocks.TEACHERDESK_ich; }
	}
}
