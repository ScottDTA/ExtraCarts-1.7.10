package com.dta.extracarts.mods.ironchest.entities;

import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.ironchest.client.ContainerSilverChestCart;
import com.dta.extracarts.mods.ironchest.client.GuiSilverChestCart;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.dta.extracarts.ExtraCarts;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class EntitySilverChestCart extends EntityExtraCartChestMinecart implements OpenableGUI {
	
	private Block ironChest = Block.getBlockFromName("IronChest:BlockIronChest");
	private Item SilverGoldUpgrade = GameRegistry.findItem("IronChest", "silverGoldUpgrade");
	
	public EntitySilverChestCart(World world) {
		super(world);
		this.setDisplayTileData(4);
	}
	
	@Override
	public int getSizeInventory() {
		return 72;
	}

	@Override
	public Block func_145817_o() {
		return ironChest;
	}
	
	@Override
	public boolean interactFirst(EntityPlayer player) {
		ItemStack curItem = player.getCurrentEquippedItem();
		if (curItem !=null && curItem.getItem() == SilverGoldUpgrade) {
			EntityGoldChestCart goldCart = new EntityGoldChestCart(player.worldObj);
			goldCart.copyDataFrom(this, true);
			goldCart.setDisplayTileData(2);
			for (int i = 0; i < this.getSizeInventory(); i++) {
				this.setInventorySlotContents(i, null);
			}
			this.setDead();
			if (!player.worldObj.isRemote) {
				player.worldObj.spawnEntityInWorld(goldCart);
			}
			player.destroyCurrentEquippedItem();
			return true;
		}
	    if (!this.worldObj.isRemote) {
	    	FMLNetworkHandler.openGui(player, ExtraCarts.instance, 4, player.worldObj, this.getEntityId(), 0, 0);
	    }
        return true;
    }

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiSilverChestCart(player.inventory, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerSilverChestCart(player.inventory, this);
	}
}
