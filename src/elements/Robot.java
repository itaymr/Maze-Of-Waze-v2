package elements;

import utils.Point3D;

public class Robot {

	
	private int id;
	private double value;
	private Point3D loc;
	private int src;
	private int dest;
	private int score;
	
	public Robot(double value, int id, Point3D location)
	{
		this.value = value;
		this.id = id;
		this.loc = location;
		this.score = 0;
	}
	
	public double getValue()
	{
		return value;
	}
	public double getID()
	{
		return id;
	}
	public int getSrc() {
		return src;
	}
	
	public int getDest()
	{
		return dest;
	}
	
	public Point3D getLocation()
	{
		return loc;
	}
	
	
	
	
}
