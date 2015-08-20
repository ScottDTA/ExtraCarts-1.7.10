package com.dta.extracarts.mods.ironchest.client;

import com.dta.extracarts.container.ContainerExtraChestCart;
import net.minecraft.inventory.IInventory;

public class ContainerCopperChestCart extends ContainerExtraChestCart {
	public ContainerCopperChestCart (IInventory invPlayer, IInventory cart) {
        super(invPlayer, cart, 184, 184, 5);
	}
}
