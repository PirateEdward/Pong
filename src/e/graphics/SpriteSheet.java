package e.graphics;

import java.awt.image.BufferedImage;

import e.io.Loader;

public class SpriteSheet {
	
	public int Width, Height, tWidth, tHeight;
	public int[] pixels;

	public SpriteSheet(String path, int tWidth, int tHeight) {
		BufferedImage image = Loader.loadImage(path);
		Width = image.getWidth();
		Height = image.getHeight();
		this.tWidth = tWidth;
		this.tHeight = tHeight;
		pixels = new int[Width * Height];
		image.getRGB(0, 0, Width, Height, pixels, 0, Width);
	}
	
	public int[] getTexPixels(int xs, int ys){
		xs *= tWidth;
		ys *= tHeight;
		int[] p = new int[tWidth * tHeight];
		for(int y = 0; y < tHeight; y++) {
			for(int x = 0; x < tWidth; x++) {
				p[x + y * tWidth] = pixels[x + xs + (y + ys) * Width];
			}
		}
		return p;
	}

}
