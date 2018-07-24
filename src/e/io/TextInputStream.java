package e.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TextInputStream extends FileInputStream {
	
	protected char d;
	
	public TextInputStream(String path) throws FileNotFoundException{
		super(path);
	}
	
	public TextInputStream(File file) throws FileNotFoundException {
		super(file);
	}
	
	public int readArraySize() throws IOException{
		if(d == '[')
			return readInt();
		else return 1;
	}
	
	public int[] readIntArray(int size) throws IOException{
		while(d != '{'){
			d = readChar();
		}
		int[] a = new int[size];
		for(int i = 0; i < size; i++){
			a[i] = readInt();
		}
		return a;
	}
	
	public double[] readDoubleArray(int size) throws IOException{
		while(d != '{'){
			d = readChar();
		}
		double[] a = new double[size];
		for(int i = 0; i < size; i++){
			a[i] = readDouble();
		}
		return a;
	}
	
	public float[] readFloatArray(int size) throws IOException{
		while(d != '{'){
			d = readChar();
		}
		float[] a = new float[size];
		for(int i = 0; i < size; i++){
			a[i] = readFloat();
		}
		return a;
	}
	
	public float readFloat() throws IOException{
		return (float) readDouble();
	}

	public double readDouble() throws IOException{
		double nr = readInt();
		if(d == '.'){
			double div = 10;
			d = readChar();
			while(Character.isDigit(d)){
				nr += (d - 48) / div;
				div *= 10;
				d = readChar();
			}
		}
		return nr;
	}
	
	public int readInt() throws IOException{
		while(!Character.isDigit(d) && d!='-'){
			d = readChar();
		}
		char a = d;
		if(a == '-') d = readChar();
		int nr = 0;
		while(Character.isDigit(d)){
			d-=48;
			nr += d;
			nr *= 10;
			d = readChar();
		}
		nr/=10;
		if(a == '-') nr -= 2 * nr;
		return nr;
	}
	
	public String readString() throws IOException{
		String a = "";
		d = readChar();
		do{
			a+=d;
			d = readChar();
		}while(d != '"');
		return a;
	}
	
	public String readNextString() throws IOException{
		do{
			d = readChar();
		}while(d != '"');
		String a = readString();
		return a;
	}
	
	public String[] readStringArray(int size) throws IOException{
		while(d != '{'){
			d = readChar();
		}
		String[] a = new String[size];
		for(int i = 0; i < size; i++){
			a[i] = readNextString();
		}
		return a;
	}
	
	public char readChar() throws IOException{
		return (char) read();
	}
	
	public int readDigit() throws IOException{
		return read() - 48;
	}

}
