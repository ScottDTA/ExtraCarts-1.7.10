package com.dta.extracarts.mods.ironchest;

import com.dta.extracarts.ModInfo;
import com.dta.extracarts.SubMod;
import com.dta.extracarts.mods.ironchest.entities.IronChestEntities;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Skylar on 10/17/2014.
 */
public class IronChestSubMod implements SubMod {
	@Override
	public void init(FMLPreInitializationEvent event) {

	}

	@Override
	public void load(FMLInitializationEvent event) {

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		if (Loader.isModLoaded("IronChest") && ModInfo.IRON_CHEST_ENABLED) {
			IronChestItems.init();
			IronChestItems.registerItems();
			IronChestItems.registerRecipes();
			IronChestEntities.init();
		}
	}
}
