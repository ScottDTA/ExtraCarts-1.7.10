package com.dta.extracarts.client.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

/**
 * Created by Skylar on 10/8/2014.
 */
public abstract class ContainerExtraChestCart extends Container {
    private IInventory cart;
    private int ySize;
    private int xSize;

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return cart.isUseableByPlayer(entityplayer);

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
