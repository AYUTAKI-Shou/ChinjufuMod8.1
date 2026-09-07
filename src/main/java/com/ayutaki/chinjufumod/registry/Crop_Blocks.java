package com.ayutaki.chinjufumod.registry;

import java.util.function.Function;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.blocks.crop.Ami_Shikake;
import com.ayutaki.chinjufumod.blocks.crop.Ami_Youshoku;
import com.ayutaki.chinjufumod.blocks.crop.Azuki;
import com.ayutaki.chinjufumod.blocks.crop.Cabbage;
import com.ayutaki.chinjufumod.blocks.crop.Chanoki;
import com.ayutaki.chinjufumod.blocks.crop.Chilipepper;
import com.ayutaki.chinjufumod.blocks.crop.Corn;
import com.ayutaki.chinjufumod.blocks.crop.Cumin;
import com.ayutaki.chinjufumod.blocks.crop.Enden;
import com.ayutaki.chinjufumod.blocks.crop.Enden_kara;
import com.ayutaki.chinjufumod.blocks.crop.Grape;
import com.ayutaki.chinjufumod.blocks.crop.GreenOnion;
import com.ayutaki.chinjufumod.blocks.crop.Hakusai;
import com.ayutaki.chinjufumod.blocks.crop.Hamaguri;
import com.ayutaki.chinjufumod.blocks.crop.HodaGi_A_Bot;
import com.ayutaki.chinjufumod.blocks.crop.HodaGi_A_Top;
import com.ayutaki.chinjufumod.blocks.crop.HodaGi_B_Bot;
import com.ayutaki.chinjufumod.blocks.crop.HodaGi_B_Top;
import com.ayutaki.chinjufumod.blocks.crop.HodaGi_C_Bot;
import com.ayutaki.chinjufumod.blocks.crop.HodaGi_C_Top;
import com.ayutaki.chinjufumod.blocks.crop.Inagi;
import com.ayutaki.chinjufumod.blocks.crop.LostClam;
import com.ayutaki.chinjufumod.blocks.crop.Mikan;
import com.ayutaki.chinjufumod.blocks.crop.Nori;
import com.ayutaki.chinjufumod.blocks.crop.NoriAmi;
import com.ayutaki.chinjufumod.blocks.crop.Onion;
import com.ayutaki.chinjufumod.blocks.crop.PepperVanilla;
import com.ayutaki.chinjufumod.blocks.crop.Rice;
import com.ayutaki.chinjufumod.blocks.crop.Rice_8;
import com.ayutaki.chinjufumod.blocks.crop.Sakura_me;
import com.ayutaki.chinjufumod.blocks.crop.SeedsBox;
import com.ayutaki.chinjufumod.blocks.crop.Soy;
import com.ayutaki.chinjufumod.blocks.crop.Spinach;
import com.ayutaki.chinjufumod.blocks.crop.Tengusa;
import com.ayutaki.chinjufumod.blocks.crop.Tomato;
import com.ayutaki.chinjufumod.blocks.crop.Turmeric;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Crop_Blocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ChinjufuMod.MOD_ID);

	public static final DeferredBlock<Block> SEEDSBOX = register("block_seedsbox", SeedsBox::new, baseState().mapColor(MapColor.WOOD).noCollission().strength(0.5F).sound(SoundType.CROP));

	public static final DeferredBlock<Block> AZUKI = register("block_vege_azuki", Azuki::new, cropRandom());
	public static final DeferredBlock<Block> CABBAGE = register("block_vege_cabbage", Cabbage::new, cropRandom());
	public static final DeferredBlock<Block> HAKUSAI = register("block_vege_hakusai", Hakusai::new, cropRandom());
	public static final DeferredBlock<Block> CORN = register("block_vege_corn", Corn::new, cropRandom());
	public static final DeferredBlock<Block> GREENONION = register("block_vege_greenonion", GreenOnion::new, cropRandom());
	public static final DeferredBlock<Block> ONION = register("block_vege_onion", Onion::new, cropRandom());
	public static final DeferredBlock<Block> RICE = register("block_vege_rice", Rice::new, cropRandom());
	public static final DeferredBlock<Block> RICE_8 = register("block_vege_rice_8", Rice_8::new, baseState().mapColor(MapColor.PLANT).noCollission().strength(0.1F).sound(SoundType.CROP));
	public static final DeferredBlock<Block> SOY = register("block_vege_soy", Soy::new, cropRandom());
	public static final DeferredBlock<Block> SPINACH = register("block_vege_spinach", Spinach::new, cropRandom());
	public static final DeferredBlock<Block> TOMATO = register("block_vege_tomato", Tomato::new, cropRandom());
	public static final DeferredBlock<Block> SAKURA = register("block_tree_sakura_me", Sakura_me::new, cropRandom());

	public static final DeferredBlock<Block> CHANOKI = register("block_wood_chanoki", Chanoki::new, woodRandom());
	public static final DeferredBlock<Block> BUDOUNOKI = register("block_wood_grape", Grape::new, woodRandom());
	public static final DeferredBlock<Block> MIKAN = register("block_wood_mikan", Mikan::new, woodRandom());

	public static final DeferredBlock<Block> HODAGI_A_BOT = register("block_hodagi_a_bot", HodaGi_A_Bot::new, woodRandom());
	public static final DeferredBlock<Block> HODAGI_A_TOP = register("block_hodagi_a_top", HodaGi_A_Top::new, woodRandom());
	public static final DeferredBlock<Block> HODAGI_B_BOT = register("block_hodagi_b_bot", HodaGi_B_Bot::new, woodRandom());
	public static final DeferredBlock<Block> HODAGI_B_TOP = register("block_hodagi_b_top", HodaGi_B_Top::new, woodRandom());
	public static final DeferredBlock<Block> HODAGI_C_BOT = register("block_hodagi_c_bot", HodaGi_C_Bot::new, woodRandom());
	public static final DeferredBlock<Block> HODAGI_C_TOP = register("block_hodagi_c_top", HodaGi_C_Top::new, woodRandom());

	public static final DeferredBlock<Block> PEPPER = register("block_spice_pepper", PepperVanilla::new, woodRandom());
	public static final DeferredBlock<Block> CUMIN = register("block_spice_cumin", Cumin::new, cropRandom());
	public static final DeferredBlock<Block> TURMERIC = register("block_spice_turmeric", Turmeric::new, cropRandom());
	public static final DeferredBlock<Block> CHILI = register("block_spice_chilipepper", Chilipepper::new, cropRandom());
	public static final DeferredBlock<Block> VANILLA = register("block_spice_vanilla", PepperVanilla::new, woodRandom());

	public static final DeferredBlock<Block> INAGI = register("block_inagi", Inagi::new, plantRandom());

	public static final DeferredBlock<Block> ENDEN = register("block_enden", Enden::new, sandRandom().mapColor(MapColor.SNOW).noOcclusion().isValidSpawn(Crop_Blocks::neverEntity));
	public static final DeferredBlock<Block> ENDEN_k = register("block_enden_k", Enden_kara::new, sandRandom().mapColor(MapColor.SNOW).noOcclusion().isValidSpawn(Crop_Blocks::neverEntity));
	public static final DeferredBlock<Block> NORIAMI = register("block_noriami", NoriAmi::new, plantRandom());

	public static final DeferredBlock<Block> TOAMI = register("item_toami", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOL));
	public static final DeferredBlock<Block> SHIKAKE_AMI = register("block_ami_shikake", Ami_Shikake::new, woolRandom());
	public static final DeferredBlock<Block> YOUSHOKU_AMI = register("block_ami_youshoku", Ami_Youshoku::new, woolRandom());

	public static final DeferredBlock<Block> HAMAGURI = register("block_hamaguri", Hamaguri::new, baseState().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.SNOW));
	public static final DeferredBlock<Block> KAINASHI = register("block_lostclam", (props) -> new LostClam(new ColorRGBA(14406560), props), sandRandom().mapColor(MapColor.SAND));
	public static final DeferredBlock<Block> TENGUSA = register("block_vege_tengusa", Tengusa::new, seaRandom());
	public static final DeferredBlock<Block> NORI = register("block_vege_nori", Nori::new, seaRandom());
	
	
	/* Share variables */
	private static boolean never(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return false;
	}

	private static Boolean neverEntity(BlockState state, BlockGetter worldIn, BlockPos pos, EntityType<?> entity) {
		return (boolean)false;
	}
	
	private static Properties baseState() {
		return BlockBehaviour.Properties.of().noOcclusion().isValidSpawn(Crop_Blocks::neverEntity).isSuffocating(Crop_Blocks::never);
	}
	
	private static Properties cropRandom() {
		return baseState().mapColor(MapColor.PLANT).noCollission().strength(0.1F).sound(SoundType.CROP).randomTicks();
	}
	
	private static Properties woodRandom() {
		return baseState().mapColor(MapColor.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD).randomTicks();
	}
	
	private static Properties plantRandom() {
		return baseState().mapColor(MapColor.PLANT).noCollission().strength(1.0F).sound(SoundType.WOOD).randomTicks();
	}

	private static Properties sandRandom() {
		return BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.SAND).randomTicks();
	}

	private static Properties woolRandom() {
		return baseState().mapColor(MapColor.WOOL).strength(1.0F).sound(SoundType.WOOL).randomTicks();
	}
	
	private static Properties seaRandom() {
		return baseState().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.WET_GRASS).randomTicks();
	}
	
	///* Register *///
	private static DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, Block> function, BlockBehaviour.Properties props) {
		return BLOCKS.register(name, () -> function.apply(props.setId(ResourceKey.create(Registries.BLOCK, ChinjufuMod.id(name)))));
	}
}
