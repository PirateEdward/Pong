package e;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import e.graphics.Shader;
import e.graphics.SpriteSheet;
import e.graphics.gui.FontType;

public class Resources {
	
	public static Random rand;
	
	private static Map<String, Shader> shaders;
	private static Map<String, SpriteSheet> spritesheets;
	private static Map<String, FontType> fonts;
	
	public Resources() {
		rand = new Random();
		
		shaders = new HashMap<String, Shader>();
		spritesheets = new HashMap<String, SpriteSheet>();
		fonts = new HashMap<String, FontType>();
		
		shaders.put("texture", new Shader("res/shaders/with_texture.shader"));
		shaders.get("texture").setUniformMat4f("pr_matrix", Window.pr_matrix);
		
		shaders.put("text", new Shader("res/shaders/text.shader"));
		shaders.get("text").setUniformMat4f("pr_matrix", Window.pr_matrix);
		
		shaders.put("object", new Shader("res/shaders/object.shader"));
		shaders.get("object").setUniformMat4f("pr_matrix", Window.pr_matrix);
		
		shaders.put("random_ball", new Shader("res/shaders/random_ball.shader"));
		shaders.get("random_ball").setUniformMat4f("pr_matrix", Window.pr_matrix);
		
		shaders.put("background", new Shader("res/shaders/background.shader"));
		shaders.get("background").setUniformMat4f("pr_matrix", Window.pr_matrix);
		
		shaders.put("color", new Shader("res/shaders/color.shader"));
		shaders.get("color").setUniformMat4f("pr_matrix", Window.pr_matrix);
		
		
		spritesheets.put("default_font", new SpriteSheet("res/textures/font.png", 8, 10));
		
		
		fonts.put("default_font", new FontType(spritesheets.get("default_font")));
		
	}
	
	public static Shader getShader(String key){
		return shaders.get(key);
	}
	
	public static SpriteSheet getSpriteSheet(String key){
		return spritesheets.get(key);
	}
	
	public static FontType getFont(String key){
		return fonts.get(key);
	}

}
