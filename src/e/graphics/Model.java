package e.graphics;

public class Model {
	
	public VertexArray mesh;
	public Texture tex;

	public Model(VertexArray mesh, Texture tex) {
		this.mesh = mesh;
		this.tex = tex;
	}
	
	public Model(VertexArray mesh){
		this.mesh = mesh;
	}
	
	public void render(){
		if(tex != null)
			tex.bind();
		mesh.render();
	}
	
	public void render(Shader shader){
		shader.enable();
		render();
	}

}
