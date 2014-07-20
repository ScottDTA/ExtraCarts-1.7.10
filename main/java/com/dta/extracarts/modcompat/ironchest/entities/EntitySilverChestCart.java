package com.dta.extracarts.modcompat.ironchest.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.entities.EntityExtraCartContainer;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class EntitySilverChestCart extends EntityExtraCartContainer {
	
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
		ItemStack drop = new ItemStack(ironChest, 1, 4);
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

}
