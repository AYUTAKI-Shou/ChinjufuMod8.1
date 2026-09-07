package com.ayutaki.chinjufumod.items.dish;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.fuel.Teatime_noFuel;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/* BucketItem を参照。extends は BlockNamedItem とする */
public class DonabeKara_TT extends Teatime_noFuel {
	private final Fluid containedBlock;

	public DonabeKara_TT(Fluid containedFluidIn, Block block, Item.Properties props) {
		super(block, props);

		this.containedBlock = containedFluidIn;
	}

	/* 水を入れる BucketItem */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, this.containedBlock == Fluids.EMPTY ? RayTraceContext.FluidMode.SOURCE_ONLY : RayTraceContext.FluidMode.NONE);
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, hStack, raytraceresult);

		if (ret != null) return ret;

		if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
			return ActionResult.resultPass(hStack);
		}

		else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
			return ActionResult.resultPass(hStack);
		}

		else {
			BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceresult;
			BlockPos pos = blockraytraceresult.getPos();
			Direction direction = blockraytraceresult.getFace();
			BlockPos pos1 = pos.offset(direction);

			if (worldIn.isBlockModifiable(playerIn, pos) && playerIn.canPlayerEdit(pos1, direction, hStack)) {

				if (this.containedBlock == Fluids.EMPTY) {
					BlockState state1 = worldIn.getBlockState(pos);

					if (state1.getBlock() instanceof IBucketPickupHandler) {
						Fluid fluid = ((IBucketPickupHandler)state1.getBlock()).pickupFluid(worldIn, pos, state1);

						if (fluid != Fluids.EMPTY) {
							playerIn.addStat(Stats.ITEM_USED.get(this));

							if (fluid == Fluids.LAVA) {
									worldIn.playSound(playerIn, pos, SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundCategory.BLOCKS, 1.0F, 1.0F);
									worldIn.playSound(null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_GENERIC_BURN, SoundCategory.PLAYERS, 1.0F, 1.0F);
									
									/** add Potion Effect. **/
									if (!worldIn.isRemote) { playerIn.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 1, 0)); }

									int i = pos.getX();
									int j = pos.getY();
									int k = pos.getZ();
									for(int l = 0; l < 8; ++l) {
										worldIn.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D); }

									CMEvents.take1Item(playerIn, hand, Items.AIR);
									CMEvents.consumeN_Hand(1, playerIn, hand); }

							if (fluid == Fluids.WATER) {
								CMEvents.soundBucketFill(worldIn, pos, playerIn, 0.8F, 1.2F);
								CMEvents.take1Item(playerIn, hand, Items_Teatime.NABESHIO_nama);
								CMEvents.consumeN_Hand(1, playerIn, hand); }

							return ActionResult.resultSuccess(hStack);
						} //!= Fluids.EMPTY
					} //IBucket

					return ActionResult.resultFail(hStack);
				} //EMPTY
			} //mayInteract
		} //else
		return ActionResult.resultFail(hStack);
	}

	/* Branch the process. */
	public ActionResultType onItemUse(ItemUseContext context) {
		PlayerEntity playerIn = context.getPlayer();

		if (context.getFace() == Direction.UP && (playerIn.isSneaking()) ) {
			return this.tryPlace(new BlockItemUseContext(context)); }

		else {
			return this.onItemRightClick(context.getWorld(), context.getPlayer(), context.getHand()).getType(); }
	}

	/* ToolTip*/
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.block_simpledish").applyTextStyle(TextFormatting.GRAY));
	}
}
