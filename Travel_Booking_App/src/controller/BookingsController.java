package controller;

import model.Bookings;
import model.Destinations;
import model.Trips;
import model.User;
import utility.AppContext;
import utility.GenericView;
import utility.GenericView.Crud;
import view.AddBookingGUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kalei, mariah
 */
public class BookingsController {
    private AppContext context;
    private AddBookingGUI view;
//    private Bookings booking;
    private int bookingID;
    private User user;
    private User cust;
    
    public BookingsController( AppContext context, AddBookingGUI view){
        this.view = view;
        this.context = context;
        this.user = context.getCurrentUser();
        this.cust = context.getCurrentCustomerUser();
    }
    
    //getters and setters
    public int getBookingID() { return bookingID; }
    public void setBookingID(int bookingID) { this.bookingID = bookingID; }
    
    
    //------------------------------------------------------------------------
    //Utility
    //------------------------------------------------------------------------
    public void pullData(  ){
        User cust = context.getCurrentCustomerUser();
        String userRole = context.getCurrentUser().getRole();
        
        if ( view.getCrud() == GenericView.Crud.CREATE ) {
            //no initial data; add on listener
        } else if ( view.getCrud() == GenericView.Crud.UPDATE ) {
//            showBooking() and leave appropriate fields unlocked
        } else if ( view.getCrud() == GenericView.Crud.REQUEST ) {
            //showBooking() and lock sheet
        } else {
            // if customer, agent, or tour guide --> popup: no permissions 
//                    to delete, requeset delete from admin, return
            // if admin: Popup confirm actually want to delete
//            if yes delete
//            if no do nothing (er. close the popup)
        }
    }
    public void showBooking( int bookingID ){}
    
     // using 'User' 'Trips' etc instead of Id allows for polymorphism, 
     // userID, tripID, etc will conflict: (all Ids are ints)
    public void showBookings( User cust ){}
    public void showBookings( Trips trip ){}
    public void showBookings( Destinations dest ){}
    public void showBookings( Package pack ){}
//    public void showBookings( Product prod ){} // is there an id this is tied to?
    
    public void addBooking( int bookingID ){}
    public void editBooking( int bookingID ){}
    public void deleteBooking( int bookingID ){}
    
}
