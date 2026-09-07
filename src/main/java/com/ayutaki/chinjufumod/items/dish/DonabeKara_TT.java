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
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		RayTraceResult raytraceresult = getPlayerPOVHitResult(worldIn, playerIn, this.containedBlock == Fluids.EMPTY ? RayTraceContext.FluidMode.SOURCE_ONLY : RayTraceContext.FluidMode.NONE);
		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, hStack, raytraceresult);

		if (ret != null) return ret;

		if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
			return ActionResult.pass(hStack);
		}

		else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK) {
			return ActionResult.pass(hStack);
		}

		else {
			BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceresult;
			BlockPos pos = blockraytraceresult.getBlockPos();
			Direction direction = blockraytraceresult.getDirection();
			BlockPos pos1 = pos.relative(direction);

			if (worldIn.mayInteract(playerIn, pos) && playerIn.mayUseItemAt(pos1, direction, hStack)) {
				if (this.containedBlock == Fluids.EMPTY) {
					BlockState state1 = worldIn.getBlockState(pos);
					
					if (state1.getBlock() instanceof IBucketPickupHandler) {
						Fluid fluid = ((IBucketPickupHandler)state1.getBlock()).takeLiquid(worldIn, pos, state1);
						
						if (fluid != Fluids.EMPTY) {
							playerIn.awardStat(Stats.ITEM_USED.get(this));

							if (fluid == Fluids.LAVA) {
								worldIn.playSound(playerIn, pos, SoundEvents.BUCKET_FILL_LAVA, SoundCategory.BLOCKS, 1.0F, 1.0F);
								worldIn.playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.8F, 1.0F);

								/** add Potion Effect. **/
								if (!worldIn.isClientSide) {playerIn.addEffect(new EffectInstance(Effects.HARM, 1, 0)); }

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

							return ActionResult.success(hStack);
						} //!= Fluids.EMPTY
					} //IBucket
					
					return ActionResult.fail(hStack);
				} //EMPTY
			} //mayInteract
		} //else
		return ActionResult.fail(hStack);
	}

	/* Branch the process. */
	@Override
	public ActionResultType useOn(ItemUseContext context) {
		PlayerEntity playerIn = context.getPlayer();

		if (context.getClickedFace() == Direction.UP && (playerIn.isCrouching())) {
			return this.place(new BlockItemUseContext(context)); }

		else {
			return this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult(); }
	}

	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> itemTip, ITooltipFlag tipFlag) {
		itemTip.add(new TranslationTextComponent("tips.block_simpledish").withStyle(TextFormatting.GRAY));
	}
}
