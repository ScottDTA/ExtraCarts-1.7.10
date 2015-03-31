package com.dta.extracarts;

import com.dta.extracarts.block.FakeBlockRegistry;
import com.dta.extracarts.client.GuiHandler;
import com.dta.extracarts.config.ConfigHandler;
import com.dta.extracarts.proxy.CommonProxy;
import com.dta.extracarts.utils.LogUtils;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class ExtraCarts {
	@Instance(ModInfo.MODID)
	public static ExtraCarts instance;

	@SidedProxy(clientSide = "com.dta.extracarts.proxy.ClientProxy", serverSide = "com.dta.extracarts.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void init(FMLPreInitializationEvent event) {
		ConfigHandler.setConfigFile(event.getSuggestedConfigurationFile());
        ConfigHandler.init();

		for(Module module : ModInfo.getModules()) {
			if(!module.areRequirementsMet() && module.getIsActive()) {
				module.setIsActive(false);
				LogUtils.log(Level.ERROR, "Requirements are not met for " + module.getModuleName() + ". Deactivating");
			}
			if(module.getIsActive()) {
				LogUtils.log(Level.INFO, "Loading " + module.getModuleName() + " module");
			}
		}

        for(Module module : ModInfo.getModules()) {
			if(module.getIsActive())
				module.init(event);
		}
		FakeBlockRegistry.registerBlocks();
		proxy.init(event);
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
	    new GuiHandler();
		for(Module module : ModInfo.getModules()) {
			if(module.getIsActive()) module.load(event);
		}
	}
	    
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		for(Module module : ModInfo.getModules()) {
			if(module.getIsActive()) module.postInit(event);
		}
	}
}
