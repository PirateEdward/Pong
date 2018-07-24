package e.graphics;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import e.io.Loader;

public class Texture {
	
	private int Width, Height;
	private int texture;
	
	public Texture(SpriteSheet sheet, int x, int y){
		Width = sheet.tWidth;
		Height = sheet.tHeight;
		texture = genID(sheet.getTexPixels(x, y));
	}
	
	public Texture(String path){
		texture = load(path);
	}
	
	private int load(String path){
		int[] pixels = null;
		
		BufferedImage image = Loader.loadImage(path);
		Width = image.getWidth();
		Height = image.getHeight();
		pixels = new int[Width * Height];
		image.getRGB(0, 0, Width, Height, pixels, 0, Width);
		
		return genID(pixels);
	}
	
	public int genID(int[] pixels){
		int[] data = new int[Width * Height];
		for(int i = 0; i < Width * Height; i++){
			int r = (pixels[i] & 0xff000000) >> 24;
			int g = (pixels[i] & 0xff0000) >> 16;
			int b = (pixels[i] & 0xff00) >> 8;
			int a = (pixels[i] & 0xff);
			data[i] = a << 24 | b << 16 | g << 8 | r;
		}
		
		int result = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, result);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data).flip();
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, Width, Height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		glBindTexture(GL_TEXTURE_2D, 0);
		return result;
	}
	
	public void bind(){
		glBindTexture(GL_TEXTURE_2D, texture);
	}
	
	public void unbind(){
		glBindTexture(GL_TEXTURE_2D, 0);
	}

}
