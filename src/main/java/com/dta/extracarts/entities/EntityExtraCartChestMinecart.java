package com.dta.extracarts.entities;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.othermodcode.railcraft.common.carts.CartTransferBase;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import mods.railcraft.api.carts.IItemTransfer;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;

@Optional.InterfaceList({
		@Optional.Interface(iface = "mods.railcraft.api.carts.IMinecart", modid = "RailcraftAPI|carts"),
		@Optional.Interface(iface = "mods.railcraft.api.carts.IItemTransfer", modid = "RailcraftAPI|carts")
})
abstract public class EntityExtraCartChestMinecart extends CartTransferBase implements IInventory, IMinecart, IItemTransfer {

	protected ItemStack[] minecartContainerItems = new ItemStack[108];
    private boolean dropContentsWhenDead = true;

    public EntityExtraCartChestMinecart(World world) {
        super(world);
    }

    public EntityExtraCartChestMinecart(World world, double p_i1717_2_, double p_i1717_4_, double p_i1717_6_) {
        super(world, p_i1717_2_, p_i1717_4_, p_i1717_6_);
    }
    
    public void killMinecartNoDrop(DamageSource damageSource) {
    	super.killMinecart(damageSource);
    }

	public void killMinecart(DamageSource damageSource, ItemStack drop) {
		super.killMinecart(damageSource);
		float f = this.rand.nextFloat() * 0.8F + 0.1F;
		float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
		float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
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
    public ItemStack getStackInSlot(int p_70301_1_) {
        return this.getMinecartContainerItems()[p_70301_1_];
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        if (this.getMinecartContainerItems()[p_70298_1_] != null) {
            ItemStack itemstack;

            if (this.getMinecartContainerItems()[p_70298_1_].stackSize <= p_70298_2_) {
                itemstack = this.getMinecartContainerItems()[p_70298_1_];
                this.getMinecartContainerItems()[p_70298_1_] = null;
                return itemstack;
            } else {
                itemstack = this.getMinecartContainerItems()[p_70298_1_].splitStack(p_70298_2_);

                if (this.getMinecartContainerItems()[p_70298_1_].stackSize == 0) {
                    this.getMinecartContainerItems()[p_70298_1_] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    public abstract Block getCartBlock();

    @Override
    public Block func_145820_n() {
        return getCartBlock();
    }

    @Override
    public Block func_145817_o() {
        return getCartBlock();
    }


    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        if (this.getMinecartContainerItems()[p_70304_1_] != null) {
            ItemStack itemstack = this.getMinecartContainerItems()[p_70304_1_];
            this.getMinecartContainerItems()[p_70304_1_] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
        this.getMinecartContainerItems()[p_70299_1_] = p_70299_2_;

        if (p_70299_2_ != null && p_70299_2_.stackSize > this.getInventoryStackLimit()) {
            p_70299_2_.stackSize = this.getInventoryStackLimit();
        }
    }


    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return !this.isDead && player.getDistanceSqToEntity(this) <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.func_95999_t() : "container.minecart";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void travelToDimension(int p_71027_1_) {
        this.setDropContentsWhenDead(false);
        super.travelToDimension(p_71027_1_);
    }

    @Override
    public void setDead() {
        if (this.isDropContentsWhenDead()) {
            for (int i = 0; i < this.getSizeInventory(); ++i) {
                ItemStack itemstack = this.getStackInSlot(i);

                if (itemstack != null) {
                    float f = this.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0) {
                        int j = this.rand.nextInt(21) + 10;

                        if (j > itemstack.stackSize) {
                            j = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j;
                        EntityItem entityitem = new EntityItem(this.worldObj, this.posX + (double)f, this.posY + (double)f1, this.posZ + (double)f2, new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }

                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)this.rand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)this.rand.nextGaussian() * f3);
                        if (!this.worldObj.isRemote) {
                        	this.worldObj.spawnEntityInWorld(entityitem);
                        }
                    }
                }
            }
        }

        super.setDead();
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {
        super.writeEntityToNBT(p_70014_1_);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.getMinecartContainerItems().length; ++i) {
            if (this.getMinecartContainerItems()[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.getMinecartContainerItems()[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        p_70014_1_.setTag("Items", nbttaglist);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {
        super.readEntityFromNBT(p_70037_1_);
        NBTTagList nbttaglist = p_70037_1_.getTagList("Items", 10);
        this.setMinecartContainerItems(new ItemStack[this.getSizeInventory()]);

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.getMinecartContainerItems().length) {
                this.getMinecartContainerItems()[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public boolean interactFirst(EntityPlayer player) {
        if(net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new MinecartInteractEvent(this, player))) {
            return true;
        }

        if (!this.worldObj.isRemote) {
            FMLNetworkHandler.openGui(player, ExtraCarts.instance, 2, player.worldObj, this.getEntityId(), 0, 0);
        }

        return true;
    }

	@Override
	abstract public int getSizeInventory();

	@Override
    abstract public int getMinecartType();

	public ItemStack[] getMinecartContainerItems() {
		return minecartContainerItems;
	}

	public void setMinecartContainerItems(ItemStack[] minecartContainerItems) {
		this.minecartContainerItems = minecartContainerItems;
	}

	public boolean isDropContentsWhenDead() {
		return dropContentsWhenDead;
	}

	public void setDropContentsWhenDead(boolean dropContentsWhenDead) {
		this.dropContentsWhenDead = dropContentsWhenDead;
	}

	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		return false;
	}
}
