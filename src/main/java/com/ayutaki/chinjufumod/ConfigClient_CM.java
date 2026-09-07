package com.ayutaki.chinjufumod;

import org.apache.commons.lang3.tuple.Pair;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;

public class ConfigClient_CM {

	public static final ModConfigSpec SPEC;
	public static final ConfigClient_CM INSTANCE;
	
	public IntValue armorTexture;
	
	private ConfigClient_CM(ModConfigSpec.Builder builder) {
		builder.comment("Texture of ChinjufuMod's Armor.").push("armor_texture");
		this.armorTexture = builder
				.comment("All=0, Without Clothes=1, Only Clothes=2")
				.defineInRange("Armor_Texture", 0, 0, 2);
		builder.pop();
	}
	
	static {
		final Pair<ConfigClient_CM, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(ConfigClient_CM::new);
		SPEC= specPair.getRight();
		INSTANCE = specPair.getLeft();
	}
}
