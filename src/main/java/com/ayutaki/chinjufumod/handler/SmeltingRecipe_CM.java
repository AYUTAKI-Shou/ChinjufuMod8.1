package com.ayutaki.chinjufumod.handler;

import com.ayutaki.chinjufumod.registry.Items_Chinjufu;
import com.ayutaki.chinjufumod.registry.Items_Seasonal;
import com.ayutaki.chinjufumod.registry.Items_Teatime;
import com.ayutaki.chinjufumod.registry.Items_Wablock;
import com.ayutaki.chinjufumod.registry.Items_Wadeco;
import com.ayutaki.chinjufumod.registry.Items_WallPane;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SmeltingRecipe_CM {

	/* かまどレシピの登録 Register smelting recipe.*/
	public static void registerSmeltingRecipes() {
		register();
	}

	public static void register() {
		/*ひび入り花崗岩レンガ*/
		GameRegistry.addSmelting(new ItemStack(Items_WallPane.BRICK_C, 1, 1), new ItemStack(Items_WallPane.BRICK_C, 1, 7), 0.5F);

		/*ひび入り閃緑岩レンガ*/
		GameRegistry.addSmelting(new ItemStack(Items_WallPane.BRICK_C, 1, 2), new ItemStack(Items_WallPane.BRICK_C, 1, 8), 0.5F);

		/*ひび入り安山岩レンガ*/
		GameRegistry.addSmelting(new ItemStack(Items_WallPane.BRICK_C, 1, 3), new ItemStack(Items_WallPane.BRICK_C, 1, 9), 0.5F);

		/*瓦(黒)*/
		GameRegistry.addSmelting(new ItemStack(Items_Wablock.CLAYKAWARA, 1, 0), new ItemStack(Items_Wablock.KAWARA, 1, 15), 0.5F);

		/*アルミニウム精錬 素材 -> 精錬結果*/
		GameRegistry.addSmelting(new ItemStack(Items_Chinjufu.BAUXITE, 1, 0), new ItemStack(Items_Chinjufu.ALUMINUM, 1, 0), 1.0F);

		/*マッチ*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.Item_MATCH, 1, 0), new ItemStack(Items.DYE, 1, 15), 0.5F);

		/*生酒の火入れ*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.NAMASAKEBOT, 1, 0), new ItemStack(Items_Teatime.SAKEBOT, 1, 0), 0.5F);

		/*皿*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.CLAY_DISH, 1, 1), new ItemStack(Items_Teatime.Item_SARA, 1, 0), 0.5F);

		/*湯呑み*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.CLAY_DISH, 1, 2), new ItemStack(Items_Teatime.Item_DISH, 1, 1), 0.5F);

		/*急須*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.CLAY_DISH, 1, 3), new ItemStack(Items_Teatime.KYUSU_kara, 1, 0), 0.5F);

		/*ティーカップ*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.CLAY_DISH, 1, 4), new ItemStack(Items_Teatime.Item_DISH, 1, 2), 0.5F);

		/*ティーポット*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.CLAY_DISH, 1, 5), new ItemStack(Items_Teatime.TEAPOT_kara, 1, 0), 0.5F);

		/*茶碗*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.CLAY_DISH, 1, 6), new ItemStack(Items_Teatime.Item_DISH, 1, 3), 0.5F);

		/*鍋*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.CLAY_DISH, 1, 7), new ItemStack(Items_Teatime.NABE_kara, 1, 0), 0.5F);

		/*呑水*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.CLAY_DISH, 1, 8), new ItemStack(Items_Teatime.Item_DISH, 1, 5), 0.5F);

		/*どんぶり*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.CLAY_DISH, 1, 9), new ItemStack(Items_Teatime.Item_DISH, 1, 6), 0.5F);

		/*テーブルロール*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.KOMUGI, 1, 3), new ItemStack(Items_Teatime.BUN, 1, 0), 0.5F);

		/*スコーン*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.KOMUGI, 1, 5), new ItemStack(Items_Teatime.SCONE, 1, 0), 0.5F);

		/*せんべい*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.KOMUGI, 1, 6), new ItemStack(Items_Teatime.SENBEI, 1, 0), 0.5F);

		/*焼きとうもろこし*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.FOOD_CORN, 1, 0), new ItemStack(Items_Teatime.FOOD_CORN_B, 1, 0), 0.5F);

		/* 新原木から木炭 */
		GameRegistry.addSmelting(new ItemStack(Items_Seasonal.SAKURA_log, 1, 0), new ItemStack(Items.COAL, 1, 1), 0.5F);
		GameRegistry.addSmelting(new ItemStack(Items_Seasonal.KAEDE_log, 1, 0), new ItemStack(Items.COAL, 1, 1), 0.5F);
		GameRegistry.addSmelting(new ItemStack(Items_Seasonal.ICHOH_log, 1, 0), new ItemStack(Items.COAL, 1, 1), 0.5F);

		/* 藁灰 */
		GameRegistry.addSmelting(new ItemStack(Items_Seasonal.WARATABA, 1, 0), new ItemStack(Items_Seasonal.WARAHAI, 1, 0), 0.1F);
		GameRegistry.addSmelting(Blocks.HAY_BLOCK, new ItemStack(Items_Seasonal.WARAHAI, 1, 0), 0.1F);

		/* ピザ */
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.KOMUGI, 1, 8), new ItemStack(Items_Teatime.PIZZA, 1, 0), 0.1F);
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.KOMUGI, 1, 9), new ItemStack(Items_Teatime.PIZZA, 1, 1), 0.1F);
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.KOMUGI, 1, 10), new ItemStack(Items_Teatime.PIZZA, 1, 2), 0.1F);
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.KOMUGI, 1, 11), new ItemStack(Items_Teatime.PIZZA, 1, 3), 0.1F);
		
		/* チョコレート焙煎 */
		GameRegistry.addSmelting(new ItemStack(Items_Seasonal.COCOA_F, 1, 0), new ItemStack(Items_Seasonal.COCOA_R, 1, 0), 0.1F);

		/*竹ブロック(焦げ)*/
		GameRegistry.addSmelting(new ItemStack(Items_Wadeco.TAKECUBE, 1, 0), new ItemStack(Items_Wadeco.TAKECUBE_K, 1, 0), 0.5F);
		
		/*焼きイカ*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.CUT_IKA, 1, 0), new ItemStack(Items_Teatime.COOKED_IKA, 1, 0), 0.5F);
		
		/*焼きハマグリ*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.HAMAGURI, 1, 0), new ItemStack(Items_Teatime.HAMAGURI_COOK, 1, 0), 0.1F);
		
		/*焼きクリ*/
		GameRegistry.addSmelting(new ItemStack(Items_Seasonal.KURI, 1, 0), new ItemStack(Items_Seasonal.KURI_ROAST, 1, 0), 0.1F);
		
		/*焼きタケノコ*/
		GameRegistry.addSmelting(new ItemStack(Items_Seasonal.TAKENOKO, 1, 0), new ItemStack(Items_Seasonal.TAKENOKO_ROAST, 1, 0), 0.5F);
		
		/*あんパン*/
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.PAN_KIJI, 1, 1), new ItemStack(Items_Teatime.PAN_FOOD, 1, 1), 0.5F);
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.PAN_KIJI, 1, 2), new ItemStack(Items_Teatime.PAN_FOOD, 1, 2), 0.5F);
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.PAN_KIJI, 1, 3), new ItemStack(Items_Teatime.PAN_FOOD, 1, 3), 0.5F);
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.PAN_KIJI, 1, 4), new ItemStack(Items_Teatime.PAN_FOOD, 1, 4), 0.5F);
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.PAN_KIJI, 1, 5), new ItemStack(Items_Teatime.PAN_FOOD, 1, 5), 0.5F);
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.PAN_KIJI, 1, 6), new ItemStack(Items_Teatime.PAN_FOOD, 1, 6), 0.5F);
		GameRegistry.addSmelting(new ItemStack(Items_Teatime.PAN_KIJI, 1, 7), new ItemStack(Items_Teatime.PAN_FOOD, 1, 7), 0.5F);
	}
}
