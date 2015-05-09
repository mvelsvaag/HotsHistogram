package hotspot;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

class Histogram {
	protected BufferedImage bImage;
	protected HashMap<String,ArrayList<Integer>> daBins;
	protected int[] rBins;
	protected int[] gBins;
	protected int[] bBins;
	public Histogram(BufferedImage bi) {
		bImage = bi;
		daBins = new HashMap<String,ArrayList<Integer>>();
		
	}
	
	public void createHistogram(int numBins) {
		rBins = new int[numBins];
		gBins = new int[numBins];
		bBins = new int[numBins];
		daBins.put("red", new ArrayList<Integer>(numBins));
		daBins.put("green", new ArrayList<Integer>(numBins));
		daBins.put("blue", new ArrayList<Integer>(numBins));
		for(int i = 0; i < numBins; i++) {
			daBins.get("red").add(0);
			daBins.get("green").add(0);
			daBins.get("blue").add(0);
		}
		
		for(int i = 0; i < bImage.getWidth(); i++) {
	    	 for(int j = 0; j < bImage.getHeight(); j++) {
	    		Color c = new Color(bImage.getRGB(i, j)); 
	    		daBins.get("red").set(c.getRed()/numBins,daBins.get("red").get(c.getRed()/numBins) + 1);
	    		daBins.get("green").set(c.getGreen()/numBins,daBins.get("green").get(c.getGreen()/numBins) + 1);
	    		daBins.get("blue").set(c.getBlue()/numBins,daBins.get("blue").get(c.getBlue()/numBins) + 1);
	    	 }
		}
	}
	
	public HashMap<String,ArrayList<Integer>> getDaBins() {
		
		return daBins;
	}
}