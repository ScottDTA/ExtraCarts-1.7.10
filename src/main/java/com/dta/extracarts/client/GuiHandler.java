package com.dta.extracarts.client;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.modcompat.ironchest.client.ContainerCopperChestCart;
import com.dta.extracarts.modcompat.ironchest.client.ContainerDiamondChestCart;
import com.dta.extracarts.modcompat.ironchest.client.ContainerDirtChestCart;
import com.dta.extracarts.modcompat.ironchest.client.ContainerGoldChestCart;
import com.dta.extracarts.modcompat.ironchest.client.ContainerIronChestCart;
import com.dta.extracarts.modcompat.ironchest.client.ContainerSilverChestCart;
import com.dta.extracarts.modcompat.ironchest.client.GuiCopperChestCart;
import com.dta.extracarts.modcompat.ironchest.client.GuiDiamondChestCart;
import com.dta.extracarts.modcompat.ironchest.client.GuiDirtChestCart;
import com.dta.extracarts.modcompat.ironchest.client.GuiGoldChestCart;
import com.dta.extracarts.modcompat.ironchest.client.GuiIronChestCart;
import com.dta.extracarts.modcompat.ironchest.client.GuiSilverChestCart;
import com.dta.extracarts.modcompat.ironchest.entities.EntityCopperChestCart;
import com.dta.extracarts.modcompat.ironchest.entities.EntityCrystalChestCart;
import com.dta.extracarts.modcompat.ironchest.entities.EntityDiamondChestCart;
import com.dta.extracarts.modcompat.ironchest.entities.EntityDirtChestCart;
import com.dta.extracarts.modcompat.ironchest.entities.EntityGoldChestCart;
import com.dta.extracarts.modcompat.ironchest.entities.EntityIronChestCart;
import com.dta.extracarts.modcompat.ironchest.entities.EntityObsidianChestCart;
import com.dta.extracarts.modcompat.ironchest.entities.EntitySilverChestCart;
import com.dta.extracarts.modcompat.mfr.client.interfaces.ContainerDSUCart;
import com.dta.extracarts.modcompat.mfr.client.interfaces.GuiDSUCart;
import com.dta.extracarts.modcompat.mfr.entities.EntityDSUCart;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {
	
	public GuiHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(ExtraCarts.instance, this);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		Entity entity = world.getEntityByID(x);
		switch(ID) {
			case 0:
				if (entity != null && entity instanceof EntityIronChestCart) {
					return new ContainerIronChestCart(player.inventory, (EntityIronChestCart) entity);
				}
				break;
			case 1:
				if (entity != null && entity instanceof EntityGoldChestCart) {
					return new ContainerGoldChestCart(player.inventory, (EntityGoldChestCart) entity);
				}
				break;
			case 2:
				if (entity != null && entity instanceof EntityDiamondChestCart) {
					return new ContainerDiamondChestCart(player.inventory, (EntityDiamondChestCart) entity);
				} else if (entity instanceof EntityCrystalChestCart) {
					return new ContainerDiamondChestCart(player.inventory, (EntityCrystalChestCart) entity);
				} else if (entity instanceof EntityObsidianChestCart) {
					return new ContainerDiamondChestCart(player.inventory, (EntityObsidianChestCart) entity);
				}
				break;
			case 3:
				if (entity != null && entity instanceof EntityCopperChestCart) {
					return new ContainerCopperChestCart(player.inventory, (EntityCopperChestCart) entity);
				}
				break;
			case 4:
				if (entity != null && entity instanceof EntitySilverChestCart) {
					return new ContainerSilverChestCart(player.inventory, (EntitySilverChestCart) entity);
				}
				break;
			case 5:
				if (entity != null && entity instanceof EntityDirtChestCart) {
					return new ContainerDirtChestCart(player.inventory, (EntityDirtChestCart) entity);
				}
				break;
			case 6:
				if (entity != null && entity instanceof EntityDSUCart) {
					return new ContainerDSUCart(player.inventory, (EntityDSUCart) entity);
				}
				break;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		Entity entity = world.getEntityByID(x);
		switch(ID) {
			case 0:
				if (entity != null && entity instanceof EntityIronChestCart) {
					return new GuiIronChestCart(player.inventory, (EntityIronChestCart) entity);
				}
				break;
			case 1:
				if (entity != null && entity instanceof EntityGoldChestCart) {
					return new GuiGoldChestCart(player.inventory, (EntityGoldChestCart) entity);
				}
				break;
			case 2:
				if (entity != null && entity instanceof EntityDiamondChestCart) {
					return new GuiDiamondChestCart(player.inventory, (EntityDiamondChestCart) entity);
				} else if (entity instanceof EntityCrystalChestCart) {
					return new GuiDiamondChestCart(player.inventory, (EntityCrystalChestCart) entity);
				} else if (entity instanceof EntityObsidianChestCart) {
					return new GuiDiamondChestCart(player.inventory, (EntityObsidianChestCart) entity);
				}
				break;
			case 3:
				if (entity != null && entity instanceof EntityCopperChestCart) {
					return new GuiCopperChestCart(player.inventory, (EntityCopperChestCart) entity);
				}
				break;
			case 4:
				if (entity != null && entity instanceof EntitySilverChestCart) {
					return new GuiSilverChestCart(player.inventory, (EntitySilverChestCart) entity);
				}
				break;
			case 5:
				if (entity != null && entity instanceof EntityDirtChestCart) {
					return new GuiDirtChestCart(player.inventory, (EntityDirtChestCart) entity);
				}
				break;
			case 6:
				if (entity != null && entity instanceof EntityDSUCart) {
					return new GuiDSUCart(player.inventory, (EntityDSUCart) entity);
				}
				break;
		}
		return null;
	}

}
