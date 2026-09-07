package com.ayutaki.chinjufumod.items.sakuteki;

import java.util.List;
import java.util.stream.Stream;

import com.ayutaki.chinjufumod.blocks.cmblock.Base_WakeWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.ShipTypes_CM;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.ForgeMod;

public class Device_Sonar extends Abstract_Device {

	public Device_Sonar(Item.Properties props) {
		super(props);
	}

	protected boolean GISOU(Player playerIn) {
		return ShipTypes_CM.typeDestroyer(playerIn) || ShipTypes_CM.typeSubmarine(playerIn);
	}

	protected float pitchSE() { return 1.0F; }

	protected boolean waterCheck(LivingEntity entityLiving) {
		return entityLiving.isEyeInFluidType(ForgeMod.WATER_TYPE.get());
	}

	private void text_Tips2(Level worldIn, Player playerIn) {
		CMEvents.soundError(worldIn, playerIn);
		playerIn.displayClientMessage(Component.translatable("tips.item_device_sonar2"), true);
	}
	
	/* Click on the Block. */
	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player playerIn = context.getPlayer();
		Level worldIn = context.getLevel();
		InteractionHand hand = context.getHand();

		if (this.GISOU(playerIn)) {
			/** Sonar Click UP_DOWN_1 **/
			Direction facing = context.getClickedFace().getOpposite();
			
			int ms = 80;
			double x = playerIn.getX();
			double y = playerIn.getY() + 1.0D;
			double z = playerIn.getZ();
			
			int width = 6;
			int distance = 16;
			int SPC = 15;
			
			int W1 = width * 1; //12 = 6 * 2
			int W2 = width * 2;
			int W3 = width * 3;
			int W4 = width * 4;
			int W5 = width * 5;
			int W6 = width * 6;
			int W7 = width * 7;
			int W8 = width * 8; //96 = (6 * 8) * 2
			
			int D1 = distance * 1;
			int D2 = distance * 2;
			int D3 = distance * 3;
			int D4 = distance * 4;
			int D5 = distance * 5;
			int D6 = distance * 6;
			int D7 = distance * 7;
			int D8 = distance * 8;
			
			double SP1 = 1.5D;
			int SP2 = SPC * 1;
			int SP3 = SPC * 2;
			int SP4 = SPC * 3;
			int SP5 = SPC * 4;
			int SP6 = SPC * 5;
			int SP7 = SPC * 6;
			int SP8 = SPC * 7;
			
			AABB DOWN_1 = new AABB(x - W1, y - SP1, z - W1, x + W1, y - D1, z + W1);
			AABB DOWN_2 = new AABB(x - W2, y - SP2, z - W2, x + W2, y - D2, z + W2);
			AABB DOWN_3 = new AABB(x - W3, y - SP3, z - W3, x + W3, y - D3, z + W3);
			AABB DOWN_4 = new AABB(x - W4, y - SP4, z - W4, x + W4, y - D4, z + W4);
			AABB DOWN_5 = new AABB(x - W5, y - SP5, z - W5, x + W5, y - D5, z + W5);
			AABB DOWN_6 = new AABB(x - W6, y - SP6, z - W6, x + W6, y - D6, z + W6);
			AABB DOWN_7 = new AABB(x - W7, y - SP7, z - W7, x + W7, y - D7, z + W7);
			AABB DOWN_8 = new AABB(x - W8, y - SP8, z - W8, x + W8, y - D8, z + W8);

			List<LivingEntity> LIST_D1 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_1);
			List<LivingEntity> LIST_D2 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_2);
			List<LivingEntity> LIST_D3 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_3);
			List<LivingEntity> LIST_D4 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_4);
			List<LivingEntity> LIST_D5 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_5);
			List<LivingEntity> LIST_D6 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_6);
			List<LivingEntity> LIST_D7 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_7);
			List<LivingEntity> LIST_D8 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_8);

			Stream<LivingEntity> FILTER_D1 = LIST_D1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
			Stream<LivingEntity> FILTER_D2 = LIST_D2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
			Stream<LivingEntity> FILTER_D3 = LIST_D3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
			Stream<LivingEntity> FILTER_D4 = LIST_D4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
			Stream<LivingEntity> FILTER_D5 = LIST_D5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
			Stream<LivingEntity> FILTER_D6 = LIST_D6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
			Stream<LivingEntity> FILTER_D7 = LIST_D7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
			Stream<LivingEntity> FILTER_D8 = LIST_D8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));
			/** Sonar Click UP_DOWN_1 **/
			
			if (playerIn.isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
				if (playerIn.getCooldowns().isOnCooldown(this)) { return InteractionResult.PASS; }
				
				else {
					switch (facing) {
					default :
						super.use(worldIn, playerIn, hand);
						return InteractionResult.PASS; //must 'PASS'.
						
					case UP :
						AABB UP_1 = new AABB(x - W1, y + SP1, z - W1, x + W1, y + D1, z + W1);
						AABB UP_2 = new AABB(x - W2, y + SP2, z - W2, x + W2, y + D2, z + W2);
						AABB UP_3 = new AABB(x - W3, y + SP3, z - W3, x + W3, y + D3, z + W3);
						AABB UP_4 = new AABB(x - W4, y + SP4, z - W4, x + W4, y + D4, z + W4);
						AABB UP_5 = new AABB(x - W5, y + SP5, z - W5, x + W5, y + D5, z + W5);
						AABB UP_6 = new AABB(x - W6, y + SP6, z - W6, x + W6, y + D6, z + W6);
						AABB UP_7 = new AABB(x - W7, y + SP7, z - W7, x + W7, y + D7, z + W7);
						AABB UP_8 = new AABB(x - W8, y + SP8, z - W8, x + W8, y + D8, z + W8);

						List<LivingEntity> LIST_U1 = worldIn.getEntitiesOfClass(LivingEntity.class, UP_1);
						List<LivingEntity> LIST_U2 = worldIn.getEntitiesOfClass(LivingEntity.class, UP_2);
						List<LivingEntity> LIST_U3 = worldIn.getEntitiesOfClass(LivingEntity.class, UP_3);
						List<LivingEntity> LIST_U4 = worldIn.getEntitiesOfClass(LivingEntity.class, UP_4);
						List<LivingEntity> LIST_U5 = worldIn.getEntitiesOfClass(LivingEntity.class, UP_5);
						List<LivingEntity> LIST_U6 = worldIn.getEntitiesOfClass(LivingEntity.class, UP_6);
						List<LivingEntity> LIST_U7 = worldIn.getEntitiesOfClass(LivingEntity.class, UP_7);
						List<LivingEntity> LIST_U8 = worldIn.getEntitiesOfClass(LivingEntity.class, UP_8);

						Stream<LivingEntity> FILTER_U1 = LIST_U1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_U2 = LIST_U2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_U3 = LIST_U3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_U4 = LIST_U4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_U5 = LIST_U5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_U6 = LIST_U6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_U7 = LIST_U7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_U8 = LIST_U8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));
						
						/** add Potion Effect. **/
						if (!worldIn.isClientSide) {
							FILTER_U1.forEach(entity -> entity.addEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_U2.forEach(entity -> entity.addEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_U3.forEach(entity -> entity.addEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_U4.forEach(entity -> entity.addEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_U5.forEach(entity -> entity.addEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_U6.forEach(entity -> entity.addEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_U7.forEach(entity -> entity.addEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_U8.forEach(entity -> entity.addEffect(this.glowTIME(20))), ms * 7, milliS); }

						this.coolDown(worldIn, playerIn);
						return InteractionResult.SUCCESS;
						
					case DOWN :
						/** add Potion Effect. **/
						if (!worldIn.isClientSide) {
							FILTER_D1.forEach(entity -> entity.addEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_D2.forEach(entity -> entity.addEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_D3.forEach(entity -> entity.addEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_D4.forEach(entity -> entity.addEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_D5.forEach(entity -> entity.addEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_D6.forEach(entity -> entity.addEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_D7.forEach(entity -> entity.addEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_D8.forEach(entity -> entity.addEffect(this.glowTIME(20))), ms * 7, milliS); }

						this.coolDown(worldIn, playerIn);
						return InteractionResult.SUCCESS;
					} //switch
				} //!Cooldown
			} //waterIn
						
			else {
				BlockPos pos = context.getClickedPos();
				BlockState state = worldIn.getBlockState(pos);
				
				if (state.getBlock() instanceof Base_WakeWater && facing == Direction.DOWN) {
					if (playerIn.getCooldowns().isOnCooldown(this)) { return InteractionResult.PASS; }
					
					else {
						/** add Potion Effect. **/
						if (!worldIn.isClientSide) {
							FILTER_D1.forEach(entity -> entity.addEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_D2.forEach(entity -> entity.addEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_D3.forEach(entity -> entity.addEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_D4.forEach(entity -> entity.addEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_D5.forEach(entity -> entity.addEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_D6.forEach(entity -> entity.addEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_D7.forEach(entity -> entity.addEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_D8.forEach(entity -> entity.addEffect(this.glowTIME(20))), ms * 7, milliS); }

						this.coolDown(worldIn, playerIn);
						return InteractionResult.SUCCESS;
					} //!Cooldown
				} //WakeWater
				
				else { return InteractionResult.PASS; }
			} //waterOUT
		} //GISOU
		
		else { 
			this.GISOU_Not4(worldIn, playerIn);
			return InteractionResult.PASS; }
	}
	
	/** Click on Empty Space. **/
	@SuppressWarnings("deprecation")
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		
		if (this.GISOU(playerIn)) {
			int ms = 80;
			double x = playerIn.getX();
			double y = playerIn.getY() + 1.0D;
			double z = playerIn.getZ();

			if (playerIn.isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
				if (playerIn.getCooldowns().isOnCooldown(this)) { return InteractionResultHolder.fail(hStack); }
				
				else {
					/** Rador Sonar NEWS **/
					this.coolDown(worldIn, playerIn);

					int CON = 2;
					int width = 8;
					int height = 4;
					int distance = 16;
					int SPC = 14;
					
					int W1 = CON + width * 1; //20 = { 2 + (8 * 1) } * 2
					int W2 = CON + width * 2; //36
					int W3 = CON + width * 3; //52
					int W4 = CON + width * 4; //68
					int W5 = CON + width * 5; //84
					int W6 = CON + width * 6; //100
					int W7 = CON + width * 7; //116
					int W8 = CON + width * 8; //132 = { 2 + (8 * 8) } * 2
					
					int H1 = height * 1;
					int H2 = height * 2;
					int H3 = height * 3;
					int H4 = height * 4;
					int H5 = height * 5;
					int H6 = height * 6;
					int H7 = height * 7;
					int H8 = height * 8; //64 = (4 * 8) * 2
					
					int D1 = distance * 1;
					int D2 = distance * 2;
					int D3 = distance * 3;
					int D4 = distance * 4;
					int D5 = distance * 5;
					int D6 = distance * 6;
					int D7 = distance * 7;
					int D8 = distance * 8; //128 = 16 * 8
					
					double SP1 = 1.5D;
					int SP2 = 13;
					int SP3 = SP2 + SPC * 1; //27 = 13+ (14 * 1)
					int SP4 = SP2 + SPC * 2;
					int SP5 = SP2 + SPC * 3;
					int SP6 = SP2 + SPC * 4;
					int SP7 = SP2 + SPC * 5;
					int SP8 = SP2 + SPC * 6; //97 = 13+ (14 * 6)
					
					Direction facing = playerIn.getDirection();
					switch (facing) { //UP & DOWN are added to the block. Player only has a horizontal direction.
					case NORTH :
					default :
						AABB NORTH_1 = new AABB(x - W1, y - H1, z - SP1, x + W1, y + H1, z - D1);
						AABB NORTH_2 = new AABB(x - W2, y - H2, z - SP2, x + W2, y + H2, z - D2);
						AABB NORTH_3 = new AABB(x - W3, y - H3, z - SP3, x + W3, y + H3, z - D3);
						AABB NORTH_4 = new AABB(x - W4, y - H4, z - SP4, x + W4, y + H4, z - D4);
						AABB NORTH_5 = new AABB(x - W5, y - H5, z - SP5, x + W5, y + H5, z - D5);
						AABB NORTH_6 = new AABB(x - W6, y - H6, z - SP6, x + W6, y + H6, z - D6);
						AABB NORTH_7 = new AABB(x - W7, y - H7, z - SP7, x + W7, y + H7, z - D7);
						AABB NORTH_8 = new AABB(x - W8, y - H8, z - SP8, x + W8, y + H8, z - D8);
	
						List<LivingEntity> LIST_N1 = worldIn.getEntitiesOfClass(LivingEntity.class, NORTH_1);
						List<LivingEntity> LIST_N2 = worldIn.getEntitiesOfClass(LivingEntity.class, NORTH_2);
						List<LivingEntity> LIST_N3 = worldIn.getEntitiesOfClass(LivingEntity.class, NORTH_3);
						List<LivingEntity> LIST_N4 = worldIn.getEntitiesOfClass(LivingEntity.class, NORTH_4);
						List<LivingEntity> LIST_N5 = worldIn.getEntitiesOfClass(LivingEntity.class, NORTH_5);
						List<LivingEntity> LIST_N6 = worldIn.getEntitiesOfClass(LivingEntity.class, NORTH_6);
						List<LivingEntity> LIST_N7 = worldIn.getEntitiesOfClass(LivingEntity.class, NORTH_7);
						List<LivingEntity> LIST_N8 = worldIn.getEntitiesOfClass(LivingEntity.class, NORTH_8);

						Stream<LivingEntity> FILTER_N1 = LIST_N1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_N2 = LIST_N2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_N3 = LIST_N3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_N4 = LIST_N4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_N5 = LIST_N5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_N6 = LIST_N6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_N7 = LIST_N7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_N8 = LIST_N8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));
						
						/** add Potion Effect. **/
						if (!worldIn.isClientSide) {
							FILTER_N1.forEach(entity -> entity.addEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_N2.forEach(entity -> entity.addEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_N3.forEach(entity -> entity.addEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_N4.forEach(entity -> entity.addEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_N5.forEach(entity -> entity.addEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_N6.forEach(entity -> entity.addEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_N7.forEach(entity -> entity.addEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_N8.forEach(entity -> entity.addEffect(this.glowTIME(20))), ms * 7, milliS); }
						break;

					case SOUTH :
						AABB SOUTH_1 = new AABB(x - W1, y - H1, z + SP1, x + W1, y + H1, z + D1);
						AABB SOUTH_2 = new AABB(x - W2, y - H2, z + SP2, x + W2, y + H2, z + D2);
						AABB SOUTH_3 = new AABB(x - W3, y - H3, z + SP3, x + W3, y + H3, z + D3);
						AABB SOUTH_4 = new AABB(x - W4, y - H4, z + SP4, x + W4, y + H4, z + D4);
						AABB SOUTH_5 = new AABB(x - W5, y - H5, z + SP5, x + W5, y + H5, z + D5);
						AABB SOUTH_6 = new AABB(x - W6, y - H6, z + SP6, x + W6, y + H6, z + D6);
						AABB SOUTH_7 = new AABB(x - W7, y - H7, z + SP7, x + W7, y + H7, z + D7);
						AABB SOUTH_8 = new AABB(x - W8, y - H8, z + SP8, x + W8, y + H8, z + D8);

						List<LivingEntity> LIST_S1 = worldIn.getEntitiesOfClass(LivingEntity.class, SOUTH_1);
						List<LivingEntity> LIST_S2 = worldIn.getEntitiesOfClass(LivingEntity.class, SOUTH_2);
						List<LivingEntity> LIST_S3 = worldIn.getEntitiesOfClass(LivingEntity.class, SOUTH_3);
						List<LivingEntity> LIST_S4 = worldIn.getEntitiesOfClass(LivingEntity.class, SOUTH_4);
						List<LivingEntity> LIST_S5 = worldIn.getEntitiesOfClass(LivingEntity.class, SOUTH_5);
						List<LivingEntity> LIST_S6 = worldIn.getEntitiesOfClass(LivingEntity.class, SOUTH_6);
						List<LivingEntity> LIST_S7 = worldIn.getEntitiesOfClass(LivingEntity.class, SOUTH_7);
						List<LivingEntity> LIST_S8 = worldIn.getEntitiesOfClass(LivingEntity.class, SOUTH_8);

						Stream<LivingEntity> FILTER_S1 = LIST_S1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_S2 = LIST_S2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_S3 = LIST_S3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_S4 = LIST_S4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_S5 = LIST_S5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_S6 = LIST_S6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_S7 = LIST_S7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_S8 = LIST_S8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));
						
						/** add Potion Effect. **/
						if (!worldIn.isClientSide) {
							FILTER_S1.forEach(entity -> entity.addEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_S2.forEach(entity -> entity.addEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_S3.forEach(entity -> entity.addEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_S4.forEach(entity -> entity.addEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_S5.forEach(entity -> entity.addEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_S6.forEach(entity -> entity.addEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_S7.forEach(entity -> entity.addEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_S8.forEach(entity -> entity.addEffect(this.glowTIME(20))), ms * 7, milliS); }
						break;

					case EAST :
						AABB EAST_1 = new AABB(x + SP1, y - H1, z - W1, x + D1, y + H1, z + W1);
						AABB EAST_2 = new AABB(x + SP2, y - H2, z - W2, x + D2, y + H2, z + W2);
						AABB EAST_3 = new AABB(x + SP3, y - H3, z - W3, x + D3, y + H3, z + W3);
						AABB EAST_4 = new AABB(x + SP4, y - H4, z - W4, x + D4, y + H4, z + W4);
						AABB EAST_5 = new AABB(x + SP5, y - H5, z - W5, x + D5, y + H5, z + W5);
						AABB EAST_6 = new AABB(x + SP6, y - H6, z - W6, x + D6, y + H6, z + W6);
						AABB EAST_7 = new AABB(x + SP7, y - H7, z - W7, x + D7, y + H7, z + W7);
						AABB EAST_8 = new AABB(x + SP8, y - H8, z - W8, x + D8, y + H8, z + W8);

						List<LivingEntity> LIST_E1 = worldIn.getEntitiesOfClass(LivingEntity.class, EAST_1);
						List<LivingEntity> LIST_E2 = worldIn.getEntitiesOfClass(LivingEntity.class, EAST_2);
						List<LivingEntity> LIST_E3 = worldIn.getEntitiesOfClass(LivingEntity.class, EAST_3);
						List<LivingEntity> LIST_E4 = worldIn.getEntitiesOfClass(LivingEntity.class, EAST_4);
						List<LivingEntity> LIST_E5 = worldIn.getEntitiesOfClass(LivingEntity.class, EAST_5);
						List<LivingEntity> LIST_E6 = worldIn.getEntitiesOfClass(LivingEntity.class, EAST_6);
						List<LivingEntity> LIST_E7 = worldIn.getEntitiesOfClass(LivingEntity.class, EAST_7);
						List<LivingEntity> LIST_E8 = worldIn.getEntitiesOfClass(LivingEntity.class, EAST_8);

						Stream<LivingEntity> FILTER_E1 = LIST_E1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_E2 = LIST_E2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_E3 = LIST_E3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_E4 = LIST_E4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_E5 = LIST_E5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_E6 = LIST_E6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_E7 = LIST_E7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_E8 = LIST_E8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));
						
						/** add Potion Effect. **/
						if (!worldIn.isClientSide) {
							FILTER_E1.forEach(entity -> entity.addEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_E2.forEach(entity -> entity.addEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_E3.forEach(entity -> entity.addEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_E4.forEach(entity -> entity.addEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_E5.forEach(entity -> entity.addEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_E6.forEach(entity -> entity.addEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_E7.forEach(entity -> entity.addEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_E8.forEach(entity -> entity.addEffect(this.glowTIME(20))), ms * 7, milliS); }
						break;
						
					case WEST :
						AABB WEST_1 = new AABB(x - SP1, y - H1, z - W1, x - D1, y + H1, z + W1);
						AABB WEST_2 = new AABB(x - SP2, y - H2, z - W2, x - D2, y + H2, z + W2);
						AABB WEST_3 = new AABB(x - SP3, y - H3, z - W3, x - D3, y + H3, z + W3);
						AABB WEST_4 = new AABB(x - SP4, y - H4, z - W4, x - D4, y + H4, z + W4);
						AABB WEST_5 = new AABB(x - SP5, y - H5, z - W5, x - D5, y + H5, z + W5);
						AABB WEST_6 = new AABB(x - SP6, y - H6, z - W6, x - D6, y + H6, z + W6);
						AABB WEST_7 = new AABB(x - SP7, y - H7, z - W7, x - D7, y + H7, z + W7);
						AABB WEST_8 = new AABB(x - SP8, y - H8, z - W8, x - D8, y + H8, z + W8);

						List<LivingEntity> LIST_W1 = worldIn.getEntitiesOfClass(LivingEntity.class, WEST_1);
						List<LivingEntity> LIST_W2 = worldIn.getEntitiesOfClass(LivingEntity.class, WEST_2);
						List<LivingEntity> LIST_W3 = worldIn.getEntitiesOfClass(LivingEntity.class, WEST_3);
						List<LivingEntity> LIST_W4 = worldIn.getEntitiesOfClass(LivingEntity.class, WEST_4);
						List<LivingEntity> LIST_W5 = worldIn.getEntitiesOfClass(LivingEntity.class, WEST_5);
						List<LivingEntity> LIST_W6 = worldIn.getEntitiesOfClass(LivingEntity.class, WEST_6);
						List<LivingEntity> LIST_W7 = worldIn.getEntitiesOfClass(LivingEntity.class, WEST_7);
						List<LivingEntity> LIST_W8 = worldIn.getEntitiesOfClass(LivingEntity.class, WEST_8);

						Stream<LivingEntity> FILTER_W1 = LIST_W1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_W2 = LIST_W2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_W3 = LIST_W3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_W4 = LIST_W4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_W5 = LIST_W5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_W6 = LIST_W6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_W7 = LIST_W7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
						Stream<LivingEntity> FILTER_W8 = LIST_W8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));
						
						/** add Potion Effect. **/
						if (!worldIn.isClientSide) {
							FILTER_W1.forEach(entity -> entity.addEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_W2.forEach(entity -> entity.addEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_W3.forEach(entity -> entity.addEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_W4.forEach(entity -> entity.addEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_W5.forEach(entity -> entity.addEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_W6.forEach(entity -> entity.addEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_W7.forEach(entity -> entity.addEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_W8.forEach(entity -> entity.addEffect(this.glowTIME(20))), ms * 7, milliS); }
						break;
					} // facing
					/** Rador Sonar NEWS **/
					return InteractionResultHolder.success(hStack);
				} //!Cooldown
			} //waterIn
			
			else {
				BlockHitResult blockResult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.SOURCE_ONLY);
				if (blockResult.getType() == HitResult.Type.MISS) { 
					this.text_Tips2(worldIn, playerIn);
					return InteractionResultHolder.pass(hStack); }

				if (blockResult.getType() != HitResult.Type.BLOCK) { 
					this.text_Tips2(worldIn, playerIn);
					return InteractionResultHolder.pass(hStack); }
				
				else {
					BlockPos pos = blockResult.getBlockPos();
					BlockState state = worldIn.getBlockState(pos);
					FluidState fluid = worldIn.getFluidState(pos);
					
					if (state.getBlock() instanceof LiquidBlock) {
						
						boolean WATER_UP = (fluid.getType().is(FluidTags.WATER) && 
								((Integer)state.getValue(LiquidBlock.LEVEL)).intValue() == 0 && blockResult.getDirection() == Direction.UP);
						if (WATER_UP) {
							if (playerIn.getCooldowns().isOnCooldown(this)) { return InteractionResultHolder.fail(hStack); }
							
							else {
								/** Sonar DOWN_2 **/
								int width = 6;
								int distance = 16;
								int SPC = 15;
								
								int W1 = width * 1; //12 = 6 * 2
								int W2 = width * 2;
								int W3 = width * 3;
								int W4 = width * 4;
								int W5 = width * 5;
								int W6 = width * 6;
								int W7 = width * 7;
								int W8 = width * 8; //96 = (6 * 8) * 2
								
								int D1 = distance * 1;
								int D2 = distance * 2;
								int D3 = distance * 3;
								int D4 = distance * 4;
								int D5 = distance * 5;
								int D6 = distance * 6;
								int D7 = distance * 7;
								int D8 = distance * 8;
								
								double SP1 = 1.5D;
								int SP2 = SPC * 1;
								int SP3 = SPC * 2;
								int SP4 = SPC * 3;
								int SP5 = SPC * 4;
								int SP6 = SPC * 5;
								int SP7 = SPC * 6;
								int SP8 = SPC * 7;
								
								AABB DOWN_1 = new AABB(x - W1, y - SP1, z - W1, x + W1, y - D1, z + W1);
								AABB DOWN_2 = new AABB(x - W2, y - SP2, z - W2, x + W2, y - D2, z + W2);
								AABB DOWN_3 = new AABB(x - W3, y - SP3, z - W3, x + W3, y - D3, z + W3);
								AABB DOWN_4 = new AABB(x - W4, y - SP4, z - W4, x + W4, y - D4, z + W4);
								AABB DOWN_5 = new AABB(x - W5, y - SP5, z - W5, x + W5, y - D5, z + W5);
								AABB DOWN_6 = new AABB(x - W6, y - SP6, z - W6, x + W6, y - D6, z + W6);
								AABB DOWN_7 = new AABB(x - W7, y - SP7, z - W7, x + W7, y - D7, z + W7);
								AABB DOWN_8 = new AABB(x - W8, y - SP8, z - W8, x + W8, y - D8, z + W8);

								List<LivingEntity> LIST_D1 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_1);
								List<LivingEntity> LIST_D2 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_2);
								List<LivingEntity> LIST_D3 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_3);
								List<LivingEntity> LIST_D4 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_4);
								List<LivingEntity> LIST_D5 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_5);
								List<LivingEntity> LIST_D6 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_6);
								List<LivingEntity> LIST_D7 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_7);
								List<LivingEntity> LIST_D8 = worldIn.getEntitiesOfClass(LivingEntity.class, DOWN_8);

								Stream<LivingEntity> FILTER_D1 = LIST_D1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
								Stream<LivingEntity> FILTER_D2 = LIST_D2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
								Stream<LivingEntity> FILTER_D3 = LIST_D3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
								Stream<LivingEntity> FILTER_D4 = LIST_D4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
								Stream<LivingEntity> FILTER_D5 = LIST_D5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
								Stream<LivingEntity> FILTER_D6 = LIST_D6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
								Stream<LivingEntity> FILTER_D7 = LIST_D7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
								Stream<LivingEntity> FILTER_D8 = LIST_D8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));

								/** add Potion Effect. **/
								if (!worldIn.isClientSide) {
									FILTER_D1.forEach(entity -> entity.addEffect(this.glowTIME(90)));
									scheduler.schedule(() -> FILTER_D2.forEach(entity -> entity.addEffect(this.glowTIME(80))), ms * 1, milliS);
									scheduler.schedule(() -> FILTER_D3.forEach(entity -> entity.addEffect(this.glowTIME(70))), ms * 2, milliS);
									scheduler.schedule(() -> FILTER_D4.forEach(entity -> entity.addEffect(this.glowTIME(60))), ms * 3, milliS);
									scheduler.schedule(() -> FILTER_D5.forEach(entity -> entity.addEffect(this.glowTIME(50))), ms * 4, milliS);
									scheduler.schedule(() -> FILTER_D6.forEach(entity -> entity.addEffect(this.glowTIME(40))), ms * 5, milliS);
									scheduler.schedule(() -> FILTER_D7.forEach(entity -> entity.addEffect(this.glowTIME(30))), ms * 6, milliS);
									scheduler.schedule(() -> FILTER_D8.forEach(entity -> entity.addEffect(this.glowTIME(20))), ms * 7, milliS); }

								this.coolDown(worldIn, playerIn);
								/** Sonar DOWN_2 **/
								return InteractionResultHolder.success(hStack);
							} //!Cooldown
						} //Material.WATER
						
						else {
							this.text_Tips2(worldIn, playerIn);
							return InteractionResultHolder.fail(hStack); }
					} //FlowingFluid
					
					else {
						this.text_Tips2(worldIn, playerIn);
						return InteractionResultHolder.fail(hStack); }
				} //raytrace
			} //waterOUT
		} //GISOU

		else { 
			this.GISOU_Not4(worldIn, playerIn);
			return InteractionResultHolder.fail(hStack); }
	}
	
	/* ToolTip */
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> itemTip, TooltipFlag tipFlag) {
		itemTip.add(Component.translatable("tips.item_device_sonar").withStyle(ChatFormatting.GRAY));
		itemTip.add(Component.translatable("tips.item_device_sonar2").withStyle(ChatFormatting.GRAY));
		itemTip.add(Component.translatable("tips.item_device_sonar3").withStyle(ChatFormatting.BLUE));
	}
}
