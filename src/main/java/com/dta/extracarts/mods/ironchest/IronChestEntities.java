package com.dta.extracarts.mods.ironchest;

import com.dta.extracarts.ExtraCarts;

import com.dta.extracarts.mods.ironchest.entities.*;
import cpw.mods.fml.common.registry.EntityRegistry;

public class IronChestEntities {
	public static void init() {
		EntityRegistry.registerModEntity(EntityIronChestCarts.class, "EntityIronChestCarts", 1, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityGoldChestCarts.class, "EntityGoldChestCarts", 2, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityDiamondChestCarts.class, "EntityDiamondChestCarts", 3, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityCopperChestCarts.class, "EntityCopperChestCarts", 4, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntitySilverChestCarts.class, "EntitySilverChestCarts", 5, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityCrystalChestCarts.class, "EntityCrystalChestCarts", 6, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityObsidianChestCarts.class, "EntityObsidianChestCarts", 7, ExtraCarts.instance, 80, 3, true);
		EntityRegistry.registerModEntity(EntityDirtChestCarts.class, "EntityDirtChestCarts", 8, ExtraCarts.instance, 80, 3, true);
	}
}
