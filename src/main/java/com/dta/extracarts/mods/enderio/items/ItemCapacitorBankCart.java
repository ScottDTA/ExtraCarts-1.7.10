package com.dta.extracarts.mods.enderio.items;

import com.dta.extracarts.items.ExtraCartItem;
import com.dta.extracarts.mods.enderio.entities.EntityCapacitorBankCart;
import com.dta.extracarts.utils.LogUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import java.util.List;

/**
 * Created by Skylar on 10/22/2014.
 */
public class ItemCapacitorBankCart extends ExtraCartItem {
	@SideOnly(Side.CLIENT)
	private IIcon itemCapacitorBankCart;

	public ItemCapacitorBankCart() {
		super(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int dmg) {
		switch (dmg) {
			default:
				return itemCapacitorBankCart;
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
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		EntityMinecart entityMinecart;
		entityMinecart = new EntityCapacitorBankCart(world);
		return placeCart(itemstack, player, world, x, y, z, entityMinecart);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemCapacitorBankCart = register.registerIcon("extracarts:thermalexpansion/StrongBoxCart");
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
