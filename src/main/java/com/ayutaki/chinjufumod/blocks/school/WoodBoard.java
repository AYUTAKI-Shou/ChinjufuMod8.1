package com.ayutaki.chinjufumod.blocks.school;

import java.util.ArrayList;
import java.util.List;

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
import com.ayutaki.chinjufumod.registry.School_Blocks;
import com.ayutaki.chinjufumod.tileentity.WoodBoard_TileEntity;
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
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WoodBoard extends AbstractWoodBoard {

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0625, 0.0625, 0.09375, 0.9375, 0.9375);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0625, 0.0625, 0.09375, 0.9375, 0.9375);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0625, 0.0625, 0.09375, 0.9375, 0.9375);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0625, 0.0625, 0.09375, 0.9375, 0.9375);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	public WoodBoard(String name) {
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
			if (tileEntity instanceof WoodBoard_TileEntity) {
				WoodBoard_TileEntity board = (WoodBoard_TileEntity) tileEntity;
				
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
						
						playerIn.openGui(ChinjufuMod.instance, GuiHandler_CM.WOODBOARD_GUI, worldIn, pos.getX(), pos.getY(), pos.getZ());
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

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_Chinjufu.WOODBOARD, 1, cloneMeta()));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Chinjufu.WOODBOARD, 1, cloneMeta());
	}
	
	private int cloneMeta() {
		if (this == School_Blocks.BOARD_OAK) { return 0; }
		if (this == School_Blocks.BOARD_SPRUCE) { return 1; }
		if (this == School_Blocks.BOARD_BIRCH) { return 2; }
		if (this == School_Blocks.BOARD_JUNGLE) { return 3; }
		if (this == School_Blocks.BOARD_ACACIA) { return 4; }
		if (this == School_Blocks.BOARD_DOAK) { return 5; }
		if (this == School_Blocks.BOARD_SAKURA) { return 6; }
		if (this == School_Blocks.BOARD_KAEDE) { return 7; }
		else { return 8; } 
	}
}
