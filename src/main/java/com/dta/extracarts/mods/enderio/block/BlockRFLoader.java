package com.dta.extracarts.mods.enderio.block;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.mods.enderio.tileentity.TileEntityRFLoader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Skylar on 5/27/2015.
 */
public class BlockRFLoader extends BlockContainer {

	@SideOnly(Side.CLIENT)
	private IIcon[] textures = new IIcon[4];

	public BlockRFLoader() {
		super(Material.iron);
		setHardness(1.0F);
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List subItems) {
		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iIconRegister) {
		getTextures()[0] = iIconRegister.registerIcon("enderio:machineSide");
		getTextures()[1] = iIconRegister.registerIcon("enderio:machineTop");
		getTextures()[2] = iIconRegister.registerIcon("enderio:capacitorBankInput");
		getTextures()[3] = iIconRegister.registerIcon("enderio:capacitorBankOutput");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
									EntityPlayer player, int metadata, float what, float these, float are) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		player.openGui(ExtraCarts.instance, 0, world, x, y, z);
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide) {
		int metadata = world.getBlockMetadata(x, y, z);
		return getIcon(blockSide, metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		switch (side) {
			case 0:
				if (metadata == 0) {
					return getTextures()[3];
				} else {
					return getTextures()[0];
				}
			case 1:
				if (metadata == 0) {
					return getTextures()[1];
				} else {
					return getTextures()[2];
				}
			default:
				return getTextures()[0];
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityRFLoader();
	}

	public IIcon[] getTextures() {
		return textures;
	}

	public void setTextures(IIcon[] textures) {
		this.textures = textures;
	}
}
