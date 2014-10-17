package com.dta.extracarts.modcompat.ironchest.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.entities.EntityExtraCartContainer;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class EntityIronChestCart extends EntityExtraCartContainer {
	
	private Block ironChest = Block.getBlockFromName("IronChest:BlockIronChest");
	private Item IronGoldUpgrade = GameRegistry.findItem("IronChest", "ironGoldUpgrade");
	
	
	public EntityIronChestCart(World world) {
		super(world);
		this.setDisplayTileData(0);
	}
	
	@Override
	public int getSizeInventory() {
		return 54;
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
		super.killMinecart(par1DamageSource);
		this.func_145778_a(Item.getItemFromBlock(ironChest), 1, 0.0F);
    }
	
	@Override
	public boolean interactFirst(EntityPlayer player) {
		ItemStack curItem = player.getCurrentEquippedItem();
		if (curItem !=null && curItem.getItem() == IronGoldUpgrade) {
			EntityGoldChestCart goldCart = new EntityGoldChestCart(player.worldObj);
			goldCart.copyDataFrom(this, true);
			goldCart.setDisplayTileData(1);
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
	    	FMLNetworkHandler.openGui(player, ExtraCarts.instance, 0, player.worldObj, this.getEntityId(), 0, 0);
	    }
        return true;
    }

}
