package com.dta.extracarts.modcompat.ironchest.client;

import com.dta.extracarts.client.ContainerExtraChestCart;
import net.minecraft.inventory.IInventory;

public class ContainerSilverChestCart extends ContainerExtraChestCart {
	public ContainerSilverChestCart (IInventory invPlayer, IInventory cart) {
        super(invPlayer, cart, 184, 238, 8);
	}
}
