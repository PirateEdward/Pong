package e.graphics.gui;

import e.graphics.SpriteSheet;
import e.graphics.Texture;

public class FontType {
	
	public static final char[] chars = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
		'U', 'V', 'W', 'X', 'Y', 'Z','1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'!', '.', '?', '@', '#', '$', '%', '^', '&', '(', ')', '[', ']', '{', '}', '\\', '/', '|', '<', '>', ':', ';', ',', '\'', '*', 
		'+', '-', '=', ' '};
	
	public Texture[] textures;

	public FontType(SpriteSheet sheet) {
		textures = new Texture[sheet.Width / sheet.tWidth];
		for(int i = 0; i < textures.length; i++){
			textures[i] = new Texture(sheet, i, 0);
		}
	}
	
	public Texture getTexture(char a){
		return textures[getID(a)];
	}
	
	public Texture getTexture(int i){
		return textures[i];
	}
	
	private int getID(char a){
		for(int i = 0; i < chars.length; i++){
			if(a == chars[i])
				return i;
		}
		return chars.length;
	}

}
