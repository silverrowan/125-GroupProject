package controller;

import javax.swing.JLabel;
import view.ViewDestinationsGUI;
import model.Destinations;
import view.ViewDestinationsGUI;

/**
 *
 * @author Mariah Malczewska
 */
public class DestinationsController {
    private ViewDestinationsGUI viewDestinationsGUI;
    private Destinations destinations;
//    private DestinationsDao destinationsDao;
    
    public DestinationsController(ViewDestinationsGUI viewDestinationsGUI, Destinations destinations){
        this.viewDestinationsGUI = viewDestinationsGUI;
        this.destinations = destinations;
    }
    
    public void loadRecord(){
        //fetch from Destinations
        String recordContents = Destinations.getFullAddress();
        ViewDestinationsGUI.updateHotelFullAddressLbl( recordContents );
    }
    //gets value of specific row in Database we are viewing
    public void hotelData(int destinationID) {
        //get from DB via Model (Destinations)
//        Destinations.getFullAddressByID(destinationID); // can only ref static
    }

//    public JLabel getHotelFullAddressLbl() {
//        this.addCourseView.addAddNewCourseBtnListener(new AddCourseRecord());
//        }
 
//    class AddCourseRecord implements ActionListener {
//        public AddCourseRecord() {}
//        
//        @Override
//        public void actionPerformed(ActionEvent e){
//            String courseName = addCourseView.getCourseNameTxt().getText(); //get text in view field
//            int credit = Integer.parseInt( addCourseView.getCreditTxt().getText() );
//            Course newCourse = new Course(courseName, credit);
//            //Explaining tis after DAO complete
//            boolean result = courseDao.addCourseRecord(newCourse); // pass record into object
//            if(result){
//                JOptionPane.showMessageDialog(null, "Course added successfully");
//            } else {
//                JOptionPane.showMessageDialog(null, "Course was not added");
//            }
//        }
//    } 
//}
    
    
    
    
    
    
}
