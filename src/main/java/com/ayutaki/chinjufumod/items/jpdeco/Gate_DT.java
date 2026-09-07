package com.ayutaki.chinjufumod.items.jpdeco;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.gate.Abstract_Gate;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Wadeco;
import com.ayutaki.chinjufumod.registry.doors.Gate_Blocks;
import com.ayutaki.chinjufumod.state.HalfState;
import com.ayutaki.chinjufumod.state.HingeState;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Gate_DT extends IR_Wadeco {
	
	public Gate_DT(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_gate_spruce_b";
		case 1:
			return "item." + "block_gate_spruce";
		case 2:
			return "item." + "block_gate_iron";
		case 3:
			return "item." + "block_gate_irongrill";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
		}
	}
	
	/* Call this when you use the item. ex) Place a block. */
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
		
		boolean woodSE = (k == 0 || k == 1); 
		SoundEvent SE = woodSE? SoundEvents.BLOCK_WOOD_PLACE : SoundEvents.BLOCK_METAL_PLACE;
		boolean mode = playerIn.capabilities.isCreativeMode;
		
		/** Put "this.block". **/
		if (playerIn.canPlayerEdit(pos, facing, hStack) && this.int2Block(k).canPlaceBlockAt(worldIn, pos)) {

			if (playerIn.isSneaking()) {

				IBlockState Upper1 = this.int2Block(k).getDefaultState().withProperty(Abstract_Gate.H_FACING, direction)
						.withProperty(Abstract_Gate.HALF, HalfState.UPPER).withProperty(Abstract_Gate.OPEN, Boolean.valueOf(false))
						.withProperty(Abstract_Gate.HINGE, HingeState.RIGHT).withProperty(Abstract_Gate.POWERED, Boolean.valueOf(false));
				IBlockState Lower1 = this.int2Block(k).getDefaultState().withProperty(Abstract_Gate.H_FACING, direction)
						.withProperty(Abstract_Gate.HALF, HalfState.LOWER).withProperty(Abstract_Gate.OPEN, Boolean.valueOf(false))
						.withProperty(Abstract_Gate.HINGE, HingeState.RIGHT).withProperty(Abstract_Gate.POWERED, Boolean.valueOf(false));
				IBlockState Upper2 = takeBlock2(k).getDefaultState().withProperty(Abstract_Gate.H_FACING, direction)
						.withProperty(Abstract_Gate.HALF, HalfState.UPPER).withProperty(Abstract_Gate.OPEN, Boolean.valueOf(false))
						.withProperty(Abstract_Gate.HINGE, HingeState.RIGHT).withProperty(Abstract_Gate.POWERED, Boolean.valueOf(false));
				IBlockState Lower2 = takeBlock2(k).getDefaultState().withProperty(Abstract_Gate.H_FACING, direction)
						.withProperty(Abstract_Gate.HALF, HalfState.LOWER).withProperty(Abstract_Gate.OPEN, Boolean.valueOf(false))
						.withProperty(Abstract_Gate.HINGE, HingeState.RIGHT).withProperty(Abstract_Gate.POWERED, Boolean.valueOf(false));

				if (direction == EnumFacing.NORTH && block.isReplaceable(worldIn, new BlockPos(x + 1, y, z))) {
					worldIn.setBlockState(pos.up(), Upper1, 3);
					worldIn.setBlockState(pos, Lower1, 3);
					worldIn.setBlockState(new BlockPos(x + 1, y + 1, z), Upper2, 3);
					worldIn.setBlockState(new BlockPos(x + 1, y, z), Lower2, 3);
					
					worldIn.playSound(playerIn, pos, SE, SoundCategory.BLOCKS, 1.0F, 0.8F);
					if (!mode) { hStack.shrink(1); }
					return EnumActionResult.SUCCESS; }
				
				if (direction == EnumFacing.SOUTH && block.isReplaceable(worldIn, new BlockPos(x - 1, y, z))) {
					worldIn.setBlockState(pos.up(), Upper1, 3);
					worldIn.setBlockState(pos, Lower1, 3);
					worldIn.setBlockState(new BlockPos(x - 1, y + 1, z), Upper2, 3);
					worldIn.setBlockState(new BlockPos(x - 1, y, z), Lower2, 3);
					
					worldIn.playSound(playerIn, pos, SE, SoundCategory.BLOCKS, 1.0F, 0.8F);
					if (!mode) { hStack.shrink(1); }
					return EnumActionResult.SUCCESS; }
				
				if (direction == EnumFacing.EAST && block.isReplaceable(worldIn, new BlockPos(x, y, z + 1))) {
					worldIn.setBlockState(pos.up(), Upper1, 3);
					worldIn.setBlockState(pos, Lower1, 3);
					worldIn.setBlockState(new BlockPos(x, y + 1, z + 1), Upper2, 3);
					worldIn.setBlockState(new BlockPos(x, y, z + 1), Lower2, 3);
					
					worldIn.playSound(playerIn, pos, SE, SoundCategory.BLOCKS, 1.0F, 0.8F);
					if (!mode) { hStack.shrink(1); }
					return EnumActionResult.SUCCESS; }
				
				if (direction == EnumFacing.WEST && block.isReplaceable(worldIn, new BlockPos(x, y, z - 1))) {
					worldIn.setBlockState(pos.up(), Upper1, 3);
					worldIn.setBlockState(pos, Lower1, 3);
					worldIn.setBlockState(new BlockPos(x, y + 1, z - 1), Upper2, 3);
					worldIn.setBlockState(new BlockPos(x, y, z - 1), Lower2, 3);
					
					worldIn.playSound(playerIn, pos, SE, SoundCategory.BLOCKS, 1.0F, 0.8F);
					if (!mode) { hStack.shrink(1); }
					return EnumActionResult.SUCCESS; }
				
				else { 
					CMEvents.textNoPlace(worldIn, pos, playerIn);
					return EnumActionResult.FAIL; }
			}

			/** デフォルトは left。右取手, 左蝶番で開くのが hinge=left **/
			else {
				
				IBlockState Upper1 = this.int2Block(k).getDefaultState().withProperty(Abstract_Gate.H_FACING, direction)
						.withProperty(Abstract_Gate.HALF, HalfState.UPPER).withProperty(Abstract_Gate.OPEN, Boolean.valueOf(false))
						.withProperty(Abstract_Gate.HINGE, HingeState.LEFT).withProperty(Abstract_Gate.POWERED, Boolean.valueOf(false));
				IBlockState Lower1 = this.int2Block(k).getDefaultState().withProperty(Abstract_Gate.H_FACING, direction)
						.withProperty(Abstract_Gate.HALF, HalfState.LOWER).withProperty(Abstract_Gate.OPEN, Boolean.valueOf(false))
						.withProperty(Abstract_Gate.HINGE, HingeState.LEFT).withProperty(Abstract_Gate.POWERED, Boolean.valueOf(false));
				IBlockState Upper2 = takeBlock2(k).getDefaultState().withProperty(Abstract_Gate.H_FACING, direction)
						.withProperty(Abstract_Gate.HALF, HalfState.UPPER).withProperty(Abstract_Gate.OPEN, Boolean.valueOf(false))
						.withProperty(Abstract_Gate.HINGE, HingeState.LEFT).withProperty(Abstract_Gate.POWERED, Boolean.valueOf(false));
				IBlockState Lower2 = takeBlock2(k).getDefaultState().withProperty(Abstract_Gate.H_FACING, direction)
						.withProperty(Abstract_Gate.HALF, HalfState.LOWER).withProperty(Abstract_Gate.OPEN, Boolean.valueOf(false))
						.withProperty(Abstract_Gate.HINGE, HingeState.LEFT).withProperty(Abstract_Gate.POWERED, Boolean.valueOf(false));
				
				if (direction == EnumFacing.NORTH && block.isReplaceable(worldIn, new BlockPos(x - 1, y, z))) {
					worldIn.setBlockState(pos.up(), Upper1, 3);
					worldIn.setBlockState(pos, Lower1, 3);
					worldIn.setBlockState(new BlockPos(x - 1, y + 1, z), Upper2, 3);
					worldIn.setBlockState(new BlockPos(x - 1, y, z), Lower2, 3);
					
					worldIn.playSound(playerIn, pos, SE, SoundCategory.BLOCKS, 1.0F, 0.8F);
					if (!mode) { hStack.shrink(1); }
					return EnumActionResult.SUCCESS; }
				
				if (direction == EnumFacing.SOUTH && block.isReplaceable(worldIn, new BlockPos(x + 1, y, z))) {
					worldIn.setBlockState(pos.up(), Upper1, 3);
					worldIn.setBlockState(pos, Lower1, 3);
					worldIn.setBlockState(new BlockPos(x + 1, y + 1, z), Upper2, 3);
					worldIn.setBlockState(new BlockPos(x + 1, y, z), Lower2, 3);
					
					worldIn.playSound(playerIn, pos, SE, SoundCategory.BLOCKS, 1.0F, 0.8F);
					if (!mode) { hStack.shrink(1); }
					return EnumActionResult.SUCCESS; }
				
				if (direction == EnumFacing.EAST && block.isReplaceable(worldIn, new BlockPos(x, y, z - 1))) {
					worldIn.setBlockState(pos.up(), Upper1, 3);
					worldIn.setBlockState(pos, Lower1, 3);
					worldIn.setBlockState(new BlockPos(x, y + 1, z - 1), Upper2, 3);
					worldIn.setBlockState(new BlockPos(x, y, z - 1), Lower2, 3);
					
					worldIn.playSound(playerIn, pos, SE, SoundCategory.BLOCKS, 1.0F, 0.8F);
					if (!mode) { hStack.shrink(1); }
					return EnumActionResult.SUCCESS; }
				
				if (direction == EnumFacing.WEST && block.isReplaceable(worldIn, new BlockPos(x, y, z + 1))) {
					worldIn.setBlockState(pos.up(), Upper1, 3);
					worldIn.setBlockState(pos, Lower1, 3);
					worldIn.setBlockState(new BlockPos(x, y + 1, z + 1), Upper2, 3);
					worldIn.setBlockState(new BlockPos(x, y, z + 1), Lower2, 3);
					
					worldIn.playSound(playerIn, pos, SE, SoundCategory.BLOCKS, 1.0F, 0.8F);
					if (!mode) { hStack.shrink(1); }
					return EnumActionResult.SUCCESS; }

				else { 
					CMEvents.textNoPlace(worldIn, pos, playerIn);
					return EnumActionResult.FAIL; }
			}
		}

		else { return EnumActionResult.FAIL; }
	}

	private Block int2Block(int k) {
		if (k == 0) { return Gate_Blocks.GATE_SPRUCE_B; }
		if (k == 1) { return Gate_Blocks.GATE_SPRUCE; }
		if (k == 2) { return Gate_Blocks.GATE_IRON; }
		else { return Gate_Blocks.GATE_IRONGRILL; }
	}
	
	private Block takeBlock2(int k) {
		if (k == 0) { return Gate_Blocks.GATE_SPRUCE_B2; }
		if (k == 1) { return Gate_Blocks.GATE_SPRUCE2; }
		if (k == 2) { return Gate_Blocks.GATE_IRON2; }
		else { return Gate_Blocks.GATE_IRONGRILL2; }
	}
	
	/* tips */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_gate.name"));
	}
}
