package com.dta.extracarts.mods.mfr;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import com.dta.extracarts.ModInfo;
import net.minecraft.item.ItemStack;

/**
 * Created by Skylar on 3/24/2015.
 */
public class NEIExtraCartsMFRConfig implements IConfigureNEI {
	@Override
	public void loadConfig() {
		API.hideItem(new ItemStack(MFRModule.fakeDSUBlock));
	}

	@Override
	public String getName() {
		return "Extra Carts MFR NEI Plugin";
	}

	@Override
	public String getVersion() {
		return ModInfo.VERSION;
	}
}
