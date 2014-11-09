package com.dta.extracarts.mods.enderio;

import com.dta.extracarts.Module;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

/**
 * Created by Skylar on 10/22/2014.
 */
public class EnderIOModule extends Module{
    @Override
    public String getModuleName() {
        return "EnderIO";
    }

	@Override
	public Boolean areRequirementsMet() {
		return Loader.isModLoaded("EnderIO");
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		EnderIOItems.init();
		EnderIOItems.registerItems();
	}
}
