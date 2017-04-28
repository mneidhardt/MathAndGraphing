package dk.meem.graphing;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.event.MouseEvent;


class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    // ------ First some global vars ------------
 
	private static final long serialVersionUID = 1L;
    private int maxX, maxY;
    private Color backgroundColor = new Color(255,255,255);
    
    static final int arcwidth=10;			// Width of the circles I draw.
    static final int halfwidth=arcwidth/2;
    
    List<Polygon> polygons = new ArrayList<Polygon>();
    MultiGeometry mg;
    double zoom;
    
    Mainframe creator;
    // ------ End of global vars ----------


    // Constructor:
    DrawPanel(int x, int y, Mainframe cr)	{
        this.maxX=x;
        this.maxY=y;
        setPreferredSize(new Dimension(maxX, maxY));
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        this.zoom = 1.0;
        creator = cr;

        // Fælledparken skatepark.
        String geometri =    "723930.2271 6178382.3569, 723934.3453 6178386.6491, 723906.056 6178415.5764, 723910.7127 6178434.6264, 723902.246 6178436.1081, 723886.371 6178441.1881, 723864.5693 6178438.0131, 723853.7743 6178422.5614, 723858.6426 6178377.053, 723842.3442 6178361.178, 723875.3643 6178327.9462, 723930.2271 6178382.3569";
        String teliaparken = "724461.42 6178878.5, 724441.27 6178854.96, 724427.51 6178866.74, 724431.79 6178871.74, 724416.42 6178884.91, 724412.76 6178888.03, 724334.3 6178796.21, 724339.52 6178791.74, 724329.82 6178780.41, 724357.88 6178756.39, 724351.79 6178749.28, 724439.41 6178674.27, 724445.58 6178681.48, 724472.95 6178658.04, 724496.32 6178685.33, 724503.5 6178679.18, 724554.56 6178738.82, 724547.07 6178745.23, 724570.96 6178773.13, 724543.79 6178796.39, 724549.52 6178803.08, 724527.32 6178822.09, 724461.42 6178878.5";
        String parken =      "724461.42 6178878.5, 724441.27 6178854.96, 724427.51 6178866.74, 724431.79 6178871.74, 724416.42 6178884.91, 724412.76 6178888.03, 724334.3 6178796.21, 724339.52 6178791.74, 724329.82 6178780.41, 724357.88 6178756.39, 724351.79 6178749.28, 724439.41 6178674.27, 724445.58 6178681.48, 724472.95 6178658.04, 724496.32 6178685.33, 724503.5 6178679.18, 724554.56 6178738.82, 724547.07 6178745.23, 724570.96 6178773.13, 724543.79 6178796.39, 724549.52 6178803.08, 724527.32 6178822.09, 724461.42 6178878.5";
        String travbaneparken = "727452.728 6170394.3112, 727456.4322 6170196.4025, 727681.3285 6170203.2817, 727680.2701 6170218.6275, 727747.4744 6170219.6858, 727745.3578 6170403.3071, 727452.728 6170394.3112";
        String skaterparken = "723930.2271 6178382.3569, 723934.3453 6178386.6491, 723906.056 6178415.5764, 723910.7127 6178434.6264, 723902.246 6178436.1081, 723886.371 6178441.1881, 723864.5693 6178438.0131, 723853.7743 6178422.5614, 723858.6426 6178377.053, 723842.3442 6178361.178, 723875.3643 6178327.9462, 723930.2271 6178382.3569";
        String helsinoerstadion = "MULTIPOLYGON (((723938.2021 6217035.3268, 724013.9392 6216954.9595, 724021.2153 6216954.9595, 724069.8325 6217001.5924, 724070.1633 6217008.5377, 723994.4261 6217088.9051, 723988.1423 6217088.9051, 723937.8713 6217042.2722, 723938.2021 6217035.3268)), ((724023.8124 6216933.982, 724105.0681 6216851.5697, 724150.178 6216899.8604, 724069.2115 6216979.9595, 724023.8124 6216933.982)), ((723926.407 6217173.399, 723968.6292 6217123.6317, 724044.6971 6217190.1084, 724005.44 6217236.4486, 723926.407 6217173.399)))";
        String helsingehallerne = "MULTIPOLYGON (((698466.6 6213380.25, 698500.4 6213348.43, 698500.69 6213348.74, 698505.22 6213344.47, 698507.56 6213346.95, 698511.59 6213343.16, 698502.11 6213333.08, 698514.09 6213321.8, 698513.62 6213321.3, 698517.9 6213317.2, 698515.46 6213314.63, 698490.71 6213338.33, 698496.36 6213344.22, 698470.98 6213368.52, 698452.77 6213349.51, 698503.26 6213301.17, 698504.49 6213302.46, 698510.5 6213296.71, 698514.65 6213301.04, 698521.52 6213294.55, 698527.5 6213300.88, 698536.89 6213292.0, 698540.34 6213295.48, 698548.46 6213287.7, 698565.79 6213305.79, 698557.3 6213313.92, 698571.72 6213328.98, 698563.51 6213336.83, 698555.47 6213328.43, 698544.15 6213339.27, 698546.67 6213341.91, 698533.77 6213353.85, 698545.07 6213374.03, 698542.6742 6213376.4515, 698544.4286 6213378.94, 698542.0793 6213380.702, 698554.7065 6213395.091, 698497.8844 6213449.1234, 698477.3286 6213426.8057, 698494.52 6213409.91, 698466.6 6213380.25), (698519.17 6213350.8, 698525.24 6213344.97, 698519.76 6213339.26, 698513.69 6213345.09, 698519.17 6213350.8)))";

        
        polygons.add(WKTParser.fromWkt(geometri));
        polygons.add(WKTParser.fromWkt(teliaparken));
        polygons.add(WKTParser.fromWkt(parken));
        polygons.add(WKTParser.fromWkt(travbaneparken));
        polygons.add(WKTParser.fromWkt(skaterparken));

        mg = WKTParser.fromWktMulti(helsinoerstadion);
    }


    public void clearCurve() {
        repaint();
    }


    @Override
    public void paintComponent(Graphics g_orig) {
        Graphics2D g = (Graphics2D)g_orig;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(backgroundColor);
        g.fillRect(0,0, this.getWidth(), this.getHeight());
        
        /* New stuff.
			http://stackoverflow.com/questions/30792089/java-graphics2d-translate-and-scale
		*/
        /*
        double anchorX = (maxX - maxX*zoom)/2;
        double anchorY = (maxY - maxY*zoom)/2;
        AffineTransform at = new AffineTransform();
        at.translate(anchorX, anchorY);
        at.scale(zoom,  zoom);
        at.translate(-anchorX, -anchorY);
        g.setTransform(at);
        */
        /* End new stuff. */

        // Old way:
        g.scale(zoom,  zoom);
        
        if ( ! mg.isEmpty()) {
        	mg.toOrigo();
        	mg.translate(new Point(100,200));
       		mg.draw(g);
        }
        
		/*if (!polygons.isEmpty()) {
			for (Polygon polygon : polygons) {
				polygon.toOrigo();
				polygon.translate(new Point(300, 300));
				polygon.draw(g);
			}
		}*/
        
        g.dispose();
    }
    
    public void mousePressed(MouseEvent e) {
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
        repaint();
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
    	/*if (e.getButton() == 3) {
    		polygon.clear();
    	} else if (e.getButton() == 1) {
    		polygon.add(new Point(e.getX(), e.getY()));
    	}*/
    }
    public void mouseMoved(MouseEvent e) {
        setCursor(creator.getCurrCursor());		// Overkill?
    }
    
    public void mouseWheelMoved(MouseWheelEvent e) {
    	double step = 0.05;
    	if (e.getWheelRotation() > 0) { this.zoom += step; }
    	else if (e.getWheelRotation() < 0) { this.zoom -= step; }

    	repaint();
    }
    
    private void doTriangulation() {
    	/*
		polygon.clear();
		Triangulator2 tri2 = new Triangulator2();
		Point[] points = tri2.init(50);
		System.out.println("size(400,400); noFill();");
		
		for (int tt=0; tt<points.length; tt++)
		{
			System.out.println("rect(" + points[tt].getX() + "," + points[tt].getY() + ", 3, 3);");
		}
		
		Triangle[] triangles = tri2.getTriangles();
		
		for (int tt=0; tt<tri2.getNumOfTriangles(); tt++) {
			System.out.println("beginShape(TRIANGLES);");
			System.out.println("vertex( " + points[triangles[tt].p1].getX() + "," + points[triangles[tt].p1].getY() + ");");
			System.out.println("vertex( " + points[triangles[tt].p2].getX() + "," + points[triangles[tt].p2].getY() + ");");
			System.out.println("vertex( " + points[triangles[tt].p3].getX() + "," + points[triangles[tt].p3].getY() + ");");
			System.out.println("endShape();");
		}
    	*/
    }
}
