package com.ayutaki.chinjufumod.tags;

import java.util.concurrent.CompletableFuture;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class ItemCTags_CM extends ItemTagsProvider {

	public ItemCTags_CM(PackOutput outPut, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blockTag) {
		super(outPut, provider, blockTag, ChinjufuMod.MOD_ID);
	}

	public static final TagKey<Item> BUCKETS = commonTag("buckets");
	public static final TagKey<Item> BUCKETS_EMPTY = commonTag("buckets/empty");
	public static final TagKey<Item> BUCKETS_WATER = commonTag("buckets/water");
	public static final TagKey<Item> BUCKETS_MILK = commonTag("buckets/milk");
	
	public static final TagKey<Item> CHEESE = commonTag("cheese");
	public static final TagKey<Item> FRESH_CHEESE = commonTag("fresh_cheese");
	public static final TagKey<Item> CLAMS = commonTag("clams");
	
	public static final TagKey<Item> CROPS = commonTag("crops");
	public static final TagKey<Item> CROPS_APPLE = commonTag("crops/apple");
	public static final TagKey<Item> CROPS_BAMBOOSHOOT = commonTag("crops/bambooshoot");
	public static final TagKey<Item> CROPS_BEETROOT = commonTag("crops/beetroot");
	public static final TagKey<Item> CROPS_BLACKPEPPER = commonTag("crops/blackpepper");
	public static final TagKey<Item> CROPS_CABBAGE = commonTag("crops/cabbage");
	public static final TagKey<Item> CROPS_CARROT = commonTag("crops/carrot");
	public static final TagKey<Item> CROPS_CHERRY = commonTag("crops/cherry");
	public static final TagKey<Item> CROPS_CHESTNUT = commonTag("crops/chestnut");
	public static final TagKey<Item> CROPS_CHILI = commonTag("crops/chili_pepper");
	public static final TagKey<Item> CROPS_HAKUSAI = commonTag("crops/chinese_cabbage");
	public static final TagKey<Item> CROPS_CITRUS = commonTag("crops/citrus");
	public static final TagKey<Item> CROPS_CORN = commonTag("crops/corn");
	public static final TagKey<Item> CROPS_CUMIN = commonTag("crops/cumin");
	public static final TagKey<Item> CROPS_GRAPE = commonTag("crops/grape");
	public static final TagKey<Item> CROPS_GREENONION = commonTag("crops/greenonion");
	public static final TagKey<Item> CROPS_NETHERWART = commonTag("crops/nether_wart");
	public static final TagKey<Item> CROPS_ONION = commonTag("crops/onion");
	public static final TagKey<Item> CROPS_ORANGE = commonTag("crops/orange");
	public static final TagKey<Item> CROPS_PEPPER = commonTag("crops/pepper");
	public static final TagKey<Item> CROPS_PEPPERCORN = commonTag("crops/peppercorn");
	public static final TagKey<Item> CROPS_POTATO = commonTag("crops/potato");
	public static final TagKey<Item> CROPS_RICE = commonTag("crops/rice");
	public static final TagKey<Item> CROPS_SOY = commonTag("crops/soybean");
	public static final TagKey<Item> CROPS_SPINACH = commonTag("crops/spinach");
	public static final TagKey<Item> CROPS_TEALEAF = commonTag("crops/tea_leaves");
	public static final TagKey<Item> CROPS_TOMATO = commonTag("crops/tomato");
	public static final TagKey<Item> CROPS_TURMERIC = commonTag("crops/turmeric");
	public static final TagKey<Item> CROPS_WHEAT = commonTag("crops/wheat");
	public static final TagKey<Item> VANILLABEANS = commonTag("crops/vanillabeans");
	
	public static final TagKey<Item> DUSTS = commonTag("dusts");
	public static final TagKey<Item> DUSTS_BLACKPEPPER = commonTag("dusts/black_pepper");
	public static final TagKey<Item> DUSTS_CHILI = commonTag("dusts/chili_pepper");
	public static final TagKey<Item> DUSTS_CUMIN = commonTag("dusts/cumin");
	public static final TagKey<Item> DUSTS_DASHI = commonTag("dusts/dashi");
	public static final TagKey<Item> DUSTS_GLOWATONE = commonTag("dusts/glowstone");
	public static final TagKey<Item> DUSTS_PEPPER = commonTag("dusts/pepper");
	public static final TagKey<Item> DUSTS_PRISMARINE = commonTag("dusts/prismarine");
	public static final TagKey<Item> DUSTS_REDSTONE = commonTag("dusts/redstone");
	public static final TagKey<Item> DUSTS_SALT = commonTag("dusts/salt");
	public static final TagKey<Item> DUSTS_TURMERIC = commonTag("dusts/turmeric");
	
	public static final TagKey<Item> DYES = commonTag("dyes");
	public static final TagKey<Item> DYES_BLACK = commonTag("dyes/black");
	public static final TagKey<Item> DYES_BLUE = commonTag("dyes/blue");
	public static final TagKey<Item> DYES_BROWN = commonTag("dyes/brown");
	public static final TagKey<Item> DYES_CYAN = commonTag("dyes/cyan");
	public static final TagKey<Item> DYES_GRAY = commonTag("dyes/gray");
	public static final TagKey<Item> DYES_GREEN = commonTag("dyes/green");
	public static final TagKey<Item> DYES_LIGHTBLUE = commonTag("dyes/light_blue");
	public static final TagKey<Item> DYES_LIGHTGRAY = commonTag("dyes/light_gray");
	public static final TagKey<Item> DYES_LIME = commonTag("dyes/lime");
	public static final TagKey<Item> DYES_MAGENTA = commonTag("dyes/magenta");
	public static final TagKey<Item> DYES_ORANGE = commonTag("dyes/orange");
	public static final TagKey<Item> DYES_PINK = commonTag("dyes/pink");
	public static final TagKey<Item> DYES_PURPLE = commonTag("dyes/purple");
	public static final TagKey<Item> DYES_RED = commonTag("dyes/red");
	public static final TagKey<Item> DYES_WHITE = commonTag("dyes/white");
	public static final TagKey<Item> DYES_YELLOW = commonTag("dyes/yellow");
	
	public static final TagKey<Item> FEATHERS = commonTag("feathers");

	public static final TagKey<Item> FOODS = commonTag("foods");
	public static final TagKey<Item> COOKED_BEEF = commonTag("foods/cooked_beef");
	public static final TagKey<Item> COOKED_CHICKEN = commonTag("foods/cooked_chicken");
	public static final TagKey<Item> COOKED_COD = commonTag("foods/cooked_cod");
	public static final TagKey<Item> COOKED_SALMON = commonTag("foods/cooked_salmon");
	public static final TagKey<Item> COOKED_SQUID = commonTag("foods/cooked_squid");
	public static final TagKey<Item> COOKED_MUTTON = commonTag("foods/cooked_mutton");
	public static final TagKey<Item> COOKED_PORK = commonTag("foods/cooked_pork");
	public static final TagKey<Item> COOKED_RABBIT = commonTag("foods/cooked_rabbit");
	public static final TagKey<Item> RAW_BEEF = commonTag("foods/raw_beef");
	public static final TagKey<Item> RAW_CHICKEN = commonTag("foods/raw_chicken");
	public static final TagKey<Item> RAW_COD = commonTag("foods/raw_cod");
	public static final TagKey<Item> RAW_SALMON = commonTag("foods/raw_salmon");
	public static final TagKey<Item> RAW_SQUID = commonTag("foods/raw_squid");
	public static final TagKey<Item> RAW_MUTTON = commonTag("foods/raw_mutton");
	public static final TagKey<Item> RAW_PORK = commonTag("foods/raw_pork");
	public static final TagKey<Item> RAW_RABBIT = commonTag("foods/raw_rabbit");
	
	public static final TagKey<Item> FLOUR = commonTag("flour");
	public static final TagKey<Item> FRUITS = commonTag("fruits");
	public static final TagKey<Item> FRUITS_APPLE = commonTag("fruits/apple");
	public static final TagKey<Item> FRUITS_CHERRY = commonTag("fruits/cherry");
	public static final TagKey<Item> FRUITS_CITRUS = commonTag("fruits/citrus");
	public static final TagKey<Item> FRUITS_GRAPE = commonTag("fruits/grape");
	public static final TagKey<Item> FRUITS_ORANGE = commonTag("fruits/orange");
	
	public static final TagKey<Item> GEMS = commonTag("gems");
	public static final TagKey<Item> GEMS_DIAMOND = commonTag("gems/diamond");
	public static final TagKey<Item> GEMS_EMERALD = commonTag("gems/emerald");
	public static final TagKey<Item> GEMS_LAPIS = commonTag("gems/lapis");
	public static final TagKey<Item> GEMS_PRISMARINE = commonTag("gems/prismarine");
	public static final TagKey<Item> GEMS_QUARTZ = commonTag("gems/quartz");
	
	public static final TagKey<Item> GLASS_COLORLESS = commonTag("glass_blocks/colorless");
	public static final TagKey<Item> GLASS_PANES_COLORLESS = commonTag("glass_panes/colorless");
	
	public static final TagKey<Item> INGOTS = commonTag("ingots");
	public static final TagKey<Item> INGOTS_ALUMINUM = commonTag("ingots/aluminum");
	public static final TagKey<Item> INGOTS_COPPER = commonTag("ingots/copper");
	public static final TagKey<Item> INGOTS_GOLD = commonTag("ingots/gold");
	public static final TagKey<Item> INGOTS_IRON = commonTag("ingots/iron");
	public static final TagKey<Item> INGOTS_NETHERITE = commonTag("ingots/netherite");
	
	public static final TagKey<Item> LEATHER = commonTag("leather");
	public static final TagKey<Item> LEAVES = commonTag("leaves");
	public static final TagKey<Item> LEAVES_CHERRY = commonTag("leaves/sakura");
	public static final TagKey<Item> LEAVES_ACER = commonTag("leaves/acer");
	public static final TagKey<Item> LEAVES_GINKGO = commonTag("leaves/ginkgo");
	public static final TagKey<Item> LEAVES_AUTUMN_OAK = commonTag("leaves/autumn_oak");
	public static final TagKey<Item> LOGS = commonTag("logs");
	public static final TagKey<Item> LOGS_CHERRY = commonTag("logs/sakura");
	public static final TagKey<Item> LOGS_ACER = commonTag("logs/acer");
	public static final TagKey<Item> LOGS_GINKGO = commonTag("logs/ginkgo");
	public static final TagKey<Item> PLANKS = commonTag("planks");
	public static final TagKey<Item> PLANKS_CHERRY = commonTag("planks/sakura");
	public static final TagKey<Item> PLANKS_ACER = commonTag("planks/acer");
	public static final TagKey<Item> PLANKS_GINKGO = commonTag("planks/ginkgo");
	public static final TagKey<Item> WOODEN_SLABS = commonTag("wooden_slabs");
	public static final TagKey<Item> WOODEN_SLABS_CHERRY = commonTag("wooden_slabs/sakura");
	public static final TagKey<Item> WOODEN_SLABS_ACER = commonTag("wooden_slabs/acer");
	public static final TagKey<Item> WOODEN_SLABS_GINKGO = commonTag("wooden_slabs/ginkgo");
	
	public static final TagKey<Item> NUGGETS = commonTag("nuggets");
	public static final TagKey<Item> NUGGETS_GOLD = commonTag("nuggets/gold");
	public static final TagKey<Item> NUGGETS_IRON = commonTag("nuggets/iron");
	
	public static final TagKey<Item> RODS = commonTag("rods");
	public static final TagKey<Item> RODS_BLAZE = commonTag("rods/blaze");
	public static final TagKey<Item> RODS_WOODEN = commonTag("rods/wooden");
	
	public static final TagKey<Item> SAPLINGS = commonTag("saplings");
	public static final TagKey<Item> SAPLINGS_CHERRY = commonTag("saplings/sakura");
	public static final TagKey<Item> SAPLINGS_ACER = commonTag("saplings/acer");
	public static final TagKey<Item> SAPLINGS_GINKGO = commonTag("saplings/ginkgo");
	public static final TagKey<Item> SAPLINGS_AUTUMN_OAK = commonTag("saplings/autumn_oak");
	
	public static final TagKey<Item> SEEDS = commonTag("seeds");
	public static final TagKey<Item> SEEDS_AZUKIBEAN = commonTag("seeds/azukibean");
	public static final TagKey<Item> SEEDS_CABBAGE = commonTag("seeds/cabbage");
	public static final TagKey<Item> SEEDS_CHERRY = commonTag("seeds/sakura");
	public static final TagKey<Item> SEEDS_CHILI = commonTag("seeds/chili_pepper");
	public static final TagKey<Item> SEEDS_HAKUSAI = commonTag("seeds/chinese_cabbage");
	public static final TagKey<Item> SEEDS_CORN = commonTag("seeds/corn");
	public static final TagKey<Item> SEEDS_CUMIN = commonTag("seeds/cumin");
	public static final TagKey<Item> SEEDS_GREENONION = commonTag("seeds/greenonion");
	public static final TagKey<Item> SEEDS_ONION = commonTag("seeds/onion");
	public static final TagKey<Item> SEEDS_RICE = commonTag("seeds/rice");
	public static final TagKey<Item> SEEDS_SOY = commonTag("seeds/soy");
	public static final TagKey<Item> SEEDS_SOYBEAN = commonTag("seeds/soybean");
	public static final TagKey<Item> SEEDS_SPINACH = commonTag("seeds/spinach");
	public static final TagKey<Item> SEEDS_TOMATO = commonTag("seeds/tomato");
	
	public static final TagKey<Item> SLIMEBALLS = commonTag("slime_balls");
	public static final TagKey<Item> STRING = commonTag("string");

	public static final TagKey<Item> SAUCE_MAYONNAISE = commonTag("foods/sauces/mayonnaise");
	public static final TagKey<Item> SAUCE_SOYSAUSE = commonTag("foods/sauces/soysauce");
	public static final TagKey<Item> SAUCE_WORCETER = commonTag("foods/sauces/worcestersauce");
	
	public static final TagKey<Item> BUTTER = commonTag("butter");
	public static final TagKey<Item> COOKINGOIL = commonTag("cookingoil");
	public static final TagKey<Item> MISOPASTE = commonTag("misopaste");
	public static final TagKey<Item> TOFU = commonTag("tofu");
	public static final TagKey<Item> VINEGAR = commonTag("vinegar");
	public static final TagKey<Item> YEAST = commonTag("yeast");
	public static final TagKey<Item> DRY_VANILLA = commonTag("dried_vanillabeans");
	public static final TagKey<Item> DRY_TENGUSA = commonTag("dried_gelidiaceae");
	
	private static TagKey<Item> commonTag(String name) {
		return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
	}
	
	
	/* addTags */
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(BUCKETS_EMPTY).add(Items.BUCKET, Items_Teatime.MIZUOKE.get());
		this.tag(BUCKETS_WATER).add(Items.WATER_BUCKET, Items_Teatime.MIZUOKE_full.get());
		this.tag(BUCKETS_MILK).add(Items.MILK_BUCKET, Items_Teatime.MIZUOKE_Milk.get());
		this.tag(BUCKETS).addTag(BUCKETS_EMPTY).addTag(BUCKETS_WATER).addTag(BUCKETS_MILK);

		this.tag(CHEESE).add(Items_Teatime.PC_CHEESE.get());
		this.tag(FRESH_CHEESE).add(Items_Teatime.FCHEESE.get());
		this.tag(CLAMS).add(Items_Teatime.HAMAGURI.get());
		
		this.tag(CROPS_APPLE).add(Items.APPLE);
		this.tag(CROPS_BAMBOOSHOOT).add(Items_Seasonal.TAKENOKO.get());
		this.tag(CROPS_BEETROOT).add(Items.BEETROOT);
		this.tag(CROPS_BLACKPEPPER).add(Items_Teatime.PEPPER_DRY.get());
		this.tag(CROPS_CABBAGE).add(Items_Teatime.FOOD_CABBAGE.get());
		this.tag(CROPS_CARROT).add(Items.CARROT);
		this.tag(CROPS_CHERRY).add(Items_Teatime.FOOD_CHERRY.get());
		this.tag(CROPS_CHESTNUT).add(Items_Seasonal.KURI.get());
		this.tag(CROPS_CHILI).add(Items_Teatime.CHILIPEPPER.get());
		this.tag(CROPS_HAKUSAI).add(Items_Teatime.FOOD_HAKUSAI.get());
		this.tag(CROPS_CITRUS).add(Items_Teatime.FOOD_MIKAN.get());
		this.tag(CROPS_CORN).add(Items_Teatime.FOOD_CORN.get());
		this.tag(CROPS_CUMIN).add(Items_Teatime.SEEDS_CUMIN.get());
		this.tag(CROPS_GRAPE).add(Items_Teatime.FOOD_GRAPE.get());
		this.tag(CROPS_GREENONION).add(Items_Teatime.FOOD_GREENONION.get());
		this.tag(CROPS_NETHERWART).add(Items.NETHER_WART);
		this.tag(CROPS_ONION).add(Items_Teatime.FOOD_ONION.get());
		this.tag(CROPS_ORANGE).add(Items_Teatime.FOOD_MIKAN.get());
		this.tag(CROPS_PEPPER).add(Items_Teatime.PEPPER_RAW.get());
		this.tag(CROPS_PEPPERCORN).add(Items_Teatime.PEPPER_RAW.get());
		this.tag(CROPS_POTATO).add(Items.POTATO);
		this.tag(CROPS_RICE).add(Items_Teatime.INE.get(), Items_Teatime.INE_D.get());
		this.tag(CROPS_SOY).add(Items_Teatime.SAYA.get());
		this.tag(CROPS_SPINACH).add(Items_Teatime.FOOD_SPINACH.get());
		this.tag(CROPS_TEALEAF).add(Items_Teatime.CHABA.get());
		this.tag(CROPS_TOMATO).add(Items_Teatime.FOOD_TOMATO.get());
		this.tag(CROPS_TURMERIC).add(Items_Teatime.SEEDS_TURMERIC.get());
		this.tag(CROPS_WHEAT).add(Items.WHEAT);
		this.tag(VANILLABEANS).add(Items_Teatime.VANILLA_RAW.get());
		this.tag(CROPS)
		.addTag(CROPS_BAMBOOSHOOT).addTag(CROPS_BEETROOT).addTag(CROPS_BLACKPEPPER).addTag(CROPS_CABBAGE)
		.addTag(CROPS_CARROT).addTag(CROPS_CHERRY).addTag(CROPS_CHESTNUT).addTag(CROPS_CHILI)
		.addTag(CROPS_HAKUSAI).addTag(CROPS_CITRUS).addTag(CROPS_CORN).addTag(CROPS_CUMIN)
		.addTag(CROPS_GRAPE).addTag(CROPS_GREENONION).addTag(CROPS_NETHERWART).addTag(CROPS_ONION)
		.addTag(CROPS_ORANGE).addTag(CROPS_PEPPER).addTag(CROPS_PEPPERCORN).addTag(CROPS_POTATO)
		.addTag(CROPS_RICE).addTag(CROPS_SOY).addTag(CROPS_SPINACH)
		.addTag(CROPS_TEALEAF).addTag(CROPS_TOMATO).addTag(CROPS_TURMERIC).addTag(CROPS_WHEAT)
		.addTag(VANILLABEANS);

		this.tag(DUSTS_BLACKPEPPER)
		.addTag(DUSTS_PEPPER).add(Items_Teatime.DUST_PEPPER.get());
		this.tag(DUSTS_CHILI).add(Items_Teatime.DUST_CHILI.get());
		this.tag(DUSTS_CUMIN).add(Items_Teatime.DUST_CUMIN.get());
		this.tag(DUSTS_DASHI)
		.add(Items_Teatime.DASHI_bot_14.get(), Items_NoTab.DASHI_bot_24.get(), Items_NoTab.DASHI_bot_34.get(), Items_NoTab.DASHI_bot_44.get());
		this.tag(DUSTS_GLOWATONE).add(Items.GLOWSTONE_DUST);
		this.tag(DUSTS_PEPPER).add(Items_Teatime.DUST_PEPPER.get());
		this.tag(DUSTS_PRISMARINE).add(Items.PRISMARINE_CRYSTALS);
		this.tag(DUSTS_REDSTONE).add(Items.REDSTONE);
		this.tag(DUSTS_SALT).add(Items_Teatime.SHIO.get());
		this.tag(DUSTS_TURMERIC).add(Items_Teatime.DUST_TURMERIC.get());
		this.tag(DUSTS)
		.addTag(DUSTS_BLACKPEPPER).addTag(DUSTS_CHILI).addTag(DUSTS_CUMIN).addTag(DUSTS_DASHI)
		.addTag(DUSTS_GLOWATONE).addTag(DUSTS_PEPPER).addTag(DUSTS_PRISMARINE).addTag(DUSTS_REDSTONE)
		.addTag(DUSTS_SALT).addTag(DUSTS_TURMERIC);
		
		this.tag(DYES_BLACK).add(Items.BLACK_DYE);
		this.tag(DYES_BLUE).add(Items.BLUE_DYE);
		this.tag(DYES_BROWN).add(Items.BROWN_DYE);
		this.tag(DYES_CYAN).add(Items.CYAN_DYE);
		this.tag(DYES_GRAY).add(Items.GRAY_DYE);
		this.tag(DYES_GREEN).add(Items.GREEN_DYE);
		this.tag(DYES_LIGHTBLUE).add(Items.LIGHT_BLUE_DYE);
		this.tag(DYES_LIGHTGRAY).add(Items.LIGHT_GRAY_DYE);
		this.tag(DYES_LIME).add(Items.LIME_DYE);
		this.tag(DYES_MAGENTA).add(Items.MAGENTA_DYE);
		this.tag(DYES_ORANGE).add(Items.ORANGE_DYE);
		this.tag(DYES_PINK).add(Items.PINK_DYE);
		this.tag(DYES_PURPLE).add(Items.PURPLE_DYE);
		this.tag(DYES_RED).add(Items.RED_DYE);
		this.tag(DYES_WHITE).add(Items.WHITE_DYE);
		this.tag(DYES_YELLOW).add(Items.YELLOW_DYE);
		this.tag(DYES)
		.addTag(DYES_BLACK).addTag(DYES_BLUE).addTag(DYES_BROWN).addTag(DYES_CYAN)
		.addTag(DYES_GRAY).addTag(DYES_GREEN).addTag(DYES_LIGHTBLUE).addTag(DYES_LIGHTGRAY)
		.addTag(DYES_LIME).addTag(DYES_MAGENTA).addTag(DYES_ORANGE).addTag(DYES_PINK)
		.addTag(DYES_PURPLE).addTag(DYES_RED).addTag(DYES_WHITE).addTag(DYES_YELLOW);

		this.tag(FEATHERS).add(Items.FEATHER);
		this.tag(FLOUR).add(Items_Teatime.KOMUGI.get());
		
		this.tag(COOKED_BEEF).add(Items.COOKED_BEEF);
		this.tag(COOKED_CHICKEN).add(Items.COOKED_CHICKEN);
		this.tag(COOKED_COD).add(Items.COOKED_COD);
		this.tag(COOKED_SALMON).add(Items.COOKED_SALMON);
		this.tag(COOKED_SQUID).add(Items_Teatime.COOKED_IKA.get());
		this.tag(COOKED_MUTTON).add(Items.COOKED_MUTTON);
		this.tag(COOKED_PORK).add(Items.COOKED_PORKCHOP);
		this.tag(COOKED_RABBIT).add(Items.COOKED_RABBIT);
		this.tag(RAW_BEEF).add(Items.BEEF);
		this.tag(RAW_CHICKEN).add(Items.CHICKEN);
		this.tag(RAW_COD).add(Items.COD);
		this.tag(RAW_SALMON).add(Items.SALMON);
		this.tag(RAW_SQUID).add(Items_Teatime.IKA.get());
		this.tag(RAW_MUTTON).add(Items.MUTTON);
		this.tag(RAW_PORK).add(Items.PORKCHOP);
		this.tag(RAW_RABBIT).add(Items.RABBIT);

		this.tag(FRUITS_APPLE).add(Items.APPLE);
		this.tag(FRUITS_CHERRY).add(Items_Teatime.FOOD_CHERRY.get());
		this.tag(FRUITS_CITRUS).add(Items_Teatime.FOOD_MIKAN.get());
		this.tag(FRUITS_GRAPE).add(Items_Teatime.FOOD_GRAPE.get());
		this.tag(FRUITS_ORANGE).add(Items_Teatime.FOOD_MIKAN.get());
		this.tag(FRUITS)
		.addTag(FRUITS_APPLE).addTag(FRUITS_CHERRY).addTag(FRUITS_CITRUS)
		.addTag(FRUITS_GRAPE).addTag(FRUITS_ORANGE);
		
		this.tag(GLASS_COLORLESS).add(Items.GLASS);
		this.tag(GLASS_PANES_COLORLESS).add(Items.GLASS_PANE);
		
		this.tag(GEMS_DIAMOND).add(Items.DIAMOND);
		this.tag(GEMS_EMERALD).add(Items.EMERALD);
		this.tag(GEMS_LAPIS).add(Items.LAPIS_LAZULI);
		this.tag(GEMS_PRISMARINE).add(Items.PRISMARINE_CRYSTALS);
		this.tag(GEMS_QUARTZ).add(Items.QUARTZ);
		this.tag(GEMS)
		.addTag(GEMS_DIAMOND).addTag(GEMS_EMERALD).addTag(GEMS_LAPIS).addTag(GEMS_PRISMARINE).addTag(GEMS_QUARTZ);
		
		this.tag(INGOTS_ALUMINUM).add(Items_Chinjufu.ALUMINUM.get());
		this.tag(INGOTS_COPPER).add(Items.COPPER_INGOT);
		this.tag(INGOTS_GOLD).add(Items.GOLD_INGOT);
		this.tag(INGOTS_IRON).add(Items.IRON_INGOT);
		this.tag(INGOTS_NETHERITE).add(Items.NETHERITE_INGOT);
		this.tag(INGOTS)
		.addTag(INGOTS_ALUMINUM).addTag(INGOTS_COPPER)
		.addTag(INGOTS_GOLD).addTag(INGOTS_IRON).addTag(INGOTS_NETHERITE);
		
		this.tag(LEATHER).add(Items.LEATHER);
		this.tag(LEAVES_CHERRY).add(Items_Seasonal.SAKURA_flow.get());
		this.tag(LEAVES_ACER).add(Items_Seasonal.KAEDE_leaf.get());
		this.tag(LEAVES_GINKGO).add(Items_Seasonal.ICHOH_leaf.get());
		this.tag(LEAVES_AUTUMN_OAK).add(Items_Seasonal.OAKKARE_leaf.get());
		this.tag(LEAVES)
		.addTag(ItemTags.LEAVES).addTag(LEAVES_CHERRY)
		.addTag(LEAVES_ACER).addTag(LEAVES_GINKGO).addTag(LEAVES_AUTUMN_OAK);
		
		this.tag(LOGS_CHERRY).add(Items_Seasonal.SAKURA_log.get());
		this.tag(LOGS_ACER).add(Items_Seasonal.KAEDE_log.get());
		this.tag(LOGS_GINKGO).add(Items_Seasonal.ICHOH_log.get());
		this.tag(LOGS)
		.addTag(ItemTags.LOGS).addTag(LOGS_CHERRY).addTag(LOGS_ACER).addTag(LOGS_GINKGO);
		
		this.tag(PLANKS_CHERRY).add(Items_Seasonal.SAKURA_planks.get());
		this.tag(PLANKS_ACER).add(Items_Seasonal.KAEDE_planks.get());
		this.tag(PLANKS_GINKGO).add(Items_Seasonal.ICHOH_planks.get());
		this.tag(PLANKS)
		.addTag(ItemTags.PLANKS).addTag(PLANKS_CHERRY).addTag(PLANKS_ACER).addTag(PLANKS_GINKGO);
		
		this.tag(WOODEN_SLABS_CHERRY).add(Items_Seasonal.SAKURA_slabhalf.get());
		this.tag(WOODEN_SLABS_ACER).add(Items_Seasonal.KAEDE_slabhalf.get());
		this.tag(WOODEN_SLABS_GINKGO).add(Items_Seasonal.ICHOH_slabhalf.get());
		this.tag(WOODEN_SLABS)
		.addTag(ItemTags.WOODEN_SLABS).addTag(WOODEN_SLABS_CHERRY)
		.addTag(WOODEN_SLABS_ACER).addTag(WOODEN_SLABS_GINKGO);
		
		this.tag(NUGGETS_GOLD).add(Items.GOLD_NUGGET);
		this.tag(NUGGETS_IRON).add(Items.IRON_NUGGET);
		this.tag(NUGGETS).addTag(NUGGETS_GOLD).addTag(NUGGETS_IRON);
		
		this.tag(RODS_BLAZE).add(Items.BLAZE_ROD);
		this.tag(RODS_WOODEN).add(Items.STICK);
		this.tag(RODS).addTag(RODS_BLAZE).addTag(RODS_WOODEN);
		
		this.tag(SAPLINGS_CHERRY).add(Items_Seasonal.SAKURA_nae.get());
		this.tag(SAPLINGS_ACER).add(Items_Seasonal.KAEDE_nae.get());
		this.tag(SAPLINGS_GINKGO).add(Items_Seasonal.ICHOH_nae.get());
		this.tag(SAPLINGS_AUTUMN_OAK).add(Items_Seasonal.OAKKARE_nae.get());
		this.tag(SAPLINGS)
		.addTag(ItemTags.SAPLINGS).addTag(SAPLINGS_CHERRY)
		.addTag(SAPLINGS_ACER).addTag(SAPLINGS_GINKGO).addTag(SAPLINGS_AUTUMN_OAK);
		
		this.tag(SEEDS_AZUKIBEAN).add(Items_Teatime.SEEDS_AZUKI.get());
		this.tag(SEEDS_CABBAGE).add(Items_Teatime.SEEDS_CABBAGE.get());
		this.tag(SEEDS_CHERRY).add(Items_Teatime.SEEDS_CHERRY.get());
		this.tag(SEEDS_CHILI).add(Items_Teatime.SEEDS_CHILI.get());
		this.tag(SEEDS_HAKUSAI).add(Items_Teatime.SEEDS_HAKUSAI.get());
		this.tag(SEEDS_CORN).add(Items_Teatime.SEEDS_CORN.get());
		this.tag(SEEDS_CUMIN).add(Items_Teatime.SEEDS_CUMIN.get());
		this.tag(SEEDS_GREENONION).add(Items_Teatime.SEEDS_GREENONION.get());
		this.tag(SEEDS_ONION).add(Items_Teatime.SEEDS_ONION.get());
		this.tag(SEEDS_RICE).add(Items_Teatime.SEEDS_RICE.get());
		this.tag(SEEDS_SOY).add(Items_Teatime.SEEDS_SOY.get()).addTag(SEEDS_SOYBEAN);
		this.tag(SEEDS_SOYBEAN).add(Items_Teatime.SEEDS_SOY.get());
		this.tag(SEEDS_SPINACH).add(Items_Teatime.SEEDS_SPINACH.get());
		this.tag(SEEDS_TOMATO).add(Items_Teatime.SEEDS_TOMATO.get());
		this.tag(SEEDS)
		.addTag(SEEDS_AZUKIBEAN).addTag(SEEDS_CABBAGE).addTag(SEEDS_CHERRY).addTag(SEEDS_CHILI).addTag(SEEDS_HAKUSAI)
		.addTag(SEEDS_CORN).addTag(SEEDS_CUMIN).addTag(SEEDS_GREENONION).addTag(SEEDS_ONION)
		.addTag(SEEDS_RICE).addTag(SEEDS_SOY).addTag(SEEDS_SOYBEAN).addTag(SEEDS_SPINACH).addTag(SEEDS_TOMATO);

		this.tag(SLIMEBALLS).add(Items.SLIME_BALL);
		this.tag(STRING).add(Items_Seasonal.ORIITO.get());
		
		this.tag(SAUCE_MAYONNAISE)
		.add(Items_Teatime.MAYO_bot_14.get(), Items_NoTab.MAYO_bot_24.get(), 
				Items_NoTab.MAYO_bot_34.get(), Items_NoTab.MAYO_bot_44.get());
		this.tag(SAUCE_SOYSAUSE)
		.add(Items_Teatime.SHOUYU_bot_14.get(), Items_NoTab.SHOUYU_bot_24.get(), 
				Items_NoTab.SHOUYU_bot_34.get(), Items_NoTab.SHOUYU_bot_44.get());
		this.tag(SAUCE_WORCETER)
		.add(Items_Teatime.OSAUCE_bot_14.get(), Items_NoTab.OSAUCE_bot_24.get(), 
				Items_NoTab.OSAUCE_bot_34.get(), Items_NoTab.OSAUCE_bot_44.get());
		
		this.tag(BUTTER).add(Items_Teatime.BUTTER.get());
		this.tag(COOKINGOIL).add(Items_Teatime.SOYOIL_bot_12.get(), Items_NoTab.SOYOIL_bot_22.get());
		this.tag(MISOPASTE).add(Items_Teatime.MISO.get());
		this.tag(TOFU).add(Items_Teatime.TOUFU.get());
		this.tag(VINEGAR).add(Items_Teatime.KOMEZU_bot_12.get(), Items_NoTab.KOMEZU_bot_22.get());
		this.tag(YEAST).add(Items_Teatime.KOUBO.get());
		this.tag(DRY_VANILLA)
		.add(Items_Teatime.VANILLA_bot_14.get(), Items_NoTab.VANILLA_bot_24.get(), 
				Items_NoTab.VANILLA_bot_34.get(), Items_NoTab.VANILLA_bot_44.get());
		this.tag(DRY_TENGUSA).add(Items_Teatime.TENGUSA_DRY.get());
	}
	
	@Override
	public String getName() {
		return "ChinjufuMod Common Item Tags";
	}
}
