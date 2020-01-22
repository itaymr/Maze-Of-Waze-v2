package dataStructure;

import utils.Point3D;

public class Node implements node_data {
	
	private Point3D location;
	private int tag = 0;
	private String info = "";
	private int key;
	private double weight = 0;

	private static int keyGeneretor = 0;
	
	/**
	 * Constructs a Node with a key.
	 */
	public Node () {
		this.key=keyGeneretor;
		this.tag=0;
		this.weight=0;
		this.info="";
		keyGeneretor++;
	}
	
	/**
	 * Copy constructor
	 * @param o - the other node to copy
	 */
	public Node (Node o) {
		this.key=o.key;
		this.tag=o.tag;
		this.weight=o.weight;
		this.info=o.info;
		this.location=o.location;
	}

	/**
	 * Constructs a node with a location.
	 * @param loc - Point3D location
	 */
	public Node (Point3D loc) {
		this.key=keyGeneretor;
		this.location=loc;
		this.tag=0;
		this.weight=0;
		this.info="";
		keyGeneretor++;
	}
	
	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public Point3D getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) {
		this.location = p;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight =w;
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		this.info=s;
		
	}
	public void setInfo(int s) {
		this.info=Integer.toString(s);
		
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag=t;
		
	}

}
