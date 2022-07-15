package com.creativemd.opf.client;


import com.mojang.blaze3d.platform.GlStateManager;

public abstract class PictureTexture {
	
	public int width;
	public int height;
	
	public PictureTexture(int width, int height) {
		this.width = width;
		this.height = height;
		
	}
	
	public void beforeRender() {
		
	}
	
	public abstract void tick();
	
	public abstract int getTextureID();
	
	public boolean hasTexture() {
		return getTextureID() != -1;
	}
	
	public void release() {
		GlStateManager._deleteTexture(getTextureID());
	}
	
}
