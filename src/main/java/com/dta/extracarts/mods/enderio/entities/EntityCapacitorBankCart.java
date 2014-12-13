package com.dta.extracarts.mods.enderio.entities;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.entities.EntityExtraCartChestMinecart;
import com.dta.extracarts.mods.enderio.EnderIOItems;
import com.dta.extracarts.mods.ironchest.client.ContainerDiamondChestCart;
import com.dta.extracarts.mods.ironchest.client.GuiDiamondChestCart;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import mods.railcraft.api.carts.IMinecart;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Created by Skylar on 10/26/2014.
 */
@Optional.Interface(iface="IMinecart", modid="Railcraft")
public class EntityCapacitorBankCart extends EntityExtraCartChestMinecart implements OpenableGUI, IMinecart {
	private Block ironChest = Block.getBlockFromName("IronChest:BlockIronChest");

	public EntityCapacitorBankCart(World world) {
		super(world);
		this.setDisplayTileData(6);
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
		return ironChest;
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

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new GuiDiamondChestCart(player.inventory, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return new ContainerDiamondChestCart(player.inventory, this);
	}

	@Optional.Method(modid = "Railcraft")
	@Override
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		ItemStack CartStack = new ItemStack(EnderIOItems.itemCapacitorBankCart, 1, 0);
		if (cart instanceof EntityCapacitorBankCart && stack.getItem() == CartStack.getItem()) {
			return true;
		}
		return false;
	}
}
