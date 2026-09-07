package com.ayutaki.chinjufumod.blocks.school;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage3_Face;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_FaceDown;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.blocks.furniture.DeskBook2;
import com.ayutaki.chinjufumod.blocks.furniture.DeskCloth;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.School_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class TeacherDesk extends BaseStage3_Face {
	/* Collision */
	private static final AxisAlignedBB SOUTH_R = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.0, 1.0, 1.0, 0.625);
	private static final AxisAlignedBB EAST_R = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.0, 1.0, 1.0, 0.625);
	private static final AxisAlignedBB WEST_R = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.0, 1.0, 1.0, 0.625);
	private static final AxisAlignedBB NORTH_R = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.0, 1.0, 1.0, 0.625);
	private static final AxisAlignedBB AABB_C = new AxisAlignedBB(0.0D, 0.6875D, 0.0D, 1.0D, 1.0D, 1.0D);
	private static final AxisAlignedBB SOUTH_L = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, 0.0, 0.375, 1.0, 1.0, 1.0);
	private static final AxisAlignedBB EAST_L = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, 0.0, 0.375, 1.0, 1.0, 1.0);
	private static final AxisAlignedBB WEST_L = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, 0.0, 0.375, 1.0, 1.0, 1.0);
	private static final AxisAlignedBB NORTH_L = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, 0.0, 0.375, 1.0, 1.0, 1.0);
	
	public TeacherDesk(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		EnumFacing direction = (EnumFacing)state.getValue(H_FACING);
		EnumFacing playerFacing = playerIn.getHorizontalFacing().getOpposite();
		
		IBlockState northState = worldIn.getBlockState(pos.north());
		IBlockState southState = worldIn.getBlockState(pos.south());
		IBlockState eastState = worldIn.getBlockState(pos.east());
		IBlockState westState = worldIn.getBlockState(pos.west());
		IBlockState upState = worldIn.getBlockState(pos.up());
		
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		boolean book = (hItem == Items_Chinjufu.SHOUHOU_empty || hItem == Items.BOOK);
		boolean upAble = upState.getMaterial().isReplaceable();
		
		if (i == 1) {
			boolean success = (hItem == Item.getItemFromBlock(Blocks.CARPET) || book);
			
			if (success) {
				if (book && upAble) {
					CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
					Block bookBlock = (hItem == Items.BOOK)? Chinjufu_Blocks.DESKBOOK_1 : Chinjufu_Blocks.NOTEBOOK;
					
					worldIn.setBlockState(pos.up(), bookBlock.getDefaultState()
							.withProperty(BaseStage4_FaceDown.H_FACING, playerFacing)
							.withProperty(BaseStage4_FaceDown.STAGE_1_4, Integer.valueOf(1))); }
				
				if (hItem == Item.getItemFromBlock(Blocks.CARPET)) {
					int k = hStack.getMetadata();
					IBlockState carpetState = this.int2Block(k).getDefaultState()
							.withProperty(DeskCloth.H_FACING, direction)
							.withProperty(DeskCloth.STAGE_1_4, Integer.valueOf(takeMeta(k)));
					
					switch (direction) {
					case NORTH :
					default:
						if (southState.getMaterial().isReplaceable()) {
							worldIn.setBlockState(pos.south(), carpetState);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }

						else { //!Replaceable
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
				
					case SOUTH :
						if (northState.getMaterial().isReplaceable()) {
							worldIn.setBlockState(pos.north(), carpetState);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!Replaceable
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
				
					case EAST :
						if (westState.getMaterial().isReplaceable()) {
							worldIn.setBlockState(pos.west(), carpetState);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!Replaceable
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
						
					case WEST :
						if (eastState.getMaterial().isReplaceable()) {
							worldIn.setBlockState(pos.east(), carpetState);
							CMEvents.consume1_seCloth(worldIn, pos, playerIn, hand); }
						
						else { //!Replaceable
							CMEvents.textIsBlocked(worldIn, pos, playerIn); }
						break;
					} // switch
				}
				return true;
			}

			else { return false; }
		}

		else {
			if (book && upAble) {
				CMEvents.consume1_seWoodP(worldIn, pos, playerIn, hand);
				Block book2 = (hItem == Items.BOOK)? Chinjufu_Blocks.DESKBOOK_2 : Chinjufu_Blocks.NOTEBOOK_2;
				Block book3 = (hItem == Items.BOOK)? Chinjufu_Blocks.DESKBOOK_3 : Chinjufu_Blocks.NOTEBOOK_3;
				
				Block bookBlock = (i == 2)? book2 : book3;
				worldIn.setBlockState(pos.up(), bookBlock.getDefaultState()
						.withProperty(DeskBook2.H_FACING, direction)
						.withProperty(DeskBook2.STAGE_1_4, Integer.valueOf(1)));
				
				return true; }
			
			else { return false; }
		}
	}
	
	private Block int2Block(int k) {
		if (k <= 3) { return Chinjufu_Blocks.DESKCLOTH_03; }
		if (k >= 4 && k <= 7) { return Chinjufu_Blocks.DESKCLOTH_47; }
		if (k >= 8 && k <= 11) { return Chinjufu_Blocks.DESKCLOTH_811; }
		else { return Chinjufu_Blocks.DESKCLOTH_1215; }
	}
	
	private int takeMeta(int k) {
		if (k == 0 || k == 4 || k == 8 || k == 12) { return 1; }
		if (k == 1 || k == 5 || k == 9 || k == 13) { return 2; }
		if (k == 2 || k == 6 || k == 10 || k == 14) { return 3; }
		else { return 4; }
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
			return (i == 2)? NORTH_R : ((i ==3)? NORTH_L : AABB_C);
			
		case SOUTH:
			return (i == 2)? SOUTH_R : ((i ==3)? SOUTH_L : AABB_C);

		case EAST:
			return (i == 2)? EAST_R : ((i ==3)? EAST_L : AABB_C);

		case WEST:
			return (i == 2)? WEST_R : ((i ==3)? WEST_L : AABB_C);
		}
	}

	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return true;
	}

	/* A torch can be placed on the side. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	/* Rendering */
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/* A block that breaks at the same time when it is broken. */
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		int i = ((Integer)state.getValue(STAGE_1_3)).intValue();
		EnumFacing direction = state.getValue(H_FACING);
		
		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();
		
		if (i == 1) {
			switch (direction) {
			case NORTH :
			default :
				worldIn.destroyBlock(new BlockPos(x - 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x + 1, y, z), false);
				break;

			case SOUTH :
				worldIn.destroyBlock(new BlockPos(x - 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x + 1, y, z), false);
				break;

			case EAST :
				worldIn.destroyBlock(new BlockPos(x, y, z - 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z + 1), false);
				break;
				
			case WEST :
				worldIn.destroyBlock(new BlockPos(x, y, z - 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z + 1), false);
				break;
			} // direction
		}
	
		if (i == 2) {
			switch (direction) {
			case NORTH :
			default :
				worldIn.destroyBlock(new BlockPos(x - 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x - 2, y, z), false);
				break;

			case SOUTH :
				worldIn.destroyBlock(new BlockPos(x + 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x + 2, y, z), false);
				break;

			case EAST :
				worldIn.destroyBlock(new BlockPos(x, y, z - 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z - 2), false);
				break;
				
			case WEST :
				worldIn.destroyBlock(new BlockPos(x, y, z + 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z + 2), false);
				break;
			} // direction
		}
	
		if (i == 3) {
			switch (direction) {
			case NORTH :
			default :
				worldIn.destroyBlock(new BlockPos(x + 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x + 2, y, z), false);
				break;

			case SOUTH :
				worldIn.destroyBlock(new BlockPos(x - 1, y, z), false);
				worldIn.destroyBlock(new BlockPos(x - 2, y, z), false);
				break;

			case EAST :
				worldIn.destroyBlock(new BlockPos(x, y, z + 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z + 2), false);
				break;
				
			case WEST :
				worldIn.destroyBlock(new BlockPos(x, y, z - 1), false);
				worldIn.destroyBlock(new BlockPos(x, y, z - 2), false);
				break;
			} // direction
		}
	}
	
	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Items_Chinjufu.TEACHERDESK_item, 1, cloneMeta()));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Chinjufu.TEACHERDESK_item, 1, cloneMeta());
	}
	
	private int cloneMeta() {
		if (this == School_Blocks.TEACHERDESK) { return 0; }
		if (this == School_Blocks.TEACHERDESK_s) { return 1; }
		if (this == School_Blocks.TEACHERDESK_b) { return 2; }
		if (this == School_Blocks.TEACHERDESK_j) { return 3; }
		if (this == School_Blocks.TEACHERDESK_a) { return 4; }
		if (this == School_Blocks.TEACHERDESK_d) { return 5; }
		if (this == School_Blocks.TEACHERDESK_saku) { return 6; }
		if (this == School_Blocks.TEACHERDESK_kae) { return 7; }
		else { return 8; } 
	}
}
