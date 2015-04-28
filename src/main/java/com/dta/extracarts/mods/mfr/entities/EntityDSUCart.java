package com.dta.extracarts.mods.mfr.entities;

import com.dta.extracarts.block.FakeBlockRegistry;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.mfr.MFRItems;
import com.dta.extracarts.mods.mfr.client.ContainerDSUCart;
import com.dta.extracarts.mods.mfr.client.GuiDSUCart;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

@Optional.Interface(iface = "mods.railcraft.api.carts.IMinecart", modid = "RailcraftAPI|carts")
public class EntityDSUCart extends EntityExtraCartChestMinecart implements OpenableGUI {
	private ItemStack storedItem = null;
	private int storedQty = 0;
	private Block dsu = GameRegistry.findBlock("extracarts", "fakeBlock." +
			FakeBlockRegistry.getFakeBlockByName("fakeDSUBlock").getBlockNumber());
	
	public EntityDSUCart(World world) {
		super(world);
		this.setDropContentsWhenDead(false);
		this.setDisplayTileData(FakeBlockRegistry.getFakeBlockByName("fakeDSUBlock").getMetaNumber());
	}

	@Override
	public Block getCartBlock() {
		return dsu;
	}
	
	@Override
	public void killMinecart(DamageSource par1DamageSource) {
		super.killMinecartNoDrop(par1DamageSource);
		float f = this.rand.nextFloat() * 0.8F + 0.1F;
        float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
        float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
		ItemStack dropCart = new ItemStack(dsu, 1, 3);
		NBTTagCompound compound = new NBTTagCompound();
		if (storedItem != null)	{
			compound.setTag("storedStack", storedItem.writeToNBT(new NBTTagCompound()));
			compound.setInteger("storedQuantity", storedQty + this.getMinecartContainerItems()[2].stackSize);
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

    public void onInventoryChanged() {
		if (worldObj.isRemote) {
			return;
		}
		
		if ((this.getMinecartContainerItems()[2] == null) & storedItem != null & storedQty == 0) {
			storedItem = null;
		}
		
		if (this.getMinecartContainerItems()[0] != null) {
			ItemStack stack = this.getMinecartContainerItems()[0].copy();
			stack.stackSize = 1;
			if (storedQty == 0 && (storedItem == null || ItemStack.areItemStacksEqual(storedItem, stack))) {
				storedItem = this.getMinecartContainerItems()[0].copy();
				storedItem.stackSize = 1;
				storedQty = this.getMinecartContainerItems()[0].stackSize;
				this.getMinecartContainerItems()[0] = null;
			} else if (ItemStack.areItemStacksEqual(storedItem, stack)) {
				storedQty += this.getMinecartContainerItems()[0].stackSize;
				this.getMinecartContainerItems()[0] = null;
			}
		}
		
		if (this.getMinecartContainerItems()[1] != null) {
			ItemStack stack = this.getMinecartContainerItems()[1].copy();
			stack.stackSize = 1;
			if (storedQty == 0 && (storedItem == null || ItemStack.areItemStacksEqual(storedItem, stack))) {
				storedItem = this.getMinecartContainerItems()[1].copy();
				storedItem.stackSize = 1;
				storedQty = this.getMinecartContainerItems()[1].stackSize;
				this.getMinecartContainerItems()[1] = null;
			} else if (ItemStack.areItemStacksEqual(storedItem, stack)) {
				storedQty += this.getMinecartContainerItems()[1].stackSize;
				this.getMinecartContainerItems()[1] = null;
			}
		}
		
		if (this.getMinecartContainerItems()[2] == null && storedItem != null) {
			this.getMinecartContainerItems()[2] = storedItem.copy();
			this.getMinecartContainerItems()[2].stackSize = Math.min(storedQty, Math.min(storedItem.getMaxStackSize(), this.getInventoryStackLimit()));
			storedQty -= this.getMinecartContainerItems()[2].stackSize;
		} else if (this.getMinecartContainerItems()[2] != null) {
			ItemStack stack = this.getMinecartContainerItems()[2].copy();
			stack.stackSize = 1;
			if (storedQty > 0 && this.getMinecartContainerItems()[2].stackSize < this.getMinecartContainerItems()[2].getMaxStackSize() && ItemStack.areItemStacksEqual(storedItem, stack)) {
				int amt = Math.min(this.getMinecartContainerItems()[2].getMaxStackSize() - this.getMinecartContainerItems()[2].stackSize, storedQty);
				this.getMinecartContainerItems()[2].stackSize += amt;
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
		ItemStack o = this.getMinecartContainerItems()[2];
		if (o != null) {
			storedAdd = o.stackSize;
			this.getMinecartContainerItems()[2] = null;
		}
		super.writeToNBT(compound);
		this.getMinecartContainerItems()[2] = o;
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
		if (this.getMinecartContainerItems()[2] == null) {
			return 64;
		}
	    return Math.min(64, (Integer.MAX_VALUE - (storedQty + this.getMinecartContainerItems()[2].stackSize)));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiDSUCart(player.inventory, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerDSUCart(player.inventory, this);
	}

	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		ItemStack CartStack = new ItemStack(MFRItems.MFRCart, 1, 0);
		if (cart instanceof EntityDSUCart && stack.getItem() == CartStack.getItem() && stack.getItemDamage() == 0) {
				return true;
		}
		return false;
	}

	@Override
	protected ItemStack moveItemStack(ItemStack stack, IInventory dest) {
		if(stack == null) {
			return null;
		}
		if(storedItem == null) {
			storedItem = stack;
		} else if (stack.getItem() == storedItem.getItem()) {
			if (stack.stackSize + storedQty < Integer.MAX_VALUE) {
				storedQty += stack.stackSize;
				stack = null;
			}
		}
		return stack;
	}
}
