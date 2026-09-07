package com.ayutaki.chinjufumod.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

public class AssetEquipment_Data implements DataProvider {
	private final PackOutput.PathProvider pathProvider;
	
	public AssetEquipment_Data(PackOutput outPut) {
		this.pathProvider = outPut.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
	}

	@Override
	public CompletableFuture<?> run(CachedOutput outPut) {
		Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> map = new HashMap<>();
		AssetArmor_CM.bootstrap((resource, model) -> {
			if (map.putIfAbsent(resource, model) != null) {
				throw new IllegalStateException("Tried to register chinjufumod wear model twice for id: " + resource); }
		});
		return DataProvider.saveAll(outPut, EquipmentClientInfo.CODEC, this.pathProvider::json, map);
	}

	@Override
	public String getName() {
		return "ChinjufuMod EquipmentAsset";
	}
}
