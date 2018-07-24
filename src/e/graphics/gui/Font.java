package e.graphics.gui;

import e.Resources;

public class Font {
	
	public FontType type;
	public float size;
	public Color color;
	
	public Font(String fontType, float size, Color color){
		type = Resources.getFont(fontType);
		this.size = size;
		this.color = color;
	}

}
