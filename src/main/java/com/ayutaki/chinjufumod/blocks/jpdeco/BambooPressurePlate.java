package com.ayutaki.chinjufumod.blocks.jpdeco;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BasePressurePlateBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class BambooPressurePlate extends BasePressurePlateBlock {
	public static final MapCodec<BambooPressurePlate> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(BlockSetType.CODEC.fieldOf("block_set_type")
					.forGetter(getter -> getter.type), propertiesCodec()).apply(instance, BambooPressurePlate::new));
	@Override
	public MapCodec<BambooPressurePlate> codec() { return CODEC; }
	
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
	public static final DirectionProperty H_FACING = BlockStateProperties.HORIZONTAL_FACING;
	
	public BambooPressurePlate(BlockSetType type, BlockBehaviour.Properties props) {
		super(props, type); //for 1.20.6
		/** Default state **/
		registerDefaultState(this.stateDefinition.any().setValue(H_FACING, Direction.NORTH)
				.setValue(POWERED, Boolean.valueOf(false)));
	}

	/* Gives a value when placed. +180 .getOpposite() */
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(H_FACING, context.getHorizontalDirection().getOpposite());
	}
	
	/* HORIZONTAL Property */
	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(H_FACING, rotation.rotate(state.getValue(H_FACING)));
	}

	@SuppressWarnings("deprecation")
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(H_FACING)));
	}

	/* Create Blockstate */
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(H_FACING, POWERED);
	}
	
	
	@Override
	protected int getSignalForState(BlockState state) {
		return state.getValue(POWERED) ? 15 : 0;
	}

	@Override
	protected BlockState setSignalForState(BlockState state, int i) {
		return state.setValue(POWERED, Boolean.valueOf(i > 0));
	}

	@Override
	protected int getSignalStrength(Level worldIn, BlockPos pos) {
		Class<? extends Entity> oclass = switch (this.type.pressurePlateSensitivity()) {
			case EVERYTHING -> Entity.class;
			case MOBS -> LivingEntity.class;
		};
		return getEntityCount(worldIn, TOUCH_AABB.move(pos), oclass) > 0 ? 15 : 0;
	}
}
