package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.crop.Tengusa;
import com.ayutaki.chinjufumod.blocks.dish.TengusaWashed;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.handler.SoundEvents_CM;
import com.ayutaki.chinjufumod.items.addtab.IR_Teatime;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Dish_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockSand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Shio_TT extends IR_Teatime {

	public Shio_TT(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "item_salt";
		case 1:
			return "item." + "item_nigari";
		case 2:
			return "item." + "item_rennet";
		case 3:
			return "item." + "item_crop_tengusa";
		case 4:
			return "item." + "item_crop_tengusawash";
		case 5:
			return "item." + "item_crop_tengusadry";
		}
	}

	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
		}
	}

	private void TENGUSA_wash(World worldIn, BlockPos pos, ItemStack hStack, EntityPlayer playerIn) {
		worldIn.playSound(playerIn, pos, SoundEvents_CM.WATER_SPLASH, SoundCategory.BLOCKS, 0.5F, 1.2F);
		
		ItemStack take = new ItemStack(Items_Teatime.SHIO, 1, 4);
		if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); }
		
		boolean mode = playerIn.capabilities.isCreativeMode;
		if (mode) { }
		else { hStack.shrink(2); } }
	
	/* FlintAndSteel */
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		
		if (k == 3) {
			if (block == Blocks.CAULDRON) {
				int gHC = hStack.getCount();
				
				if (gHC >= 2) {
					int level = state.getValue(BlockCauldron.LEVEL);
					if (level != 0) {
						((BlockCauldron)block).setWaterLevel(worldIn, pos, state, level - 1);
						this.TENGUSA_wash(worldIn, pos, hStack, playerIn);
						return EnumActionResult.SUCCESS; }
					
					else { return EnumActionResult.FAIL; } }
				
				else {
					CMEvents.textNotEnough_Items(worldIn, pos, playerIn);
					return EnumActionResult.FAIL; }
			}
			
			else {
				if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }
				
				float temp = worldIn.getBiome(pos).getTemperature(pos);
				IBlockState downState1 = worldIn.getBlockState(pos.down());
				IBlockState downState2 = worldIn.getBlockState(pos.down(2));
				boolean WATER = (downState1.getBlock() == Blocks.WATER);
				boolean SAND2 = (downState2.getBlock() instanceof BlockSand) || (downState2.getBlock() instanceof BlockGravel);
				boolean canPlace = WATER && SAND2 && temp >= 0.5F;
				
				if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && canPlace) {
					/** Put the Block. **/
					worldIn.setBlockState(pos, Crop_Blocks.TENGUSA.getDefaultState().withProperty(Tengusa.STAGE_0_7, Integer.valueOf(0)), 10);
					CMEvents.ItemBlock_Grass(worldIn, pos, playerIn, hand);

					return EnumActionResult.SUCCESS; }
			}
		}
		
		if (k == 4) {
			if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }
			
			if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(Dish_Blocks.TENGUSA_WASH, pos, false, facing, (Entity)null)) {
				/** Put the Block. **/
				worldIn.setBlockState(pos, Dish_Blocks.TENGUSA_WASH.getDefaultState()
						.withProperty(TengusaWashed.STAGE_1_5, Integer.valueOf(1)), 10);
				CMEvents.ItemBlock_Grass(worldIn, pos, playerIn, hand);

				return EnumActionResult.SUCCESS; }
		}
		return EnumActionResult.FAIL;
	}
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		if (k == 1) { itemTip.add(I18n.format("tips.item_nigari.name")); }
		if (k == 2) { itemTip.add(I18n.format("tips.item_rennet.name")); }
		if (k == 3) { 
			itemTip.add(I18n.format("tips.item_crop_tengusa.name"));
			itemTip.add(I18n.format("tips.item_crop_tengusa2.name")); }
		if (k == 4) { itemTip.add(I18n.format("tips.item_crop_tengusawash.name")); }
	}
}
