package com.ayutaki.chinjufumod.blocks.cmblock;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingWater;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_Weapon;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
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

public class AmmoBauxiteBox extends BaseFacingWater {

	private static final VoxelShape AABB_BOX = Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D),
			Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D),
			Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D),
			Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D));
	
	public AmmoBauxiteBox(BlockBehaviour.Properties props) {
		super(props);
	}

	/* RightClick Action for 1.21.4 */
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level worldIn, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult hit) {
		ItemStack hStack = playerIn.getItemInHand(hand);
		Item hItem = hStack.getItem();
		
		if (hItem instanceof BlockItem) { return InteractionResult.PASS; }
		
		if (hStack.isEmpty()) {
			CMEvents.emptyTake_NItem(worldIn, pos, playerIn, this.takeItem(), 8);
			
			worldIn.setBlock(pos, Chinjufu_Blocks.EMPTY_BOX.get().defaultBlockState()
					.setValue(EmptyBox.WATERLOGGED, state.getValue(WATERLOGGED)), 3); } 
		
		return InteractionResult.SUCCESS;
	}
	
	private Item takeItem() {
		if (this == Chinjufu_Blocks.AMMO_BOX.get()) { return Items_Weapon.AMMUNITION_L.get(); }
		else { return Items_Chinjufu.BAUXITE.get(); }
	}
	
	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return AABB_BOX;
	}
}
