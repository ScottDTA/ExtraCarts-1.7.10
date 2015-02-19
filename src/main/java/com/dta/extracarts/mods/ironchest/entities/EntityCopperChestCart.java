package com.dta.extracarts.mods.ironchest.entities;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.ironchest.IronChestItems;
import com.dta.extracarts.mods.ironchest.client.ContainerCopperChestCart;
import com.dta.extracarts.mods.ironchest.client.GuiCopperChestCart;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.railcraft.api.carts.IItemTransfer;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

@Optional.InterfaceList({
		@Optional.Interface(iface = "mods.railcraft.api.carts.IMinecart", modid = "RailcraftAPI|carts"),
		@Optional.Interface(iface = "mods.railcraft.api.carts.IItemTransfer", modid = "RailcraftAPI|carts")
})
public class EntityCopperChestCart extends EntityExtraCartChestMinecart implements OpenableGUI, IMinecart, IItemTransfer {
	
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

	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		ItemStack CartStack = new ItemStack(IronChestItems.IronChestCart, 1, 3);
		if (cart instanceof EntityCopperChestCart && stack.getItem() == CartStack.getItem() && stack.getItemDamage() == 3) {
				return true;
		}
		return false;
	}
}
