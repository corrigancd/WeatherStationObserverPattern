package presentation;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShowImage extends Panel {
    private static final long serialVersionUID = 1L;
    // Constructor
    public ShowImage() {}  
    public void ShowImageOnScreen(String filename, String fcast, 
    							  double cpres, double lpres, 
    							  double ctemp, double chumd) {    	
        try {
        	// Calculate pressure difference.
        	double pdif = lpres-cpres; 	
        	// Set display string from data parameters.
        	String fcastString  = "<html>Pressure Change: "+pdif+" bar<br/>Forecast: " + fcast+"</html>";
        	String curConditions = "<html>Temp: " + ctemp + " F<br/>Humidity: "+ chumd+" %</html>";
        	
        	// Create JLabels for display strings.
        	JLabel forecast = new JLabel(fcastString);
        	JLabel current  = new JLabel(curConditions);
        	Font font = new Font("SansSerif", 10, 30);
        	
        	// Read image, create JFrame and set layout to Box.
        	BufferedImage image = ImageIO.read(getClass().getResource(filename));
            JFrame frame = new JFrame("Weather Widget"); //a graphical window for the image
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
            
            // Create JFrame image.
            ImageIcon img = new ImageIcon(image);
            // Add image to the frame
            frame.getContentPane().add(new JLabel(img)); 
            // Set dimensions.
            Dimension textSize = new Dimension(250, 80);
            Dimension framesize = new Dimension(img.getIconWidth(), 
            		(int) (img.getIconHeight()+textSize.getHeight()+150));
            
            // Set font and test sizes.
            forecast.setPreferredSize( textSize );            
            forecast.setFont(font);
            current.setPreferredSize( textSize );            
            current.setFont(font);
            // Add JLabels to JFrame.
            frame.add(forecast);
            frame.add(current);
            // Set frame parameters.
            frame.setSize(framesize); //setting size of frame
            frame.setVisible(true);  
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stops script when window is x'd
            
        } catch (IOException ie) {
        	ie.printStackTrace();
        }        
    }
}


