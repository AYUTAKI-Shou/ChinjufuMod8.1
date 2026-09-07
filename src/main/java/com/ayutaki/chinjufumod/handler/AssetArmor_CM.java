package com.ayutaki.chinjufumod.handler;

import java.util.function.BiConsumer;

import com.ayutaki.chinjufumod.ChinjufuMod;

import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class AssetArmor_CM {

	/* GISOU */
	public static final ResourceKey<EquipmentAsset> FUBUKI = createId("fubuki");
	public static final ResourceKey<EquipmentAsset> KASUMI = createId("kasumi");
	public static final ResourceKey<EquipmentAsset> SHIGURE = createId("shigure");
	public static final ResourceKey<EquipmentAsset> SHIRATSUYU = createId("shiratsuyu");
	public static final ResourceKey<EquipmentAsset> AKATSUKI = createId("akatsuki");
	
	public static final ResourceKey<EquipmentAsset> YURA = createId("yura");
	public static final ResourceKey<EquipmentAsset> SENDAI = createId("sendai");
	public static final ResourceKey<EquipmentAsset> MOGAMI = createId("mogami");
	public static final ResourceKey<EquipmentAsset> TONE = createId("tone");
	
	public static final ResourceKey<EquipmentAsset> RJ = createId("ryujou");
	public static final ResourceKey<EquipmentAsset> ZUIHOU = createId("zuihou");
	public static final ResourceKey<EquipmentAsset> KAGA = createId("kaga");
	public static final ResourceKey<EquipmentAsset> AKAGI = createId("akagi");

	public static final ResourceKey<EquipmentAsset> KONGOU = createId("kongou");
	public static final ResourceKey<EquipmentAsset> FUSOU = createId("fusou");
	public static final ResourceKey<EquipmentAsset> ISE = createId("ise");
	public static final ResourceKey<EquipmentAsset> NAGATO = createId("nagato");

	public static final ResourceKey<EquipmentAsset> I168 = createId("i168");
	public static final ResourceKey<EquipmentAsset> I13 = createId("i13");
	public static final ResourceKey<EquipmentAsset> RO500 = createId("ro500");
	public static final ResourceKey<EquipmentAsset> I401 = createId("i401");
	
	/* SantaCos */
	public static final ResourceKey<EquipmentAsset> AKASHISANTA = createId("santaakashi");
	public static final ResourceKey<EquipmentAsset> KUMANOSANTA = createId("santakumano");
	public static final ResourceKey<EquipmentAsset> SUZUYASANTA = createId("santasuzuya");
	public static final ResourceKey<EquipmentAsset> RJ_SANTA = createId("santaryujou");
	public static final ResourceKey<EquipmentAsset> TEITOKUSANTA = createId("santattk");
	
	/* YUKATA */
	public static final ResourceKey<EquipmentAsset> IKADUCHIYKT = createId("ykt_ikaduchi");
	public static final ResourceKey<EquipmentAsset> INADUMAYKT = createId("ykt_inaduma");
	public static final ResourceKey<EquipmentAsset> HAMAKAZEYKT = createId("ykt_hamakaze");
	public static final ResourceKey<EquipmentAsset> URAKAZEYKT = createId("ykt_urakaze");
	public static final ResourceKey<EquipmentAsset> KAWAKAZEYKT = createId("ykt_kawakaze");
	public static final ResourceKey<EquipmentAsset> OBOROYKT = createId("ykt_oboro");
	public static final ResourceKey<EquipmentAsset> TTOKUYKT = createId("ykt_ttoku");
	public static final ResourceKey<EquipmentAsset> TTOKUYKTB = createId("ykt_ttokub");
	
	static ResourceKey<EquipmentAsset> createId(String name) {
		return ResourceKey.create(EquipmentAssets.ROOT_ID, ChinjufuMod.id(name));
	}

	
	/////* Bootstrap */////
	public static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
		/* GISOU */
		consumer.accept(FUBUKI, onlyHumanoid("fubuki"));
		consumer.accept(KASUMI, onlyHumanoid("kasumi"));
		consumer.accept(SHIGURE, onlyHumanoid("shigure"));
		consumer.accept(SHIRATSUYU, onlyHumanoid("shiratsuyu"));
		consumer.accept(AKATSUKI, onlyHumanoid("akatsuki"));
		
		consumer.accept(YURA, onlyHumanoid("yura"));
		consumer.accept(SENDAI, onlyHumanoid("sendai"));
		consumer.accept(MOGAMI, onlyHumanoid("mogami"));
		consumer.accept(TONE, onlyHumanoid("tone"));
		
		consumer.accept(RJ, onlyHumanoid("ryujou"));
		consumer.accept(ZUIHOU, onlyHumanoid("zuihou"));
		consumer.accept(KAGA, onlyHumanoid("kaga"));
		consumer.accept(AKAGI, onlyHumanoid("akagi"));

		consumer.accept(KONGOU, onlyHumanoid("kongou"));
		consumer.accept(FUSOU, onlyHumanoid("fusou"));
		consumer.accept(ISE, onlyHumanoid("ise"));
		consumer.accept(NAGATO, onlyHumanoid("nagato"));

		consumer.accept(I168, onlyHumanoid("i168"));
		consumer.accept(I13, onlyHumanoid("i13"));
		consumer.accept(RO500, onlyHumanoid("ro500"));
		consumer.accept(I401, onlyHumanoid("i401"));
		
		/* SantaCos */
		consumer.accept(AKASHISANTA, onlyHumanoid("santaakashi"));
		consumer.accept(KUMANOSANTA, onlyHumanoid("santakumano"));
		consumer.accept(SUZUYASANTA, onlyHumanoid("santasuzuya"));
		consumer.accept(RJ_SANTA, onlyHumanoid("santaryujou"));
		consumer.accept(TEITOKUSANTA, onlyHumanoid("santattk"));
		
		/* YUKATA */
		consumer.accept(IKADUCHIYKT, onlyHumanoid("ykt_ikaduchi"));
		consumer.accept(INADUMAYKT, onlyHumanoid("ykt_inaduma"));
		consumer.accept(HAMAKAZEYKT, onlyHumanoid("ykt_hamakaze"));
		consumer.accept(URAKAZEYKT, onlyHumanoid("ykt_urakaze"));
		consumer.accept(KAWAKAZEYKT, onlyHumanoid("ykt_kawakaze"));
		consumer.accept(OBOROYKT, onlyHumanoid("ykt_oboro"));
		consumer.accept(TTOKUYKT, onlyHumanoid("ykt_ttoku"));
		consumer.accept(TTOKUYKTB, onlyHumanoid("ykt_ttokub"));
	}
	
	private static EquipmentClientInfo onlyHumanoid(String name) {
		return EquipmentClientInfo.builder().addHumanoidLayers(ChinjufuMod.id(name)).build();
	}
}
