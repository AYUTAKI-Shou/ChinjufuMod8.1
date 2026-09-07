package com.ayutaki.chinjufumod.blocks.dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class NabeCooked_Pudding extends BaseStage4_Face {
	/** １=バニラ, 2=抹茶, 3=紅茶, 4=カカオ **/
	public NabeCooked_Pudding(String name) {
		super(name);

		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
	}

	/* Steam effect. */

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		/** １=バニラ, 2=抹茶, 3=紅茶, 4=カカオ **/
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (hStack.isEmpty()) {
			CMEvents.emptyTakeN_ClothB(worldIn, pos, playerIn, Items_NoTab.PUDDING_RAW, 1, i);
			ItemStack take = new ItemStack(Items_Seasonal.TANMONO, 2, 0);
			if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }
			
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState()); }
		
		else { //!empty
			CMEvents.textFullItem(worldIn, pos, playerIn); }
		/** 'true' to not put anything on top. **/
		return true;
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.21875D, 0.0D, 0.21875D, 0.78125D, 0.25D, 0.78125D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* Cooking */
	protected boolean cookingIn(World worldIn, BlockPos pos) {
		Block downBlock = worldIn.getBlockState(pos.down()).getBlock();
		return (downBlock == Blocks.LIT_FURNACE || downBlock == Kitchen_Blocks.LIT_KITSTOVE || 
				downBlock == Kitchen_Blocks.LIT_KITOVEN || downBlock == Kitchen_Blocks.LIT_KITOVEN_B || 
				downBlock == Kitchen_Blocks.LIT_IRORI);
	}
	
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		World par1World = worldIn;
		int par2 = x;
		int par3 = y;
		int par4 = z;
		Random par5Random = rand;

		/** １=バニラ, 2=抹茶, 3=紅茶, 4=カカオ **/
		if (cookingIn(worldIn, pos)) {
			for (int la = 0; la < 1; ++la) {
				double d0 = (double) ((float) par2 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
				double d1 = ((double) ((float) par3 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D) + 0.5D;
				double d2 = (double) ((float) par4 + 0.5F) + (double) (par5Random.nextFloat() - 0.5F) * 0.01D;
				double d3 = 0.12D;
				double d4 = 0.17D;
				par1World.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, d0 - d4 + 0.25, d1 + d3 -0.5, d2, 0.0D, 0.0D, 0.0D);
			}
			
			if (rand.nextDouble() < 0.1D) {
				worldIn.playSound(x, y, z, SoundEvents_CM.GUTSUGUTSU, SoundCategory.BLOCKS, 0.5F, 0.7F, false); }
		}
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_NoTab.PUDDING_RAW, 1, cloneMeta(state)));
		stack.add(new ItemStack(Items_Seasonal.TANMONO, 2, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.NABE_NAMA_PUDDING, 1, cloneMeta(state));
	}

	private int cloneMeta(IBlockState state) {
		return ((Integer)state.getValue(STAGE_1_4)).intValue();
	}
}
