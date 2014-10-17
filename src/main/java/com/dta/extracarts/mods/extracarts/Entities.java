package com.dta.extracarts.mods.extracarts;

import com.dta.extracarts.ExtraCarts;

import com.dta.extracarts.mods.extracarts.entities.EntityEnderChestCart;
import cpw.mods.fml.common.registry.EntityRegistry;

public class Entities {

	public static void init() {
		EntityRegistry.registerModEntity(EntityEnderChestCart.class, "EntityEnderChestCart", 0, ExtraCarts.instance, 80, 3, true);
	}
}
