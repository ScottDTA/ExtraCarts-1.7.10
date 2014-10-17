package com.dta.extracarts.mods.extracarts.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;

public class EntityEnderChestCart extends EntityMinecartContainer {

	public EntityEnderChestCart(World world) {
		super(world);
	}
	
	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public int getMinecartType() {
		return 1;
	}
	
	@Override
	public Block func_145817_o() {
		return Blocks.ender_chest;
	}
	
	@Override
	public void killMinecart(DamageSource par1DamageSource) {
		super.killMinecart(par1DamageSource);
		this.func_145778_a(Item.getItemFromBlock(Blocks.ender_chest), 1, 0.0F);
    }
	
	@Override
	public boolean interactFirst(EntityPlayer player) {
		if(MinecraftForge.EVENT_BUS.post(new MinecartInteractEvent(this, player))) {
			return true;
	    }
		
		InventoryEnderChest inventoryenderchest = player.getInventoryEnderChest();
        
	    if (!this.worldObj.isRemote) {
	    	player.displayGUIChest(inventoryenderchest);
	    }
        return true;
    }


}
