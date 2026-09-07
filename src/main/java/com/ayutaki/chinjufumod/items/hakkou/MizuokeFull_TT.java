package com.ayutaki.chinjufumod.items.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.crop.Enden;
import com.ayutaki.chinjufumod.blocks.hakkou.Mizuoke_full;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Teatime;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSand.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MizuokeFull_TT extends IBR_Teatime {

	public MizuokeFull_TT(String name) {
		super(name, Hakkou_Blocks.MIZUOKE_full);
		setUnlocalizedName(name);

		setContainerItem(Items_Teatime.MIZUOKE);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_Teatime.MIZUOKE);
	}

	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		ItemStack hStack = playerIn.getHeldItem(hand);

		if (!playerIn.isSneaking() && playerIn.canPlayerEdit(pos, facing, hStack)) {

			/** 砂を塩田に変える **/
			if (state.getBlock() == Blocks.SAND && state.getValue(BlockSand.VARIANT) != EnumType.RED_SAND && facing == EnumFacing.UP) {
				/** 生成するブロック **/
				IBlockState putSTATE = Crop_Blocks.ENDEN.getDefaultState().withProperty(Enden.STAGE_1_9, Integer.valueOf(1));
				worldIn.setBlockState(pos, putSTATE, 10);
				if (playerIn instanceof EntityPlayerMP) { CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)playerIn, pos, hStack); }

				CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
				return EnumActionResult.SUCCESS;
			}

			/** 大釜への注水 **/
			if (state.getBlock() == Blocks.CAULDRON) {
				int cauldron = state.getValue(BlockCauldron.LEVEL);

				if (cauldron != 3) {
					((BlockCauldron)block).setWaterLevel(worldIn, pos, state, 3);
					if (playerIn instanceof EntityPlayerMP) { CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)playerIn, pos, hStack); }

					CMEvents.MIZUOKE_toEmpty(worldIn, pos, playerIn, hand);
					return EnumActionResult.SUCCESS;
				}
				return EnumActionResult.PASS;
			}

			else {
				if (worldIn.provider.doesWaterVaporize()) {
					int l = pos.getX();
					int i = pos.getY();
					int j = pos.getZ();

					for (int k = 0; k < 8; ++k) {
						worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)l + Math.random(), (double)i + Math.random(), (double)j + Math.random(), 0.0D, 0.0D, 0.0D); }
					worldIn.playSound(playerIn, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

					CMEvents.mode1Through_Consume(playerIn, hand, Items_Teatime.MIZUOKE, 0);
					return EnumActionResult.SUCCESS;
				}

				if (!worldIn.provider.doesWaterVaporize()) {
					/** 生成するブロック 流れない Blocks.WATER とは別 **/
					IBlockState putSTATE = Blocks.FLOWING_WATER.getDefaultState();
					worldIn.setBlockState(pos.offset(facing), putSTATE, 11);
					if (playerIn instanceof EntityPlayerMP) { CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)playerIn, pos, hStack); }

					CMEvents.soundBucketEmpty(worldIn, pos, playerIn);
					CMEvents.mode1Through_Consume(playerIn, hand, Items_Teatime.MIZUOKE, 0);
					return EnumActionResult.SUCCESS;
				}
			}
		}

		/** 水桶の設置 **/
		if (playerIn.isSneaking()) {

			if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }
			/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
			int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			EnumFacing direction = EnumFacing.getHorizontal(i);

			if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Hakkou_Blocks.MIZUOKE_full, pos, false, facing, (Entity)null)) {
				/** Put the Block. **/
				IBlockState putSTATE = Hakkou_Blocks.MIZUOKE_full.getDefaultState()
						.withProperty(Mizuoke_full.H_FACING, direction)
						.withProperty(Mizuoke_full.STAGE_1_2, Integer.valueOf(1));
				worldIn.setBlockState(pos, putSTATE, 10); }

			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_mizuoke_full.name"));
		itemTip.add(I18n.format("tips.block_simpledish.name"));
	}
}
