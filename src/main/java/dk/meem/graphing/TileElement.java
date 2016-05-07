package dk.meem.graphing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import dk.meem.graphing.primitive.Point;
import dk.meem.graphing.primitive.Line;

public class TileElement extends Geometry {
	List<Line> lines = new ArrayList<Line>();
	int maxY;
	
	public TileElement(int maxY, int type) {
		this.maxY = maxY;

		if (type == 1) {
			this.lines = diamondShape();
		}

	}
	
	public void rotate(double radians, Point pivot) {
		this.translate(pivot.multBy(-1.0));
		
		List<Line> rlines = new ArrayList<Line>();
		
		for (Line line : this.lines) {
			rlines.add(line.rotate(radians));
		}
		
		this.lines = rlines;
		
		this.translate(pivot);
	}
	
	public void translate(Point trpoint) {
		List<Line> tlines = new ArrayList<Line>();
		
		for (Line line : this.lines) {
			tlines.add(line.translate(trpoint));
		}
		
		this.lines = tlines;
	}
	
	public void scale(double x, double y) {
		List<Line> slines = new ArrayList<Line>();
		
		for (Line line : this.lines) {
			slines.add(line.scale(x,y));
		}
		
		this.lines = slines;
		
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		
		for (Line line : lines) {
			g.drawLine((int)line.getP1().getX(), (int)line.getP1().getY(),
					   (int)line.getP2().getX(), (int)line.getP2().getY());
			
		}

	}
	
	public List<Line> diamondShape() {
		List<Line> diamond = new ArrayList<Line>();
		
		diamond.add(new Line(0,0,0,10));
		diamond.add(new Line(0,10,-30,20));
		diamond.add(new Line(-30,20, 0,30));
		diamond.add(new Line(0,30, 0,40));
		diamond.add(new Line(0,30, 30,20));
		diamond.add(new Line(30,20, 0, 10));
		
		diamond.add(new Line(-30,10, 0,20));
		diamond.add(new Line(0,20, -30,30));
		diamond.add(new Line(30,10, 0,20));
		diamond.add(new Line(0,20, 30,30));
		
		
		return diamond;
		/*
		lines.add(new Line(100,100, 200,100));
		lines.add(new Line(200,100, 200,50));
		lines.add(new Line(200,50,  100,50));
		lines.add(new Line(100,50, 100,100));
		*/
		
		/*
		lines.add(new Line(300,300, 300,310));
		lines.add(new Line(300,310, 230,320));
		lines.add(new Line(230,320, 300,330));
		lines.add(new Line(300,330, 300,340));
		lines.add(new Line(300,330, 370,320));
		lines.add(new Line(370,320, 300,310));
		*/
	}
}
