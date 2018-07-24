package e.graphics;

public class VertexArrayRect extends VertexArray {
	
	private static byte[] indices = {0, 1, 2,
									 2, 3, 0};
	private static float[] tex_coords = {0, 1,
										 0, 0,
										 1, 0,
										 1, 1};
	
	public static float[] genVertices(float width, float height, float z){
		float[] vertices = new float[] {
				-width / 2.0f, -height / 2.0f, z,
				-width / 2.0f,  height / 2.0f, z,
				 width / 2.0f,  height / 2.0f, z,
				 width / 2.0f, -height / 2.0f, z
		};
		return vertices;
	}
	
	public float width, height, z;

	public VertexArrayRect(float width, float height, float z) {
		super(genVertices(width, height, z), indices, tex_coords);
		this.width = width;
		this.height = height;
		this.z = z;
	}

}
