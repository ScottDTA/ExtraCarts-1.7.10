package com.dta.extracarts.mods.enderio;

import com.dta.extracarts.ModInfo;
import com.dta.extracarts.mods.enderio.items.ItemCapacitorBankCart;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Skylar on 10/22/2014.
 */
public class EnderIOItems {
	public static ItemCapacitorBankCart strongBox;

	public static void init() {
		strongBox = new ItemCapacitorBankCart();
	}

	public static void registerItems() {
		GameRegistry.registerItem(strongBox, ModInfo.MODID + "_" + strongBox.getUnlocalizedName());
	}
}
