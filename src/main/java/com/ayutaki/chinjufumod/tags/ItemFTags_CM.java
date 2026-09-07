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
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemFTags_CM extends ItemTagsProvider {

	public ItemFTags_CM(PackOutput outPut, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> tag, ExistingFileHelper helper) {
		super(outPut, provider, tag, ChinjufuMod.MOD_ID, helper);
	}
	
	/* TagKey */
	public static final TagKey<Item> ADMIRAL_STAMP = create("admiral_stamp");
	public static final TagKey<Item> BONE_MEAL = create("bone_meal");
	public static final TagKey<Item> COOKING_WATER = create("cooking_water");
	public static final TagKey<Item> KAGAMIMOCHI_CITRUS = create("kagamimochi_citrus");
	public static final TagKey<Item> MATERIALS_BOOTS = create("materials_boots");
	public static final TagKey<Item> MATERIALS_FUEL = create("materials_fuel");
	public static final TagKey<Item> MATERIALS_LAMP = create("materials_lamp");
	public static final TagKey<Item> MATERIALS_OIL = create("materials_oil");
	public static final TagKey<Item> MATERIALS_POTATO = create("materials_potato");
	public static final TagKey<Item> MATERIALS_STRAW = create("materials_straw");
	public static final TagKey<Item> MATERIALS_TATAMI = create("materials_tatami");
	public static final TagKey<Item> MATERIALS_YARN = create("materials_yarn");
	public static final TagKey<Item> MATERIALS_SEAGASS = create("materials_seagrass");
	
	
	/** forge **/
	public static final TagKey<Item> BUCKETS = forgeTag("buckets");
	public static final TagKey<Item> BUCKETS_EMPTY = forgeTag("buckets/empty");
	public static final TagKey<Item> BUCKETS_WATER = forgeTag("buckets/water");
	public static final TagKey<Item> BUCKETS_MILK = forgeTag("buckets/milk");
	
	public static final TagKey<Item> CARPETS = forgeTag("carpets");
	
	public static final TagKey<Item> CONDIMENTS = forgeTag("condiments");
	public static final TagKey<Item> MAYONAISE = forgeTag("condiments/mayonaise");
	public static final TagKey<Item> WORCETERSOUCE = forgeTag("condiments/worcestersauce");
	
	public static final TagKey<Item> COOKED_BEEF = forgeTag("cooked_beef");
	public static final TagKey<Item> COOKED_CHICKEN = forgeTag("cooked_chicken");
	public static final TagKey<Item> COOKED_FISHES = forgeTag("cooked_fishes");
	public static final TagKey<Item> COOKED_CLAM = forgeTag("cooked_fishes/clam");
	public static final TagKey<Item> COOKED_COD = forgeTag("cooked_fishes/cod");
	public static final TagKey<Item> COOKED_SALMON = forgeTag("cooked_fishes/salmon");
	public static final TagKey<Item> COOKED_SQUID = forgeTag("cooked_fishes/squid");
	public static final TagKey<Item> COOKED_MUTTON = forgeTag("cooked_mutton");
	public static final TagKey<Item> COOKED_PORK = forgeTag("cooked_pork");
	public static final TagKey<Item> COOKED_RABBIT = forgeTag("cooked_rabbit");
	
	public static final TagKey<Item> COOKEDBEEF = forgeTag("cookedbeef");
	public static final TagKey<Item> COOKEDCHICKEN = forgeTag("cookedchicken");
	public static final TagKey<Item> COOKEDMUTTON = forgeTag("cookedmutton");
	public static final TagKey<Item> COOKEDPORK = forgeTag("cookedpork");
	public static final TagKey<Item> COOKEDRABBIT = forgeTag("cookedrabbit");
	
	public static final TagKey<Item> CROPS = forgeTag("crops");
	public static final TagKey<Item> CROPS_APPLE = forgeTag("crops/apple");
	public static final TagKey<Item> CROPS_BAMBOOSHOOT = forgeTag("crops/bambooshoot");
	public static final TagKey<Item> CROPS_BEETROOT = forgeTag("crops/beetroot");
	public static final TagKey<Item> CROPS_BLACKPEPPER = forgeTag("crops/blackpepper");
	public static final TagKey<Item> CROPS_CABBAGE = forgeTag("crops/cabbage");
	public static final TagKey<Item> CROPS_CARROT = forgeTag("crops/carrot");
	public static final TagKey<Item> CROPS_CHERRY = forgeTag("crops/cherry");
	public static final TagKey<Item> CROPS_CHESTNUT = forgeTag("crops/chestnut");
	public static final TagKey<Item> CROPS_CHILI = forgeTag("crops/chilipepper");
	public static final TagKey<Item> CROPS_HAKUSAI = forgeTag("crops/chinesecabbage");
	public static final TagKey<Item> CROPS_CITRUS = forgeTag("crops/citrus");
	public static final TagKey<Item> CROPS_CORN = forgeTag("crops/corn");
	public static final TagKey<Item> CROPS_CUMIN = forgeTag("crops/cumin");
	public static final TagKey<Item> CROPS_GRAPE = forgeTag("crops/grape");
	public static final TagKey<Item> CROPS_GREENONION = forgeTag("crops/greenonion");
	public static final TagKey<Item> CROPS_NETHERWART = forgeTag("crops/nether_wart");
	public static final TagKey<Item> CROPS_ONION = forgeTag("crops/onion");
	public static final TagKey<Item> CROPS_ORANGE = forgeTag("crops/orange");
	public static final TagKey<Item> CROPS_PEPPER = forgeTag("crops/pepper");
	public static final TagKey<Item> CROPS_PEPPERCORN = forgeTag("crops/peppercorn");
	public static final TagKey<Item> CROPS_POTATO = forgeTag("crops/potato");
	public static final TagKey<Item> CROPS_RICE = forgeTag("crops/rice");
	public static final TagKey<Item> CROPS_SOY = forgeTag("crops/soy");
	public static final TagKey<Item> CROPS_SPINACH = forgeTag("crops/spinach");
	public static final TagKey<Item> CROPS_TEA = forgeTag("crops/tea");
	public static final TagKey<Item> CROPS_TEALEAF = forgeTag("crops/tealeaf");
	public static final TagKey<Item> CROPS_TOMATO = forgeTag("crops/tomato");
	public static final TagKey<Item> CROPS_TURMERIC = forgeTag("crops/turmeric");
	public static final TagKey<Item> CROPS_WHEAT = forgeTag("crops/wheat");
	public static final TagKey<Item> VANILLABEAN = forgeTag("crops/vanillabean");
	public static final TagKey<Item> VANILLABEANS = forgeTag("crops/vanillabeans");

	public static final TagKey<Item> DUSTS = forgeTag("dusts");
	public static final TagKey<Item> DUSTS_BLACKPEPPER = forgeTag("dusts/blackpepper");
	public static final TagKey<Item> DUSTS_CHILI = forgeTag("dusts/chili");
	public static final TagKey<Item> DUSTS_CUMIN = forgeTag("dusts/cumin");
	public static final TagKey<Item> DUSTS_DASHI = forgeTag("dusts/dashi");
	public static final TagKey<Item> DUSTS_GLOWATONE = forgeTag("dusts/glowstone");
	public static final TagKey<Item> DUSTS_PEPPER = forgeTag("dusts/pepper");
	public static final TagKey<Item> DUSTS_PRISMARINE = forgeTag("dusts/prismarine");
	public static final TagKey<Item> DUSTS_REDSTONE = forgeTag("dusts/redstone");
	public static final TagKey<Item> DUSTS_SALT = forgeTag("dusts/salt");
	public static final TagKey<Item> DUSTS_TURMERIC = forgeTag("dusts/turmeric");

	public static final TagKey<Item> DYES = forgeTag("dyes");
	public static final TagKey<Item> DYES_BLACK = forgeTag("dyes/black");
	public static final TagKey<Item> DYES_BLUE = forgeTag("dyes/blue");
	public static final TagKey<Item> DYES_BROWN = forgeTag("dyes/brown");
	public static final TagKey<Item> DYES_CYAN = forgeTag("dyes/cyan");
	public static final TagKey<Item> DYES_GRAY = forgeTag("dyes/gray");
	public static final TagKey<Item> DYES_GREEN = forgeTag("dyes/green");
	public static final TagKey<Item> DYES_LIGHTBLUE = forgeTag("dyes/light_blue");
	public static final TagKey<Item> DYES_LIGHTGRAY = forgeTag("dyes/light_gray");
	public static final TagKey<Item> DYES_LIME = forgeTag("dyes/lime");
	public static final TagKey<Item> DYES_MAGENTA = forgeTag("dyes/magenta");
	public static final TagKey<Item> DYES_ORANGE = forgeTag("dyes/orange");
	public static final TagKey<Item> DYES_PINK = forgeTag("dyes/pink");
	public static final TagKey<Item> DYES_PURPLE = forgeTag("dyes/purple");
	public static final TagKey<Item> DYES_RED = forgeTag("dyes/red");
	public static final TagKey<Item> DYES_WHITE = forgeTag("dyes/white");
	public static final TagKey<Item> DYES_YELLOW = forgeTag("dyes/yellow");
	
	public static final TagKey<Item> EGGS = forgeTag("eggs");
	public static final TagKey<Item> FEATHERS = forgeTag("feathers");
	public static final TagKey<Item> FLOWERS = forgeTag("flowers");
	public static final TagKey<Item> FLOWERS_CHERRY = forgeTag("flowers/cherry");
	
	public static final TagKey<Item> FOODS = forgeTag("foods");
	public static final TagKey<Item> FOODS_BLACKPEPPER = forgeTag("foods/blackpepper");
	public static final TagKey<Item> FOODS_BUTTER = forgeTag("foods/butter");
	public static final TagKey<Item> FOODS_CHEESE = forgeTag("foods/cheese");
	public static final TagKey<Item> FOODS_CHILI = forgeTag("foods/chili");
	public static final TagKey<Item> FOODS_FLOUR = forgeTag("foods/flour");
	public static final TagKey<Item> FOODS_FRESHCHEESE = forgeTag("foods/freshcheese");
	public static final TagKey<Item> FOODS_MISOPASTE = forgeTag("foods/misopaste");
	public static final TagKey<Item> FOODS_OIL = forgeTag("foods/oil");
	public static final TagKey<Item> FOODS_PEPPER= forgeTag("foods/pepper");
	public static final TagKey<Item> FOODS_SALT = forgeTag("foods/salt");
	public static final TagKey<Item> FOODS_SILKENTOFU = forgeTag("foods/silkentofu");
	public static final TagKey<Item> FOODS_SILKENTOUFU = forgeTag("foods/silkentoufu");
	public static final TagKey<Item> FOODS_SOYSAUSE = forgeTag("foods/soysauce");
	public static final TagKey<Item> FOODS_VINEGAR = forgeTag("foods/vinegar");
	
	public static final TagKey<Item> FRUITS = forgeTag("fruits");
	public static final TagKey<Item> FRUITS_APPLE = forgeTag("fruits/apple");
	public static final TagKey<Item> FRUITS_CHERRY = forgeTag("fruits/cherry");
	public static final TagKey<Item> FRUITS_CITRUS = forgeTag("fruits/citrus");
	public static final TagKey<Item> FRUITS_GRAPE = forgeTag("fruits/grape");
	public static final TagKey<Item> FRUITS_ORANGE = forgeTag("fruits/orange");
	
	public static final TagKey<Item> GEMS = forgeTag("gems");
	public static final TagKey<Item> GEMS_DIAMOND = forgeTag("gems/diamond");
	public static final TagKey<Item> GEMS_EMERALD = forgeTag("gems/emerald");
	public static final TagKey<Item> GEMS_LAPIS = forgeTag("gems/lapis");
	public static final TagKey<Item> GEMS_PRISMARINE = forgeTag("gems/prismarine");
	public static final TagKey<Item> GEMS_QUARTZ = forgeTag("gems/quartz");
	
	public static final TagKey<Item> GLASS = forgeTag("glass");
	public static final TagKey<Item> GLASS_BLACK = forgeTag("glass/black");
	public static final TagKey<Item> GLASS_BLUE = forgeTag("glass/blue");
	public static final TagKey<Item> GLASS_BROWN = forgeTag("glass/brown");
	public static final TagKey<Item> GLASS_CYAN = forgeTag("glass/cyan");
	public static final TagKey<Item> GLASS_GRAY = forgeTag("glass/gray");
	public static final TagKey<Item> GLASS_GREEN = forgeTag("glass/green");
	public static final TagKey<Item> GLASS_LIGHTBLUE = forgeTag("glass/light_blue");
	public static final TagKey<Item> GLASS_LIGHTGRAY = forgeTag("glass/light_gray");
	public static final TagKey<Item> GLASS_LIME = forgeTag("glass/lime");
	public static final TagKey<Item> GLASS_MAGENTA = forgeTag("glass/magenta");
	public static final TagKey<Item> GLASS_ORANGE = forgeTag("glass/orange");
	public static final TagKey<Item> GLASS_PINK = forgeTag("glass/pink");
	public static final TagKey<Item> GLASS_PURPLE = forgeTag("glass/purple");
	public static final TagKey<Item> GLASS_RED = forgeTag("glass/red");
	public static final TagKey<Item> GLASS_WHITE = forgeTag("glass/white");
	public static final TagKey<Item> GLASS_YELLOW = forgeTag("glass/yellow");
	public static final TagKey<Item> GLASS_COLORLESS = forgeTag("glass/colorless");
	
	public static final TagKey<Item> GLASS_PANES = forgeTag("glass_panes");
	public static final TagKey<Item> GLASS_PANES_BLACK = forgeTag("glass_panes/black");
	public static final TagKey<Item> GLASS_PANES_BLUE = forgeTag("glass_panes/blue");
	public static final TagKey<Item> GLASS_PANES_BROWN = forgeTag("glass_panes/brown");
	public static final TagKey<Item> GLASS_PANES_CYAN = forgeTag("glass_panes/cyan");
	public static final TagKey<Item> GLASS_PANES_GRAY = forgeTag("glass_panes/gray");
	public static final TagKey<Item> GLASS_PANES_GREEN = forgeTag("glass_panes/green");
	public static final TagKey<Item> GLASS_PANES_LIGHTBLUE = forgeTag("glass_panes/light_blue");
	public static final TagKey<Item> GLASS_PANES_LIGHTGRAY = forgeTag("glass_panes/light_gray");
	public static final TagKey<Item> GLASS_PANES_LIME = forgeTag("glass_panes/lime");
	public static final TagKey<Item> GLASS_PANES_MAGENTA = forgeTag("glass_panes/magenta");
	public static final TagKey<Item> GLASS_PANES_ORANGE = forgeTag("glass_panes/orange");
	public static final TagKey<Item> GLASS_PANES_PINK = forgeTag("glass_panes/pink");
	public static final TagKey<Item> GLASS_PANES_PURPLE = forgeTag("glass_panes/purple");
	public static final TagKey<Item> GLASS_PANES_RED = forgeTag("glass_panes/red");
	public static final TagKey<Item> GLASS_PANES_WHITE = forgeTag("glass_panes/white");
	public static final TagKey<Item> GLASS_PANES_YELLOW = forgeTag("glass_panes/yellow");
	public static final TagKey<Item> GLASS_PANES_COLORLESS = forgeTag("glass_panes/colorless");
	
	public static final TagKey<Item> INGOTS = forgeTag("ingots");
	public static final TagKey<Item> INGOTS_ALUMINIUM = forgeTag("ingots/aluminium");
	public static final TagKey<Item> INGOTS_ALUMINUM = forgeTag("ingots/aluminum");
	public static final TagKey<Item> INGOTS_COPPER = forgeTag("ingots/copper");
	public static final TagKey<Item> INGOTS_GOLD = forgeTag("ingots/gold");
	public static final TagKey<Item> INGOTS_IRON = forgeTag("ingots/iron");
	public static final TagKey<Item> INGOTS_NETHERITE = forgeTag("ingots/netherite");
	
	public static final TagKey<Item> NUGGETS = forgeTag("nuggets");
	public static final TagKey<Item> NUGGETS_GOLD = forgeTag("nuggets/gold");
	public static final TagKey<Item> NUGGETS_IRON = forgeTag("nuggets/iron");
	
	public static final TagKey<Item> ITEMS = forgeTag("items");
	public static final TagKey<Item> ITEMS_SALT = forgeTag("items/salt");
	public static final TagKey<Item> ITEMS_YEAST = forgeTag("items/yeast");
	
	public static final TagKey<Item> LEATHER = forgeTag("leather");
	
	public static final TagKey<Item> LEAVES = forgeTag("leaves");
	public static final TagKey<Item> LEAVES_CHERRY = forgeTag("leaves/cherry");
	public static final TagKey<Item> LEAVES_ACER = forgeTag("leaves/acer");
	public static final TagKey<Item> LEAVES_GINKGO = forgeTag("leaves/ginkgo");
	public static final TagKey<Item> LEAVES_AUTUMN_OAK = forgeTag("leaves/autumn_oak");
	public static final TagKey<Item> LOGS = forgeTag("logs");
	public static final TagKey<Item> LOGS_CHERRY = forgeTag("logs/cherry");
	public static final TagKey<Item> LOGS_ACER = forgeTag("logs/acer");
	public static final TagKey<Item> LOGS_GINKGO = forgeTag("logs/ginkgo");
	public static final TagKey<Item> PLANKS = forgeTag("planks");
	public static final TagKey<Item> PLANKS_CHERRY = forgeTag("planks/cherry");
	public static final TagKey<Item> PLANKS_ACER = forgeTag("planks/acer");
	public static final TagKey<Item> PLANKS_GINKGO = forgeTag("planks/ginkgo");
	
	public static final TagKey<Item> RAW_BEEF = forgeTag("raw_beef");
	public static final TagKey<Item> RAW_CHICKEN = forgeTag("raw_chicken");
	public static final TagKey<Item> RAW_FISHES = forgeTag("raw_fishes");
	public static final TagKey<Item> RAW_CLAM = forgeTag("raw_fishes/clam");
	public static final TagKey<Item> RAW_COD = forgeTag("raw_fishes/cod");
	public static final TagKey<Item> RAW_SALMON = forgeTag("raw_fishes/salmon");
	public static final TagKey<Item> RAW_SQUID = forgeTag("raw_fishes/squid");
	public static final TagKey<Item> RAW_MUTTON = forgeTag("raw_mutton");
	public static final TagKey<Item> RAW_PORK = forgeTag("raw_pork");
	public static final TagKey<Item> RAW_RABBIT = forgeTag("raw_rabbit");
	
	public static final TagKey<Item> RAWBEEF = forgeTag("rawbeef");
	public static final TagKey<Item> RAWCHICKEN = forgeTag("rawchicken");
	public static final TagKey<Item> RAWMUTTON = forgeTag("rawmutton");
	public static final TagKey<Item> RAWPORK = forgeTag("rawpork");
	public static final TagKey<Item> RAWRABBIT = forgeTag("rawrabbit");
	
	public static final TagKey<Item> RODS = forgeTag("rods");
	public static final TagKey<Item> RODS_BLAZE = forgeTag("rods/blaze");
	public static final TagKey<Item> RODS_WOODEN = forgeTag("rods/wooden");
	
	public static final TagKey<Item> SAPLINGS = forgeTag("saplings");
	public static final TagKey<Item> SAPLINGS_CHERRY = forgeTag("saplings/cherry");
	public static final TagKey<Item> SAPLINGS_ACER = forgeTag("saplings/acer");
	public static final TagKey<Item> SAPLINGS_GINKGO = forgeTag("saplings/ginkgo");
	public static final TagKey<Item> SAPLINGS_AUTUMN_OAK = forgeTag("saplings/autumn_oak");
	
	public static final TagKey<Item> SAUCE = forgeTag("sauces");
	public static final TagKey<Item> SAUCE_MAYONNAISE = forgeTag("sauces/mayonnaise");
	public static final TagKey<Item> SAUCE_SOYSAUSE = forgeTag("sauces/soysauce");
	public static final TagKey<Item> SAUCE_WORCETER = forgeTag("sauces/worcestersauce");
	
	public static final TagKey<Item> SEEDS = forgeTag("seeds");
	public static final TagKey<Item> SEEDS_AZUKI = forgeTag("seeds/azukibean");
	public static final TagKey<Item> SEEDS_CABBAGE = forgeTag("seeds/cabbage");
	public static final TagKey<Item> SEEDS_CHERRY = forgeTag("seeds/cherry");
	public static final TagKey<Item> SEEDS_CHILI = forgeTag("seeds/chilipepper");
	public static final TagKey<Item> SEEDS_HAKUSAI = forgeTag("seeds/chinesecabbage");
	public static final TagKey<Item> SEEDS_CORN = forgeTag("seeds/corn");
	public static final TagKey<Item> SEEDS_CUMIN = forgeTag("seeds/cumin");
	public static final TagKey<Item> SEEDS_GREENONION = forgeTag("seeds/greenonion");
	public static final TagKey<Item> SEEDS_ONION = forgeTag("seeds/onion");
	public static final TagKey<Item> SEEDS_RICE = forgeTag("seeds/rice");
	public static final TagKey<Item> SEEDS_SOY = forgeTag("seeds/soy");
	public static final TagKey<Item> SEEDS_SOYBEAN = forgeTag("seeds/soybean");
	public static final TagKey<Item> SEEDS_SPINACH = forgeTag("seeds/spinach");
	public static final TagKey<Item> SEEDS_TOMATO = forgeTag("seeds/tomato");

	public static final TagKey<Item> SLIMEBALLS = forgeTag("slimeballs");
	
	public static final TagKey<Item> SPICES = forgeTag("spices");
	public static final TagKey<Item> SPICES_BLACKPEPPER = forgeTag("spices/blackpepper");
	public static final TagKey<Item> SPICES_CHILI = forgeTag("spices/chili");
	public static final TagKey<Item> SPICES_CUMIN = forgeTag("spices/cumin");
	public static final TagKey<Item> SPICES_PEPPER = forgeTag("spices/pepper");
	public static final TagKey<Item> SPICES_TURMERIC = forgeTag("spices/turmeric");
	public static final TagKey<Item> SPICES_VANILLA = forgeTag("spices/vanilla");
	public static final TagKey<Item> DRY_TENGUSA = forgeTag("dried_gelidiaceae");
	
	public static final TagKey<Item> STRING = forgeTag("string");
	public static final TagKey<Item> SUGAR = forgeTag("sugar");
	
	public static final TagKey<Item> WOODEN_SLABS = forgeTag("wooden_slabs");
	public static final TagKey<Item> WOODEN_SLABS_CHERRY = forgeTag("wooden_slabs/cherry");
	public static final TagKey<Item> WOODEN_SLABS_ACER = forgeTag("wooden_slabs/acer");
	public static final TagKey<Item> WOODEN_SLABS_GINKGO = forgeTag("wooden_slabs/ginkgo");
	
	public static final TagKey<Item> WOOL = forgeTag("wool");
	
	@SuppressWarnings("removal")
	private static TagKey<Item> create(String name) {
		return ItemTags.create(new ResourceLocation(ChinjufuMod.MOD_ID, name));
	}
	
	@SuppressWarnings("removal")
	private static TagKey<Item> forgeTag(String name) {
		return ItemTags.create(new ResourceLocation("forge", name));
	}
	
	/* addTags */
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		/** chinjufumod **/
		this.tag(ADMIRAL_STAMP).add(Items_NoTab.ADMIRAL_STAMP.get(), Items_Chinjufu.ADMIRAL_STAMPB.get());
		this.tag(BONE_MEAL).add(Items_NoTab.HAMAGURI_KARA.get(), Items_Teatime.SAKEKASU.get());
		this.tag(COOKING_WATER).add(Items.POTION, Items_Teatime.KEIRYO_CUP_full.get());
		this.tag(KAGAMIMOCHI_CITRUS).addTag(CROPS_CITRUS).addTag(CROPS_ORANGE);
		this.tag(MATERIALS_BOOTS).add(Items.BLACK_CARPET, Items.LEATHER);
		this.tag(MATERIALS_FUEL).add(Items.COAL, Items.CHARCOAL);
		this.tag(MATERIALS_LAMP).addTag(INGOTS_ALUMINUM).addTag(INGOTS_IRON);
		this.tag(MATERIALS_OIL).add(Items.SLIME_BALL, Items_Teatime.SOYOIL_bot_12.get(), Items_NoTab.SOYOIL_bot_22.get());
		this.tag(MATERIALS_POTATO).addTag(CROPS_POTATO).add(Items.POISONOUS_POTATO);
		this.tag(MATERIALS_STRAW).add(Items_Teatime.INEWARA.get()).addTag(CROPS_WHEAT);
		this.tag(MATERIALS_TATAMI)
		.add(Items.SUGAR_CANE, Items_Teatime.INEWARA.get()).addTag(CROPS_WHEAT);
		this.tag(MATERIALS_YARN).add(Items.STRING, Items_Seasonal.ORIITO.get());
		this.tag(MATERIALS_SEAGASS).add(Items.SEAGRASS, Items_Teatime.NORI_N.get());
		
		
		/** forge **/
		this.tag(BUCKETS_EMPTY).add(Items.BUCKET, Items_Teatime.MIZUOKE.get());
		this.tag(BUCKETS_WATER).add(Items.WATER_BUCKET, Items_Teatime.MIZUOKE_full.get());
		this.tag(BUCKETS_MILK).add(Items.MILK_BUCKET, Items_Teatime.MIZUOKE_Milk.get());
		this.tag(BUCKETS).addTag(BUCKETS_EMPTY).addTag(BUCKETS_WATER).addTag(BUCKETS_MILK);
		
		this.tag(CARPETS)
		.add(Items.WHITE_CARPET, Items.ORANGE_CARPET, Items.MAGENTA_CARPET, Items.LIGHT_BLUE_CARPET,
				Items.YELLOW_CARPET, Items.LIME_CARPET, Items.PINK_CARPET, Items.GRAY_CARPET, 
				Items.LIGHT_GRAY_CARPET, Items.CYAN_CARPET, Items.PURPLE_CARPET, Items.BLUE_CARPET,
				Items.BROWN_CARPET, Items.GREEN_CARPET, Items.RED_CARPET, Items.BLACK_CARPET);
		
		this.tag(MAYONAISE)
		.add(Items_Teatime.MAYO_bot_14.get(), Items_NoTab.MAYO_bot_24.get(), Items_NoTab.MAYO_bot_34.get(), Items_NoTab.MAYO_bot_44.get());
		this.tag(WORCETERSOUCE)
		.add(Items_Teatime.OSAUCE_bot_14.get(), Items_NoTab.OSAUCE_bot_24.get(), Items_NoTab.OSAUCE_bot_34.get(), Items_NoTab.OSAUCE_bot_44.get());
		this.tag(CONDIMENTS).addTag(MAYONAISE).addTag(WORCETERSOUCE);
		
		this.tag(COOKED_BEEF).add(Items.COOKED_BEEF).addTag(COOKEDBEEF);
		this.tag(COOKED_CHICKEN).add(Items.COOKED_CHICKEN).addTag(COOKEDCHICKEN);
		this.tag(COOKED_CLAM).add(Items_Teatime.HAMAGURI_COOK.get());
		this.tag(COOKED_COD).add(Items.COOKED_COD);
		this.tag(COOKED_SALMON).add(Items.COOKED_SALMON);
		this.tag(COOKED_SQUID).add(Items_Teatime.COOKED_IKA.get());
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
		this.tag(CROPS_BAMBOOSHOOT).add(Items_Seasonal.TAKENOKO.get());
		this.tag(CROPS_BEETROOT).add(Items.BEETROOT);
		this.tag(CROPS_BLACKPEPPER).add(Items_Teatime.PEPPER_DRY.get());
		this.tag(CROPS_CABBAGE).add(Items_Teatime.FOOD_CABBAGE.get());
		this.tag(CROPS_CARROT).add(Items.CARROT);
		this.tag(CROPS_CHERRY).addTag(FRUITS_CHERRY).add(Items_Teatime.FOOD_CHERRY.get());
		this.tag(CROPS_CHESTNUT).add(Items_Seasonal.KURI.get());
		this.tag(CROPS_CHILI).add(Items_Teatime.CHILIPEPPER.get());
		this.tag(CROPS_HAKUSAI).add(Items_Teatime.FOOD_HAKUSAI.get());
		this.tag(CROPS_CITRUS).addTag(FRUITS_CITRUS).add(Items_Teatime.FOOD_MIKAN.get());
		this.tag(CROPS_CORN).add(Items_Teatime.FOOD_CORN.get());
		this.tag(CROPS_CUMIN).add(Items_Teatime.SEEDS_CUMIN.get()).addTag(SEEDS_CUMIN);
		this.tag(CROPS_GRAPE).addTag(FRUITS_GRAPE).add(Items_Teatime.FOOD_GRAPE.get());
		this.tag(CROPS_GREENONION).add(Items_Teatime.FOOD_GREENONION.get());
		this.tag(CROPS_NETHERWART).add(Items.NETHER_WART);
		this.tag(CROPS_ONION).add(Items_Teatime.FOOD_ONION.get());
		this.tag(CROPS_ORANGE).addTag(FRUITS_ORANGE).add(Items_Teatime.FOOD_MIKAN.get());
		this.tag(CROPS_PEPPER).add(Items_Teatime.PEPPER_RAW.get());
		this.tag(CROPS_PEPPERCORN).add(Items_Teatime.PEPPER_RAW.get());
		this.tag(CROPS_POTATO).add(Items.POTATO);
		this.tag(CROPS_RICE).add(Items_Teatime.INE.get(), Items_Teatime.INE_D.get());
		this.tag(CROPS_SOY).add(Items_Teatime.SAYA.get());
		this.tag(CROPS_SPINACH).add(Items_Teatime.FOOD_SPINACH.get());
		this.tag(CROPS_TEA).add(Items_Teatime.CHABA.get());
		this.tag(CROPS_TEALEAF).addTag(CROPS_TEA).add(Items_Teatime.CHABA.get());
		this.tag(CROPS_TOMATO).add(Items_Teatime.FOOD_TOMATO.get());
		this.tag(CROPS_TURMERIC).add(Items_Teatime.SEEDS_TURMERIC.get());
		this.tag(CROPS_WHEAT).add(Items.WHEAT);
		this.tag(VANILLABEAN).add(Items_Teatime.VANILLA_RAW.get());
		this.tag(VANILLABEANS).add(Items_Teatime.VANILLA_RAW.get()).addTag(VANILLABEAN);
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
		.addTag(DUSTS_PEPPER).add(Items_Teatime.DUST_PEPPER.get());
		this.tag(DUSTS_CHILI).addTag(FOODS_CHILI).addTag(SPICES_CHILI).add(Items_Teatime.DUST_CHILI.get());
		this.tag(DUSTS_CUMIN).addTag(SPICES_CUMIN).add(Items_Teatime.DUST_CUMIN.get());
		this.tag(DUSTS_DASHI)
		.add(Items_Teatime.DASHI_bot_14.get(), Items_NoTab.DASHI_bot_24.get(), Items_NoTab.DASHI_bot_34.get(), Items_NoTab.DASHI_bot_44.get());
		this.tag(DUSTS_GLOWATONE).add(Items.GLOWSTONE_DUST);
		this.tag(DUSTS_PEPPER).add(Items_Teatime.DUST_PEPPER.get());
		this.tag(DUSTS_PRISMARINE).add(Items.PRISMARINE_CRYSTALS);
		this.tag(DUSTS_REDSTONE).add(Items.REDSTONE);
		this.tag(DUSTS_SALT)
		.addTag(FOODS_SALT).addTag(ITEMS_SALT).add(Items_Teatime.SHIO.get());
		this.tag(DUSTS_TURMERIC).addTag(SPICES_TURMERIC).add(Items_Teatime.DUST_TURMERIC.get());
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
		this.tag(FLOWERS_CHERRY).add(Items_Seasonal.SAKURA_flow.get());
		this.tag(FLOWERS).addTag(FLOWERS_CHERRY);
		
		this.tag(FOODS_BLACKPEPPER).add(Items_Teatime.DUST_PEPPER.get());
		this.tag(FOODS_BUTTER).add(Items_Teatime.BUTTER.get());
		this.tag(FOODS_CHEESE).add(Items_Teatime.PC_CHEESE.get());
		this.tag(FOODS_CHILI).add(Items_Teatime.DUST_CHILI.get());
		this.tag(FOODS_FLOUR).add(Items_Teatime.KOMUGI.get());
		this.tag(FOODS_FRESHCHEESE).add(Items_Teatime.FCHEESE.get());
		this.tag(FOODS_MISOPASTE).add(Items_Teatime.MISO.get());
		this.tag(FOODS_OIL).add(Items_Teatime.SOYOIL_bot_12.get(), Items_NoTab.SOYOIL_bot_22.get());
		this.tag(FOODS_PEPPER).add(Items_Teatime.DUST_PEPPER.get());
		this.tag(FOODS_SALT).add(Items_Teatime.SHIO.get());
		this.tag(FOODS_SILKENTOFU).addTag(FOODS_SILKENTOUFU).add(Items_Teatime.TOUFU.get());
		this.tag(FOODS_SILKENTOUFU).add(Items_Teatime.TOUFU.get());
		this.tag(FOODS_SOYSAUSE)
		.add(Items_Teatime.SHOUYU_bot_14.get(), Items_NoTab.SHOUYU_bot_24.get(), Items_NoTab.SHOUYU_bot_34.get(), Items_NoTab.SHOUYU_bot_44.get());
		this.tag(FOODS_VINEGAR)
		.add(Items_Teatime.KOMEZU_bot_12.get(), Items_NoTab.KOMEZU_bot_22.get());
		this.tag(FOODS)
		.addTag(FOODS_BLACKPEPPER).addTag(FOODS_BUTTER).addTag(FOODS_CHEESE).addTag(FOODS_CHILI)
		.addTag(FOODS_FLOUR).addTag(FOODS_FRESHCHEESE).addTag(FOODS_MISOPASTE).addTag(FOODS_OIL)
		.addTag(FOODS_PEPPER).addTag(FOODS_SALT).addTag(FOODS_SILKENTOFU).addTag(FOODS_SILKENTOUFU)
		.addTag(FOODS_SOYSAUSE).addTag(FOODS_VINEGAR);
		
		this.tag(FRUITS_APPLE).add(Items.APPLE);
		this.tag(FRUITS_CHERRY).add(Items_Teatime.FOOD_CHERRY.get());
		this.tag(FRUITS_CITRUS).add(Items_Teatime.FOOD_MIKAN.get());
		this.tag(FRUITS_GRAPE).add(Items_Teatime.FOOD_GRAPE.get());
		this.tag(FRUITS_ORANGE).add(Items_Teatime.FOOD_MIKAN.get());
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

		this.tag(INGOTS_ALUMINIUM).add(Items_Chinjufu.ALUMINUM.get());
		this.tag(INGOTS_ALUMINUM).add(Items_Chinjufu.ALUMINUM.get()).addTag(INGOTS_ALUMINIUM);
		this.tag(INGOTS_COPPER).add(Items.COPPER_INGOT);
		this.tag(INGOTS_GOLD).add(Items.GOLD_INGOT);
		this.tag(INGOTS_IRON).add(Items.IRON_INGOT);
		this.tag(INGOTS_NETHERITE).add(Items.NETHERITE_INGOT);
		this.tag(INGOTS)
		.addTag(INGOTS_ALUMINIUM).addTag(INGOTS_ALUMINUM).addTag(INGOTS_COPPER)
		.addTag(INGOTS_GOLD).addTag(INGOTS_IRON).addTag(INGOTS_NETHERITE);
		
		this.tag(NUGGETS_GOLD).add(Items.GOLD_NUGGET);
		this.tag(NUGGETS_IRON).add(Items.IRON_NUGGET);
		this.tag(NUGGETS).addTag(NUGGETS_GOLD).addTag(NUGGETS_IRON);
		
		this.tag(ITEMS_SALT).add(Items_Teatime.SHIO.get());
		this.tag(ITEMS_YEAST).add(Items_Teatime.KOUBO.get());
		this.tag(ITEMS).addTag(ITEMS_SALT).addTag(ITEMS_YEAST);
		
		this.tag(LEATHER).add(Items.LEATHER);
		
		this.tag(LEAVES_CHERRY).add(Items_Seasonal.SAKURA_flow.get()).addTag(FLOWERS_CHERRY);
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
		
		this.tag(RAW_BEEF).add(Items.BEEF).addTag(RAWBEEF);
		this.tag(RAW_CHICKEN).add(Items.CHICKEN).addTag(RAWCHICKEN);
		this.tag(RAW_CLAM).add(Items_Teatime.HAMAGURI.get());
		this.tag(RAW_COD).add(Items.COD);
		this.tag(RAW_SALMON).add(Items.SALMON);
		this.tag(RAW_SQUID).add(Items_Teatime.IKA.get());
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
		
		this.tag(SAPLINGS_CHERRY).add(Items_Seasonal.SAKURA_nae.get());
		this.tag(SAPLINGS_ACER).add(Items_Seasonal.KAEDE_nae.get());
		this.tag(SAPLINGS_GINKGO).add(Items_Seasonal.ICHOH_nae.get());
		this.tag(SAPLINGS_AUTUMN_OAK).add(Items_Seasonal.OAKKARE_nae.get());
		this.tag(SAPLINGS)
		.addTag(ItemTags.SAPLINGS).addTag(SAPLINGS_CHERRY)
		.addTag(SAPLINGS_ACER).addTag(SAPLINGS_GINKGO).addTag(SAPLINGS_AUTUMN_OAK);
		
		this.tag(SAUCE_MAYONNAISE)
		.add(Items_Teatime.MAYO_bot_14.get(), Items_NoTab.MAYO_bot_24.get(), Items_NoTab.MAYO_bot_34.get(), Items_NoTab.MAYO_bot_44.get())
		.addTag(MAYONAISE);
		this.tag(SAUCE_SOYSAUSE)
		.add(Items_Teatime.SHOUYU_bot_14.get(), Items_NoTab.SHOUYU_bot_24.get(), Items_NoTab.SHOUYU_bot_34.get(), Items_NoTab.SHOUYU_bot_44.get())
		.addTag(FOODS_SOYSAUSE);
		this.tag(SAUCE_WORCETER)
		.add(Items_Teatime.OSAUCE_bot_14.get(), Items_NoTab.OSAUCE_bot_24.get(), Items_NoTab.OSAUCE_bot_34.get(), Items_NoTab.OSAUCE_bot_44.get())
		.addTag(WORCETERSOUCE);
		this.tag(SAUCE).addTag(SAUCE_MAYONNAISE).addTag(SAUCE_SOYSAUSE).addTag(SAUCE_WORCETER);
		
		this.tag(SEEDS_AZUKI).add(Items_Teatime.SEEDS_AZUKI.get());
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
		.addTag(SEEDS_AZUKI).addTag(SEEDS_CABBAGE).addTag(SEEDS_CHERRY).addTag(SEEDS_CHILI).addTag(SEEDS_HAKUSAI)
		.addTag(SEEDS_CORN).addTag(SEEDS_CUMIN).addTag(SEEDS_GREENONION).addTag(SEEDS_ONION)
		.addTag(SEEDS_RICE).addTag(SEEDS_SOY).addTag(SEEDS_SOYBEAN).addTag(SEEDS_SPINACH).addTag(SEEDS_TOMATO);
		
		this.tag(SLIMEBALLS).add(Items.SLIME_BALL);
		
		this.tag(SPICES_BLACKPEPPER).add(Items_Teatime.DUST_PEPPER.get());
		this.tag(SPICES_CHILI).add(Items_Teatime.DUST_CHILI.get());
		this.tag(SPICES_CUMIN).add(Items_Teatime.DUST_CUMIN.get());
		this.tag(SPICES_PEPPER).add(Items_Teatime.DUST_PEPPER.get());
		this.tag(SPICES_TURMERIC).add(Items_Teatime.DUST_TURMERIC.get());
		this.tag(SPICES_VANILLA)
		.add(Items_Teatime.VANILLA_bot_14.get(), Items_NoTab.VANILLA_bot_24.get(), 
				Items_NoTab.VANILLA_bot_34.get(), Items_NoTab.VANILLA_bot_44.get());
		this.tag(SPICES)
		.addTag(SPICES_BLACKPEPPER).addTag(SPICES_CHILI).addTag(SPICES_CUMIN)
		.addTag(SPICES_PEPPER).addTag(SPICES_TURMERIC).addTag(SPICES_VANILLA);
		
		this.tag(DRY_TENGUSA).add(Items_Teatime.TENGUSA_DRY.get());
		this.tag(STRING).add(Items_Seasonal.ORIITO.get());
		this.tag(SUGAR).add(Items.SUGAR);
		
		this.tag(WOODEN_SLABS_CHERRY).add(Items_Seasonal.SAKURA_slabhalf.get());
		this.tag(WOODEN_SLABS_ACER).add(Items_Seasonal.KAEDE_slabhalf.get());
		this.tag(WOODEN_SLABS_GINKGO).add(Items_Seasonal.ICHOH_slabhalf.get());
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
