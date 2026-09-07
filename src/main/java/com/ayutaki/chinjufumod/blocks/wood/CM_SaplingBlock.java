package com.ayutaki.chinjufumod.blocks.wood;

import com.ayutaki.chinjufumod.registry.Wood_Blocks;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CM_SaplingBlock extends BushBlock implements BonemealableBlock {
	public static final MapCodec<CM_SaplingBlock> CODEC = RecordCodecBuilder.mapCodec(
			instance -> instance.group(TreeGrower_CM.CODEC.fieldOf("tree").forGetter(function -> function.treeGrower), propertiesCodec())
					.apply(instance, CM_SaplingBlock::new));
	@Override
	public MapCodec<? extends CM_SaplingBlock> codec() { return CODEC; }
	
	public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
	protected static final float AABB_OFFSET = 6.0F;
	private static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	private final TreeGrower_CM treeGrower;
	
	public CM_SaplingBlock(TreeGrower_CM treeIn, BlockBehaviour.Properties props) {
		super(props);
		treeGrower = treeIn;
	}
	
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		Block block = state.getBlock();
		return state.is(BlockTags.DIRT) || state.is(Blocks.FARMLAND) || block == Wood_Blocks.FALL_LEAF.get();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; } // Forge: prevent loading unloaded chunks when checking neighbor's light
		if (worldIn.getMaxLocalRawBrightness(pos.above()) >= 9 && rand.nextInt(7) == 0) {
			this.advanceTree(worldIn, pos, state, rand);
		}
	}

	public void advanceTree(ServerLevel worldIn, BlockPos pos, BlockState state, RandomSource rand) {
		if (state.getValue(STAGE) == 0) {
			worldIn.setBlock(pos, state.cycle(STAGE), 4);
		} else {
			this.treeGrower.growTree(worldIn, worldIn.getChunkSource().getGenerator(), pos, state, rand);
		}
	}

	@Override
	public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		return (double)worldIn.random.nextFloat() < 0.45;
	}

	@Override
	public void performBonemeal(ServerLevel worldIn, RandomSource rand, BlockPos pos, BlockState state) {
		this.advanceTree(worldIn, pos, state, rand);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAGE);
	}
}
