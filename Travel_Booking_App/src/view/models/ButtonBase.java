package view.models;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author Mariah Malczewska
 */
public class ButtonBase extends JButton {
    
    public ButtonBase(){
    setBackground(new Color(204, 255, 204));
    setFont(new Font("Segoe UI", Font.BOLD, 24));
    }
}
