package com.dta.extracarts.entities;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.modcompat.ironchest.entities.IronChestEntities;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.EntityRegistry;

public class Entities {

	public static void init() {
		EntityRegistry.registerModEntity(EntityEnderChestCart.class, "EntityEnderChestCart", 0, ExtraCarts.instance, 80, 3, true);
		
		if (Loader.isModLoaded("IronChest")) {
			IronChestEntities.init();
		}
	}
}
