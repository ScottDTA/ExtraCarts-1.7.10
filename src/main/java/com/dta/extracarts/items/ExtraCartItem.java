package com.dta.extracarts.items;

import net.minecraft.block.BlockRailBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Skylar on 10/22/2014.
 */
public abstract class ExtraCartItem extends ItemMinecart {
	public ExtraCartItem(int p_i45345_1_) {
		super(p_i45345_1_);
		this.setCreativeTab(CreativeTabs.tabTransport);
		this.maxStackSize = 1;
	}

	@Override
	public abstract boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7,
							 float par8, float par9, float par10);

	public boolean placeCart(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z,
							 EntityMinecart entityMinecart) {
		if (BlockRailBase.func_150051_a(world.getBlock(x, y, z))) {
			if (!world.isRemote) {
				entityMinecart.posX = (float)x + 0.5F;
				entityMinecart.posY = (float)y + 0.5F;
				entityMinecart.posZ = (float)z + 0.5F;
				world.spawnEntityInWorld(entityMinecart);
			}

			--itemStack.stackSize;
			return true;
		} else {
			return false;
		}
	}
}
