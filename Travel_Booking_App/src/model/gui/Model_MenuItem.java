
package model.gui;

import view.models.RoImageIcon;
import java.awt.FontMetrics;
import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 *
 * @author rowan
 * for each item in the dashboard menu
 * icon is the name of the file in the view\graphics package (don't add the .png, just name body)
 */
public class Model_MenuItem {
    String icon;
    String name;
    MenuType type;
    String linkAction;
      
    //Constructors
    public Model_MenuItem() { }

/**
 * 
 * @param icon : the name of the file in view\graphics\ to reference. Do not include the .png
 * @param name : how this will appear in the menu
 * @param type : Controls how rendering works (theoretically) Not implemented. Use MENU for now
 * @param linkAction : the name of the function this item will call - which will call the appropriate page w filters etc. Do not include the ()
 */
    public Model_MenuItem(String icon, String name, MenuType type, String linkAction) {
        this.icon = icon;
        this.name = name;
        this.type = type;
        this.linkAction = linkAction;
    }
    
    //Getters & Setters

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public MenuType getType() { return type; }
    public void setType(MenuType type) { this.type = type; }
    
    public String getLinkAction() { return linkAction; }  
    public void setLinkAction(String linkAction) { this.linkAction = linkAction; }

    // Utility    
    
    public static enum MenuType { HEADER, MENU, EMPTY, SEPARATOR }
    
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
