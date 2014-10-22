package com.dta.extracarts;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Skylar on 10/17/2014.
 */
public interface Module {
    public String getModuleName();
	public void setIsActive(Boolean isActive);
	public Boolean getIsActive();
	public void init(FMLPreInitializationEvent event);
	public void load(FMLInitializationEvent event);
	public void postInit(FMLPostInitializationEvent event);
}
