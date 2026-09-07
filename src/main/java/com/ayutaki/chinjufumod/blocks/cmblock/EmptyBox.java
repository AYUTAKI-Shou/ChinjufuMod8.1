package com.ayutaki.chinjufumod.blocks.cmblock;

import com.ayutaki.chinjufumod.blocks.base.BaseWaterLogged;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EmptyBox extends BaseWaterLogged {

	private static final VoxelShape AABB_BOX = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D),
			Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D),
			Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D));
	
	public EmptyBox(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action */
	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		int gHC = hStack.getCount();

		boolean ammo = (hItem == Items_Weapon.AMMUNITION_L.get() && gHC >= 8);
		boolean bauxi = (hItem == Items_Chinjufu.BAUXITE.get() && gHC >= 8);

		if (ammo || bauxi) {
			CMEvents.consumeN_Hand(8, playerIn, hand);
			CMEvents.soundWoodPlace(worldIn, pos);
			
			Direction facing = playerIn.getDirection().getOpposite();
			Block takeType = (ammo? Chinjufu_Blocks.AMMO_BOX.get() : Chinjufu_Blocks.BAUXITE_BOX.get());
			worldIn.setBlock(pos, takeType.defaultBlockState()
					.setValue(AmmoBauxiteBox.H_FACING, facing)
					.setValue(AmmoBauxiteBox.WATERLOGGED, state.getValue(WATERLOGGED)), 3);

			return InteractionResult.SUCCESS; }
		
		return InteractionResult.PASS;
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AABB_BOX;
	}

	/* Flammable Block */
	/** IForgeBlock.class Called when fire is updating, checks if a block face can catch fire. **/
	@Override
	public boolean isFlammable(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return true; }

	/** Called when fire is updating on a neighbor block. **/
	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 5; }

	/**Chance that fire will spread and consume this block. 300 being a 100% chance, 0, being a 0% chance **/
	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction direct) { return 20; }
}
