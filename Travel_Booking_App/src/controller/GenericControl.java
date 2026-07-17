/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import javax.swing.JFrame;
import utility.AppContext;
import utility.GenericView;

/**
 *
 * @author Mariah Malczewska
 */
public class GenericControl {
    private final AppContext context;
    private final JFrame view;

    public GenericControl( AppContext context, JFrame view ) {
        this.context = context;
        this.view = view;
    }    
    
}
