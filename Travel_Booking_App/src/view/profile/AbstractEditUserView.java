
package view.profile;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import utility.AppContext;
import utility.GenericView;

/**
 * Defines common behaviour for all views that edit user profiles
 * @author Max Zhang
 */
public abstract class AbstractEditUserView extends GenericView {
    
    public AbstractEditUserView(AppContext context) {
        super(context);
    }
    
    public AbstractEditUserView() {}
    
    // getters and setters
    public abstract JButton getBtnCancel();

    public abstract JButton getBtnSave();

    public abstract JTextField getInputEmail();

    public abstract JTextField getInputFirstName();

    public abstract JTextField getInputLastName();

    public abstract JTextField getInputPassword();

    public abstract JTextField getInputPhone();

    public abstract JTextField getInputUsername();
    
    public abstract JComboBox getSelectionRole();
    
    public abstract JCheckBox getRadioStatus();
    
    public abstract JTextField getInputStreetNumber();
    
    public abstract JTextField getInputStreet();
    
    public abstract JTextField getInputCity();
    
    public abstract JTextField getInputProvince();
    
    public abstract JTextField getInputPost();
    
    public abstract JTextField getInputCountry();
    
    public abstract void addCancelBtnListener(ActionListener cancelListener);
    
    public abstract void addSaveBtnListener(ActionListener saveListener);
    
    public abstract void addDeleteAccountBtnListener(ActionListener deleteAccountListener);
    
}
