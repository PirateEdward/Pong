package e.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.util.HashMap;
import java.util.Map;

import e.graphics.gui.Color;
import e.io.Loader;
import e.math.Matrix4f;
import e.math.Vector3f;

public class Shader {
	
	public static final int VERTEX_ATTRIB = 0;
	public static final int TCOORDS_ATTRIB = 1;
	
	private final int ID;
	private Map<String, Integer> locationCache;

	public Shader(String path) {
		locationCache = new HashMap<String, Integer>();
		ID = load(path);
	}
	
	public int getUniform(String name){
		if(locationCache.containsKey(name))
			return locationCache.get(name);
		int u = glGetUniformLocation(ID, name);
		if(u == -1) 
			System.err.println("Could not find uniform variable '" + name + "'!");
		else
			locationCache.put(name, u);
		return u;
	}
	
	public void setUniform1i(String name, int value){
		glUniform1i(getUniform(name), value);
	}
	
	public void setUniform1f(String name, float value){
		glUniform1f(getUniform(name), value);
	}
	
	public void setUniform2f(String name, float v1, float v2){
		glUniform2f(getUniform(name), v1, v2);
	}
	
	public void setUniform3f(String name, Vector3f vector){
		glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
	}
	
	public void setColorUniform(String name, Color color){
		glUniform4f(getUniform(name), color.r, color.g, color.b, color.a);
	}
	
	public void setUniformMat4f(String name, Matrix4f matrix){
		enable();
		glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
	}
	
	public void enable(){
		glUseProgram(ID);
	}
	
	public void disable(){
		glUseProgram(0);
	}
	
	////////////////////////////////////
	
	public static int load(String path){
		String vertex = Loader.loadVertex(path);
		String fragment = Loader.loadFragment(path);
		return create(vertex, fragment);
	}

	public static int create(String vertex, String fragment){
		int program = glCreateProgram();
		int vertexID = glCreateShader(GL_VERTEX_SHADER);
		int fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(vertexID, vertex);
		glShaderSource(fragmentID, fragment);
		
		glCompileShader(vertexID);
		if(glGetShaderi(vertexID, GL_COMPILE_STATUS) == GL_FALSE){
			System.err.println("Failed to compile vertex shader!");
			System.err.println(glGetShaderInfoLog(vertexID));
			return -1;
		}
		
		glCompileShader(fragmentID);
		if(glGetShaderi(fragmentID, GL_COMPILE_STATUS) == GL_FALSE){
			System.err.println("Failed to compile fragment shader!");
			System.err.println(glGetShaderInfoLog(fragmentID));
			return -1;
		}
		
		glAttachShader(program, vertexID);
		glAttachShader(program, fragmentID);
		glLinkProgram(program);
		glValidateProgram(program);
		
		glDeleteShader(vertexID);
		glDeleteShader(fragmentID);
		
		return program;
	}

}
