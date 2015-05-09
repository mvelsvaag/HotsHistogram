package hotspot;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

class ImageRobot {
  public static void main(String args[]) throws
           AWTException, IOException {
     // capture the whole screen
	 //System.in.read();
	 try {
		Thread.sleep(5000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
	 
	 
	 
	 BufferedImage screencapture = new Robot().createScreenCapture(
           new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) );
     ColorModel scColorModel = screencapture.getColorModel();
     try {
		Thread.sleep(1);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
     BufferedImage screencapture2 = new Robot().createScreenCapture(
             new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) );
     int count = 0;
     
     Histogram hist = new Histogram(screencapture);
     hist.createHistogram(16);
     for(String k: hist.getDaBins().keySet()) {
    	 System.out.println(k);
    	 for(Object i: hist.getDaBins().get(k).toArray()) {
    		 System.out.println(i);
    	 }
     }
     
     BufferedImage difference = new BufferedImage(screencapture2.getWidth(),screencapture2.getHeight(),screencapture2.getType());
   
     
     for(int i = 0; i < screencapture.getWidth(); i++) {
    	 for(int j = 0; j < screencapture.getHeight(); j++) {
    		 int d = screencapture.getRGB(i, j) - screencapture2.getRGB(i, j);
    		 if(d != 0) {
    			 difference.setRGB(i,j,screencapture2.getRGB(i,j));
    			 //System.out.println(d+" (" +i+","+j+")");
    			 count++;
    		 }
    	 }
     }
     File outputFile = new File("image.png");
     ImageIO.write(screencapture, "png", outputFile);
     System.out.println(count);
     System.out.println(screencapture.getWidth()*screencapture.getHeight());
     System.exit(0);
  }
}