package e.math;

import static java.lang.Math.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class Matrix4f {
	
	public static final int SIZE = 4;
	
	public static Matrix4f identity(){
		Matrix4f result = new Matrix4f();
		for(int y = 0; y < SIZE; y++){
			for(int x = 0; x < SIZE; x++){
				if(x != y) result.matrix[x + y * SIZE] = 0.0f;
				else result.matrix[x + y * SIZE] = 1.0f;
			}
		}
		return result;
	}
	
	public static Matrix4f translate(Vector3f vector){
		Matrix4f result = identity();
		
		result.matrix[0 + 3 * SIZE] = vector.x;
		result.matrix[1 + 3 * SIZE] = vector.y;
		result.matrix[2 + 3 * SIZE] = vector.z;
		
		return result;
	}
	
	public static Matrix4f rotate(float angle){
		Matrix4f result = identity();
		float r = (float) toRadians(angle);
		float cos = (float) cos(r);
		float sin = (float) sin(r);
		
		result.matrix[0 + 0 * SIZE] = cos;
		result.matrix[1 + 0 * SIZE] = sin;
		
		result.matrix[0 + 1 * SIZE] = -sin;
		result.matrix[1 + 1 * SIZE] = cos;
		
		return result;
	}
	
	public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far){
		Matrix4f result = identity();
		
		result.matrix[0 + 0 * SIZE] = 2.0f / (right - left);
		
		result.matrix[1 + 1 * SIZE] = 2.0f / (top - bottom);
		
		result.matrix[2 + 2 * SIZE] = 2.0f / (near - far);
		
		result.matrix[0 + 3 * SIZE] = (left + right) / (left - right);
		result.matrix[1 + 3 * SIZE] = (bottom + top) / (bottom - top);
		result.matrix[2 + 3 * SIZE] = (far + near) / (far - near);
		
		return result;
	}
	
	////////////////////////////////
	
	public float[] matrix;

	public Matrix4f() {
		matrix = new float[SIZE * SIZE];
	}
	
	public Matrix4f multiply(Matrix4f other){
		Matrix4f result = new Matrix4f();
		for(int y = 0; y < SIZE; y++){
			for(int x = 0; x < SIZE; x++){
				float sum = 0.0f;
				for(int i = 0; i < SIZE; i++) {
					sum += this.matrix[x + i * SIZE] * other.matrix[i + y * SIZE];
				}
				result.matrix[x + y * SIZE] = sum;
			}
		}
		return result;
	}
	
	public FloatBuffer toFloatBuffer() {
		FloatBuffer b = BufferUtils.createFloatBuffer(matrix.length);
		b.put(matrix).flip();
		return b;
	}

}
