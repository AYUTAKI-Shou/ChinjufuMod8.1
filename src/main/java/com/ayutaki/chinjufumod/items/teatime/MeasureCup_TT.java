package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.dish.MeasureCup;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Teatime;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MeasureCup_TT extends IBR_Teatime {

	private final Block containedBlock;
	
	public MeasureCup_TT(String name, Block containedBlockIn) {
		super(name, Dish_Blocks.KEIRYO_CUP);
		setUnlocalizedName(name);

		this.containedBlock = containedBlockIn;
	}

	private void MeasureCup_toFull(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand) {
		CMEvents.take1Item(playerIn, hand, Items_Teatime.KEIRYO_CUP_full, 0);
		CMEvents.consume1_seBottle(worldIn, pos, playerIn, hand); }
	
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
							int level = state.getValue(BlockCauldron.LEVEL);

							if (level == 0) { return new ActionResult<ItemStack>(EnumActionResult.PASS, hStack); }
							
							else {
								((BlockCauldron)block1).setWaterLevel(worldIn, pos, state, level - 1);
								this.MeasureCup_toFull(worldIn, pos, playerIn, hand);
								return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack); }
						 }

						/** 水と溶岩 **/
						if (material == Material.WATER) {
							playerIn.addStat(StatList.getObjectUseStats(this));
							this.MeasureCup_toFull(worldIn, pos, playerIn, hand);
							return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack); }

						else { return new ActionResult<ItemStack>(EnumActionResult.PASS, hStack); }
					}
				}
			}
			return new ActionResult<ItemStack>(EnumActionResult.PASS, hStack);
		}	
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
				.mayPlace(Dish_Blocks.KEIRYO_CUP, pos, false, facing, (Entity)null) && playerIn.isSneaking()) {

			/** Put the Block. **/
			IBlockState putSTATE = Dish_Blocks.KEIRYO_CUP.getDefaultState().withProperty(MeasureCup.H_FACING, direction);
			worldIn.setBlockState(pos, putSTATE, 10);

			SoundType soundtype = putSTATE.getBlock().getSoundType(putSTATE, worldIn, pos, playerIn);
			worldIn.playSound((EntityPlayer)null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

			hStack.shrink(1);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_measurecup.name"));
		itemTip.add(I18n.format("tips.block_simpledish.name"));
	}
}
