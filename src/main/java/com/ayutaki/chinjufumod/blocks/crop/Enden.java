package com.ayutaki.chinjufumod.blocks.crop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.Regi_Name;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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

public class Enden extends Regi_Name {
	/* Property */
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool WEST = PropertyBool.create("west");
	public static final PropertyBool EAST = PropertyBool.create("east");
	/** 1=Water Filled, 2, 3, 4, 5=Salt_1, 6=Salt_1, 7=Salt_2, 8=Salt_2, 9=Salt_3 **/
	public static final PropertyInteger STAGE_1_9 = PropertyInteger.create("wet", 1, 9);

	private static final AxisAlignedBB AABB_1 = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.90625D, 1.0D);
	private static final AxisAlignedBB AABB_9 = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

	public Enden(String name) {
		super(name, Material.SAND);
		setCreativeTab(ChinjufuModTabs.TEATIME);
		
		setSoundType(SoundType.SAND);
		setHardness(2.0F);
		setResistance(10.0F);
		setLightOpacity(3);

		setDefaultState(this.blockState.getBaseState()
				.withProperty(SOUTH, false)
				.withProperty(NORTH, false)
				.withProperty(WEST, false)
				.withProperty(EAST, false)
				.withProperty(STAGE_1_9, Integer.valueOf(1)));
		
		setTickRandomly(true);
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.SNOW;
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		/** 1=Water Filled, 2, 3, 4, 5=Salt_1, 6=Salt_1, 7=Salt_2, 8=Salt_2, 9=Salt_3 **/
		int i = ((Integer)state.getValue(STAGE_1_9)).intValue();
		ItemStack hStack = playerIn.getHeldItem(hand);

		/** Too early to collect **/
		if (i < 5) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		/** Can harvest **/
		if (i >= 5) {
			if (hStack.isEmpty()) {
				if (i == 5 || i == 6) { 
					CMEvents.emptyTake1_SnowB(worldIn, pos, playerIn, Items_Teatime.SHIO, 0); }
				if (i == 7 || i == 8) { 
					CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.SHIO, 2, 0); }
				if (i == 9) {
					CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.SHIO, 3, 0);
					ItemStack take = new ItemStack(Items_Teatime.SHIO, 1, 1);
					if (!playerIn.inventory.addItemStackToInventory(take)) { playerIn.dropItem(take, false); } }
				
				worldIn.setBlockState(pos, Crop_Blocks.ENDEN_k.getDefaultState()); }

			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
	 	}
		/** 'true' to not put anything on top. **/
		return true;
	}


	/* Connect the blocks. */
	private boolean canConnectTo(IBlockAccess worldIn, BlockPos pos, EnumFacing facing) {
		IBlockState state = worldIn.getBlockState(pos.offset(facing));
		return state.getBlock() == this;
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		boolean south = canConnectTo(worldIn, pos, EnumFacing.SOUTH);
		boolean north = canConnectTo(worldIn, pos, EnumFacing.NORTH);
		boolean west = canConnectTo(worldIn, pos, EnumFacing.WEST);
		boolean east = canConnectTo(worldIn, pos, EnumFacing.EAST);
		return state.withProperty(SOUTH, south).withProperty(NORTH, north).withProperty(WEST, west).withProperty(EAST, east);
	}


	/* TickRandomと変化条件 */
	public static void turnToSand(World worldIn, BlockPos pos) {
		worldIn.setBlockState(pos, Blocks.SAND.getDefaultState());
	}

	private boolean upAir(World worldIn, BlockPos pos) {
		IBlockState upState = worldIn.getBlockState(pos.up());
		Block upBlock = upState.getBlock();
		return (upBlock == Blocks.AIR);
	}

	/* RandomTick */
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, 2);
	}
	
	@SuppressWarnings("deprecation")
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
		worldIn.scheduleUpdate(pos, this, 2);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }
		
		if (!upAir(worldIn, pos)) {
			worldIn.scheduleUpdate(pos, this, 2);
			turnToSand(worldIn, pos); }
		
		if (!worldIn.canBlockSeeSky(pos.up())) { }

		if (worldIn.canBlockSeeSky(pos.up())) {
			/** 1=Water Filled, 2, 3, 4, 5=Salt_1, 6=Salt_1, 7=Salt_2, 8=Salt_2, 9=Salt_3 **/
			int i = ((Integer)state.getValue(STAGE_1_9)).intValue();

			if (worldIn.isRainingAt(pos.up())) {
				if (i == 1) { }
				else { //i != 1
					if (rand.nextInt(1) == 0) {
						worldIn.setBlockState(pos, state.withProperty(STAGE_1_9, Integer.valueOf(i - 1)), 2); } }
			}
			
			else {
				/** RawBrightness is >= 14. **/
				boolean canDRY = worldIn.isDaytime() && worldIn.getLightFromNeighbors(pos.up()) >= 14 && i != 9;
				if (canDRY) {
					if (rand.nextInt(6) == 0) {
						worldIn.setBlockState(pos, state.withProperty(STAGE_1_9, Integer.valueOf(i + 1)), 3); }
				}

				else { }
			}
		}
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_1_9)).intValue();
		return (i == 9)? AABB_9 : AABB_1;
	}

	/* Data value */
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_1_9, Integer.valueOf(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_1_9)).intValue();
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (9 - ((Integer)state.getValue(STAGE_1_9)).intValue()) * 2;
	}

	@Override
	public boolean isSideSolid(IBlockState baseState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		if (side == EnumFacing.UP) { return true; }
		return false;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { SOUTH, NORTH, WEST, EAST, STAGE_1_9 });
	}

	/* モンスターをSpawnするようにする */
	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess worldIn, BlockPos pos, net.minecraft.entity.EntityLiving.SpawnPlacementType type) {
		return false;
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

	/* A torch can be placed on top. true or false */
	@Override
	public boolean isTopSolid(IBlockState state) {
		return true;
	}

	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "shovel";
	}

	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		stack.add(new ItemStack(Item.getItemFromBlock(Blocks.SAND)));
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Item.getItemFromBlock(Blocks.SAND));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> blockTip, ITooltipFlag advanced) {
		int k = stack.getMetadata();
		blockTip.add(I18n.format("tips.block_enden.name",k));
	}
}
