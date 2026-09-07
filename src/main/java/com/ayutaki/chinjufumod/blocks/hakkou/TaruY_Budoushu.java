package com.ayutaki.chinjufumod.blocks.hakkou;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.ChinjufuModTabs;
import com.ayutaki.chinjufumod.blocks.base.Regi_BlockLog;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Hakkou_Blocks;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TaruY_Budoushu extends Regi_BlockLog {

	protected static final int COOK_TIME = 12000;
	/* Property */
	public static final PropertyInteger STAGE_0_2 = PropertyInteger.create("stage", 0, 2);

	/** 0=未発酵, 1=ワイン, 2=熟成ワイン **/
	public TaruY_Budoushu(String name) {
		super(name);
		setCreativeTab(ChinjufuModTabs.TEATIME);
		setHardness(1.0F);
		setResistance(5.0F);
		setLightOpacity(1);
		
		setDefaultState(this.blockState.getBaseState()
				.withProperty(STAGE_0_2, Integer.valueOf(0))
				.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
	}

	/* TickRandom */
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.scheduleUpdate(pos, this, COOK_TIME);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		int i = ((Integer)state.getValue(STAGE_0_2)).intValue();

		if (i <= 1) {
			worldIn.scheduleUpdate(pos, this, COOK_TIME);
			worldIn.setBlockState(pos, state.withProperty(STAGE_0_2, Integer.valueOf(i + 1))); }

		else { }
	}

	/* RightClick Action */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack hStack = playerIn.getHeldItem(hand);
		Item hItem = hStack.getItem();
		int k = hStack.getMetadata();
		int i = ((Integer)state.getValue(STAGE_0_2)).intValue();
		/** 0=未発酵, 1=ワイン, 2=熟成ワイン **/
		
		/** Too early to collect **/
		if (i == 0) { CMEvents.textEarlyCollect(worldIn, pos, playerIn); }
		
		if (i == 1 || i == 2) {
			if (hItem == Items_Teatime.Item_DISH && k == 8) {
				/** Collect with an Item **/
				CMEvents.consumeN_Hand(1, playerIn, hand);
				CMEvents.takeSAKEBottle_seFill(worldIn, pos, playerIn, hand, this.takeBottle(state), 0);
				CMEvents.addEXP(1, worldIn, pos, playerIn);
				
				worldIn.setBlockState(pos, Hakkou_Blocks.RINGOSHU_TARU.getDefaultState()
						.withProperty(TaruY_Ringoshu.LOG_AXIS, state.getValue(LOG_AXIS))
						.withProperty(TaruY_Ringoshu.STAGE_0_3, Integer.valueOf(3))); }
			
			if (hItem != Items_Teatime.Item_DISH || k != 8) { CMEvents.textNotHave(worldIn, pos, playerIn); }
		}
		/** 'true' to not put anything on top. **/
		return true;
	}

	private Item takeBottle(IBlockState state) {
		int i = ((Integer)state.getValue(STAGE_0_2)).intValue();
		
		if (i == 1) { return Items_Teatime.WINEBOT; }
		else { return Items_Teatime.JUKUWINEBOT; }
	}

	/* Data value */
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState();

		switch (meta) {
			case 0:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y).withProperty(STAGE_0_2, 0);
				break;
			case 1:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X).withProperty(STAGE_0_2, 0);
				break;
			case 2:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z).withProperty(STAGE_0_2, 0);
				break;
			case 3:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y).withProperty(STAGE_0_2, 1);
				break;
			case 4:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X).withProperty(STAGE_0_2, 1);
				break;
			case 5:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z).withProperty(STAGE_0_2, 1);
				break;
			case 6:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y).withProperty(STAGE_0_2, 2);
				break;
			case 7:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X).withProperty(STAGE_0_2, 2);
				break;
			case 8:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z).withProperty(STAGE_0_2, 2);
				break;
			default:
				state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE).withProperty(STAGE_0_2, 0);
		}
		return state;
	}

	@SuppressWarnings("incomplete-switch")
	public int getMetaFromState(IBlockState state) {
		int i = 0;

		switch ((BlockLog.EnumAxis)state.getValue(LOG_AXIS)) {
			case X:
				i |= 0;
				break;
			case Z:
				i |= 1;
				break;
			case Y:
				i |= 2;
		}

		int j = state.getValue(STAGE_0_2);
		i += j * 3;
		return i;
	}

	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LOG_AXIS, STAGE_0_2 });
	}

	/*Drop Item and Clone Item.*/
	@Override
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> stack = new ArrayList<ItemStack>();
		/** 0=未発酵, 1=ワイン, 2=熟成ワイン **/
		int i = ((Integer)state.getValue(STAGE_0_2)).intValue();

		if (i == 0) { stack.add(cloneStack()); }
		else {
			stack.add(new ItemStack(Items_Teatime.FOOD_GRAPE, 7, 0));
			stack.add(new ItemStack(Items_Teatime.HAKKOUTARU, 1, 0)); }
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer playerIn) {
		return cloneStack();
	}
	
	private ItemStack cloneStack() {
		return new ItemStack(Items_Teatime.BUDOUSHU_TARU, 1, 0);
	}

	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (2 - ((Integer)state.getValue(STAGE_0_2)).intValue()) * 2;
	}

	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> blockTip, ITooltipFlag advanced) {
		blockTip.add(I18n.format("tips.block_taru_budoushu.name"));
	}
}
