package com.dta.extracarts.mods.thermalexpansion;

import com.dta.extracarts.Module;
import cpw.mods.fml.common.Loader;

/**
 * Created by Skylar on 10/22/2014.
 */
public class ThermalExpansionModule extends Module{
    @Override
    public String getModuleName() {
        return "Thermal Expansion";
    }

	public Boolean areRequirementsMet() {
		return Loader.isModLoaded("ThermalExpansion");
	}
}
