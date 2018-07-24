package e.graphics.gui;

import e.graphics.Shader;
import e.graphics.Texture;
import e.graphics.VertexArrayRect;
import e.math.Matrix4f;
import e.math.Vector3f;

public class Text {
	
	public VertexArrayRect mesh;
	public String text;
	public Font font;
	public Texture[] textures;
	
	public Text(String text, Font font) {
		this.font = font;
		setText(text);
	}
	
	public void setText(String text) {
		this.text = text;
		mesh = new VertexArrayRect(0.8f * font.size, font.size, 0.0f);
		textures = new Texture[text.length()];
		for(int i = 0; i < textures.length; i++){
			textures[i] = font.type.getTexture(text.charAt(i));
		}
	}
	
	public void render(Shader shader, Vector3f position){
		shader.enable();
		shader.setUniform1i("tex", 1);
		shader.setColorUniform("color_in", font.color);
		mesh.bind();
		for(int i = 0; i < textures.length; i++){
			shader.setUniformMat4f("ml_matrix", Matrix4f.translate(position.addBack(new Vector3f((i - textures.length / 2) * font.size, 0.0f, 0.0f))));
			textures[i].bind();
			mesh.render();
		}
		shader.disable();
	}

}
