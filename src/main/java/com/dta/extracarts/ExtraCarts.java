package com.dta.extracarts;

import net.minecraftforge.common.MinecraftForge;

import com.dta.extracarts.client.interfaces.GuiHandler;
import com.dta.extracarts.config.ConfigHandler;
import com.dta.extracarts.entities.Entities;
import com.dta.extracarts.events.ECEventHandler;
import com.dta.extracarts.modcompat.ironchest.IronChestItems;
import com.dta.extracarts.modcompat.ironchest.entities.IronChestEntities;
import com.dta.extracarts.modcompat.mfr.MFRItems;
import com.dta.extracarts.modcompat.mfr.entities.MFREntities;
import com.dta.extracarts.proxies.CommonProxy;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.MODID,
     name = ModInfo.NAME,
     version = ModInfo.VERSION,
     dependencies = "after:IronChest;"
     		      + "after:MineFactoryReloaded")
public class ExtraCarts {
	
	@SidedProxy(clientSide = "com.dta.extracarts.proxies.ClientProxy", serverSide = "com.dta.extracarts.proxies.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance(ModInfo.MODID)
	public static ExtraCarts instance;
	
	
	
	@EventHandler
	public void init(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		
		if (ModInfo.ENDER_CART_ENABLED) {
			ModItems.init();
			ModItems.registerItems();
		}
		
		
	
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		if (ModInfo.ENDER_CART_ENABLED) {
			ModItems.registerRecipes();
			Entities.init();
		}
	    new GuiHandler();
	    MinecraftForge.EVENT_BUS.register(new ECEventHandler());
	}
	    
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (Loader.isModLoaded("IronChest") && ModInfo.IRON_CHEST_ENABLED) {
			IronChestItems.init();
			IronChestItems.registerItems();
			IronChestItems.registerRecipes();
			IronChestEntities.init();
		}
		if (Loader.isModLoaded("MineFactoryReloaded") && ModInfo.DSU_ENABLED) {
			System.out.println("MFR is loaded");
			MFRItems.init();
			MFRItems.registerItems();
			MFRItems.registerRecipes();
			MFREntities.init();
		}
	}
}
