package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import dataStructure.DGraph;
import utils.StdDraw;

public class Functions_GUI implements functions {

	private LinkedList<function> _functions = new LinkedList<function>();

	@Override
	public int size() {

		return _functions.size();
	}

	@Override
	public boolean isEmpty() {
		return _functions.isEmpty();
	}

	@Override
	public boolean contains(Object o) {


		return _functions.contains(o);
	}

	@Override
	public Iterator<function> iterator() {
		Iterator<function> iter = _functions.iterator();

		return iter;
	}

	@Override
	public Object[] toArray() {
		function f[] = new function[this.size()];
		Iterator<function> it = this.iterator();
		int i = 0;
		while(it.hasNext())
		{
			f[i] = it.next();
			i++;
		}
		return f;
	}

	@Override
	public <T> T[] toArray(T[] a) {

		return _functions.toArray(a);
	}

	@Override
	public boolean add(function e) {
		return 	_functions.add(e);
	}

	@Override
	public boolean remove(Object o) {

		return 	_functions.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {

		return _functions.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends function> c) {

		return _functions.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return 	_functions.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return _functions.retainAll(c);
	}

	@Override
	public void clear() {
		_functions.clear();
	}

	@Override
	public void initFromFile(String file) throws IOException {
		
		Gson gson = new Gson();
		FileReader fr = new FileReader(file);
		//ֿtoArray();
		gson.fromJson(fr, (Type) _functions);
		System.out.println("functions:" +_functions);
		fr.close();		
		this.drawFunctions();

	}

	@Override
	public void saveToFile(String file) throws IOException {
		Gson gson = new Gson();
		FileWriter fw = new FileWriter(file);
		gson.toJson(_functions, fw);

		fw.close();
	}

	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		//////Drawing Axis
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.line(i, ry.get_min(), i, ry.get_max());
		}
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}

		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		//////Numbers on Axis
		StdDraw.setFont(new Font("Ariel", Font.BOLD, 14));
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());

		
		
		for (double i = rx.get_min(); i <= rx.get_max(); i++) {
			StdDraw.text(i, -0.30, Integer.toString(Math.toIntExact((long) i)));
		}
		for (double i = ry.get_min(); i <= ry.get_max(); i++) {
			StdDraw.text(-0.20,i, Integer.toString(Math.toIntExact((long) i)));
		}
		//////End of Drawing Axis
		int d=1;
		for (function f : _functions) {
			double step = (Math.abs(rx.get_min())+Math.abs(rx.get_max()))/resolution;
			int R = (int)(Math.random()*256);
			int G = (int)(Math.random()*256);
			int B= (int)(Math.random()*256);
			Color color = new Color(R, G, B);
			StdDraw.setPenColor(color);
			for (double i = rx.get_min(); i < rx.get_max(); i+=step)
			{
				StdDraw.line(i, f.f(i), i+step, f.f(i+step));
			}
			System.out.print(d+") "+"Color[R="+R+", G="+G+", B="+B+"] - f(x) = ");
			System.out.println(f.toString());

			d++;
		}

	}

	@Override
	public void drawFunctions(String json_file) {
		try
		{
			Object obj = new JSONParser().parse(new FileReader(json_file));
			JSONObject jo = (JSONObject) obj;

			int width = (int)jo.get("width");
			int height = (int)jo.get("height");
			String rangey = (String)jo.get("Range_Y");
			String rangex = (String)jo.get("Range_X");
			int resolution = (int)jo.get("resolution");

			//[10, -10]
			double ymin, ymax, xmax, xmin;

			ymin = Double.parseDouble(rangey.substring(1, rangey.indexOf(',')));
			ymax = Double.parseDouble(rangey.substring(rangey.indexOf(',') + 1, rangey.indexOf("]") - 1));
			xmax = Double.parseDouble(rangex.substring(rangex.indexOf(',') + 1, rangex.indexOf("]") - 1));
			xmin = Double.parseDouble(rangex.substring(1, rangex.indexOf(',')));

			Range ry = new Range(ymin, ymax);
			Range rx = new Range(xmin, xmax);

			this.drawFunctions(width, height, rx, ry, resolution);


		}
		catch(Exception e)
		{

		}
	}

	public void drawFunctions() {
		drawFunctions(480, 480, new Range(-2, 2), new Range(-2, 2), 0);

	}

	public function get(int i) {


		return _functions.get(i);
	}

}
