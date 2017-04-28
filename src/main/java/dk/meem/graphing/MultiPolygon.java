package dk.meem.graphing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class MultiPolygon {
	private Color graphColor = new Color(0,0,0);
	private ArrayList<Polygon> polygons = new ArrayList<>();
	
	public MultiPolygon() {
	}
	
	public MultiPolygon(ArrayList<Polygon> polygons) {
		this.polygons = polygons; 
	}
	
	public void clear() {
		this.polygons.clear();
	}
	
	public int size() {
		return polygons.size();
	}
	
	public boolean isEmpty() {
		return polygons.isEmpty();
	}
	
	public void draw(Graphics2D g) {
		g.setColor(graphColor);
		
		for (int i=0; i<polygons.size(); i++) {
			polygons.get(i).draw(g);
		}
		
		//g.drawArc((int)centro.getX(), (int)centro.getY(), 6, 6, 0, 360);
	}

	/* This moves all polygons, by translating them all 
	 * by the first point in first polygon.
	 */
	public void toOrigo() {
		Point base = polygons.get(0).firstPoint();
		this.translate(base.multBy(-1.0));
	}

	public void translate(Point t) {
		if ( ! polygons.isEmpty()) {
			for (int i = 0; i<polygons.size(); i++) {
				polygons.get(i).translate(t);
			}
		}
	}
	
	public void add(Polygon p) {
		polygons.add(p);
	}
	

	@Override
	public String toString() {
		String res = "MultiPolygon. Multipolygon=" + System.lineSeparator();
		for (Polygon p : this.polygons) {
			res += p.toString() + System.lineSeparator();
		}
		
		return res;
	}

}
