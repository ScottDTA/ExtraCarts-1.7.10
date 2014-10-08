package com.dta.extracarts.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import com.dta.extracarts.ModInfo;

public class ConfigHandler {
	public static void init(File file){
		Configuration config = new Configuration(file);
		config.load();
		ModInfo.ENDER_CART_ENABLED = config.get("Mod Support", "Ender Chest Cart Enabled", true).getBoolean(true);
		ModInfo.IRON_CHEST_ENABLED = config.get("Mod Support", "Iron Chest Mod Support Enabled", true).getBoolean(true);
		ModInfo.DSU_ENABLED = config.get("Mod Support",  "MineFactory Reloaded Support Enabled", true).getBoolean(true);
		config.save();
	}
}
