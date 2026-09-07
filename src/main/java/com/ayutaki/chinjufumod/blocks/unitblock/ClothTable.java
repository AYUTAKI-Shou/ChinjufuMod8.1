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

public class ClothTable extends BaseClothTable {

	public ClothTable(String name) {
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
				worldIn.setBlockState(pos, takeSub().getDefaultState()
						.withProperty(ClothTable.STAGE_0_15, Integer.valueOf(i))); }
			
			else { //!isCrouching
				CMEvents.emptyTakeN_ClothB(worldIn, pos, playerIn, Item.getItemFromBlock(Blocks.CARPET), 1, i);
				worldIn.setBlockState(pos, Unit_Blocks.CAFETABLE.getDefaultState()
						.withProperty(CafeTable.STAGE_0_8, Integer.valueOf(takeMeta()))); }
			
			return true; }
		
		else { return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ); }
	}
	
	private Block takeSub() {
		if (this == Unit_Blocks.CLOTHTABLE_oak) { return Unit_Blocks.CLOTHTABLE_oaksub; }
		if (this == Unit_Blocks.CLOTHTABLE_spruce) { return Unit_Blocks.CLOTHTABLE_sprucesub; }
		if (this == Unit_Blocks.CLOTHTABLE_birch) { return Unit_Blocks.CLOTHTABLE_birchsub; }
		if (this == Unit_Blocks.CLOTHTABLE_jungle) { return Unit_Blocks.CLOTHTABLE_junglesub; }
		if (this == Unit_Blocks.CLOTHTABLE_acacia) { return Unit_Blocks.CLOTHTABLE_acaciasub; }
		if (this == Unit_Blocks.CLOTHTABLE_darkoak) { return Unit_Blocks.CLOTHTABLE_darkoaksub; }
		if (this == Unit_Blocks.CLOTHTABLE_sakura) { return Unit_Blocks.CLOTHTABLE_sakurasub; }
		if (this == Unit_Blocks.CLOTHTABLE_kaede) { return Unit_Blocks.CLOTHTABLE_kaedesub; }
		else { return Unit_Blocks.CLOTHTABLE_ichohsub; }
	}
	
	private int takeMeta() {
		if (this == Unit_Blocks.CLOTHTABLE_oak) { return 0; }
		if (this == Unit_Blocks.CLOTHTABLE_spruce) { return 1; }
		if (this == Unit_Blocks.CLOTHTABLE_birch) { return 2; }
		if (this == Unit_Blocks.CLOTHTABLE_jungle) { return 3; }
		if (this == Unit_Blocks.CLOTHTABLE_acacia) { return 4; }
		if (this == Unit_Blocks.CLOTHTABLE_darkoak) { return 5; }
		if (this == Unit_Blocks.CLOTHTABLE_sakura) { return 6; }
		if (this == Unit_Blocks.CLOTHTABLE_kaede) { return 7; }
		else { return 8; }
	}
}
