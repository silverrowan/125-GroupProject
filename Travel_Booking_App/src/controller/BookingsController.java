package controller;

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
    
    public BookingsController( AppContext context, AddBookingGUI view){
        this.view = view;
        this.context = context;
    }
    
    public void getInitialData(  ){
        if ( view.getCrud() == GenericView.Crud.CREATE ) {
            //no initial data 
        }
    }
    public void addBooking( int bookingID ){}
    public void showBooking( int bookingID ){}
    public void showBookings( int userID ){}
    
}
