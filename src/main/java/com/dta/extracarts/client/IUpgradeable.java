package com.dta.extracarts.client;

import com.dta.extracarts.entities.EntityExtraCartsChestMinecart;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.ArrayList;


/**
 * Created by Skylar on 2/18/2015.
 */
public interface IUpgradeable {
	public ArrayList<Item> upgrades = new ArrayList<Item>();

	public ArrayList<Item> getUpgradeItems();

	public EntityExtraCartsChestMinecart upgradedCart(Item currentHeldItem, World world);

	void addUpgrades();
}
