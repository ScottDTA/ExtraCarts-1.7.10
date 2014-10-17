package com.dta.extracarts.mods.ironchest;

import com.dta.extracarts.ExtraCarts;

import com.dta.extracarts.mods.ironchest.entities.*;
import cpw.mods.fml.common.registry.EntityRegistry;

public class IronChestEntities {
	public static void init() {
		EntityRegistry.registerModEntity(EntityIronChestCart.class, "EntityIronChestCart", 1, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityGoldChestCart.class, "EntityGoldChestCart", 2, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityDiamondChestCart.class, "EntityDiamondChestCart", 3, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityCopperChestCart.class, "EntityCopperChestCart", 4, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntitySilverChestCart.class, "EntitySilverChestCart", 5, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityCrystalChestCart.class, "EntityCrystalChestCart", 6, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityObsidianChestCart.class, "EntityObsidianChestCart", 7, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityDirtChestCart.class, "EntityDirtChestCart", 8, ExtraCarts.instance, 80, 3, true);
	}
}
