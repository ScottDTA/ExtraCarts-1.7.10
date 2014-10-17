package com.dta.extracarts;

import com.dta.extracarts.mods.extracarts.ExtraCartsSubMod;
import com.dta.extracarts.mods.ironchest.IronChestSubMod;
import com.dta.extracarts.mods.mfr.MFRSubMod;
import net.minecraftforge.common.MinecraftForge;

import com.dta.extracarts.client.GuiHandler;
import com.dta.extracarts.config.ConfigHandler;
import com.dta.extracarts.events.ECEventHandler;

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

	public static ArrayList<SubMod> subMods = new ArrayList<SubMod>();

	@EventHandler
	public void init(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());

		subMods.add(new ExtraCartsSubMod());
		subMods.add(new IronChestSubMod());
		subMods.add(new MFRSubMod());

		for(SubMod subMod: subMods) {
			subMod.init(event);
		}
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
	    new GuiHandler();
	    MinecraftForge.EVENT_BUS.register(new ECEventHandler());
		for(SubMod subMod: subMods) {
			subMod.load(event);
		}
	}
	    
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		for(SubMod subMod: subMods) {
			subMod.postInit(event);
		}
	}
}
