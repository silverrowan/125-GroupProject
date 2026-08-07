
package view.Person;

/**
 *
 * @author rowan
 */
public class runPerson {
    public static void main(String args[]){
        PersonDash dash = new PersonDash();
        PersonDAO dao = new PersonDAO();
        PersonDashControl control = new PersonDashControl( dash, dao);
        dash.setVisible(true);
    }
    
}
