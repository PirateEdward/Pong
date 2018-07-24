package e.io;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
	private Loader(){}

	public static String loadAsString(String path) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String buffer = "";
			while((buffer = reader.readLine()) != null){
				result.append(buffer + '\n');
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	public static String loadFragment(String path){
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String buffer = "";
			while(buffer != null && !buffer.equals("#fragment")){
				buffer = reader.readLine();
			}
			while((buffer = reader.readLine()) != null && !buffer.equals("#vertex")){
				result.append(buffer + '\n');
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	public static String loadVertex(String path){
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String buffer = "";
			while(buffer != null && !buffer.equals("#vertex")){
				buffer = reader.readLine();
			}
			while((buffer = reader.readLine()) != null && !buffer.equals("#fragment")){
				result.append(buffer + '\n');
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return result.toString();
	}
	
	public static BufferedImage loadImage(String path){
		BufferedImage image = null;
		try {
			image = ImageIO.read(new FileInputStream(path));
		}catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

}
