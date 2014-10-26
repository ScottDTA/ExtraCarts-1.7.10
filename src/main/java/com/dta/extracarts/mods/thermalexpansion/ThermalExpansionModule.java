package com.dta.extracarts.mods.thermalexpansion;

import com.dta.extracarts.Module;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

/**
 * Created by Skylar on 10/22/2014.
 */
public class ThermalExpansionModule extends Module{
    @Override
    public String getModuleName() {
        return "Thermal Expansion";
    }

	@Override
	public Boolean areRequirementsMet() {
		return Loader.isModLoaded("ThermalExpansion");
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		ThermalExpansionItems.init();
		ThermalExpansionItems.registerItems();
	}
}
