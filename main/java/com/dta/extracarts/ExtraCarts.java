package com.dta.extracarts;

import net.minecraftforge.common.MinecraftForge;

import com.dta.extracarts.client.interfaces.GuiHandler;
import com.dta.extracarts.entities.Entities;
import com.dta.extracarts.proxies.CommonProxy;
import com.dta.extracarts.events.ECEventHandler;

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
     dependencies = "after:IronChest")
public class ExtraCarts {
	
	@SidedProxy(clientSide = "com.dta.extracarts.proxies.ClientProxy", serverSide = "com.dta.extracrats.proxies.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance(ModInfo.MODID)
	public static ExtraCarts instance;
	
	
	
	@EventHandler
	public void init(FMLPreInitializationEvent event) {
		ModItems.init();
		ModItems.registerItems();
		
		
	
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		ModItems.registerRecipes();
	    Entities.init();
	    new GuiHandler();
	    MinecraftForge.EVENT_BUS.register(new ECEventHandler());
	}
	    
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	    	
	}
}
