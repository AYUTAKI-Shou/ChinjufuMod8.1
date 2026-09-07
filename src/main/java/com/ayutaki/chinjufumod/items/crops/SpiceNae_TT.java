package com.ayutaki.chinjufumod.items.crops;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.crop.PepperVanilla;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Teatime;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SpiceNae_TT extends IR_Teatime {

	public SpiceNae_TT(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "item_seeds_pepper";
		case 1:
			return "item." + "item_seeds_cumin";
		case 2:
			return "item." + "item_seeds_turmeric";
		case 3:
			return "item." + "item_seeds_chilipepper";
		case 4:
			return "item." + "item_seeds_vanilla";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
		}
	}
	
	/* Place block */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		float temp = worldIn.getBiome(pos).getTemperature(pos);
		
		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		
		if (k == 0) {
			if (temp >= 0.5F) {
				boolean canPlace = (facing == EnumFacing.UP && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable());
				
				if (state.getBlock() instanceof BlockDirt && !hStack.isEmpty() && canPlace) {
					worldIn.setBlockState(pos, Crop_Blocks.PEPPER.getDefaultState().withProperty(PepperVanilla.STAGE_0_15, Integer.valueOf(0)), 10);
					worldIn.setBlockState(pos.up(), Crop_Blocks.PEPPER.getDefaultState().withProperty(PepperVanilla.STAGE_0_15, Integer.valueOf(8)), 10);

					CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
					return EnumActionResult.SUCCESS;
				}
				
				else { return EnumActionResult.FAIL; }
			}
			
			else { 
				playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.too_cold.name", new Object[0]), true);
				return EnumActionResult.FAIL; }
		}
		
		if (k == 1) {
			if (temp <= 0.85F) {
				if (state.getBlock() instanceof BlockFarmland && !hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable()) {

					worldIn.setBlockState(pos, Crop_Blocks.CUMIN.getDefaultState().withProperty(BlockCrops.AGE, Integer.valueOf(0)), 10);
					CMEvents.ItemBlock_Grass(worldIn, pos, playerIn, hand);
					return EnumActionResult.SUCCESS;
				}
				
				else { return EnumActionResult.FAIL; }
			}
			
			else { 
				playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.too_hot.name", new Object[0]), true);
				return EnumActionResult.FAIL; }
		}
		
		if (k == 2) {
			if (temp >= 0.5F) {
				if (state.getBlock() instanceof BlockFarmland && !hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable()) {

					worldIn.setBlockState(pos, Crop_Blocks.TURMERIC.getDefaultState().withProperty(BlockCrops.AGE, Integer.valueOf(0)), 10);
					CMEvents.ItemBlock_Grass(worldIn, pos, playerIn, hand);
					return EnumActionResult.SUCCESS;
				}
				
				else { return EnumActionResult.FAIL; }
			}
			
			else { 
				playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.too_cold.name", new Object[0]), true);
				return EnumActionResult.FAIL; }
		}
		
		if (k == 3) {
			if (temp >= 0.5F) {
				if (state.getBlock() instanceof BlockFarmland && !hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable()) {

					worldIn.setBlockState(pos, Crop_Blocks.CHILI.getDefaultState().withProperty(BlockCrops.AGE, Integer.valueOf(0)), 10);
					CMEvents.ItemBlock_Grass(worldIn, pos, playerIn, hand);
					return EnumActionResult.SUCCESS;
				}
				
				else { return EnumActionResult.FAIL; }
			}
			
			else { 
				playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.too_cold.name", new Object[0]), true);
				return EnumActionResult.FAIL; }
		}
		
		if (k == 4) {
			if (temp >= 0.5F) {
				boolean canPlace = (facing == EnumFacing.UP && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable());
				
				if (state.getBlock() instanceof BlockDirt && !hStack.isEmpty() && canPlace) {
					worldIn.setBlockState(pos, Crop_Blocks.VANILLA.getDefaultState().withProperty(PepperVanilla.STAGE_0_15, Integer.valueOf(0)), 10);
					worldIn.setBlockState(pos.up(), Crop_Blocks.VANILLA.getDefaultState().withProperty(PepperVanilla.STAGE_0_15, Integer.valueOf(8)), 10);

					CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
					return EnumActionResult.SUCCESS;
				}
				
				else { return EnumActionResult.FAIL; }
			}
			
			else { 
				playerIn.sendStatusMessage(new TextComponentTranslation("text.chinjufumod.too_cold.name", new Object[0]), true);
				return EnumActionResult.FAIL; }
		}
		
		else { return EnumActionResult.FAIL; }
	}


	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k == 0 || k == 4) { itemTip.add(I18n.format("tips.item_seeds_pepper.name")); }
		if (k == 1) { 
			itemTip.add(I18n.format("tips.item_seeds_cumin.name"));
			itemTip.add(I18n.format("tips.item_crop_pepperdry.name")); }
		if (k == 2) { 
			itemTip.add(I18n.format("tips.item_seeds_turmeric.name"));
			itemTip.add(I18n.format("tips.item_crop_pepperdry.name")); }
		if (k == 3) { 
			itemTip.add(I18n.format("tips.item_seeds_turmeric.name")); }
	}
}
