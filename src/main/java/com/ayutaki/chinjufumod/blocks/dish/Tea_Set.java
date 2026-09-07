package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Tea_Set extends BaseStage4_FDP {

	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.25, 0.0, 0.125, 0.75, 0.15625, 0.9375);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.25, 0.0, 0.125, 0.75, 0.15625, 0.9375);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.25, 0.0, 0.125, 0.75, 0.15625, 0.9375);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.25, 0.0, 0.125, 0.75, 0.15625, 0.9375);

	private static final AxisAlignedBB DOWN_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.25, -0.5, 0.125, 0.75, 0.01, 0.9375);
	private static final AxisAlignedBB DOWN_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.25, -0.5, 0.125, 0.75, 0.01, 0.9375);
	private static final AxisAlignedBB DOWN_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.25, -0.5, 0.125, 0.75, 0.01, 0.9375);
	private static final AxisAlignedBB DOWN_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.25, -0.5, 0.125, 0.75, 0.01, 0.9375);

	public Tea_Set(String name) {
		super(name);
		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i != 4) {
			if (hStack.isEmpty()) {
				CMEvents.soundEat(worldIn, pos);
				
				/** add Potion Effect. **/
				if (!worldIn.isRemote) {
					/** DIG_SPEED 1200/20=60 seconds **/
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.HASTE, 1200, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 4, 0)); }
				
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (i == 4) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();

		switch (direction) {
		case NORTH:
		default:
			return flag? AABB_NORTH : DOWN_NORTH;
		case SOUTH:
			return flag? AABB_SOUTH : DOWN_SOUTH;
		case WEST:
			return flag? AABB_WEST : DOWN_WEST;
		case EAST:
			return flag? AABB_EAST : DOWN_EAST;
		}
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (i == 1) { stack.add(cloneStack()); }
		if (i != 1) {
			stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 2));
			stack.add(new ItemStack(Items_Teatime.Item_SARA, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.TEASET, 1, 0);
	}
}
