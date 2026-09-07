package com.ayutaki.chinjufumod.handler;

import java.util.Set;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.block.WoodType;

public class WoodType_CM extends WoodType {
	
	private static final Set<WoodType> VALUES = new ObjectArraySet<>();
	public static final WoodType BLACKBOARD = register(new WoodType_CM("blackboard"));

	private final String name;
	
	protected WoodType_CM(String nameIn) {
		super(nameIn);
		this.name = nameIn;
	}
	
	private static WoodType register(WoodType woodTypeIn) {
		VALUES.add(woodTypeIn);
		return woodTypeIn;
	}
	 
	public String getName() {
		return this.name;
	}
}
