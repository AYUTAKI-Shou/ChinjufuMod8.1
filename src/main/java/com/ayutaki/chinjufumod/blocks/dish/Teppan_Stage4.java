package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.furnace.AbstractOvenBlock;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Teppan_Stage4 extends Abstract_Teppan {

	protected static final int COOK_TIME = 700;
	/* Property */
	public static final IntegerProperty STAGE_1_4 = IntegerProperty.create("stage", 1, 4);
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.makeCuboidShape(2.5D, 0.0D, 2.5D, 13.5D, 1.0D, 13.5D);
	
	public Teppan_Stage4(Block.Properties props) {
		super(props);
		/** Default state **/
		setDefaultState(this.stateContainer.getBaseState().with(H_FACING, Direction.NORTH)
				.with(STAGE_1_4, Integer.valueOf(1))
				.with(DOWN, Boolean.valueOf(false))
				.with(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = state.get(STAGE_1_4);
		/** 1=raw, 2=re, 3=soba, 4=sauce **/
		boolean yakiSoba = (this == Dish_Blocks.YAKISOBA_nama) || (this == Dish_Blocks.YAKISOBASHIO_nama);
	
		if (yakiSoba && i == 4) {
			if (hItem == Items_Teatime.SARA) {
				/** Collect with an Item **/
				CMEvents.changeDish_seSnowB(worldIn, pos, playerIn, hand, this.takeItem());
				CMEvents.addEXP(1, worldIn, pos);
				
				worldIn.setBlockState(pos, Dish_Blocks.OKONOMIC_nama.getDefaultState()
						.with(Teppan_4empty.H_FACING, state.get(H_FACING))
						.with(Teppan_4empty.DOWN, state.get(DOWN))
						.with(Teppan_4empty.WATERLOGGED, state.get(WATERLOGGED))
						.with(Teppan_4empty.STAGE_1_4, Integer.valueOf(4)), 3); }
				
				if (hItem != Items_Teatime.SARA) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		else { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** SUCCESS to not put anything on top. **/
		return ActionResultType.SUCCESS;
	}

	private Item takeItem() {
		if (this == Dish_Blocks.YAKISOBA_nama) { return Items_Teatime.YAKISOBA; }
		else { return Items_Teatime.YAKISOBASHIO; }
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Nullable
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		World worldIn = context.getWorld();
		BlockPos pos = context.getPos();
		IFluidState fluid = worldIn.getFluidState(pos);
		
		BlockState downState = worldIn.getBlockState(pos.down());
		Block downBlock = downState.getBlock();
		boolean teppan = (downBlock instanceof AbstractOvenBlock || downBlock instanceof Kit_Cooktop);

		return this.getDefaultState().with(H_FACING, context.getPlacementHorizontalFacing().getOpposite())
				.with(STAGE_1_4, Integer.valueOf(1))
				.with(DOWN, Boolean.valueOf(teppan))
				.with(WATERLOGGED, fluid.getFluid() == Fluids.WATER);
	}

	/* Update BlockState. */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn)); }

		boolean yakiSoba = (this == Dish_Blocks.YAKISOBA_nama) || (this == Dish_Blocks.YAKISOBASHIO_nama);
		int i = state.get(STAGE_1_4);
		if(waterOUT(state)) {
			if (yakiSoba && i == 4) { }
			
			else {
				if (cookingIn(worldIn, pos)) {
					worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(3))); }
				else { } }
		}

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }

		boolean down = connectTeppan(worldIn, pos, Direction.DOWN);
		return state.with(DOWN, down);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		boolean yakiSoba = (this == Dish_Blocks.YAKISOBA_nama) || (this == Dish_Blocks.YAKISOBASHIO_nama);
		int i = state.get(STAGE_1_4);
		if(waterOUT(state)) {
			if (yakiSoba && i == 4) { }
			
			else {
				if (cookingIn(worldIn, pos)) {
					worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(3))); }
				else { } }
		}

		if (waterIn(state)) { worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		int i = state.get(STAGE_1_4);
		boolean yakiSoba = (this == Dish_Blocks.YAKISOBA_nama) || (this == Dish_Blocks.YAKISOBASHIO_nama);
		
		if (waterOUT(state)) {
			if (yakiSoba && i == 4) { }
			
			else {
				if (cookingIn(worldIn, pos)) {
					worldIn.setBlockState(pos, this.toClick().getDefaultState()
							.with(Teppan_Stage4C.H_FACING, state.get(H_FACING))
							.with(Teppan_Stage4C.DOWN, state.get(DOWN))
							.with(Teppan_Stage4C.WATERLOGGED, state.get(WATERLOGGED))
							.with(Teppan_Stage4C.STAGE_1_4, Integer.valueOf(i)), 3);
					CMEvents.soundKotePlace(worldIn, pos);
					worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(3))); }
				
				else { } }
		}
		
		if (waterIn(state)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, 60);
			
			CMEvents.drop1_ROTTENFOOD(worldIn, pos);
			worldIn.setBlockState(pos, Dish_Blocks.OKONOMIC_nama.getDefaultState()
					.with(Teppan_4empty.H_FACING, state.get(H_FACING))
					.with(Teppan_4empty.DOWN, state.get(DOWN))
					.with(Teppan_4empty.WATERLOGGED, state.get(WATERLOGGED))
					.with(Teppan_4empty.STAGE_1_4, Integer.valueOf(4)), 3); }
	}

	private Block toClick() {
		if (this == Dish_Blocks.OKONOMISOBA_nama) { return Dish_Blocks.OKONOMISOBA_click; }
		if (this == Dish_Blocks.OKONOMISOBAS_nama) { return Dish_Blocks.OKONOMISOBAS_click; }
		if (this == Dish_Blocks.OKONOMISOBAC_nama) { return Dish_Blocks.OKONOMISOBAC_click; }
		if (this == Dish_Blocks.YAKISOBA_nama) { return Dish_Blocks.YAKISOBA_click; }
		else { return Dish_Blocks.YAKISOBASHIO_click; }
	}

	/* Create Blockstate */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOWN, H_FACING, STAGE_1_4, WATERLOGGED);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return AABB_BOX;
	}
	
	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;

		if (cookingIn(worldIn, pos)) {
			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound(d0, d1, d2, SoundEvents_CM.JUU, SoundCategory.BLOCKS, 0.2F, 1.0F, false); }

			int i = state.get(STAGE_1_4);
			if (i !=1 && rand.nextDouble() < 0.25D) {
				worldIn.addParticle(ParticleTypes.POOF, d0 + d4, d1 + d6 - 0.3D, d2 + d4, 0.0D, 0.0D, 0.0D); }
		}
		
		else { }
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		super.addInformation(stack, worldIn, blockTip, tipFlag);
		blockTip.add(new TranslationTextComponent("tips.take_plate").applyTextStyle(TextFormatting.GRAY));
	}
}
