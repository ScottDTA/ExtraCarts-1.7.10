package com.dta.extracarts.mods.mfr;

import com.dta.extracarts.ModInfo;
import com.dta.extracarts.SubMod;
import com.dta.extracarts.mods.mfr.entities.MFREntities;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Skylar on 10/17/2014.
 */
public class MFRSubMod implements SubMod {
	@Override
	public void init(FMLPreInitializationEvent event) {

	}

	@Override
	public void load(FMLInitializationEvent event) {

	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		if (Loader.isModLoaded("MineFactoryReloaded") && ModInfo.DSU_ENABLED) {
			System.out.println("MFR is loaded");
			MFRItems.init();
			MFRItems.registerItems();
			MFRItems.registerRecipes();
			MFREntities.init();
		}
	}
}
