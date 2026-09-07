package com.ayutaki.chinjufumod.items.garden;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.garden.Samon;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Wadeco;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockSand;
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

public class Kumade_DT extends IR_Wadeco {

	public Kumade_DT(String name) {
		super(name);
		setUnlocalizedName(name);

		this.maxStackSize = 1;
		setMaxDamage(128);
	}

	private void soundKumade(World iworld, EntityPlayer playerIn, BlockPos pos) {
		iworld.playSound(playerIn, pos, SoundEvents.BLOCK_SAND_PLACE, SoundCategory.BLOCKS, 1.0F, 0.8F); }
	
	private void consumeAndBreak(ItemStack hStack, EntityPlayer playerIn, World iworld, BlockPos pos) {
		this.soundKumade(iworld, playerIn, pos);
		CMEvents.toolDamege(1, playerIn, hStack);
		playerIn.getCooldownTracker().setCooldown(this, 10);
	}
	
	/* FlintAndSteel */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World iworld, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = iworld.getBlockState(pos);
		ItemStack hStack = playerIn.getHeldItem(hand);
		Block block = state.getBlock();
		
		if(!playerIn.getCooldownTracker().hasCooldown(this)) {
			
			if (state.getBlock() instanceof BlockSand && state.getValue(BlockSand.VARIANT) == BlockSand.EnumType.SAND) {
				iworld.setBlockState(pos, Garden_Blocks.SAMON.getDefaultState().withProperty(Samon.STAGE_0_7, Integer.valueOf(0)), 3);
				this.consumeAndBreak(hStack, playerIn, iworld, pos);
				return EnumActionResult.SUCCESS; }
			
			if (state.getBlock() instanceof BlockGravel) {
				iworld.setBlockState(pos, Garden_Blocks.SAMON_B.getDefaultState().withProperty(Samon.STAGE_0_7, Integer.valueOf(0)), 3);
				this.consumeAndBreak(hStack, playerIn, iworld, pos);
				return EnumActionResult.SUCCESS; }

			/** Samon **/
			if (block instanceof Samon) {
				this.soundKumade(iworld, playerIn, pos);
				iworld.setBlockState(pos, state.cycleProperty(Samon.STAGE_0_7), 3);
				
				playerIn.getCooldownTracker().setCooldown(this, 10);
				return EnumActionResult.SUCCESS; }
		}
		
		return EnumActionResult.FAIL;
	}

	/* Items needed for repair. */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return (repair.getItem() == Items.STICK);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.item_kumade.name"));
	}
}
