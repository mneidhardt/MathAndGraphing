package dk.meem.graphing.test;

import dk.meem.graphing.Polygon;
import dk.meem.graphing.WKTParser;
import junit.framework.TestCase;

public class WKTParserTest extends TestCase {
	
	public WKTParserTest(String testName) {
		super(testName);
	}
	
	public void test1() {
		String geometri = "724461.42 6178878.5, 724441.27 6178854.96, 724427.51 6178866.74, 724431.79 6178871.74, 724416.42 6178884.91, 724412.76 6178888.03, 724334.3 6178796.21, 724339.52 6178791.74, 724329.82 6178780.41, 724357.88 6178756.39, 724351.79 6178749.28, 724439.41 6178674.27, 724445.58 6178681.48, 724472.95 6178658.04, 724496.32 6178685.33, 724503.5 6178679.18, 724554.56 6178738.82, 724547.07 6178745.23, 724570.96 6178773.13, 724543.79 6178796.39, 724549.52 6178803.08, 724527.32 6178822.09, 724461.42 6178878.5";
		int count = geometri.trim().split(",").length;
	
		Polygon pol1 = WKTParser.fromWkt(geometri);
		Polygon pol2 = WKTParser.unitFromWkt(geometri);
	
		assertTrue(pol1 instanceof Polygon);
		assertTrue(pol2 instanceof Polygon);
		
		assertEquals(count, pol1.size());
		assertEquals(count, pol2.size());
	}

}
