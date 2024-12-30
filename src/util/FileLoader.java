package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileLoader {
	public static BufferedImage find(String region){
		try{
			File source = new File(region);
			return ImageIO.read(source);
		} catch (Exception e){
			System.out.println("error: " + e.getMessage() + " Arg: " + region);
		}
		return null;
	}
}
