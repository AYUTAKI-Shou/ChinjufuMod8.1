package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Teatime;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TTab_DishDrink extends IBR_Teatime {

	public TTab_DishDrink(String name, Block putBlock) {
		super(name, putBlock);
	}

	/* onItemUseFinish */
	protected abstract ItemStack remainStack(ItemStack stack);
	
	protected abstract void addEffect(ItemStack stack, EntityPlayer playerIn);
	
	/* onItemUse */
	protected abstract Block takeBlock();
	
	protected abstract IBlockState takeState(EntityPlayer playerIn, EnumHand hand, EnumFacing direction);

	
	/* 飲み終わった時の処理 */
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		EntityPlayer playerIn = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
		/** add Potion Effect. **/
		if (!worldIn.isRemote) { this.addEffect(stack, playerIn); }

		if (entityLiving instanceof EntityPlayerMP) {
			EntityPlayerMP playerInmp = (EntityPlayerMP)entityLiving;
			CriteriaTriggers.CONSUME_ITEM.trigger(playerInmp, stack);
			playerInmp.addStat(StatList.getObjectUseStats(this));
		}

		if (playerIn == null || !playerIn.capabilities.isCreativeMode) {

			if (stack.isEmpty()) { return this.remainStack(stack); }
			else if (!playerIn.inventory.addItemStackToInventory(this.remainStack(stack))) { playerIn.dropItem(this.remainStack(stack), false); }

			stack.shrink(1);
		}
		return stack;
	}

	/* 飲むのにかかる時間 */
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}

	/* 飲むアクションをさせる */
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	/* 右クリックで飲むアクション */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.setActiveHand(handIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
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

		if (!hStack.isEmpty() && facing == EnumFacing.UP && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn
				.mayPlace(this.takeBlock(), pos, false, facing, (Entity)null) && (playerIn.isSneaking() || playerIn.isRiding())) {

			/** Put the Block. **/
			worldIn.setBlockState(pos, this.takeState(playerIn, hand, direction), 10);
			CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand);

			return EnumActionResult.SUCCESS;
		}
		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_simpledish.name"));
	}
}
