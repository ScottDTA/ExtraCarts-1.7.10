package com.dta.extracarts.modcompat.mfr.client.interfaces;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class DSUOutSlot extends Slot {

	public DSUOutSlot(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
}
