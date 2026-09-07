package com.ayutaki.chinjufumod.blocks.unitblock;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
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
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CafeTable_sub extends BaseUnitDesk {

	public CafeTable_sub(String name) {
		super(name);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		boolean carpets = (hItem == Item.getItemFromBlock(Blocks.CARPET));

		if (carpets) {
			CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand);
			
			int k = hStack.getMetadata();			
			worldIn.setBlockState(pos, takeSub(state).getDefaultState()
					.withProperty(ClothTable_sub.STAGE_0_15, Integer.valueOf(k)));
			return true; }
		
		else { return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ); }
	}
	
	private Block takeSub(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_0_8)).intValue();
		
		if (i == 0) { return Unit_Blocks.CLOTHTABLE_oaksub; }
		if (i == 1) { return Unit_Blocks.CLOTHTABLE_sprucesub; }
		if (i == 2) { return Unit_Blocks.CLOTHTABLE_birchsub; }
		if (i == 3) { return Unit_Blocks.CLOTHTABLE_junglesub; }
		if (i == 4) { return Unit_Blocks.CLOTHTABLE_acaciasub; }
		if (i == 5) { return Unit_Blocks.CLOTHTABLE_darkoaksub; }
		if (i == 6) { return Unit_Blocks.CLOTHTABLE_sakurasub; }
		if (i == 7) { return Unit_Blocks.CLOTHTABLE_kaedesub; }
		else { return Unit_Blocks.CLOTHTABLE_ichohsub; }
	}
	
	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return true;
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_0_8)).intValue();
		stack.add(new ItemStack(Items_Chinjufu.CAFETABLE_item, 1, i));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		int i = ((Integer)state.getValue(STAGE_0_8)).intValue();
		return new ItemStack(Items_Chinjufu.CAFETABLE_item, 1, i);
	}
}
