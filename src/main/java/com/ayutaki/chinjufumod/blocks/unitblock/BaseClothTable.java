package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.blocks.base.Abstract_WaterLogged;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook1;
import com.ayutaki.chinjufumod.blocks.furniture.NoteBook;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BaseClothTable extends Abstract_WaterLogged {
	public static final MapCodec<BaseClothTable> CODEC = simpleCodec(BaseClothTable::new);
	@Override
	public MapCodec<? extends BaseClothTable> codec() { return CODEC; }
	
	/* Property */
	public static final IntegerProperty STAGE_0_15 = IntegerProperty.create("color", 0, 15);
	public static final BooleanProperty NORTH = BooleanProperty.create("north");
	public static final BooleanProperty EAST = BooleanProperty.create("east");
	public static final BooleanProperty SOUTH = BooleanProperty.create("south");
	public static final BooleanProperty WEST = BooleanProperty.create("west");
	
	/* Collision */
	private static final VoxelShape TTTT = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);

	private static final VoxelShape FFFF = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(7.0D, 1.0D, 7.0D, 9.0D, 14.0D, 9.0D),
			Block.box(7.0D, 0.0D, 1.0D, 9.0D, 1.0D, 15.0D),
			Block.box(1.0D, 0.0D, 7.0D, 7.0D, 1.0D, 9.0D),
			Block.box(9.0D, 0.0D, 7.0D, 15.0D, 1.0D, 9.0D));

	private static final VoxelShape TTFF = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(2.5D, 0.0D, 11.0D, 5.0D, 14.0D, 13.5D));
	private static final VoxelShape FTFT = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(11.0D, 0.0D, 11.0D, 13.5D, 14.0D, 13.5D));
	private static final VoxelShape TFTF = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(2.5D, 0.0D, 2.5D, 5.0D, 14.0D, 5.0D));
	private static final VoxelShape FFTT = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(11.0D, 0.0D, 2.5D, 13.5D, 14.0D, 5.0D));

	private static final VoxelShape FTFF = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 13.0D, 3.0D, 14.0D, 15.0D),
			Block.box(13.0D, 0.0D, 13.0D, 15.0D, 14.0D, 15.0D));
	private static final VoxelShape FFTF = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 1.0D, 3.0D, 14.0D, 3.0D),
			Block.box(13.0D, 0.0D, 1.0D, 15.0D, 14.0D, 3.0D));
	private static final VoxelShape TFFF = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(1.0D, 0.0D, 1.0D, 3.0D, 14.0D, 3.0D),
			Block.box(1.0D, 0.0D, 13.0D, 3.0D, 14.0D, 15.0D));
	private static final VoxelShape FFFT = Shapes.or(Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D),
			Block.box(13.0D, 0.0D, 1.0D, 15.0D, 14.0D, 3.0D),
			Block.box(13.0D, 0.0D, 13.0D, 15.0D, 14.0D, 15.0D));
	
	public BaseClothTable(BlockBehaviour.Properties props) {
		super(props);
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(STAGE_0_15, Integer.valueOf(0))
				.setValue(NORTH, Boolean.valueOf(false))
				.setValue(EAST, Boolean.valueOf(false))
				.setValue(SOUTH, Boolean.valueOf(false))
				.setValue(WEST, Boolean.valueOf(false))
				.setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	/* RightClick Action */
	@Override
	public ItemInteractionResult useItemOn(ItemStack stackIn, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		
		Direction facing = playerIn.getDirection().getOpposite();
		BlockState upState = worldIn.getBlockState(pos.above());
		FluidState upFluid = worldIn.getFluidState(pos.above());
		
		Item hItem = hStack.getItem();
		boolean upAble = (upState.canBeReplaced() && upFluid.getType() == Fluids.EMPTY);
		boolean success = (hItem == Items_Chinjufu.SHOUHOU_empty.get() || hItem == Items.BOOK);
		
		if (success) {
			if (hItem == Items_Chinjufu.SHOUHOU_empty.get() && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos.above(), Furniture_Blocks.NOTEBOOK.get().defaultBlockState()
						.setValue(NoteBook.H_FACING, facing)
						.setValue(NoteBook.HAS_BOOK, Boolean.valueOf(false))
						.setValue(NoteBook.DOWN, Boolean.valueOf(false))
						.setValue(NoteBook.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }
			
			
			if (hItem == Items.BOOK && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				worldIn.setBlock(pos.above(), Furniture_Blocks.DESKBOOK_1.get().defaultBlockState()
						.setValue(DeskBook1.H_FACING, facing)
						.setValue(DeskBook1.STAGE_1_4, Integer.valueOf(1))
						.setValue(DeskBook1.DOWN, Boolean.valueOf(false))
						.setValue(DeskBook1.WATERLOGGED, Boolean.valueOf(upFluid.getType() == Fluids.WATER)), 3); }
			
			return ItemInteractionResult.SUCCESS; }
		
		else { return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION; }
	}
	
	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level worldIn = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluid = worldIn.getFluidState(pos);

		return this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(fluid.getType() == Fluids.WATER));
	}
	
	/* Connect the blocks. */
	private boolean canConnectTo(BlockGetter worldIn, BlockPos pos, Direction direction) {
		BlockState state = worldIn.getBlockState(pos.relative(direction));
		return state.getBlock() == this;
	}

	/* Update BlockState. */
	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState newState, LevelAccessor worldIn, BlockPos pos, BlockPos newPos) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn)); }
		
		boolean north = canConnectTo(worldIn, pos, Direction.NORTH);
		boolean east = canConnectTo(worldIn, pos, Direction.EAST);
		boolean south = canConnectTo(worldIn, pos, Direction.SOUTH);
		boolean west = canConnectTo(worldIn, pos, Direction.WEST);
		return state.setValue(NORTH, north).setValue(EAST, east).setValue(SOUTH, south).setValue(WEST, west);
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		boolean east = state.getValue(EAST).booleanValue();
		boolean north = state.getValue(NORTH).booleanValue();
		boolean south = state.getValue(SOUTH).booleanValue();
		boolean west = state.getValue(WEST).booleanValue();

		if (east == false && north == false && south == false && west == false) { return FFFF; }
		
		if (east == true && north == true && south == false && west == false) { return TTFF; }
		if (east == false && north == true && south == false && west == true) { return FTFT; }
		if (east == true && north == false && south == true && west == false) { return TFTF; }
		if (east == false && north == false && south == true && west == true) { return FFTT; }

		if (east == false && north == true && south == false && west == false) { return FTFF; }
		if (east == true && north == false && south == false && west == false) { return TFFF; }
		if (east == false && north == false && south == false && west == true) { return FFFT; }
		if (east == false && north == false && south == true && west == false) { return FFTF; }

		else { return TTTT; }
	}
	
	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STAGE_0_15, NORTH, EAST, SOUTH, WEST, WATERLOGGED);
	}

	/* Flammable Block */
	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return true; }

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 5; }

	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 20; }

	/* Clone Item in Creative. */
	@Override
	public ItemStack getCloneItemStack(LevelReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(takeItem());
	}
	
	private Item takeItem() {
		if ((this == Unit_Blocks.CLOTHTABLE_oak.get()) || (this == Unit_Blocks.CLOTHTABLE_oaksub.get())) { return Items_Chinjufu.CAFETABLE.get(); }
		if ((this == Unit_Blocks.CLOTHTABLE_spruce.get()) || (this == Unit_Blocks.CLOTHTABLE_sprucesub.get())) { return Items_Chinjufu.CAFETABLE_spruce.get(); }
		if ((this == Unit_Blocks.CLOTHTABLE_birch.get()) || (this == Unit_Blocks.CLOTHTABLE_birchsub.get())) { return Items_Chinjufu.CAFETABLE_birch.get(); }
		if ((this == Unit_Blocks.CLOTHTABLE_jungle.get()) || (this == Unit_Blocks.CLOTHTABLE_junglesub.get())) { return Items_Chinjufu.CAFETABLE_jungle.get(); }
		if ((this == Unit_Blocks.CLOTHTABLE_acacia.get()) || (this == Unit_Blocks.CLOTHTABLE_acaciasub.get())) { return Items_Chinjufu.CAFETABLE_acacia.get(); }
		if ((this == Unit_Blocks.CLOTHTABLE_darkoak.get()) || (this == Unit_Blocks.CLOTHTABLE_darkoaksub.get())) { return Items_Chinjufu.CAFETABLE_darkoak.get(); }
		if ((this == Unit_Blocks.CLOTHTABLE_mangrove.get()) || (this == Unit_Blocks.CLOTHTABLE_mangrovesub.get())) { return Items_Chinjufu.CAFETABLE_mangrove.get(); }
		if ((this == Unit_Blocks.CLOTHTABLE_cherry.get()) || (this == Unit_Blocks.CLOTHTABLE_cherrysub.get())) { return Items_Chinjufu.CAFETABLE_cherry.get(); }
		
		if ((this == Unit_Blocks.CLOTHTABLE_sakura.get()) || (this == Unit_Blocks.CLOTHTABLE_sakurasub.get())) { return Items_Seasonal.CAFETABLE_sakura.get(); }
		if ((this == Unit_Blocks.CLOTHTABLE_kaede.get()) || (this == Unit_Blocks.CLOTHTABLE_kaedesub.get())) { return Items_Seasonal.CAFETABLE_kaede.get(); }
		else { return Items_Seasonal.CAFETABLE_ichoh.get(); }
	}	
}
