package com.ayutaki.chinjufumod.items.furniture;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSapo;
import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.addtab.IR_Chinjufu;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class DressingTable_CT extends IR_Chinjufu {

	public DressingTable_CT(String name) {
		super(name);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* Sub item meta and name. */
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 0:
		default:
			return "item." + "block_dressingtable";
		case 1:
			return "item." + "block_dressingtable_s";
		case 2:
			return "item." + "block_dressingtable_b";
		case 3:
			return "item." + "block_dressingtable_j";
		case 4:
			return "item." + "block_dressingtable_a";
		case 5:
			return "item." + "block_dressingtable_d";
		case 6:
			return "item." + "block_dressingtable_saku";
		case 7:
			return "item." + "block_dressingtable_kae";
		case 8:
			return "item." + "block_dressingtable_ich";
		}
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(new ItemStack(this, 1, 0));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
			items.add(new ItemStack(this, 1, 4));
			items.add(new ItemStack(this, 1, 5));
			items.add(new ItemStack(this, 1, 6));
			items.add(new ItemStack(this, 1, 7));
			items.add(new ItemStack(this, 1, 8));
		}
	}
	
	/* Place block */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(worldIn, pos)) { pos = pos.offset(facing); }

		/* 4.0F / 360.0F) + 0.5D -> add 180... 4.0F / 360.0F) + 2.5D */
		int i = MathHelper.floor((double)(playerIn.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		EnumFacing direction = EnumFacing.getHorizontal(i);
		ItemStack hStack = playerIn.getHeldItem(hand);
		int k = hStack.getMetadata();
		
		if (!hStack.isEmpty() && playerIn.canPlayerEdit(pos, facing, hStack) && worldIn.mayPlace(int2Block(k), pos, false, facing, (Entity)null)) {

			/** Put the Block. **/
			worldIn.setBlockState(pos, this.int2Block(k).getDefaultState().withProperty(BaseFacingSapo.H_FACING, direction), 10);
			worldIn.setBlockState(pos.up(), this.getBlockState2(k).withProperty(BaseStage3_Face.H_FACING, direction), 10);

			CMEvents.ItemBlock_Wood(worldIn, pos, playerIn, hand);
			return EnumActionResult.SUCCESS;
		}

		else { return EnumActionResult.FAIL; }
	}

	private Block int2Block(int k) {
		if (k == 0) { return Furniture_Blocks.DRESSINGTABLE; }
		if (k == 1) { return Furniture_Blocks.DRESSINGTABLE_s; }
		if (k == 2) { return Furniture_Blocks.DRESSINGTABLE_b; }
		if (k == 3) { return Furniture_Blocks.DRESSINGTABLE_j; }
		if (k == 4) { return Furniture_Blocks.DRESSINGTABLE_a; }
		if (k == 5) { return Furniture_Blocks.DRESSINGTABLE_d; }
		if (k == 6) { return Furniture_Blocks.DRESSINGTABLE_saku; }
		if (k == 7) { return Furniture_Blocks.DRESSINGTABLE_kae; }
		if (k == 8) { return Furniture_Blocks.DRESSINGTABLE_ich; }
		return null;
	}
	
	private IBlockState getBlockState2(int k) {
		if (k == 0) { return Furniture_Blocks.DRESSINGTABLE_TOP1.getDefaultState().withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(1)); }
		if (k == 1) { return Furniture_Blocks.DRESSINGTABLE_TOP1.getDefaultState().withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(2)); }
		if (k == 2) { return Furniture_Blocks.DRESSINGTABLE_TOP1.getDefaultState().withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3)); }
		if (k == 3) { return Furniture_Blocks.DRESSINGTABLE_TOP2.getDefaultState().withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(1)); }
		if (k == 4) { return Furniture_Blocks.DRESSINGTABLE_TOP2.getDefaultState().withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(2)); }
		if (k == 5) { return Furniture_Blocks.DRESSINGTABLE_TOP2.getDefaultState().withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3)); }
		if (k == 6) { return Furniture_Blocks.DRESSINGTABLE_TOP3.getDefaultState().withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(1)); }
		if (k == 7) { return Furniture_Blocks.DRESSINGTABLE_TOP3.getDefaultState().withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(2)); }
		if (k == 8) { return Furniture_Blocks.DRESSINGTABLE_TOP3.getDefaultState().withProperty(BaseStage3_Face.STAGE_1_3, Integer.valueOf(3)); }
		return null;
	}
}
