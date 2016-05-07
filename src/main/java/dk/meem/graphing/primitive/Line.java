package dk.meem.graphing.primitive;

public class Line {
    private Point p1;
    private Point p2;


    public Line (Point p1, Point p2) {
    	this.p1 = p1;
    	this.p2 = p2;
    }

    public Line(double x1, double y1, double x2, double y2) {
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }
    
    public Line translate(Point p) {
    	return new Line(this.p1.add(p), this.p2.add(p));
    }
    
    public Line rotate(double radians) {
    	return new Line(this.p1.rotate(radians), this.p2.rotate(radians));
    }
    
    public Line scale(double sx, double sy) {
    	return new Line(this.p1.getX()*sx, this.p1.getY()*sy,
    			        this.p2.getX()*sx, this.p2.getY()*sy);
    }

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line other = (Line) obj;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Line [p1=" + p1 + ", p2=" + p2 + "]";
	}


}
