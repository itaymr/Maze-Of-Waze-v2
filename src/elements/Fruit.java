package elements;

import utils.Point3D;

public class Fruit {

	
	private double value;
	private int type;
	private Point3D loc;
	private int src;
	private int dest;
	
	public Fruit(double value, int type, Point3D location)
	{
		this.value = value;
		this.type = type;
		this.loc = location;
	}
	
	public double getValue()
	{
		return value;
	}
	public double getType()
	{
		return type;
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
