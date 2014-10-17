package com.dta.extracarts.mods.ironchest.client;

import com.dta.extracarts.client.ContainerExtraChestCart;
import net.minecraft.inventory.IInventory;

public class ContainerGoldChestCart extends ContainerExtraChestCart {
	public ContainerGoldChestCart (IInventory invPlayer, IInventory cart) {
        super(invPlayer, cart, 184, 256, 9);
	}
}
