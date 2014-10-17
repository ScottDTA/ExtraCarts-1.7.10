package com.dta.extracarts.mods.ironchest.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.entities.EntityExtraCartContainer;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class EntityCrystalChestCart extends EntityExtraCartContainer {
	
	private Block ironChest = Block.getBlockFromName("IronChest:BlockIronChest");
	
	public EntityCrystalChestCart(World world) {
		super(world);
		this.setDisplayTileData(5);
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
		super.killMinecart(par1DamageSource);
		float f = this.rand.nextFloat() * 0.8F + 0.1F;
        float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
        float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
		ItemStack drop = new ItemStack(ironChest, 1, 5);
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
	    if (!this.worldObj.isRemote) {
	    	FMLNetworkHandler.openGui(player, ExtraCarts.instance, 2, player.worldObj, this.getEntityId(), 0, 0);
	    }
        return true;
    }

}
