package com.ayutaki.chinjufumod.blocks.unitblock;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Unit_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ClothTable_sub extends BaseClothTable {

	public ClothTable_sub(String name) {
		super(name);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		ItemStack hStack = playerIn.getHeldItem(hand);
		
		if (hStack.isEmpty()) {
			if (playerIn.isSneaking()) {
				CMEvents.soundWoodPlace(worldIn, pos);
				worldIn.setBlockState(pos, takeMain().getDefaultState()
						.withProperty(ClothTable.STAGE_0_15, Integer.valueOf(i))); }
			
			else { //!isCrouching
				CMEvents.emptyTakeN_ClothB(worldIn, pos, playerIn, Item.getItemFromBlock(Blocks.CARPET), 1, i);
				worldIn.setBlockState(pos, Unit_Blocks.CAFETABLE_sub.getDefaultState()
						.withProperty(CafeTable_sub.STAGE_0_8, Integer.valueOf(takeMeta()))); }
			
			return true; }
		
		else { return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ); }
	}
	
	private Block takeMain() {
		if (this == Unit_Blocks.CLOTHTABLE_oaksub) { return Unit_Blocks.CLOTHTABLE_oak; }
		if (this == Unit_Blocks.CLOTHTABLE_sprucesub) { return Unit_Blocks.CLOTHTABLE_spruce; }
		if (this == Unit_Blocks.CLOTHTABLE_birchsub) { return Unit_Blocks.CLOTHTABLE_birch; }
		if (this == Unit_Blocks.CLOTHTABLE_junglesub) { return Unit_Blocks.CLOTHTABLE_jungle; }
		if (this == Unit_Blocks.CLOTHTABLE_acaciasub) { return Unit_Blocks.CLOTHTABLE_acacia; }
		if (this == Unit_Blocks.CLOTHTABLE_darkoaksub) { return Unit_Blocks.CLOTHTABLE_darkoak; }
		if (this == Unit_Blocks.CLOTHTABLE_sakurasub) { return Unit_Blocks.CLOTHTABLE_sakura; }
		if (this == Unit_Blocks.CLOTHTABLE_kaedesub) { return Unit_Blocks.CLOTHTABLE_kaede; }
		else { return Unit_Blocks.CLOTHTABLE_ichoh; }
	}
	
	private int takeMeta() {
		if (this == Unit_Blocks.CLOTHTABLE_oaksub) { return 0; }
		if (this == Unit_Blocks.CLOTHTABLE_sprucesub) { return 1; }
		if (this == Unit_Blocks.CLOTHTABLE_birchsub) { return 2; }
		if (this == Unit_Blocks.CLOTHTABLE_junglesub) { return 3; }
		if (this == Unit_Blocks.CLOTHTABLE_acaciasub) { return 4; }
		if (this == Unit_Blocks.CLOTHTABLE_darkoaksub) { return 5; }
		if (this == Unit_Blocks.CLOTHTABLE_sakurasub) { return 6; }
		if (this == Unit_Blocks.CLOTHTABLE_kaedesub) { return 7; }
		else { return 8; }
	}
}
