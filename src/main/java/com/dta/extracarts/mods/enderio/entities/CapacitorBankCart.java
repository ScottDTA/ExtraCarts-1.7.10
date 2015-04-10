package com.dta.extracarts.mods.enderio.entities;

import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import net.minecraft.world.World;

/**
 * Created by Skylar on 4/9/2015.
 */
public class CapacitorBankCart extends EntityExtraCartChestMinecart {
	public CapacitorBankCart(World world) {
		super(world);
	}

	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	public int getMinecartType() {
		return 1;
	}
}
