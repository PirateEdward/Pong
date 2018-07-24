package e.io;

import java.io.FileNotFoundException;
import java.io.IOException;

import e.helpers.ValueHolder;

public class KeyValueInputStream extends TextInputStream {
	
	public ValueHolder valueHolder;

	public KeyValueInputStream(String path, ValueHolder valueHolder) throws FileNotFoundException {
		super(path);
		this.valueHolder = valueHolder;
	}
	
	public void load() throws IOException{
		findStart();
		do{
			d = readChar();
			if(d == '$'){
				String name = readVariableName();
				int size = readArraySize();
				if(size == 1){
					String value = readNextString();
					valueHolder.add(name, value);
				}
				else {
					String[] value = readStringArray(size);
					valueHolder.add(name, value);
				}
				endLine();
			}
		}while(d != '}');
	}
	
	public char readFirstChar() throws IOException{
		do{
			d = readChar();
		}while(Character.isWhitespace(d));
		return d;
	}
	
	public void endLine() throws IOException{
		do{
			d = readChar();
		}while(d != ';');
	}
	
	public void findStart() throws IOException{
		char d;
		do{
			d = readChar();
		}while(d != '{');
	}
	
	public String readVariableName() throws IOException {
		String a = "";
		d = readChar();
		while(!Character.isWhitespace(d) && (Character.isDigit(d) || Character.isAlphabetic(d) || d == '_')){
			a+=d;
			d = readChar();
		}
		return a;
	}

}
