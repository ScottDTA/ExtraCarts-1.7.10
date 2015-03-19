package com.dta.extracarts.mods.ironchest.events;

import com.dta.extracarts.mods.ironchest.entities.EntityCopperChestCarts;
import com.dta.extracarts.mods.ironchest.entities.EntityIronChestCarts;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class ECEventHandler {

	private Item WoodIronUpgrade = GameRegistry.findItem("IronChest", "woodIronUpgrade");
	private Item WoodCopperUpgrade = GameRegistry.findItem("IronChest", "woodCopperUpgrade");
	
	@SubscribeEvent
	public void MinecartInteractEvent(MinecartInteractEvent event) {
		if (event.entity instanceof EntityMinecartChest) {
			ItemStack curItem = event.player.getCurrentEquippedItem();
	    	EntityMinecartChest oldCart = (EntityMinecartChest) event.entity;
	    	if (curItem != null && curItem.getItem() == WoodIronUpgrade) {
	    		EntityIronChestCarts ironCart = new EntityIronChestCarts(oldCart.worldObj);
	    		ironCart.copyDataFrom(event.entity, true);
	    		ironCart.setDisplayTileData(0);
	 	        if (!event.entity.worldObj.isRemote) {
	 	           	oldCart.worldObj.spawnEntityInWorld(ironCart);
	 	        }
	 	        for (int i = 0; i < oldCart.getSizeInventory(); i++) {
	 	        	oldCart.setInventorySlotContents(i, null);
	 	        }
	    		oldCart.setDead();
	    		event.player.destroyCurrentEquippedItem();
	    		event.setCanceled(true);
	    	} else if (curItem != null && curItem.getItem() == WoodCopperUpgrade) {	
	    		EntityCopperChestCarts copperCart = new EntityCopperChestCarts(oldCart.worldObj);
	    		copperCart.copyDataFrom(event.entity, true);
	    		copperCart.setDisplayTileData(3);
	 	        if (!event.entity.worldObj.isRemote) {
	 	        	oldCart.worldObj.spawnEntityInWorld(copperCart);
	 	        }
	 	        for (int i = 0; i < oldCart.getSizeInventory(); i++) {
	 	          	oldCart.setInventorySlotContents(i, null);
	 	        }
	    		oldCart.setDead();
	    		event.player.destroyCurrentEquippedItem();
	    		event.setCanceled(true);
	    	}
	    }
	}
}
