package com.dta.extracarts.mods.mfr.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.dta.extracarts.mods.mfr.entities.EntityDSUCarts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerDSUCart extends Container {

	private EntityDSUCarts cart;
	private int tempQty;
	private int ySize;
	private int xSize;
	
	public ContainerDSUCart (IInventory invPlayer, EntityDSUCarts cart) {
		this.cart = cart;
		this.ySize=205;
		this.xSize=184;
		int leftOffset = (xSize - 162)/2 - 3;
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(invPlayer, x, leftOffset + 18 * x, ySize-23));
		}
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(invPlayer, x + y * 9 + 9, leftOffset + 18 * x, ySize - (4 - y) * 18 - 9));
			}
		}
		addSlotToContainer(new DSUInSlot(cart, 0, 134, 16));
		addSlotToContainer(new DSUInSlot(cart, 1, 152, 16));
		addSlotToContainer(new DSUOutSlot(cart, 2, 152, 49));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return cart.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		//return null;
		
        Slot slot = (Slot) inventorySlots.get(i);
        
        if (slot != null && slot.getHasStack())
        {
            ItemStack stack = slot.getStack();
            ItemStack result = stack.copy();
            if (i >= 36) {
         //       if (!mergeItemStack(stack, 0, 36, false)) {
                    return null;
           //     }
            } else if (!mergeItemStack(stack, 37, 38, false)) {
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
    public void addCraftingToCrafters(ICrafting player) {
    	super.addCraftingToCrafters(player);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data){
    	if(id == 200) tempQty = upcastShort(data);
		if(id == 201) cart.setQuantity(tempQty | (data << 16));

    }
    
    @Override
    public void detectAndSendChanges() {
    	super.detectAndSendChanges();
    	
    	for(int i = 0; i < crafters.size(); i++) {
			((ICrafting)crafters.get(i)).sendProgressBarUpdate(this, 200, cart.getQuantity());
			((ICrafting)crafters.get(i)).sendProgressBarUpdate(this, 201, cart.getQuantity() >> 16);
		}

    	
    	for (int i = 39; i --> 36; )
		{
			Slot slotObject = (Slot)inventorySlots.get(i);
			if (slotObject != null)
				for (int j = 0; j < this.crafters.size(); ++j)
					((ICrafting)this.crafters.get(j)).sendSlotContents(this, slotObject.slotNumber, slotObject.getStack());
		}

    }
    
    private int upcastShort(int value) {
		if(value < 0) value += 65536;
		return value;
	}

}
