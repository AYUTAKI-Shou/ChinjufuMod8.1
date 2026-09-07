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

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
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

	protected static final int FIRE_TIME = 120;
	/* Property */
	public static final IntegerProperty STAGE_0_10 = IntegerProperty.create("stage", 0, 10);
	public static final BooleanProperty LIT = BooleanProperty.create("lit");
	public static final BooleanProperty DONABE = BooleanProperty.create("donabe");

	/* Collision */
	private static final VoxelShape AABB_BOX = VoxelShapes.or(Block.makeCuboidShape(0.0D, 11.0D, 0.0D, 16.0D, 15.0D, 0.5D),
			Block.makeCuboidShape(0.0D, 11.0D, 15.5D, 16.0D, 15.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 11.0D, 0.0D, 0.5D, 15.0D, 16.0D),
			Block.makeCuboidShape(15.5D, 11.0D, 0.0D, 16.0D, 15.0D, 16.0D),
			Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D));

	public Irori(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(STAGE_0_10, Integer.valueOf(0))
				.with(WATERLOGGED, Boolean.valueOf(false))
				.with(LIT, Boolean.valueOf(false))
				.with(DONABE, Boolean.valueOf(false)));
	}

	public int getLightValue(BlockState state) {
		int i = state.get(STAGE_0_10);
		return (i == 0 || i == 1 || i == 10) ? 0 : ((state.get(LIT))? 14 : 12);
	}

	/* RightClick Action */
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_0_10);

		/** Waterlogged **/
		if (state.get(WATERLOGGED)) { CMEvents.textIsWaterlogged(worldIn, pos, playerIn); }
		
		else { //!WATERLOGGED

			if (hItem == Items.CHARCOAL) {
				if (i == 0) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.with(STAGE_0_10, Integer.valueOf(1))); }
				
				if (i > 2 && i < 10) {
					this.Charcoal2BoneMeal(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.with(STAGE_0_10, Integer.valueOf(2)).with(LIT, Boolean.valueOf(true))); }
				
				if (i == 10) {
					this.Charcoal2BoneMeal(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos, state.with(STAGE_0_10, Integer.valueOf(1))); } }
			
			if (hItem == Items.FLINT_AND_STEEL) {
				if (i == 1) {
					CMEvents.soundFlint(worldIn, pos);
					CMEvents.toolDamege(1, playerIn, hStack);
					worldIn.setBlockState(pos, state.with(STAGE_0_10, Integer.valueOf(2)).with(LIT, Boolean.valueOf(true))); } }
			
			if (hItem == Items_Teatime.MATCH) {
				if (i == 1) {
					CMEvents.consume1_seFlint(worldIn, pos, playerIn, hand);				
					worldIn.setBlockState(pos, state.with(STAGE_0_10, Integer.valueOf(2)).with(LIT, Boolean.valueOf(true))); } }
			
			/** 魚の串焼き **/
			boolean hitNorth = (hit.getHitVec().x - (double)pos.getX() > 0.3D) && (hit.getHitVec().x - (double)pos.getX() < 0.7D) && (hit.getHitVec().z - (double)pos.getZ() < 0.3D);
			boolean hitSouth = (hit.getHitVec().x - (double)pos.getX() > 0.3D) && (hit.getHitVec().x - (double)pos.getX() < 0.7D) && (hit.getHitVec().z - (double)pos.getZ() > 0.7D);
			boolean hitEast = (hit.getHitVec().x - (double)pos.getX() > 0.7D) && (hit.getHitVec().z - (double)pos.getZ() > 0.3D) && (hit.getHitVec().z - (double)pos.getZ() < 0.7D);
			boolean hitWest = (hit.getHitVec().x - (double)pos.getX() < 0.3D) && (hit.getHitVec().z - (double)pos.getZ() > 0.3D) && (hit.getHitVec().z - (double)pos.getZ() < 0.7D);

			if (hItem == Items_Teatime.KUSHI_SAKANA && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable()) {

				if (hitNorth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos.up(), Dish_Blocks.IRORISAKANA_E2.getDefaultState()
						.with(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(10))); }

				if (hitSouth) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos.up(), Dish_Blocks.IRORISAKANA_E1.getDefaultState()
						.with(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(2))); }

				if (hitEast) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos.up(), Dish_Blocks.IRORISAKANA_E1.getDefaultState()
						.with(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(8))); }

				if (hitWest) {
					CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
					worldIn.setBlockState(pos.up(), Dish_Blocks.IRORISAKANA_E1.getDefaultState()
						.with(BaseIrori_Sakana.STAGE_0_15, Integer.valueOf(0))); }
			}
			
			/** No usable items. **/
			if (hItem != Items.CHARCOAL && hItem != Items_Teatime.KUSHI_SAKANA && hItem != Items_Teatime.MATCH && hItem != Items.FLINT_AND_STEEL) {
				
				if (hStack.isEmpty()) {
					if (i == 10) {
						CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items.BONE_MEAL);
						worldIn.setBlockState(pos, state.with(STAGE_0_10, Integer.valueOf(0))); }
					
					else { //i != 10
						CMEvents.textNotHave(worldIn, pos, playerIn); } }
				
				else { //!empty
					CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
		}
		return ActionResultType.SUCCESS;
	}

	private void Charcoal2BoneMeal(World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		boolean mode = playerIn.abilities.isCreativeMode;

		if (mode) { }
		else { //!mode
			hStack.shrink(1);
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BONE_MEAL)); }
		CMEvents.soundSnowPlace(worldIn, pos); }

	
	/* Gives a value when placed. */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		return this.getDefaultState().with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
	}

	/* Connect the blocks. */
	protected boolean connectNabe(IBlockReader worldIn, BlockPos pos, Direction face) {
		BlockPos newPos = pos.offset(face);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();

		return (block instanceof BaseNabe || block instanceof BaseNabe_2Cook || block instanceof BaseNabe_nama ||
				block instanceof Nabe_kara || block instanceof Kettle_full);
	}

	/* Distinguish LOST from WATERLOGGED. */
	private boolean waterIn(BlockState state) {
		return state.get(WATERLOGGED);
	}
	
	private boolean waterOUT(BlockState state) {
		return !state.get(WATERLOGGED);
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, Kitchen_Blocks.IRORI, FIRE_TIME); }

		boolean donabe = connectNabe(worldIn, pos, Direction.UP);
		return state.with(DONABE, donabe);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, Kitchen_Blocks.IRORI, FIRE_TIME); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_0_10);

		if (waterIn(state)) {
			/** Nothing on the top. **/
			if (i == 0) { }

			else { //i != 0
				worldIn.getPendingBlockTicks().scheduleTick(pos, Kitchen_Blocks.IRORI, FIRE_TIME);
				worldIn.setBlockState(pos, state.with(STAGE_0_10, Integer.valueOf(0)).with(LIT, Boolean.valueOf(false)));
				
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
		int i = state.get(STAGE_0_10);
		
		if (waterOUT(state)) {
			/** Charcoal is burning. **/
			if (i >= 2 && i <= 9) {
				if (rand.nextInt(1) == 0) {
					if (i <= 6) { worldIn.setBlockState(pos, state.with(STAGE_0_10, Integer.valueOf(i + 1)).with(LIT, Boolean.valueOf(true))); }
	
					if (i >= 7) { worldIn.setBlockState(pos, state.with(STAGE_0_10, Integer.valueOf(i + 1)).with(LIT, Boolean.valueOf(false))); }
				}
			}
		}
	}

	private void dropBonemeal(ServerWorld worldIn, BlockPos pos) {
		ItemStack stack = new ItemStack(Items.BONE_MEAL);
		InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	}

	private void dropCharcoal(ServerWorld worldIn, BlockPos pos) {
		ItemStack stack = new ItemStack(Items.CHARCOAL);
		InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
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
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entity) {
		int px = pos.getX();
		int py = pos.getY();
		int pz = pos.getZ();

		BlockState state = worldIn.getBlockState(new BlockPos(px, py, pz));
		int i = state.get(STAGE_0_10);

		if (i > 1 && i < 10) {
			if (!entity.isImmuneToFire() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
				entity.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
			}
			super.onEntityWalk(worldIn, pos, entity);
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

		int i = state.get(STAGE_0_10);
		if (i != 0 && i != 1 && i != 10) {
			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false); }

			if (rand.nextDouble() < 0.25D) {
				/** which. position x y z, speed x y z **/
				worldIn.addParticle(ParticleTypes.SMOKE, d0 + d4, d1 + d6, d2 + d4, 0.0D, 0.0D, 0.0D); }
		}
	}

	/* Can't breathe. */
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Block is a cube. */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	/* Mobs spawn. */
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
		return false;
	}
	
	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_irori").applyTextStyle(TextFormatting.GRAY));
	}
}
