package com.dta.extracarts.modcompat.ironchest.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCopperChestCart extends Container {
	private IInventory cart;
	private int ySize;
	private int xSize;
	
	public ContainerCopperChestCart (IInventory invPlayer, IInventory cart) {
		this.cart = cart;
		this.ySize = 184;//202
		this.xSize = 184;//184
		int leftOffset = (xSize - 162)/2 +1;
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, leftOffset + 18 * x, ySize-24));
		}
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, leftOffset + 18 * x, ySize - (4 - y) * 18 - 10));
			}
		}
		for (int y = 0; y < 5; y++){
			for (int x = 0; x < 9; x++){
				addSlotToContainer(new Slot(cart, x + y * 9, 12 + 18 * x, 8 + 18 * y));
			}
		}

		
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return cart.isUseableByPlayer(entityplayer);
		
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
            } else if (!mergeItemStack(stack, 36, 36 + cart.getSizeInventory(), false)) {
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
}
