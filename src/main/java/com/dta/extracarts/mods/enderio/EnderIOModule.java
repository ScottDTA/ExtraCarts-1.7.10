package com.dta.extracarts.mods.enderio;

import com.dta.extracarts.Module;
import cpw.mods.fml.common.Loader;

/**
 * Created by Skylar on 4/8/2015.
 */
public class EnderIOModule extends Module {
	@Override
	public String getModuleName() {
		return "Ender IO";
	}

	@Override
	public Boolean areRequirementsMet(){
		return Loader.isModLoaded("EnderIO");
	}
}
