package com.dta.extracarts.mods.ironchest.entities;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.ironchest.IronChestItems;
import com.dta.extracarts.mods.ironchest.client.ContainerDirtChestCart;
import com.dta.extracarts.mods.ironchest.client.GuiDirtChestCart;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

@Optional.Interface(iface = "mods.railcraft.api.carts.IMinecart", modid = "RailcraftAPI|carts")
public class EntityDirtChestCart extends EntityExtraCartChestMinecart implements OpenableGUI, IMinecart {
	
	private Block ironChest = Block.getBlockFromName("IronChest:BlockIronChest");
	
	public EntityDirtChestCart(World world) {
		super(world);
		this.setDisplayTileData(7);
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}
	
	@Override
	public Block func_145817_o() {
		return ironChest;
	}

	@Override
	public void killMinecart(DamageSource par1DamageSource) {
		super.killMinecart(par1DamageSource, new ItemStack(ironChest, 1, 7));
	}
	
	@Override
	public boolean interactFirst(EntityPlayer player) {
	    if (!this.worldObj.isRemote) {
	    	FMLNetworkHandler.openGui(player, ExtraCarts.instance, 5, player.worldObj, this.getEntityId(), 0, 0);
	    }
        return true;
    }

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiDirtChestCart(player.inventory, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerDirtChestCart(player.inventory, this);
	}

	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		ItemStack CartStack = new ItemStack(IronChestItems.IronChestCart, 1, 7);
		if (cart instanceof EntityDirtChestCart && stack.getItem() == CartStack.getItem() && stack.getItemDamage() == 7) {
				return true;
		}
		return false;
	}
}
