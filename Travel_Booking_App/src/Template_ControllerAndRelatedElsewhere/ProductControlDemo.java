package Template_ControllerAndRelatedElsewhere;

import utility.AppContext;
import view.ProductsGUI;

/**
 *
 * @author Mariah Malczewska
 */
public class ProductControlDemo { // Just to stop template from complaining (currently?)
    private AppContext context;
    private ProductsGUI prodView;

public ProductControlDemo( AppContext context, ProductsGUI prodView ) {
    this.context = context;
    this.prodView = prodView;
    
    //add listeners etc
}

}
