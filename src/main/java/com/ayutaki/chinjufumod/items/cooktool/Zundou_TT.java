package com.ayutaki.chinjufumod.items.cooktool;

import com.ayutaki.chinjufumod.blocks.dish.Zundou;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Teatime;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class Zundou_TT extends IBR_Teatime {

	public Zundou_TT(String name) {
		super(name, Dish_Blocks.ZUNDOU);
		setUnlocalizedName(name);
	}

	/* 水を入れる ItemBucket 淡水を使う為, ヤカンと寸胴は適用外 */

	/* 牛乳を汲む ItemShears */
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, net.minecraft.entity.player.EntityPlayer playerIn, EntityLivingBase entity, net.minecraft.util.EnumHand hand) {
		if (entity.world.isRemote) { return false; }

		/* EntityCow */
		if (stack.getItem() == Items_Teatime.ZUNDOU) {

			if (entity instanceof EntityCow && !playerIn.capabilities.isCreativeMode && !entity.isChild()) {

				entity.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
				CMEvents.take1Item(playerIn, hand, Items_Teatime.ZUNDOU_MILK, 0);
				/* 消費を最後に回す */
				stack.shrink(1);
				return true;
			}
		}
		return false;
	}

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

			/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
			int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			EnumFacing direction = EnumFacing.getHorizontal(i);
			ItemStack hStack = playerIn.getHeldItem(hand);

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Dish_Blocks.ZUNDOU, pos, false, facing, (Entity)null)) {

			/** Put the Block. **/
			IBlockState putSTATE = Dish_Blocks.ZUNDOU.getDefaultState()
					.withProperty(Zundou.H_FACING, direction)
					.withProperty(Zundou.STAGE_1_3, Integer.valueOf(1));
			worldIn.setBlockState(pos, putSTATE, 10);

			CMEvents.ItemBlock_Metal(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}
}
