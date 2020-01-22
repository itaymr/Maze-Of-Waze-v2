package Ex1Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import algorithms.Graph_Algo;
import dataStructure.Node;
import dataStructure.DGraph;
import dataStructure.node_data;
import gui.GraphGUI;
import utils.Point3D;

class GraphTest {



	Node n1 = new Node();
	Node n2 = new Node();
	Node n3 = new Node();
	Node n4 = new Node();
	Node n5 = new Node();
	Node n6 = new Node();
	Node n7 = new Node();
	Node n8 = new Node();
	Node n9 = new Node();
	Node n10 = new Node();


	private DGraph dg = new DGraph();
	private Graph_Algo algo = new Graph_Algo();
	private GraphGUI gui = new GraphGUI(dg);



	@Test
	public void testAdd()
	{
		algo.init(dg);

		dg.addNode(n1);
		assertEquals(n1, dg.getNode(n1.getKey()));
		dg.addNode(n2);
		assertEquals(n2, dg.getNode(n2.getKey()));
		dg.addNode(n3);
		assertEquals(n3, dg.getNode(n3.getKey()));

		assertNotNull(dg.getNode(n1.getKey()));
		assertNotNull(dg.getNode(n2.getKey()));
		assertNotNull(dg.getNode(n3.getKey()));

	}
	@Test
	public void testConnect() {

		//connect 3 Nodes so that the graph is connected

		dg = new DGraph();
		algo = new Graph_Algo();
		algo.init(dg);
		dg.addNode(n1);
		dg.addNode(n2);
		dg.addNode(n3);

		dg.connect(n1.getKey(), n2.getKey(), 1);
		assertNotNull(dg.getEdge(n1.getKey(), n2.getKey()));
		dg.connect(n2.getKey(), n3.getKey(), 2);
		dg.connect(n3.getKey(), n1.getKey(), 3);
		assertEquals(algo.isConnected(), true);
		dg.addNode(n4);
		assertEquals(algo.isConnected(), false);
	}

	@Test
	public void testEdge()
	{

		dg = new DGraph();
		algo = new Graph_Algo();
		algo.init(dg);
		dg.addNode(n1);
		dg.addNode(n2);
		dg.addNode(n3);
		dg.connect(n1.getKey(), n2.getKey(), 1);
		dg.connect(n2.getKey(), n3.getKey(), 2);
		dg.connect(n3.getKey(), n1.getKey(), 3);

		assertNotNull(dg.getEdge(n1.getKey(), n2.getKey()));
		assertNotNull(dg.getEdge(n2.getKey(), n3.getKey()));
		assertNotNull(dg.getEdge(n3.getKey(), n1.getKey()));
		assertNull(dg.getEdge(n3.getKey(), n2.getKey()));
	}

	@Test
	public void testDijkstra()
	{
		dg = new DGraph();
		algo = new Graph_Algo();
		algo.init(dg);
		dg.addNode(n1);
		dg.addNode(n2);
		dg.addNode(n3);
		dg.addNode(n4);
		dg.addNode(n5);
		dg.addNode(n6);
		dg.connect(n4.getKey(), n5.getKey(), 11);
		dg.connect(n5.getKey(), n6.getKey(), 11);
		dg.connect(n4.getKey(), n3.getKey(), 12);
		dg.connect(n3.getKey(), n4.getKey(), 13);
		dg.connect(n1.getKey(), n6.getKey(), 1);
		dg.connect(n1.getKey(), n2.getKey(), 1);
		dg.connect(n2.getKey(), n3.getKey(), 2);
		dg.connect(n3.getKey(), n1.getKey(), 3);
		
		algo.Dijkstra(n1.getKey());

		assertEquals(n6.getWeight(), 1.0, 0.001);
		assertEquals(n2.getWeight(), 1.0, 0.001);
		assertEquals(n3.getWeight(), 3.0, 0.001);
	}
	
	@Test
	public void testShortestPath()
	{
		dg = new DGraph();
		algo = new Graph_Algo();
		algo.init(dg);
		dg.addNode(n1);
		dg.addNode(n2);
		dg.addNode(n3);
		dg.addNode(n4);
		dg.connect(n1.getKey(), n2.getKey(), 2);
		dg.connect(n1.getKey(), n3.getKey(), 1);
		dg.connect(n2.getKey(), n4.getKey(), 6);
		dg.connect(n3.getKey(), n4.getKey(), 5);

		n1.setInfo("n1");
		n2.setInfo("n2");
		n3.setInfo("n3");
		n4.setInfo("n4");


		ArrayList<node_data> list = (ArrayList<node_data>) algo.shortestPath(n1.getKey(), n4.getKey());
		assertEquals(list.get(0), n1);
		assertEquals(list.get(1), n3);
		assertEquals(list.get(2), n4);

	}


}
