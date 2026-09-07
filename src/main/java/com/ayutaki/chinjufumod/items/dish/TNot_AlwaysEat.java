package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TNot_AlwaysEat extends ItemFood {

	public TNot_AlwaysEat(String name, int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		setRegistryName(new ResourceLocation(ChinjufuMod.MOD_ID, name));
		
		setAlwaysEdible();
	}
	
	protected abstract ItemStack remainStack();
	
	/* onItemUseFinish */
	protected abstract void addEffect(ItemStack stack, EntityPlayer playerIn);

	/* onItemUse */
	protected abstract Block takeBlock();
	
	protected abstract IBlockState takeState(EntityPlayer playerIn, EnumHand hand, EnumFacing direction);

	protected abstract void putEvent(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand);
	
	/* Finish RightClick Action */
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		EntityPlayer playerIn = (EntityPlayer)entityLiving;
		playerIn.getFoodStats().addStats(this, stack);

		if (entityLiving instanceof EntityPlayer) {
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
			/** add Potion Effect. **/
			if (!worldIn.isRemote) { this.addEffect(stack, playerIn); }
			
			playerIn.addStat(StatList.getObjectUseStats(this));
			if (playerIn instanceof EntityPlayerMP) {
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)playerIn, stack); }
		}

		/** add Item **/
		if (playerIn == null || !playerIn.capabilities.isCreativeMode) {
			ItemStack take = this.remainStack();
			if (stack.isEmpty()) { return take; }
			else if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }
			
			stack.shrink(1);
		}
		return stack;
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
			this.putEvent(worldIn, pos, playerIn, hand);
			
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
/* ItemFood を継承し, ブロック設置のメソッドを付ける ItemSeedFood を参照
 * 性質としては, ブロックを呼び出せる ItemFood
 * 空腹度による制約が掛かるのは ItemFood の難点
 * 紅茶の応用は誤食し易い為, 料理のコストを考慮して ItemFood 継承を採用する */