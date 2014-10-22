package com.dta.extracarts;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Skylar on 10/17/2014.
 */
public abstract class Module {
	private Boolean isActive = true;

	public abstract String getModuleName();

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void init(FMLPreInitializationEvent event) {}
	public void load(FMLInitializationEvent event) {}
	public void postInit(FMLPostInitializationEvent event) {}
}
