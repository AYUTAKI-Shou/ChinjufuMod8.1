package com.ayutaki.chinjufumod.items.foods;

import net.minecraft.world.food.FoodProperties;

public class FoodPoints {

	public static final FoodProperties ROTTEN_FOOD = new FoodProperties.Builder().nutrition(3).saturationModifier(0.1F).build();
	
	/* Teatime, Food Property Saturation */
	public static final FoodProperties FPS1_01A = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).alwaysEdible().build();
	public static final FoodProperties FPS1_03 = new FoodProperties.Builder().nutrition(1).saturationModifier(0.3F).build();
	public static final FoodProperties FPS2_03 = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).build();
	public static final FoodProperties FPS3_03 = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3F).build();
	public static final FoodProperties FPS4_06 = new FoodProperties.Builder().nutrition(4).saturationModifier(0.6F).build();
	
	public static final FoodProperties CORN_B = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).build();
	public static final FoodProperties HAKUSAI2 = new FoodProperties.Builder().nutrition(2).saturationModifier(0.6F).build();

	public static final FoodProperties PC_PIZZA = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).build();
	public static final FoodProperties KIRIMI = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build();
	public static final FoodProperties SUSHI = new FoodProperties.Builder().nutrition(4).saturationModifier(0.6F).alwaysEdible().build();
	public static final FoodProperties SHOUYUSUSHI = new FoodProperties.Builder().nutrition(5).saturationModifier(0.8F).alwaysEdible().build();
	public static final FoodProperties FUTOMAKI = new FoodProperties.Builder().nutrition(4).saturationModifier(0.6F).alwaysEdible().build();
	
	public static final FoodProperties CAKE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.4F).build();
	public static final FoodProperties BUN = new FoodProperties.Builder().nutrition(6).saturationModifier(0.4F).build();
	public static final FoodProperties SANDWICH = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).build();

	public static final FoodProperties NORI_I = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build();
	public static final FoodProperties CUT_IKA = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).build();
	public static final FoodProperties COOKED_IKA = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();
	public static final FoodProperties COOKED_HAMAGURI = new FoodProperties.Builder().nutrition(2).saturationModifier(0.4F).build();

	/* DISH, Dish Property Saturation */
	public static final FoodProperties DPS1_02 = new FoodProperties.Builder().nutrition(1).saturationModifier(0.2F).build();
	public static final FoodProperties DPS2_03 = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).build();
	public static final FoodProperties DPS3_04 = new FoodProperties.Builder().nutrition(3).saturationModifier(0.4F).build();
	public static final FoodProperties DPS5_06 = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6F).build();
	public static final FoodProperties DPS8_10 = new FoodProperties.Builder().nutrition(8).saturationModifier(1.0F).build();
	public static final FoodProperties DPS10_10 = new FoodProperties.Builder().nutrition(10).saturationModifier(1.0F).build();
	public static final FoodProperties DPS12_10 = new FoodProperties.Builder().nutrition(12).saturationModifier(1.0F).build();
	
	/* GLASS */
	public static final FoodProperties ALWAYS_0 = new FoodProperties.Builder().alwaysEdible().build();
	
	/* Seasonal */
	public static final FoodProperties KURI_ROAST = new FoodProperties.Builder().nutrition(2).saturationModifier(0.4F).build();
	public static final FoodProperties KURI_SWEET = new FoodProperties.Builder().nutrition(4).saturationModifier(0.6F).build();
	public static final FoodProperties KURI_CHOCO = new FoodProperties.Builder().nutrition(4).saturationModifier(0.6F).alwaysEdible().build();
	public static final FoodProperties TAKENOKO_ROAST = new FoodProperties.Builder().nutrition(4).saturationModifier(0.6F).build();

	public static final FoodProperties CHOCO = new FoodProperties.Builder().nutrition(1).saturationModifier(0.3F).alwaysEdible().build();
}
