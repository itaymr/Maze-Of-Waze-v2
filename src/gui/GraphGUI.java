package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFrame;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.StdDraw;

public class GraphGUI extends JFrame{

	private graph gr;
	
	private double min_x = 0, min_y = 0, max_x = 500, max_y = 500;
	private int resolution = 500;
	private int width = 1980, height = 1080;
	private Collection<node_data> vertices;
	
	
	
	public GraphGUI(DGraph dg) {
		this.init(dg);
	}


	public void init(DGraph dg)
	{
		
		this.gr = dg;
		
		
		double x, y;
		vertices = gr.getV();
		
		Iterator<node_data> it = vertices.iterator();
		node_data temp = it.next();
		min_x = temp.getLocation().x();
		max_x = temp.getLocation().x();
		min_y = temp.getLocation().y();
		max_y = temp.getLocation().y();

		while(it.hasNext())
		{
			temp = it.next();
			
			if(temp.getLocation().x() < min_x)
			{
				min_x = temp.getLocation().x();
			}
			else if(temp.getLocation().x() > max_x)
			{
				max_x = temp.getLocation().x();
			}
			
			if(temp.getLocation().y() < min_y)
			{
				min_y = temp.getLocation().y();
			}
			else if(temp.getLocation().y() > max_y)
			{
				max_y = temp.getLocation().y();
			}
		}
		
		System.out.println("max_y " + max_y);
		System.out.println("min_y " + min_y);
		System.out.println("max_x " + max_x);
		System.out.println("min_x " + min_x);


		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setVisible(true);
	}
	
	
	public void paint(Graphics d)
	{
		super.paint(d);

		Graphics2D gr2 = (Graphics2D) d;
		d.setColor(Color.BLUE);
		
		
		Collection<node_data> nodes = gr.getV();
		for(node_data nd : nodes)
		{

			int x = scaleX(nd.getLocation().x());
			int y = scaleY(nd.getLocation().y());

			System.out.println("x is : " + x);
			System.out.println("y is : " + y);

			d.setColor(Color.BLUE);
			d.fillOval(x,  y,  7,  7);
			d.setColor(Color.BLACK);
			d.drawString(String.valueOf(nd.getKey()), x, y- 10);
			try {
				for(edge_data e: gr.getE(nd.getKey()))
				{
					int x0 , x1, y0, y1;
					x0=scaleX(gr.getNode(e.getSrc()).getLocation().x());
					x1=scaleX(gr.getNode(e.getDest()).getLocation().x());
					y0=scaleY(gr.getNode(e.getSrc()).getLocation().y());
					y1=scaleY(gr.getNode(e.getDest()).getLocation().y());
					d.setColor(Color.RED);
					d.drawLine(x0,  y0,  x1,  y1);
					
					int qx =(( (((x1 + x0)/2)+x1)/2   ) + x1) / 2;
					int qy =(( (((y1 + y0)/2)+y1)/2   ) + y1) / 2;	
					
					d.setColor(Color.ORANGE);
					d.fillOval(qx,  qy,  5,  5);
					d.setColor(Color.BLACK);
					d.drawString(String.valueOf(e.getWeight()), (x1 + x0)/2, (y1 + y0)/2);

					
//					StdDraw.setPenColor(Color.YELLOW);

//					StdDraw.circle(x, y, 0.007);
//
//					StdDraw.filledCircle(x, y, 0.007);
//					StdDraw.setPenColor(Color.RED);
//					StdDraw.setPenRadius(0.003);
//					StdDraw.text((x1 + x0)/2, (y1 + y0)/2, Double.toString(e.getWeight()));
//					StdDraw.setPenRadius();						
				}
			}
			catch(NullPointerException e)
			{
				continue;
			}
			
		}
		
	}
	
	private int scaleY(double y) {
		
		double sensitivity = (max_y - min_y)/resolution;
		System.out.println("sensitivity" + sensitivity);
		
		return (int) ((y - min_y) * 1/sensitivity) + 100;
	}


	private int scaleX(double x) {
		
		double sensitivity = (max_x - min_x)/resolution;
		
		return (int) ((x - min_x) * 1/sensitivity) + 100;
	}


	public void update()
	{
		this.repaint();
	}
	
}
