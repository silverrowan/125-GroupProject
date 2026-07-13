package view.components;

import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import model.gui.Model_MenuItem;

/**
 *
 * @author rowan
 */
public class ListMenu<E extends Object> extends JList<E> {
    private Color selectColor;
    
    private final DefaultListModel<E> model = new DefaultListModel<>();
    private final CardMenuRenderer renderer = new CardMenuRenderer();
       
    public ListMenu() {
        setModel(model);
        
        setCellRenderer(( list, value, index, isSelected, cellHasFocus) -> {
            Model_MenuItem data = (Model_MenuItem) value;
            
            renderer.setData(data);
            renderer.setSelected(isSelected, list);
            return renderer;
        });
    }
    
    public void addItem(Model_MenuItem data) {
        model.addElement( (E) data);
    }
}
