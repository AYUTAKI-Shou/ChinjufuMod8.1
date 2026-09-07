package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Soup_Shikki extends BaseStage3_FDA {

	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.40625D, 0.0D, 0.40625D, 0.59375D, 0.125D, 0.59375D);
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.40625D, -0.5D, 0.40625D, 0.59375D, 0.01D, 0.59375D);

	public Soup_Shikki(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();

		if (i != 3) {
			if (hStack.isEmpty()) {
				CMEvents.soundEat(worldIn, pos);
				
				/** add Potion Effect. **/
				if (!worldIn.isRemote) {
					boolean miso = (this == Dish_Blocks.MISOSOUP);
					int SAT = miso? 1 : 2;
					/** DIG_SPEED, 1200/20=60 seconds., SATURATION 2 =1 piece of MEAT **/
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.HASTE, 1200, 0));
					((EntityLivingBase) playerIn).addPotionEffect(new PotionEffect(MobEffects.SATURATION, SAT, 0)); }
				
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)), 3); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		
		if (i == 3) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		/** 'true' to not put anything on top. **/
		return true;
	}

	/*Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();
		return flag? AABB : AABB_DOWN;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		if (i == 1) { stack.add(new ItemStack(cloneItem(), 1, 0)); }
		else { stack.add(new ItemStack(Items_Teatime.Item_DISH, 1, 4)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}
	
	private Item cloneItem() {
		if (this == Dish_Blocks.ZENZAI_M) { return Items_Teatime.ZENZAI_M; }
		if (this == Dish_Blocks.ZENZAI_K) { return Items_Teatime.ZENZAI_K; }
		else { return Items_Teatime.MISOSOUP; }
	}
}
