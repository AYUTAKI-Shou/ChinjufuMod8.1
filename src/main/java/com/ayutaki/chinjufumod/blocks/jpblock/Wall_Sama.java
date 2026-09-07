package com.ayutaki.chinjufumod.blocks.jpblock;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.color.Base_Hake;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.JPBlock_Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Wall_Sama extends BaseStage4_Face {
	/* BoundingBox *//* Collision */
	private static final AxisAlignedBB AABB_NS = new AxisAlignedBB(0.0D, 0.0D, 0.3125D, 1.0D, 1.0D, 0.6875D);
	private static final AxisAlignedBB AABB_EW = new AxisAlignedBB(0.3125D, 0.0D, 0.0D, 0.6875D, 1.0D, 1.0D);
	
	public Wall_Sama(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.WABLOCK);

		setSoundType(SoundType.STONE);
		setHardness(2.0F);
		setResistance(10.0F);
		setLightOpacity(1);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_white) { return MapColor.SNOW; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_orange) { return MapColor.ADOBE; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_magenta) { return MapColor.MAGENTA; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_lightb) { return MapColor.LIGHT_BLUE; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_yellow) { return MapColor.YELLOW; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_lime) { return MapColor.LIME; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_pink) { return MapColor.PINK; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_gray) { return MapColor.GRAY; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_lightg) { return MapColor.SILVER; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_cyan) { return MapColor.CYAN; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_purple) { return MapColor.PURPLE; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_blue) { return MapColor.BLUE; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_brown) { return MapColor.BROWN; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_green) { return MapColor.GREEN; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_red) { return MapColor.RED; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_black) { return MapColor.BLACK; }
		else { return MapColor.ADOBE; }
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing direction = state.getValue(H_FACING);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		switch (direction) {
		case NORTH :
		default : return (i == 1 || i == 2)? AABB_NS : FULL_BLOCK_AABB;
		case SOUTH : return (i == 1 || i == 2)? AABB_NS : FULL_BLOCK_AABB;
		case EAST : return (i == 1 || i == 2)? AABB_EW : FULL_BLOCK_AABB;	
		case WEST : return (i == 1 || i == 2)? AABB_EW : FULL_BLOCK_AABB;
		} // switch
	}
	
	@Override 
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean t_f) {
		EnumFacing direction = state.getValue(H_FACING);
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		
		if (i == 1 || i == 2) {
			switch (direction) {
			case NORTH :
			default :
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NS);
			case SOUTH :
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_NS);
			case EAST :
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_EW);
			case WEST :
				addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB_EW);
			} // switch
		} //i == 1 || i == 2
		
		else {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, FULL_BLOCK_AABB);
		}
	}
	
	/* Arrow pass. */
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return NULL_AABB;
	}
	
	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();

		boolean block2Item = ((this == JPBlock_Blocks.DIRTWALL_SAMA && hItem == Items_Wablock.DIRTWALL_SAMA) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_white && hItem == Items_Wablock.SHIKKUI_SAMA_white) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_orange && hItem == Items_Wablock.SHIKKUI_SAMA_orange) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_magenta && hItem == Items_Wablock.SHIKKUI_SAMA_magenta) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_yellow && hItem == Items_Wablock.SHIKKUI_SAMA_yellow) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_lime && hItem == Items_Wablock.SHIKKUI_SAMA_lime) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_pink && hItem == Items_Wablock.SHIKKUI_SAMA_pink) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_gray && hItem == Items_Wablock.SHIKKUI_SAMA_gray) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_lightb && hItem == Items_Wablock.SHIKKUI_SAMA_lightb) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_lightg && hItem == Items_Wablock.SHIKKUI_SAMA_lightg) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_cyan && hItem == Items_Wablock.SHIKKUI_SAMA_cyan) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_purple && hItem == Items_Wablock.SHIKKUI_SAMA_purple) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_blue && hItem == Items_Wablock.SHIKKUI_SAMA_blue) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_brown && hItem == Items_Wablock.SHIKKUI_SAMA_brown) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_green && hItem == Items_Wablock.SHIKKUI_SAMA_green) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_red && hItem == Items_Wablock.SHIKKUI_SAMA_red) ||
				(this == JPBlock_Blocks.SHIKKUI_SAMA_black && hItem == Items_Wablock.SHIKKUI_SAMA_black));
				
		if (hItem instanceof Base_Hake) { return false; }
		
		if (block2Item) {
			if (i == 1 || i == 2) {
				CMEvents.consume1_seStoneP(worldIn, pos, playerIn, hand);
				worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 2)), 10);
				return true; }
		}
			
		else {
			if (hStack.isEmpty() && playerIn.isSneaking()) {
				CMEvents.soundStonePlace(worldIn, pos);
				switch (i) {
				case 1 :
				default :
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 10);
					break;

				case 2 :
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i - 1)), 10);
					break;

				case 3 :
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i + 1)), 10);
					break;
					
				case 4 :
					worldIn.setBlockState(pos, state.withProperty(STAGE_1_4, Integer.valueOf(i - 1)), 10);
					break;
				} // switch STAGE_1_4
				return true;
			}
		}
		return false;
	}
	
	/* Rendering */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		return (i != 1 && i != 3)? true : false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		return (i != 1 && i != 3)? true : false;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}

	/*Drop Item and Clone Item.*/
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		int i = ((Integer)state.getValue(STAGE_1_4)).intValue();
		int w = (i == 3 || i == 4)? 2 : 1;
		
		stack.add(new ItemStack(cloneItem(), w, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem());
	}

	private Item cloneItem() {
		if (this == JPBlock_Blocks.DIRTWALL_SAMA) { return Items_Wablock.DIRTWALL_SAMA; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_white) { return Items_Wablock.SHIKKUI_SAMA_white; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_orange) { return Items_Wablock.SHIKKUI_SAMA_orange; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_magenta) { return Items_Wablock.SHIKKUI_SAMA_magenta; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_lightb) { return Items_Wablock.SHIKKUI_SAMA_lightb; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_yellow) { return Items_Wablock.SHIKKUI_SAMA_yellow; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_lime) { return Items_Wablock.SHIKKUI_SAMA_lime; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_pink) { return Items_Wablock.SHIKKUI_SAMA_pink; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_gray) { return Items_Wablock.SHIKKUI_SAMA_gray; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_lightg) { return Items_Wablock.SHIKKUI_SAMA_lightg; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_cyan) { return Items_Wablock.SHIKKUI_SAMA_cyan; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_purple) { return Items_Wablock.SHIKKUI_SAMA_purple; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_blue) { return Items_Wablock.SHIKKUI_SAMA_blue; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_brown) { return Items_Wablock.SHIKKUI_SAMA_brown; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_green) { return Items_Wablock.SHIKKUI_SAMA_green; }
		if (this == JPBlock_Blocks.SHIKKUI_SAMA_red) { return Items_Wablock.SHIKKUI_SAMA_red; }
		else { return Items_Wablock.SHIKKUI_SAMA_black; }
	}
	
	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> blockTip, ITooltipFlag advanced) {
		blockTip.add(I18n.format("tips.block_sama.name"));
	}
}
