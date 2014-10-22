package com.dta.extracarts.mods.ironchest.client;

import com.dta.extracarts.client.ContainerExtraChestCart;
import net.minecraft.inventory.IInventory;

public class ContainerIronChestCart extends ContainerExtraChestCart {
	public ContainerIronChestCart (IInventory invPlayer, IInventory cart) {
        super(invPlayer, cart, 184, 202, 6);
	}
}
