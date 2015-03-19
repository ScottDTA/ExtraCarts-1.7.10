package com.dta.extracarts.entities;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.client.IUpgradeable;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import mods.railcraft.api.carts.CartTools;
import mods.railcraft.api.carts.IItemTransfer;
import mods.railcraft.api.carts.ILinkageManager;
import mods.railcraft.api.carts.IMinecart;
import mods.railcraft.api.core.items.IStackFilter;
import mods.railcraft.common.util.inventory.filters.ArrayStackFilter;
import mods.railcraft.common.util.inventory.filters.StackFilter;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

@Optional.InterfaceList({
		@Optional.Interface(iface = "mods.railcraft.api.carts.IMinecart", modid = "RailcraftAPI|carts"),
		@Optional.Interface(iface = "mods.railcraft.api.carts.IItemTransfer", modid = "RailcraftAPI|carts")
})
abstract public class EntityExtraCartsChestMinecart extends EntityMinecart implements IInventory, IMinecart {

	private TileEntity tileEntity;
	private ItemStack[] minecartContainerItems = new ItemStack[108];
	private Block cartBlock;

    private boolean dropContentsWhenDead = true;

    public EntityExtraCartsChestMinecart(World world) {
        super(world);
    }

    public EntityExtraCartsChestMinecart(World p_i1717_1_, double p_i1717_2_, double p_i1717_4_, double p_i1717_6_) {
        super(p_i1717_1_, p_i1717_2_, p_i1717_4_, p_i1717_6_);
    }

	@Override
	public Block func_145817_o() {
		return getCartBlock();
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
    public ItemStack getStackInSlot(int p_70301_1_)
    {
        return this.getMinecartContainerItems()[p_70301_1_];
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_)
    {
        if (this.getMinecartContainerItems()[p_70298_1_] != null)
        {
            ItemStack itemstack;

            if (this.getMinecartContainerItems()[p_70298_1_].stackSize <= p_70298_2_)
            {
                itemstack = this.getMinecartContainerItems()[p_70298_1_];
                this.getMinecartContainerItems()[p_70298_1_] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.getMinecartContainerItems()[p_70298_1_].splitStack(p_70298_2_);

                if (this.getMinecartContainerItems()[p_70298_1_].stackSize == 0)
                {
                    this.getMinecartContainerItems()[p_70298_1_] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_)
    {
        if (this.getMinecartContainerItems()[p_70304_1_] != null)
        {
            ItemStack itemstack = this.getMinecartContainerItems()[p_70304_1_];
            this.getMinecartContainerItems()[p_70304_1_] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_)
    {
        this.getMinecartContainerItems()[p_70299_1_] = p_70299_2_;

        if (p_70299_2_ != null && p_70299_2_.stackSize > this.getInventoryStackLimit())
        {
            p_70299_2_.stackSize = this.getInventoryStackLimit();
        }
    }


    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.isDead ? false : player.getDistanceSqToEntity(this) <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return true;
    }

    @Override
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.func_95999_t() : "container.minecart";
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void travelToDimension(int p_71027_1_)
    {
        this.setDropContentsWhenDead(false);
        super.travelToDimension(p_71027_1_);
    }

    @Override
    public void setDead()
    {
        if (this.isDropContentsWhenDead())
        {
            for (int i = 0; i < this.getSizeInventory(); ++i)
            {
                ItemStack itemstack = this.getStackInSlot(i);

                if (itemstack != null)
                {
                    float f = this.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.stackSize > 0)
                    {
                        int j = this.rand.nextInt(21) + 10;

                        if (j > itemstack.stackSize)
                        {
                            j = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j;
                        EntityItem entityitem = new EntityItem(this.worldObj, this.posX + (double)f, this.posY + (double)f1, this.posZ + (double)f2, new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

                        if (itemstack.hasTagCompound())
                        {
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
    protected void writeEntityToNBT(NBTTagCompound p_70014_1_)
    {
        super.writeEntityToNBT(p_70014_1_);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.getMinecartContainerItems().length; ++i)
        {
            if (this.getMinecartContainerItems()[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.getMinecartContainerItems()[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        p_70014_1_.setTag("Items", nbttaglist);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound p_70037_1_)
    {
        super.readEntityFromNBT(p_70037_1_);
        NBTTagList nbttaglist = p_70037_1_.getTagList("Items", 10);
        this.setMinecartContainerItems(new ItemStack[this.getSizeInventory()]);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.getMinecartContainerItems().length)
            {
                this.getMinecartContainerItems()[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public boolean interactFirst(EntityPlayer entityPlayer)
    {
        if(net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(
				new net.minecraftforge.event.entity.minecart.MinecartInteractEvent(this, entityPlayer))) {
			return true;
		}

		if(this instanceof IUpgradeable) {
			ItemStack curItem = entityPlayer.getCurrentEquippedItem();
			if(curItem != null) {
				for(Item item: ((IUpgradeable)this).getUpgradeItems()){
					if(item.equals(curItem.getItem())){
						EntityExtraCartsChestMinecart entityExtraCartsChestMinecart =
								((IUpgradeable) this).upgradedCart(curItem.getItem(), worldObj);
						entityExtraCartsChestMinecart.copyDataFrom(this, true);

						for (int i = 0; i < this.getSizeInventory(); i++) {
							this.setInventorySlotContents(i, null);
						}
						this.setDead();

						if(!entityPlayer.worldObj.isRemote) {
							entityPlayer.worldObj.spawnEntityInWorld(entityExtraCartsChestMinecart);
						}

						entityPlayer.destroyCurrentEquippedItem();

						return true;
					}
				}
			}
		}

        if (!this.worldObj.isRemote && !entityPlayer.isSneaking())
        {
			FMLNetworkHandler.openGui(entityPlayer, ExtraCarts.instance, 2, entityPlayer.worldObj, this.getEntityId(), 0, 0);
        }

        return true;
    }

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public void markDirty() {
	
	}

	@Override
	public int getMinecartType() {
		return 0;
	}


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
    public abstract boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart);


	//TODO: Anything below here is Railcraft code. Make sure to attach the license and ensure you are following it
	@Optional.Method(modid = "RailcraftAPI|carts")
	public ItemStack offerItem(Object source, ItemStack offer) {
		if (getSizeInventory() > 0) {
			offer = moveItemStack(offer, this);
			if (offer == null)
				return null;
		}

		ILinkageManager lm = CartTools.getLinkageManager(worldObj);

		EntityMinecart linkedCart = lm.getLinkedCartA(this);
		if (linkedCart != source && linkedCart instanceof IItemTransfer)
			offer = ((IItemTransfer) linkedCart).offerItem(this, offer);

		if (offer == null)
			return null;

		linkedCart = lm.getLinkedCartB(this);
		if (linkedCart != source && linkedCart instanceof IItemTransfer)
			offer = ((IItemTransfer) linkedCart).offerItem(this, offer);

		return offer;
	}

	@Optional.Method(modid = "RailcraftAPI|carts")
	public ItemStack requestItem(Object source) {
		return requestItem(this, StackFilter.ALL);
	}

	@Optional.Method(modid = "RailcraftAPI|carts")
	public ItemStack requestItem(Object source, ItemStack request) {
		return requestItem(this, new ArrayStackFilter(request));
	}

	@Optional.Method(modid = "RailcraftAPI|carts")
	public ItemStack requestItem(Object source, IStackFilter request) {
		ItemStack result = null;
		if (getSizeInventory() > 0) {
			result = removeOneItem(this, request);
			if (result != null)
				return result;
		}

		ILinkageManager lm = CartTools.getLinkageManager(worldObj);

		EntityMinecart linkedCart = lm.getLinkedCartA(this);
		if (linkedCart != source && linkedCart instanceof IItemTransfer)
			result = ((IItemTransfer) linkedCart).requestItem(this, request);

		if (result != null)
			return result;

		linkedCart = lm.getLinkedCartB(this);
		if (linkedCart != source && linkedCart instanceof IItemTransfer)
			result = ((IItemTransfer) linkedCart).requestItem(this, request);

		return result;
	}

	/**
	 * Removes and returns a single item from the inventory that matches the
	 * filter.
	 *
	 * @param inv The inventory
	 * @param filter EnumItemType to match against
	 * @return An ItemStack
	 */
	private ItemStack removeOneItem(IInventory inv, IStackFilter filter) {
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack slot = inv.getStackInSlot(i);
			if (slot != null && filter.matches(slot))
				return inv.decrStackSize(i, 1);
		}
		return null;
	}

	private ItemStack moveItemStack(ItemStack stack, IInventory dest) {
		if (stack == null)
			return null;
		stack = stack.copy();
		if (dest == null)
			return stack;
		boolean movedItem = false;
		do {
			movedItem = false;
			ItemStack destStack = null;
			for (int ii = 0; ii < dest.getSizeInventory(); ii++) {
				destStack = dest.getStackInSlot(ii);
				if (destStack != null && destStack.isItemEqual(stack)) {
					int maxStack = Math.min(destStack.getMaxStackSize(), dest.getInventoryStackLimit());
					int room = maxStack - destStack.stackSize;
					if (room > 0) {
						int move = Math.min(room, stack.stackSize);
						destStack.stackSize += move;
						stack.stackSize -= move;
						if (stack.stackSize <= 0)
							return null;
						movedItem = true;
					}
				}
			}
			if (!movedItem)
				for (int ii = 0; ii < dest.getSizeInventory(); ii++) {
					destStack = dest.getStackInSlot(ii);
					if (destStack == null) {
						if (stack.stackSize > dest.getInventoryStackLimit())
							dest.setInventorySlotContents(ii, stack.splitStack(dest.getInventoryStackLimit()));
						else {
							dest.setInventorySlotContents(ii, stack);
							return null;
						}
						movedItem = true;
					}
				}
		} while (movedItem);
		return stack;
	}
	// TODO: END Railcraft code

	public TileEntity getTileEntity() {
		return tileEntity;
	}

	public void setTileEntity(TileEntity tileEntity) {
		this.tileEntity = tileEntity;
	}

	public Block getCartBlock() {
		return cartBlock;
	}

	public void setCartBlock(Block cartBlock) {
		this.cartBlock = cartBlock;
	}
}
