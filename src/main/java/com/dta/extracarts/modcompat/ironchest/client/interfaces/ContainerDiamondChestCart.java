package com.dta.extracarts.modcompat.ironchest.client.interfaces;

import com.dta.extracarts.client.interfaces.ContainerExtraChestCart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDiamondChestCart extends ContainerExtraChestCart {
	public ContainerDiamondChestCart (IInventory invPlayer, IInventory cart) {
        super(invPlayer, cart, 238, 256, 9, 12);
	}
}
