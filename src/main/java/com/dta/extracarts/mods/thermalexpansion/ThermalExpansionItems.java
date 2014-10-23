package com.dta.extracarts.mods.thermalexpansion;

import com.dta.extracarts.ModInfo;
import com.dta.extracarts.mods.thermalexpansion.items.ItemStrongBoxCart;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by Skylar on 10/22/2014.
 */
public class ThermalExpansionItems {
	public static ItemStrongBoxCart strongBox;

	public static void init() {
		strongBox = new ItemStrongBoxCart();
	}

	public static void registerItems() {
		GameRegistry.registerItem(strongBox, ModInfo.MODID + "_" + strongBox.getUnlocalizedName());
	}
}
