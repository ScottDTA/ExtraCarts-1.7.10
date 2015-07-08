/*
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 *
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package com.dta.extracarts.borrowed.railcraft.common.util.inventory.filters;

import com.dta.extracarts.borrowed.railcraft.common.util.inventory.InvTools;
import mods.railcraft.api.core.items.IStackFilter;
import net.minecraft.item.ItemStack;

/**
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class ArrayStackFilter implements IStackFilter {

	private final ItemStack[] stacks;

	public ArrayStackFilter(ItemStack... stacks) {
		this.stacks = stacks;
	}

	@Override
	public boolean matches(ItemStack stack) {
		if (stacks.length == 0 || !hasFilter()) {
			return true;
		}
		return InvTools.isItemEqual(stack, stacks);
	}

	public ItemStack[] getStacks() {
		return stacks;
	}

	public boolean hasFilter() {
		for (ItemStack filter : stacks) {
			if (filter != null) {
				return true;
			}
		}
		return false;
	}
}