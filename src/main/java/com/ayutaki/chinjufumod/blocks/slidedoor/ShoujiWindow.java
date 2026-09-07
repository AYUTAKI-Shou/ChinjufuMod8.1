package com.ayutaki.chinjufumod.blocks.slidedoor;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.items.window.CurtainTall_CT;
import com.ayutaki.chinjufumod.items.window.Curtain_CT;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
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

public class ShoujiWindow extends BaseStage3_Face {
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.40625, 0.0, 0.0, 0.59375, 1.0, 1.0);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.40625, 0.0, 0.0, 0.59375, 1.0, 1.0);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.40625, 0.0, 0.0, 0.59375, 1.0, 1.0);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.40625, 0.0, 0.0, 0.59375, 1.0, 1.0);

	private static final AxisAlignedBB AABB_OL_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.40625, 0.0, 0.46875, 0.59375, 1.0, 1.0);
	private static final AxisAlignedBB AABB_OL_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.40625, 0.0, 0.46875, 0.59375, 1.0, 1.0);
	private static final AxisAlignedBB AABB_OL_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.40625, 0.0, 0.46875, 0.59375, 1.0, 1.0);
	private static final AxisAlignedBB AABB_OL_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.40625, 0.0, 0.46875, 0.59375, 1.0, 1.0);

	private static final AxisAlignedBB AABB_OR_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.40625, 0.0, 0.0, 0.59375, 1.0, 0.53125);
	private static final AxisAlignedBB AABB_OR_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.40625, 0.0, 0.0, 0.59375, 1.0, 0.53125);
	private static final AxisAlignedBB AABB_OR_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.40625, 0.0, 0.0, 0.59375, 1.0, 0.53125);
	private static final AxisAlignedBB AABB_OR_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.40625, 0.0, 0.0, 0.59375, 1.0, 0.53125);

	public ShoujiWindow(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		/** 1=Close, 2=Open Left, 3=Open Right **/
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		EnumFacing direction = state.getValue(H_FACING);
		EnumFacing playerFacing = playerIn.getHorizontalFacing();
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();

		if (hItem instanceof Curtain_CT || hItem instanceof CurtainTall_CT) { return false; }

		else {
			switch (i) {
			case 1 :
			default :
				switch (direction) {
				case NORTH :
				default :
					/* プレイヤーの向きと叩く位置 */
					if ((playerFacing == EnumFacing.NORTH && hitX < 0.5) || (playerFacing == EnumFacing.SOUTH && hitX < 0.5)) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)), 3); }
	
					if ((playerFacing == EnumFacing.NORTH && hitX > 0.5) || (playerFacing == EnumFacing.SOUTH && hitX > 0.5)) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 2)), 3); }
					break;
	
				case SOUTH :
					if ((playerFacing == EnumFacing.NORTH && hitX < 0.5) || (playerFacing == EnumFacing.SOUTH && hitX < 0.5)) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 2)), 3); }
	
					if ((playerFacing == EnumFacing.NORTH && hitX > 0.5) || (playerFacing == EnumFacing.SOUTH && hitX > 0.5)) {
					CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)), 3); }
					break;
	
				case EAST :
					if ((playerFacing == EnumFacing.EAST && hitZ < 0.5) || (playerFacing == EnumFacing.WEST && hitZ < 0.5)) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)), 3); }
	
					if ((playerFacing == EnumFacing.EAST && hitZ > 0.5) || (playerFacing == EnumFacing.WEST && hitZ > 0.5)) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 2)), 3); }
					break;
					
				case WEST :
					if ((playerFacing == EnumFacing.EAST && hitZ < 0.5) || (playerFacing == EnumFacing.WEST && hitZ < 0.5)) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 2)), 3); }
	
					if ((playerFacing == EnumFacing.EAST && hitZ > 0.5) || (playerFacing == EnumFacing.WEST && hitZ > 0.5)) {
						CMEvents.soundFusumaS(worldIn, pos);
						worldIn.setBlockState(pos, state.withProperty(STAGE_1_3, Integer.valueOf(i + 1)), 3); }
					break;
				} /* ブロックの向き */
				break;
		
			case 2 :
				CMEvents.soundFusumaS(worldIn, pos);
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING))
					.withProperty(ShoujiWindow.STAGE_1_3, Integer.valueOf(1)));
				break;
		
			case 3 :
				CMEvents.soundFusumaS(worldIn, pos);
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(H_FACING, state.getValue(H_FACING))
					.withProperty(ShoujiWindow.STAGE_1_3, Integer.valueOf(1)));
				break;
			} // switch
	
			return true;
		}
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		state = state.getActualState(source, pos);
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);

		switch (direction) {
		case NORTH:
		default:
			return (i == 1)? AABB_NORTH : ((i == 2)? AABB_OL_NORTH : AABB_OR_NORTH);
			
		case SOUTH:
			return (i == 1)? AABB_SOUTH : ((i == 2)? AABB_OL_SOUTH : AABB_OR_SOUTH);

		case EAST:
			return (i == 1)? AABB_EAST : ((i == 2)? AABB_OL_EAST : AABB_OR_EAST);

		case WEST:
			return (i == 1)? AABB_WEST : ((i == 2)? AABB_OL_WEST : AABB_OR_WEST);
		}
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean t_f) {
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		EnumFacing direction = state.getValue(H_FACING);

		switch (direction) {
		case NORTH :
		default:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, (i == 1)? AABB_NORTH : ((i == 2)? AABB_OL_EAST : AABB_OR_NORTH));
			break;
			
		case SOUTH:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, (i == 1)? AABB_SOUTH : ((i == 2)? AABB_OL_SOUTH : AABB_OR_SOUTH));
			break;

		case EAST:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, (i == 1)? AABB_EAST : ((i == 2)? AABB_OL_EAST : AABB_OR_EAST));
			break;

		case WEST:
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, (i == 1)? AABB_WEST : ((i == 2)? AABB_OL_WEST : AABB_OR_WEST));
			break;
		}
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

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(cloneItem(state), 1, cloneMeta()));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(state), 1, cloneMeta());
	}
	
	private Item cloneItem(IBlockState state) {
		boolean seasonal = (this == Slidedoor_Blocks.SHOUJIWIN_sakura || this == Slidedoor_Blocks.SHOUJIWINR_sakura || 
				this == Slidedoor_Blocks.SHOUJIWIN_kaede || this == Slidedoor_Blocks.SHOUJIWINR_kaede || 
				this == Slidedoor_Blocks.SHOUJIWIN_ichoh || this == Slidedoor_Blocks.SHOUJIWINR_ichoh);

		if (seasonal) { return Items_Seasonal.SSHOUJIWIN_item; }
		else { return Items_Wadeco.SHOUJIWIN_item; }
	}
	
	private int cloneMeta() {
		if (this == Slidedoor_Blocks.SHOUJIWIN_oak || this == Slidedoor_Blocks.SHOUJIWINR_oak) { return 0; }
		if (this == Slidedoor_Blocks.SHOUJIWIN_spruce || this == Slidedoor_Blocks.SHOUJIWINR_spruce) { return 1; }
		if (this == Slidedoor_Blocks.SHOUJIWIN_birch || this == Slidedoor_Blocks.SHOUJIWINR_birch) { return 2; }
		if (this == Slidedoor_Blocks.SHOUJIWIN_jungle || this == Slidedoor_Blocks.SHOUJIWINR_jungle) { return 3; }
		if (this == Slidedoor_Blocks.SHOUJIWIN_acacia || this == Slidedoor_Blocks.SHOUJIWINR_acacia) { return 4; }
		if (this == Slidedoor_Blocks.SHOUJIWIN_darkoak || this == Slidedoor_Blocks.SHOUJIWINR_darkoak) { return 5; }

		if (this == Slidedoor_Blocks.SHOUJIWIN_sakura || this == Slidedoor_Blocks.SHOUJIWINR_sakura) { return 0; }
		if (this == Slidedoor_Blocks.SHOUJIWIN_kaede || this == Slidedoor_Blocks.SHOUJIWINR_kaede) { return 1; }
		else { return 2; }
	}
}
