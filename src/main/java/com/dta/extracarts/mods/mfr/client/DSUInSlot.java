package com.dta.extracarts.mods.mfr.client;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.dta.extracarts.mods.mfr.entities.EntityDSUCart;

public class DSUInSlot extends Slot {
	
	private EntityDSUCart cart = null;
	
	public DSUInSlot(EntityDSUCart cart, int par2, int par3, int par4) {
		super(cart, par2, par3, par4);
		this.cart = cart;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		ItemStack copy = stack.copy();
		copy.stackSize = 1;
		int storedQty = 0;
		if (cart.getMinecartContainerItems()[2] != null) {
			ItemStack slot2 = cart.getMinecartContainerItems()[2].copy();
			storedQty += slot2.stackSize;
			storedQty += cart.getQuantity();
			slot2.stackSize = 1;
			if (!ItemStack.areItemStacksEqual(slot2, copy)) {
				return false;
			}
		}
		if (storedQty >= Integer.MAX_VALUE) {
			return false;
		}
		return true;
	}
	
	@Override
	public int getSlotStackLimit() {
		int storedQty = 0;
		if (cart.getMinecartContainerItems()[2] != null) {
			storedQty += cart.getMinecartContainerItems()[2].stackSize;
			storedQty += cart.getQuantity();
		}  
	    return Math.min(cart.getInventoryStackLimit(), (Integer.MAX_VALUE - storedQty));
	}
}
