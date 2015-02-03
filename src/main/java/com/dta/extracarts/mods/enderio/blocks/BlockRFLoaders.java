package com.dta.extracarts.mods.enderio.blocks;

import com.dta.extracarts.ExtraCarts;
import com.dta.extracarts.client.OpenableGUI;
import com.dta.extracarts.mods.enderio.client.ContainerRFLoaders;
import com.dta.extracarts.mods.enderio.client.GuiRFLoaders;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Skylar on 11/9/2014.
 */
public class BlockRFLoaders extends BlockContainer implements OpenableGUI {
	private int direction = 1;
	private IIcon[] textures = new IIcon[3];

	public BlockRFLoaders() {
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
		getTextures()[2] = iIconRegister.registerIcon("enderio:stirlingGenFrontOff");
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
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		int direction = BlockPistonBase.determineOrientation(par1World, par2, par3, par4, par5EntityLivingBase);
		this.setDirection(direction);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int blockSide) {
		TileEntity te = world.getTileEntity(x, y, z);
		if(te instanceof TileEntityRFLoaders) {
			TileEntityRFLoaders tileEntityRFLoaders = new TileEntityRFLoaders();
			this.setDirection(tileEntityRFLoaders.getDirection());
		}
		return getIcon(blockSide, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		if (this.getDirection() == side)
			return getTextures()[1];
		if (side != 0 && side != 1)
			return getTextures()[0];
		return getTextures()[2];
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		if(te instanceof TileEntityRFLoaders) {
			return new ContainerRFLoaders(player.inventory, (TileEntityRFLoaders) te);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		if(te instanceof TileEntityRFLoaders) {
			return new GuiRFLoaders(player.inventory, (TileEntityRFLoaders) te);
		}
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityRFLoaders();
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public IIcon[] getTextures() {
		return textures;
	}

	public void setTextures(IIcon[] textures) {
		this.textures = textures;
	}
}
