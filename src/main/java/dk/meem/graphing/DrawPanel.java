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
import java.io.IOException;
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
    private Color pointcolor = new Color(200,200,200);
    
    /*Ellipse ell;
    Trigonometric trig;
    */
    //Polygon polygon;

    List<TileElement> tilering1;
    List<TileElement> tilering2;
    List<TileElement> tilering3;
    List<TileElement> tilering4;
    List<TileElement> tilering5;
    List<TileElement> tilering6;
    List<List> tilerings = new ArrayList<List>();
    
    Point pivot;

    double zoom;
    
    Mainframe creator;
    // ------ End of global vars ----------


    // Constructor:
    DrawPanel(int x, int y, Mainframe cr) {
        this.maxX=x;
        this.maxY=y;
        pivot = new Point(maxX/2.0,maxY/2.0);
        setPreferredSize(new Dimension(maxX, maxY));
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        
        this.initQE();
        
        creator = cr;
    }

    public void initQE() {
    	double[][] cof = {
    			{0.00001, 0.00002, 0.00003, 0.0004},
    			{0.00001, 0.00002, 0.00003},
    			{0.0, -5, 15},
    			{6, -5, 1},
    			{1, -2, 2}
    	};
    	
		for (int i = 0; i < cof.length; i++) {
			try {
				QuadraticEquation qe = new QuadraticEquation(maxX, maxY, cof[i]);
				System.out.println(qe.getEquation());
				qe.solve();
				System.out.println("d = " + qe.getD());
				if (qe.getD() >= 0.0) {
					for (double root : qe.getRoots()) {
						System.out.println("root = " + root);
					}
				} else {
					System.out.println("No real roots.");
				}
			} catch (IOException ie) {
				System.out.println("IOException: " + ie.getMessage());
			}
			System.out.println("------------------------------------");
		}
		
		System.exit(0);
    }


    public void initTiles() throws IOException {
        int ytranslate=50;
        double scalefactor=1.0;

        for (int i=0; i<20; i++) {  // Make 20 rings.
        	
        	List<TileElement> tlist = new ArrayList<TileElement>();
        	
            for (int j=1; i<=40; i++) { // Each ring has 40 elements.
            	TileElement tile = new TileElement(maxY, 1);
            	tile.scale(scalefactor,  scalefactor);
            	tile.translate(new Point(pivot.getX(), maxY-ytranslate));
            	tile.rotate(9*j*Math.PI/180, pivot);
            	tlist.add(tile);
            }
            ytranslate += 30;
            scalefactor -= 0.1;
            
            this.tilerings.add(tlist);
        }

        this.tilering1 = new ArrayList<TileElement>();
        for (int i=1; i<=40; i++) {
        	TileElement tile = new TileElement(maxY, 1);
        	tile.translate(new Point(pivot.getX(), maxY-50));
        	tile.rotate(9*i*Math.PI/180, pivot);
        	this.tilering1.add(tile);
        }
        this.tilering2 = new ArrayList<TileElement>();
        for (int i=1; i<=40; i++) {
        	TileElement tile = new TileElement(maxY, 1);
        	tile.scale(0.9, 0.9);
        	tile.translate(new Point(pivot.getX(), maxY-80));
        	tile.rotate(9*i*Math.PI/180, pivot);
        	this.tilering2.add(tile);
        }
        this.tilering3 = new ArrayList<TileElement>();
        for (int i=1; i<=40; i++) {
        	TileElement tile = new TileElement(maxY, 1);
        	tile.scale(0.8, 0.8);
        	tile.translate(new Point(pivot.getX(), maxY-110));
        	tile.rotate(9*i*Math.PI/180, pivot);
        	this.tilering3.add(tile);
        }
        this.tilering4 = new ArrayList<TileElement>();
        for (int i=1; i<=40; i++) {
        	TileElement tile = new TileElement(maxY, 1);
        	tile.scale(0.7, 0.7);
        	tile.translate(new Point(pivot.getX(), maxY-140));
        	tile.rotate(9*i*Math.PI/180, pivot);
        	this.tilering4.add(tile);
        }
        this.tilering5 = new ArrayList<TileElement>();
        for (int i=1; i<=40; i++) {
        	TileElement tile = new TileElement(maxY, 1);
        	tile.scale(0.6, 0.6);
        	tile.translate(new Point(pivot.getX(), maxY-170));
        	tile.rotate(9*i*Math.PI/180, pivot);
        	this.tilering5.add(tile);
        }
        this.tilering6 = new ArrayList<TileElement>();
        for (int i=1; i<=40; i++) {
        	TileElement tile = new TileElement(maxY, 1);
        	tile.scale(0.5, 0.5);
        	tile.translate(new Point(pivot.getX(), maxY-200));
        	tile.rotate(9*i*Math.PI/180, pivot);
        	this.tilering6.add(tile);
        }	
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
        double anchorX = (maxX - maxX*zoom)/2;
        double anchorY = (maxY - maxY*zoom)/2;
        AffineTransform at = new AffineTransform();
        at.translate(anchorX, anchorY);
        at.scale(zoom,  zoom);
        at.translate(-anchorX, -anchorY);
        g.setTransform(at);
		*/
        /* End new stuff. */

        /*
        for (TileElement tile : tilering1) {
        	tile.draw(g);
        }
        for (TileElement tile : tilering2) {
        	tile.draw(g);
        }
        for (TileElement tile : tilering3) {
        	tile.draw(g);
        }
        for (TileElement tile : tilering4) {
        	tile.draw(g);
        }
        for (TileElement tile : tilering5) {
        	tile.draw(g);
        }
        for (TileElement tile : tilering6) {
        	tile.draw(g);
        }
        */
        
        /*for (List<TileElement> tilering : tilerings) {
        	for (TileElement tile : tilering) {
        		tile.draw(g);
        	}
        }*/
        
        g.setColor(pointcolor);
        g.fillArc((int)pivot.getX(), (int)pivot.getY(), arcwidth, arcwidth, 0, 360);
        
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
    		this.doTriangulation();
    	} else if (e.getButton() == 1) {
    		this.polygon.add(new Point(e.getX(), e.getY()));
    	}*/
    }
    public void mouseMoved(MouseEvent e) {
        setCursor(creator.getCurrCursor());		// Overkill?
    }
    
    public void mouseWheelMoved(MouseWheelEvent e) {
    	double cw = 1.0;
    	
    	if (e.getWheelRotation() > 0) {
    		cw = -1.0;
    	}
    	else if (e.getWheelRotation() < 0) {
    		cw = 1.0;
    	}
    	
    	repaint();
    }
    
    /*
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
    */
}
