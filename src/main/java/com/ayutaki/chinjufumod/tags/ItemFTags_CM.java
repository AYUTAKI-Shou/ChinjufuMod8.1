package com.ayutaki.chinjufumod.tags;

import com.ayutaki.chinjufumod.ChinjufuMod;
import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_NoTab;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemFTags_CM extends ItemTagsProvider {
	
	public ItemFTags_CM(DataGenerator gen, BlockTagsProvider blockTag, ExistingFileHelper helper) {
		super(gen, blockTag, ChinjufuMod.MOD_ID, helper);
	}

	/* TagKey */
	public static final ITag.INamedTag<Item> ADMIRAL_STAMP = create("admiral_stamp");
	public static final ITag.INamedTag<Item> BONE_MEAL = create("bone_meal");
	public static final ITag.INamedTag<Item> COOKING_WATER = create("cooking_water");
	public static final ITag.INamedTag<Item> KAGAMIMOCHI_CITRUS = create("kagamimochi_citrus");
	public static final ITag.INamedTag<Item> MATERIALS_BOOTS = create("materials_boots");
	public static final ITag.INamedTag<Item> MATERIALS_FUEL = create("materials_fuel");
	public static final ITag.INamedTag<Item> MATERIALS_LAMP = create("materials_lamp");
	public static final ITag.INamedTag<Item> MATERIALS_OIL = create("materials_oil");
	public static final ITag.INamedTag<Item> MATERIALS_POTATO = create("materials_potato");
	public static final ITag.INamedTag<Item> MATERIALS_STRAW = create("materials_straw");
	public static final ITag.INamedTag<Item> MATERIALS_TATAMI = create("materials_tatami");
	public static final ITag.INamedTag<Item> MATERIALS_YARN = create("materials_yarn");
	public static final ITag.INamedTag<Item> MATERIALS_SEAGASS = create("materials_seagrass");
	
	/** forge **/
	public static final ITag.INamedTag<Item> BUCKETS = forgeTag("buckets");
	public static final ITag.INamedTag<Item> BUCKETS_EMPTY = forgeTag("buckets/empty");
	public static final ITag.INamedTag<Item> BUCKETS_WATER = forgeTag("buckets/water");
	public static final ITag.INamedTag<Item> BUCKETS_MILK = forgeTag("buckets/milk");
	
	public static final ITag.INamedTag<Item> CARPETS = forgeTag("carpets");
	
	public static final ITag.INamedTag<Item> CONDIMENTS = forgeTag("condiments");
	public static final ITag.INamedTag<Item> MAYONAISE = forgeTag("condiments/mayonaise");
	public static final ITag.INamedTag<Item> WORCETERSOUCE = forgeTag("condiments/worcestersauce");
	
	public static final ITag.INamedTag<Item> COOKED_BEEF = forgeTag("cooked_beef");
	public static final ITag.INamedTag<Item> COOKED_CHICKEN = forgeTag("cooked_chicken");
	public static final ITag.INamedTag<Item> COOKED_FISHES = forgeTag("cooked_fishes");
	public static final ITag.INamedTag<Item> COOKED_CLAM = forgeTag("cooked_fishes/clam");
	public static final ITag.INamedTag<Item> COOKED_COD = forgeTag("cooked_fishes/cod");
	public static final ITag.INamedTag<Item> COOKED_SALMON = forgeTag("cooked_fishes/salmon");
	public static final ITag.INamedTag<Item> COOKED_SQUID = forgeTag("cooked_fishes/squid");
	public static final ITag.INamedTag<Item> COOKED_MUTTON = forgeTag("cooked_mutton");
	public static final ITag.INamedTag<Item> COOKED_PORK = forgeTag("cooked_pork");
	public static final ITag.INamedTag<Item> COOKED_RABBIT = forgeTag("cooked_rabbit");
	
	public static final ITag.INamedTag<Item> COOKEDBEEF = forgeTag("cookedbeef");
	public static final ITag.INamedTag<Item> COOKEDCHICKEN = forgeTag("cookedchicken");
	public static final ITag.INamedTag<Item> COOKEDMUTTON = forgeTag("cookedmutton");
	public static final ITag.INamedTag<Item> COOKEDPORK = forgeTag("cookedpork");
	public static final ITag.INamedTag<Item> COOKEDRABBIT = forgeTag("cookedrabbit");
	
	public static final ITag.INamedTag<Item> CROPS = forgeTag("crops");
	public static final ITag.INamedTag<Item> CROPS_APPLE = forgeTag("crops/apple");
	public static final ITag.INamedTag<Item> CROPS_BAMBOOSHOOT = forgeTag("crops/bambooshoot");
	public static final ITag.INamedTag<Item> CROPS_BEETROOT = forgeTag("crops/beetroot");
	public static final ITag.INamedTag<Item> CROPS_BLACKPEPPER = forgeTag("crops/blackpepper");
	public static final ITag.INamedTag<Item> CROPS_CABBAGE = forgeTag("crops/cabbage");
	public static final ITag.INamedTag<Item> CROPS_CARROT = forgeTag("crops/carrot");
	public static final ITag.INamedTag<Item> CROPS_CHERRY = forgeTag("crops/cherry");
	public static final ITag.INamedTag<Item> CROPS_CHESTNUT = forgeTag("crops/chestnut");
	public static final ITag.INamedTag<Item> CROPS_CHILI = forgeTag("crops/chilipepper");
	public static final ITag.INamedTag<Item> CROPS_HAKUSAI = forgeTag("crops/chinesecabbage");
	public static final ITag.INamedTag<Item> CROPS_CITRUS = forgeTag("crops/citrus");
	public static final ITag.INamedTag<Item> CROPS_CORN = forgeTag("crops/corn");
	public static final ITag.INamedTag<Item> CROPS_CUMIN = forgeTag("crops/cumin");
	public static final ITag.INamedTag<Item> CROPS_GRAPE = forgeTag("crops/grape");
	public static final ITag.INamedTag<Item> CROPS_GREENONION = forgeTag("crops/greenonion");
	public static final ITag.INamedTag<Item> CROPS_NETHERWART = forgeTag("crops/nether_wart");
	public static final ITag.INamedTag<Item> CROPS_ONION = forgeTag("crops/onion");
	public static final ITag.INamedTag<Item> CROPS_ORANGE = forgeTag("crops/orange");
	public static final ITag.INamedTag<Item> CROPS_PEPPER = forgeTag("crops/pepper");
	public static final ITag.INamedTag<Item> CROPS_PEPPERCORN = forgeTag("crops/peppercorn");
	public static final ITag.INamedTag<Item> CROPS_POTATO = forgeTag("crops/potato");
	public static final ITag.INamedTag<Item> CROPS_RICE = forgeTag("crops/rice");
	public static final ITag.INamedTag<Item> CROPS_SOY = forgeTag("crops/soy");
	public static final ITag.INamedTag<Item> CROPS_SPINACH = forgeTag("crops/spinach");
	public static final ITag.INamedTag<Item> CROPS_TEA = forgeTag("crops/tea");
	public static final ITag.INamedTag<Item> CROPS_TEALEAF = forgeTag("crops/tealeaf");
	public static final ITag.INamedTag<Item> CROPS_TOMATO = forgeTag("crops/tomato");
	public static final ITag.INamedTag<Item> CROPS_TURMERIC = forgeTag("crops/turmeric");
	public static final ITag.INamedTag<Item> CROPS_WHEAT = forgeTag("crops/wheat");
	public static final ITag.INamedTag<Item> VANILLABEAN = forgeTag("crops/vanillabean");
	public static final ITag.INamedTag<Item> VANILLABEANS = forgeTag("crops/vanillabeans");

	public static final ITag.INamedTag<Item> DUSTS = forgeTag("dusts");
	public static final ITag.INamedTag<Item> DUSTS_BLACKPEPPER = forgeTag("dusts/blackpepper");
	public static final ITag.INamedTag<Item> DUSTS_CHILI = forgeTag("dusts/chili");
	public static final ITag.INamedTag<Item> DUSTS_CUMIN = forgeTag("dusts/cumin");
	public static final ITag.INamedTag<Item> DUSTS_DASHI = forgeTag("dusts/dashi");
	public static final ITag.INamedTag<Item> DUSTS_GLOWATONE = forgeTag("dusts/glowstone");
	public static final ITag.INamedTag<Item> DUSTS_PEPPER = forgeTag("dusts/pepper");
	public static final ITag.INamedTag<Item> DUSTS_PRISMARINE = forgeTag("dusts/prismarine");
	public static final ITag.INamedTag<Item> DUSTS_REDSTONE = forgeTag("dusts/redstone");
	public static final ITag.INamedTag<Item> DUSTS_SALT = forgeTag("dusts/salt");
	public static final ITag.INamedTag<Item> DUSTS_TURMERIC = forgeTag("dusts/turmeric");

	public static final ITag.INamedTag<Item> DYES = forgeTag("dyes");
	public static final ITag.INamedTag<Item> DYES_BLACK = forgeTag("dyes/black");
	public static final ITag.INamedTag<Item> DYES_BLUE = forgeTag("dyes/blue");
	public static final ITag.INamedTag<Item> DYES_BROWN = forgeTag("dyes/brown");
	public static final ITag.INamedTag<Item> DYES_CYAN = forgeTag("dyes/cyan");
	public static final ITag.INamedTag<Item> DYES_GRAY = forgeTag("dyes/gray");
	public static final ITag.INamedTag<Item> DYES_GREEN = forgeTag("dyes/green");
	public static final ITag.INamedTag<Item> DYES_LIGHTBLUE = forgeTag("dyes/light_blue");
	public static final ITag.INamedTag<Item> DYES_LIGHTGRAY = forgeTag("dyes/light_gray");
	public static final ITag.INamedTag<Item> DYES_LIME = forgeTag("dyes/lime");
	public static final ITag.INamedTag<Item> DYES_MAGENTA = forgeTag("dyes/magenta");
	public static final ITag.INamedTag<Item> DYES_ORANGE = forgeTag("dyes/orange");
	public static final ITag.INamedTag<Item> DYES_PINK = forgeTag("dyes/pink");
	public static final ITag.INamedTag<Item> DYES_PURPLE = forgeTag("dyes/purple");
	public static final ITag.INamedTag<Item> DYES_RED = forgeTag("dyes/red");
	public static final ITag.INamedTag<Item> DYES_WHITE = forgeTag("dyes/white");
	public static final ITag.INamedTag<Item> DYES_YELLOW = forgeTag("dyes/yellow");
	
	public static final ITag.INamedTag<Item> EGGS = forgeTag("eggs");
	public static final ITag.INamedTag<Item> FEATHERS = forgeTag("feathers");
	public static final ITag.INamedTag<Item> FLOWERS = forgeTag("flowers");
	public static final ITag.INamedTag<Item> FLOWERS_CHERRY = forgeTag("flowers/cherry");
	
	public static final ITag.INamedTag<Item> FOODS = forgeTag("foods");
	public static final ITag.INamedTag<Item> FOODS_BLACKPEPPER = forgeTag("foods/blackpepper");
	public static final ITag.INamedTag<Item> FOODS_BUTTER = forgeTag("foods/butter");
	public static final ITag.INamedTag<Item> FOODS_CHEESE = forgeTag("foods/cheese");
	public static final ITag.INamedTag<Item> FOODS_CHILI = forgeTag("foods/chili");
	public static final ITag.INamedTag<Item> FOODS_FLOUR = forgeTag("foods/flour");
	public static final ITag.INamedTag<Item> FOODS_FRESHCHEESE = forgeTag("foods/freshcheese");
	public static final ITag.INamedTag<Item> FOODS_MISOPASTE = forgeTag("foods/misopaste");
	public static final ITag.INamedTag<Item> FOODS_OIL = forgeTag("foods/oil");
	public static final ITag.INamedTag<Item> FOODS_PEPPER= forgeTag("foods/pepper");
	public static final ITag.INamedTag<Item> FOODS_SALT = forgeTag("foods/salt");
	public static final ITag.INamedTag<Item> FOODS_SILKENTOFU = forgeTag("foods/silkentofu");
	public static final ITag.INamedTag<Item> FOODS_SILKENTOUFU = forgeTag("foods/silkentoufu");
	public static final ITag.INamedTag<Item> FOODS_SOYSAUSE = forgeTag("foods/soysauce");
	public static final ITag.INamedTag<Item> FOODS_VINEGAR = forgeTag("foods/vinegar");
	
	public static final ITag.INamedTag<Item> FRUITS = forgeTag("fruits");
	public static final ITag.INamedTag<Item> FRUITS_APPLE = forgeTag("fruits/apple");
	public static final ITag.INamedTag<Item> FRUITS_CHERRY = forgeTag("fruits/cherry");
	public static final ITag.INamedTag<Item> FRUITS_CITRUS = forgeTag("fruits/citrus");
	public static final ITag.INamedTag<Item> FRUITS_GRAPE = forgeTag("fruits/grape");
	public static final ITag.INamedTag<Item> FRUITS_ORANGE = forgeTag("fruits/orange");
	
	public static final ITag.INamedTag<Item> GEMS = forgeTag("gems");
	public static final ITag.INamedTag<Item> GEMS_DIAMOND = forgeTag("gems/diamond");
	public static final ITag.INamedTag<Item> GEMS_EMERALD = forgeTag("gems/emerald");
	public static final ITag.INamedTag<Item> GEMS_LAPIS = forgeTag("gems/lapis");
	public static final ITag.INamedTag<Item> GEMS_PRISMARINE = forgeTag("gems/prismarine");
	public static final ITag.INamedTag<Item> GEMS_QUARTZ = forgeTag("gems/quartz");
	
	public static final ITag.INamedTag<Item> GLASS = forgeTag("glass");
	public static final ITag.INamedTag<Item> GLASS_BLACK = forgeTag("glass/black");
	public static final ITag.INamedTag<Item> GLASS_BLUE = forgeTag("glass/blue");
	public static final ITag.INamedTag<Item> GLASS_BROWN = forgeTag("glass/brown");
	public static final ITag.INamedTag<Item> GLASS_CYAN = forgeTag("glass/cyan");
	public static final ITag.INamedTag<Item> GLASS_GRAY = forgeTag("glass/gray");
	public static final ITag.INamedTag<Item> GLASS_GREEN = forgeTag("glass/green");
	public static final ITag.INamedTag<Item> GLASS_LIGHTBLUE = forgeTag("glass/light_blue");
	public static final ITag.INamedTag<Item> GLASS_LIGHTGRAY = forgeTag("glass/light_gray");
	public static final ITag.INamedTag<Item> GLASS_LIME = forgeTag("glass/lime");
	public static final ITag.INamedTag<Item> GLASS_MAGENTA = forgeTag("glass/magenta");
	public static final ITag.INamedTag<Item> GLASS_ORANGE = forgeTag("glass/orange");
	public static final ITag.INamedTag<Item> GLASS_PINK = forgeTag("glass/pink");
	public static final ITag.INamedTag<Item> GLASS_PURPLE = forgeTag("glass/purple");
	public static final ITag.INamedTag<Item> GLASS_RED = forgeTag("glass/red");
	public static final ITag.INamedTag<Item> GLASS_WHITE = forgeTag("glass/white");
	public static final ITag.INamedTag<Item> GLASS_YELLOW = forgeTag("glass/yellow");
	public static final ITag.INamedTag<Item> GLASS_COLORLESS = forgeTag("glass/colorless");
	
	public static final ITag.INamedTag<Item> GLASS_PANES = forgeTag("glass_panes");
	public static final ITag.INamedTag<Item> GLASS_PANES_BLACK = forgeTag("glass_panes/black");
	public static final ITag.INamedTag<Item> GLASS_PANES_BLUE = forgeTag("glass_panes/blue");
	public static final ITag.INamedTag<Item> GLASS_PANES_BROWN = forgeTag("glass_panes/brown");
	public static final ITag.INamedTag<Item> GLASS_PANES_CYAN = forgeTag("glass_panes/cyan");
	public static final ITag.INamedTag<Item> GLASS_PANES_GRAY = forgeTag("glass_panes/gray");
	public static final ITag.INamedTag<Item> GLASS_PANES_GREEN = forgeTag("glass_panes/green");
	public static final ITag.INamedTag<Item> GLASS_PANES_LIGHTBLUE = forgeTag("glass_panes/light_blue");
	public static final ITag.INamedTag<Item> GLASS_PANES_LIGHTGRAY = forgeTag("glass_panes/light_gray");
	public static final ITag.INamedTag<Item> GLASS_PANES_LIME = forgeTag("glass_panes/lime");
	public static final ITag.INamedTag<Item> GLASS_PANES_MAGENTA = forgeTag("glass_panes/magenta");
	public static final ITag.INamedTag<Item> GLASS_PANES_ORANGE = forgeTag("glass_panes/orange");
	public static final ITag.INamedTag<Item> GLASS_PANES_PINK = forgeTag("glass_panes/pink");
	public static final ITag.INamedTag<Item> GLASS_PANES_PURPLE = forgeTag("glass_panes/purple");
	public static final ITag.INamedTag<Item> GLASS_PANES_RED = forgeTag("glass_panes/red");
	public static final ITag.INamedTag<Item> GLASS_PANES_WHITE = forgeTag("glass_panes/white");
	public static final ITag.INamedTag<Item> GLASS_PANES_YELLOW = forgeTag("glass_panes/yellow");
	public static final ITag.INamedTag<Item> GLASS_PANES_COLORLESS = forgeTag("glass_panes/colorless");
	
	public static final ITag.INamedTag<Item> INGOTS = forgeTag("ingots");
	public static final ITag.INamedTag<Item> INGOTS_ALUMINIUM = forgeTag("ingots/aluminium");
	public static final ITag.INamedTag<Item> INGOTS_ALUMINUM = forgeTag("ingots/aluminum");
	public static final ITag.INamedTag<Item> INGOTS_GOLD = forgeTag("ingots/gold");
	public static final ITag.INamedTag<Item> INGOTS_IRON = forgeTag("ingots/iron");
	public static final ITag.INamedTag<Item> INGOTS_NETHERITE = forgeTag("ingots/netherite");
	
	public static final ITag.INamedTag<Item> NUGGETS = forgeTag("nuggets");
	public static final ITag.INamedTag<Item> NUGGETS_GOLD = forgeTag("nuggets/gold");
	public static final ITag.INamedTag<Item> NUGGETS_IRON = forgeTag("nuggets/iron");
	
	public static final ITag.INamedTag<Item> ITEMS = forgeTag("items");
	public static final ITag.INamedTag<Item> ITEMS_SALT = forgeTag("items/salt");
	public static final ITag.INamedTag<Item> ITEMS_YEAST = forgeTag("items/yeast");
	
	public static final ITag.INamedTag<Item> LEATHER = forgeTag("leather");
	
	public static final ITag.INamedTag<Item> LEAVES = forgeTag("leaves");
	public static final ITag.INamedTag<Item> LEAVES_CHERRY = forgeTag("leaves/cherry");
	public static final ITag.INamedTag<Item> LEAVES_ACER = forgeTag("leaves/acer");
	public static final ITag.INamedTag<Item> LEAVES_GINKGO = forgeTag("leaves/ginkgo");
	public static final ITag.INamedTag<Item> LEAVES_AUTUMN_OAK = forgeTag("leaves/autumn_oak");
	public static final ITag.INamedTag<Item> LOGS = forgeTag("logs");
	public static final ITag.INamedTag<Item> LOGS_CHERRY = forgeTag("logs/cherry");
	public static final ITag.INamedTag<Item> LOGS_ACER = forgeTag("logs/acer");
	public static final ITag.INamedTag<Item> LOGS_GINKGO = forgeTag("logs/ginkgo");
	public static final ITag.INamedTag<Item> PLANKS = forgeTag("planks");
	public static final ITag.INamedTag<Item> PLANKS_CHERRY = forgeTag("planks/cherry");
	public static final ITag.INamedTag<Item> PLANKS_ACER = forgeTag("planks/acer");
	public static final ITag.INamedTag<Item> PLANKS_GINKGO = forgeTag("planks/ginkgo");
	
	public static final ITag.INamedTag<Item> RAW_BEEF = forgeTag("raw_beef");
	public static final ITag.INamedTag<Item> RAW_CHICKEN = forgeTag("raw_chicken");
	public static final ITag.INamedTag<Item> RAW_FISHES = forgeTag("raw_fishes");
	public static final ITag.INamedTag<Item> RAW_CLAM = forgeTag("raw_fishes/clam");
	public static final ITag.INamedTag<Item> RAW_COD = forgeTag("raw_fishes/cod");
	public static final ITag.INamedTag<Item> RAW_SALMON = forgeTag("raw_fishes/salmon");
	public static final ITag.INamedTag<Item> RAW_SQUID = forgeTag("raw_fishes/squid");
	public static final ITag.INamedTag<Item> RAW_MUTTON = forgeTag("raw_mutton");
	public static final ITag.INamedTag<Item> RAW_PORK = forgeTag("raw_pork");
	public static final ITag.INamedTag<Item> RAW_RABBIT = forgeTag("raw_rabbit");
	
	public static final ITag.INamedTag<Item> RAWBEEF = forgeTag("rawbeef");
	public static final ITag.INamedTag<Item> RAWCHICKEN = forgeTag("rawchicken");
	public static final ITag.INamedTag<Item> RAWMUTTON = forgeTag("rawmutton");
	public static final ITag.INamedTag<Item> RAWPORK = forgeTag("rawpork");
	public static final ITag.INamedTag<Item> RAWRABBIT = forgeTag("rawrabbit");
	
	public static final ITag.INamedTag<Item> RODS = forgeTag("rods");
	public static final ITag.INamedTag<Item> RODS_BLAZE = forgeTag("rods/blaze");
	public static final ITag.INamedTag<Item> RODS_WOODEN = forgeTag("rods/wooden");
	
	public static final ITag.INamedTag<Item> SAPLINGS = forgeTag("saplings");
	public static final ITag.INamedTag<Item> SAPLINGS_CHERRY = forgeTag("saplings/cherry");
	public static final ITag.INamedTag<Item> SAPLINGS_ACER = forgeTag("saplings/acer");
	public static final ITag.INamedTag<Item> SAPLINGS_GINKGO = forgeTag("saplings/ginkgo");
	public static final ITag.INamedTag<Item> SAPLINGS_AUTUMN_OAK = forgeTag("saplings/autumn_oak");
	
	public static final ITag.INamedTag<Item> SAUCE = forgeTag("sauces");
	public static final ITag.INamedTag<Item> SAUCE_MAYONNAISE = forgeTag("sauces/mayonnaise");
	public static final ITag.INamedTag<Item> SAUCE_SOYSAUSE = forgeTag("sauces/soysauce");
	public static final ITag.INamedTag<Item> SAUCE_WORCETER = forgeTag("sauces/worcestersauce");
	
	public static final ITag.INamedTag<Item> SEEDS = forgeTag("seeds");
	public static final ITag.INamedTag<Item> SEEDS_AZUKI = forgeTag("seeds/azukibean");
	public static final ITag.INamedTag<Item> SEEDS_CABBAGE = forgeTag("seeds/cabbage");
	public static final ITag.INamedTag<Item> SEEDS_CHERRY = forgeTag("seeds/cherry");
	public static final ITag.INamedTag<Item> SEEDS_CHILI = forgeTag("seeds/chilipepper");
	public static final ITag.INamedTag<Item> SEEDS_HAKUSAI = forgeTag("seeds/chinesecabbage");
	public static final ITag.INamedTag<Item> SEEDS_CORN = forgeTag("seeds/corn");
	public static final ITag.INamedTag<Item> SEEDS_CUMIN = forgeTag("seeds/cumin");
	public static final ITag.INamedTag<Item> SEEDS_GREENONION = forgeTag("seeds/greenonion");
	public static final ITag.INamedTag<Item> SEEDS_ONION = forgeTag("seeds/onion");
	public static final ITag.INamedTag<Item> SEEDS_RICE = forgeTag("seeds/rice");
	public static final ITag.INamedTag<Item> SEEDS_SOY = forgeTag("seeds/soy");
	public static final ITag.INamedTag<Item> SEEDS_SOYBEAN = forgeTag("seeds/soybean");
	public static final ITag.INamedTag<Item> SEEDS_SPINACH = forgeTag("seeds/spinach");
	public static final ITag.INamedTag<Item> SEEDS_TOMATO = forgeTag("seeds/tomato");

	public static final ITag.INamedTag<Item> SLIMEBALLS = forgeTag("slimeballs");
	
	public static final ITag.INamedTag<Item> SPICES = forgeTag("spices");
	public static final ITag.INamedTag<Item> SPICES_BLACKPEPPER = forgeTag("spices/blackpepper");
	public static final ITag.INamedTag<Item> SPICES_CHILI = forgeTag("spices/chili");
	public static final ITag.INamedTag<Item> SPICES_CUMIN = forgeTag("spices/cumin");
	public static final ITag.INamedTag<Item> SPICES_PEPPER = forgeTag("spices/pepper");
	public static final ITag.INamedTag<Item> SPICES_TURMERIC = forgeTag("spices/turmeric");
	public static final ITag.INamedTag<Item> SPICES_VANILLA = forgeTag("spices/vanilla");
	public static final ITag.INamedTag<Item> DRY_TENGUSA = forgeTag("dried_gelidiaceae");
	
	public static final ITag.INamedTag<Item> STRING = forgeTag("string");
	public static final ITag.INamedTag<Item> SUGAR = forgeTag("sugar");
	
	public static final ITag.INamedTag<Item> WOODEN_SLABS = forgeTag("wooden_slabs");
	public static final ITag.INamedTag<Item> WOODEN_SLABS_CHERRY = forgeTag("wooden_slabs/cherry");
	public static final ITag.INamedTag<Item> WOODEN_SLABS_ACER = forgeTag("wooden_slabs/acer");
	public static final ITag.INamedTag<Item> WOODEN_SLABS_GINKGO = forgeTag("wooden_slabs/ginkgo");
	
	public static final ITag.INamedTag<Item> WOOL = forgeTag("wool");
	
	private static ITag.INamedTag<Item> create(String name) {
		return ItemTags.createOptional(new ResourceLocation(ChinjufuMod.MOD_ID, name));
	}
	
	private static ITag.INamedTag<Item> forgeTag(String name) {
		return ItemTags.createOptional(new ResourceLocation("forge", name));
	}
	
	/* addTags */
	@Override
	protected void addTags() {
		/** chinjufumod **/
		this.tag(ADMIRAL_STAMP).add(Items_NoTab.ADMIRAL_STAMP, Items_Chinjufu.ADMIRAL_STAMPB);
		this.tag(BONE_MEAL).add(Items_NoTab.HAMAGURI_KARA, Items_Teatime.SAKEKASU);
		this.tag(COOKING_WATER).add(Items.POTION, Items_Teatime.KEIRYO_CUP_full);
		this.tag(KAGAMIMOCHI_CITRUS).addTag(CROPS_CITRUS).addTag(CROPS_ORANGE);
		this.tag(MATERIALS_BOOTS).add(Items.BLACK_CARPET, Items.LEATHER);
		this.tag(MATERIALS_FUEL).add(Items.COAL, Items.CHARCOAL);
		this.tag(MATERIALS_LAMP).addTag(INGOTS_ALUMINUM).addTag(INGOTS_IRON);
		this.tag(MATERIALS_OIL).add(Items.SLIME_BALL, Items_Teatime.SOYOIL_bot_12, Items_NoTab.SOYOIL_bot_22);
		this.tag(MATERIALS_POTATO).addTag(CROPS_POTATO).add(Items.POISONOUS_POTATO);
		this.tag(MATERIALS_STRAW).add(Items_Teatime.INEWARA).addTag(CROPS_WHEAT);
		this.tag(MATERIALS_TATAMI)
		.add(Items.SUGAR_CANE, Items_Teatime.INEWARA).addTag(CROPS_WHEAT);
		this.tag(MATERIALS_YARN).add(Items.STRING, Items_Seasonal.ORIITO);
		this.tag(MATERIALS_SEAGASS).add(Items.SEAGRASS, Items_Teatime.NORI_N);

		
		/** forge **/
		this.tag(BUCKETS_EMPTY).add(Items.BUCKET, Items_Teatime.MIZUOKE);
		this.tag(BUCKETS_WATER).add(Items.WATER_BUCKET, Items_Teatime.MIZUOKE_full);
		this.tag(BUCKETS_MILK).add(Items.MILK_BUCKET, Items_Teatime.MIZUOKE_Milk);
		this.tag(BUCKETS).addTag(BUCKETS_EMPTY).addTag(BUCKETS_WATER).addTag(BUCKETS_MILK);
		
		this.tag(CARPETS)
		.add(Items.WHITE_CARPET, Items.ORANGE_CARPET, Items.MAGENTA_CARPET, Items.LIGHT_BLUE_CARPET,
				Items.YELLOW_CARPET, Items.LIME_CARPET, Items.PINK_CARPET, Items.GRAY_CARPET, 
				Items.LIGHT_GRAY_CARPET, Items.CYAN_CARPET, Items.PURPLE_CARPET, Items.BLUE_CARPET,
				Items.BROWN_CARPET, Items.GREEN_CARPET, Items.RED_CARPET, Items.BLACK_CARPET);
		
		this.tag(MAYONAISE)
		.add(Items_Teatime.MAYO_bot_14, Items_NoTab.MAYO_bot_24, Items_NoTab.MAYO_bot_34, Items_NoTab.MAYO_bot_44);
		this.tag(WORCETERSOUCE)
		.add(Items_Teatime.OSAUCE_bot_14, Items_NoTab.OSAUCE_bot_24, Items_NoTab.OSAUCE_bot_34, Items_NoTab.OSAUCE_bot_44);
		this.tag(CONDIMENTS).addTag(MAYONAISE).addTag(WORCETERSOUCE);
		
		this.tag(COOKED_BEEF).add(Items.COOKED_BEEF).addTag(COOKEDBEEF);
		this.tag(COOKED_CHICKEN).add(Items.COOKED_CHICKEN).addTag(COOKEDCHICKEN);
		this.tag(COOKED_CLAM).add(Items_Teatime.HAMAGURI_COOK);
		this.tag(COOKED_COD).add(Items.COOKED_COD);
		this.tag(COOKED_SALMON).add(Items.COOKED_SALMON);
		this.tag(COOKED_SQUID).add(Items_Teatime.COOKED_IKA);
		this.tag(COOKED_FISHES).addTag(COOKED_COD).addTag(COOKED_SALMON);
		this.tag(COOKED_MUTTON).add(Items.COOKED_MUTTON).addTag(COOKEDMUTTON);
		this.tag(COOKED_PORK).add(Items.COOKED_PORKCHOP).addTag(COOKEDPORK);
		this.tag(COOKED_RABBIT).add(Items.COOKED_RABBIT).addTag(COOKEDRABBIT);
		
		this.tag(COOKEDBEEF).add(Items.COOKED_BEEF);
		this.tag(COOKEDCHICKEN).add(Items.COOKED_CHICKEN);
		this.tag(COOKEDMUTTON).add(Items.COOKED_MUTTON);
		this.tag(COOKEDPORK).add(Items.COOKED_PORKCHOP);
		this.tag(COOKEDRABBIT).add(Items.COOKED_RABBIT);
		
		this.tag(CROPS_APPLE).addTag(FRUITS_APPLE);
		this.tag(CROPS_BAMBOOSHOOT).add(Items_Seasonal.TAKENOKO);
		this.tag(CROPS_BEETROOT).add(Items.BEETROOT);
		this.tag(CROPS_BLACKPEPPER).add(Items_Teatime.PEPPER_DRY);
		this.tag(CROPS_CABBAGE).add(Items_Teatime.FOOD_CABBAGE);
		this.tag(CROPS_CARROT).add(Items.CARROT);
		this.tag(CROPS_CHERRY).addTag(FRUITS_CHERRY).add(Items_Teatime.FOOD_CHERRY);
		this.tag(CROPS_CHESTNUT).add(Items_Seasonal.KURI);
		this.tag(CROPS_CHILI).add(Items_Teatime.CHILIPEPPER);
		this.tag(CROPS_HAKUSAI).add(Items_Teatime.FOOD_HAKUSAI);
		this.tag(CROPS_CITRUS).addTag(FRUITS_CITRUS).add(Items_Teatime.FOOD_MIKAN);
		this.tag(CROPS_CORN).add(Items_Teatime.FOOD_CORN);
		this.tag(CROPS_CUMIN).add(Items_Teatime.SEEDS_CUMIN).addTag(SEEDS_CUMIN);
		this.tag(CROPS_GRAPE).addTag(FRUITS_GRAPE).add(Items_Teatime.FOOD_GRAPE);
		this.tag(CROPS_GREENONION).add(Items_Teatime.FOOD_GREENONION);
		this.tag(CROPS_NETHERWART).add(Items.NETHER_WART);
		this.tag(CROPS_ONION).add(Items_Teatime.FOOD_ONION);
		this.tag(CROPS_ORANGE).addTag(FRUITS_ORANGE).add(Items_Teatime.FOOD_MIKAN);
		this.tag(CROPS_PEPPER).add(Items_Teatime.PEPPER_RAW);
		this.tag(CROPS_PEPPERCORN).add(Items_Teatime.PEPPER_RAW);
		this.tag(CROPS_POTATO).add(Items.POTATO);
		this.tag(CROPS_RICE).add(Items_Teatime.INE, Items_Teatime.INE_D);
		this.tag(CROPS_SOY).add(Items_Teatime.SAYA);
		this.tag(CROPS_SPINACH).add(Items_Teatime.FOOD_SPINACH);
		this.tag(CROPS_TEA).add(Items_Teatime.CHABA);
		this.tag(CROPS_TEALEAF).addTag(CROPS_TEA).add(Items_Teatime.CHABA);
		this.tag(CROPS_TOMATO).add(Items_Teatime.FOOD_TOMATO);
		this.tag(CROPS_TURMERIC).add(Items_Teatime.SEEDS_TURMERIC);
		this.tag(CROPS_WHEAT).add(Items.WHEAT);
		this.tag(VANILLABEAN).add(Items_Teatime.VANILLA_RAW);
		this.tag(VANILLABEANS).add(Items_Teatime.VANILLA_RAW).addTag(VANILLABEAN);
		this.tag(CROPS)
		.addTag(CROPS_BAMBOOSHOOT).addTag(CROPS_BEETROOT).addTag(CROPS_BLACKPEPPER).addTag(CROPS_CABBAGE)
		.addTag(CROPS_CARROT).addTag(CROPS_CHERRY).addTag(CROPS_CHESTNUT).addTag(CROPS_CHILI)
		.addTag(CROPS_HAKUSAI).addTag(CROPS_CITRUS).addTag(CROPS_CORN).addTag(CROPS_CUMIN)
		.addTag(CROPS_GRAPE).addTag(CROPS_GREENONION).addTag(CROPS_NETHERWART).addTag(CROPS_ONION)
		.addTag(CROPS_ORANGE).addTag(CROPS_PEPPER).addTag(CROPS_PEPPERCORN).addTag(CROPS_POTATO)
		.addTag(CROPS_RICE).addTag(CROPS_SOY).addTag(CROPS_SPINACH).addTag(CROPS_TEA)
		.addTag(CROPS_TEALEAF).addTag(CROPS_TOMATO).addTag(CROPS_TURMERIC).addTag(CROPS_WHEAT)
		.addTag(VANILLABEAN).addTag(VANILLABEANS);
	
		this.tag(DUSTS_BLACKPEPPER)
		.addTag(FOODS_BLACKPEPPER).addTag(FOODS_PEPPER).addTag(SPICES_BLACKPEPPER).addTag(SPICES_PEPPER)
		.addTag(DUSTS_PEPPER).add(Items_Teatime.DUST_PEPPER);
		this.tag(DUSTS_CHILI).addTag(FOODS_CHILI).addTag(SPICES_CHILI).add(Items_Teatime.DUST_CHILI);
		this.tag(DUSTS_CUMIN).addTag(SPICES_CUMIN).add(Items_Teatime.DUST_CUMIN);
		this.tag(DUSTS_DASHI)
		.add(Items_Teatime.DASHI_bot_14, Items_NoTab.DASHI_bot_24, Items_NoTab.DASHI_bot_34, Items_NoTab.DASHI_bot_44);
		this.tag(DUSTS_GLOWATONE).add(Items.GLOWSTONE_DUST);
		this.tag(DUSTS_PEPPER).add(Items_Teatime.DUST_PEPPER);
		this.tag(DUSTS_PRISMARINE).add(Items.PRISMARINE_CRYSTALS);
		this.tag(DUSTS_REDSTONE).add(Items.REDSTONE);
		this.tag(DUSTS_SALT)
		.addTag(FOODS_SALT).addTag(ITEMS_SALT).add(Items_Teatime.SHIO);
		this.tag(DUSTS_TURMERIC).addTag(SPICES_TURMERIC).add(Items_Teatime.DUST_TURMERIC);
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
		
		this.tag(EGGS).add(Items.EGG);
		this.tag(FEATHERS).add(Items.FEATHER);
		this.tag(FLOWERS_CHERRY).add(Items_Seasonal.SAKURA_flow);
		this.tag(FLOWERS).addTag(FLOWERS_CHERRY);
		
		this.tag(FOODS_BLACKPEPPER).add(Items_Teatime.DUST_PEPPER);
		this.tag(FOODS_BUTTER).add(Items_Teatime.BUTTER);
		this.tag(FOODS_CHEESE).add(Items_Teatime.PC_CHEESE);
		this.tag(FOODS_CHILI).add(Items_Teatime.DUST_CHILI);
		this.tag(FOODS_FLOUR).add(Items_Teatime.KOMUGI);
		this.tag(FOODS_FRESHCHEESE).add(Items_Teatime.FCHEESE);
		this.tag(FOODS_MISOPASTE).add(Items_Teatime.MISO);
		this.tag(FOODS_OIL).add(Items_Teatime.SOYOIL_bot_12, Items_NoTab.SOYOIL_bot_22);
		this.tag(FOODS_PEPPER).add(Items_Teatime.DUST_PEPPER);
		this.tag(FOODS_SALT).add(Items_Teatime.SHIO);
		this.tag(FOODS_SILKENTOFU).addTag(FOODS_SILKENTOUFU).add(Items_Teatime.TOUFU);
		this.tag(FOODS_SILKENTOUFU).add(Items_Teatime.TOUFU);
		this.tag(FOODS_SOYSAUSE)
		.add(Items_Teatime.SHOUYU_bot_14, Items_NoTab.SHOUYU_bot_24, Items_NoTab.SHOUYU_bot_34, Items_NoTab.SHOUYU_bot_44);
		this.tag(FOODS_VINEGAR)
		.add(Items_Teatime.KOMEZU_bot_12, Items_NoTab.KOMEZU_bot_22);
		this.tag(FOODS)
		.addTag(FOODS_BLACKPEPPER).addTag(FOODS_BUTTER).addTag(FOODS_CHEESE).addTag(FOODS_CHILI)
		.addTag(FOODS_FLOUR).addTag(FOODS_FRESHCHEESE).addTag(FOODS_MISOPASTE).addTag(FOODS_OIL)
		.addTag(FOODS_PEPPER).addTag(FOODS_SALT).addTag(FOODS_SILKENTOFU).addTag(FOODS_SILKENTOUFU)
		.addTag(FOODS_SOYSAUSE).addTag(FOODS_VINEGAR);
		
		this.tag(FRUITS_APPLE).add(Items.APPLE);
		this.tag(FRUITS_CHERRY).add(Items_Teatime.FOOD_CHERRY);
		this.tag(FRUITS_CITRUS).add(Items_Teatime.FOOD_MIKAN);
		this.tag(FRUITS_GRAPE).add(Items_Teatime.FOOD_GRAPE);
		this.tag(FRUITS_ORANGE).add(Items_Teatime.FOOD_MIKAN);
		this.tag(FRUITS)
		.addTag(FRUITS_APPLE).addTag(FRUITS_CHERRY).addTag(FRUITS_CITRUS).addTag(FRUITS_GRAPE).addTag(FRUITS_ORANGE);
		
		this.tag(GEMS_DIAMOND).add(Items.DIAMOND);
		this.tag(GEMS_EMERALD).add(Items.EMERALD);
		this.tag(GEMS_LAPIS).add(Items.LAPIS_LAZULI);
		this.tag(GEMS_PRISMARINE).add(Items.PRISMARINE_CRYSTALS);
		this.tag(GEMS_QUARTZ).add(Items.QUARTZ);
		this.tag(GEMS)
		.addTag(GEMS_DIAMOND).addTag(GEMS_EMERALD).addTag(GEMS_LAPIS).addTag(GEMS_PRISMARINE).addTag(GEMS_QUARTZ);
		
		this.tag(GLASS_BLACK).add(Items.BLACK_STAINED_GLASS);
		this.tag(GLASS_BLUE).add(Items.BLUE_STAINED_GLASS);
		this.tag(GLASS_BROWN).add(Items.BROWN_STAINED_GLASS);
		this.tag(GLASS_CYAN).add(Items.CYAN_STAINED_GLASS);
		this.tag(GLASS_GRAY).add(Items.GRAY_STAINED_GLASS);
		this.tag(GLASS_GREEN).add(Items.GREEN_STAINED_GLASS);
		this.tag(GLASS_LIGHTBLUE).add(Items.LIGHT_BLUE_STAINED_GLASS);
		this.tag(GLASS_LIGHTGRAY).add(Items.LIGHT_GRAY_STAINED_GLASS);
		this.tag(GLASS_LIME).add(Items.LIME_STAINED_GLASS);
		this.tag(GLASS_MAGENTA).add(Items.MAGENTA_STAINED_GLASS);
		this.tag(GLASS_ORANGE).add(Items.ORANGE_STAINED_GLASS);
		this.tag(GLASS_PINK).add(Items.PINK_STAINED_GLASS);
		this.tag(GLASS_PURPLE).add(Items.PURPLE_STAINED_GLASS);
		this.tag(GLASS_RED).add(Items.RED_STAINED_GLASS);
		this.tag(GLASS_WHITE).add(Items.WHITE_STAINED_GLASS);
		this.tag(GLASS_YELLOW).add(Items.YELLOW_STAINED_GLASS);
		this.tag(GLASS_COLORLESS).add(Items.GLASS);
		this.tag(GLASS)
		.addTag(GLASS_BLACK).addTag(GLASS_BLUE).addTag(GLASS_BROWN).addTag(GLASS_CYAN)
		.addTag(GLASS_GRAY).addTag(GLASS_GREEN).addTag(GLASS_LIGHTBLUE).addTag(GLASS_LIGHTGRAY)
		.addTag(GLASS_LIME).addTag(GLASS_MAGENTA).addTag(GLASS_ORANGE).addTag(GLASS_PINK)
		.addTag(GLASS_PURPLE).addTag(GLASS_RED).addTag(GLASS_WHITE).addTag(GLASS_YELLOW)
		.addTag(GLASS_COLORLESS);
		
		this.tag(GLASS_PANES_BLACK).add(Items.BLACK_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_BLUE).add(Items.BLUE_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_BROWN).add(Items.BROWN_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_CYAN).add(Items.CYAN_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_GRAY).add(Items.GRAY_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_GREEN).add(Items.GREEN_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_LIGHTBLUE).add(Items.LIGHT_BLUE_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_LIGHTGRAY).add(Items.LIGHT_GRAY_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_LIME).add(Items.LIME_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_MAGENTA).add(Items.MAGENTA_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_ORANGE).add(Items.ORANGE_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_PINK).add(Items.PINK_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_PURPLE).add(Items.PURPLE_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_RED).add(Items.RED_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_WHITE).add(Items.WHITE_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_YELLOW).add(Items.YELLOW_STAINED_GLASS_PANE);
		this.tag(GLASS_PANES_COLORLESS).add(Items.GLASS_PANE);
		this.tag(GLASS_PANES)
		.addTag(GLASS_PANES_BLACK).addTag(GLASS_PANES_BLUE).addTag(GLASS_PANES_BROWN).addTag(GLASS_PANES_CYAN)
		.addTag(GLASS_PANES_GRAY).addTag(GLASS_PANES_GREEN).addTag(GLASS_PANES_LIGHTBLUE).addTag(GLASS_PANES_LIGHTGRAY)
		.addTag(GLASS_PANES_LIME).addTag(GLASS_PANES_MAGENTA).addTag(GLASS_PANES_ORANGE).addTag(GLASS_PANES_PINK)
		.addTag(GLASS_PANES_PURPLE).addTag(GLASS_PANES_RED).addTag(GLASS_PANES_WHITE).addTag(GLASS_PANES_YELLOW)
		.addTag(GLASS_PANES_COLORLESS);

		this.tag(INGOTS_ALUMINIUM).add(Items_Chinjufu.ALUMINUM);
		this.tag(INGOTS_ALUMINUM).add(Items_Chinjufu.ALUMINUM).addTag(INGOTS_ALUMINIUM);
		this.tag(INGOTS_GOLD).add(Items.GOLD_INGOT);
		this.tag(INGOTS_IRON).add(Items.IRON_INGOT);
		this.tag(INGOTS_NETHERITE).add(Items.NETHERITE_INGOT);
		this.tag(INGOTS)
		.addTag(INGOTS_ALUMINIUM).addTag(INGOTS_ALUMINUM)
		.addTag(INGOTS_GOLD).addTag(INGOTS_IRON).addTag(INGOTS_NETHERITE);
		
		this.tag(NUGGETS_GOLD).add(Items.GOLD_NUGGET);
		this.tag(NUGGETS_IRON).add(Items.IRON_NUGGET);
		this.tag(NUGGETS).addTag(NUGGETS_GOLD).addTag(NUGGETS_IRON);
		
		this.tag(ITEMS_SALT).add(Items_Teatime.SHIO);
		this.tag(ITEMS_YEAST).add(Items_Teatime.KOUBO);
		this.tag(ITEMS).addTag(ITEMS_SALT).addTag(ITEMS_YEAST);
		
		this.tag(LEATHER).add(Items.LEATHER);
		
		this.tag(LEAVES_CHERRY).add(Items_Seasonal.SAKURA_flow).addTag(FLOWERS_CHERRY);
		this.tag(LEAVES_ACER).add(Items_Seasonal.KAEDE_leaf);
		this.tag(LEAVES_GINKGO).add(Items_Seasonal.ICHOH_leaf);
		this.tag(LEAVES_AUTUMN_OAK).add(Items_Seasonal.OAKKARE_leaf);
		this.tag(LEAVES)
		.addTag(ItemTags.LEAVES).addTag(LEAVES_CHERRY)
		.addTag(LEAVES_ACER).addTag(LEAVES_GINKGO).addTag(LEAVES_AUTUMN_OAK);
		
		this.tag(LOGS_CHERRY).add(Items_Seasonal.SAKURA_log);
		this.tag(LOGS_ACER).add(Items_Seasonal.KAEDE_log);
		this.tag(LOGS_GINKGO).add(Items_Seasonal.ICHOH_log);
		this.tag(LOGS)
		.addTag(ItemTags.LOGS).addTag(LOGS_CHERRY).addTag(LOGS_ACER).addTag(LOGS_GINKGO);
		
		this.tag(PLANKS_CHERRY).add(Items_Seasonal.SAKURA_planks);
		this.tag(PLANKS_ACER).add(Items_Seasonal.KAEDE_planks);
		this.tag(PLANKS_GINKGO).add(Items_Seasonal.ICHOH_planks);
		this.tag(PLANKS)
		.addTag(ItemTags.PLANKS).addTag(PLANKS_CHERRY).addTag(PLANKS_ACER).addTag(PLANKS_GINKGO);
		
		this.tag(RAW_BEEF).add(Items.BEEF).addTag(RAWBEEF);
		this.tag(RAW_CHICKEN).add(Items.CHICKEN).addTag(RAWCHICKEN);
		this.tag(RAW_CLAM).add(Items_Teatime.HAMAGURI);
		this.tag(RAW_COD).add(Items.COD);
		this.tag(RAW_SALMON).add(Items.SALMON);
		this.tag(RAW_SQUID).add(Items_Teatime.IKA);
		this.tag(RAW_FISHES).addTag(RAW_COD).addTag(RAW_SALMON);
		this.tag(RAW_MUTTON).add(Items.MUTTON).addTag(RAWMUTTON);
		this.tag(RAW_PORK).add(Items.PORKCHOP).addTag(RAWPORK);
		this.tag(RAW_RABBIT).add(Items.RABBIT).addTag(RAWRABBIT);
		
		this.tag(RAWBEEF).add(Items.BEEF);
		this.tag(RAWCHICKEN).add(Items.CHICKEN);
		this.tag(RAWMUTTON).add(Items.MUTTON);
		this.tag(RAWPORK).add(Items.PORKCHOP);
		this.tag(RAWRABBIT).add(Items.RABBIT);
		
		this.tag(RODS_BLAZE).add(Items.BLAZE_ROD);
		this.tag(RODS_WOODEN).add(Items.STICK);
		this.tag(RODS).addTag(RODS_BLAZE).addTag(RODS_WOODEN);
		
		this.tag(SAPLINGS_CHERRY).add(Items_Seasonal.SAKURA_nae);
		this.tag(SAPLINGS_ACER).add(Items_Seasonal.KAEDE_nae);
		this.tag(SAPLINGS_GINKGO).add(Items_Seasonal.ICHOH_nae);
		this.tag(SAPLINGS_AUTUMN_OAK).add(Items_Seasonal.OAKKARE_nae);
		this.tag(SAPLINGS)
		.addTag(ItemTags.SAPLINGS).addTag(SAPLINGS_CHERRY)
		.addTag(SAPLINGS_ACER).addTag(SAPLINGS_GINKGO).addTag(SAPLINGS_AUTUMN_OAK);
		
		this.tag(SAUCE_MAYONNAISE)
		.add(Items_Teatime.MAYO_bot_14, Items_NoTab.MAYO_bot_24, Items_NoTab.MAYO_bot_34, Items_NoTab.MAYO_bot_44)
		.addTag(MAYONAISE);
		this.tag(SAUCE_SOYSAUSE)
		.add(Items_Teatime.SHOUYU_bot_14, Items_NoTab.SHOUYU_bot_24, Items_NoTab.SHOUYU_bot_34, Items_NoTab.SHOUYU_bot_44)
		.addTag(FOODS_SOYSAUSE);
		this.tag(SAUCE_WORCETER)
		.add(Items_Teatime.OSAUCE_bot_14, Items_NoTab.OSAUCE_bot_24, Items_NoTab.OSAUCE_bot_34, Items_NoTab.OSAUCE_bot_44)
		.addTag(WORCETERSOUCE);
		this.tag(SAUCE).addTag(SAUCE_MAYONNAISE).addTag(SAUCE_SOYSAUSE).addTag(SAUCE_WORCETER);

		this.tag(SEEDS_AZUKI).add(Items_Teatime.SEEDS_AZUKI);
		this.tag(SEEDS_CABBAGE).add(Items_Teatime.SEEDS_CABBAGE);
		this.tag(SEEDS_CHERRY).add(Items_Teatime.SEEDS_CHERRY);
		this.tag(SEEDS_CHILI).add(Items_Teatime.SEEDS_CHILI);
		this.tag(SEEDS_HAKUSAI).add(Items_Teatime.SEEDS_HAKUSAI);
		this.tag(SEEDS_CORN).add(Items_Teatime.SEEDS_CORN);
		this.tag(SEEDS_CUMIN).add(Items_Teatime.SEEDS_CUMIN);
		this.tag(SEEDS_GREENONION).add(Items_Teatime.SEEDS_GREENONION);
		this.tag(SEEDS_ONION).add(Items_Teatime.SEEDS_ONION);
		this.tag(SEEDS_RICE).add(Items_Teatime.SEEDS_RICE);
		this.tag(SEEDS_SOY).add(Items_Teatime.SEEDS_SOY).addTag(SEEDS_SOYBEAN);
		this.tag(SEEDS_SOYBEAN).add(Items_Teatime.SEEDS_SOY);
		this.tag(SEEDS_SPINACH).add(Items_Teatime.SEEDS_SPINACH);
		this.tag(SEEDS_TOMATO).add(Items_Teatime.SEEDS_TOMATO);
		this.tag(SEEDS)
		.addTag(SEEDS_AZUKI).addTag(SEEDS_CABBAGE).addTag(SEEDS_CHERRY).addTag(SEEDS_CHILI).addTag(SEEDS_HAKUSAI)
		.addTag(SEEDS_CORN).addTag(SEEDS_CUMIN).addTag(SEEDS_GREENONION).addTag(SEEDS_ONION)
		.addTag(SEEDS_RICE).addTag(SEEDS_SOY).addTag(SEEDS_SOYBEAN).addTag(SEEDS_SPINACH).addTag(SEEDS_TOMATO);
		
		this.tag(SLIMEBALLS).add(Items.SLIME_BALL);
		
		this.tag(SPICES_BLACKPEPPER).add(Items_Teatime.DUST_PEPPER);
		this.tag(SPICES_CHILI).add(Items_Teatime.DUST_CHILI);
		this.tag(SPICES_CUMIN).add(Items_Teatime.DUST_CUMIN);
		this.tag(SPICES_PEPPER).add(Items_Teatime.DUST_PEPPER);
		this.tag(SPICES_TURMERIC).add(Items_Teatime.DUST_TURMERIC);
		this.tag(SPICES_VANILLA)
		.add(Items_Teatime.VANILLA_bot_14, Items_NoTab.VANILLA_bot_24, 
				Items_NoTab.VANILLA_bot_34, Items_NoTab.VANILLA_bot_44);
		this.tag(SPICES)
		.addTag(SPICES_BLACKPEPPER).addTag(SPICES_CHILI).addTag(SPICES_CUMIN)
		.addTag(SPICES_PEPPER).addTag(SPICES_TURMERIC).addTag(SPICES_VANILLA);
		
		this.tag(DRY_TENGUSA).add(Items_Teatime.TENGUSA_DRY);
		this.tag(STRING).add(Items_Seasonal.ORIITO);
		this.tag(SUGAR).add(Items.SUGAR);
		
		this.tag(WOODEN_SLABS_CHERRY).add(Items_Seasonal.SAKURA_slabhalf);
		this.tag(WOODEN_SLABS_ACER).add(Items_Seasonal.KAEDE_slabhalf);
		this.tag(WOODEN_SLABS_GINKGO).add(Items_Seasonal.ICHOH_slabhalf);
		this.tag(WOODEN_SLABS)
		.addTag(ItemTags.WOODEN_SLABS).addTag(WOODEN_SLABS_CHERRY)
		.addTag(WOODEN_SLABS_ACER).addTag(WOODEN_SLABS_GINKGO);
		
		this.tag(WOOL)
		.add(Items.WHITE_WOOL, Items.ORANGE_WOOL, Items.MAGENTA_WOOL, Items.LIGHT_BLUE_WOOL,
				Items.YELLOW_WOOL, Items.LIME_WOOL, Items.PINK_WOOL, Items.GRAY_WOOL, 
				Items.LIGHT_GRAY_WOOL, Items.CYAN_WOOL, Items.PURPLE_WOOL, Items.BLUE_WOOL,
				Items.BROWN_WOOL, Items.GREEN_WOOL, Items.RED_WOOL, Items.BLACK_WOOL);
	}
	
	@Override
	public String getName() {
		return "ChinjufuMod Item Tags";
	}
}
