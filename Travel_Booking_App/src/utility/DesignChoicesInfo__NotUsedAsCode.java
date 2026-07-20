package utility;

import java.awt.Color;

/**
 *
 * @author rowan
 */
public class DesignChoicesInfo__NotUsedAsCode {

    class Typography {
//    Most text is default: Noto Sans, 12pt, plain
//    Heading text is bigger & bold: Noto Sans, 
//        14pt for prev. not much diff, change to 16, 
//        Bold Italic
    }

    class Colors {
//        (default grey: [242,242,242])
//        (white: [255,255,255])
       
        Color brightOceanGreen = new Color(204,255,204); // #ccffcc; neon teal-green
        Color xLightOceanGreen = new Color(230, 255, 230); // #E6FFE6;
        
        Color xLightTeal = new Color(189,238,239); // #BDEEEF; close to robin egg blue
        Color lightTeal = new Color(161, 231, 232); // #A1E7E8
        Color medLightTeal = new Color(124, 222, 223); // #7CDEDF
        Color medTeal = new Color(50, 203, 205); //#32CBCD
        Color darkTeal = new Color(41, 166, 168); //#29A6A8
        
        Color mustard = new Color(244,189,24); //#f4bd18
        Color boldRose = new Color(223,32,73); //#df2049
        
        Color orange = new Color(255,204,153); //#FFCC99; original somewhat sofer orange
//            lightest  #FFF2E6 255, 242, 230
//            lighter #FFDBB8 255, 219, 184
//                'orange' above sits here in the gradiation
//            slightly darker #FFAD5C, 255, 173, 92
//            med, high sat #FF962E, 255, 150, 46
//            pure sat #FF8000 255, 128, 0
//            kinda dull... #D16900 209, 105, 0
        
    class Notes {
//        tldr: 
//            - initComponents() - builds structure once;
//            - paintComponents() - draws visuals MANY times;
//            - setters + repaint() - change state and trigger repaint in setter;
//            NEVER MIX the three;
//            (also group layout (the default) can cause wierd bugs in custom lists - use something else)  
//
//        -----
//        
//        Swing has 3 'responsibilities' to UI generation; completely separated from ea other
//        1) Structure - WHAT exists; runs ONCE;
//                    - initComponents();
//                    - constructors;
//                    - add(), setLayout();
//                    - creating buttons, panels, labels, etc;
//        2) Layout/Positioning - WHERE is it?; runs as needed (automatic);
//                dont touch directly. Is handled by;
//                    - <whateverName>Layout();
//        3) Painting - what does it look like; runs MANY times per second (if needed)
//                MUST be fast & free from side-effects or wierd stuff happens;
//                    - paintComponent();
//                    - paintBorder();
//                    - paintChildren();
//            ***********DO NOT include structure or layout inside painting! 
//                                  Causes constant UI rebuild -> flickering, mem leaks, corruption
//            ***********Avoid parsing; heavy compute
//                                    cheap operations (ok in paint): drawing shapes, using cached colors, simple math;
//                                    expensive operations (not ok in paint): string parsing (eg. Color.decode), object creation, IO, building UI components
//            "Paint Lifecycle"
//            repaint() -> paintComponent() -> paintBorder() -> paintChildren()
//                background -> paintComponent
//                UI components -> paintChildren **** handled by Swing ****
//                borders -> paintBorder        
//        
//        
//        Working with NetBeans GUI builder
//                DO NOT TOUCH - initComponents()
//                        instead, add changes to constructor *after* initComponents
//                OK to add/alter:
//                    event handlers;
//                    own methods;
//                    
//        Properly Designed Custom Components:
//            field = state only; must not be computed in paint;
//            constructor = initialization only (no paint);
//            paintComponent = just rendering, no parsing, no construction, runs 'once'
//                    paintChild = like paintComponent, but runs repeatedly; for each child
//            setters should include a repaint(); - triggers a redraw, including contents of paintComponent
//        W Custom Components Workflow
//            - build UI in netBeans (forms)
//            - expose behaviour via methods eg. setGradient
//            - modify components via methods AFTER initComponents   
        }    
    }
}