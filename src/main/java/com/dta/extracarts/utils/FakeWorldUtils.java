package com.dta.extracarts.utils;

import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

/**
 * Created by Skylar on 2/5/2015.
 */
public class FakeWorldUtils {
	public static WorldSettings createWorldSettings(World world) {
		WorldSettings worldSettings = new WorldSettings(world.getSeed(), world.getWorldInfo().getGameType(),
				world.getWorldInfo().isMapFeaturesEnabled(), world.getWorldInfo().isHardcoreModeEnabled(),
				world.getWorldInfo().getTerrainType());
		return worldSettings;
	}
}
