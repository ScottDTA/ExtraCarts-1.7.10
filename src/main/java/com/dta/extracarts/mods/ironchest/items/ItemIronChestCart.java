package com.dta.extracarts.mods.ironchest.items;

import java.util.List;

import com.dta.extracarts.items.ExtraCartItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.dta.extracarts.mods.ironchest.entities.EntityCopperChestCart;
import com.dta.extracarts.mods.ironchest.entities.EntityCrystalChestCart;
import com.dta.extracarts.mods.ironchest.entities.EntityDiamondChestCart;
import com.dta.extracarts.mods.ironchest.entities.EntityDirtChestCart;
import com.dta.extracarts.mods.ironchest.entities.EntityGoldChestCart;
import com.dta.extracarts.mods.ironchest.entities.EntityIronChestCart;
import com.dta.extracarts.mods.ironchest.entities.EntityObsidianChestCart;
import com.dta.extracarts.mods.ironchest.entities.EntitySilverChestCart;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemIronChestCart extends ExtraCartItem {
	
	@SideOnly(Side.CLIENT)
	private IIcon itemIronChestCart;
	private IIcon itemGoldChestCart;
	private IIcon itemDiamondChestCart;
	private IIcon itemCopperChestCart;
	private IIcon itemSilverChestCart;
	private IIcon itemCrystalChestCart;
	private IIcon itemObsidianChestCart;
	private IIcon itemDirtChestCart;
	
	public ItemIronChestCart() {
		super(1);
		this.hasSubtypes=true;
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
		EntityMinecart entityMinecart;
		switch (itemstack.getItemDamage()) {
			default:
				entityMinecart = new EntityIronChestCart(world);
				break;
			case 1:
				entityMinecart = new EntityGoldChestCart(world);
				break;
			case 2:
				entityMinecart = new EntityDiamondChestCart(world);
				break;
			case 3:
				entityMinecart = new EntityCopperChestCart(world);
				break;
			case 4:
				entityMinecart = new EntitySilverChestCart(world);
				break;
			case 5:
				entityMinecart = new EntityCrystalChestCart(world);
				break;
			case 6:
				entityMinecart = new EntityObsidianChestCart(world);
				break;
			case 7:
				entityMinecart = new EntityDirtChestCart(world);
				break;
		}
		return placeCart(itemstack, player, world, x, y, z, entityMinecart);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIronChestCart = register.registerIcon("extracarts:ironchest/IronChestCart");
		itemGoldChestCart = register.registerIcon("extracarts:ironchest/GoldChestCart");
		itemDiamondChestCart = register.registerIcon("extracarts:ironchest/DiamondChestCart");
		itemCopperChestCart = register.registerIcon("extracarts:ironchest/CopperChestCart");
		itemSilverChestCart = register.registerIcon("extracarts:ironchest/SilverChestCart");
		itemCrystalChestCart = register.registerIcon("extracarts:ironchest/CrystalChestCart");
		itemObsidianChestCart = register.registerIcon("extracarts:ironchest/ObsidianChestCart");
		itemDirtChestCart = register.registerIcon("extracarts:ironchest/DirtChestCart");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int dmg) {
		switch (dmg) {
			default:	
				return itemIronChestCart;
			case 1:
				return itemGoldChestCart;
			case 2:
				return itemDiamondChestCart;
			case 3:
				return itemCopperChestCart;
			case 4:
				return itemSilverChestCart;
			case 5:
				return itemCrystalChestCart;
			case 6:
				return itemObsidianChestCart;
			case 7:
				return itemDirtChestCart;
		}
		
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		switch (itemstack.getItemDamage()) {
		  	default:
		  		return "item.IronChestCart";
		  	case 1:
		  		return "item.GoldChestCart";
		  	case 2:
		  		return "item.DiamondChestCart";
		  	case 3:
		  		return "item.CopperChestCart";
		  	case 4:
		  		return "item.SilverChestCart";
		  	case 5:
		  		return "item.CrystalChestCart";
		  	case 6:
		  		return "item.ObsidianChestCart";
		  	case 7:
		  		return "item.DirtChestCart";
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	  for (int i = 0; i < 8; i++) {
		  ItemStack stack = new ItemStack(item, 1, i);
		  list.add(stack);
	  }
	}
}
