package com.creativemd.opf.client;

//import com.creativemd.creativecore.client.rendering.model.CreativeBlockRenderHelper;
import com.creativemd.opf.OPFrame;
import com.creativemd.opf.block.TileEntityPicFrame;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ClientRegistry;
//import net.minecraftforge.fml.client.registry.ClientRegistry;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;

@OnlyIn(Dist.CLIENT)
public class OPFrameClient {

	public static void initClient() {
//		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPicFrame.class, new PicTileRenderer());
//
		// doesn't exist? https://github.com/CreativeMD/CreativeCore/tree/1.18/src/main/java/team/creative/creativecore/client/render/model
//		CreativeBlockRenderHelper.registerCreativeRenderedBlock(OPFrame.frame);
	}
}
