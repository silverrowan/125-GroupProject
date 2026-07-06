/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import model.gui.Model_MenuItem;
import view.models.RoImageIcon;

/**
 *
 * @author rowan
 */
public class CardMenuRenderer extends JPanel implements ListCellRenderer<Model_MenuItem> {
    private JLabel lblIcon = new JLabel();
    private JLabel lblTitle = new JLabel();
    private boolean selected;
    
    public CardMenuRenderer() {
        setLayout( new BorderLayout( 10,0 ));
        setBorder(BorderFactory.createEmptyBorder(10, 22, 10, 12));
        setOpaque(false);
        
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        
        add(lblIcon, BorderLayout.WEST);
        add(lblTitle, BorderLayout.CENTER);

        lblIcon.setOpaque(false);
        lblTitle.setOpaque(false);     
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends Model_MenuItem> list, Model_MenuItem value, int index, 
            boolean isSelected, boolean cellHasFocus) {
        
        lblTitle.setText( value.getName() );
        lblIcon.setIcon( value.toRoImageIcon() );
               
        //Styling (Card)
        if (isSelected) {
            setBackground( new Color(220, 235, 255) );
        } else {
            setBackground( Color.WHITE );
        }
        
        //card spacing
        setBorder( BorderFactory.createCompoundBorder( 
                BorderFactory.createEmptyBorder(6,8,6,8),
                BorderFactory.createLineBorder( new Color(230, 230, 230) )
        ) );
        
        //row height
        list.setFixedCellHeight( 70 );
        
        //hover
        // ... maybe later. fair bit here
        
        return this;
    }    

    public void setData( Model_MenuItem data ) {
        lblTitle.setFont(new Font( null , 1, 32));
        
        lblTitle.setText(data.getName());
        RoImageIcon icon = (RoImageIcon) data.toRoImageIcon();
        lblIcon.setIcon( icon.scaleIconYFontY( lblTitle ));
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
            g2.setColor(new Color(255,255,255,80) );
            g2.fillRoundRect(8, 0, getWidth()-16, getHeight(), 8, 8);
        }
        
        g2.dispose();
        super.paintComponent(grphcs);
         
    }
}
