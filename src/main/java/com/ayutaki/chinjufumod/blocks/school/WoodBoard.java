package com.ayutaki.chinjufumod.blocks.school;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.color.Base_Chalk;
import com.ayutaki.chinjufumod.items.color.Board_Eraser;
import com.ayutaki.chinjufumod.network.OpenUI_WoodBoard;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;
import com.ayutaki.chinjufumod.tileentity.render.TakeValue_CM;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.PacketDistributor;

public class WoodBoard extends AbstractWoodBoard {
	/* Collision */
	private static final VoxelShape AABB_SOUTH = Block.box(1.0D, 1.0D, 0.0D, 15.0D, 15.0D, 1.5D);
	private static final VoxelShape AABB_WEST = Block.box(14.5D, 1.0D, 1.0D, 16.0D, 15.0D, 15.0D);
	private static final VoxelShape AABB_NORTH = Block.box(1.0D, 1.0D, 14.5D, 15.0D, 15.0D, 16.0D);
	private static final VoxelShape AABB_EAST = Block.box(0.0D, 1.0D, 1.0D, 1.5D, 15.0D, 15.0D);

	public WoodBoard(AbstractBlock.Properties props) {
		super(props);
	}
	 
	/* RightClick Action */
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult hit) {
		if (playerIn != null) {
			World world = playerIn.level;
			
			TileEntity tileEntity = world.getBlockEntity(pos);
			if (tileEntity instanceof WoodBoard_TileEntity) {
				WoodBoard_TileEntity board = (WoodBoard_TileEntity) tileEntity;
				
				ItemStack hStack = playerIn.getItemInHand(hand);
				Item hItem = hStack.getItem();
				boolean mode = playerIn.abilities.instabuild;
				
				if(board.isWaxed()) {
					CMEvents.textIsWaxed(worldIn, pos, playerIn);
					return ActionResultType.PASS; }
				
				else { //!board.isWaxed()
					/** Chalk **/
					if (hItem instanceof Base_Chalk) {
						playerIn.playSound(SoundEvents_CM.WRITE_CHALK, 1.0F, 1.0F);
						board.setTxtColor(TakeValue_CM.chalkColor(hItem));
						
						if(!world.isClientSide()) {
							board.setAllowedPlayerEditor(playerIn);
							ChinjufuMod.CHANNEL.send(PacketDistributor.ALL.noArg(), new OpenUI_WoodBoard(playerIn.getUUID(), board.getBlockPos()));
							hStack.hurtAndBreak(mode? 0 : 1, playerIn, user -> { hStack.shrink(1); });}

						return ActionResultType.SUCCESS; }
					
					/** Glowi Text **/
					boolean hasGlow = board.hasGlowText();
					if (!hasGlow && hItem == Items.GLOWSTONE_DUST) {
						board.setGlowText(true);
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						return ActionResultType.SUCCESS; }
					
					if (hasGlow && hItem == Items.BONE_MEAL) {
						board.setGlowText(false);
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						return ActionResultType.SUCCESS; }
					
					/** Waxed **/
					if (hItem == Items.HONEYCOMB) {
						board.setWaxed(true);
						CMEvents.Wax_Particle(worldIn, pos, playerIn, hand);
						return ActionResultType.SUCCESS; }
					
					/** Eraser **/
					if (hItem instanceof Board_Eraser) {
						int life = hStack.getMaxDamage() - hStack.getDamageValue();
						if (life <= 1) { 
							CMEvents.textNeedClean(worldIn, pos, playerIn);
							return ActionResultType.PASS; }
						
						else {
							board.clearText();
							hStack.hurtAndBreak(mode? 0 : 1, playerIn, user -> { hStack.shrink(1); } );
							playerIn.playSound(SoundEvents_CM.USE_ERASER, 1.0F, 1.0F);
							return ActionResultType.SUCCESS; } 
					}
				} //!board.isWaxed()
				
			}//tileEntity
		}//playerIn != null
		return ActionResultType.PASS;
	}

	/* Collisions for each property. */
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Direction direction = state.getValue(H_FACING);
		
		switch (direction) {
		case NORTH:
		default: return AABB_NORTH;
		case SOUTH: return AABB_SOUTH;
		case WEST: return AABB_WEST;
		case EAST: return AABB_EAST;
		} // switch
	}
	
	/* The best harvesting tool. */
	@Nullable
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
