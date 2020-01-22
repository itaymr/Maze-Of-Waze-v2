package dataStructure;

public class Edge implements edge_data{

	private int src, dest, tag;
	private double weight;
	private String info;

	/**
	 * Constructs and edge with a source, dest keys and a weight
	 * @param src - source key
	 * @param dest - destination key
	 * @param w - weight
	 */
	public Edge(int src, int dest, double w) {
		this.src=src;
		this.dest=dest;
		this.tag=0;
		this.weight=w;
		this.info="";
	}

	/**
	 * Copy constructor
	 * @param o the edge to copy
	 */
	public Edge(Edge o) {
		this.src=o.src;
		this.dest=o.dest;
		this.tag=o.tag;
		this.weight=o.weight;
		this.info=o.info;
	}


	@Override
	public int getSrc(){ 
		return this.src; 
	}

	@Override
	public int getDest(){ 
		return this.dest; 
	}

	@Override
	public double getWeight(){
		return this.weight;
	}

	@Override
	public String getInfo(){ 
		return this.info; 
	}

	@Override
	public void setInfo(String s){
		this.info = s;
	}

	@Override
	public int getTag(){ 
		return this.tag; 
	}

	@Override
	public void setTag(int t){
		this.tag = t;
	}

}