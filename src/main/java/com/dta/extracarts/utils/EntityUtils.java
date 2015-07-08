package com.dta.extracarts.utils;

import com.dta.extracarts.ExtraCarts;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * Created by Skylar on 4/12/2015.
 */
public class EntityUtils {

	private static int entityID = 15;

	public static void registerEntity(Class entityClass, String name) {
		int entityID = getNextAvailableEntityID();
		EntityRegistry.registerModEntity(entityClass, name, entityID, ExtraCarts.instance, 64, 1, true);
	}

	private static int getNextAvailableEntityID() {
		return entityID++;
	}
}
