package com.dta.extracarts.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Skylar on 10/8/2014.
 */
public abstract class ContainerExtraChestCart extends Container {
    private IInventory cart;
    private int ySize;
    private int xSize;

    public ContainerExtraChestCart () {

    }

    public ContainerExtraChestCart (IInventory invPlayer, IInventory cart, int xSize, int ySize, int rows) {
        this(invPlayer, cart, xSize, ySize, rows, 9);
    }

    public ContainerExtraChestCart (IInventory invPlayer, IInventory cart, int xSize, int ySize, int rows, int columns) {
        this.cart = cart;
        this.ySize = ySize;//202
        this.xSize = xSize;//184
        int leftOffset = (xSize - 162)/2 +1;
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invPlayer, x, leftOffset + 18 * x, ySize-24));
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, leftOffset + 18 * x, ySize - (4 - y) * 18 - 10));
            }
        }
        for (int y = 0; y < rows; y++){
            for (int x = 0; x < columns; x++){
                addSlotToContainer(new Slot(cart, x + y * 9, 12 + 18 * x, 8 + 18 * y));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return cart.isUseableByPlayer(entityPlayer);
    }

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		Slot slot = (Slot) inventorySlots.get(i);

		if (slot != null && slot.getHasStack())
		{
			ItemStack stack = slot.getStack();
			ItemStack result = stack.copy();
			if (i >= 36) {
				if (!mergeItemStack(stack, 0, 36, false)) {
					return null;
				}
			} else if (!mergeItemStack(stack, 36, 36 + getCart().getSizeInventory(), false)) {
				return null;
			}
			if (stack.stackSize == 0) {
				slot.putStack(null);
			}
			else {
				slot.onSlotChanged();
			}

			slot.onPickupFromSlot(player, stack);
			return result;
		}
		return null;
	}

    public IInventory getCart() {
        return cart;
    }

    public void setCart(IInventory cart) {
        this.cart = cart;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }
}
