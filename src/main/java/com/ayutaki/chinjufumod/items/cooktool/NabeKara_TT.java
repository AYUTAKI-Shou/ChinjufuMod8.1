package com.ayutaki.chinjufumod.items.cooktool;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.dish.Nabe_kara;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IBR_Teatime;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NabeKara_TT extends IBR_Teatime {

	private final Block containedBlock;

	public NabeKara_TT(String name, Block containedBlockIn) {
		super(name, Dish_Blocks.NABE_kara);
		setUnlocalizedName(name);

		this.containedBlock = containedBlockIn;
	}

	/* from ItemBucket */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		boolean flag = this.containedBlock == Blocks.AIR;
		ItemStack hStack = playerIn.getHeldItem(hand);
		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, flag);
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, hStack, raytraceresult);
		if (ret != null) return ret;

		if (raytraceresult == null) {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, hStack); }

		else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
			return new ActionResult<ItemStack>(EnumActionResult.PASS, hStack); }

		else {
			BlockPos pos = raytraceresult.getBlockPos();

			if (!worldIn.isBlockModifiable(playerIn, pos)) {
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, hStack);
			}

			else if (flag) {

				if (!playerIn.canPlayerEdit(pos.offset(raytraceresult.sideHit), raytraceresult.sideHit, hStack)) {
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, hStack);
				}

				else {
					IBlockState state = worldIn.getBlockState(pos);
					Material material = state.getMaterial();

					if (material == Material.WATER && ((Integer)state.getValue(BlockLiquid.LEVEL)).intValue() == 0) {

						worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
						playerIn.addStat(StatList.getObjectUseStats(this));

						CMEvents.soundBucketFill(worldIn, pos, playerIn, 0.8F, 1.2F);
						CMEvents.take1Item(playerIn, hand, Items_Teatime.NABESHIO_nama, 0);
						CMEvents.consumeN_Hand(1, playerIn, hand);

						return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack); }

					else if (material == Material.LAVA && ((Integer)state.getValue(BlockLiquid.LEVEL)).intValue() == 0) {

						playerIn.playSound(SoundEvents.ENTITY_GENERIC_BURN, 1.0F, 1.0F);
						/** add Potion Effect. **/
						if (!worldIn.isRemote) { playerIn.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 1, 0)); }
						playerIn.addStat(StatList.getObjectUseStats(this));

						CMEvents.take1Item(playerIn, hand, Items.AIR, 0);
						CMEvents.consumeN_Hand(1, playerIn, hand);

						return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack); }

					else { return new ActionResult<ItemStack>(EnumActionResult.FAIL, hStack); }
				}
			}

			else { return new ActionResult<ItemStack>(EnumActionResult.FAIL, hStack); }
		}
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

		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Dish_Blocks.NABE_kara, pos, false, facing, (Entity)null)) {

			/** Put the Block. **/
			IBlockState putSTATE = Dish_Blocks.NABE_kara.getDefaultState()
					.withProperty(Nabe_kara.H_FACING, direction)
					.withProperty(Nabe_kara.STAGE_1_4, Integer.valueOf(1));
			worldIn.setBlockState(pos, putSTATE, 10);

			CMEvents.ItemBlock_Stone(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.block_food_karanabe.name"));
	}
}
