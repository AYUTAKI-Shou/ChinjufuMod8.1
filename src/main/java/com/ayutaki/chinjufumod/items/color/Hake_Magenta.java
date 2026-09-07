package com.ayutaki.chinjufumod.items.color;

import com.ayutaki.chinjufumod.blocks.base.BaseFacingSapo;
import com.ayutaki.chinjufumod.blocks.base.BaseFacingSlabW;
import com.ayutaki.chinjufumod.blocks.base.BaseStage2_Face;
import com.ayutaki.chinjufumod.blocks.base.BaseStage4_Face;
import com.ayutaki.chinjufumod.blocks.chair.BaseZabuton;
import com.ayutaki.chinjufumod.blocks.chair.CafeChair;
import com.ayutaki.chinjufumod.blocks.chair.Sofa;
import com.ayutaki.chinjufumod.blocks.chair.Zaisu;
import com.ayutaki.chinjufumod.blocks.furniture.Candle;
import com.ayutaki.chinjufumod.blocks.furniture.DeskCloth;
import com.ayutaki.chinjufumod.blocks.furniture.Lit_Candle;
import com.ayutaki.chinjufumod.blocks.harbor.Truss_Amp;
import com.ayutaki.chinjufumod.blocks.harbor.Truss_Amp2;
import com.ayutaki.chinjufumod.blocks.harbor.Truss_Cable;
import com.ayutaki.chinjufumod.blocks.harbor.Truss_Cable2;
import com.ayutaki.chinjufumod.blocks.jpblock.Base_Slab_JP;
import com.ayutaki.chinjufumod.blocks.jpblock.Base_Full_JP;
import com.ayutaki.chinjufumod.blocks.jpblock.Base_Wall;
import com.ayutaki.chinjufumod.blocks.jpblock.Base_WallKawara;
import com.ayutaki.chinjufumod.blocks.jpblock.Kawara;
import com.ayutaki.chinjufumod.blocks.jpblock.Kawara_Crash;
import com.ayutaki.chinjufumod.blocks.jpblock.Kawara_Slab;
import com.ayutaki.chinjufumod.blocks.jpblock.Kawara_Stairs;
import com.ayutaki.chinjufumod.blocks.jpblock.Namako;
import com.ayutaki.chinjufumod.blocks.jpblock.NamakoB;
import com.ayutaki.chinjufumod.blocks.jpblock.NamakoB_Crash;
import com.ayutaki.chinjufumod.blocks.jpblock.NamakoB_Slab;
import com.ayutaki.chinjufumod.blocks.jpblock.NamakoB_Stairs;
import com.ayutaki.chinjufumod.blocks.jpblock.Namako_Crash;
import com.ayutaki.chinjufumod.blocks.jpblock.Namako_Slab;
import com.ayutaki.chinjufumod.blocks.jpblock.Namako_Stairs;
import com.ayutaki.chinjufumod.blocks.jpblock.Plaster;
import com.ayutaki.chinjufumod.blocks.jpblock.Plaster_Crash;
import com.ayutaki.chinjufumod.blocks.jpblock.Plaster_Slab;
import com.ayutaki.chinjufumod.blocks.jpblock.Plaster_Stairs;
import com.ayutaki.chinjufumod.blocks.jpblock.Wall_Kawara;
import com.ayutaki.chinjufumod.blocks.jpblock.Wall_Namako;
import com.ayutaki.chinjufumod.blocks.jpblock.Wall_NamakoB;
import com.ayutaki.chinjufumod.blocks.jpblock.Wall_NamakoBCrash;
import com.ayutaki.chinjufumod.blocks.jpblock.Wall_NamakoCrash;
import com.ayutaki.chinjufumod.blocks.jpblock.Wall_Plaster;
import com.ayutaki.chinjufumod.blocks.jpblock.Wall_PlasterCrash;
import com.ayutaki.chinjufumod.blocks.jpblock.Wall_Sama;
import com.ayutaki.chinjufumod.blocks.jpdeco.Andon_1;
import com.ayutaki.chinjufumod.blocks.jpdeco.Andon_2;
import com.ayutaki.chinjufumod.blocks.jpdeco.Andon_3;
import com.ayutaki.chinjufumod.blocks.jpdeco.Andon_4;
import com.ayutaki.chinjufumod.blocks.jpdeco.BaseTatamiWood;
import com.ayutaki.chinjufumod.blocks.jpdeco.Futon;
import com.ayutaki.chinjufumod.blocks.jpdeco.Lit_Andon_1;
import com.ayutaki.chinjufumod.blocks.jpdeco.Lit_Andon_2;
import com.ayutaki.chinjufumod.blocks.jpdeco.Lit_Andon_3;
import com.ayutaki.chinjufumod.blocks.jpdeco.Lit_Andon_4;
import com.ayutaki.chinjufumod.blocks.jpdeco.Tatami;
import com.ayutaki.chinjufumod.blocks.jpdeco.Tatami_Y;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Acacia;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Birch;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_DarkOak;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Ichoh;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Jungle;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Kaede;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Oak;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Sakura;
import com.ayutaki.chinjufumod.blocks.kamoislab.KamoiPlaster_Spruce;
import com.ayutaki.chinjufumod.blocks.ranma.Noren;
import com.ayutaki.chinjufumod.blocks.slidedoor.Fusuma;
import com.ayutaki.chinjufumod.blocks.slidedoor.Fusuma_B;
import com.ayutaki.chinjufumod.blocks.unitblock.BaseClothTable;
import com.ayutaki.chinjufumod.blocks.unitblock.Wagasa;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Stage2;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Clay;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_ClayColor;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Glass;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_GlassStained;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Namako;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Namako_B;
import com.ayutaki.chinjufumod.blocks.wallpane.WallPane_Plaster;
import com.ayutaki.chinjufumod.blocks.window.Curtain;
import com.ayutaki.chinjufumod.blocks.window.CurtainTall;
import com.ayutaki.chinjufumod.handler.CMEvents;
import com.ayutaki.chinjufumod.registry.Chinjufu_Blocks;
import com.ayutaki.chinjufumod.registry.Furniture_Blocks;
import com.ayutaki.chinjufumod.registry.Garden_Blocks;
import com.ayutaki.chinjufumod.registry.Harbor_Blocks;
import com.ayutaki.chinjufumod.registry.JPBlock_Blocks;
import com.ayutaki.chinjufumod.registry.JPDeco_Blocks;
import com.ayutaki.chinjufumod.registry.KamoiPlaster_Blocks;
import com.ayutaki.chinjufumod.registry.Lamp_Blocks;
import com.ayutaki.chinjufumod.registry.WallPanel_JPBlocks;
import com.ayutaki.chinjufumod.registry.WallPanel_Blocks;
import com.ayutaki.chinjufumod.registry.Window_Blocks;
import com.ayutaki.chinjufumod.registry.doors.Fusuma_Blocks;
import com.ayutaki.chinjufumod.state.HalfState;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockConcretePowder;
import net.minecraft.block.BlockGlazedTerracotta;
import net.minecraft.block.BlockHardenedClay;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStainedGlassPane;
import net.minecraft.block.BlockStainedHardenedClay;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Hake_Magenta extends Base_Hake {

	public Hake_Magenta(String name) {
		super(name);
	}

	/* FlintAndSteel */
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState stateIn = worldIn.getBlockState(pos);
		ItemStack hStack = playerIn.getHeldItem(hand);
		Block blockIn = stateIn.getBlock();
		
		/** 羊毛, コンクリート **/
		if (blockIn instanceof BlockColored || blockIn instanceof BlockConcretePowder ||
				blockIn instanceof BlockStainedGlass || blockIn instanceof BlockStainedHardenedClay) {
			int color = ((EnumDyeColor)stateIn.getValue(BlockColored.COLOR)).getMetadata();

			if (color != 2) {
				worldIn.setBlockState(pos, stateIn.withProperty(BlockColored.COLOR, EnumDyeColor.MAGENTA), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		/** ガラスブロック **/
		if (blockIn == Blocks.GLASS) {
			worldIn.setBlockState(pos, Blocks.STAINED_GLASS.getDefaultState()
					.withProperty(BlockStainedGlass.COLOR, EnumDyeColor.MAGENTA), 11);

			if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); }
			return EnumActionResult.SUCCESS;
		}


		/** 板ガラス, カーペット **/
		if (blockIn instanceof BlockStainedGlassPane || blockIn instanceof BlockCarpet) {
			int color = ((EnumDyeColor)stateIn.getValue(BlockColored.COLOR)).getMetadata();

			if (color != 2) {
				worldIn.setBlockState(pos, stateIn.withProperty(BlockColored.COLOR, EnumDyeColor.MAGENTA), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn == Blocks.GLASS_PANE) {
			worldIn.setBlockState(pos, Blocks.STAINED_GLASS_PANE.getDefaultState()
					.withProperty(BlockStainedGlassPane.COLOR, EnumDyeColor.MAGENTA), 11);

			if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
			return EnumActionResult.SUCCESS;
		}


		/** 堅焼き粘土 **/
		if (blockIn instanceof BlockHardenedClay) {
			worldIn.setBlockState(pos, Blocks.STAINED_HARDENED_CLAY.getDefaultState()
					.withProperty(BlockStainedHardenedClay.COLOR, EnumDyeColor.MAGENTA), 11);

			if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); }
			return EnumActionResult.SUCCESS;
		}


		/** テラコッタ **/
		if (blockIn instanceof BlockGlazedTerracotta) {
			if (blockIn != Blocks.MAGENTA_GLAZED_TERRACOTTA) {
				worldIn.setBlockState(pos, Blocks.MAGENTA_GLAZED_TERRACOTTA.getDefaultState()
						.withProperty(BlockGlazedTerracotta.FACING, stateIn.getValue(BlockGlazedTerracotta.FACING)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}


		/** 瓦, 漆喰, なまこ壁 **/
		if (blockIn instanceof Plaster || blockIn instanceof Plaster_Crash ||
				blockIn instanceof Kawara || blockIn instanceof Kawara_Crash ||
				blockIn instanceof Namako || blockIn instanceof Namako_Crash ||
				blockIn instanceof NamakoB || blockIn instanceof NamakoB_Crash) {
			int color = ((Integer)stateIn.getValue(Base_Full_JP.STAGE_0_15)).intValue();

			if (color != 2) {
				worldIn.setBlockState(pos, stateIn.withProperty(Base_Full_JP.STAGE_0_15, Integer.valueOf(2)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}


		/** ハーフブロック **/
		if (blockIn instanceof Kawara_Slab) {
			if (blockIn != JPBlock_Blocks.KAWARA_SH_magenta) {

				worldIn.setBlockState(pos, JPBlock_Blocks.KAWARA_SH_magenta.getDefaultState()
						.withProperty(Base_Slab_JP.CRA, stateIn.getValue(Base_Slab_JP.CRA))
						.withProperty(Base_Slab_JP.DOUBLE, stateIn.getValue(Base_Slab_JP.DOUBLE))
						.withProperty(Base_Slab_JP.HALF, stateIn.getValue(Base_Slab_JP.HALF)), 11);

				if (stateIn.getValue(Base_Slab_JP.DOUBLE)) {
					if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); } }
					
				else { //!DOUBLE
					if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); } }

				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof Plaster_Slab) {
			if (blockIn != JPBlock_Blocks.SHIKKUI_SH_magenta) {

				worldIn.setBlockState(pos, JPBlock_Blocks.SHIKKUI_SH_magenta.getDefaultState()
						.withProperty(Base_Slab_JP.CRA, stateIn.getValue(Base_Slab_JP.CRA))
						.withProperty(Base_Slab_JP.DOUBLE, stateIn.getValue(Base_Slab_JP.DOUBLE))
						.withProperty(Base_Slab_JP.HALF, stateIn.getValue(Base_Slab_JP.HALF)), 11);

				if (stateIn.getValue(Base_Slab_JP.DOUBLE)) {
					if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); } }
					
				else { //!DOUBLE
					if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); } }

				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof Namako_Slab) {
			if (blockIn != JPBlock_Blocks.NAMAKO_SH_magenta) {

				worldIn.setBlockState(pos, JPBlock_Blocks.NAMAKO_SH_magenta.getDefaultState()
						.withProperty(Base_Slab_JP.CRA, stateIn.getValue(Base_Slab_JP.CRA))
						.withProperty(Base_Slab_JP.DOUBLE, stateIn.getValue(Base_Slab_JP.DOUBLE))
						.withProperty(Base_Slab_JP.HALF, stateIn.getValue(Base_Slab_JP.HALF)), 11);

				if (stateIn.getValue(Base_Slab_JP.DOUBLE)) {
					if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); } }
					
				else { //!DOUBLE
					if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); } }

				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof NamakoB_Slab) {
			if (blockIn != JPBlock_Blocks.NAMAKOB_SH_magenta) {

				worldIn.setBlockState(pos, JPBlock_Blocks.NAMAKOB_SH_magenta.getDefaultState()
						.withProperty(Base_Slab_JP.CRA, stateIn.getValue(Base_Slab_JP.CRA))
						.withProperty(Base_Slab_JP.DOUBLE, stateIn.getValue(Base_Slab_JP.DOUBLE))
						.withProperty(Base_Slab_JP.HALF, stateIn.getValue(Base_Slab_JP.HALF)), 11);

				if (stateIn.getValue(Base_Slab_JP.DOUBLE)) {
					if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); } }
					
				else { //!DOUBLE
					if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); } }

				return EnumActionResult.SUCCESS; }
		}


		/** 階段 **/
		if (blockIn instanceof Kawara_Stairs) {
			if (blockIn != JPBlock_Blocks.KAWARA_ST_magenta) {
				worldIn.setBlockState(pos, JPBlock_Blocks.KAWARA_ST_magenta.getDefaultState()
						.withProperty(BlockStairs.FACING, stateIn.getValue(BlockStairs.FACING))
						.withProperty(BlockStairs.HALF, stateIn.getValue(BlockStairs.HALF))
						.withProperty(BlockStairs.SHAPE, stateIn.getValue(BlockStairs.SHAPE)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof Plaster_Stairs) {
			if (blockIn != JPBlock_Blocks.SHIKKUI_ST_magenta) {
				worldIn.setBlockState(pos, JPBlock_Blocks.SHIKKUI_ST_magenta.getDefaultState()
						.withProperty(BlockStairs.FACING, stateIn.getValue(BlockStairs.FACING))
						.withProperty(BlockStairs.HALF, stateIn.getValue(BlockStairs.HALF))
						.withProperty(BlockStairs.SHAPE, stateIn.getValue(BlockStairs.SHAPE)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof Namako_Stairs) {
			if (blockIn != JPBlock_Blocks.NAMAKO_ST_magenta) {
				worldIn.setBlockState(pos, JPBlock_Blocks.NAMAKO_ST_magenta.getDefaultState()
						.withProperty(BlockStairs.FACING, stateIn.getValue(BlockStairs.FACING))
						.withProperty(BlockStairs.HALF, stateIn.getValue(BlockStairs.HALF))
						.withProperty(BlockStairs.SHAPE, stateIn.getValue(BlockStairs.SHAPE)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof NamakoB_Stairs) {
			if (blockIn != JPBlock_Blocks.NAMAKOB_ST_magenta) {
				worldIn.setBlockState(pos, JPBlock_Blocks.NAMAKOB_ST_magenta.getDefaultState()
						.withProperty(BlockStairs.FACING, stateIn.getValue(BlockStairs.FACING))
						.withProperty(BlockStairs.HALF, stateIn.getValue(BlockStairs.HALF))
						.withProperty(BlockStairs.SHAPE, stateIn.getValue(BlockStairs.SHAPE)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		///////////////////////
		/** トラス **/
		if (blockIn instanceof Truss_Cable) {
			if (blockIn != Harbor_Blocks.TRUSS_magenta) {
				worldIn.setBlockState(pos, Harbor_Blocks.TRUSS_magenta.getDefaultState()
						.withProperty(Truss_Cable.POWER, stateIn.getValue(Truss_Cable.POWER)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof Truss_Cable2) {
			if (blockIn != Harbor_Blocks.TRUSS2_magenta) {
				worldIn.setBlockState(pos, Harbor_Blocks.TRUSS2_magenta.getDefaultState()
						.withProperty(Truss_Cable2.POWER, stateIn.getValue(Truss_Cable2.POWER)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}
		
		if (blockIn instanceof Truss_Amp) {
			if (blockIn != Harbor_Blocks.AMP_magenta) {
				worldIn.setBlockState(pos, Harbor_Blocks.AMP_magenta.getDefaultState()
						.withProperty(Truss_Amp.FACING, stateIn.getValue(Truss_Amp.FACING)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof Truss_Amp2) {
			if (blockIn != Harbor_Blocks.AMP2_magenta) {
				worldIn.setBlockState(pos, Harbor_Blocks.AMP2_magenta.getDefaultState()
						.withProperty(Truss_Amp2.FACING, stateIn.getValue(Truss_Amp2.FACING)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}
		
		/** 椅子, 座布団, キャンドル **/
		if (blockIn instanceof CafeChair || blockIn instanceof BaseZabuton ||
				blockIn instanceof Candle || blockIn instanceof Lit_Candle) {
			int stage = ((Integer)stateIn.getValue(CafeChair.STAGE_0_15)).intValue();

			if (stage != 2) {
				worldIn.setBlockState(pos, stateIn.withProperty(CafeChair.STAGE_0_15, Integer.valueOf(2)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		/** 行灯 **/
		if (blockIn instanceof Andon_1 || blockIn instanceof Andon_2 ||
				blockIn instanceof Andon_3 || blockIn instanceof Andon_4) {
			int stage = ((Integer)stateIn.getValue(BaseStage4_Face.STAGE_1_4)).intValue();

			if (!(blockIn == Lamp_Blocks.ANDON_1 && stage == 3)) {
				worldIn.setBlockState(pos, Lamp_Blocks.ANDON_1.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, stateIn.getValue(BaseStage4_Face.H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(3)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof Lit_Andon_1 || blockIn instanceof Lit_Andon_2 ||
				blockIn instanceof Lit_Andon_3 || blockIn instanceof Lit_Andon_4) {
			int stage = ((Integer)stateIn.getValue(BaseStage4_Face.STAGE_1_4)).intValue();

			if (!(blockIn == Lamp_Blocks.LIT_ANDON_1 && stage == 3)) {
				worldIn.setBlockState(pos, Lamp_Blocks.LIT_ANDON_1.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, stateIn.getValue(BaseStage4_Face.H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, Integer.valueOf(3)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		/** ソファ **/
		if (blockIn instanceof Sofa) {
			if (blockIn != Furniture_Blocks.SOFA_magenta && blockIn != Furniture_Blocks.SOFA_leather) {
				worldIn.setBlockState(pos, Furniture_Blocks.SOFA_magenta.getDefaultState()
						.withProperty(BaseFacingSapo.H_FACING, stateIn.getValue(BaseFacingSapo.H_FACING)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		/** 座椅子 **/
		if (blockIn instanceof Zaisu) {
			if (blockIn != JPDeco_Blocks.ZAISU_magenta) {
				worldIn.setBlockState(pos, JPDeco_Blocks.ZAISU_magenta.getDefaultState()
						.withProperty(BaseFacingSapo.H_FACING, stateIn.getValue(BaseFacingSapo.H_FACING)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		/** のれん **/
		if (blockIn instanceof Noren) {
			if (blockIn != JPDeco_Blocks.NOREN_magenta) {
				worldIn.setBlockState(pos, JPDeco_Blocks.NOREN_magenta.getDefaultState()
						.withProperty(BaseFacingSapo.H_FACING, stateIn.getValue(BaseFacingSapo.H_FACING)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		/** 布団 **/
		if (blockIn instanceof Futon) {
			if (blockIn != JPDeco_Blocks.FUTON_magenta) {
				worldIn.setBlockState(pos, JPDeco_Blocks.FUTON_magenta.getDefaultState()
						.withProperty(BaseStage2_Face.H_FACING, stateIn.getValue(BaseStage2_Face.H_FACING))
						.withProperty(BaseStage2_Face.STAGE_1_2, stateIn.getValue(BaseStage2_Face.STAGE_1_2)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		/** 畳 **/
		if (blockIn instanceof Tatami) {
			if (blockIn != JPDeco_Blocks.TATAMI_magenta && blockIn != JPDeco_Blocks.TATAMI) {
				worldIn.setBlockState(pos, JPDeco_Blocks.TATAMI_magenta.getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, stateIn.getValue(BaseFacingSlabW.H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, stateIn.getValue(BaseFacingSlabW.DOUBLE))
						.withProperty(BaseFacingSlabW.HALF, stateIn.getValue(BaseFacingSlabW.HALF)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof Tatami_Y) {
			if (blockIn != JPDeco_Blocks.TATAMIY_magenta && blockIn != JPDeco_Blocks.TATAMIY) {
				worldIn.setBlockState(pos, JPDeco_Blocks.TATAMIY_magenta.getDefaultState()
						.withProperty(BaseFacingSlabW.H_FACING, stateIn.getValue(BaseFacingSlabW.H_FACING))
						.withProperty(BaseFacingSlabW.DOUBLE, stateIn.getValue(BaseFacingSlabW.DOUBLE))
						.withProperty(BaseFacingSlabW.HALF, stateIn.getValue(BaseFacingSlabW.HALF)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof BaseTatamiWood) {
			int color = ((Integer)stateIn.getValue(BaseTatamiWood.STAGE_0_15)).intValue();

			if (color != 2) {
				worldIn.setBlockState(pos, stateIn.withProperty(BaseTatamiWood.STAGE_0_15, Integer.valueOf(2)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}
		
		/** 傘 **/
		if (blockIn instanceof Wagasa) {
			if (blockIn != Garden_Blocks.KASA_magenta) {
				worldIn.setBlockState(pos, Garden_Blocks.KASA_magenta.getDefaultState()
						.withProperty(Wagasa.WHICH, stateIn.getValue(Wagasa.WHICH)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		///////////////////////
		/** ふすま HINGEはUPPERから H_FACINGはLOWERから **/
		IBlockState upState = worldIn.getBlockState(pos.up());
		IBlockState downState = worldIn.getBlockState(pos.down());

		if (blockIn instanceof Fusuma) {
			if (blockIn != Fusuma_Blocks.FUSUMA_magenta) {
				
				if (stateIn.getValue(Fusuma.HALF) == HalfState.LOWER) {
					worldIn.setBlockState(pos, Fusuma_Blocks.FUSUMA_magenta.getDefaultState()
							.withProperty(Fusuma.H_FACING, stateIn.getValue(Fusuma.H_FACING))
							.withProperty(Fusuma.OPEN, stateIn.getValue(Fusuma.OPEN))
							.withProperty(Fusuma.HINGE, upState.getValue(Fusuma.HINGE))
							.withProperty(Fusuma.POWERED, upState.getValue(Fusuma.POWERED))
							.withProperty(Fusuma.HALF, HalfState.LOWER), 11);
					worldIn.setBlockState(pos.up(), Fusuma_Blocks.FUSUMA_magenta.getDefaultState()
							.withProperty(Fusuma.H_FACING, stateIn.getValue(Fusuma.H_FACING))
							.withProperty(Fusuma.OPEN, stateIn.getValue(Fusuma.OPEN))
							.withProperty(Fusuma.HINGE, upState.getValue(Fusuma.HINGE))
							.withProperty(Fusuma.POWERED, upState.getValue(Fusuma.POWERED))
							.withProperty(Fusuma.HALF, HalfState.UPPER), 11); }

				if (stateIn.getValue(Fusuma.HALF) == HalfState.UPPER) {
					worldIn.setBlockState(pos, Fusuma_Blocks.FUSUMA_magenta.getDefaultState()
							.withProperty(Fusuma.H_FACING, downState.getValue(Fusuma.H_FACING))
							.withProperty(Fusuma.OPEN, downState.getValue(Fusuma.OPEN))
							.withProperty(Fusuma.HINGE, stateIn.getValue(Fusuma.HINGE))
							.withProperty(Fusuma.POWERED, stateIn.getValue(Fusuma.POWERED))
							.withProperty(Fusuma.HALF, HalfState.UPPER), 11);
					worldIn.setBlockState(pos.down(), Fusuma_Blocks.FUSUMA_magenta.getDefaultState()
							.withProperty(Fusuma.H_FACING, downState.getValue(Fusuma.H_FACING))
							.withProperty(Fusuma.OPEN, downState.getValue(Fusuma.OPEN))
							.withProperty(Fusuma.HINGE, stateIn.getValue(Fusuma.HINGE))
							.withProperty(Fusuma.POWERED, stateIn.getValue(Fusuma.POWERED))
							.withProperty(Fusuma.HALF, HalfState.LOWER), 11); }

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof Fusuma_B) {
			if (blockIn != Fusuma_Blocks.FUSUMAB_magenta) {
				
				if (stateIn.getValue(Fusuma_B.HALF) == HalfState.LOWER) {
					worldIn.setBlockState(pos, Fusuma_Blocks.FUSUMAB_magenta.getDefaultState()
							.withProperty(Fusuma_B.H_FACING, stateIn.getValue(Fusuma_B.H_FACING))
							.withProperty(Fusuma_B.OPEN, stateIn.getValue(Fusuma_B.OPEN))
							.withProperty(Fusuma_B.HINGE, upState.getValue(Fusuma_B.HINGE))
							.withProperty(Fusuma_B.POWERED, upState.getValue(Fusuma_B.POWERED))
							.withProperty(Fusuma_B.HALF, HalfState.LOWER), 11);
					worldIn.setBlockState(pos.up(), Fusuma_Blocks.FUSUMAB_magenta.getDefaultState()
							.withProperty(Fusuma_B.H_FACING, stateIn.getValue(Fusuma_B.H_FACING))
							.withProperty(Fusuma_B.OPEN, stateIn.getValue(Fusuma_B.OPEN))
							.withProperty(Fusuma_B.HINGE, upState.getValue(Fusuma_B.HINGE))
							.withProperty(Fusuma_B.POWERED, upState.getValue(Fusuma_B.POWERED))
							.withProperty(Fusuma_B.HALF, HalfState.UPPER), 11); }

				if (stateIn.getValue(Fusuma_B.HALF) == HalfState.UPPER) {
					worldIn.setBlockState(pos, Fusuma_Blocks.FUSUMAB_magenta.getDefaultState()
							.withProperty(Fusuma_B.H_FACING, downState.getValue(Fusuma_B.H_FACING))
							.withProperty(Fusuma_B.OPEN, downState.getValue(Fusuma_B.OPEN))
							.withProperty(Fusuma_B.HINGE, stateIn.getValue(Fusuma_B.HINGE))
							.withProperty(Fusuma_B.POWERED, stateIn.getValue(Fusuma_B.POWERED))
							.withProperty(Fusuma_B.HALF, HalfState.UPPER), 11);
					worldIn.setBlockState(pos.down(), Fusuma_Blocks.FUSUMAB_magenta.getDefaultState()
							.withProperty(Fusuma_B.H_FACING, downState.getValue(Fusuma_B.H_FACING))
							.withProperty(Fusuma_B.OPEN, downState.getValue(Fusuma_B.OPEN))
							.withProperty(Fusuma_B.HINGE, stateIn.getValue(Fusuma_B.HINGE))
							.withProperty(Fusuma_B.POWERED, stateIn.getValue(Fusuma_B.POWERED))
							.withProperty(Fusuma_B.HALF, HalfState.LOWER), 11); }

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		///////////////////////
		/** 鴨居 **/
		if (blockIn instanceof KamoiPlaster_Oak) {
			if (blockIn != KamoiPlaster_Blocks.KAMOI_magenta_oak) {
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_magenta_oak.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, stateIn.getValue(BaseStage4_Face.H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, stateIn.getValue(BaseStage4_Face.STAGE_1_4)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof KamoiPlaster_Spruce) {
			if (blockIn != KamoiPlaster_Blocks.KAMOI_magenta_spru) {
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_magenta_spru.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, stateIn.getValue(BaseStage4_Face.H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, stateIn.getValue(BaseStage4_Face.STAGE_1_4)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof KamoiPlaster_Birch) {
			if (blockIn != KamoiPlaster_Blocks.KAMOI_magenta_bir) {
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_magenta_bir.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, stateIn.getValue(BaseStage4_Face.H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, stateIn.getValue(BaseStage4_Face.STAGE_1_4)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof KamoiPlaster_Jungle) {
			if (blockIn != KamoiPlaster_Blocks.KAMOI_magenta_jun) {
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_magenta_jun.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, stateIn.getValue(BaseStage4_Face.H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, stateIn.getValue(BaseStage4_Face.STAGE_1_4)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof KamoiPlaster_Acacia) {
			if (blockIn != KamoiPlaster_Blocks.KAMOI_magenta_aca) {
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_magenta_aca.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, stateIn.getValue(BaseStage4_Face.H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, stateIn.getValue(BaseStage4_Face.STAGE_1_4)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof KamoiPlaster_DarkOak) {
			if (blockIn != KamoiPlaster_Blocks.KAMOI_magenta_doak) {
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_magenta_doak.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, stateIn.getValue(BaseStage4_Face.H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, stateIn.getValue(BaseStage4_Face.STAGE_1_4)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof KamoiPlaster_Sakura) {
			if (blockIn != KamoiPlaster_Blocks.KAMOI_magenta_sakura) {
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_magenta_sakura.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, stateIn.getValue(BaseStage4_Face.H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, stateIn.getValue(BaseStage4_Face.STAGE_1_4)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof KamoiPlaster_Kaede) {
			if (blockIn != KamoiPlaster_Blocks.KAMOI_magenta_kaede) {
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_magenta_kaede.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, stateIn.getValue(BaseStage4_Face.H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, stateIn.getValue(BaseStage4_Face.STAGE_1_4)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof KamoiPlaster_Ichoh) {
			if (blockIn != KamoiPlaster_Blocks.KAMOI_magenta_ichoh) {
				worldIn.setBlockState(pos, KamoiPlaster_Blocks.KAMOI_magenta_ichoh.getDefaultState()
						.withProperty(BaseStage4_Face.H_FACING, stateIn.getValue(BaseStage4_Face.H_FACING))
						.withProperty(BaseStage4_Face.STAGE_1_4, stateIn.getValue(BaseStage4_Face.STAGE_1_4)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		///////////////////////
		/** 粘土 **/
		if (blockIn instanceof WallPane_ClayColor) {
			if (blockIn != WallPanel_Blocks.WP_CLAY_magenta) {
				worldIn.setBlockState(pos, WallPanel_Blocks.WP_CLAY_magenta.getDefaultState()
						.withProperty(BaseFacingSapo.H_FACING, stateIn.getValue(BaseFacingSapo.H_FACING)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof WallPane_Clay) {
			worldIn.setBlockState(pos, WallPanel_Blocks.WP_CLAY_magenta.getDefaultState()
					.withProperty(BaseFacingSapo.H_FACING, stateIn.getValue(BaseFacingSapo.H_FACING)), 11);

			if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
			return EnumActionResult.SUCCESS;
		}

		/** ガラス **/
		if (blockIn instanceof WallPane_GlassStained) {
			if (blockIn != WallPanel_Blocks.WP_GLASS_magenta) {
				worldIn.setBlockState(pos, WallPanel_Blocks.WP_GLASS_magenta.getDefaultState()
						.withProperty(BaseFacingSapo.H_FACING, stateIn.getValue(BaseFacingSapo.H_FACING)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof WallPane_Glass) {
			worldIn.setBlockState(pos, WallPanel_Blocks.WP_GLASS_magenta.getDefaultState()
					.withProperty(BaseFacingSapo.H_FACING, stateIn.getValue(BaseFacingSapo.H_FACING)), 11);

			if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
			return EnumActionResult.SUCCESS;
		}

		/** しっくい **/
		if (blockIn instanceof WallPane_Plaster) {
			if (blockIn != WallPanel_JPBlocks.WP_PLASTER_magenta && blockIn != WallPanel_JPBlocks.WP_DIRTWALL) {
				worldIn.setBlockState(pos, WallPanel_JPBlocks.WP_PLASTER_magenta.getDefaultState()
						.withProperty(WallPane_Stage2.H_FACING, stateIn.getValue(WallPane_Stage2.H_FACING))
						.withProperty(WallPane_Stage2.STAGE_1_2, stateIn.getValue(WallPane_Stage2.STAGE_1_2)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		/** なまこ **/
		if (blockIn instanceof WallPane_Namako) {
			if (blockIn != WallPanel_JPBlocks.WP_NAMAKO_magenta) {
				worldIn.setBlockState(pos, WallPanel_JPBlocks.WP_NAMAKO_magenta.getDefaultState()
						.withProperty(WallPane_Stage2.H_FACING, stateIn.getValue(WallPane_Stage2.H_FACING))
						.withProperty(WallPane_Stage2.STAGE_1_2, stateIn.getValue(WallPane_Stage2.STAGE_1_2)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		if (blockIn instanceof WallPane_Namako_B) {
			if (blockIn != WallPanel_JPBlocks.WP_NAMAKOB_magenta) {
				worldIn.setBlockState(pos, WallPanel_JPBlocks.WP_NAMAKOB_magenta.getDefaultState()
						.withProperty(WallPane_Stage2.H_FACING, stateIn.getValue(WallPane_Stage2.H_FACING))
						.withProperty(WallPane_Stage2.STAGE_1_2, stateIn.getValue(WallPane_Stage2.STAGE_1_2)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}

		///6.1.2////////////////////
		if (blockIn instanceof Wall_Plaster || blockIn instanceof Wall_PlasterCrash ||
				blockIn instanceof Wall_Namako || blockIn instanceof Wall_NamakoCrash ||
				blockIn instanceof Wall_NamakoB || blockIn instanceof Wall_NamakoBCrash) {
			int color = ((Integer)stateIn.getValue(Base_Wall.STAGE_0_15)).intValue();

			if (color != 2) {
				worldIn.setBlockState(pos, stateIn.withProperty(Base_Wall.STAGE_0_15, Integer.valueOf(2))
						.withProperty(Base_Wall.NORTH, stateIn.getValue(Base_Wall.NORTH))
						.withProperty(Base_Wall.EAST, stateIn.getValue(Base_Wall.EAST))
						.withProperty(Base_Wall.SOUTH, stateIn.getValue(Base_Wall.SOUTH))
						.withProperty(Base_Wall.WEST, stateIn.getValue(Base_Wall.WEST)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}
		
		if (blockIn instanceof Wall_Kawara) {
			int color = ((Integer)stateIn.getValue(Base_WallKawara.STAGE_0_15)).intValue();

			if (color != 2) {
				worldIn.setBlockState(pos, stateIn.withProperty(Base_WallKawara.STAGE_0_15, Integer.valueOf(2))
						.withProperty(Base_WallKawara.NORTH, stateIn.getValue(Base_WallKawara.NORTH))
						.withProperty(Base_WallKawara.EAST, stateIn.getValue(Base_WallKawara.EAST))
						.withProperty(Base_WallKawara.SOUTH, stateIn.getValue(Base_WallKawara.SOUTH))
						.withProperty(Base_WallKawara.WEST, stateIn.getValue(Base_WallKawara.WEST)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}
		
		if (blockIn instanceof Wall_Sama) {
			if (blockIn != JPBlock_Blocks.SHIKKUI_SAMA_magenta && blockIn != JPBlock_Blocks.DIRTWALL_SAMA) {
				worldIn.setBlockState(pos, JPBlock_Blocks.SHIKKUI_SAMA_magenta.getDefaultState()
						.withProperty(BaseStage4_Face.STAGE_1_4, stateIn.getValue(BaseStage4_Face.STAGE_1_4))
						.withProperty(BaseStage4_Face.H_FACING, stateIn.getValue(BaseStage4_Face.H_FACING)), 11);

				if (stateIn.getValue(BaseStage4_Face.STAGE_1_4) == 1 || stateIn.getValue(BaseStage4_Face.STAGE_1_4) == 2) {
					if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); } }

				if (stateIn.getValue(BaseStage4_Face.STAGE_1_4) != 1 && stateIn.getValue(BaseStage4_Face.STAGE_1_4) != 2) {
					if (playerIn != null) { Base_Hake.consumeAndBreak(2, playerIn, worldIn, pos, hStack); } }
				return EnumActionResult.SUCCESS; }
		}
		
		///6.2.4////////////////////
		if (blockIn instanceof Curtain) {
			if (blockIn != Window_Blocks.CURTAIN_magenta) {
				worldIn.setBlockState(pos, Window_Blocks.CURTAIN_magenta.getDefaultState()
						.withProperty(Curtain.H_FACING, stateIn.getValue(Curtain.H_FACING))
						.withProperty(Curtain.STAGE_1_4, stateIn.getValue(Curtain.STAGE_1_4)), 11);

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}
		
		if (blockIn instanceof CurtainTall) {
			if (blockIn != Window_Blocks.CURTAINTALL_magenta) {
				
				if (stateIn.getValue(CurtainTall.HALF) == HalfState.LOWER) {
					worldIn.setBlockState(pos, Window_Blocks.CURTAINTALL_magenta.getDefaultState()
							.withProperty(CurtainTall.H_FACING, stateIn.getValue(CurtainTall.H_FACING))
							.withProperty(CurtainTall.OPEN, stateIn.getValue(CurtainTall.OPEN))
							.withProperty(CurtainTall.HINGE, upState.getValue(CurtainTall.HINGE))
							.withProperty(CurtainTall.HALF, HalfState.LOWER), 11);
					worldIn.setBlockState(pos.up(), Window_Blocks.CURTAINTALL_magenta.getDefaultState()
							.withProperty(CurtainTall.H_FACING, stateIn.getValue(CurtainTall.H_FACING))
							.withProperty(CurtainTall.OPEN, stateIn.getValue(CurtainTall.OPEN))
							.withProperty(CurtainTall.HINGE, upState.getValue(CurtainTall.HINGE))
							.withProperty(CurtainTall.HALF, HalfState.UPPER), 11); }

				if (stateIn.getValue(CurtainTall.HALF) == HalfState.UPPER) {
					worldIn.setBlockState(pos, Window_Blocks.CURTAINTALL_magenta.getDefaultState()
							.withProperty(CurtainTall.H_FACING, downState.getValue(CurtainTall.H_FACING))
							.withProperty(CurtainTall.OPEN, downState.getValue(CurtainTall.OPEN))
							.withProperty(CurtainTall.HINGE, stateIn.getValue(CurtainTall.HINGE))
							.withProperty(CurtainTall.HALF, HalfState.UPPER), 11);
					worldIn.setBlockState(pos.down(), Window_Blocks.CURTAINTALL_magenta.getDefaultState()
							.withProperty(CurtainTall.H_FACING, downState.getValue(CurtainTall.H_FACING))
							.withProperty(CurtainTall.OPEN, downState.getValue(CurtainTall.OPEN))
							.withProperty(CurtainTall.HINGE, stateIn.getValue(CurtainTall.HINGE))
							.withProperty(CurtainTall.HALF, HalfState.LOWER), 11); }

				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}
		
		if (blockIn instanceof DeskCloth) {
			int color = ((Integer)stateIn.getValue(DeskCloth.STAGE_1_4)).intValue();
			if (blockIn != Chinjufu_Blocks.DESKCLOTH_03 || color != 3) {
				worldIn.setBlockState(pos, Chinjufu_Blocks.DESKCLOTH_03.getDefaultState()
						.withProperty(DeskCloth.H_FACING, stateIn.getValue(DeskCloth.H_FACING))
						.withProperty(DeskCloth.STAGE_1_4, Integer.valueOf(3)), 11);
				
				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}
		
		if (blockIn instanceof BaseClothTable) {
			int color = ((Integer)stateIn.getValue(BaseClothTable.STAGE_0_15)).intValue();
			if (color != 2) {
				worldIn.setBlockState(pos, stateIn.withProperty(BaseClothTable.STAGE_0_15, Integer.valueOf(2)), 11);//MAGENTA
				
				if (playerIn != null) { Base_Hake.consumeAndBreak(1, playerIn, worldIn, pos, hStack); }
				return EnumActionResult.SUCCESS; }
		}
		
		/* Wash the brush. MIZUOKE work is in the block. */
		if (blockIn == Blocks.CAULDRON) {
			int level = stateIn.getValue(BlockCauldron.LEVEL);
			if (level != 0) {
				((BlockCauldron)blockIn).setWaterLevel(worldIn, pos, stateIn, level - 1);
				CMEvents.washHAKE_Cauldron(worldIn, pos, hStack, playerIn);
				return EnumActionResult.SUCCESS; }
		}
		return EnumActionResult.PASS;
	}
}
