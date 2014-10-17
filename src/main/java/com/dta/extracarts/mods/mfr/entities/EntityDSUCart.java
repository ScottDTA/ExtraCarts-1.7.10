package com.dta.extracarts.mods.mfr.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.entities.EntityExtraCartContainer;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class EntityDSUCart extends EntityExtraCartContainer {
	
	private ItemStack storedItem = null;
	private int storedQty = 0;
	private NBTTagCompound compound = null;
	private Block dsu = GameRegistry.findBlock("MineFactoryReloaded", "tile.mfr.machine.1");
	
	
	public EntityDSUCart(World world) {
		super(world);
		this.setDisplayTileData(3);
	}

	@Override
	public Block func_145817_o() {
		return dsu;
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
	    if (!this.worldObj.isRemote) {
	    	System.out.println("Open Says Me!");
	    	FMLNetworkHandler.openGui(player, ExtraCarts.instance, 6, player.worldObj, this.getEntityId(), 0, 0);
	    }
        return true;
    }
	
	@Override
	public void killMinecart(DamageSource par1DamageSource) {
		super.killMinecartNoDrop(par1DamageSource);
		float f = this.rand.nextFloat() * 0.8F + 0.1F;
        float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
        float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
		ItemStack dropCart = new ItemStack(dsu, 1, 3);
		compound = new NBTTagCompound();
		if (storedItem != null)	{
			compound.setTag("storedStack", storedItem.writeToNBT(new NBTTagCompound()));
			compound.setInteger("storedQuantity", storedQty + this.minecartContainerItems[2].stackSize);
		} else {
			compound.setInteger("storedQuantity", 0);
		}
		dropCart.setTagCompound(compound);
		EntityItem entityitem = new EntityItem(this.worldObj, this.posX + (double)f, this.posY + (double)f1, this.posZ + (double)f2, dropCart);
        float f3 = 0.05F;
        entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
        entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
        entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);
        this.worldObj.spawnEntityInWorld(entityitem);
    }

	@Override
	public int getSizeInventory() {
		return 3;
	}
	
	@Override
	public int getMinecartType() {
		return 1;
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if (i > 1) {
			return false;
		}
		ItemStack stack = itemstack.copy();
		stack.stackSize = 1;
		if (storedItem == null || ItemStack.areItemStacksEqual(storedItem, stack)) {
			return true;
		}
		return false;
	}
	
	public int getQuantity() {
		return this.storedQty;
	}
	
	public void setQuantity(int amt) {
		this.storedQty =  amt;
	}
	
	public void setItem(ItemStack stack) {
		stack.stackSize = 1;
		this.storedItem = stack;
	}
	
	@Override
    public void onInventoryChanged() {
		if (worldObj.isRemote) {
			return;
		}
		
		if ((this.minecartContainerItems[2] == null) & storedItem != null & storedQty == 0) {
			storedItem = null;
		}
		
		if (this.minecartContainerItems[0] != null) {
			ItemStack stack = this.minecartContainerItems[0].copy();
			stack.stackSize = 1;
			if (storedQty == 0 && (storedItem == null || ItemStack.areItemStacksEqual(storedItem, stack))) {
				storedItem = this.minecartContainerItems[0].copy();
				storedItem.stackSize = 1;
				storedQty = this.minecartContainerItems[0].stackSize;
				this.minecartContainerItems[0] = null;
			} else if (ItemStack.areItemStacksEqual(storedItem, stack)) {
				storedQty += this.minecartContainerItems[0].stackSize;
				this.minecartContainerItems[0] = null;
			}
		}
		
		if (this.minecartContainerItems[1] != null) {
			ItemStack stack = this.minecartContainerItems[1].copy();
			stack.stackSize = 1;
			if (storedQty == 0 && (storedItem == null || ItemStack.areItemStacksEqual(storedItem, stack))) {
				storedItem = this.minecartContainerItems[1].copy();
				storedItem.stackSize = 1;
				storedQty = this.minecartContainerItems[1].stackSize;
				this.minecartContainerItems[1] = null;
			} else if (ItemStack.areItemStacksEqual(storedItem, stack)) {
				storedQty += this.minecartContainerItems[1].stackSize;
				this.minecartContainerItems[1] = null;
			}
		}
		
		
		if (this.minecartContainerItems[2] == null && storedItem != null) {
			this.minecartContainerItems[2] = storedItem.copy();
			this.minecartContainerItems[2].stackSize = Math.min(storedQty, Math.min(storedItem.getMaxStackSize(), this.getInventoryStackLimit()));
			storedQty -= this.minecartContainerItems[2].stackSize;
		} else if (this.minecartContainerItems[2] != null) {
			ItemStack stack = this.minecartContainerItems[2].copy();
			stack.stackSize = 1;
			if (storedQty > 0 && this.minecartContainerItems[2].stackSize < this.minecartContainerItems[2].getMaxStackSize() && ItemStack.areItemStacksEqual(storedItem, stack)) {
				int amt = Math.min(this.minecartContainerItems[2].getMaxStackSize() - this.minecartContainerItems[2].stackSize, storedQty);
				this.minecartContainerItems[2].stackSize += amt;
				storedQty -= amt;
			}
		}
		
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(worldObj.isRemote) {
			return;
		}
		this.onInventoryChanged();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		int storedAdd = 0;
		ItemStack o = this.minecartContainerItems[2];
		if (o != null) {
			storedAdd = o.stackSize;
			this.minecartContainerItems[2] = null;
		}
		super.writeToNBT(compound);
		this.minecartContainerItems[2] = o;
		if (storedItem != null)	{
			compound.setTag("storedStack", storedItem.writeToNBT(new NBTTagCompound()));
			compound.setInteger("storedQuantity", storedQty + storedAdd);
		}
		else {
			compound.setInteger("storedQuantity", 0);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		storedQty = compound.getInteger("storedQuantity");
		storedItem = null;
		if (compound.hasKey("storedStack")) {
			storedItem = ItemStack.loadItemStackFromNBT((NBTTagCompound)compound.getTag("storedStack"));
		}
		if (storedItem == null & storedQty > 0)	{
			storedQty = 0;
		}
	}

	public ItemStack getStoredItem() {
		return this.storedItem;
	}
	
	@Override
	public int getInventoryStackLimit() {
		if (this.minecartContainerItems[2] == null) {
			return 64;
		}
	    return Math.min(64, (Integer.MAX_VALUE - (storedQty + this.minecartContainerItems[2].stackSize)));
	}

}
