package com.ayutaki.chinjufumod.items.teatime;

import java.util.List;

import javax.annotation.Nullable;

import com.ayutaki.chinjufumod.items.base.Item_Regi;
import com.ayutaki.chinjufumod.registry.Items_Teatime;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PuddingRaw_NT extends Item_Regi {

	public PuddingRaw_NT(String name) {
		super(name);
		setUnlocalizedName(name);
		setMaxStackSize(64);
		
		setContainerItem(Items_Teatime.NABE_kara);
		/** Have sub items. **/
		setHasSubtypes(true);
	}

	/* This item will remain. */
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(Items_Teatime.NABE_kara);
	}

	/* Sub item meta and name. */
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getMetadata()) {
		case 1:
		default:
			return "item." + "item_raw_pudding_custard";
		case 2:
			return "item." + "item_raw_pudding_greentea";
		case 3:
			return "item." + "item_raw_pudding_redtea";
		case 4:
			return "item." + "item_raw_pudding_cacao";
		}
	}

	/* ToolTip*/
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> itemTip, ITooltipFlag advanced) {
		itemTip.add(I18n.format("tips.item_raw_kanten.name"));
	}
}
