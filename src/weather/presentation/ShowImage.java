package weather.presentation;

import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShowImage extends Panel {
    private static final long serialVersionUID = 1L;

    public ShowImage() {}
    
    public void ShowImageOnScreen(String filename) {    	
        try {
        	BufferedImage image = ImageIO.read(getClass().getResource(filename));
            JFrame frame = new JFrame("Weather Widget"); //a graphical window for the image
            frame.getContentPane().add(new JLabel(new ImageIcon(image))); // adding the image to the frame
            frame.setSize(1200,900); //setting size of frame
            frame.setVisible(true);  
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stops script when window is x'd
            
        } catch (IOException ie) {
        	ie.printStackTrace();
        }        
    }
}


