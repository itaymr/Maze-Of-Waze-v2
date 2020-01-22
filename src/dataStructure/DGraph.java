package dataStructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.JSONObject;
import org.json.simple.JSONArray;

import gui.GraphGUI;
import utils.Point3D;

public class DGraph implements graph{

	int modeCounter = 0 ;
	int edgeCounter = 0 ;

	public HashMap<Integer,node_data> nodes = new HashMap<Integer,node_data>();
	public HashMap<Integer,HashMap<Integer,edge_data>> edges =new HashMap<Integer,HashMap<Integer,edge_data>>();

	public GraphGUI listener;
	
	
	public DGraph () {
		this.modeCounter = 0;
		this.edgeCounter = 0;
		this.nodes = new HashMap<Integer,node_data>();
		this.edges =new HashMap<Integer,HashMap<Integer,edge_data>>();
	}

	
	public void init(String graph)
	{
		try {
			JSONObject obj = new JSONObject(graph);
			org.json.JSONArray edges = obj.getJSONArray("Edges");
			org.json.JSONArray nodes = obj.getJSONArray("Nodes");
			
			for(int n = 0; n < nodes.length();n ++)
			{
				JSONObject currNode = (JSONObject) nodes.get(n);
//				org.json.JSONArray pos = jobj.getJSONArray("pos");
				
				
				String test[] = currNode.getString("pos").split(",");
				
				double x = Double.parseDouble(test[0]);
				double y = Double.parseDouble(test[1]);
				double z = Double.parseDouble(test[2]);

				Point3D point = new Point3D(x, y, z);
				this.addNode(new Node(point));
			}
			
			
			for(int e = 0; e < edges.length(); e++)
			{
				JSONObject currEdge = (JSONObject) edges.get(e);
				this.connect(
						currEdge.getInt("src"),
						currEdge.getInt("dest"),
						currEdge.getInt("w")
						);
			}
		}catch(Exception e)
		{
			System.out.println("Error init: " + e);
		}
		
		
		
	}
	@Override
	public node_data getNode(int key) {
		return this.nodes.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if(this.edges.get(src) == null)
		{
			return null;
		}
		return this.edges.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		this.nodes.put(n.getKey(),(Node)n);
	}

	@Override
	public void connect(int src, int dest, double w) {
		
		if (this.nodes.get(src)==null || this.nodes.get(dest)== null) {
			System.out.println("Can't establish connectiont between the nodes");
		}
		else {
			Edge temp = new Edge(src,dest,w);
			if (this.edges.get(src) == null) {
				this.edges.put(src, new HashMap<Integer,edge_data>());
				this.edges.get(src).put(dest, temp);
				edgeCounter++;
				this.modeCounter++;
			}
			else {
				this.edges.get(src).put(dest, temp);
				edgeCounter++;
				this.modeCounter++;
			}
		}
	}

	@Override
	public Collection<node_data> getV() {
		return nodes.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		if (this.edges.isEmpty()) {
			return null; 
		}
		if (this.edges.get(node_id)==null) {
			return null; 
		}
		return this.edges.get(node_id).values(); 
	}

	@Override
public node_data removeNode(int key) {
		
		if (this.nodes.get(key)==null) { System.out.println("No such node" ); return null; }
		
		node_data ans = new Node((Node)nodes.get(key));
		ArrayList<Integer> toD = new ArrayList<Integer>();
		
		this.edges.forEach((k, v) -> {
			if (v.get(key)!=null) {
				v.remove(key);
				edgeCounter--;
				this.modeCounter++;
				if (v.isEmpty()) {
					toD.add(k);
				}
			}
		});
		for (int i : toD) {
			this.edges.remove(i);
		}
		edgeCounter -= this.edges.get(key).size();
		this.edges.remove(key);
		this.nodes.remove(key);
		this.modeCounter++;

		return ans;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if (this.edges.get(src).get(dest)==null) { return null; }
		edge_data e = new Edge((Edge)this.edges.get(src).get(dest));
		
		this.edges.get(src).remove(dest);
		edgeCounter--;
		this.modeCounter++;
		return e;
	}


	@Override
	public int nodeSize() {
		return nodes.size();
	}

	@Override
	public int edgeSize() {
		return edgeCounter;
	}

	@Override
	public int getMC() {
		return modeCounter;
	}

	public ArrayList<Node> adjacentNodes(node_data u) throws NullPointerException {
		
		Iterator<edge_data> it = this.getE(u.getKey()).iterator();
		

		ArrayList<Node> list = new ArrayList<Node>();
		while(it.hasNext())
		{
			list.add((Node) this.getNode(it.next().getDest()));
			
		}
		
		return list;
	}

	public double length(node_data u, node_data adj) {
		
		return this.getEdge(u.getKey(), adj.getKey()).getWeight();
	}


	public void addListener(GraphGUI graphGUI) {
		
		this.listener = graphGUI;
	}


}
