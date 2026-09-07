package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

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

public class Corn_Soup extends BaseStage4_FDP {

	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.34375D, 0.0D, 0.34375D, 0.65325D, 0.125D, 0.65325D);
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.34375D, -0.5D, 0.34375D, 0.65325D, 0.01D, 0.65325D);

	private static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(3.5D * cw, 0.0D * cw, 2.5D * cw, 12.5D * cw, 3.0D * cw, 11.5D * cw);
	private static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(4.5D * cw, 0.0D * cw, 3.5D * cw, 13.5D * cw, 3.0D * cw, 12.5D * cw);
	private static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(3.5D * cw, 0.0D * cw, 4.5D * cw, 12.5D * cw, 3.0D * cw, 13.5D * cw);
	private static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(2.5D * cw, 0.0D * cw, 3.5D * cw, 11.5D * cw, 3.0D * cw, 12.5D * cw);

	private static final AxisAlignedBB DOWN_SOUTH = new AxisAlignedBB(3.5D * cw, -8.0D * cw, 2.5D * cw, 12.5D * cw, 0.1D * cw, 11.5D * cw);
	private static final AxisAlignedBB DOWN_WEST = new AxisAlignedBB(4.5D * cw, -8.0D * cw, 3.5D * cw, 13.5D * cw, 0.1D * cw, 12.5D * cw);
	private static final AxisAlignedBB DOWN_NORTH = new AxisAlignedBB(3.5D * cw, -8.0D * cw, 4.5D * cw, 12.5D * cw, 0.1D * cw, 13.5D * cw);
	private static final AxisAlignedBB DOWN_EAST = new AxisAlignedBB(2.5D * cw, -8.0D * cw, 3.5D * cw, 11.5D * cw, 0.1D * cw, 12.5D * cw);

	public Corn_Soup(String name) {
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

		if (i >= 3) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i < 3
			if (hStack.isEmpty()) {
				CMEvents.soundEat(worldIn, pos);
				
				/** add Potion Effect. **/
				if (!worldIn.isRemote) {
					/** DIG_SPEED, 1200/20=60 seconds., SATURATION 2 =1 piece of MEAT **/
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.HASTE, 1200, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, 1, 0)); }
				
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	/*Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 4) { 
			EnumFacing direction = (EnumFacing)state.getValue(H_FACING);			
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
		
		else { return flag? AABB : AABB_DOWN; }
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(cloneStack(state));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack(state);
	}
	
	private ItemStack cloneStack(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 1) { return new ItemStack(Items_Teatime.CORNSOUP, 1, 0); }
		else { return new ItemStack(Items_Teatime.Item_SARA, 1, 0); }
	}
}
