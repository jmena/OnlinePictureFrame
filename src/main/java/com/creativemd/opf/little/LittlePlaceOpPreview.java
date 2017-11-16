package com.creativemd.opf.little;

import com.creativemd.creativecore.common.utils.ColorUtils;
import com.creativemd.creativecore.common.utils.ColoredCube;
import com.creativemd.creativecore.common.utils.CubeObject;
import com.creativemd.creativecore.common.utils.RotationUtils;
import com.creativemd.littletiles.client.tiles.LittleRenderingCube;
import com.creativemd.littletiles.common.structure.LittleStructure;
import com.creativemd.littletiles.common.tileentity.TileEntityLittleTiles;
import com.creativemd.littletiles.common.tiles.LittleTile;
import com.creativemd.littletiles.common.tiles.place.PlacePreviewTile;
import com.creativemd.littletiles.common.tiles.preview.LittleTilePreview;
import com.creativemd.littletiles.common.tiles.vec.LittleTileBox;
import com.creativemd.opf.block.TileEntityPicFrame;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class LittlePlaceOpPreview extends PlacePreviewTile {

	public LittlePlaceOpPreview(LittleTileBox box, LittleTilePreview preview) {
		super(box, preview);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public List<LittleRenderingCube> getPreviews()
	{
		NBTTagCompound nbt = preview.getTileData();
		List<LittleRenderingCube> cubes = new ArrayList<LittleRenderingCube>();
		cubes.add(box.getRenderingCube(null, 0));
		if(!nbt.getBoolean("fresh"))
		{
			TileEntityPicFrame tileEntity = (TileEntityPicFrame) TileEntity.create(Minecraft.getMinecraft().world, nbt.getCompoundTag("tileEntity"));
			CubeObject picPreview = LittleOpFrame.getBoundingBoxByTilenEntity(tileEntity, nbt.getInteger("meta"));
			picPreview.add(box.getMinVec().getVec());
			LittleRenderingCube renderingCube = box.getRenderingCube(picPreview, null, 0);
			renderingCube.color = ColorUtils.VecToInt(new Vec3d(0, 1, 1));
			cubes.add(renderingCube);
		}
		
		
		return cubes;
	}
	
	@Override
	public List<LittleTile> placeTile(EntityPlayer player, ItemStack stack, BlockPos pos, TileEntityLittleTiles teLT, LittleStructure structure, ArrayList<LittleTile> unplaceableTiles, boolean forced, EnumFacing facing, boolean requiresCollisionTest)
	{
		List<LittleTile> tiles = super.placeTile(player, stack, pos, teLT, structure, unplaceableTiles, forced, facing, requiresCollisionTest);
		LittleTile tile = tiles.size() > 0 ? tiles.get(0) : null;
		if(tile instanceof LittleOpFrame && preview.getTileData().getBoolean("fresh"))
		{
			((LittleOpFrame) tile).setMeta(facing.getIndex());
			ReflectionHelper.setPrivateValue(TileEntity.class, ((LittleOpFrame) tile).getTileEntity(), ((LittleOpFrame) tile).getMeta(), "blockMetadata", "field_145847_g");
		}
		return tiles;
	}
	
	@Override
	public PlacePreviewTile copy()
	{
		return new LittlePlaceOpPreview(box.copy(), preview.copy());
	}

}
