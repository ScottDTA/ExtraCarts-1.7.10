package com.dta.extracarts.mods.mfr.items.crafting;

import com.dta.extracarts.mods.mfr.MFRSubMod;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;

public class DSUCartRecipe implements IRecipe {

	//private Item dsuCart = new ItemMFRCart();
	//private ItemStack result = new ItemStack(dsuCart, 1, 0);
	private Block dsuBlock = GameRegistry.findBlock("MineFactoryReloaded", "tile.mfr.machine.1");
	private ItemStack dsuStack = new ItemStack(dsuBlock, 1, 3);
	
	
	@Override
	public boolean matches(InventoryCrafting inventorycrafting, World world) {
		int cart = 0;
        int dsu = 0;
        int others = 0;
        for (int k1 = 0; k1 < inventorycrafting.getSizeInventory(); ++k1) {
        	ItemStack itemstack = inventorycrafting.getStackInSlot(k1);
        	if (itemstack != null) {
        		if (itemstack.getItem() == Items.minecart) {
        			++cart;
        		} else if (itemstack.getItem() == dsuStack.getItem() && itemstack.getItemDamage() == 3) {
         			++dsu;
        		} else {
        			++others;
        		}
        	}
        }
      	if (cart != 1 || dsu != 1 || others > 0) {
    		return false;
    	} else {
    		return true;
    	}
    	
    	
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventorycrafting) {
		ItemStack dsu = null;
		for (int x = 0; x < inventorycrafting.getSizeInventory(); ++x) {
			if (inventorycrafting.getStackInSlot(x) != null && inventorycrafting.getStackInSlot(x).getItem() == dsuStack.getItem()) {
				dsu = inventorycrafting.getStackInSlot(x).copy();
			}
		}
		ItemStack result = new ItemStack(MFRSubMod.MFRCart, 1, 0);
		int qty = 0;
		ItemStack stored = null;
		if (dsu.hasTagCompound()) {
			NBTTagCompound dsuCompound = dsu.getTagCompound();
			if (dsuCompound.hasKey("storedQuantity")) {
				qty = dsuCompound.getInteger("storedQuantity");
			}
			if (dsuCompound.hasKey("storedStack")) {
				stored = ItemStack.loadItemStackFromNBT((NBTTagCompound)dsuCompound.getTag("storedStack"));
			}
		}
		NBTTagCompound resultCompound = new NBTTagCompound();
		resultCompound.setInteger("storedQuantity", qty);
		if (stored != null) {
			resultCompound.setTag("storedStack", stored.writeToNBT(new NBTTagCompound()));
		}
		result.setTagCompound(resultCompound);
		return result;
	}

	@Override
	public int getRecipeSize() {
		return 10;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return new ItemStack(MFRSubMod.MFRCart, 1, 0);
	}
}
