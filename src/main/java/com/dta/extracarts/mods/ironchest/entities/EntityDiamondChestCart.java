package com.dta.extracarts.mods.ironchest.entities;

import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.ironchest.client.ContainerDiamondChestCart;
import com.dta.extracarts.mods.ironchest.client.GuiDiamondChestCart;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.dta.extracarts.ExtraCarts;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class EntityDiamondChestCart extends EntityExtraCartChestMinecart implements OpenableGUI{
	
	private Block ironChest = Block.getBlockFromName("IronChest:BlockIronChest");
	private Item DiamondCrystalUpgrade = GameRegistry.findItem("IronChest", "diamondCrystalUpgrade");
	private Item DiamondObsidianUpgrade = GameRegistry.findItem("IronChest", "diamondObsidianUpgrade");
	
	public EntityDiamondChestCart(World world) {
		super(world);
		this.setDisplayTileData(2);
	}
	
	@Override
	public int getSizeInventory() {
		return 108;
	}

	@Override
	public int getMinecartType() {
		return 1;
	}
	
	@Override
	public Block func_145817_o() {
		return ironChest;
	}

	@Override
	public void killMinecart(DamageSource par1DamageSource) {
		super.killMinecart(par1DamageSource, new ItemStack(ironChest, 1, 2));
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		ItemStack curItem = player.getCurrentEquippedItem();
		if (curItem !=null && curItem.getItem() == DiamondCrystalUpgrade) {
			EntityCrystalChestCart crystalCart = new EntityCrystalChestCart(player.worldObj);
			crystalCart.copyDataFrom(this, true);
			crystalCart.setDisplayTileData(5);
			for (int i = 0; i < this.getSizeInventory(); i++) {
				this.setInventorySlotContents(i, null);
			}
			this.setDead();
			if (!player.worldObj.isRemote) {
				player.worldObj.spawnEntityInWorld(crystalCart);
			}
			player.destroyCurrentEquippedItem();
			return true;
		}
		if (curItem !=null && curItem.getItem() == DiamondObsidianUpgrade) {
			EntityObsidianChestCart obsidianCart = new EntityObsidianChestCart(player.worldObj);
			obsidianCart.copyDataFrom(this, true);
			obsidianCart.setDisplayTileData(6);
			for (int i = 0; i < this.getSizeInventory(); i++) {
				this.setInventorySlotContents(i, null);
			}
			this.setDead();
			if (!player.worldObj.isRemote) {
				player.worldObj.spawnEntityInWorld(obsidianCart);
			}
			player.destroyCurrentEquippedItem();
			return true;
		}
	    if (!this.worldObj.isRemote) {
	    	FMLNetworkHandler.openGui(player, ExtraCarts.instance, 2, player.worldObj, this.getEntityId(), 0, 0);
	    }
        return true;
    }

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiDiamondChestCart(player.inventory, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerDiamondChestCart(player.inventory, this);
	}

}
