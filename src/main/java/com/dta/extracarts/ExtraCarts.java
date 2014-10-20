package com.dta.extracarts;

import com.dta.extracarts.mods.base.BaseModule;
import com.dta.extracarts.mods.ironchest.IronChestModule;
import com.dta.extracarts.mods.mfr.MFRModule;

import com.dta.extracarts.client.GuiHandler;
import com.dta.extracarts.config.ConfigHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;

@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class ExtraCarts {
	@Instance(ModInfo.MODID)
	public static ExtraCarts instance;

	public static ArrayList<Module> modules = new ArrayList<Module>();

	@EventHandler
	public void init(FMLPreInitializationEvent event) {
        ConfigHandler.init(event.getSuggestedConfigurationFile());

        for(Module module : modules) {
			module.init(event);
		}
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
	    new GuiHandler();
		for(Module module : modules) {
			module.load(event);
		}
	}
	    
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		for(Module module : modules) {
			module.postInit(event);
		}
	}
}
