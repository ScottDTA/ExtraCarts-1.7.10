package com.dta.extracarts.mods.ironchest.entities;

import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.ironchest.client.ContainerCopperChestCart;
import com.dta.extracarts.mods.ironchest.client.GuiCopperChestCart;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.dta.extracarts.ExtraCarts;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class EntityCopperChestCart extends EntityExtraCartChestMinecart implements OpenableGUI {
	
	private Block ironChest = Block.getBlockFromName("IronChest:BlockIronChest");
	private Item CopperSilverUpgrade = GameRegistry.findItem("IronChest", "copperSilverUpgrade");
	private Item CopperIronUpgrade = GameRegistry.findItem("IronChest", "copperIronUpgrade");
	
	public EntityCopperChestCart(World world) {
		super(world);
		this.setDisplayTileData(3);
	}
	
	@Override
	public int getSizeInventory() {
		return 45;
	}
	
	@Override
	public Block func_145817_o() {
		return ironChest;
	}

	@Override
	public void killMinecart(DamageSource par1DamageSource) {
		super.killMinecart(par1DamageSource, new ItemStack(ironChest, 1, 3));
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		ItemStack curItem = player.getCurrentEquippedItem();
		if (curItem !=null && curItem.getItem() == CopperSilverUpgrade) {
			EntitySilverChestCart silverCart = new EntitySilverChestCart(player.worldObj);
			silverCart.copyDataFrom(this, true);
			silverCart.setDisplayTileData(4);
			for (int i = 0; i < this.getSizeInventory(); i++) {
				this.setInventorySlotContents(i, null);
			}
			this.setDead();
			if (!player.worldObj.isRemote) {
				player.worldObj.spawnEntityInWorld(silverCart);
			}
			player.destroyCurrentEquippedItem();
			return true;
		}
		if (curItem !=null && curItem.getItem() == CopperIronUpgrade) {
			EntityIronChestCart ironCart = new EntityIronChestCart(player.worldObj);
			ironCart.copyDataFrom(this, true);
			ironCart.setDisplayTileData(0);
			for (int i = 0; i < this.getSizeInventory(); i++) {
				this.setInventorySlotContents(i, null);
			}
			this.setDead();
			if (!player.worldObj.isRemote) {
				player.worldObj.spawnEntityInWorld(ironCart);
			}
			player.destroyCurrentEquippedItem();
			return true;
		}
	    if (!this.worldObj.isRemote) {
	    	FMLNetworkHandler.openGui(player, ExtraCarts.instance, 3, player.worldObj, this.getEntityId(), 0, 0);
	    }
        return true;
    }

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiCopperChestCart(player.inventory, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerCopperChestCart(player.inventory, this);
	}
}
