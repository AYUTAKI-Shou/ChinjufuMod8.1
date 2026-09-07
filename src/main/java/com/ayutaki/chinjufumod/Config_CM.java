package com.ayutaki.chinjufumod;

import org.apache.commons.lang3.tuple.Pair;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.IntValue;

public class Config_CM {

	public static final ModConfigSpec SPEC;
	public static final Config_CM INSTANCE;
	
	public final BooleanValue blastBlockBreak;
	
	public final BooleanValue sakuraBiomeGene;
	public final IntValue sakuraBiomeChance;
	public final BooleanValue kaedeBiomeGene;
	public final IntValue kaedeBiomeChance;
	public final BooleanValue ichohBiomeGene;
	public final IntValue ichohBiomeChance;
	
	public final BooleanValue chestnutsFall;
	public final BooleanValue lowSound;
	public final BooleanValue useMAKIMONO;
	
	private Config_CM(ModConfigSpec.Builder builder) {
		/** Blast **/
		builder.comment("Ammo blast breaks the block.").push("explosion");
		this.blastBlockBreak = builder
				.comment("Ammo blast breaks the block, when this setting is true.")
				.define("Break_Blocks", true);
		builder.pop();
		
		/** SAKURA **/
		builder.comment("Generation and chance of CherryBiome.").push("biome_cherry");
		this.sakuraBiomeGene = builder
				.comment("CherryBiome will be generated, when this setting is true.")
				.define("CherryBiome_Generate", true);
		this.sakuraBiomeChance = builder
				.comment("Common=9, Rare=1, Default=2")
				.defineInRange("CherryBiome_Chance", 2, 1, 9);
		builder.pop();

		/** KAEDE **/
		builder.comment("Generation and chance of AcerBiome.").push("biome_acer");
		this.kaedeBiomeGene = builder
				.comment("AcerBiome will be generated, when this setting is true.")
				.define("AcerBiome_Generate", true);
		this.kaedeBiomeChance = builder
				.comment("Common=9, Rare=1, Default=2")
				.defineInRange("AcerBiome_Chance", 2, 1, 9);
		builder.pop();

		/** ICHOH **/
		builder.comment("Generation and chance of GinkgoBiome.").push("biome_ginkgo");
		this.ichohBiomeGene = builder
				.comment("GinkgoBiome will be generated, when this setting is true.")
				.define("GinkgoBiome_Generate", true);
		this.ichohBiomeChance = builder
				.comment("Common=9, Rare=1, Default=2")
				.defineInRange("GinkgoBiome_Chance", 2, 1, 9);
		builder.pop();
		
		/** KURI **/
		builder.comment("Chestnuts fall from Autumn leaves.").push("chestnuts_fall");
		this.chestnutsFall = builder
				.comment("Chestnuts fall from Autumn leaves, when this setting is true.")
				.define("Chestnuts_Fall", true);
		builder.pop();
		
		/** Low Sound **/
		builder.comment("Low-processing sound on carrier-borne aircraft.").push("low_sound");
		this.lowSound = builder
				.comment("Use a more low-processing sound on carrier-borne aircraft, when this setting is true.")
				.define("Low_Sound", false);
		builder.pop();
		
		/** MAKIMONO **/
		builder.comment("Use a MAKIMONO block.").push("use_makimono");
		this.useMAKIMONO = builder
				.comment("You can use MAKIMONO block, when this setting is true.")
				.define("Use_MAKIMONO", true);
		builder.pop();
	}
	
	static {
		final Pair<Config_CM, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Config_CM::new);
		SPEC= specPair.getRight();
		INSTANCE = specPair.getLeft();
	}
}
