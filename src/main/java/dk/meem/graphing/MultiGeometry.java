package dk.meem.graphing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class MultiGeometry<T extends Geometry> {
	private Color graphColor = new Color(0,0,0);
	private ArrayList<T> geometries = new ArrayList<>();
	
	public MultiGeometry() {
	}
	
	public MultiGeometry(ArrayList<T> geom) {
		this.geometries = geom; 
	}
	
	public void clear() {
		this.geometries.clear();
	}
	
	public int size() {
		return geometries.size();
	}
	
	public boolean isEmpty() {
		return geometries.isEmpty();
	}
	
	public void draw(Graphics2D g) {
		g.setColor(graphColor);
		
		for (T t : geometries) {
			t.draw(g);
		}
		
		//g.drawArc((int)centro.getX(), (int)centro.getY(), 6, 6, 0, 360);
	}

	/* This moves all polygons, by translating them all 
	 * by the first point in first polygon.
	 */
	public void toOrigo() {
		Point base = geometries.get(0).firstPoint();
		this.translate(base.multBy(-1.0));
	}

	public void translate(Point p) {
		if ( ! geometries.isEmpty()) {
			for (T t : geometries) { //int i = 0; i<polygons.size(); i++) {
				t.translate(p);
			}
		}
	}
	
	public void add(T t) {
		geometries.add(t);
	}
	

	@Override
	public String toString() {
		String res = "MultiPolygon. Multipolygon=" + System.lineSeparator();
		for (T t : this.geometries) {
			res += t.toString() + System.lineSeparator();
		}
		
		return res;
	}

}
