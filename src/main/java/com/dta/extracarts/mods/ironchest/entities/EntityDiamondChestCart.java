package com.dta.extracarts.mods.ironchest.entities;

import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.ironchest.IronChestItems;
import com.dta.extracarts.mods.ironchest.client.ContainerDiamondChestCart;
import com.dta.extracarts.mods.ironchest.client.GuiDiamondChestCart;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

@Optional.Interface(iface = "mods.railcraft.api.carts.IMinecart", modid = "RailcraftAPI|carts")
public class EntityDiamondChestCart extends EntityExtraCartChestMinecart implements OpenableGUI, IMinecart {
	
	private Block ironChest = Block.getBlockFromName("IronChest:BlockIronChest");
	private Item DiamondCrystalUpgrade = GameRegistry.findItem("IronChest", "diamondCrystalUpgrade");
	private Item DiamondObsidianUpgrade = GameRegistry.findItem("IronChest", "diamondObsidianUpgrade");
	
	public EntityDiamondChestCart(World world) {
		super(world);
		this.setDisplayTileData(2);
	}

	@Override
	public Block getCartBlock() {
		return ironChest;
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
		return super.interactFirst(player);
    }

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiDiamondChestCart(player.inventory, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerDiamondChestCart(player.inventory, this);
	}

	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		ItemStack CartStack = new ItemStack(IronChestItems.IronChestCart, 1, 2);
		if (cart instanceof EntityDiamondChestCart && stack.getItem() == CartStack.getItem() && stack.getItemDamage() == 2) {
				return true;
		}
		return false;
	}

}
