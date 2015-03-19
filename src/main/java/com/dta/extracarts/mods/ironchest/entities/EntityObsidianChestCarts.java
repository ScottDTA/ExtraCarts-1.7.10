package com.dta.extracarts.mods.ironchest.entities;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartsChestMinecart;
import com.dta.extracarts.mods.ironchest.IronChestItems;
import com.dta.extracarts.mods.ironchest.client.ContainerDiamondChestCart;
import com.dta.extracarts.mods.ironchest.client.GuiDiamondChestCart;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import mods.railcraft.api.carts.IItemTransfer;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

@Optional.InterfaceList({
		@Optional.Interface(iface = "mods.railcraft.api.carts.IMinecart", modid = "RailcraftAPI|carts"),
		@Optional.Interface(iface = "mods.railcraft.api.carts.IItemTransfer", modid = "RailcraftAPI|carts")
})
public class EntityObsidianChestCarts extends EntityExtraCartsChestMinecart implements OpenableGUI, IMinecart, IItemTransfer {
	
	private Block ironChest = Block.getBlockFromName("IronChest:BlockIronChest");
	
	public EntityObsidianChestCarts(World world) {
		super(world);
		this.setDisplayTileData(6);
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
		super.killMinecart(par1DamageSource, new ItemStack(ironChest, 1, 6));
    }
	
	@Override
	public boolean interactFirst(EntityPlayer player) {
	    if (!this.worldObj.isRemote) {
	    	FMLNetworkHandler.openGui(player, ExtraCarts.instance, 2, player.worldObj, this.getEntityId(), 0, 0);
	    }
        return true;
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
		ItemStack CartStack = new ItemStack(IronChestItems.IronChestCart, 1, 6);
		if (cart instanceof EntityObsidianChestCarts && stack.getItem() == CartStack.getItem() && stack.getItemDamage() == 6) {
				return true;
		}
		return false;
	}
}
