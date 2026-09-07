package com.ayutaki.chinjufumod.blocks.jpdeco;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Lamp_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Base_Andon extends BaseStage4_Face {

	public Base_Andon(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(1.0F);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		if (hItem instanceof Base_Hake) { return false; }
		
		else {
			if (hItem == Items.FLINT_AND_STEEL) {
				CMEvents.soundFlint(worldIn, pos);
				
				worldIn.setBlockState(pos, takeBlock().getDefaultState()
						.withProperty(H_FACING, state.getValue(H_FACING))
						.withProperty(STAGE_1_4, Integer.valueOf(i)));
				CMEvents.toolDamege(1, playerIn, hStack); }
	
			if (hItem == Items_Teatime.Item_MATCH) {
				CMEvents.consume1_seFlint(worldIn, pos, playerIn, hand);	
				
				worldIn.setBlockState(pos, takeBlock().getDefaultState()
						.withProperty(H_FACING, state.getValue(H_FACING))
						.withProperty(STAGE_1_4, Integer.valueOf(i))); }
			
			if (hItem != Items.FLINT_AND_STEEL && hItem != Items_Teatime.Item_MATCH) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		
		/** 'true' to not put anything on top. **/
		return true;
	}
	
	protected Block takeBlock() {
		if (this == Lamp_Blocks.ANDON_1) { return Lamp_Blocks.LIT_ANDON_1; }
		if (this == Lamp_Blocks.ANDON_2) { return Lamp_Blocks.LIT_ANDON_2; }
		if (this == Lamp_Blocks.ANDON_3) { return Lamp_Blocks.LIT_ANDON_3; }
		else { return Lamp_Blocks.LIT_ANDON_4; }
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.75D, 0.6875D);
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	/* A torch can be placed on top. true or false */
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
}
