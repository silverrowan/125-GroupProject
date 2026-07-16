package view.models;

import java.awt.FontMetrics;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Mariah Malczewska
 */
public class Model_lblScales extends JLabel {
    public Icon toIcon(String icon) {
        return new ImageIcon(getClass().getResource("/view/graphics/" + icon + ".png"));
    }
    
    /**
    *
    * resizes icon to height x ? PIXELS; obeys original aspect ratio
    * SCALE_SMOOTH -> better image quality
    * SCALE_AREA_AVERAGING -> sharper image, may be better for small icons
    */
    public Icon scaleIconHeight(int height, String icon) {
        ImageIcon ogIcon = (ImageIcon) toIcon(icon);
        
        int ogHeight = ogIcon.getIconHeight();
        int ogWidth = ogIcon.getIconWidth();
        // not using aspect ratio so that less affected by int shortening
        int finalWidth = ( height * ogWidth ) / ogHeight;
//        double aspectRatio = (double) width / height;
        
        Image scaled = ogIcon.getImage().getScaledInstance( finalWidth, height, java.awt.Image.SCALE_AREA_AVERAGING);
        
        return new ImageIcon(scaled);
    }
    
    /**
    *
    * finds font size
    * input is the label with the relevant text
    */
    public int getFontHeight(JLabel label) {
        FontMetrics fontMetrics = label.getFontMetrics(label.getFont());      
        return fontMetrics.getHeight();
    }
   
    /**
    *
    * resizes icon to match font size; does NOT obey aspect ratio
    * input is the label with the relevant text
    */
    public Icon scaleIconByText(JLabel label, String icon) {
        ImageIcon ogIcon = (ImageIcon) toIcon(icon);
        int textSize = getFontHeight(label);
        
        ImageIcon scaled = (ImageIcon) scaleIconHeight(textSize, icon);
        
        return scaled;
    }
}
