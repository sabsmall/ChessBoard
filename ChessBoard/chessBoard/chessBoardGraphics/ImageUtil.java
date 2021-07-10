package chessBoardGraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

public class ImageUtil {
	
	static BufferedImage convertToManaged( BufferedImage im ) {
		
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		BufferedImage managedImage = gc.createCompatibleImage(im.getWidth(), im.getHeight(), Transparency.TRANSLUCENT);
	    
	    Graphics tempG = managedImage.getGraphics();
	    // don't know why he got sub image from the unmanaged image instead of just using it
	    //tempG.drawImage(im.getSubimage(0, 0, im.getWidth(), im.getHeight()), 0, 0, null);
	    tempG.drawImage(im, 0, 0, null);
	    tempG.dispose();
	    
	    return managedImage;
	    
	}
	
	static BufferedImage invertImageColors( BufferedImage im  ) {
		
		BufferedImage invertedImage = new BufferedImage(im.getWidth(), im.getHeight(), im.getType());
		
        for (int x = 0; x < im.getWidth(); x++) {
            for (int y = 0; y < im.getHeight(); y++) {
                Color col = new Color(im.getRGB(x, y), true);
                col = new Color(255 - col.getRed(),
                                255 - col.getGreen(),
                                255 - col.getBlue());
                invertedImage.setRGB(x, y, col.getRGB());
            }
        }
        
        return convertToManaged(invertedImage);
        
	}

}
