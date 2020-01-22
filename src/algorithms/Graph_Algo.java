package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{


	private DGraph dgraph;


	public Graph_Algo()
	{
		//requires init
	}
	public Graph_Algo(graph _graph) {
		
		dgraph = (DGraph) _graph;
	}



	@Override
	public void init(graph g) {

		dgraph = (DGraph) g;
	}



	@Override
	public void init(String file_name) {
		
		
	}

	@Override
	public void save(String file_name) {
		
		
	}
	
	/**
	 * Uses a non-recursive implementation of depth-first-search to mark all connected nodes.
	 * @param nd - the Node to start with.
	 * @return true iff the graph is connected. 
	 */
	public boolean DFS(Node nd)
	{

		Stack<node_data> stack = new Stack<node_data>();
		stack.push(nd);

		while(!stack.isEmpty())
		{
			Node w = (Node) stack.pop();

			if(w.getTag() == 0) // the node has not been seen before
			{
				w.setTag(1);
				//travel to all of its connections
				try
				{
					Iterator<edge_data> it = dgraph.getE(w.getKey()).iterator();

				}
				catch(NullPointerException e){
					return false;
				}
				Iterator<edge_data> it = dgraph.getE(w.getKey()).iterator();
				while(it.hasNext())
				{
					Edge next = (Edge) it.next();
					stack.push(dgraph.getNode(next.getDest()));
				}

			}

		}

		for(node_data n: dgraph.getV())
		{
			if(n.getTag() == 0)
			{
				return false;
			}
			n.setTag(0); // resets the node
		}

		return true;
	}

	public boolean isConnected() {


		Collection<node_data> col = dgraph.getV();
		if(col.isEmpty())
		{
			return true;
		}
		return DFS((Node) col.iterator().next());
	}









	//	
	//	Stack<node_data> stack = new Stack<node_data>();
	//	
	//	stack.push(dgraph.getV().iterator().next()); // push first node
	//	
	//	while(!stack.isEmpty())
	//	{
	//		Node v = (Node)stack.pop();
	//		if(v.getTag() == 0)
	//		{
	//			v.setTag(1);
	//			
	//			for(node_data w: dgraph.getV())
	//			{
	//				stack.push(w);
	//			}
	//		}
	//  }

	//	for(node_data nd: dgraph.getV())
	//	{
	//		if(nd.getTag() == 0)
	//		{
	//			return false;
	//		}
	//		nd.setTag(0); // resets the node
	//	}
	//	
	//	
	//	return true;
	//	




	@Override
	public double shortestPathDist(int src, int dest) {
		this.Dijkstra(src);

		return dgraph.getNode(dest).getWeight();

	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
			
		this.Dijkstra(src);
		
		// get all Nodes that point towards dest
		// from those, add the minimum weighted node to a list
		// change dest to be the minimum one
		// repeat until dest is equal to source
		
		ArrayList<node_data> toReturn = new ArrayList<node_data>();
		toReturn.add(dgraph.getNode(dest));

		while(dest != src)
		{
			ArrayList<node_data> temp = new ArrayList<node_data>();
			for(node_data nd: dgraph.getV())
			{
				
				try {
					if(dgraph.getEdge(nd.getKey(), dest) != null) // if this is not null, then an edge is found
					{
						temp.add(nd);
					}
				}
				catch(NullPointerException e){
					continue;
				}
				
			}
			
			
			//now that all edges are found, we only save the minimum one in a  list.
			if(temp.isEmpty()) // if temp is empty we don't have a valid path
			{
				return null;
			}
			else
			{
				node_data min = min(temp);
				toReturn.add(min);
				
				//we change dest to be the minimum one, so we can repeat the process until we get to the src
				
				dest = min.getKey();
			}


			
		}
		
		Collections.reverse(toReturn);
		return toReturn;

	}

//	public node_data min(ArrayList<node_data> mySet) {
//		
//		node_data min = new Node();
//		min.setWeight(Double.MAX_VALUE);
//		for(node_data nd: mySet)
//		{
//			if(min.getWeight() > nd.getWeight())
//			{
//				min = nd;
//			}
//		}
//		return min;
//
//
//	}


	public Node min(Collection<node_data> mySet)
	{
		
		Iterator<node_data> it = mySet.iterator();
		Node min = (Node) it.next();
		while(it.hasNext())
		{
			Node temp = (Node) it.next();
			if(temp.getWeight() < min.getWeight())
			{
				min = temp;
			}	
		}
		
		return min;
	}


	public void Dijkstra(int src)
	{

		//initialization


		//1
		for(node_data nd: dgraph.getV())
		{
			if(nd.getKey() == src)
			{
				nd.setWeight(0);
				continue;
			}
			nd.setWeight(Double.MAX_VALUE);
			
		}
		
		//2
//		Iterator<node_data> it = dgraph.getV().iterator();
//
//		while(it.hasNext())
//		{
//			Node temp = (Node) it.next();
//			if(temp.getKey() == src)
//			{
//				temp.setWeight(0);
//			}
//			else
//			{
//				temp.setWeight(Double.MAX_VALUE);
//			}
//		}

		ArrayList<node_data> mySet = new ArrayList<node_data>();
		double alt = 0;
		mySet.addAll(dgraph.getV());
		while(!mySet.isEmpty())
		{
			node_data u = min(mySet);
			mySet.remove(u);

			try {
				dgraph.adjacentNodes(u);
			}
			catch(NullPointerException e){
				continue;
			}
			for(edge_data e: dgraph.getE(u.getKey()))
			{
				node_data adj = dgraph.getNode(e.getDest());
				if(!mySet.contains(adj))
				{
					continue;
				}
				alt = u.getWeight() + e.getWeight();
				if(alt < adj.getWeight())
				{
					adj.setWeight(alt);
				}


			}



		}


	}


	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
