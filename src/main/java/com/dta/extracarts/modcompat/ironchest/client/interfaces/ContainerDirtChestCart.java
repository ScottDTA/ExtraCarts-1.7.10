package com.dta.extracarts.modcompat.ironchest.client.interfaces;

import com.dta.extracarts.client.interfaces.ContainerExtraChestCart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerDirtChestCart extends ContainerExtraChestCart {
	public ContainerDirtChestCart (IInventory invPlayer, IInventory cart) {
		setCart(cart);
		setySize(184);//202
		setxSize(184);//184
		int leftOffset = (getxSize() - 162)/2 +1;
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, leftOffset + 18 * x, getySize()-24));
		}
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, leftOffset + 18 * x, getySize() - (4 - y) * 18 - 10));
			}
		}
		addSlotToContainer(new SlotDirt(cart, 0, 12 + 4 * 18, 8 + 2 * 18));
	}
	//If not overriden, crashes. Only dirtcart does this. No idea why. Just leave alone.
	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer) {
		return getCart().isUseableByPlayer(entityPlayer);
	}
}
