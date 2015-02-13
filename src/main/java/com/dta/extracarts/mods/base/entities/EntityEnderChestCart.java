package com.dta.extracarts.mods.base.entities;

import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import cpw.mods.fml.common.Optional;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;

@Optional.Interface(iface="mods.railcraft.api.carts.IMinecart", modid="RailcraftAPI|carts")
public class EntityEnderChestCart extends EntityExtraCartChestMinecart {
	Block enderchest = Blocks.ender_chest;
	TileEntity tileEntity = new TileEntityEnderChest();

	public EntityEnderChestCart(World world) {
		super(world);
		this.setCartBlock(enderchest);
		this.setTileEntity(tileEntity);
	}
	
	@Override
	public int getSizeInventory() {
		return 27;
	}

	@Override
	public int getMinecartType() {
		return 1;
	}

	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		return false;
	}

	@Override
	public Block func_145817_o() {
		return this.getCartBlock();
	}
	
	@Override
	public void killMinecart(DamageSource par1DamageSource) {
		super.killMinecart(par1DamageSource);
		this.func_145778_a(Item.getItemFromBlock(Blocks.ender_chest), 1, 0.0F);
    }
	
	@Override
	public boolean interactFirst(EntityPlayer player) {
		if(MinecraftForge.EVENT_BUS.post(new MinecartInteractEvent(this, player))) {
			return true;
	    }
		InventoryEnderChest inventoryenderchest = player.getInventoryEnderChest();

		if (!this.worldObj.isRemote) {
			player.displayGUIChest(inventoryenderchest);
		}
		return true;
    }


}
