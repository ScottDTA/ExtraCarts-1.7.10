package com.dta.extracarts.mods.enderio.entities;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.enderio.EnderIOItems;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Created by Skylar on 10/26/2014.
 */
@Optional.Interface(iface="mods.railcraft.api.carts.IMinecart", modid="RailcraftAPI|carts")
public class EntityCapacitorBankCart extends EntityExtraCartChestMinecart {
	private Block capacitorBank = Block.getBlockFromName("minecraft:chest");

	public EntityCapacitorBankCart(World world) {
		super(world);
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
		return capacitorBank;
	}

	@Override
	public void killMinecart(DamageSource par1DamageSource) {
		super.killMinecart(par1DamageSource);
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		if (!this.worldObj.isRemote) {
			FMLNetworkHandler.openGui(player, ExtraCarts.instance, 2, player.worldObj, this.getEntityId(), 0, 0);
		}
		return true;
	}

	@Optional.Method(modid = "RailcraftAPI|carts")
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		ItemStack CartStack = new ItemStack(EnderIOItems.itemCapacitorBankCart, 1, 0);
		if (cart instanceof EntityCapacitorBankCart && stack.getItem() == CartStack.getItem()) {
			return true;
		}
		return false;
	}
}
