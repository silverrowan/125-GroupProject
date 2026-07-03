
package model.gui;

import java.awt.FontMetrics;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import model.gui.RoImageIcon;

/**
 *
 * @author rowan
 */
public class Model_MenuItem {
    String icon;
    String name;
    MenuType type;
    

    
    //Constructors

    public Model_MenuItem() {
    }

    public Model_MenuItem(String icon, String name, MenuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
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
}
