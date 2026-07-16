/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import model.gui.Model_MenuItem;
import view.models.RoImageIcon;

/**
 *
 * @author rowan
 */
public class CardMenuRenderer extends JPanel implements ListCellRenderer<Model_MenuItem> {
    private JLabel lblIcon = new JLabel();
    private JLabel lblTitle = new JLabel();
    private JSeparator separator = new JSeparator();
    private boolean selected;
    private Color selectColor;
    
    public CardMenuRenderer() {
        setLayout( new BorderLayout( 10,0 ));
        setBorder(BorderFactory.createEmptyBorder(10, 22, 10, 12));
        setOpaque(false);
        
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        
        add(lblIcon, BorderLayout.WEST);
        add(lblTitle, BorderLayout.CENTER);

        lblIcon.setOpaque(false);
        lblTitle.setOpaque(false);   
        
        separator.setOpaque(false);
        separator.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends Model_MenuItem> list, Model_MenuItem value, int index, 
            boolean isSelected, boolean cellHasFocus) {
        
            //Card contents
        //----------Separator--------------
    if (value.getType() == Model_MenuItem.MenuType.SEPARATOR) {
        separator.setPreferredSize(new Dimension(1, 10));   // adjust height here
        return separator;
    }
    //------------------Header----------------
    if (value.getType() == Model_MenuItem.MenuType.HEADER) { setData( value ); } 
    else { setDataWIcon( value ); }
    setSelected( isSelected , list);

    //card spacing
    setBorder( BorderFactory.createCompoundBorder( 
            BorderFactory.createEmptyBorder(6,8,6,8),
            BorderFactory.createLineBorder( new Color(230, 230, 230) )
    ) );
//        list.setFixedCellHeight( 70 ); //row height
        //hover        // ... maybe later. fair bit here
        setPreferredSize( new Dimension(1, 70) );
        return this;
} 

    public void setDataWIcon( Model_MenuItem value ) {
        lblTitle.setFont(new Font("URW Bookman", Font.BOLD, 32));
        lblTitle.setText(value.getName());

        if (value.getIcon() == null) {
            lblIcon.setIcon(null);
        } else {
            RoImageIcon icon = (RoImageIcon) value.toRoImageIcon();
            lblIcon.setIcon(icon.scaleIconYFontY(lblTitle));
        }
    }
    
    public void setData( Model_MenuItem value ) {
        lblTitle.setFont(new Font("URW Bookman", Font.BOLD, 32));
        lblTitle.setText(value.getName());
        lblIcon.setIcon(null);        
        lblIcon.setIcon(null);        
    }
    
    public void setSelected(boolean selected, JList<?> list) {
        this.selected = selected;
        if (selected) {
            setBackground(list.getSelectionBackground());
            lblTitle.setForeground( Color.BLACK );
        } else {
            setBackground( list.getBackground());
            lblTitle.setForeground( list.getForeground());            
        }
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (selected) {
            if ( selectColor == null ) {
                g2.setColor(new Color(189,238,239,80) );
            } else {
                g2.setColor( selectColor );
            }
            g2.fillRoundRect(8, 0, getWidth()-16, getHeight(), 8, 8);
        }
        
        g2.dispose();
        super.paintComponent(grphcs);
         
    }
}
