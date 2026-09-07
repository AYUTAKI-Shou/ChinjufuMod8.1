package com.ayutaki.chinjufumod.items.window;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.window.Window;
import com.ayutaki.chinjufumod.items.addtab.IR_Chinjufu;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class CT_SubWindow extends IR_Chinjufu {

	public CT_SubWindow(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* onItemUse */
	protected abstract Block int2Block(int k);
	
	protected abstract void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand);
	
	
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
		
		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(this.int2Block(k), pos, false, facing, (Entity)null)) {
			int sneak = playerIn.isSneaking()? 3 : 1;
			
			IBlockState putSTATE = this.int2Block(k).getDefaultState().withProperty(Window.H_FACING, direction)
					.withProperty(Window.STAGE_1_4, Integer.valueOf(sneak));
			worldIn.setBlockState(pos, putSTATE, 2);

			this.putEvent(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}
		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_window.name"));
	}
}
