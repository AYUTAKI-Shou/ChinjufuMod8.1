package com.ayutaki.chinjufumod.items.chinjufu;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.base.ItemBlock_Regi;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;

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

public class AdmiralStamp_CN extends ItemBlock_Regi {

	public AdmiralStamp_CN(String name, Block putBlock) {
		super(name, putBlock);
		setUnlocalizedName(name);
		/* Add tab only to 'item_admiralstamp_b' */
		
		/** アイテムのスタック数 **/
		setMaxStackSize(1);
		/** アイテムの耐久値 **/
		setMaxDamage(15);
	}

	/* 耐久値を持たせるために ContainerItemStack を呼び出す
	 * Call ContainerItemStack to have damage value. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	/* クラフト後の処理 Processing after craft */
	@Override
	public ItemStack getContainerItem(ItemStack stack) {

		/** 残り耐久値とダメージが一致したときは, アイテムが消える
		 * Remaining durable value and damage are equal, the item disappears. **/
		if (stack.getMaxDamage() == stack.getItemDamage()) {
			return ItemStack.EMPTY;
		}

		/** それ以外は, ダメージを与えたアイテムを返す
		 * Otherwise, return items that damaged **/
		else {
			ItemStack newItemStack = stack.copy();
			newItemStack.setItemDamage(stack.getItemDamage() + 1);
			return newItemStack;
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

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Chinjufu_Blocks.ADMIRAL_STAMP1, pos, false, facing, (Entity)null)) {

			if (k<= 3) {
				worldIn.setBlockState(pos, Chinjufu_Blocks.ADMIRAL_STAMP1.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, direction)
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(k + 1)), 10); }

			if (k >= 4 && k <= 7) {
				worldIn.setBlockState(pos, Chinjufu_Blocks.ADMIRAL_STAMP2.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, direction)
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(k - 3)), 10); }

			if (k >= 8 && k <= 11) {
				worldIn.setBlockState(pos, Chinjufu_Blocks.ADMIRAL_STAMP3.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, direction)
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(k - 7)), 10); }

			if (k >= 12) {
				worldIn.setBlockState(pos, Chinjufu_Blocks.ADMIRAL_STAMP4.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, direction)
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(k - 11)), 10); }

			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}
	
	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.item_admiral_stamp.name"));
	}
}
