package com.dta.extracarts.mods.enderio.block;

import com.dta.extracarts.block.FakeSubBlock;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 * Created by Skylar on 4/27/2015.
 */
public class FakeSimpleCapacitorBank extends FakeSubBlock {
	private IIcon texture;

	public FakeSimpleCapacitorBank() {
		super("fakeSimpleCapacitorBank");
	}

	@Override
	public void registerBlockIcons(IIconRegister iIconRegister) {
		texture = iIconRegister.registerIcon("extracarts:enderio/capacitorBankSimple");
	}

	@Override
	public IIcon getIcon(int side, int metadata) {
		return texture;
	}
}
