package com.dta.extracarts;

import com.dta.extracarts.mods.base.BaseModule;
import com.dta.extracarts.mods.ironchest.IronChestModule;
import com.dta.extracarts.mods.mfr.MFRModule;

import java.util.ArrayList;

public class ModInfo {
	
	public static final String NAME = "Extra Carts";
    public static final String MODID = "extracarts";
    public static final String VERSION = "0.7.3";

    private static ArrayList<Module> MODULES_ENABLED = new ArrayList<Module>();

    public static ArrayList<Module> getModules() {
        if(MODULES_ENABLED.isEmpty()) {
			MODULES_ENABLED.add(new BaseModule());
			MODULES_ENABLED.add(new IronChestModule());
			MODULES_ENABLED.add(new MFRModule());
		}
        return MODULES_ENABLED;
    }
}
