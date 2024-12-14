package ua.pp.shurgent.tfctech.blocks.terrain;

import java.util.ArrayList;
import java.util.Random;

import com.bioxx.tfc.TileEntities.TEOre;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import ua.pp.shurgent.tfctech.Globals;
import ua.pp.shurgent.tfctech.core.ModDetails;
import ua.pp.shurgent.tfctech.core.ModItems;
import ua.pp.shurgent.tfctech.tileentities.TEModOre;

import com.bioxx.tfc.Blocks.Terrain.BlockOre;
import com.bioxx.tfc.Core.TFC_Core;

public class BlockModOre extends BlockOre {

	public String[] blockNames = Globals.ORE_METAL;

	public BlockModOre(Material mat) {
		super(mat);

	}
	public ItemStack getDrop(World world, int x, int y, int z, int fortune) {
		TEOre te = (TEOre)world.getTileEntity(x, y, z);
		int ore = te.droppedOreID;
		ItemStack itemstack = new ItemStack(ModItems.oreChunk, 1, this.damageDropped(ore));
		itemstack.stackSize = this.quantityDropped(ore, fortune, world.rand);
		return itemstack;
	}

	@Override
	public boolean isOreGradable(int meta) {
		return false;
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (meta >= icons.length)
			return icons[0];
		return icons[meta];
	}

	protected IIcon[] icons = new IIcon[blockNames.length];

	@Override
	public void registerBlockIcons(IIconRegister iconRegisterer) {
		for (int i = 0; i < blockNames.length; i++)
			icons[i] = iconRegisterer.registerIcon(ModDetails.ModID + ":" + "ores/" + blockNames[i] + " Ore");
	}

	public static Item getDroppedItem(int meta) {
		return ModItems.smallOreChunk;
	}



	@Override
	public TileEntity createTileEntity(World w, int meta) {
		return new TEModOre();
	}

}
