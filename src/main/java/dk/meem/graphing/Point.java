package dk.meem.graphing;

public class Point {
    private double x=0.0;
    private double y=0.0;
    private double z=0.0;


    public Point () {
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point add(Point B) {
        return new Point(this.getX()+B.getX(), this.getY()+B.getY());
    }

    public Point subtract(Point B) {
        return new Point(this.getX()-B.getX(), this.getY()-B.getY());
    }

    public Point divideBy(double d) {
        return new Point(this.x/d, this.y/d);
    }

    public Point multBy(double d) {
        return new Point(this.x * d, this.y * d);
    }

    public double distanceTo(Point B) {
        double sum = Math.pow(this.x - B.getX(), 2) +
                     Math.pow(this.y - B.getY(), 2);

        return Math.sqrt(sum);
    }

    public double vectorlength() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    public Point rotate(double radians) {
		return new Point(this.x*Math.cos(radians) - this.y*Math.sin(radians),
				         this.x*Math.sin(radians) + this.y*Math.cos(radians));
	}

    public String toString() {
        return (int)this.x + "," + (int)this.y;
    }

@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

}
