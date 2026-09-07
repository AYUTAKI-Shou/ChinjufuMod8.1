package com.ayutaki.chinjufumod.blocks.crop;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.mojang.serialization.MapCodec;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PepperVanilla extends Abstract_WaterLogged {
	public static final MapCodec<PepperVanilla> CODEC = simpleCodec(PepperVanilla::new);
	@Override
	public MapCodec<? extends PepperVanilla> codec() { return CODEC; }
	
	/* Property 0 1 2 3 4, 5 6 7 */
	public static final IntegerProperty STAGE_0_7 = IntegerProperty.create("stage", 0, 7);
	public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
	
	/* Collision */
	private static final VoxelShape PBOT_0 = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 6.0D, 12.0D);
	private static final VoxelShape PBOT_1 = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 10.5D, 12.0D);
	private static final VoxelShape PBOT_2 = Block.box(3.5D, 0.0D, 3.5D, 12.5D, 15.0D, 12.5D);
	private static final VoxelShape PBOT_3 = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);

	private static final VoxelShape VBOT_0 = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D);
	private static final VoxelShape VBOT_1 = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);
	private static final VoxelShape VBOT_2 = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 14.0D, 13.0D);
	private static final VoxelShape VBOT_3 = Block.box(2.5D, 0.0D, 2.5D, 13.5D, 16.0D, 13.5D);

	private static final VoxelShape BOT_7 = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
	private static final VoxelShape TOP_0 = Block.box(7.5D, 0.0D, 7.5D, 8.5D, 10.0D, 8.5D);
	private static final VoxelShape TOP_3 = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 4.0D, 12.0D);
	private static final VoxelShape TOP_7 = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
	
	public PepperVanilla(BlockBehaviour.Properties props) {
		super(props);
		registerDefaultState(this.stateDefinition.any().setValue(STAGE_0_7, Integer.valueOf(0))
				.setValue(HALF, DoubleBlockHalf.LOWER)
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}
	
	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int i = state.getValue(STAGE_0_7);
		/** Property 0 1 2 3 4, 5 6 7 **/
		
		BlockState lowFace = this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER);
		BlockState upFace = this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER);
		
		if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
			
			/** Too early to collect **/
			if (i <= 6) {
				if ((i <= 5) && hItem == Items.BONE_MEAL) {
					CMEvents.BoneMeal_Particle(worldIn, pos, playerIn, hand);
					worldIn.setBlock(pos, lowFace.setValue(STAGE_0_7, Integer.valueOf(i + 2)), 3);
					worldIn.setBlock(pos.above(), upFace.setValue(STAGE_0_7, Integer.valueOf(i + 2)), 3);
				}
				
				if (hItem != Items.BONE_MEAL) {
					if (hStack.isEmpty()) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }	

					else { //!empty
						CMEvents.textNotHave(worldIn, pos, playerIn); }
				}
			}
			
			/** Can harvest **/
			if (i == 7) {
				if (hStack.isEmpty()) {
					CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, this.takeItem(), 4);
					worldIn.setBlock(pos, lowFace.setValue(STAGE_0_7, Integer.valueOf(4)), 3);
					worldIn.setBlock(pos.above(), upFace.setValue(STAGE_0_7, Integer.valueOf(4)), 3); }
				
				else { //!empty
					CMEvents.textFullItem(worldIn, pos, playerIn); }
			}
		}
		/** SUCCESS to not put anything on top. **/
		return InteractionResult.SUCCESS;
	}

	private Item takeItem() {
		if (this == Crop_Blocks.VANILLA.get()) { return Items_Teatime.VANILLA_RAW.get(); }
		else { return Items_Teatime.PEPPER_RAW.get(); }
	}
	
	/* Limit the place. */
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return (state.is(BlockTags.DIRT) && state.isSolidRender() && !state.is(BlockTags.ANIMALS_SPAWNABLE_ON));
	}
	
	@Override
	public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
		BlockPos downPos = pos.below();
		return this.mayPlaceOn(worldIn.getBlockState(downPos), worldIn, downPos);
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		float temp = worldIn.getBiome(pos).value().getBaseTemperature();
		Player playerIn = context.getPlayer();
		
		if (temp >= 0.5F) {
			/** pos.up() = Replaceable block. for 1.21.4 **/
			if (pos.getY() < worldIn.getMaxY() && worldIn.getBlockState(pos.above()).canBeReplaced(context)) {
				return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER)); }
	
			else { return null; }
		}
	
		else {
			playerIn.displayClientMessage(Component.translatable("text.chinjufumod.too_cold"), true);
			return null; }
	}
	
	/* Add DoubleBlockHalf.UPPER on the Block. */
	public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity entityIn, ItemStack stack) {
		FluidState fluidUp = worldIn.getFluidState(pos.above());

		worldIn.setBlock(pos.above(), this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)
				.setValue(WATERLOGGED, Boolean.valueOf(fluidUp.getType() == Fluids.WATER)), 3);
	}
	
	public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		return false;
	}
	
	/* Update BlockState. for 1.21.4 */
	@Override
	protected BlockState updateShape(BlockState state, LevelReader worldIn, ScheduledTickAccess tick, BlockPos pos, Direction facing, BlockPos newPos, BlockState newState, RandomSource rand) {
		BlockState state1 = super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand);
		if (!state1.isAir()) {
			tick.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }

		DoubleBlockHalf half = state.getValue(HALF);
		if (facing.getAxis() != Direction.Axis.Y || half == DoubleBlockHalf.LOWER != (facing == Direction.UP) ||
				 newState.getBlock() == this && newState.getValue(HALF) != half) {
			return (half == DoubleBlockHalf.LOWER && facing == Direction.DOWN && !state.canSurvive(worldIn, pos)) ?
					 Blocks.AIR.defaultBlockState() : super.updateShape(state, worldIn, tick, pos, facing, newPos, newState, rand); }

		else { return Blocks.AIR.defaultBlockState(); }
	}

	/* TickRandom */
	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
		BlockState upState = worldIn.getBlockState(pos.above());
		DoubleBlockHalf half = state.getValue(HALF);
		int i = state.getValue(STAGE_0_7);
		/** Property 0 1 2 3 4, 5 6 7 **/
		
		BlockState lowFace = this.defaultBlockState().setValue(HALF, DoubleBlockHalf.LOWER);
		BlockState upFace = this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER);
		
		if (!worldIn.isAreaLoaded(pos, 2)) { return; }

		switch (half) {
		case LOWER:
		default:
			float temp = worldIn.getBiome(pos).value().getBaseTemperature();

			if (temp >= 0.5F) {
				if (state.getValue(WATERLOGGED) || upState.getValue(WATERLOGGED)) {
					if (i > 4) {
						if (rand.nextInt(2) == 0) {
							CMEvents.drop1_ROTTENFOOD(worldIn, pos);
							worldIn.setBlock(pos, lowFace.setValue(STAGE_0_7, Integer.valueOf(4)), 3);
							worldIn.setBlock(pos.above(), upFace.setValue(STAGE_0_7, Integer.valueOf(4)), 3); } }
					
					else { } }
				
				else { //!WATERLOGGED
					if (i < 7 && worldIn.getRawBrightness(pos, 0) >= 9) {
						if (rand.nextInt(8) == 0) {
							worldIn.setBlock(pos, lowFace.setValue(STAGE_0_7, Integer.valueOf(i + 1)), 3);
							worldIn.setBlock(pos.above(), upFace.setValue(STAGE_0_7, Integer.valueOf(i + 1)), 3); } }
			
					else { } }
			}
			
			if (temp < 0.5F && rand.nextInt(1) == 0) {
				worldIn.destroyBlock(pos, true);
				worldIn.destroyBlock(pos.above(), false); }
			break;

		case UPPER: break;
		} // switch
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		int i = state.getValue(STAGE_0_7);
		boolean half = (state.getValue(HALF) == DoubleBlockHalf.UPPER);
		boolean PEPPER = (this == Crop_Blocks.PEPPER.get());
		
		switch (i) {
		case 0 :
		default : return (half)? TOP_0 : ((PEPPER)? PBOT_0 : VBOT_0);
		case 1 : return (half)? TOP_0 : ((PEPPER)? PBOT_1 : VBOT_1);
		case 2 : return (half)? TOP_0 : ((PEPPER)? PBOT_2 : VBOT_2);
		case 3 : return (half)? TOP_3 : ((PEPPER)? PBOT_3 : VBOT_3);
		case 4 :
		case 5 :
		case 6 :
		case 7 : return (half)? TOP_7 : BOT_7;
		} // // STAGE0_7
	}

	/* Destroy a DoubleBlock from DoublePlantBlock.class */
	@Override
	public BlockState playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player playerIn) {
		if (!worldIn.isClientSide) {
			if (playerIn.isCreative()) { breakLowerPart(worldIn, pos, state, playerIn); } 
			
			else { dropResources(state, worldIn, pos, (BlockEntity)null, playerIn, playerIn.getMainHandItem()); }
		}
		return super.playerWillDestroy(worldIn, pos, state, playerIn);
	}

	@Override
	public void playerDestroy(Level worldIn, Player playerIn, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
		super.playerDestroy(worldIn, playerIn, pos, Blocks.AIR.defaultBlockState(), blockEntity, stack);
	}

	protected static void breakLowerPart(Level worldIn, BlockPos pos, BlockState state, Player playerIn) {
		DoubleBlockHalf half = state.getValue(HALF);
		if (half == DoubleBlockHalf.UPPER) {
			BlockPos downPos = pos.below();
			BlockState downState = worldIn.getBlockState(downPos);
			if (downState.is(state.getBlock()) && downState.getValue(HALF) == DoubleBlockHalf.LOWER) {
				BlockState downState1 = downState.hasProperty(BlockStateProperties.WATERLOGGED) && downState.getValue(BlockStateProperties.WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
				worldIn.setBlock(downPos, downState1, 35);
				worldIn.levelEvent(playerIn, 2001, downPos, Block.getId(downState)); }
		}
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_7, HALF, WATERLOGGED);
	}
	
	/* Clone Item in Creative. for 1.21.4 */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state, boolean flag) {
		return new ItemStack(this.cloneItem());
	}

	private Item cloneItem() {
		if (this == Crop_Blocks.VANILLA.get()) { return Items_Teatime.SEEDS_VANILLA.get(); }
		else { return Items_Teatime.SEEDS_PEPPER.get(); }
	}

	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
	}
	
	/* ToolTip */
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> blockTip, TooltipFlag tipFlag) {
		blockTip.add(Component.translatable("tips.item_seeds_pepper").withStyle(ChatFormatting.GRAY));
	}
}
