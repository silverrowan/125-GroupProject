
package view.components;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
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
    
    private final DefaultListModel model;
    private int selectedIndex = -1;
    
    public ListMenu() {
        model = new DefaultListModel();
        setModel(model);
    }
    
    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {


            @Override
            public Component getListCellRendererComponent(JList<?> list, Object o, int index, boolean isSelected, boolean cellHasFocus) {
                Model_MenuItem data;
                if (o instanceof Model_MenuItem) {
                    data = (Model_MenuItem) o;
                } else {
                    data = new Model_MenuItem("", o + "",Model_MenuItem.MenuType.EMPTY);
                }
                MenuItem item = new MenuItem(data);
                item.setSelected(isSelected); //chat recomendation - about renderer not respectinc selection state, not sure exactly what it does gifure it out
                return item;
            }
        };
    }
    
    public void addItem(Model_MenuItem data) {
        model.addElement(data);
    }
}
