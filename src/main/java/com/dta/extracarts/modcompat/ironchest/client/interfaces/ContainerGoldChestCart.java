package com.dta.extracarts.modcompat.ironchest.client.interfaces;

import com.dta.extracarts.client.interfaces.ContainerExtraChestCart;
import net.minecraft.inventory.IInventory;

public class ContainerGoldChestCart extends ContainerExtraChestCart {
	public ContainerGoldChestCart (IInventory invPlayer, IInventory cart) {
        super(invPlayer, cart, 184, 256, 9);
	}
}
