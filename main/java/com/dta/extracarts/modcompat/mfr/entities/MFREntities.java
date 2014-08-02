package com.dta.extracarts.modcompat.mfr.entities;

import com.dta.extracarts.ExtraCarts;

import cpw.mods.fml.common.registry.EntityRegistry;

public class MFREntities {
	public static void init() {
		EntityRegistry.registerModEntity(EntityDSUCart.class, "EntityDSUCart", 9, ExtraCarts.instance, 80, 3, true);
	}
}
