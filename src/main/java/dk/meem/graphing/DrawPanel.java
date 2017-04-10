package dk.meem.graphing;

import javax.swing.*;

import dk.meem.graphing.primitive.Triangle;
import dk.meem.graphing.primitive.Point;

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
    
    Polygon polygon;
    ArrayList<Polygon> mpoly;
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
        String helsinoerstadion = "(((723938.2021 6217035.3268, 724013.9392 6216954.9595, 724021.2153 6216954.9595, 724069.8325 6217001.5924, 724070.1633 6217008.5377, 723994.4261 6217088.9051, 723988.1423 6217088.9051, 723937.8713 6217042.2722, 723938.2021 6217035.3268)), ((724023.8124 6216933.982, 724105.0681 6216851.5697, 724150.178 6216899.8604, 724069.2115 6216979.9595, 724023.8124 6216933.982)), ((723926.407 6217173.399, 723968.6292 6217123.6317, 724044.6971 6217190.1084, 724005.44 6217236.4486, 723926.407 6217173.399)))";

        mpoly = WKTParser.fromWktMulti(helsinoerstadion);
        polygon = WKTParser.unitFromWkt(skaterparken);
    }


    public void clearCurve() {
        repaint();
    }


    public void paintComponent(Graphics g_orig) {
        Graphics2D g = (Graphics2D)g_orig;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(backgroundColor);
        g.fillRect(0,0, this.getWidth(), this.getHeight());
        
        /* New stuff.
			http://stackoverflow.com/questions/30792089/java-graphics2d-translate-and-scale
		*/
        double anchorX = (maxX - maxX*zoom)/2;
        double anchorY = (maxY - maxY*zoom)/2;
        AffineTransform at = new AffineTransform();
        at.translate(anchorX, anchorY);
        at.scale(zoom,  zoom);
        at.translate(-anchorX, -anchorY);
        g.setTransform(at);
        /* End new stuff. */

        // Old way: g.scale(zoom,  zoom);
        
        if ( ! mpoly.isEmpty()) {
        	for (Polygon poly : mpoly) {
        		poly.draw(g);
        	}
        }
        
        if ( ! polygon.isEmpty()) {
        	polygon.draw(g);
        }
        
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
    	if (e.getButton() == 3) {
    		polygon.clear();
    	} else if (e.getButton() == 1) {
    		polygon.add(new Point(e.getX(), e.getY()));
    	}
    }
    public void mouseMoved(MouseEvent e) {
        setCursor(creator.getCurrCursor());		// Overkill?
    }
    
    public void mouseWheelMoved(MouseWheelEvent e) {
    	double step = 0.05;
    	if (e.getWheelRotation() > 0) { this.zoom += step; }
    	else if (e.getWheelRotation() < 0) { this.zoom -= step; }

    	if ( ! polygon.isEmpty()) {
    		System.out.println("Area2= " + polygon.area());
    	}
    	repaint();
    }
    
    private void doTriangulation() {
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
    	
    }
}
