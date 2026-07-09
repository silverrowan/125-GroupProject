
package model.gui;

import java.awt.FontMetrics;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 *
 * @author rowan
 */
public class Model_Menu {
    String icon;
    String name;
    MenuType type;
    String targetPage;
    

    
    //Constructors

    public Model_Menu() {
    }

    public Model_Menu(String icon, String name, MenuType type, String targetPage) {
        this.icon = icon;
        this.name = name;
        this.type = type;
        this.targetPage = targetPage;
    }
    
    //Getters & Setters

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    // Utility    
    
    public static enum MenuType {
        TITLE, MENU, EMPTY
    } 
    
    public Icon toIcon() {
        return new ImageIcon(getClass().getResource("/view/graphics/" + icon + ".png"));
    }    

    public Icon toRoImageIcon() {
        return new RoImageIcon( getClass().getResource("/view/graphics/" + icon + ".png") );
    }
    
    public int getContentsHeight(JLabel label) {
        FontMetrics cFont = label.getFontMetrics( label.getFont() );
        int cFontHeight = cFont.getHeight();
        
        Icon cIcon = label.getIcon();
        int cIconHeight = cIcon.getIconHeight();
        
        int tallest = Math.max(cIconHeight, cFontHeight);

        return tallest;
    }
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
