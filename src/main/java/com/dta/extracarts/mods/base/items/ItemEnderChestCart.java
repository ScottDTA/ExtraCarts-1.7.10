package com.dta.extracarts.mods.base.items;

import com.dta.extracarts.items.ExtraCartItem;
import com.dta.extracarts.mods.base.entities.EntityEnderChestCarts;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.dta.extracarts.ModInfo;

public class ItemEnderChestCart extends ExtraCartItem {

	public ItemEnderChestCart() {
		super(1);
		this.setUnlocalizedName("EnderChestCart");
		setTextureName(ModInfo.MODID + ":base/EnderChestCart");
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		return super.placeCart(itemstack, player, world, x, y, z, new EntityEnderChestCarts(world));
    }
}
