package com.ayutaki.chinjufumod.blocks.amado;

import java.util.ArrayList;
import java.util.List;

import com.ayutaki.chinjufumod.blocks.base.BaseStage2_Face;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.blocks.base.CollisionHelper;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.Slidedoor_Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
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

public class Tobukuro_BotL2 extends BaseStage2_Face {
	/* Collision */
	private static final AxisAlignedBB AABB_SOUTH = CollisionHelper.getBlockBounds(EnumFacing.SOUTH, 0.0, -0.03125, -0.03125, 0.3125, 1.0, 1.03125);
	private static final AxisAlignedBB AABB_EAST = CollisionHelper.getBlockBounds(EnumFacing.EAST, 0.0, -0.03125, -0.03125, 0.3125, 1.0, 1.03125);
	private static final AxisAlignedBB AABB_WEST = CollisionHelper.getBlockBounds(EnumFacing.WEST, 0.0, -0.03125, -0.03125, 0.3125, 1.0, 1.03125);
	private static final AxisAlignedBB AABB_NORTH = CollisionHelper.getBlockBounds(EnumFacing.NORTH, 0.0, -0.03125, -0.03125, 0.3125, 1.0, 1.03125);
	private static final AxisAlignedBB[] AABB = { AABB_SOUTH, AABB_WEST, AABB_NORTH, AABB_EAST };

	/** 1=1 sheet, 2=Zero **/
	public Tobukuro_BotL2(String name) {
		super(name);
		setHardness(2.0F);
		setResistance(5.0F);
		setSoundType(SoundType.WOOD);
		setLightOpacity(1);
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		/** 1=1 sheet, 2=Zero **/
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();
		EnumFacing direction = state.getValue(H_FACING);

		IBlockState northState = worldIn.getBlockState(pos.north());
		IBlockState southState = worldIn.getBlockState(pos.south());
		IBlockState eastState = worldIn.getBlockState(pos.east());
		IBlockState westState = worldIn.getBlockState(pos.west());

		double x = (double) pos.getX();
		double y = (double) pos.getY();
		double z = (double) pos.getZ();

		if (i == 2) { CMEvents.textIsEmpty(worldIn, pos, playerIn); }
		
		else { //i != 2
			IBlockState AMADO_FACELO_4 = this.takeBot().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING))
					.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(4));
			IBlockState AMADO_FACEUP_4 = this.takeTop().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING))
					.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(4));

			CMEvents.soundAmado(worldIn, pos);
			
			switch (direction) {
			case NORTH :
			default :
				if (eastState.getMaterial().isReplaceable()) {
					worldIn.setBlockState(new BlockPos(x + 1, y, z), AMADO_FACELO_4);
					worldIn.setBlockState(new BlockPos(x + 1, y + 1, z), AMADO_FACEUP_4);
					
					this.updateTOBUKURO_UP(state, worldIn, pos); }
				
				else { } //!Replaceable
				break;

			case SOUTH :
				if (westState.getMaterial().isReplaceable()) {
					worldIn.setBlockState(new BlockPos(x - 1, y, z), AMADO_FACELO_4);
					worldIn.setBlockState(new BlockPos(x - 1, y + 1, z), AMADO_FACEUP_4);
					
					this.updateTOBUKURO_UP(state, worldIn, pos); }
				
				else { } //!Replaceable
				break;

			case EAST :
				if (southState.getMaterial().isReplaceable()) {
					worldIn.setBlockState(new BlockPos(x, y, z + 1), AMADO_FACELO_4);
					worldIn.setBlockState(new BlockPos(x, y + 1, z + 1), AMADO_FACEUP_4);
					
					this.updateTOBUKURO_UP(state, worldIn, pos); }
				
				else { } //!Replaceable
				break;
				
			case WEST :
				if (northState.getMaterial().isReplaceable()) {
					worldIn.setBlockState(new BlockPos(x, y, z - 1), AMADO_FACELO_4);
					worldIn.setBlockState(new BlockPos(x, y + 1, z - 1), AMADO_FACEUP_4);
					
					this.updateTOBUKURO_UP(state, worldIn, pos); }
				
				else { } //!Replaceable
				break;
			} // switch
		} //i != 2
		return true;
	}

	private void updateTOBUKURO_UP(IBlockState state, World worldIn, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_1_2)).intValue();
		IBlockState tobuTopL2_FACE = this.takeLeftTop2().getDefaultState().withProperty(H_FACING, state.getValue(H_FACING));

		CMEvents.soundFusumaL(worldIn, pos);
		worldIn.setBlockState(pos, state.withProperty(STAGE_1_2, Integer.valueOf(i + 1)));
		worldIn.setBlockState(pos.up(), tobuTopL2_FACE.withProperty(STAGE_1_2, Integer.valueOf(i + 1)));
	}
	
	private Block takeLeftTop2() {
		if (this == Slidedoor_Blocks.TOBUKURO_BOTL2) { return Slidedoor_Blocks.TOBUKURO_TOPL2; }
		else { return Slidedoor_Blocks.TOBUKUROS_TOPL2; }
	}
	
	private Block takeTop() {
		if (this == Slidedoor_Blocks.TOBUKURO_BOTL2) { return Slidedoor_Blocks.AMADO_TOP; }
		else { return Slidedoor_Blocks.AMADOS_TOP; }
	}

	private Block takeBot() {
		if (this == Slidedoor_Blocks.TOBUKURO_BOTL2) { return Slidedoor_Blocks.AMADO_BOT; }
		else { return Slidedoor_Blocks.AMADOS_BOT; }
	}
	
	/* A block that breaks at the same time when it is broken. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		if (worldIn.getBlockState(pos.up()).getBlock() instanceof Tobukuro_TopL2) {
			worldIn.destroyBlock(pos.up(), false); }
	}

	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(cloneItem(), 1, 0));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(cloneItem(), 1, 0);
	}
	
	private Item cloneItem() {
		if (this == Slidedoor_Blocks.TOBUKURO_BOTL2) { return Items_Wadeco.TOBUKURO_BOTR; }
		else { return Items_Wadeco.TOBUKUROS_BOTR; }
	}
	
	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = state.getValue(H_FACING);
		return AABB[facing.getHorizontalIndex()];
	}

	/* A torch can be placed on top. true or false */
	public boolean isTopSolid(IBlockState state) {
		return false;
	}

	/* A torch can be placed on the side. */
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}
