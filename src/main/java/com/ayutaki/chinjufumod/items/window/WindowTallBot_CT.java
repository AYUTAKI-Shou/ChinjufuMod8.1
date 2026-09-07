package com.ayutaki.chinjufumod.items.window;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.window.WindowTall_Bottom;
import com.ayutaki.chinjufumod.blocks.window.WindowTall_Top;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Chinjufu;
import com.ayutaki.chinjufumod.registry.Window_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
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

public class WindowTallBot_CT extends IR_Chinjufu {

	public WindowTallBot_CT(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_windowtallbot";
		case 1:
			return "item." + "block_windowtallbot_spruce";
		case 2:
			return "item." + "block_windowtallbot_birch";
		case 3:
			return "item." + "block_windowtallbot_jungle";
		case 4:
			return "item." + "block_windowtallbot_acacia";
		case 5:
			return "item." + "block_windowtallbot_darkoak";
		case 6:
			return "item." + "block_windowtallbot_sakura";
		case 7:
			return "item." + "block_windowtallbot_kaede";
		case 8:
			return "item." + "block_windowtallbot_ichoh";
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

		/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
		int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		EnumFacing direction = EnumFacing.getHorizontal(i);
		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		
		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(takeBOT(k), pos, false, facing, (Entity)null)) {

			worldIn.setBlockState(pos, takeBOT(k).getDefaultState()
					.withProperty(WindowTall_Bottom.H_FACING, direction)
					.withProperty(WindowTall_Bottom.STAGE_1_3, Integer.valueOf(1)), 2);
			worldIn.setBlockState(pos.up(), takeTOP(k).getDefaultState()
					.withProperty(WindowTall_Top.H_FACING, direction)
					.withProperty(WindowTall_Top.STAGE_1_3, Integer.valueOf(1)), 2);
			
			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	private Block takeBOT(int k) {
		if (k == 0) { return Window_Blocks.WINDOWTALLBOT_oak; }
		if (k == 1) { return Window_Blocks.WINDOWTALLBOT_spruce; }
		if (k == 2) { return Window_Blocks.WINDOWTALLBOT_birch; }
		if (k == 3) { return Window_Blocks.WINDOWTALLBOT_jungle; }
		if (k == 4) { return Window_Blocks.WINDOWTALLBOT_acacia; }
		if (k == 5) { return Window_Blocks.WINDOWTALLBOT_darkoak; }
		if (k == 6) { return Window_Blocks.WINDOWTALLBOT_sakura; }
		if (k == 7) { return Window_Blocks.WINDOWTALLBOT_kaede; }
		else { return Window_Blocks.WINDOWTALLBOT_ichoh; }
	}
	
	private Block takeTOP(int k) {
		if (k == 0) { return Window_Blocks.WINDOWTALLTOP_oak; }
		if (k == 1) { return Window_Blocks.WINDOWTALLTOP_spruce; }
		if (k == 2) { return Window_Blocks.WINDOWTALLTOP_birch; }
		if (k == 3) { return Window_Blocks.WINDOWTALLTOP_jungle; }
		if (k == 4) { return Window_Blocks.WINDOWTALLTOP_acacia; }
		if (k == 5) { return Window_Blocks.WINDOWTALLTOP_darkoak; }
		if (k == 6) { return Window_Blocks.WINDOWTALLTOP_sakura; }
		if (k == 7) { return Window_Blocks.WINDOWTALLTOP_kaede; }
		else { return Window_Blocks.WINDOWTALLTOP_ichoh; }
	}
	
	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_windowb.name"));
	}
}
