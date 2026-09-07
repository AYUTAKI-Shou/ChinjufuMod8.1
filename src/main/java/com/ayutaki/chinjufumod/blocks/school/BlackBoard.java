package com.ayutaki.chinjufumod.blocks.school;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.GuiHandler_CM;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.color.Base_Chalk;
import com.ayutaki.chinjufumod.items.color.Board_Eraser;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.tileentity.BlackBoard_TileEntity;
import com.ayutaki.chinjufumod.tileentity.render.TakeValue_CM;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlackBoard extends AbstractBlackBoard {

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.0, 0.09375, 1.0, 1.0);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.0, 0.09375, 1.0, 1.0);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.0, 0.09375, 1.0, 1.0);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.0, 0.09375, 1.0, 1.0);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public BlackBoard(String name) {
		super(name, Material.WOOD);
		setCreativeTab(ChinjufuModTabs.CHINJUFU);

		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (playerIn != null) {
			World world = playerIn.world;
			
			TileEntity tileEntity = world.getTileEntity(pos);
			if (tileEntity instanceof BlackBoard_TileEntity) {
				BlackBoard_TileEntity board = (BlackBoard_TileEntity) tileEntity;
				
				ItemStack hStack = playerIn.getHeldItem(hand);
				Item hItem = hStack.getItem();

				if(board.isWaxed()) {
					CMEvents.textIsWaxed(worldIn, pos, playerIn);
					return false; }
				
				else {
					int life = hStack.getMaxDamage() - hStack.getItemDamage();
					/** Chalk **/
					if (hItem instanceof Base_Chalk) {
						playerIn.playSound(SoundEvents_CM.WRITE_CHALK, 1.0F, 1.0F);
						board.setTextColor(TakeValue_CM.chalkColor(hItem));
						
						playerIn.openGui(ChinjufuMod.instance, GuiHandler_CM.BLACKBOARD_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
						if (life <= 1) { hStack.shrink(1); }
						else { CMEvents.toolDamege(1, playerIn, hStack); }
					
						return true; }
					
					/** Glowi Text **/
					boolean hasGlow = board.hasGlowText();
					if (!hasGlow && hItem == Items.GLOWSTONE_DUST) {
						board.setGlowText(true);
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						return true; }
					
					int k = hStack.getMetadata();
					if (hasGlow && hItem == Items.DYE && k == 15) {
						board.setGlowText(false);
						CMEvents.consume1_seSnowP(worldIn, pos, playerIn, hand);
						return true; }
					
					/** Waxed **/
					if (hItem == Items.SLIME_BALL) {
						board.setWaxed(true);
						CMEvents.Wax_ParticleSound(worldIn, pos, playerIn, hand);
						return true; }
					
					/** Eraser **/
					if (hItem instanceof Board_Eraser) {
						if (life <= 1) { 
							CMEvents.textNeedClean(worldIn, pos, playerIn);
							return false; }
						
						else { 
							board.clearText();
							CMEvents.toolDamege(1, playerIn, hStack);
							playerIn.playSound(SoundEvents_CM.USE_ERASER, 1.0F, 1.0F); 
							return true; }
					}
				}//!board.isWaxed()
			}//tileEntity
		}//playerIn != null
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean t_f) {

		EnumFacing facing = state.getValue(H_FACING);
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB[facing.getHorizontalIndex()]);
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}

	@Override
	public int getHarvestLevel(IBlockState state) {
		return 0;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Items_Chinjufu.BLACKBOARD;
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(Items_Chinjufu.BLACKBOARD, 1, 0);
	}
}
