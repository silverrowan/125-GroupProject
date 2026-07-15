package controller;

import dao.DestinationsDAO;
import model.Destinations;
import view.ViewDestinationsGUI;

/**
 *
 * @author Mariah Malczewska
 */
public class DestinationController {
    private final DestinationsDAO dao;
    private final ViewDestinationsGUI view;
    
    public DestinationController( DestinationsDAO dao, ViewDestinationsGUI view ) {
        this.dao = dao;
        this.view = view;
        

    }
}
