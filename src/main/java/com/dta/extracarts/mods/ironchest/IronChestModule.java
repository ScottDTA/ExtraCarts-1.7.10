package com.dta.extracarts.mods.ironchest;

import com.dta.extracarts.ModInfo;
import com.dta.extracarts.Module;
import com.dta.extracarts.mods.ironchest.events.ECEventHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Skylar on 10/17/2014.
 */
public class IronChestModule implements Module {
    @Override
    public String getModuleName() {
        return "IronChest";
    }

    @Override
	public void init(FMLPreInitializationEvent event) {

	}

	@Override
	public void load(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ECEventHandler());
    }

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		if (Loader.isModLoaded("IronChest") && ModInfo.IRON_CHEST_ENABLED) {
			IronChestItems.init();
			IronChestItems.registerItems();
			IronChestItems.registerRecipes();
			IronChestEntities.init();
		}
	}
}
