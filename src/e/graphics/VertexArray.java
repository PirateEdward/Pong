package e.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class VertexArray {
	
	private int count;
	private int vao, vbo, ibo, textCoords;
	
	public VertexArray(float[] vertices, byte[] indices, float[] textureCoordinates) {
		count = indices.length;
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		FloatBuffer b = BufferUtils.createFloatBuffer(vertices.length);
		b.put(vertices).flip();
		glBufferData(GL_ARRAY_BUFFER, b, GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(Shader.VERTEX_ATTRIB);
		
		textCoords = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, textCoords);
		b = BufferUtils.createFloatBuffer(vertices.length);
		b.put(textureCoordinates).flip();
		glBufferData(GL_ARRAY_BUFFER, b, GL_STATIC_DRAW);
		glVertexAttribPointer(Shader.TCOORDS_ATTRIB, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(Shader.TCOORDS_ATTRIB);
		
		ibo = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		ByteBuffer buffer = BufferUtils.createByteBuffer(indices.length);
		buffer.put(indices).flip();
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
	
	public void bind(){
		glBindVertexArray(vao);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
	}
	
	public void unbind(){
		glBindVertexArray(0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	public void draw(){
		glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
	}
	
	public void render(){
		bind();
		draw();
	}

}
