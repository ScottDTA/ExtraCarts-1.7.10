package com.dta.extracarts.config;

import java.io.File;

import com.dta.extracarts.Module;
import net.minecraftforge.common.config.Configuration;

import com.dta.extracarts.ModInfo;

public class ConfigHandler {
	public static void init(File file){
		Configuration config = new Configuration(file);
		config.load();
        for(Module module: ModInfo.getModules()) {
			module.setIsActive(config.get("Modules", module.getModuleName() + " Enabled", true).getBoolean(true));
        }
		config.save();
	}
}
