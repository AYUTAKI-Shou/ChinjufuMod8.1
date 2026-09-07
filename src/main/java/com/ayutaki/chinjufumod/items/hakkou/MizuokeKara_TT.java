package com.ayutaki.chinjufumod.items.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.hakkou.Mizuoke;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Teatime;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MizuokeKara_TT extends IBR_Teatime {

	private final Block containedBlock;

	public MizuokeKara_TT(String name, Block containedBlockIn) {
		super(name, Hakkou_Blocks.MIZUOKE);
		setUnlocalizedName(name);
		
		this.containedBlock = containedBlockIn;
	}

	@Override
	public int getItemBurnTime(ItemStack stack) {
		return 100;
	}

	private void MIZUOKE_toFull(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.take1Item(playerIn, hand, Items_Teatime.MIZUOKE_full, 0);
		CMEvents.consumeN_Hand(1, playerIn, hand);
		CMEvents.soundBucketFill(worldIn, pos, playerIn, 0.8F, 1.0F); }
	
	/* from BucketItem */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		boolean contained = this.containedBlock == Blocks.AIR;

		ItemStack hStack = playerIn.getHeldItem(hand);
		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, contained);
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, hStack, raytraceresult);

		if (ret != null) return ret;

		if (raytraceresult == null) { return new ActionResult<ItemStack>(EnumActionResult.PASS, hStack); }

		if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) { return new ActionResult<ItemStack>(EnumActionResult.PASS, hStack); }

		else {

			BlockPos pos = raytraceresult.getBlockPos();
			IBlockState state = worldIn.getBlockState(pos);
			Block block1 = state.getBlock();
			Material material = state.getMaterial();

			if (!worldIn.isBlockModifiable(playerIn, pos)) { return new ActionResult<ItemStack>(EnumActionResult.FAIL, hStack); }

			else if (contained) {

				if (!playerIn.canPlayerEdit(pos.offset(raytraceresult.sideHit), raytraceresult.sideHit, hStack)) {
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, hStack); }

				else {

					if (!playerIn.isSneaking()) {
						/** 大釜からの給水 **/
						if (state.getBlock() == Blocks.CAULDRON) {
							int cauldron = state.getValue(BlockCauldron.LEVEL);

							if (cauldron == 3) {
								((BlockCauldron)block1).setWaterLevel(worldIn, pos, state, 0);
								this.MIZUOKE_toFull(worldIn, pos, playerIn, hand);
								return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack); }

							if (cauldron == 1 || cauldron == 2) { //-1.16.5
								((BlockCauldron)block1).setWaterLevel(worldIn, pos, state, 0);
								CMEvents.soundBucketFill(worldIn, pos, playerIn, 0.8F, 1.0F);
								return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack); }

							else { return new ActionResult<ItemStack>(EnumActionResult.PASS, hStack); } }

						/** 水と溶岩 **/
						if (material == Material.WATER && ((Integer)state.getValue(BlockLiquid.LEVEL)).intValue() == 0) {

							worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
							playerIn.addStat(StatList.getObjectUseStats(this));

							this.MIZUOKE_toFull(worldIn, pos, playerIn, hand);
							return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack); }

						if (material == Material.LAVA && ((Integer)state.getValue(BlockLiquid.LEVEL)).intValue() == 0) {

							playerIn.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.0F);
							/** add Potion Effect. **/
							if (!worldIn.isRemote) { playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 1, 0)); }
							playerIn.addStat(StatList.getObjectUseStats(this));

							worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);

							int l = pos.getX();
							int i = pos.getY();
							int j = pos.getZ();

							for (int k = 0; k < 8; ++k) {
								worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)l + Math.random(), (double)i + Math.random(), (double)j + Math.random(), 0.0D, 0.0D, 0.0D); }

							CMEvents.take1Item(playerIn, hand, Items.AIR, 0);
							CMEvents.consumeN_Hand(1, playerIn, hand);
							return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack); }

						else { return new ActionResult<ItemStack>(EnumActionResult.PASS, hStack); }
					}
				}
			}
			return new ActionResult<ItemStack>(EnumActionResult.PASS, hStack);
		}
	}

	/* 牛乳を汲む ItemShears */
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, net.minecraft.entity.player.EntityPlayer playerIn, EntityLivingBase entity, EnumHand hand) {
		if (entity.world.isRemote) { return false; }

		/* EntityCow */
		if (stack.getItem() == Items_Teatime.MIZUOKE) {

			if (entity instanceof EntityCow && !playerIn.capabilities.isCreativeMode && !entity.isChild()) {

				entity.playSound(SoundEvents.ENTITY_COW_MILK, 1.0F, 1.0F);
				CMEvents.take1Item(playerIn, hand, Items_Teatime.MIZUOKE_Milk, 0);
				/* 消費を最後に回す */
				stack.shrink(1);
			}
			return true;
		}
		return false;
	}

	/* Place block */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		ItemStack hStack = playerIn.getHeldItem(hand);

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
		int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		EnumFacing direction = EnumFacing.getHorizontal(i);

		/** 水桶の設置 **/
		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn
				.mayPlace(Hakkou_Blocks.MIZUOKE, pos, false, facing, (Entity)null) && playerIn.isSneaking()) {

			/** Put the Block. **/
			IBlockState putSTATE = Hakkou_Blocks.MIZUOKE.getDefaultState()
					.withProperty(Mizuoke.H_FACING, direction)
					.withProperty(Mizuoke.STAGE_1_4, Integer.valueOf(1));
			worldIn.setBlockState(pos, putSTATE, 10);
			
			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_mizuoke.name"));
		itemTip.add(I18n.format("tips.block_simpledish.name"));
	}
}
