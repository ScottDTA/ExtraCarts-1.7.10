package com.dta.extracarts.mods.extracarts;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.ModInfo;
import com.dta.extracarts.ModItems;
import com.dta.extracarts.SubMod;
import com.dta.extracarts.mods.extracarts.entities.EntityEnderChestCart;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * Created by Skylar on 10/17/2014.
 */
public class ExtraCartsSubMod implements SubMod {
	@Override
	public void init(FMLPreInitializationEvent event) {
		if (ModInfo.ENDER_CART_ENABLED) {
			ModItems.init();
			ModItems.registerItems();
		}
	}

	@Override
	public void load(FMLInitializationEvent event) {
		if (ModInfo.ENDER_CART_ENABLED) {
			ModItems.registerRecipes();
			EntityRegistry.registerModEntity(EntityEnderChestCart.class, "EntityEnderChestCart", 0, ExtraCarts.instance, 80, 3, true);
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {

	}
}
