package dk.meem.graphing;

import javax.swing.*;

import dk.meem.graphing.primitive.Point;
import dk.meem.graphing.primitive.Triangle;

// import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;


class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    // ------ First some global vars ------------
 
	private static final long serialVersionUID = 1L;
    private int maxX, maxY;
    private Color backgroundColor = new Color(255,255,255);
    
    static final int arcwidth=10;			// Width of the circles I draw.
    static final int halfwidth=arcwidth/2;
    
    Ellipse ell;
    Trigonometric trig;
    Polynomial pol;
    Polynomial2 pol2;
    Polygon polygon;
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
        
        ell = new Ellipse(420, 10, 500, -300);
        trig = new Trigonometric();
        pol = new Polynomial(maxX, maxY);
        double[] cof = {0.00001, 0.00002, 0.00003, 0.0004};
        this.pol2 = new Polynomial2(maxX, maxY, cof);
        
        this.polygon = new Polygon();
        this.zoom = 1.0;

        creator = cr;
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
        
        //ell.draw(g);
        //trig.draw(g);
        pol2.draw(g);
        
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
    		this.doTriangulation();
    	} else if (e.getButton() == 1) {
    		this.polygon.add(new Point(e.getX(), e.getY()));
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
