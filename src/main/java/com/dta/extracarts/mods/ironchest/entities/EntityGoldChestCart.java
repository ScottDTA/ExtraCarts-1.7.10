package com.dta.extracarts.mods.ironchest.entities;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.ironchest.IronChestItems;
import com.dta.extracarts.mods.ironchest.client.ContainerGoldChestCart;
import com.dta.extracarts.mods.ironchest.client.GuiGoldChestCart;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
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
public class EntityGoldChestCart extends EntityExtraCartChestMinecart implements OpenableGUI, IMinecart {
	
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
		super.killMinecart(par1DamageSource, new ItemStack(ironChest, 1, 1));
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

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiGoldChestCart(player.inventory, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerGoldChestCart(player.inventory, this);
	}

	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		ItemStack CartStack = new ItemStack(IronChestItems.IronChestCart, 1, 1);
		if (cart instanceof EntityGoldChestCart && stack.getItem() == CartStack.getItem() && stack.getItemDamage() == 1) {
				return true;
		}
		return false;
	}
}
