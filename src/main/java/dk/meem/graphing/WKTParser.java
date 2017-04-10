package dk.meem.graphing;

import java.util.ArrayList;
import dk.meem.graphing.primitive.Point;

public class WKTParser {
	
	
	/* Multipolygon:
	 * (((723938.2021 6217035.3268, 724013.9392 6216954.9595, 724021.2153 6216954.9595, 724069.8325 6217001.5924, 724070.1633 6217008.5377, 723994.4261 6217088.9051, 723988.1423 6217088.9051, 723937.8713 6217042.2722, 723938.2021 6217035.3268)),
	 * ((724023.8124 6216933.982, 724105.0681 6216851.5697, 724150.178 6216899.8604, 724069.2115 6216979.9595, 724023.8124 6216933.982)),
	 * ((723926.407 6217173.399, 723968.6292 6217123.6317, 724044.6971 6217190.1084, 724005.44 6217236.4486, 723926.407 6217173.399)))
	 */
	private static ArrayList<String> parseWKTMulti(String geometry) {
		ArrayList<String> slist = new ArrayList<String>();
		
		String[] parts = geometry.trim().split("\\)\\)\\s*,");
		
		for (String part : parts) {
			String tmp = part.trim().replaceAll("[\\(]+",  "").replaceAll("[\\)]+", "").trim();
			if ( ! tmp.isEmpty()) {
				slist.add(tmp);
			}
		}
		
		return slist;
	}
	
	private static ArrayList<Point> parseWKTPolygon(String geometry) {
		// This expects geometry to be a list of 2D points, e.g.
		// 1.3 5.6, 4.0 7.9

		ArrayList<Point> plist = new ArrayList<Point>();
		
		String[] parts = geometry.trim().split(",");
		for (String part : parts) {
			String[] coords = part.trim().split("\\s+");
			plist.add(new Point(Double.parseDouble(coords[0]), Double.parseDouble(coords[1])));
		}
		
		return plist;
	}
	
	/* This moves all points in list.
	 * The first point is subtracted from all.
	 */
	private static ArrayList<Point> transform(ArrayList<Point> plistin) {
		ArrayList<Point> plistout = new ArrayList<Point>();
		
		Point basepoint = plistin.get(0);

		for (int i=0; i<plistin.size(); i++) {
			Point p = plistin.get(i).subtract(basepoint);
			plistout.add(p);
		}
		
		return plistout;
	}
	
	public static ArrayList<Polygon> fromWktMulti(String geometry) {
		ArrayList<String> slist = parseWKTMulti(geometry);
		ArrayList<Polygon> plist = new ArrayList<Polygon>();
		
		for (String s : slist) {
			System.out.println("s="+s);
			plist.add(unitFromWkt(s));
		}
		return plist;
	}

	public static Polygon fromWkt(String geometry) {
		return new Polygon(parseWKTPolygon(geometry));
	}

	public static Polygon unitFromWkt(String geometry) {
		return new Polygon(transform(parseWKTPolygon(geometry)));
	}
	
}
