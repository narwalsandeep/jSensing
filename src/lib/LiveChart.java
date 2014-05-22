/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib;

import com.phidgets.PhidgetException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sensors.SnrIrDistance;

/**
 *
 * @author author
 */
public class LiveChart extends JPanel {
	
	ArrayList<Double> sensorVals, filterVals, sensorNoiseVals;
    Random rnd;
	ParticleFilter pf;
	double sensorXNoise;

	private static LiveChart instance = null;

	/**
	 *
	 */
	public LiveChart(){
		
	}
	
	/**
	 *
	 * @return
	 */
	public static LiveChart getInstance() {
	  if(instance == null) {
		 instance = new LiveChart();
	  }
	  return instance;
	}
	
	public void initJPanel(){
		
		LiveChart.getInstance().initParticle();
		JFrame frame = new JFrame();
		frame.getContentPane().add(LiveChart.getInstance());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 512);
		frame.setVisible(true);
		
	}
	public void initParticle(){
		
		sensorVals = new ArrayList<Double>();
        filterVals = new ArrayList<Double>();
        sensorNoiseVals = new ArrayList<Double>();
		
        /*ParticleFilter(int particles, int minVal, int maxVal, double resampVariance)*/
		pf = new ParticleFilter(100, 0, 600, 30.0);
       // System.out.println("pf value" + pf.toString());
        
		rnd = new Random(System.currentTimeMillis());
        
		for(int i=0; i < 600; i++) {
            sensorVals.add(10.0);
            sensorNoiseVals.add(10.0);
            filterVals.add(10.0);
        }
		
	}

	public double estimate(double currentValue){
		
		sensorXNoise = (double)currentValue + (rnd.nextGaussian() * 100);
		pf.update((double) sensorXNoise);
		sensorVals.remove(0);
		sensorVals.add((double)currentValue);
		sensorNoiseVals.remove(0);
		sensorNoiseVals.add(sensorXNoise);
		filterVals.remove(0);
		double pfValue = pf.estimate();
		filterVals.add(pfValue);
		repaint();

		
		return pfValue;
		//return 
	}

	@Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(150, 0, 0));

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        double xpos =0;
        double xinc = 1;
        double yscale = 0.5;
        for(int i=1; i < sensorVals.size(); i++) {
            ///*
			g2d.setColor(new Color(0, 120, 150));
            int nyval1 = (int)(yscale * sensorVals.get(i-1));
            int nyval2 = (int)(yscale * sensorVals.get(i));
			g2d.drawLine((int)xpos, nyval1, (int)(xpos+xinc), nyval2);
            
			g2d.setColor(new Color(150, 0, 0));
            int yval1 = (int)(yscale * sensorNoiseVals.get(i-1));
            int yval2 = (int)(yscale * sensorNoiseVals.get(i));
			//g2d.drawLine((int)xpos, yval1, (int)(xpos+xinc), yval2);
            
			//g2d.drawOval((int)(xpos+xinc), yval2, 7, 7);

			g2d.setColor(new Color(150, 150, 0));
            int fyval1 = (int)(yscale * filterVals.get(i-1));
            int fyval2 = (int)(yscale * filterVals.get(i));
            g2d.drawLine((int)xpos, fyval1, (int)(xpos+xinc), fyval2);
          		
			xpos += xinc;
        }
    }    

}
