package com.ayutaki.chinjufumod.blocks.crop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.blocks.base.Regi_addState;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Crop_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
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

public class PepperVanilla extends Regi_addState {
	/* Property 0 1 2 3 4. 5 6 (7), top 8 9 10 11 12. 13 14 (15) */
	public static final PropertyInteger STAGE_0_15 = PropertyInteger.create("stage", 0, 15);

	protected static final double cw = 0.0625;
	private static final AxisAlignedBB PBOT_0 = new AxisAlignedBB(4.0D * cw, 0.0D * cw, 4.0D * cw, 12.0D * cw, 6.0D * cw, 12.0D * cw);
	private static final AxisAlignedBB PBOT_1 = new AxisAlignedBB(4.0D * cw, 0.0D * cw, 4.0D * cw, 12.0D * cw, 10.5D * cw, 12.0D * cw);
	private static final AxisAlignedBB PBOT_2 = new AxisAlignedBB(3.5D * cw, 0.0D * cw, 3.5D * cw, 12.5D * cw, 15.0D * cw, 12.5D * cw);
	private static final AxisAlignedBB PBOT_3 = new AxisAlignedBB(3.0D * cw, 0.0D * cw, 3.0D * cw, 13.0D * cw, 16.0D * cw, 13.0D * cw);

	private static final AxisAlignedBB VBOT_0 = new AxisAlignedBB(3.0D * cw, 0.0D * cw, 3.0D * cw, 13.0D * cw, 6.0D * cw, 13.0D * cw);
	private static final AxisAlignedBB VBOT_1 = new AxisAlignedBB(3.0D * cw, 0.0D * cw, 3.0D * cw, 13.0D * cw, 10.0D * cw, 13.0D * cw);
	private static final AxisAlignedBB VBOT_2 = new AxisAlignedBB(3.0D * cw, 0.0D * cw, 3.0D * cw, 13.0D * cw, 14.0D * cw, 13.0D * cw);
	private static final AxisAlignedBB VBOT_3 = new AxisAlignedBB(2.5D * cw, 0.0D * cw, 2.5D * cw, 13.5D * cw, 16.0D * cw, 13.5D * cw);

	private static final AxisAlignedBB BOT_7 = new AxisAlignedBB(2.0D * cw, 0.0D * cw, 2.0D * cw, 14.0D * cw, 16.0D * cw, 14.0D * cw);
	private static final AxisAlignedBB TOP_0 = new AxisAlignedBB(7.5D * cw, 0.0D * cw, 7.5D * cw, 8.5D * cw, 10.0D * cw, 8.5D * cw);
	private static final AxisAlignedBB TOP_3 = new AxisAlignedBB(4.0D * cw, 0.0D * cw, 4.0D * cw, 12.0D * cw, 4.0D * cw, 12.0D * cw);
	private static final AxisAlignedBB TOP_7 = new AxisAlignedBB(3.0D * cw, 0.0D * cw, 3.0D * cw, 13.0D * cw, 8.0D * cw, 13.0D * cw);
	
	public PepperVanilla(String name) {
		super(name);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(0);

		setTickRandomly(true);

		setDefaultState(this.blockState.getBaseState().withProperty(STAGE_0_15, Integer.valueOf(0)));
	}

	/* RandomTick */
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		if (!worldIn.isAreaLoaded(pos, 1)) { return; }
		
		if (i <= 7) {
			IBlockState upState = worldIn.getBlockState(pos.up());
			int h = ((Integer)upState.getValue(STAGE_0_15)).intValue();
			
			float temp = worldIn.getBiome(pos).getTemperature(pos);
			
			if (temp >= 0.5F) {
				if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
					
					if (rand.nextInt(8) == 0) {
						if (i != (h - 8)) { 
							worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(i + 8))); }
						
						if (i <= 6) {
							worldIn.setBlockState(pos, this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(i + 1)));
							worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(h + 1))); } }
				} //Ligh
			} //>= 0.5F
			
			else { //< 0.5F
				if (rand.nextInt(1) == 0) {
					worldIn.destroyBlock(pos, true);
					worldIn.destroyBlock(pos.up(), false); }
			}
		}
		
		else { }
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		/* Property 0 1 2 3 4. 5 6 (7), top 8 9 10 11 12. 13 14 (15) */
		
		/** Too early to collect **/
		if (i <= 6) {
			if ((i <= 5) && hItem == Items.DYE && k == 15) {
				CMEvents.BoneMeal_Particle(worldIn, pos, playerIn, hand);
								
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(i + 2)));
				worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(i + 10))); }
			
			if ((hItem == Items.DYE && k != 15) || hItem != Items.DYE) {
				if (hStack.isEmpty()) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
				
				else { //!empty
					CMEvents.textNotHave(worldIn, pos, playerIn); }
			}
		}
		
		if (i == 7) {
			if (hStack.isEmpty()) {
				CMEvents.emptyTakeN_SnowB(worldIn, pos, playerIn, Items_Teatime.SPICE, 4, this.takeMeta());
			
				worldIn.setBlockState(pos, state.withProperty(STAGE_0_15, Integer.valueOf(4)));
				worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(12))); }
			
			else { //!empty
				CMEvents.textFullItem(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	private int takeMeta() {
		if (this == Crop_Blocks.VANILLA) { return 8; }
		else { return 0; }
	}


	/* Change DownBlock. */
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block neighborBlock, BlockPos fromPos) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		/* Property 0 1 2 3 4. 5 6 (7), top 8 9 10 11 12. 13 14 (15) */
		Block downBlock = worldIn.getBlockState(pos.down()).getBlock();
		Block upBlock = worldIn.getBlockState(pos.up()).getBlock();
		
		if (i <= 7) {
			boolean DIRT = (worldIn.getBlockState(pos.down()).getBlock() instanceof BlockDirt);
			if (!DIRT || upBlock != this) {
				this.dropBlockAsItem(worldIn, pos, state, 0);
				worldIn.setBlockToAir(pos); }
			
			else { }
		}

		else {
			if (downBlock != this) {
				this.dropBlockAsItem(worldIn, pos, state, 0);
				worldIn.setBlockToAir(pos); }
			else { }
		}
	}

	/* A place where you can put it. */
	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getMaterial().isReplaceable() && worldIn.getBlockState(pos.up()).getMaterial().isReplaceable();
	}

	/* Collision */
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		boolean PEPPER = (this == Crop_Blocks.PEPPER);
		
		switch (i) {
		case 0 :
		default : return (PEPPER)? PBOT_0 : VBOT_0;
		case 1 : return (PEPPER)? PBOT_1 : VBOT_1;
		case 2 : return (PEPPER)? PBOT_2 : VBOT_2;
		case 3 : return (PEPPER)? PBOT_3 : VBOT_3;
		case 4 :
		case 5 :
		case 6 :
		case 7 : return BOT_7;
		case 8 :
		case 9 :
		case 10 : return TOP_0;
		case 11 : return TOP_3;
		case 12 :
		case 13 :
		case 14 :
		case 15 : return TOP_7;
		} // // STAGE0_15
	}

	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}
	
	/* Data value */
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STAGE_0_15, Integer.valueOf(meta));
	}

	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE_0_15)).intValue();
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE_0_15 });
	}

	/* Rendering */
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	/* A block that breaks at the same time when it is broken. */
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		if (playerIn.capabilities.isCreativeMode && i >= 8 && worldIn.getBlockState(pos.down()).getBlock() == this) {
			worldIn.setBlockToAir(pos.down()); }

		if (i <= 7 && worldIn.getBlockState(pos.up()).getBlock() == this) {
			if (playerIn.capabilities.isCreativeMode) { worldIn.setBlockToAir(pos); }
			worldIn.setBlockToAir(pos.up()); }
	}
	
	/* The best harvesting tool. */
	@Override
	public String getHarvestTool(IBlockState state) {
		return "axe";
	}
	
	/*Drop Item and Clone Item.*/
	public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		return false;
	}

	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		/* Property 0 1 2 3 4. 5 6 (7), top 8 9 10 11 12. 13 14 (15) */
		int i = ((Integer)state.getValue(STAGE_0_15)).intValue();
		boolean grow = (i == 6 || i == 7);
		
		if (i <= 7) { stack.add(new ItemStack(Items_Teatime.SPICE_NAE, (grow)? 3 : 1, this.cloneMeta())); }
		if (i > 7) { stack.add(new ItemStack(Items.AIR, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return new ItemStack(Items_Teatime.SPICE_NAE, 1, this.cloneMeta());
	}

	private int cloneMeta() {
		if (this == Crop_Blocks.VANILLA) { return 4; }
		else { return 0; }
	}

	/* Do not connect to a Fence. */
	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}
}
