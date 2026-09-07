package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseBlock_SakeBottle extends BaseStage4_FaceDown {
	/* Collision */
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
	private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.3125D, -0.5D, 0.3125D, 0.6875D, 0.5D, 0.6875D);

	protected BaseBlock_SakeBottle(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.TEATIME);

		setSoundType(SoundType.STONE);
		setHardness(1.0F);
		setResistance(1.0F);
		setLightOpacity(0);
	}

	/*Collision*/
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		boolean flag= !((Boolean)state.getValue(DOWN)).booleanValue();
		return flag? AABB : AABB_DOWN;
	}

	/* Rendering */
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
	
	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> blockTip, ITooltipFlag advanced) {
		blockTip.add(I18n.format("tips.block_bot_sake.name"));
	}
}
