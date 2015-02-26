package edu.eci.arsw.lab;

import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.swing.*;


import java.awt.Graphics;
 
/**
 * RMI LAB
 * This is the codebase of a RMI exercise. The objective is to implement a client/server remote desktop
 * viewer using RMI technology. 
 * @author hector.cadavid@escuelaing.edu.co
 *
 */
public class ScreenViewer{
 
	static Boolean stopAndExit=false;
	
	public static void main(String[] args) throws java.io.IOException, AWTException, InterruptedException, ScreenCaptureException {
		 
		
		JFrame canvas = new JFrame();				
		canvas.setLayout(new BorderLayout());
		
		canvas.setSize(1280,800);
		canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.setTitle("Remote client");
		
		Container pane = canvas.getContentPane();
		ColorPanel panel = new ColorPanel();
				
		ScreenCapturer sc=new ScreenCapturer();
		
		byte[] screenshotBytes=sc.getScreenShot();		
		InputStream in = new ByteArrayInputStream(screenshotBytes);
		BufferedImage screenshot = ImageIO.read(in);
		
		panel.setScreenShot(screenshot);
		pane.add(panel, BorderLayout.CENTER);
		
		JButton activationButton=new JButton("Stop and exit");
		
		activationButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						stopAndExit=true;
						
					}
				}
		);
		
		pane.add(activationButton,BorderLayout.NORTH);
		
		canvas.setVisible(true);
                
		while(!stopAndExit){

			screenshotBytes=sc.getScreenShot();		
			in = new ByteArrayInputStream(screenshotBytes);
			screenshot = ImageIO.read(in);
	        panel.setScreenShot(screenshot);
			panel.repaint();
			Thread.sleep(1000);
		}
		
		System.exit(0);
		
	}
}
 
class ColorPanel extends JComponent{
	BufferedImage screenShot;
	
	
	public void setScreenShot(BufferedImage screenShot) {
		this.screenShot = screenShot;
	}

	public ColorPanel(){

	}
 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(screenShot, null, 0,0);
	}
}
