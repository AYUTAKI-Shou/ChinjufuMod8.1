package com.ayutaki.chinjufumod.blocks.crop;

import java.util.List;
import java.util.Random;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Ami_Youshoku extends Base_YoushokuAmi {

	public Ami_Youshoku(AbstractBlock.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int stage = state.getValue(STAGE_1_9);
		int age = state.getValue(AGE_1_12);
		
		if (stage == 5) {
			
			if (hItem == Items.STRING || hItem == Items_Seasonal.ORIITO) {
				/* 5 times */
				if (playerIn.totalExperience >= 15) {
					
					if (age >= 4) {
						CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand);
						playerIn.giveExperiencePoints(-3);
						
						double x = (double) pos.getX();
						double y = (double) pos.getY();
						double z = (double) pos.getZ();
						
						worldIn.setBlock(pos, this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
								.setValue(AGE_1_12, Integer.valueOf(age - 3))
								.setValue(DOWN, state.getValue(DOWN))
								.setValue(UP, state.getValue(UP))
								.setValue(WAKE, state.getValue(WAKE))
								.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
						worldIn.setBlock(new BlockPos(x - 1, y, z - 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(1))
								.setValue(AGE_1_12, Integer.valueOf(age - 3))
								.setValue(DOWN, state.getValue(DOWN))
								.setValue(UP, state.getValue(UP))
								.setValue(WAKE, state.getValue(WAKE))
								.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
						worldIn.setBlock(new BlockPos(x, y, z - 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(2))
								.setValue(AGE_1_12, Integer.valueOf(age - 3))
								.setValue(DOWN, state.getValue(DOWN))
								.setValue(UP, state.getValue(UP))
								.setValue(WAKE, state.getValue(WAKE))
								.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
						worldIn.setBlock(new BlockPos(x + 1, y, z - 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(3))
								.setValue(AGE_1_12, Integer.valueOf(age - 3))
								.setValue(DOWN, state.getValue(DOWN))
								.setValue(UP, state.getValue(UP))
								.setValue(WAKE, state.getValue(WAKE))
								.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
						worldIn.setBlock(new BlockPos(x - 1, y, z), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(4))
								.setValue(AGE_1_12, Integer.valueOf(age - 3))
								.setValue(DOWN, state.getValue(DOWN))
								.setValue(UP, state.getValue(UP))
								.setValue(WAKE, state.getValue(WAKE))
								.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
						worldIn.setBlock(new BlockPos(x + 1, y, z), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(6))
								.setValue(AGE_1_12, Integer.valueOf(age - 3))
								.setValue(DOWN, state.getValue(DOWN))
								.setValue(UP, state.getValue(UP))
								.setValue(WAKE, state.getValue(WAKE))
								.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
						worldIn.setBlock(new BlockPos(x - 1, y, z + 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(7))
								.setValue(AGE_1_12, Integer.valueOf(age - 3))
								.setValue(DOWN, state.getValue(DOWN))
								.setValue(UP, state.getValue(UP))
								.setValue(WAKE, state.getValue(WAKE))
								.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
						worldIn.setBlock(new BlockPos(x, y, z + 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(8))
								.setValue(AGE_1_12, Integer.valueOf(age - 3))
								.setValue(DOWN, state.getValue(DOWN))
								.setValue(UP, state.getValue(UP))
								.setValue(WAKE, state.getValue(WAKE))
								.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
						worldIn.setBlock(new BlockPos(x + 1, y, z + 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(9))
								.setValue(AGE_1_12, Integer.valueOf(age - 3))
								.setValue(DOWN, state.getValue(DOWN))
								.setValue(UP, state.getValue(UP))
								.setValue(WAKE, state.getValue(WAKE))
								.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3); }
					
					if (age < 4) { CMEvents.textNotHave(worldIn, pos, playerIn); }
				}
				
				/** Not enough EXP **/
				if (playerIn.totalExperience < 15) { CMEvents.textNotEnough_EXP(worldIn, pos, playerIn); }
			} //COBWEB

			if (hItem == Items.COD_BUCKET) {
				if (state.getValue(WATERLOGGED)) {
					spawnCod(worldIn, pos);
					CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand); }
				
				else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
			}
			
			if (hItem == Items.SALMON_BUCKET) {
				if (state.getValue(WATERLOGGED)) {
					spawnSalmon(worldIn, pos);
					CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand); }
				
				else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
			}
			
			if (hItem == Items.TROPICAL_FISH_BUCKET) {
				if (state.getValue(WATERLOGGED)) {
					spawnTropicalFish(worldIn, pos);
					CMEvents.Bucket_toEmpty(worldIn, pos, playerIn, hand); }
				
				else { CMEvents.textNoPlace(worldIn, pos, playerIn); }
			}
			
			if (hItem == Items_Teatime.YOUSHOKU_AMI) { return ActionResultType.FAIL; }
			
			if (hItem != Items_Teatime.YOUSHOKU_AMI && hItem != Items.STRING && hItem != Items_Seasonal.ORIITO && 
					hItem != Items.COD_BUCKET && hItem != Items.SALMON_BUCKET && hItem != Items.TROPICAL_FISH_BUCKET) {
				CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		if (stage != 5) { }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
	
		ItemStack hStack = context.getItemInHand();
		
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		if (worldIn.getBlockState(new BlockPos(x - 1, y, z - 1)).canBeReplaced(context) &&
				worldIn.getBlockState(new BlockPos(x, y, z - 1)).canBeReplaced(context) &&
				worldIn.getBlockState(new BlockPos(x + 1, y, z - 1)).canBeReplaced(context) &&
				worldIn.getBlockState(new BlockPos(x + 1, y, z)).canBeReplaced(context) &&
				worldIn.getBlockState(new BlockPos(x - 1, y, z)).canBeReplaced(context) &&
				worldIn.getBlockState(new BlockPos(x + 1, y, z + 1)).canBeReplaced(context) &&
				worldIn.getBlockState(new BlockPos(x, y, z + 1)).canBeReplaced(context) &&
				worldIn.getBlockState(new BlockPos(x - 1, y, z + 1)).canBeReplaced(context)) {

			if ((hStack.getDamageValue() > hStack.getMaxDamage() - 12) &&
					(hStack.getDamageValue() <= hStack.getMaxDamage() - 11)) {
				return this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
						.setValue(AGE_1_12, Integer.valueOf(2))
						.setValue(DOWN, connectThis(worldIn, pos, Direction.DOWN))
						.setValue(UP, connectAir(worldIn, pos, Direction.UP))
						.setValue(WAKE, Boolean.valueOf(false))
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
			
			if ((hStack.getDamageValue() > hStack.getMaxDamage() - 11) &&
					(hStack.getDamageValue() <= hStack.getMaxDamage() - 10)) {
				return this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
						.setValue(AGE_1_12, Integer.valueOf(3))
						.setValue(DOWN, connectThis(worldIn, pos, Direction.DOWN))
						.setValue(UP, connectAir(worldIn, pos, Direction.UP))
						.setValue(WAKE, Boolean.valueOf(false))
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
			
			if ((hStack.getDamageValue() > hStack.getMaxDamage() - 10) &&
					(hStack.getDamageValue() <= hStack.getMaxDamage() - 9)) {
				return this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
						.setValue(AGE_1_12, Integer.valueOf(4))
						.setValue(DOWN, connectThis(worldIn, pos, Direction.DOWN))
						.setValue(UP, connectAir(worldIn, pos, Direction.UP))
						.setValue(WAKE, Boolean.valueOf(false))
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
			
			if ((hStack.getDamageValue() > hStack.getMaxDamage() - 9) &&
					(hStack.getDamageValue() <= hStack.getMaxDamage() - 8)) {
				return this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
						.setValue(AGE_1_12, Integer.valueOf(5))
						.setValue(DOWN, connectThis(worldIn, pos, Direction.DOWN))
						.setValue(UP, connectAir(worldIn, pos, Direction.UP))
						.setValue(WAKE, Boolean.valueOf(false))
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
			
			if ((hStack.getDamageValue() > hStack.getMaxDamage() - 8) &&
					(hStack.getDamageValue() < hStack.getMaxDamage() - 6)) {
				return this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
						.setValue(AGE_1_12, Integer.valueOf(6))
						.setValue(DOWN, connectThis(worldIn, pos, Direction.DOWN))
						.setValue(UP, connectAir(worldIn, pos, Direction.UP))
						.setValue(WAKE, Boolean.valueOf(false))
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
			
			if (hStack.getDamageValue() == hStack.getMaxDamage() - 6) {
				return this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
						.setValue(AGE_1_12, Integer.valueOf(7))
						.setValue(DOWN, connectThis(worldIn, pos, Direction.DOWN))
						.setValue(UP, connectAir(worldIn, pos, Direction.UP))
						.setValue(WAKE, Boolean.valueOf(false))
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
			
			if ((hStack.getDamageValue() > hStack.getMaxDamage() - 6) &&
					(hStack.getDamageValue() <= hStack.getMaxDamage() - 5)) {
				return this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
						.setValue(AGE_1_12, Integer.valueOf(8))
						.setValue(DOWN, connectThis(worldIn, pos, Direction.DOWN))
						.setValue(UP, connectAir(worldIn, pos, Direction.UP))
						.setValue(WAKE, Boolean.valueOf(false))
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
			
			if ((hStack.getDamageValue() > hStack.getMaxDamage() - 5) &&
					(hStack.getDamageValue() <= hStack.getMaxDamage() - 4)) {
				return this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
						.setValue(AGE_1_12, Integer.valueOf(9))
						.setValue(DOWN, connectThis(worldIn, pos, Direction.DOWN))
						.setValue(UP, connectAir(worldIn, pos, Direction.UP))
						.setValue(WAKE, Boolean.valueOf(false))
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
			
			if ((hStack.getDamageValue() > hStack.getMaxDamage() - 4) &&
					(hStack.getDamageValue() <= hStack.getMaxDamage() - 3)) {
				return this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
						.setValue(AGE_1_12, Integer.valueOf(10))
						.setValue(DOWN, connectThis(worldIn, pos, Direction.DOWN))
						.setValue(UP, connectAir(worldIn, pos, Direction.UP))
						.setValue(WAKE, Boolean.valueOf(false))
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
			
			if ((hStack.getDamageValue() > hStack.getMaxDamage() - 3) &&
					(hStack.getDamageValue() <= hStack.getMaxDamage() - 2)) {
				return this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
						.setValue(AGE_1_12, Integer.valueOf(11))
						.setValue(DOWN, connectThis(worldIn, pos, Direction.DOWN))
						.setValue(UP, connectAir(worldIn, pos, Direction.UP))
						.setValue(WAKE, Boolean.valueOf(false))
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
			
			if (hStack.getDamageValue() > hStack.getMaxDamage() - 2) {
				return this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
						.setValue(AGE_1_12, Integer.valueOf(12))
						.setValue(DOWN, connectThis(worldIn, pos, Direction.DOWN))
						.setValue(UP, connectAir(worldIn, pos, Direction.UP))
						.setValue(WAKE, Boolean.valueOf(false))
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
			
			else {
				return this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
						.setValue(AGE_1_12, Integer.valueOf(1))
						.setValue(DOWN, connectThis(worldIn, pos, Direction.DOWN))
						.setValue(UP, connectAir(worldIn, pos, Direction.UP))
						.setValue(WAKE, Boolean.valueOf(false))
						.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
		}
		
		else { 
			CMEvents.textNoPlace(context.getLevel(), context.getClickedPos(), context.getPlayer());
			return null; }
	}

	/* Add other Blocks. */
	@Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		FluidState fluid = worldIn.getFluidState(pos);
		
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		BlockPos pos1 = new BlockPos(x - 1, y, z - 1);
		BlockPos pos2 = new BlockPos(x, y, z - 1);
		BlockPos pos3 = new BlockPos(x + 1, y, z - 1);
		BlockPos pos4 = new BlockPos(x - 1, y, z);
		BlockPos pos6 = new BlockPos(x + 1, y, z);
		BlockPos pos7 = new BlockPos(x - 1, y, z + 1);
		BlockPos pos8 = new BlockPos(x, y, z + 1);
		BlockPos pos9 = new BlockPos(x + 1, y, z + 1);
		
		worldIn.setBlock(pos1, this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(1))
				.setValue(AGE_1_12, Integer.valueOf(state.getValue(AGE_1_12)))
				.setValue(DOWN, connectThis(worldIn, pos1, Direction.DOWN))
				.setValue(UP, connectAir(worldIn, pos1, Direction.UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)), 3);
		worldIn.setBlock(pos2, this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(2))
				.setValue(AGE_1_12, Integer.valueOf(state.getValue(AGE_1_12)))
				.setValue(DOWN, connectThis(worldIn, pos2, Direction.DOWN))
				.setValue(UP, connectAir(worldIn, pos2, Direction.UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)), 3);
		worldIn.setBlock(pos3, this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(3))
				.setValue(AGE_1_12, Integer.valueOf(state.getValue(AGE_1_12)))
				.setValue(DOWN, connectThis(worldIn, pos3, Direction.DOWN))
				.setValue(UP, connectAir(worldIn, pos3, Direction.UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)), 3);
		worldIn.setBlock(pos4, this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(4))
				.setValue(AGE_1_12, Integer.valueOf(state.getValue(AGE_1_12)))
				.setValue(DOWN, connectThis(worldIn, pos4, Direction.DOWN))
				.setValue(UP, connectAir(worldIn, pos4, Direction.UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)), 3);
		worldIn.setBlock(pos6, this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(6))
				.setValue(AGE_1_12, Integer.valueOf(state.getValue(AGE_1_12)))
				.setValue(DOWN, connectThis(worldIn, pos6, Direction.DOWN))
				.setValue(UP, connectAir(worldIn, pos6, Direction.UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)), 3);
		worldIn.setBlock(pos7, this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(7))
				.setValue(AGE_1_12, Integer.valueOf(state.getValue(AGE_1_12)))
				.setValue(DOWN, connectThis(worldIn, pos7, Direction.DOWN))
				.setValue(UP, connectAir(worldIn, pos7, Direction.UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)), 3);
		worldIn.setBlock(pos8, this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(8))
				.setValue(AGE_1_12, Integer.valueOf(state.getValue(AGE_1_12)))
				.setValue(DOWN, connectThis(worldIn, pos8, Direction.DOWN))
				.setValue(UP, connectAir(worldIn, pos8, Direction.UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)), 3);
		worldIn.setBlock(pos9, this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(9))
				.setValue(AGE_1_12, Integer.valueOf(state.getValue(AGE_1_12)))
				.setValue(DOWN, connectThis(worldIn, pos9, Direction.DOWN))
				.setValue(UP, connectAir(worldIn, pos9, Direction.UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)), 3);
	}
	
	/* Increase fish. */
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int stage = state.getValue(STAGE_1_9);
		int age = state.getValue(AGE_1_12);
		boolean wake = state.getValue(WAKE);
		
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }
		
		switch (stage) {
		case 1: break;
		case 2: break;
		case 3: break;
		case 4: break;
		case 5:
		default: 
			if (state.getValue(WATERLOGGED)) {
				if (wake) {
					switch (age) {
					case 1:
					default:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6:
					case 7:
					case 8:
					case 9:
					case 10:
					case 11:
						AxisAlignedBB AABB_SPAWN = new AxisAlignedBB(pos.getX() - 1, pos.getY(), pos.getZ() - 1, pos.getX() + 2, pos.getY() + 1, pos.getZ() + 2);
						AxisAlignedBB AABB_CHECK = new AxisAlignedBB(pos.getX() - 1, pos.getY() - 0.5, pos.getZ() - 1, pos.getX() + 2, pos.getY() + 1.5, pos.getZ() + 2);
						
						List<CodEntity> listCod = worldIn.getEntitiesOfClass(CodEntity.class, AABB_SPAWN);
						List<SalmonEntity> listSalmon = worldIn.getEntitiesOfClass(SalmonEntity.class, AABB_SPAWN);
						List<TropicalFishEntity> listTropicalFish = worldIn.getEntitiesOfClass(TropicalFishEntity.class, AABB_SPAWN);
						List<AbstractFishEntity> listFish = worldIn.getEntitiesOfClass(AbstractFishEntity.class, AABB_CHECK);
						/** age1=2, age2=3, age3=4, age4=5, age5=6, age6=7 **/
						
						if (listFish.size() >= 5) { }
						
						if (listFish.size() < 5) {
									if (listCod.size() >= 2 && rand.nextInt(4) == 0) {
								spawnCod(worldIn, pos);
								agingNet(state, worldIn, pos); }
							
									if (listSalmon.size() >= 2 && rand.nextInt(4) == 0) {
								spawnSalmon(worldIn, pos);
								agingNet(state, worldIn, pos); }
									
									if (listTropicalFish.size() >= 2 && rand.nextInt(4) == 0) {
								spawnTropicalFish(worldIn, pos);
								agingNet(state, worldIn, pos); } }
						break;
					case 12: break;
					} // switch AGE_1_12
				}
				
				else { //!wake
					if (rand.nextInt(4) == 0) { wakeUp(state, worldIn, pos); } }
			}
			
			else { //!WATERLOGGED
				if (rand.nextInt(1) == 0) { worldIn.destroyBlock(pos, true); } }
			break;
		case 6: break;
		case 7: break;
		case 8: break;
		case 9: break;
		} // switch STAGE_1_9
	}

	/* Fish name */
	ITextComponent codName = new TranslationTextComponent("name.farmedcod" );
	ITextComponent salmonName = new TranslationTextComponent("name.farmedsalmon");
	ITextComponent tropicalName = new TranslationTextComponent("name.farmedtropicalfish");
	
	/* Spawn fish */
	private void spawnCod(World worldIn, BlockPos pos) {
		CodEntity cod = new CodEntity(EntityType.COD, worldIn);
		cod.setPos(pos.getX(), pos.getY(), pos.getZ());
		cod.setCustomName(codName);
		worldIn.addFreshEntity(cod);
		CMEvents.soundFish(worldIn, pos);
	}
	
	private void spawnSalmon(World worldIn, BlockPos pos) {
		SalmonEntity salmon = new SalmonEntity(EntityType.SALMON, worldIn);
		salmon.setPos(pos.getX(), pos.getY(), pos.getZ());
		salmon.setCustomName(salmonName);
		worldIn.addFreshEntity(salmon);
		CMEvents.soundFish(worldIn, pos);
	}
	
	private void spawnTropicalFish(World worldIn, BlockPos pos) {
		TropicalFishEntity tropical = new TropicalFishEntity(EntityType.TROPICAL_FISH, worldIn);
		tropical.setPos(pos.getX(), pos.getY(), pos.getZ());
		tropical.setCustomName(tropicalName);
		worldIn.addFreshEntity(tropical);
		CMEvents.soundFish(worldIn, pos);
	}
	
	
	/* Net state changes. */
	private void agingNet(BlockState state, World worldIn, BlockPos pos) {
		int age = state.getValue(AGE_1_12);

		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		worldIn.setBlock(pos, this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
				.setValue(AGE_1_12, Integer.valueOf(age + 1))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x - 1, y, z - 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(1))
				.setValue(AGE_1_12, Integer.valueOf(age + 1))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x, y, z - 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(2))
				.setValue(AGE_1_12, Integer.valueOf(age + 1))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x + 1, y, z - 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(3))
				.setValue(AGE_1_12, Integer.valueOf(age + 1))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x - 1, y, z), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(4))
				.setValue(AGE_1_12, Integer.valueOf(age + 1))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x + 1, y, z), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(6))
				.setValue(AGE_1_12, Integer.valueOf(age + 1))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x - 1, y, z + 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(7))
				.setValue(AGE_1_12, Integer.valueOf(age + 1))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x, y, z + 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(8))
				.setValue(AGE_1_12, Integer.valueOf(age + 1))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x + 1, y, z + 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(9))
				.setValue(AGE_1_12, Integer.valueOf(age + 1))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(false))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
	}
	
	private void wakeUp(BlockState state, World worldIn, BlockPos pos) {
		int age = state.getValue(AGE_1_12);

		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		worldIn.setBlock(pos, this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(5))
				.setValue(AGE_1_12, Integer.valueOf(age))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(true))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x - 1, y, z - 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(1))
				.setValue(AGE_1_12, Integer.valueOf(age))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(true))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x, y, z - 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(2))
				.setValue(AGE_1_12, Integer.valueOf(age))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(true))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x + 1, y, z - 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(3))
				.setValue(AGE_1_12, Integer.valueOf(age))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(true))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x - 1, y, z), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(4))
				.setValue(AGE_1_12, Integer.valueOf(age))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(true))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x + 1, y, z), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(6))
				.setValue(AGE_1_12, Integer.valueOf(age))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(true))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x - 1, y, z + 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(7))
				.setValue(AGE_1_12, Integer.valueOf(age))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(true))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x, y, z + 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(8))
				.setValue(AGE_1_12, Integer.valueOf(age))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(true))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
		worldIn.setBlock(new BlockPos(x + 1, y, z + 1), this.defaultBlockState().setValue(STAGE_1_9, Integer.valueOf(9))
				.setValue(AGE_1_12, Integer.valueOf(age))
				.setValue(DOWN, state.getValue(DOWN))
				.setValue(UP, state.getValue(UP))
				.setValue(WAKE, Boolean.valueOf(true))
				.setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 3);
	}
}
