package com.dta.extracarts.modcompat.ironchest.client.interfaces;

import com.dta.extracarts.client.interfaces.ContainerExtraChestCart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.dta.extracarts.modcompat.ironchest.entities.EntityIronChestCart;

public class ContainerIronChestCart extends ContainerExtraChestCart {
	public ContainerIronChestCart (IInventory invPlayer, IInventory cart) {
        super(invPlayer, cart, 184, 202, 6);
	}
}
