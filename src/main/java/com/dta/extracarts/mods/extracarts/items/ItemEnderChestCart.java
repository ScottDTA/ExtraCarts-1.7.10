package com.dta.extracarts.mods.extracarts.items;

import net.minecraft.block.BlockRailBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.dta.extracarts.ModInfo;
import com.dta.extracarts.mods.extracarts.entities.EntityEnderChestCart;

public class ItemEnderChestCart extends ItemMinecart {

	public ItemEnderChestCart() {
		super(1);
		this.setUnlocalizedName("EnderChestCart");
		this.setCreativeTab(CreativeTabs.tabTransport);
		this.maxStackSize = 1;
		setTextureName(ModInfo.MODID + ":EnderChestCart");
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		if (BlockRailBase.func_150051_a(world.getBlock(x, y, z))) {
			if (!world.isRemote) {
				EntityEnderChestCart cart = new EntityEnderChestCart(world);
				cart.posX = (float)x + 0.5F;
				cart.posY = (float)y + 0.5F;
				cart.posZ = (float)z + 0.5F;
				world.spawnEntityInWorld(cart);
			}
			--itemstack.stackSize;
			return true;
        } else {
            return false;
        }
    }
	
	
}
