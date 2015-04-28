package com.dta.extracarts;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import com.dta.extracarts.block.FakeBlock;
import com.dta.extracarts.block.FakeBlockRegistry;
import net.minecraft.item.ItemStack;

/**
 * Created by Skylar on 3/24/2015.
 */
public class NEIExtraCartsConfig implements IConfigureNEI {
	@Override
	public void loadConfig() {
		for(FakeBlock fakeBlock: FakeBlockRegistry.getFakeBlockArrayList()) {
			API.hideItem(new ItemStack(fakeBlock));
		}
	}

	@Override
	public String getName() {
		return "Extra Carts NEI Plugin";
	}

	@Override
	public String getVersion() {
		return ModInfo.VERSION;
	}
}
