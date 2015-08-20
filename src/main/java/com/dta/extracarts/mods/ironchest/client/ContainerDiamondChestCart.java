package com.dta.extracarts.mods.ironchest.client;

import com.dta.extracarts.container.ContainerExtraChestCart;
import net.minecraft.inventory.IInventory;

public class ContainerDiamondChestCart extends ContainerExtraChestCart {
	public ContainerDiamondChestCart (IInventory invPlayer, IInventory cart) {
        super(invPlayer, cart, 238, 256, 9, 12);
	}
}
