package telran.webinar;

import java.util.Arrays;

public class Line {
	private int id;
	private String name;
	private Point[] points;
	public Line(int id, String name, Point[] points) {
		super();
		this.id = id;
		this.name = name;
		this.points = points;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Point[] getPoints() {
		return points;
	}
	public void setPoints(Point[] points) {
		this.points = points;
	}
	@Override
	public String toString() {
		String str = String.format("id = %d name = %s\n", id, name);
		for(Point point: points) {
			str+=point.toString();
		}
		return str;
	}
	
	
}
