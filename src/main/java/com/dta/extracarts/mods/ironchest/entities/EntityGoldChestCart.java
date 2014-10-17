package com.dta.extracarts.mods.ironchest.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.entities.EntityExtraCartContainer;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class EntityGoldChestCart extends EntityExtraCartContainer {
	
	private Block ironChest = Block.getBlockFromName("IronChest:BlockIronChest");
	private Item GoldDiamondUpgrade = GameRegistry.findItem("IronChest", "goldDiamondUpgrade");
	
	public EntityGoldChestCart(World world) {
		super(world);
		this.setDisplayTileData(1);
	}
	
	@Override
	public int getSizeInventory() {
		return 81;
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
		float f = this.rand.nextFloat() * 0.8F + 0.1F;
        float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
        float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
		ItemStack drop = new ItemStack(ironChest, 1, 1);
		EntityItem entityitem = new EntityItem(this.worldObj, this.posX + (double)f, this.posY + (double)f1, this.posZ + (double)f2, drop);
		float f3 = 0.05F;
        entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
        entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
        entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);
		if (!this.worldObj.isRemote) {
			this.worldObj.spawnEntityInWorld(entityitem);
		}
    }
	
	@Override
	public boolean interactFirst(EntityPlayer player) {
		ItemStack curItem = player.getCurrentEquippedItem();
		if (curItem !=null && curItem.getItem() == GoldDiamondUpgrade) {
			EntityDiamondChestCart diamondCart = new EntityDiamondChestCart(player.worldObj);
			diamondCart.copyDataFrom(this, true);
			diamondCart.setDisplayTileData(2);
			for (int i = 0; i < this.getSizeInventory(); i++) {
				this.setInventorySlotContents(i, null);
			}
			this.setDead();
			if (!player.worldObj.isRemote) {
				player.worldObj.spawnEntityInWorld(diamondCart);
			}
			player.destroyCurrentEquippedItem();
			return true;
		}
		
	    if (!this.worldObj.isRemote) {
	    	FMLNetworkHandler.openGui(player, ExtraCarts.instance, 1, player.worldObj, this.getEntityId(), 0, 0);
	    }
        return true;
    }

}
