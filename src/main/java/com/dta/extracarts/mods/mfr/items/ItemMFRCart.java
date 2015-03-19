package com.dta.extracarts.mods.mfr.items;

import java.util.List;

import com.dta.extracarts.items.ExtraCartItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.dta.extracarts.ModInfo;
import com.dta.extracarts.mods.mfr.entities.EntityDSUCarts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMFRCart extends ExtraCartItem {

	public ItemMFRCart() {
		super(1);
		this.setUnlocalizedName("DSUCart");
		setTextureName(ModInfo.MODID + ":mfr/DSUCart");
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7,
							 float par8, float par9, float par10) {
		EntityDSUCarts cart = new EntityDSUCarts(world);
		NBTTagCompound compound = itemstack.getTagCompound();
		if (compound != null) {
			cart.setQuantity(compound.getInteger("storedQuantity"));
			if (compound.hasKey("storedStack")) {
				cart.setItem(ItemStack.loadItemStackFromNBT((NBTTagCompound)compound.getTag("storedStack")));
			}
		} else {
			cart.setQuantity(0);
		}
		return super.placeCart(itemstack, player, world, x, y, z, cart);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		super.addInformation(stack, player, list, par4);
		NBTTagCompound compound = stack.getTagCompound();
		if (compound != null && compound.hasKey("storedStack")) {
			ItemStack stored = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("storedStack"));
			int qty = compound.getInteger("storedQuantity");
			if (stored != null && qty > 0) {
				list.add("Contains " + qty + " " + stored.getDisplayName() + (par4 ? " (" + Item.itemRegistry.getIDForObject(stored.getItem()) + ":" + stored.getItemDamageForDisplay() + ")" : ""));
			}
		}
	}
}
