package e.helpers;

import java.io.IOException;
import java.util.HashMap;

import e.io.KeyValueInputStream;

public class ValueHolder {
	
	private HashMap<String, String[]> values;
	
	public ValueHolder(String path) throws IOException{
		values = new HashMap<String, String[]>();
		load(path);
	}
	
	public void load(String path) throws IOException{
		KeyValueInputStream input = new KeyValueInputStream(path, this);
		input.load();
		input.close();
	}
	
	public void add(String name, String[] value){
		values.put(name, value);
	}
	
	public void add(String name, String value){
		String[] a = {value};
		values.put(name, a);
	}
	
	public String getSingleString(String name){
		return values.get(name)[0];
	}
	
	public String[] getArrayString(String name){
		return values.get(name);
	}
	
	public byte getSingleByte(String name) {
		return Byte.parseByte(values.get(name)[0]);
	}
	
	public byte[] getArrayByte(String name){
		String[] sa = values.get(name);
		byte[] nrs = new byte[sa.length];
		for(int i = 0; i < nrs.length; i++){
			nrs[i] = Byte.parseByte(sa[i]);
		}
		return nrs;
	}
	
	public int getSingleInt(String name){
		 return Integer.parseInt(values.get(name)[0]);
	}
	
	public int[] getArrayInt(String name){
		String[] sa = values.get(name);
		int[] nrs = new int[sa.length];
		for(int i = 0; i < nrs.length; i++){
			nrs[i] = Integer.parseInt(sa[i]);
		}
		return nrs;
	}
	
	public float getSingleFloat(String name){
		return Float.parseFloat(values.get(name)[0]);
	}
	
	public float[] getArrayFloat(String name){
		String[] sa = values.get(name);
		float[] nrs = new float[sa.length];
		for(int i = 0; i < nrs.length; i++){
			nrs[i] = Float.parseFloat(sa[i]);
		}
		return nrs;
	}
	
	public boolean getSingleBoolean(String name){
		String n = values.get(name)[0];
		if(n.equals("true") || n.equals("1")) return true;
		else return false;
	}

}
