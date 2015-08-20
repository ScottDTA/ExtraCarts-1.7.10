package com.dta.extracarts.container;

import invtweaks.api.container.ChestContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;



/**
 * Created by Skylar on 10/8/2014.
 */
@ChestContainer(isLargeChest = true)
public abstract class ContainerExtraChestCart extends Container {
    private IInventory cart;
    private int ySize;
    private int xSize;
    private int columns;

    public ContainerExtraChestCart () {

    }

    public ContainerExtraChestCart (IInventory invPlayer, IInventory cart, int xSize, int ySize, int rows) {
        this(invPlayer, cart, xSize, ySize, rows, 9);
    }

    public ContainerExtraChestCart (IInventory invPlayer, IInventory cart, int xSize, int ySize, int rows, int columns) {
        this.cart = cart;
        this.ySize = ySize;//202
        this.xSize = xSize;//184
        this.columns = columns;
        int leftOffset = (xSize - 162)/2 +1;
        for (int y = 0; y < rows; y++){
            for (int x = 0; x < columns; x++){
                addSlotToContainer(new Slot(cart, x + y * columns, 12 + 18 * x, 8 + 18 * y));
            }
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, leftOffset + 18 * x, ySize - (4 - y) * 18 - 10));
            }
        }
        for (int x = 0; x < 9; x++) {
            addSlotToContainer(new Slot(invPlayer, x, leftOffset + 18 * x, ySize-24));
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
			if (i < getCart().getSizeInventory()) {
				if (!mergeItemStack(stack, getCart().getSizeInventory(), 36 + getCart().getSizeInventory(), false)) {
					return null;
				}
			} else if (!mergeItemStack(stack, 0, getCart().getSizeInventory(), false)) {
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

	@Override
	public ItemStack slotClick(int slotNumber, int mouseButton, int modifier, EntityPlayer player) {
		Slot slot;
		if(slotNumber < 0) {
			slot = null;
		} else {
			slot = (Slot) this.inventorySlots.get(slotNumber);
		}
		if(slot instanceof IGhostSlot) {
			return slotClickPhantom(slot, mouseButton, modifier, player);
		}
		return super.slotClick(slotNumber, mouseButton, modifier, player);
	}

	private ItemStack slotClickPhantom(Slot slot, int mouseButton, int modifier, EntityPlayer entityPlayer) {
		ItemStack stack = null;

		if(mouseButton == 2) {
			if(((IGhostSlot) slot).canChange()) {
				slot.putStack(null);
			}
		} else if(mouseButton == 0 || mouseButton == 1) {
			InventoryPlayer playerInventory = entityPlayer.inventory;
			slot.onSlotChanged();
			ItemStack itemStackSlot = slot.getStack();
			ItemStack stackHeld = playerInventory.getItemStack();

			if(itemStackSlot != null) {
				stack = itemStackSlot.copy();
			}

			if(itemStackSlot == null) {
				if (stackHeld != null && slot.isItemValid(stackHeld)) {
					fillPhantomSlot(slot, stackHeld, mouseButton, modifier);
				}
			} else if(stackHeld == null) {
				adjustPhantomSlot(slot, mouseButton, modifier);
				slot.onPickupFromSlot(entityPlayer, playerInventory.getItemStack());
			} else if(slot.isItemValid(stackHeld)) {
				if(canStacksMerge(itemStackSlot, stackHeld)) {
					adjustPhantomSlot(slot, mouseButton, modifier);
				} else {
					fillPhantomSlot(slot, stackHeld, mouseButton, modifier);
				}
			}
		}
		return stack;
	}

	protected void adjustPhantomSlot(Slot slot, int mouseButton, int modifier) {
		if(!((IGhostSlot) slot).canChange()) {
			return;
		}
		ItemStack stackSlot = slot.getStack();
		int stackSize;
		if(modifier == 1) {
			stackSize = mouseButton == 0 ? (stackSlot.stackSize + 1) / 2 : stackSlot.stackSize * 2;
		} else {
			stackSize = mouseButton == 0 ? stackSlot.stackSize - 1 : stackSlot.stackSize + 1;
		}

		if(stackSize > slot.getSlotStackLimit()) {
			stackSize = slot.getSlotStackLimit();
		}

		stackSlot.stackSize = stackSize;

		if(stackSlot.stackSize <= 0) {
			slot.putStack(null);
		}
	}

	protected void fillPhantomSlot(Slot slot, ItemStack itemStack, int mouseButton, int modifier) {
		if(!((IGhostSlot) slot).canChange()) {
			return;
		}
		int stackSize;
		if(mouseButton == 0) {
			stackSize = itemStack.stackSize;
		} else {
			stackSize = 1;
		}
		if(stackSize > slot.getSlotStackLimit()) {
			stackSize = slot.getSlotStackLimit();
		}
		ItemStack ghostItemstack = itemStack.copy();
		ghostItemstack.stackSize = stackSize;

		slot.putStack(ghostItemstack);
	}

	public static boolean canStacksMerge(ItemStack itemStack1, ItemStack itemStack2) {
		if(itemStack1 == null || itemStack2 == null) {
			return false;
		}
		if (!itemStack1.isItemEqual(itemStack2)) {
			return false;
		}
		if (!ItemStack.areItemStackTagsEqual(itemStack1, itemStack2)) {
			return false;
		}
		return true;
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
    
    @ChestContainer.RowSizeCallback 
    public int getNumColumns() { 
    	return this.columns;  
    } 

}
