package com.dta.extracarts.utils;

import com.dta.extracarts.ExtraCarts;
import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * Created by Skylar on 4/12/2015.
 */
public class EntityUtils {
	public static void registerEntity(Class entityClass, String name) {
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.registerModEntity(entityClass, name, entityID, ExtraCarts.instance, 64, 1, true);
	}
}
