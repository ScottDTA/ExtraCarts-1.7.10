package com.dta.extracarts;

import com.dta.extracarts.mods.base.BaseModule;
import com.dta.extracarts.mods.ironchest.IronChestModule;
import com.dta.extracarts.mods.mfr.MFRModule;

import java.util.ArrayList;

public class ModInfo {
	
	public static final String NAME = "Extra Carts";
    public static final String MODID = "extracarts";
    public static final String VERSION = "b0.6.1";
    
    public static boolean ENDER_CART_ENABLED = true;
	public static boolean IRON_CHEST_ENABLED = true;
	public static boolean DSU_ENABLED = true;

    private static ArrayList<Module> MODULES_ENABLED = null;

    public static ArrayList<Module> getModules() {
        if(MODULES_ENABLED == null) {
            MODULES_ENABLED = new ArrayList<Module>();
            MODULES_ENABLED.add(new BaseModule());
            MODULES_ENABLED.add(new IronChestModule());
            MODULES_ENABLED.add(new MFRModule());
        }
        return MODULES_ENABLED;
    }
}
