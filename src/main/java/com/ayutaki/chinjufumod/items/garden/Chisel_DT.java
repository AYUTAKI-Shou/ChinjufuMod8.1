package com.ayutaki.chinjufumod.items.garden;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.garden.Base_Niwaishi;
import com.ayutaki.chinjufumod.blocks.kitchen.UsuTsuki;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Wadeco;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;
import com.ayutaki.chinjufumod.registry.Kitchen_Blocks;
import com.ayutaki.chinjufumod.registry.WallBrick_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Chisel_DT extends IR_Wadeco {

	public Chisel_DT(String name) {
		super(name);
		setUnlocalizedName(name);

		this.maxStackSize = 1;
		setMaxDamage(256);
	}

	private void consumeAndBreak(ItemStack hStack, EntityPlayer playerIn) {
		CMEvents.toolDamege(1, playerIn, hStack);
		playerIn.getCooldownTracker().setCooldown(this, 10);
	}
	
	private void chisel_seStone(ItemStack hStack, EntityPlayer playerIn, World iworld, BlockPos pos) {
		iworld.playSound(playerIn, pos, SoundEvents.BLOCK_STONE_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F);
		this.consumeAndBreak(hStack, playerIn);
	}
	
	private void chisel_seWood(ItemStack hStack, EntityPlayer playerIn, World iworld, BlockPos pos) {
		iworld.playSound(playerIn, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 1.0F, 0.8F);
		this.consumeAndBreak(hStack, playerIn);
	}
	
	/* FlintAndSteel */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World iworld, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = iworld.getBlockState(pos);
		ItemStack hStack = playerIn.getHeldItem(hand);
		Block block = state.getBlock();
		
		if(!playerIn.getCooldownTracker().hasCooldown(this)) {
			/** Stone **/
			if (block instanceof BlockStone) {
				if (state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.STONE) {
					iworld.setBlockState(pos, Garden_Blocks.NIWAISHI.getDefaultState().withProperty(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
					this.chisel_seStone(hStack, playerIn, iworld, pos);
					return EnumActionResult.SUCCESS; }

				if (state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.GRANITE) {
					iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_gra.getDefaultState().withProperty(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
					this.chisel_seStone(hStack, playerIn, iworld, pos);
					return EnumActionResult.SUCCESS; }

				if (state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.DIORITE) {
					iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_dio.getDefaultState().withProperty(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
					this.chisel_seStone(hStack, playerIn, iworld, pos);
					return EnumActionResult.SUCCESS; }

				if (state.getValue(BlockStone.VARIANT) == BlockStone.EnumType.ANDESITE) {
					iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_and.getDefaultState().withProperty(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
					this.chisel_seStone(hStack, playerIn, iworld, pos);
					return EnumActionResult.SUCCESS; }
			}

			/** Slab **/
			if (block instanceof BlockStoneSlab) {
				if (state.getValue(BlockStoneSlab.VARIANT) == BlockStoneSlab.EnumType.STONE) {
					iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_slab.getDefaultState().withProperty(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
					this.chisel_seStone(hStack, playerIn, iworld, pos);
					return EnumActionResult.SUCCESS; }
			}
			
			if (block == WallBrick_Blocks.RGRA_slabhalf) {
				iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_slab_gra.getDefaultState().withProperty(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, iworld, pos);
				return EnumActionResult.SUCCESS; }
			
			if (block == WallBrick_Blocks.RDIO_slabhalf) {
				iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_slab_dio.getDefaultState().withProperty(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, iworld, pos);
				return EnumActionResult.SUCCESS; }
			
			if (block == WallBrick_Blocks.RAND_slabhalf) {
				iworld.setBlockState(pos, Garden_Blocks.NIWAISHI_slab_and.getDefaultState().withProperty(Base_Niwaishi.STAGE_0_15, Integer.valueOf(0)), 3);
				this.chisel_seStone(hStack, playerIn, iworld, pos);
				return EnumActionResult.SUCCESS; }
			
			
			/** Base_Niwaishi **/
			if (block instanceof Base_Niwaishi) {
				int i = state.getValue(Base_Niwaishi.STAGE_0_15).intValue();
				boolean mode = playerIn.capabilities.isCreativeMode;
				
				if (i <= 13) {
					iworld.setBlockState(pos, state.withProperty(Base_Niwaishi.STAGE_0_15, Integer.valueOf(i + 2)), 3);
					this.chisel_seStone(hStack, playerIn, iworld, pos);
					return EnumActionResult.SUCCESS; }

				if (i == 14 || i == 15) {
					if (mode) { iworld.destroyBlock(pos, false); }
					else { iworld.destroyBlock(pos, true); }
					
					this.chisel_seStone(hStack, playerIn, iworld, pos);
					return EnumActionResult.SUCCESS; }
			}
			
			
			/** oak **/
			if (block instanceof BlockOldLog) {
				BlockPlanks.EnumType type = state.getValue(BlockOldLog.VARIANT);
				BlockLog.EnumAxis axis = state.getValue(BlockLog.LOG_AXIS);
				
				if (type == BlockPlanks.EnumType.OAK && axis == BlockLog.EnumAxis.Y) {
					iworld.setBlockState(pos, Kitchen_Blocks.USU_TSUKI.getDefaultState().withProperty(UsuTsuki.STAGE_0_15, Integer.valueOf(0)), 3);
					this.chisel_seWood(hStack, playerIn, iworld, pos);
					return EnumActionResult.SUCCESS; }
			}

			/** USU **/
			if (block instanceof UsuTsuki) {
				int i = state.getValue(UsuTsuki.STAGE_0_15).intValue();
		
				if (i <= 2) {
					iworld.setBlockState(pos, state.withProperty(UsuTsuki.STAGE_0_15, Integer.valueOf(i + 1)), 3);
					this.chisel_seWood(hStack, playerIn, iworld, pos);
					return EnumActionResult.SUCCESS; }
			}
		}

		return EnumActionResult.FAIL;
	}

	/* Items needed for repair. */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return (repair.getItem() == Items.IRON_INGOT);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.item_chisel.name"));
	}
}
