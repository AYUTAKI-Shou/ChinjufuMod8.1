package com.ayutaki.chinjufumod.blocks.slidedoor;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.window.CurtainTall_CT;
import com.ayutaki.chinjufumod.items.window.Curtain_CT;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.doors.Garasudo_Blocks;
import com.ayutaki.chinjufumod.state.HalfState;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class GlassDoor extends BaseSlidedoor {

	public GlassDoor(String name) {
		super(name);
		/** Registry **/
		ForgeRegistries.BLOCKS.register(this);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		BlockPos pos1 = state.getValue(HALF) == HalfState.LOWER ? pos : pos.down();
		IBlockState state1= pos.equals(pos1) ? state : worldIn.getBlockState(pos1);
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		if (hItem instanceof Curtain_CT || hItem instanceof CurtainTall_CT) { return false; }

		else {
			state = state1.cycleProperty(OPEN);
			worldIn.setBlockState(pos1, state, 10);
			worldIn.markBlockRangeForRenderUpdate(pos1, pos);
			CMEvents.soundHikidoL(worldIn, pos);
			return true;
		}
	}

	/* Power on/off. Destroy blocks. */
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (state.getValue(HALF) == HalfState.UPPER) {
			BlockPos downPos = pos.down();
			IBlockState downState = worldIn.getBlockState(downPos);

			if (downState.getBlock() != this) { worldIn.setBlockToAir(pos); }
			
			else if (blockIn != this) { downState.neighborChanged(worldIn, downPos, blockIn, fromPos); }
		}

		else {
			boolean flag1 = false;
			BlockPos upPos = pos.up();
			IBlockState upState = worldIn.getBlockState(upPos);

			if (upState.getBlock() != this) {
				worldIn.setBlockToAir(pos);
				flag1 = true; }

			if (flag1) {
				if (!worldIn.isRemote) { this.dropBlockAsItem(worldIn, pos, state, 0); }
			}

			else {
				boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(upPos);

				if (blockIn != this && (flag || blockIn.getDefaultState().canProvidePower()) && flag != ((Boolean)upState.getValue(POWERED)).booleanValue()) {

					worldIn.setBlockState(upPos, upState.withProperty(POWERED, Boolean.valueOf(flag)), 2);

					if (flag != ((Boolean)state.getValue(OPEN)).booleanValue()) {
						worldIn.setBlockState(pos, state.withProperty(OPEN, Boolean.valueOf(flag)), 2);
						worldIn.markBlockRangeForRenderUpdate(pos, pos);
						CMEvents.soundHikidoL(worldIn, pos); }
				}
			}
		}
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(cloneItem(state), 1, cloneMeta(state)));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(state), 1, cloneMeta(state));
	}
	
	private Item cloneItem(IBlockState state) {
		boolean seasonal = (this == Garasudo_Blocks.GARASUDO_SAKU || this == Garasudo_Blocks.GARASUDO_KAE || 
				this == Garasudo_Blocks.GARASUDO_ICH || this == Garasudo_Blocks.GARASUDOB_SAKU || 
				this == Garasudo_Blocks.GARASUDOB_KAE || this == Garasudo_Blocks.GARASUDOB_ICH);

		if (state.getValue(HALF) == HalfState.UPPER) { return Items.AIR; }
		else { 
			if (seasonal) { return Items_Seasonal.SGARASUDO_item; }
			else { return Items_Wadeco.GARASUDO_item; }
		}
	}
	
	private int cloneMeta(IBlockState state) {
		if (state.getValue(HALF) == HalfState.UPPER) { return 0; }
		else {
			if (this == Garasudo_Blocks.GARASUDO) { return 0; }
			if (this == Garasudo_Blocks.GARASUDO_SPRU) { return 1; }
			if (this == Garasudo_Blocks.GARASUDO_BIR) { return 2; }
			if (this == Garasudo_Blocks.GARASUDO_JUN) { return 3; }
			if (this == Garasudo_Blocks.GARASUDO_ACA) { return 4; }
			if (this == Garasudo_Blocks.GARASUDO_DOAK) { return 5; }
			if (this == Garasudo_Blocks.GARASUDOB) { return 6; }
			if (this == Garasudo_Blocks.GARASUDOB_SPRU) { return 7; }
			if (this == Garasudo_Blocks.GARASUDOB_BIR) { return 8; }
			if (this == Garasudo_Blocks.GARASUDOB_JUN) { return 9; }
			if (this == Garasudo_Blocks.GARASUDOB_ACA) { return 10; }
			if (this == Garasudo_Blocks.GARASUDOB_DOAK) { return 11; }
			
			if (this == Garasudo_Blocks.GARASUDO_SAKU) { return 0; }
			if (this == Garasudo_Blocks.GARASUDO_KAE) { return 1; }
			if (this == Garasudo_Blocks.GARASUDO_ICH) { return 2; }
			if (this == Garasudo_Blocks.GARASUDOB_SAKU) { return 3; }
			if (this == Garasudo_Blocks.GARASUDOB_KAE) { return 4; }
			else { return 5; }
		}
	}

	/* Rendering */
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
}
