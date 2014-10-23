package com.dta.extracarts.mods.thermalexpansion.items;

import com.dta.extracarts.items.ExtraCartItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Skylar on 10/22/2014.
 */
public class ItemStrongBoxCart extends ExtraCartItem {
	@SideOnly(Side.CLIENT)
	private IIcon itemStrongBoxCart;
	private IIcon itemHardenedStrongBoxCart;

	public ItemStrongBoxCart() {
		super(1);
		this.hasSubtypes=true;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7,
							 float par8, float par9, float par10) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int dmg) {
		switch (dmg) {
			default:
				return itemStrongBoxCart;
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		switch (itemstack.getItemDamage()) {
			default:
				return "item.StrongBoxCart";
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemStrongBoxCart = register.registerIcon("extracarts:thermalexpansion/StrongBoxCart");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 1; i++) {
			ItemStack stack = new ItemStack(item, 1, i);
			list.add(stack);
		}
	}
}
