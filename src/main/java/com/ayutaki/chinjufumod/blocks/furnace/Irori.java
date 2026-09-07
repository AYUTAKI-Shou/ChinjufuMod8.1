package com.ayutaki.chinjufumod.blocks.furnace;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.blocks.dish.BaseIrori_Sakana;
import com.ayutaki.chinjufumod.blocks.dish.BaseNabe;
import com.ayutaki.chinjufumod.blocks.dish.BaseNabe_2Cook;
import com.ayutaki.chinjufumod.blocks.dish.BaseNabe_nama;
import com.ayutaki.chinjufumod.blocks.dish.Kettle_full;
import com.ayutaki.chinjufumod.blocks.dish.Nabe_kara;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class Irori extends Abstract_WaterLogged {

	protected static final int FIRE_OFF = 120;
	/* Property */
	public static final IntegerProperty STAGE_0_10 = IntegerProperty.create("stage", 0, 10);
	public static final BooleanProperty LIT = BooleanProperty.create("lit");
	public static final BooleanProperty DONABE = BooleanProperty.create("donabe");

	/* Collision */
	private static final VoxelShape AABB_BOX = VoxelShapes.or(Block.box(0.0D, 11.0D, 0.0D, 16.0D, 15.0D, 0.5D),
			Block.box(0.0D, 11.0D, 15.5D, 16.0D, 15.0D, 16.0D),
			Block.box(0.0D, 11.0D, 0.0D, 0.5D, 15.0D, 16.0D),
			Block.box(15.5D, 11.0D, 0.0D, 16.0D, 15.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D));

	public Irori(AbstractBlock.Properties props) {
		super(props);
		registerDefaultState(this.defaultBlockState().setValue(STAGE_0_10, Integer.valueOf(0))
				.setValue(WATERLOGGED, Boolean.valueOf(false))
				.setValue(LIT, Boolean.valueOf(false))
				.setValue(DONABE, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_0_10);

		/** Waterlogged **/
		if (state.getValue(WATERLOGGED)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }
		
		else { //!WATERLOGGED

			if (hItem == Items.CHARCOAL) {
				if (i == 0) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, state.setValue(STAGE_0_10, Integer.valueOf(1)), 3); }
				
				if (i > 2 && i < 10) {
					this.Charcoal2BoneMeal(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, state.setValue(STAGE_0_10, Integer.valueOf(2)).setValue(LIT, Boolean.valueOf(true)), 3); }
				
				if (i == 10) {
					this.Charcoal2BoneMeal(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, state.setValue(STAGE_0_10, Integer.valueOf(1)), 3); } }


			if (hItem == Items.FLINT_AND_STEEL) {
				if (i == 1) {
					CMEvents.soundFlint(worldIn, pos);
					CMEvents.toolDamege(1, playerIn, hStack);
					worldIn.setBlock(pos, state.setValue(STAGE_0_10, Integer.valueOf(2)).setValue(LIT, Boolean.valueOf(true)), 3); } }


			if (hItem == Items_Teatime.MATCH) {
				if (i == 1) {
					CMEvents.consume1_seFlint(worldIn, pos, playerIn, hand);	
					worldIn.setBlock(pos, state.setValue(STAGE_0_10, Integer.valueOf(2)).setValue(LIT, Boolean.valueOf(true)), 3); } }
			
			/** 魚の串焼き **/
			boolean hitNorth = (hit.getLocation().x - (double)pos.getX() > 0.3D) && (hit.getLocation().x - (double)pos.getX() < 0.7D) && (hit.getLocation().z - (double)pos.getZ() < 0.3D);
			boolean hitSouth = (hit.getLocation().x - (double)pos.getX() > 0.3D) && (hit.getLocation().x - (double)pos.getX() < 0.7D) && (hit.getLocation().z - (double)pos.getZ() > 0.7D);
			boolean hitEast = (hit.getLocation().x - (double)pos.getX() > 0.7D) && (hit.getLocation().z - (double)pos.getZ() > 0.3D) && (hit.getLocation().z - (double)pos.getZ() < 0.7D);
			boolean hitWest = (hit.getLocation().x - (double)pos.getX() < 0.3D) && (hit.getLocation().z - (double)pos.getZ() > 0.3D) && (hit.getLocation().z - (double)pos.getZ() < 0.7D);

			if (hItem == Items_Teatime.KUSHI_SAKANA && worldIn.getBlockState(pos.above()).getMaterial().isReplaceable()) {

				if (hitNorth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos.above(), Dish_Blocks.IRORISAKANA_E2.defaultBlockState()
						.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(10)), 3); }

				if (hitSouth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos.above(), Dish_Blocks.IRORISAKANA_E1.defaultBlockState()
						.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(2)), 3); }

				if (hitEast) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);	
					worldIn.setBlock(pos.above(), Dish_Blocks.IRORISAKANA_E1.defaultBlockState()
						.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(8)), 3); }

				if (hitWest) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);	
					worldIn.setBlock(pos.above(), Dish_Blocks.IRORISAKANA_E1.defaultBlockState()
						.setValue(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(0)), 3); } }


			/** No usable items. **/
			if (hItem != Items.CHARCOAL && hItem != Items_Teatime.KUSHI_SAKANA && hItem != Items_Teatime.MATCH && hItem != Items.FLINT_AND_STEEL) {
				if (hStack.isEmpty()) {
					if (i == 10) {
						CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items.BONE_MEAL);
						worldIn.setBlock(pos, state.setValue(STAGE_0_10, Integer.valueOf(0)), 3); }
					
					else { //i != 10
						CMEvents.textNotHave(worldIn, pos, playerIn); } }
				
				else { //!empty
					CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
		}
		return ActionResultType.SUCCESS;
	}

	private void Charcoal2BoneMeal(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		boolean mode = playerIn.abilities.instabuild;

		if (mode) { }
		else { //!mode
			hStack.shrink(1);
			InventoryHelper.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BONE_MEAL)); }
		CMEvents.soundSnowPlace(worldIn, pos); }
	
	
	/* Gives a value when placed. */
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);
		
		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}

	/* Connect the blocks. */
	protected boolean connectNabe(IBlockReader worldIn, BlockPos pos, Direction face) {
		BlockPos newPos = pos.relative(face);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();

		return (block instanceof BaseNabe || block instanceof BaseNabe_2Cook || block instanceof BaseNabe_nama ||
				block instanceof Nabe_kara || block instanceof Kettle_full);
	}

	/* Distinguish LOST from WATERLOGGED. */
	private boolean waterIn(BlockState state) {
		return state.getValue(WATERLOGGED);
	}

	private boolean waterOUT(BlockState state) {
		return !state.getValue(WATERLOGGED);
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		if (waterIn(state)) { 
			worldIn.getBlockTicks().scheduleTick(pos, Kitchen_Blocks.IRORI, FIRE_OFF); }

		boolean donabe = connectNabe(worldIn, pos, Direction.UP);
		return state.setValue(DONABE, donabe);
	}

	/* TickRandom */
	@Override
	public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { 
			worldIn.getBlockTicks().scheduleTick(pos, Kitchen_Blocks.IRORI, FIRE_OFF); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.getValue(STAGE_0_10);

		if (waterIn(state)) {
			/** Nothing on the top. **/
			if (i == 0) { }

			else { //i != 0
				worldIn.getBlockTicks().scheduleTick(pos, Kitchen_Blocks.IRORI, FIRE_OFF);			
				worldIn.setBlock(pos, state.setValue(STAGE_0_10, Integer.valueOf(0)).setValue(LIT, Boolean.valueOf(false)), 3);
				
				/** Charcoal on the top. **/
				if (i == 1) {
					CMEvents.soundSnowPlace(worldIn, pos);
					this.dropCharcoal(worldIn, pos); }
				
				/** Charcoal is burning. **/
				if (i >= 2 && i <= 9) {
					CMEvents.soundFireExting(worldIn, pos);
					this.dropBonemeal(worldIn, pos); }
				
				/** Ash on the top. **/
				if (i == 10) {
					CMEvents.soundSnowPlace(worldIn, pos);
					this.dropBonemeal(worldIn, pos); }
			}
		}
	}

	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.getValue(STAGE_0_10);
		
		if (waterOUT(state)) {
			/** Charcoal is burning. **/
			if (i >= 2 && i <= 9) {
				if (rand.nextInt(1) == 0) {
					if (i <= 6) { worldIn.setBlock(pos, state.setValue(STAGE_0_10, Integer.valueOf(i + 1)).setValue(LIT, Boolean.valueOf(true)), 3); }
	
					if (i >= 7) { worldIn.setBlock(pos, state.setValue(STAGE_0_10, Integer.valueOf(i + 1)).setValue(LIT, Boolean.valueOf(false)), 3); }
				}
			}
		}
	}

	private void dropBonemeal(ServerWorld worldIn, BlockPos pos) {
		ItemStack stack = new ItemStack(Items.BONE_MEAL);
		InventoryHelper.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	}

	private void dropCharcoal(ServerWorld worldIn, BlockPos pos) {
		ItemStack stack = new ItemStack(Items.CHARCOAL);
		InventoryHelper.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DONABE, LIT, STAGE_0_10, WATERLOGGED);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}

	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}

	/* If you walk on it while it's burning, you'll take damage. */
	public void stepOn(World worldIn, BlockPos pos, Entity entity) {
		int px = pos.getX();
		int py = pos.getY();
		int pz = pos.getZ();

		BlockState state = worldIn.getBlockState(new BlockPos(px, py, pz));
		int i = state.getValue(STAGE_0_10);

		if (i > 1 && i < 10) {
			if (!entity.fireImmune() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
				entity.hurt(DamageSource.HOT_FLOOR, 1.0F);
			}
			super.stepOn(worldIn, pos, entity);
		}
	}

	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;

		int i = state.getValue(STAGE_0_10);
		if (i != 0 && i != 1 && i != 10) {
			if (rand.nextDouble() < 0.1D) {
				worldIn.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false); }

			if (rand.nextDouble() < 0.25D) {
				/** which, position x y z, speed x y z **/
				worldIn.addParticle(ParticleTypes.SMOKE, d0 + d4, d1 + d6, d2 + d4, 0.0D, 0.0D, 0.0D); }
		}
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_irori").withStyle(TextFormatting.GRAY));
	}
}
