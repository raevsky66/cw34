package telran.webinar;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinesAppl {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Point p1 = new Point(10,10);
		Point p2 = new Point(5,5);
		Point p3 = new Point(3,7);
		Point p4 = new Point(4,12);
		Point p5 = new Point(3,62);
		Point p6 = new Point(7,40);
		Point p7 = new Point(5,0);

		Point[] points1 = new Point[] {p1,p2,p3};
		Point[] points2 = new Point[] {p4,p5,p6};
		Point[] points3 = new Point[] {p1,p5,p3,p7,p6};
		
		Line line1 = new Line(1,"one",points1);
		Line line2 = new Line(2,"two",points2);
		Line line3 = new Line(3,"three",points3);
		
		List<Line> lines = new ArrayList<>();
		lines.add(line2);
		lines.add(line1);
		lines.add(line3);

		for (Line line:lines) {
			System.out.println(line.toString());
			
		}
		
		LineDataHandler.writeLines(lines);
		System.out.println("================");
		
		List<Line>lines1 = LineDataHandler.readLines();
		for (Line line:lines1) {
			System.out.println(line.toString());
			
		}		
		System.out.println("================");
		
		Line l = LineDataHandler.readLineById(1);
		System.out.println(l);
		
		LineDataHandler.rewriteCoordinatesLineById(1, 0, 4, 3);
		System.out.println("================");
		
//		l = LineDataHandler.readLineById(1);
//		System.out.println(l);
		lines1 = LineDataHandler.readLines();
		for (Line line:lines1) {
			System.out.println(line.toString());
			
		}				
		
	}

}
