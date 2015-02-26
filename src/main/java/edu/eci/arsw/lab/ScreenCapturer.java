package edu.eci.arsw.lab;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * RMI LAB
 * This is the codebase of a RMI exercise. The objective is to implement a client/server remote desktop
 * viewer using RMI technology. 
 * @author hector.cadavid@escuelaing.edu.co
 *
 */
public class ScreenCapturer {

	public byte[] getScreenShot() throws ScreenCaptureException {
		
		try{
			Robot robot = new Robot();
	        Rectangle captureSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());		
	
	        BufferedImage screenshot = robot.createScreenCapture(captureSize);
	
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write( screenshot, "jpg", baos );
	        baos.flush();
	        byte[] imageInByte = baos.toByteArray();
	        baos.close();
	        
	        return imageInByte;
		} catch(AWTException e){
			throw new ScreenCaptureException("",e);
		} catch (IOException e) {
			throw new ScreenCaptureException("",e);
		}
		
	}
	
	
}
