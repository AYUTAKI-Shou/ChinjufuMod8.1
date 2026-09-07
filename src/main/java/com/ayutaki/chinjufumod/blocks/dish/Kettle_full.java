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

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Kettle_full extends BaseFood_Stage3Water {

	protected static final int COOK_TIME = 1000;
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 7.0D, 12.0D);
	private static final VoxelShape AABB_3_BOX = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
	private static final VoxelShape AABB_3_DOWN = Block.box(5.0D, -8.0D, 5.0D, 11.0D, 8.0D, 11.0D);
	
	public Kettle_full(BlockBehaviour.Properties props) {
		super(props);
	}
	
	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		int i = state.getValue(STAGE_1_3);
		if (i == 1 && cookingIn(worldIn, pos) && !state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }

		return super.updateShape(state, facing, newState, worldIn, pos, newPos);
	}
	
	/* Conditions for TickRandom. */
	protected boolean cookingIn(LevelAccessor worldIn, BlockPos pos) {
		BlockState downState = worldIn.getBlockState(pos.below());
		Block downBlock = downState.getBlock();
		return (downBlock instanceof FurnaceBlock && downState.getValue(FurnaceBlock.LIT) == true) ||
				(downBlock instanceof AbstractOvenBlock && downState.getValue(AbstractOvenBlock.LIT) == true) ||
				(downBlock instanceof Kit_Cooktop && downState.getValue(Kit_Cooktop.STAGE_1_3) == 2) ||
				(downBlock instanceof Irori && downState.getValue(Irori.LIT) == true) ||
				(downBlock instanceof CStove_Top && downState.getValue(CStove_Top.LIT) == true);
	}
	
	/* TickRandom */
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
		int i = state.getValue(STAGE_1_3);
		if (i == 1 && cookingIn(worldIn, pos) && !state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, this, COOK_TIME + (20 * worldIn.getRandom().nextInt(5))); }
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }

		int i = state.getValue(STAGE_1_3);
		if (i == 1 && cookingIn(worldIn, pos) && !state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, this, COOK_TIME + (20 * rand.nextInt(5)));
			worldIn.setBlock(pos, state.setValue(STAGE_1_3, Integer.valueOf(2)), 3);
			CMEvents.addEXP(1, worldIn, pos); }

		else { }
	}
	
	/* Play Sound & Particle */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level worldIn, BlockPos pos, Random rand) {
		double d0 = (double)pos.getX() + 0.5D;
		double d1 = (double)pos.getY() + 0.8D;
		double d2 = (double)pos.getZ() + 0.5D;
		double d4 = rand.nextDouble() * 0.6D - 0.3D;
		double d6 = rand.nextDouble() * 6.0D / 16.0D;

		if (cookingIn(worldIn, pos)) {
			int i = state.getValue(STAGE_1_3);

			if (rand.nextDouble() < 0.1D) {
				if (i ==1) {
					worldIn.playLocalSound(d0, d1, d2, SoundEvents_CM.GUTSUGUTSU.get(), SoundSource.BLOCKS, 0.5F, 0.7F, false); }

				if (i ==2) {
					worldIn.playLocalSound(d0, d1, d2, SoundEvents_CM.GUTSUGUTSU.get(), SoundSource.BLOCKS, 0.3F, 0.7F, false); }
			}

			if (i ==2 && rand.nextDouble() < 0.25D) {
				/** which, position x y z, speed x y z **/
				worldIn.addParticle(ParticleTypes.POOF, d0 + d4, d1 + d6, d2 + d4, 0.0D, 0.0D, 0.0D);
			}
		}
	}
	
	/* Play Sound */
	@Override
	public SoundType getSoundType(BlockState state) {
		int i = state.getValue(STAGE_1_3);
		if (i == 3) { return SoundType.STONE; }
		 return this.soundType;
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean notDown = !((Boolean)state.getValue(DOWN)).booleanValue();
		int i = state.getValue(STAGE_1_3);

		if (i == 3) { return notDown? AABB_3_BOX : AABB_3_DOWN; }
		return AABB_BOX;
	}

	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(BlockGetter worldIn, BlockPos pos, BlockState state) {
		int i = state.getValue(STAGE_1_3);
		return (i != 3)? new ItemStack(Items_Teatime.KETTLE_full.get()) : new ItemStack(Items_Teatime.SAKEBOTTLE.get());
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(new TranslatableComponent("tips.block_kettle").withStyle(ChatFormatting.GRAY));
	}
}
