package com.dta.extracarts.mods.base.entities;

import com.dta.extracarts.mods.base.BaseModule;
import cpw.mods.fml.common.Optional;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityMinecartContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

@Optional.Interface(iface="mods.railcraft.api.carts.IMinecart", modid="RailcraftAPI|carts")
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
		InventoryEnderChest inventoryenderchest = player.getInventoryEnderChest();
        
	    if (!this.worldObj.isRemote && !player.isSneaking()) {
	    	player.displayGUIChest(inventoryenderchest);
	    }
        return true;
    }

	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		ItemStack CartStack = new ItemStack(BaseModule.EnderChestCart, 1, 0);
		if (cart instanceof EntityEnderChestCart && stack.getItem() == CartStack.getItem()) {
			return true;
		}
		return false;
	}

}
