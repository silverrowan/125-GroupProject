package Template_ControllerAndRelatedElsewhere;

import view.ProductsGUI;

/**
 *
 * @author Mariah Malczewska
 */
public class ProductControlDemo { // Just to stop template from complaining (currently?)
    private final AppContextPlaceholder context;
    private final ProductsGUI prodView;

public ProductControlDemo( AppContextPlaceholder context, ProductsGUI prodView ) {
    this.context = context;
    this.prodView = prodView;
    
    //add listeners etc
}

}
