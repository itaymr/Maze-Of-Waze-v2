//
//package myMath;
//
//import java.awt.Color;
//import javax.swing.JFrame;
//import de.erichseifert.gral.data.DataTable;
//import de.erichseifert.gral.plots.XYPlot;
//import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
//import de.erichseifert.gral.plots.lines.LineRenderer;
//import de.erichseifert.gral.plots.points.PointRenderer;
//import de.erichseifert.gral.ui.InteractivePanel;
//import de.*;
//
//public class LinePlotTest extends JFrame {
//	
//	
//	static DataTable data = new DataTable(Double.class, Double.class);
//	
//	
//	
//	static void setWindow(int width, int height)
//	{
//		
//	}
//	
//	//	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
//  public LinePlotTest(int width, int height, Range rx, Range ry, int resolution, function[] functions) {
//    	
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        
//        setSize(width, height);
//
//        //0.2x^4-1.5x^3+3.0x^2-x-5
//        Polynom p1 = new Polynom("x^2");
//        Polynom p2 = new Polynom("3x + 3");
//        DataTable data = new DataTable(Double.class, Double.class);
//        
//        
//        for (double x = -2.0; x <= 2.0; x+=0.01) {
//            double y = p1.f(x);
//            data.add(x, y);
//            y = p2.f(x);
//            data.add(x, y);
//        }
//        XYPlot plot = new XYPlot(data);
//        getContentPane().add(new InteractivePanel(plot));
//        LineRenderer lines = new DefaultLineRenderer2D();
//
//    }
//}