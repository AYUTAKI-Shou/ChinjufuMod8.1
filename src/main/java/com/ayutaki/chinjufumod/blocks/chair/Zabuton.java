package com.ayutaki.chinjufumod.blocks.chair;

import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlab_Water;
import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.blocks.jpblock.Base_Slab_JP;
import com.ayutaki.chinjufumod.blocks.jpdeco.BaseTatami;
import com.ayutaki.chinjufumod.blocks.wood.WoodSlab_CM;
import com.ayutaki.chinjufumod.entity.SitableEntity;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.ayutaki.chinjufumod.registry.JPChair_Blocks;
import com.ayutaki.chinjufumod.state.TatamiType;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Zabuton extends Abstract_WaterLogged {
	public static final MapCodec<Zabuton> CODEC = simpleCodec(Zabuton::new);
	@Override
	public MapCodec<? extends Zabuton> codec() { return CODEC; }
	
	public static final BooleanProperty DOWN = BooleanProperty.create("down");
	/* Collision */
	private static final VoxelShape AABB_BOX = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 3.0D, 15.0D);
	private static final VoxelShape DOWN_BOX = Block.box(1.0D, -8.0D, 1.0D, 15.0D, 0.01D, 15.0D);
	private static final VoxelShape DOWN_COLL = Block.box(1.0D, -8.0D, 1.0D, 15.0D, -5.0D, 15.0D);
	
	private static final VoxelShape AABB_WARA = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);
	private static final VoxelShape DOWN_WARA = Block.box(0.0D, -8.0D, 0.0D, 16.0D, 0.01D, 16.0D);

	public Zabuton(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(DOWN, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		boolean down = state.getValue(DOWN);

		if (hItem instanceof Base_Hake && this != JPChair_Blocks.WARAZABUTON.get()) {
			return InteractionResult.PASS; }

		else {
			if (this == JPChair_Blocks.WARAZABUTON.get()) {
				worldIn.playSound(null, pos, SoundEvents.GRASS_STEP, SoundSource.BLOCKS, 0.3F, 0.5F); }

			CMEvents.soundKinuzure(worldIn, pos);
			return down? SitableEntity.create(worldIn, pos, -0.5, playerIn) : SitableEntity.create(worldIn, pos, 0.0, playerIn);
		}
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		return this.defaultBlockState().setValue(DOWN, this.connectHalf(worldIn, pos, Direction.DOWN))
				.setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}

	/* Connect the blocks. */
	protected boolean connectHalf(BlockGetter worldIn, BlockPos pos, Direction direct) {
		BlockPos newPos = pos.relative(direct);
		BlockState state = worldIn.getBlockState(newPos);
		Block block = state.getBlock();

		return ((block instanceof SlabBlock && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM)||
				(block instanceof WoodSlab_CM && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof BaseFacingSlab_Water && state.getValue(SlabBlock.TYPE) == SlabType.BOTTOM) ||
				(block instanceof Base_Slab_JP && state.getValue(Base_Slab_JP.TYPE) == SlabType.BOTTOM) ||
				(block instanceof BaseTatami && state.getValue(BaseTatami.TYPE) == TatamiType.BOTTOM));
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		if (state.getValue(WATERLOGGED)) { 
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		return super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DOWN, WATERLOGGED);
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean down = state.getValue(DOWN);
		if (this == JPChair_Blocks.WARAZABUTON.get()) { return down? DOWN_WARA : AABB_WARA; }
		return down? DOWN_BOX : AABB_BOX;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean down = state.getValue(DOWN);
		return down? DOWN_COLL : AABB_BOX;
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.block_mzabuton").withStyle(ChatFormatting.GRAY));
	}
}
