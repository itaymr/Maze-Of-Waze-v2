package gameClient;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import org.json.JSONException;
import org.json.JSONObject;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import elements.Fruit;
import utils.Point3D;
import utils.StdDraw;

public class MyGameGUI extends JFrame implements ActionListener, Runnable, Observer{

	private graph gr;
	
	private double min_x = 0, min_y = 0, max_x = 500, max_y = 500;
	private int resolution = 500;
	private int width = 1980, height = 1080;
	private Collection<node_data> vertices;
	private int lvl;
	private game_service service;
	private ArrayList<Fruit> fruits;
	
	
	public MyGameGUI(DGraph dg, game_service serv, int level) {
		

		this.init(dg, serv, level);
	}


	public void init(DGraph dg, game_service serv, int level)
	{
		this.service = serv;
		this.lvl = level;
		this.gr = dg;
		
		service.addRobot(1);
		if(serv.getRobots().isEmpty())
		{
			System.out.println("isempty");
		}
		
		
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
		

		
		//		List<String> fruits = this.service.getFruits();
		for(String robot: service.getRobots())
		{
			System.out.println("rob: " + robot);
			try {
				JSONObject obj = new JSONObject(robot);
				JSONObject jfruit = obj.getJSONObject("Robot");
				
				String test[] = jfruit.getString("pos").split(",");
				
				int x = scaleX(Double.parseDouble(test[0]));
				int y = scaleY(Double.parseDouble(test[1]));
				

				d.setColor(Color.DARK_GRAY);
				d.drawRect(x,  y,  10,  10);
				d.fillRect(x, y, 10, 10);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		for(String fruit: service.getFruits())
		{

			try {
				JSONObject obj = new JSONObject(fruit);
				JSONObject jfruit = obj.getJSONObject("Fruit");
				
				String test[] = jfruit.getString("pos").split(",");
				
				int x = scaleX(Double.parseDouble(test[0]));
				int y = scaleY(Double.parseDouble(test[1]));
				

				d.setColor(Color.GREEN);
				d.drawRect(x,  y,  10,  10);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private int scaleY(double y) {
		
		double sensitivity = (max_y - min_y)/resolution;
		
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


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
