/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.gui;

import java.awt.FontMetrics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JComponent;

/**
 *
 * @author rowan
 * Adds a bunch of re-sizing methods to the ImageIcon Class
 * note 'scale' methods maintain original aspect ratio, 'set' methods do not
 */
public class RoImageIcon extends ImageIcon {
    private static String iconLocation = "/view/graphics/"; //set per project
    private static String iconExtension = ".png";
    
    /**
     * 
     * @param iconName - the name of the image, less .extension and folders; 
     * combined with declared image location and extension to form the file path
     * @param altText - short description of icon for accessibility readers
     * 
     * To be used when image is *not* part of the application, else
     * for images provided by the app, use Class getResource method
     */
    public RoImageIcon(String iconName, String altText) {
        super(iconLocation + iconName + iconExtension, altText);
    }
    public RoImageIcon(String iconName) {
        super(iconLocation + iconName + iconExtension);
    }
    public RoImageIcon(URL location, String altText) {
        super(location, altText);
    }
    public RoImageIcon(URL location) {
        super(location);
    }
    public RoImageIcon(String location, String iconName, String extension, String altText) {
        super(location + iconName + extension, altText);
    }
    public RoImageIcon(String location, String iconName, String extension) {
        super(location + iconName + extension);
    }

    /**
     * 
     * @param iconName - the name of the image, less .extension and folders
     * @return the PATH of the *potential* icon; does not make icon
     */
    public static String toRoImageIcon(String iconName) {
        return iconLocation + iconName + iconExtension;
    }
    public static String toRoImageIcon(String location, String iconName, String extension) {
        return location + iconName + extension;
    }
//------------------------------------------------------------------------------
//    Scaling Functions
//------------------------------------------------------------------------------
//    to specific pixel sizes

    /**
     * 
     * Creates a new icon at designated size. Assumes same path as previous
     * Interior parameter of getScaledInstance is chosen for optimal icons. For
     * better looking large images, change "Image.SCALE_AREA_AVERAGING" to "Image.SCALE_SMOOTH"
     * @param icon - the name of the image to be scaled. 
     * @param width - target width of icon (in pixels)
     * @param height - target height of icon (in pixels)
     * note does not respect aspect ratio of original image
     * @return ImageIcon type, scaled to desired size. Very likely distorted
     */
    public ImageIcon setIconXY(ImageIcon icon, int width, int height) {
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
        ImageIcon sc = new ImageIcon(scaled, icon.getDescription() );
        return (ImageIcon) sc;
    }

    public ImageIcon setIconXY(int width, int height) {
        Image scaled = this.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
        ImageIcon sc = new ImageIcon(scaled, this.getDescription() );
        return (ImageIcon) sc;
    }
    
    /**
     * 
     * @param height - target height (Y-direction) of icon (in pixels)
     * @param width - target width (X-direction) of icon (in pixels)
     * @return - ImageIcon type, scaled to desired size in specific dimension. 
     * Respects original Aspect Ratio
     */
    public ImageIcon scaleIconY(int height) {
                int ogHeight = this.getIconHeight();
                int ogWidth = this.getIconWidth();
                                
                return this.setIconXY( ( height * ogWidth ) / ogHeight, height);
    }

    public ImageIcon scaleIconX(int width) {
                int ogHeight = this.getIconHeight();
                int ogWidth = this.getIconWidth();
                                
                return this.setIconXY( width, (width * ogHeight) / ogWidth );
    }

    
    /**
     * 
     * @param size - the target size (for both dimensions)
     * @param overflow - if false, final image to fit fully inside the box
     * if true, one dimension of image to fully fill the box, the other overflows
     * @return - scaled ImageIcon
     */
        public ImageIcon scaleIconXY(int size) {   
                int ogHeight = this.getIconHeight();
                int ogWidth = this.getIconWidth();
                
                if ( ogHeight > ogWidth ) {
                    return this.scaleIconY(size);
                } else {
                    return this.scaleIconX(size);
                }
        }

        public ImageIcon scaleIconXY(int size, boolean overflow) {   
                int ogHeight = this.getIconHeight();
                int ogWidth = this.getIconWidth();
                
                if ( ( ogHeight > ogWidth && overflow == false ) || 
                        ( ogHeight < ogWidth && overflow == true ) ) {
                    return this.scaleIconY(size);
                } 
                else {
                    return this.scaleIconX(size);
                }
        }
        
        /**
         * Scales image to fit precisely within a box of size width x height
         * @param width - width of target box
         * @param height - height of target box
         * @param overflow - if false, final image to fit fully inside the box
         * if true, one dimension of image to fully fill the box, the other overflows
         * @return - scaled ImageIcon
         */
        public ImageIcon scaleIconXY(int width, int height, boolean overflow) {   
            ImageIcon h = this.scaleIconY(height);
            ImageIcon w = this.scaleIconX(width);

            int newWfromH = h.getIconWidth();               

            if ( ( newWfromH > width && overflow == false ) || 
                    ( newWfromH < width ) && overflow == true) {
                return w;
            } else { 
                return h;
            }
        }
        
        public ImageIcon scaleIconXY(int width, int height) {
            return this.scaleIconXY(width, height, false);
        }
        
//------------------------------------------------------------------------------
//    Scaling Functions
//------------------------------------------------------------------------------
//    to match other items
        
        public ImageIcon scaleMatchIconY(JComponent matchHeight) {
            int height = matchHeight.getHeight();
            return scaleIconY( height );
        }

        public ImageIcon scaleMatchIconX(JComponent matchWidth) {
            int width = matchWidth.getWidth();
            return scaleIconX( width );
        }

        public ImageIcon scaleIconYFontY(JComponent matchHeight) {
            FontMetrics data = matchHeight.getFontMetrics( matchHeight.getFont() );
            int fHeight = data.getHeight();
            return scaleIconY( fHeight );
        }
        
        public ImageIcon scaleIconXFontY(JComponent matchHeight) {
            FontMetrics data = matchHeight.getFontMetrics( matchHeight.getFont() );
            int fHeight = data.getHeight();
            return scaleIconX( fHeight );
        }
        
        public ImageIcon scaleIconYTextY(JComponent matchHeight) {
            FontMetrics data = matchHeight.getFontMetrics( matchHeight.getFont() );
            int tHeight = data.stringWidth( matchHeight.toString() );
            return scaleIconX( tHeight );
        }
        
        public ImageIcon scaleIconXTextX(JComponent matchWidth) {
            FontMetrics data = matchWidth.getFontMetrics( matchWidth.getFont() );
            int tWidth = data.stringWidth( matchWidth.toString() );
            return scaleIconX( tWidth );
        }

//------------------------------------------------------------------------------
//    Getters & Setters
//------------------------------------------------------------------------------

    public static String getIconLocation() {
        return iconLocation;
    }

    public static void setIconLocation(String iconLocation) {
        RoImageIcon.iconLocation = iconLocation;
    }

    public static String getIconExtension() {
        return iconExtension;
    }

    public static void setIconExtension(String iconExtension) {
        RoImageIcon.iconExtension = iconExtension;
    }
}