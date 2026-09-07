package com.ayutaki.chinjufumod.items.sakuteki;

import java.util.List;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.cmblock.Base_WakeWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.ShipTypes_CM;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Device_Sonar extends Abstract_Device {

	public Device_Sonar(String name) {
		super(name);
	}

	protected boolean GISOU(EntityPlayer playerIn) {
		return ShipTypes_CM.typeDestroyer(playerIn) || ShipTypes_CM.typeSubmarine(playerIn);
	}

	protected float pitchSE() { return 1.0F; }

	protected boolean waterCheck(EntityLiving entityLiving) {
		return entityLiving.isInsideOfMaterial(Material.WATER);
	}

	private void text_Tips2(World worldIn, EntityPlayer playerIn) {
		CMEvents.soundError(worldIn, playerIn);
		playerIn.sendStatusMessage(new TextComponentTranslation("tips.item_device_sonar2.name", new Object[0]), true);
	}
	
	/* Click on the Block. */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing hitFace, float hitX, float hitY, float hitZ) {

		if (this.GISOU(playerIn)) {
			/** Sonar Click UP_DOWN_1 **/
			EnumFacing facing = hitFace.getOpposite();
			
			int ms = 80;
			double x = playerIn.posX;
			double y = playerIn.posY + 1.0D;
			double z = playerIn.posZ;
			
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
			
			AxisAlignedBB DOWN_1 = new AxisAlignedBB(x - W1, y - SP1, z - W1, x + W1, y - D1, z + W1);
			AxisAlignedBB DOWN_2 = new AxisAlignedBB(x - W2, y - SP2, z - W2, x + W2, y - D2, z + W2);
			AxisAlignedBB DOWN_3 = new AxisAlignedBB(x - W3, y - SP3, z - W3, x + W3, y - D3, z + W3);
			AxisAlignedBB DOWN_4 = new AxisAlignedBB(x - W4, y - SP4, z - W4, x + W4, y - D4, z + W4);
			AxisAlignedBB DOWN_5 = new AxisAlignedBB(x - W5, y - SP5, z - W5, x + W5, y - D5, z + W5);
			AxisAlignedBB DOWN_6 = new AxisAlignedBB(x - W6, y - SP6, z - W6, x + W6, y - D6, z + W6);
			AxisAlignedBB DOWN_7 = new AxisAlignedBB(x - W7, y - SP7, z - W7, x + W7, y - D7, z + W7);
			AxisAlignedBB DOWN_8 = new AxisAlignedBB(x - W8, y - SP8, z - W8, x + W8, y - D8, z + W8);

			List<EntityLiving> LIST_D1 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_1);
			List<EntityLiving> LIST_D2 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_2);
			List<EntityLiving> LIST_D3 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_3);
			List<EntityLiving> LIST_D4 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_4);
			List<EntityLiving> LIST_D5 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_5);
			List<EntityLiving> LIST_D6 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_6);
			List<EntityLiving> LIST_D7 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_7);
			List<EntityLiving> LIST_D8 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_8);

			Stream<EntityLiving> FILTER_D1 = LIST_D1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
			Stream<EntityLiving> FILTER_D2 = LIST_D2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
			Stream<EntityLiving> FILTER_D3 = LIST_D3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
			Stream<EntityLiving> FILTER_D4 = LIST_D4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
			Stream<EntityLiving> FILTER_D5 = LIST_D5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
			Stream<EntityLiving> FILTER_D6 = LIST_D6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
			Stream<EntityLiving> FILTER_D7 = LIST_D7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
			Stream<EntityLiving> FILTER_D8 = LIST_D8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));
			/** Sonar Click UP_DOWN_1 **/
			
			if (playerIn.isInsideOfMaterial(Material.WATER)) {
				if (playerIn.getCooldownTracker().hasCooldown(this)) { return EnumActionResult.PASS; }
				
				else {
					switch (facing) {
					default :
						super.onItemRightClick(worldIn, playerIn, hand);
						return EnumActionResult.PASS; //must 'PASS'.
					
					case UP :
						AxisAlignedBB UP_1 = new AxisAlignedBB(x - W1, y + SP1, z - W1, x + W1, y + D1, z + W1);
						AxisAlignedBB UP_2 = new AxisAlignedBB(x - W2, y + SP2, z - W2, x + W2, y + D2, z + W2);
						AxisAlignedBB UP_3 = new AxisAlignedBB(x - W3, y + SP3, z - W3, x + W3, y + D3, z + W3);
						AxisAlignedBB UP_4 = new AxisAlignedBB(x - W4, y + SP4, z - W4, x + W4, y + D4, z + W4);
						AxisAlignedBB UP_5 = new AxisAlignedBB(x - W5, y + SP5, z - W5, x + W5, y + D5, z + W5);
						AxisAlignedBB UP_6 = new AxisAlignedBB(x - W6, y + SP6, z - W6, x + W6, y + D6, z + W6);
						AxisAlignedBB UP_7 = new AxisAlignedBB(x - W7, y + SP7, z - W7, x + W7, y + D7, z + W7);
						AxisAlignedBB UP_8 = new AxisAlignedBB(x - W8, y + SP8, z - W8, x + W8, y + D8, z + W8);

						List<EntityLiving> LIST_U1 = worldIn.getEntitiesWithinAABB(EntityLiving.class, UP_1);
						List<EntityLiving> LIST_U2 = worldIn.getEntitiesWithinAABB(EntityLiving.class, UP_2);
						List<EntityLiving> LIST_U3 = worldIn.getEntitiesWithinAABB(EntityLiving.class, UP_3);
						List<EntityLiving> LIST_U4 = worldIn.getEntitiesWithinAABB(EntityLiving.class, UP_4);
						List<EntityLiving> LIST_U5 = worldIn.getEntitiesWithinAABB(EntityLiving.class, UP_5);
						List<EntityLiving> LIST_U6 = worldIn.getEntitiesWithinAABB(EntityLiving.class, UP_6);
						List<EntityLiving> LIST_U7 = worldIn.getEntitiesWithinAABB(EntityLiving.class, UP_7);
						List<EntityLiving> LIST_U8 = worldIn.getEntitiesWithinAABB(EntityLiving.class, UP_8);

						Stream<EntityLiving> FILTER_U1 = LIST_U1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_U2 = LIST_U2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_U3 = LIST_U3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_U4 = LIST_U4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_U5 = LIST_U5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_U6 = LIST_U6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_U7 = LIST_U7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_U8 = LIST_U8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));
						
						/** add Potion Effect. **/
						if (!worldIn.isRemote) {
							FILTER_U1.forEach(entity -> entity.addPotionEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_U2.forEach(entity -> entity.addPotionEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_U3.forEach(entity -> entity.addPotionEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_U4.forEach(entity -> entity.addPotionEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_U5.forEach(entity -> entity.addPotionEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_U6.forEach(entity -> entity.addPotionEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_U7.forEach(entity -> entity.addPotionEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_U8.forEach(entity -> entity.addPotionEffect(this.glowTIME(20))), ms * 7, milliS); }

						this.coolDown(worldIn, playerIn);
						return EnumActionResult.SUCCESS; 
						
					case DOWN :
						/** add Potion Effect. **/
						if (!worldIn.isRemote) {
							FILTER_D1.forEach(entity -> entity.addPotionEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_D2.forEach(entity -> entity.addPotionEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_D3.forEach(entity -> entity.addPotionEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_D4.forEach(entity -> entity.addPotionEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_D5.forEach(entity -> entity.addPotionEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_D6.forEach(entity -> entity.addPotionEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_D7.forEach(entity -> entity.addPotionEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_D8.forEach(entity -> entity.addPotionEffect(this.glowTIME(20))), ms * 7, milliS); }

						this.coolDown(worldIn, playerIn);
						return EnumActionResult.SUCCESS; 
					} //switch
				} //!Cooldown
			} //waterIn
			
			else { 
				IBlockState state = worldIn.getBlockState(pos);
				
				if (state.getBlock() instanceof Base_WakeWater && facing == EnumFacing.DOWN) {
					if (playerIn.getCooldownTracker().hasCooldown(this)) { return EnumActionResult.PASS; }
					
					else {
						/** add Potion Effect. **/
						if (!worldIn.isRemote) {
							FILTER_D1.forEach(entity -> entity.addPotionEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_D2.forEach(entity -> entity.addPotionEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_D3.forEach(entity -> entity.addPotionEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_D4.forEach(entity -> entity.addPotionEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_D5.forEach(entity -> entity.addPotionEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_D6.forEach(entity -> entity.addPotionEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_D7.forEach(entity -> entity.addPotionEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_D8.forEach(entity -> entity.addPotionEffect(this.glowTIME(20))), ms * 7, milliS); }

						this.coolDown(worldIn, playerIn);
						return EnumActionResult.SUCCESS; 
					} //!Cooldown
				} //WakeWater
				
				else { return EnumActionResult.PASS; }
			} //waterOUT
		} //GISOU
		
		else {
			this.GISOU_Not4(worldIn, playerIn);
			return EnumActionResult.PASS; }
	}
	
	/** Click on Empty Space. **/
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		if (this.GISOU(playerIn)) {
			int ms = 80;
			double x = playerIn.posX;
			double y = playerIn.posY + 1.0D;
			double z = playerIn.posZ;
			
			if (playerIn.isInsideOfMaterial(Material.WATER)) {
				if (playerIn.getCooldownTracker().hasCooldown(this)) { return new ActionResult<ItemStack>(EnumActionResult.FAIL, hStack); }
				
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
					
					EnumFacing facing = playerIn.getHorizontalFacing();
					switch (facing) { //UP & DOWN are added to the block. Player only has a horizontal direction.
					case NORTH :
					default :
						AxisAlignedBB NORTH_1 = new AxisAlignedBB(x - W1, y - H1, z - SP1, x + W1, y + H1, z - D1);
						AxisAlignedBB NORTH_2 = new AxisAlignedBB(x - W2, y - H2, z - SP2, x + W2, y + H2, z - D2);
						AxisAlignedBB NORTH_3 = new AxisAlignedBB(x - W3, y - H3, z - SP3, x + W3, y + H3, z - D3);
						AxisAlignedBB NORTH_4 = new AxisAlignedBB(x - W4, y - H4, z - SP4, x + W4, y + H4, z - D4);
						AxisAlignedBB NORTH_5 = new AxisAlignedBB(x - W5, y - H5, z - SP5, x + W5, y + H5, z - D5);
						AxisAlignedBB NORTH_6 = new AxisAlignedBB(x - W6, y - H6, z - SP6, x + W6, y + H6, z - D6);
						AxisAlignedBB NORTH_7 = new AxisAlignedBB(x - W7, y - H7, z - SP7, x + W7, y + H7, z - D7);
						AxisAlignedBB NORTH_8 = new AxisAlignedBB(x - W8, y - H8, z - SP8, x + W8, y + H8, z - D8);
	
						List<EntityLiving> LIST_N1 = worldIn.getEntitiesWithinAABB(EntityLiving.class, NORTH_1);
						List<EntityLiving> LIST_N2 = worldIn.getEntitiesWithinAABB(EntityLiving.class, NORTH_2);
						List<EntityLiving> LIST_N3 = worldIn.getEntitiesWithinAABB(EntityLiving.class, NORTH_3);
						List<EntityLiving> LIST_N4 = worldIn.getEntitiesWithinAABB(EntityLiving.class, NORTH_4);
						List<EntityLiving> LIST_N5 = worldIn.getEntitiesWithinAABB(EntityLiving.class, NORTH_5);
						List<EntityLiving> LIST_N6 = worldIn.getEntitiesWithinAABB(EntityLiving.class, NORTH_6);
						List<EntityLiving> LIST_N7 = worldIn.getEntitiesWithinAABB(EntityLiving.class, NORTH_7);
						List<EntityLiving> LIST_N8 = worldIn.getEntitiesWithinAABB(EntityLiving.class, NORTH_8);

						Stream<EntityLiving> FILTER_N1 = LIST_N1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_N2 = LIST_N2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_N3 = LIST_N3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_N4 = LIST_N4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_N5 = LIST_N5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_N6 = LIST_N6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_N7 = LIST_N7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_N8 = LIST_N8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));
						
						/** add Potion Effect. **/
						if (!worldIn.isRemote) {
							FILTER_N1.forEach(entity -> entity.addPotionEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_N2.forEach(entity -> entity.addPotionEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_N3.forEach(entity -> entity.addPotionEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_N4.forEach(entity -> entity.addPotionEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_N5.forEach(entity -> entity.addPotionEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_N6.forEach(entity -> entity.addPotionEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_N7.forEach(entity -> entity.addPotionEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_N8.forEach(entity -> entity.addPotionEffect(this.glowTIME(20))), ms * 7, milliS); }
						break;

					case SOUTH :
						AxisAlignedBB SOUTH_1 = new AxisAlignedBB(x - W1, y - H1, z + SP1, x + W1, y + H1, z + D1);
						AxisAlignedBB SOUTH_2 = new AxisAlignedBB(x - W2, y - H2, z + SP2, x + W2, y + H2, z + D2);
						AxisAlignedBB SOUTH_3 = new AxisAlignedBB(x - W3, y - H3, z + SP3, x + W3, y + H3, z + D3);
						AxisAlignedBB SOUTH_4 = new AxisAlignedBB(x - W4, y - H4, z + SP4, x + W4, y + H4, z + D4);
						AxisAlignedBB SOUTH_5 = new AxisAlignedBB(x - W5, y - H5, z + SP5, x + W5, y + H5, z + D5);
						AxisAlignedBB SOUTH_6 = new AxisAlignedBB(x - W6, y - H6, z + SP6, x + W6, y + H6, z + D6);
						AxisAlignedBB SOUTH_7 = new AxisAlignedBB(x - W7, y - H7, z + SP7, x + W7, y + H7, z + D7);
						AxisAlignedBB SOUTH_8 = new AxisAlignedBB(x - W8, y - H8, z + SP8, x + W8, y + H8, z + D8);

						List<EntityLiving> LIST_S1 = worldIn.getEntitiesWithinAABB(EntityLiving.class, SOUTH_1);
						List<EntityLiving> LIST_S2 = worldIn.getEntitiesWithinAABB(EntityLiving.class, SOUTH_2);
						List<EntityLiving> LIST_S3 = worldIn.getEntitiesWithinAABB(EntityLiving.class, SOUTH_3);
						List<EntityLiving> LIST_S4 = worldIn.getEntitiesWithinAABB(EntityLiving.class, SOUTH_4);
						List<EntityLiving> LIST_S5 = worldIn.getEntitiesWithinAABB(EntityLiving.class, SOUTH_5);
						List<EntityLiving> LIST_S6 = worldIn.getEntitiesWithinAABB(EntityLiving.class, SOUTH_6);
						List<EntityLiving> LIST_S7 = worldIn.getEntitiesWithinAABB(EntityLiving.class, SOUTH_7);
						List<EntityLiving> LIST_S8 = worldIn.getEntitiesWithinAABB(EntityLiving.class, SOUTH_8);

						Stream<EntityLiving> FILTER_S1 = LIST_S1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_S2 = LIST_S2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_S3 = LIST_S3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_S4 = LIST_S4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_S5 = LIST_S5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_S6 = LIST_S6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_S7 = LIST_S7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_S8 = LIST_S8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));
						
						/** add Potion Effect. **/
						if (!worldIn.isRemote) {
							FILTER_S1.forEach(entity -> entity.addPotionEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_S2.forEach(entity -> entity.addPotionEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_S3.forEach(entity -> entity.addPotionEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_S4.forEach(entity -> entity.addPotionEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_S5.forEach(entity -> entity.addPotionEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_S6.forEach(entity -> entity.addPotionEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_S7.forEach(entity -> entity.addPotionEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_S8.forEach(entity -> entity.addPotionEffect(this.glowTIME(20))), ms * 7, milliS); }
						break;

					case EAST :
						AxisAlignedBB EAST_1 = new AxisAlignedBB(x + SP1, y - H1, z - W1, x + D1, y + H1, z + W1);
						AxisAlignedBB EAST_2 = new AxisAlignedBB(x + SP2, y - H2, z - W2, x + D2, y + H2, z + W2);
						AxisAlignedBB EAST_3 = new AxisAlignedBB(x + SP3, y - H3, z - W3, x + D3, y + H3, z + W3);
						AxisAlignedBB EAST_4 = new AxisAlignedBB(x + SP4, y - H4, z - W4, x + D4, y + H4, z + W4);
						AxisAlignedBB EAST_5 = new AxisAlignedBB(x + SP5, y - H5, z - W5, x + D5, y + H5, z + W5);
						AxisAlignedBB EAST_6 = new AxisAlignedBB(x + SP6, y - H6, z - W6, x + D6, y + H6, z + W6);
						AxisAlignedBB EAST_7 = new AxisAlignedBB(x + SP7, y - H7, z - W7, x + D7, y + H7, z + W7);
						AxisAlignedBB EAST_8 = new AxisAlignedBB(x + SP8, y - H8, z - W8, x + D8, y + H8, z + W8);

						List<EntityLiving> LIST_E1 = worldIn.getEntitiesWithinAABB(EntityLiving.class, EAST_1);
						List<EntityLiving> LIST_E2 = worldIn.getEntitiesWithinAABB(EntityLiving.class, EAST_2);
						List<EntityLiving> LIST_E3 = worldIn.getEntitiesWithinAABB(EntityLiving.class, EAST_3);
						List<EntityLiving> LIST_E4 = worldIn.getEntitiesWithinAABB(EntityLiving.class, EAST_4);
						List<EntityLiving> LIST_E5 = worldIn.getEntitiesWithinAABB(EntityLiving.class, EAST_5);
						List<EntityLiving> LIST_E6 = worldIn.getEntitiesWithinAABB(EntityLiving.class, EAST_6);
						List<EntityLiving> LIST_E7 = worldIn.getEntitiesWithinAABB(EntityLiving.class, EAST_7);
						List<EntityLiving> LIST_E8 = worldIn.getEntitiesWithinAABB(EntityLiving.class, EAST_8);

						Stream<EntityLiving> FILTER_E1 = LIST_E1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_E2 = LIST_E2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_E3 = LIST_E3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_E4 = LIST_E4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_E5 = LIST_E5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_E6 = LIST_E6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_E7 = LIST_E7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_E8 = LIST_E8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));
						
						/** add Potion Effect. **/
						if (!worldIn.isRemote) {
							FILTER_E1.forEach(entity -> entity.addPotionEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_E2.forEach(entity -> entity.addPotionEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_E3.forEach(entity -> entity.addPotionEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_E4.forEach(entity -> entity.addPotionEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_E5.forEach(entity -> entity.addPotionEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_E6.forEach(entity -> entity.addPotionEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_E7.forEach(entity -> entity.addPotionEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_E8.forEach(entity -> entity.addPotionEffect(this.glowTIME(20))), ms * 7, milliS); }
						break;
						
					case WEST :
						AxisAlignedBB WEST_1 = new AxisAlignedBB(x - SP1, y - H1, z - W1, x - D1, y + H1, z + W1);
						AxisAlignedBB WEST_2 = new AxisAlignedBB(x - SP2, y - H2, z - W2, x - D2, y + H2, z + W2);
						AxisAlignedBB WEST_3 = new AxisAlignedBB(x - SP3, y - H3, z - W3, x - D3, y + H3, z + W3);
						AxisAlignedBB WEST_4 = new AxisAlignedBB(x - SP4, y - H4, z - W4, x - D4, y + H4, z + W4);
						AxisAlignedBB WEST_5 = new AxisAlignedBB(x - SP5, y - H5, z - W5, x - D5, y + H5, z + W5);
						AxisAlignedBB WEST_6 = new AxisAlignedBB(x - SP6, y - H6, z - W6, x - D6, y + H6, z + W6);
						AxisAlignedBB WEST_7 = new AxisAlignedBB(x - SP7, y - H7, z - W7, x - D7, y + H7, z + W7);
						AxisAlignedBB WEST_8 = new AxisAlignedBB(x - SP8, y - H8, z - W8, x - D8, y + H8, z + W8);

						List<EntityLiving> LIST_W1 = worldIn.getEntitiesWithinAABB(EntityLiving.class, WEST_1);
						List<EntityLiving> LIST_W2 = worldIn.getEntitiesWithinAABB(EntityLiving.class, WEST_2);
						List<EntityLiving> LIST_W3 = worldIn.getEntitiesWithinAABB(EntityLiving.class, WEST_3);
						List<EntityLiving> LIST_W4 = worldIn.getEntitiesWithinAABB(EntityLiving.class, WEST_4);
						List<EntityLiving> LIST_W5 = worldIn.getEntitiesWithinAABB(EntityLiving.class, WEST_5);
						List<EntityLiving> LIST_W6 = worldIn.getEntitiesWithinAABB(EntityLiving.class, WEST_6);
						List<EntityLiving> LIST_W7 = worldIn.getEntitiesWithinAABB(EntityLiving.class, WEST_7);
						List<EntityLiving> LIST_W8 = worldIn.getEntitiesWithinAABB(EntityLiving.class, WEST_8);

						Stream<EntityLiving> FILTER_W1 = LIST_W1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_W2 = LIST_W2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_W3 = LIST_W3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_W4 = LIST_W4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_W5 = LIST_W5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_W6 = LIST_W6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_W7 = LIST_W7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
						Stream<EntityLiving> FILTER_W8 = LIST_W8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));
						
						/** add Potion Effect. **/
						if (!worldIn.isRemote) {
							FILTER_W1.forEach(entity -> entity.addPotionEffect(this.glowTIME(90)));
							scheduler.schedule(() -> FILTER_W2.forEach(entity -> entity.addPotionEffect(this.glowTIME(80))), ms * 1, milliS);
							scheduler.schedule(() -> FILTER_W3.forEach(entity -> entity.addPotionEffect(this.glowTIME(70))), ms * 2, milliS);
							scheduler.schedule(() -> FILTER_W4.forEach(entity -> entity.addPotionEffect(this.glowTIME(60))), ms * 3, milliS);
							scheduler.schedule(() -> FILTER_W5.forEach(entity -> entity.addPotionEffect(this.glowTIME(50))), ms * 4, milliS);
							scheduler.schedule(() -> FILTER_W6.forEach(entity -> entity.addPotionEffect(this.glowTIME(40))), ms * 5, milliS);
							scheduler.schedule(() -> FILTER_W7.forEach(entity -> entity.addPotionEffect(this.glowTIME(30))), ms * 6, milliS);
							scheduler.schedule(() -> FILTER_W8.forEach(entity -> entity.addPotionEffect(this.glowTIME(20))), ms * 7, milliS); }
						break;
					} // facing
					/** Rador Sonar NEWS **/
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack);
				} //!Cooldown
			} //waterIn
			
			else {
				RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
				if (raytraceresult == null) { 
					this.text_Tips2(worldIn, playerIn);
					return new ActionResult<ItemStack>(EnumActionResult.PASS, hStack); }
				
				if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) { 
					this.text_Tips2(worldIn, playerIn);
					return new ActionResult<ItemStack>(EnumActionResult.PASS, hStack); }
				
				else {
					BlockPos pos = raytraceresult.getBlockPos();
					IBlockState state = worldIn.getBlockState(pos);
					
					if (state.getBlock() instanceof BlockLiquid) {
						
						boolean WATER_UP = (state.getMaterial() == Material.WATER && 
								((Integer)state.getValue(BlockLiquid.LEVEL)).intValue() == 0 && raytraceresult.sideHit == EnumFacing.UP);
						if (WATER_UP) {
							if (playerIn.getCooldownTracker().hasCooldown(this)) { return new ActionResult<ItemStack>(EnumActionResult.FAIL, hStack); }
							
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
								
								AxisAlignedBB DOWN_1 = new AxisAlignedBB(x - W1, y - SP1, z - W1, x + W1, y - D1, z + W1);
								AxisAlignedBB DOWN_2 = new AxisAlignedBB(x - W2, y - SP2, z - W2, x + W2, y - D2, z + W2);
								AxisAlignedBB DOWN_3 = new AxisAlignedBB(x - W3, y - SP3, z - W3, x + W3, y - D3, z + W3);
								AxisAlignedBB DOWN_4 = new AxisAlignedBB(x - W4, y - SP4, z - W4, x + W4, y - D4, z + W4);
								AxisAlignedBB DOWN_5 = new AxisAlignedBB(x - W5, y - SP5, z - W5, x + W5, y - D5, z + W5);
								AxisAlignedBB DOWN_6 = new AxisAlignedBB(x - W6, y - SP6, z - W6, x + W6, y - D6, z + W6);
								AxisAlignedBB DOWN_7 = new AxisAlignedBB(x - W7, y - SP7, z - W7, x + W7, y - D7, z + W7);
								AxisAlignedBB DOWN_8 = new AxisAlignedBB(x - W8, y - SP8, z - W8, x + W8, y - D8, z + W8);

								List<EntityLiving> LIST_D1 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_1);
								List<EntityLiving> LIST_D2 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_2);
								List<EntityLiving> LIST_D3 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_3);
								List<EntityLiving> LIST_D4 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_4);
								List<EntityLiving> LIST_D5 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_5);
								List<EntityLiving> LIST_D6 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_6);
								List<EntityLiving> LIST_D7 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_7);
								List<EntityLiving> LIST_D8 = worldIn.getEntitiesWithinAABB(EntityLiving.class, DOWN_8);

								Stream<EntityLiving> FILTER_D1 = LIST_D1.stream().filter(entity -> rangeInt(entity, playerIn, D1)).filter(entity -> checkGlow(entity));
								Stream<EntityLiving> FILTER_D2 = LIST_D2.stream().filter(entity -> rangeInt(entity, playerIn, D2)).filter(entity -> checkGlow(entity));
								Stream<EntityLiving> FILTER_D3 = LIST_D3.stream().filter(entity -> rangeInt(entity, playerIn, D3)).filter(entity -> checkGlow(entity));
								Stream<EntityLiving> FILTER_D4 = LIST_D4.stream().filter(entity -> rangeInt(entity, playerIn, D4)).filter(entity -> checkGlow(entity));
								Stream<EntityLiving> FILTER_D5 = LIST_D5.stream().filter(entity -> rangeInt(entity, playerIn, D5)).filter(entity -> checkGlow(entity));
								Stream<EntityLiving> FILTER_D6 = LIST_D6.stream().filter(entity -> rangeInt(entity, playerIn, D6)).filter(entity -> checkGlow(entity));
								Stream<EntityLiving> FILTER_D7 = LIST_D7.stream().filter(entity -> rangeInt(entity, playerIn, D7)).filter(entity -> checkGlow(entity));
								Stream<EntityLiving> FILTER_D8 = LIST_D8.stream().filter(entity -> rangeInt(entity, playerIn, D8)).filter(entity -> checkGlow(entity));

								/** add Potion Effect. **/
								if (!worldIn.isRemote) {
									FILTER_D1.forEach(entity -> entity.addPotionEffect(this.glowTIME(90)));
									scheduler.schedule(() -> FILTER_D2.forEach(entity -> entity.addPotionEffect(this.glowTIME(80))), ms * 1, milliS);
									scheduler.schedule(() -> FILTER_D3.forEach(entity -> entity.addPotionEffect(this.glowTIME(70))), ms * 2, milliS);
									scheduler.schedule(() -> FILTER_D4.forEach(entity -> entity.addPotionEffect(this.glowTIME(60))), ms * 3, milliS);
									scheduler.schedule(() -> FILTER_D5.forEach(entity -> entity.addPotionEffect(this.glowTIME(50))), ms * 4, milliS);
									scheduler.schedule(() -> FILTER_D6.forEach(entity -> entity.addPotionEffect(this.glowTIME(40))), ms * 5, milliS);
									scheduler.schedule(() -> FILTER_D7.forEach(entity -> entity.addPotionEffect(this.glowTIME(30))), ms * 6, milliS);
									scheduler.schedule(() -> FILTER_D8.forEach(entity -> entity.addPotionEffect(this.glowTIME(20))), ms * 7, milliS); }

								this.coolDown(worldIn, playerIn);
								/** Sonar DOWN_2 **/
								return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, hStack); 
							} //!Cooldown
						} //Material.WATER
						
						else { 
							this.text_Tips2(worldIn, playerIn);
							return new ActionResult<ItemStack>(EnumActionResult.FAIL, hStack); }
					} //FlowingFluid
					
					else { 
						this.text_Tips2(worldIn, playerIn);
						return new ActionResult<ItemStack>(EnumActionResult.FAIL, hStack); }
				} //raytrace
			} //waterOUT
		} //GISOU
		
		else { 
			this.GISOU_Not4(worldIn, playerIn);
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, hStack); }
	}
	
	/* ToolTip */
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.item_device_sonar.name"));
		itemTip.add(I18n.format("tips.item_device_sonar2.name"));
		itemTip.add(TextFormatting.BLUE + I18n.format("tips.item_device_sonar3.name"));
	}
}
