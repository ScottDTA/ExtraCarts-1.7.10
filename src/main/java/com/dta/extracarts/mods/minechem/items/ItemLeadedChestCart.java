package com.dta.extracarts.mods.minechem.items;

import com.dta.extracarts.ModInfo;
import com.dta.extracarts.items.ExtraCartItem;
import com.dta.extracarts.mods.minechem.entities.EntityLeadedChestCart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Skylar on 3/30/2015.
 */
public class ItemLeadedChestCart extends ExtraCartItem {
	public ItemLeadedChestCart() {
		super(1);
		this.setUnlocalizedName("LeadedChestCart");
		this.setTextureName(ModInfo.MODID + ":minechem/LeadedChestCart");
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		return placeCart(itemstack, player, world, x, y, z, new EntityLeadedChestCart(world));
	}
}
