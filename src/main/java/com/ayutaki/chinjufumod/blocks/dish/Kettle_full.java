package com.ayutaki.chinjufumod.blocks.dish;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.furnace.AbstractOvenBlock;
import com.ayutaki.chinjufumod.blocks.furnace.CStove_Top;
import com.ayutaki.chinjufumod.blocks.furnace.Irori;
import com.ayutaki.chinjufumod.blocks.kitchen.Kit_Cooktop;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.SoundType;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
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

public class Kettle_full extends BaseFood_Stage3WP {

	protected static final int COOK_TIME = 1000;
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 7.0D, 12.0D);
	private static final VoxelShape AABB_3_BOX = Block.makeCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	private static final VoxelShape AABB_3_DOWN = Block.makeCuboidShape(5.0D, -8.0D, 5.0D, 11.0D, 8.0D, 11.0D);

	public Kettle_full(Block.Properties props) {
		super(props);
	}

	/* TickRandom */
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState newState, IWorld worldIn, BlockPos pos, BlockPos newPos) {
		int i = state.get(STAGE_1_3);
		if (i == 1 && cookingIn(worldIn, pos) && !state.get(WATERLOGGED)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
		
		return super.updatePostPlacement(state, facing, newState, worldIn, pos, newPos);
	}

	private boolean cookingIn(IBlockReader worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.down());
		Block downBlock = downState.getBlock();
		return (downBlock instanceof FurnaceBlock && downState.get(FurnaceBlock.LIT) == true) ||
				(downBlock instanceof AbstractOvenBlock && downState.get(AbstractOvenBlock.LIT) == true) ||
				(downBlock instanceof Kit_Cooktop && downState.get(Kit_Cooktop.STAGE_1_3) == 2) ||
				(downBlock instanceof Irori && downState.get(Irori.LIT) == true) ||
				(downBlock instanceof CStove_Top && downState.get(CStove_Top.LIT) == true);
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		int i = state.get(STAGE_1_3);
		if (i == 1 && cookingIn(worldIn, pos) && !state.get(WATERLOGGED)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }

		int i = state.get(STAGE_1_3);
		if (i == 1 && cookingIn(worldIn, pos) && !state.get(WATERLOGGED)) {
			worldIn.getPendingBlockTicks().scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
			worldIn.setBlockState(pos, state.with(STAGE_1_3, Integer.valueOf(2)));
			CMEvents.addEXP(1, worldIn, pos); }

		else { }
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
			int i = state.get(STAGE_1_3);

			if (rand.nextDouble() < 0.1D) {
				if (i ==1) {
					worldIn.playSound(d0, d1, d2, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.5F, 0.7F, false); }

				if (i ==2) {
					worldIn.playSound(d0, d1, d2, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.3F, 0.7F, false); }
			}

			if (i ==2 && rand.nextDouble() < 0.25D) {
				/** which. position x y z, speed x y z **/
				worldIn.addParticle(ParticleTypes.POOF, d0 + d4, d1 + d6, d2 + d4, 0.0D, 0.0D, 0.0D);
			}
		}
	}

		/* Play Sound */
	@Override
	public SoundType getSoundType(BlockState state) {
		int i = state.get(STAGE_1_3);
		if (i == 3) { return SoundType.STONE; }
		 return this.soundType;
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		boolean notDown = !((Boolean)state.get(DOWN)).booleanValue();
		int i = state.get(STAGE_1_3);

		if (i == 3) { return notDown? AABB_3_BOX : AABB_3_DOWN; }
		return AABB_BOX;
	}
	
	/* Clone Item in Creative. */
	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		int i = state.get(STAGE_1_3);
		return (i != 3)? new ItemStack(Items_Teatime.KETTLE_full) : new ItemStack(Items_Teatime.SAKEBOTTLE);
	}

	/* ToolTip */
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> blockTip, ITooltipFlag tipFlag) {
		blockTip.add(new TranslationTextComponent("tips.block_kettle").applyTextStyle(TextFormatting.GRAY));
	}
}
