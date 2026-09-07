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
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class ItemCMTags extends ItemTagsProvider {

	public ItemCMTags(PackOutput outPut, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blockTag) {
		super(outPut, provider, blockTag, ChinjufuMod.MOD_ID);
	}
	
	/* TagKey */
	public static final TagKey<Item> REPAIRS_RED_CARPET = create("repairs_red_carpet");
	public static final TagKey<Item> REPAIRS_OAK_SLAB = create("repairs_oak_slab");
	public static final TagKey<Item> REPAIRS_BIRCH_SLAB = create("repairs_birch_slab");
	public static final TagKey<Item> REPAIRS_DARKOAK_SLAB = create("repairs_darkoak_slab");
	public static final TagKey<Item> REPAIRS_KAEDE_SLAB = create("repairs_kaede_slab");
	public static final TagKey<Item> REPAIRS_SAKURA = create("repairs_sakura");
	public static final TagKey<Item> REPAIRS_KAEDE = create("repairs_kaede");
	public static final TagKey<Item> REPAIRS_ICHOH = create("repairs_ichoh");
	
	public static final TagKey<Item> REPAIRS_DANDELION = create("repairs_dandelion");
	public static final TagKey<Item> REPAIRS_POPPY = create("repairs_poppy");
	public static final TagKey<Item> REPAIRS_TANMONO = create("repairs_tanmono");
	
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
	
	
	private static TagKey<Item> create(String name) {
		return ItemTags.create(ChinjufuMod.id(name));
	}
	
	
	/* addTags */
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		/** chinjufumod **/
		this.tag(REPAIRS_RED_CARPET).add(Items.RED_CARPET);
		this.tag(REPAIRS_OAK_SLAB).add(Items.OAK_SLAB);
		this.tag(REPAIRS_BIRCH_SLAB).add(Items.BIRCH_SLAB);
		this.tag(REPAIRS_DARKOAK_SLAB).add(Items.DARK_OAK_SLAB);
		this.tag(REPAIRS_KAEDE_SLAB).add(Items_Seasonal.KAEDE_slabhalf.get());
		this.tag(REPAIRS_SAKURA).add(Items_Seasonal.SAKURA_planks.get());
		this.tag(REPAIRS_KAEDE).add(Items_Seasonal.KAEDE_planks.get());
		this.tag(REPAIRS_ICHOH).add(Items_Seasonal.ICHOH_planks.get());
		
		this.tag(REPAIRS_DANDELION).add(Items.DANDELION);
		this.tag(REPAIRS_POPPY).add(Items.POPPY);
		this.tag(REPAIRS_TANMONO).add(Items_Seasonal.TANMONO.get());
	
		this.tag(ADMIRAL_STAMP).add(Items_NoTab.ADMIRAL_STAMP.get(), Items_Chinjufu.ADMIRAL_STAMPB.get());
		this.tag(BONE_MEAL).add(Items_NoTab.HAMAGURI_KARA.get(), Items_Teatime.SAKEKASU.get());
		this.tag(COOKING_WATER).add(Items.POTION, Items_Teatime.KEIRYO_CUP_full.get());
		this.tag(KAGAMIMOCHI_CITRUS).addTag(ItemCTags_CM.CROPS_CITRUS).addTag(ItemCTags_CM.CROPS_ORANGE);
		this.tag(MATERIALS_BOOTS).add(Items.BLACK_CARPET, Items.LEATHER);
		this.tag(MATERIALS_FUEL).add(Items.COAL, Items.CHARCOAL);
		this.tag(MATERIALS_LAMP).addTag(ItemCTags_CM.INGOTS_ALUMINUM).addTag(ItemCTags_CM.INGOTS_IRON);
		this.tag(MATERIALS_OIL)
		.add(Items.SLIME_BALL, Items_Teatime.SOYOIL_bot_12.get(), Items_NoTab.SOYOIL_bot_22.get());
		this.tag(MATERIALS_POTATO).addTag(ItemCTags_CM.CROPS_POTATO).add(Items.POISONOUS_POTATO);
		this.tag(MATERIALS_STRAW).add(Items_Teatime.INEWARA.get()).addTag(ItemCTags_CM.CROPS_WHEAT);
		this.tag(MATERIALS_TATAMI)
		.add(Items.SUGAR_CANE, Items_Teatime.INEWARA.get()).addTag(ItemCTags_CM.CROPS_WHEAT);
		this.tag(MATERIALS_YARN).add(Items.STRING, Items_Seasonal.ORIITO.get());
		this.tag(MATERIALS_SEAGASS).add(Items.SEAGRASS, Items_Teatime.NORI_N.get());
	}
	
	@Override
	public String getName() {
		return "ChinjufuMod Item Tags";
	}
}
