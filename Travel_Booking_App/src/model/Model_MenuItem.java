
package model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

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
    
}
