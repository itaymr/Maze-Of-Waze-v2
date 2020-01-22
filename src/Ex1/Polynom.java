package Ex1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

	private LinkedList<Monom> _monoms = new LinkedList<Monom>();
	private Monom_Comperator comp = new Monom_Comperator();
	
	/**
	 * Constructs the ZERO Polynom
	 */
	public Polynom() {

		_monoms.add(Monom.ZERO);
	}
	
	/**
	 * Creates a polynom from a linked list of monom
	 * @param monoms
	 */

	public Polynom(LinkedList<Monom> monoms)
	{
		this._monoms = monoms;
		this.reduce();
		this.sort();
	}


	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "5x + 2*(2x^2-4+4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		if(s.startsWith("+"))
		{
			s = s.substring(1);
		}
		s = s.replaceAll(" ", "");
		s = s.replaceAll("\\-", "+-");

		String[] str = s.split("[+]| ");
		for(String token: str)
		{
			if(!token.isEmpty())
			{
				this.add(new Monom(token));
			}
		}

		this.sort();
	}

	public double f(double x) {
		
		double sum = 0;
		
		for(Monom m : _monoms)
		{
			sum+= m.f(x);
		}
		return sum;
	}

	
	public void substract(Monom m1)
	{
		if(m1.equals(Monom.ZERO))
		{
			return;
		}
		m1.reverse();
		this.add(m1);
		m1.reverse();
		this.reduce();
		this.sort();
	}
	public void add(Polynom_able p1) {

		if(p1 == null)
		{
			return;
		}
		if(p1 == this)
		{
			this.multiply(new Monom(2, 1));
		}

		Polynom ot = (Polynom)p1;	

		Iterator<Monom> it = ot.iteretor();

		while(it.hasNext())
		{
			_monoms.add(it.next());
		}

		this.sort();
		this.reduce();
	}

	@Override
	public void add(Monom m1) {

		if(m1 == null)
		{
			return; // add error later
		}
		if(_monoms.isEmpty())
		{
			_monoms.add(new Monom(m1));
			return;
		}
		if(!_monoms.isEmpty() && _monoms.getFirst().get_coefficient() == 0)
		{
			if(m1 == null) return;
			if(m1.get_coefficient() == 0) return;

			_monoms.removeFirst();
			_monoms.add(new Monom(m1));
			return;
		}


		if(m1.get_coefficient() == 0)
		{
			return;
		}

		Iterator<Monom> it = this.iteretor();
		while(it.hasNext())
		{
			Monom element = it.next();
			if(element.get_power() == m1.get_power())
			{
				element.add(m1);
				return;
			}
		}
		_monoms.add(m1);


	}

	@Override
	public void substract(Polynom_able p1) {
		
		if(p1 == this)
		{
			_monoms.clear();
			_monoms.add(Monom.ZERO);
			return;
		}
		
		Polynom p = (Polynom)p1;
		p.reverse();
		this.add(p);
	}

	@Override
	public void multiply(Polynom_able p1) 
	{
		Polynom p2 = (Polynom)p1;
		p2.reduce();
		p2.sort();
		Iterator <Monom>it = p2.iteretor();
		
		//(3x + 1) ( x + 2)
		// 3x => new Monom(3x) m => m.multiply(x) => pol.add(m), new Monom(3x) m => m.multiply(2), pol.add(m)
		// 1 => new monom(1)
		
		Polynom newPol = new Polynom();
		
		while(it.hasNext())
		{
			Monom itHandler = it.next();
			for(Monom m: _monoms)
			{	
				Monom temp = new Monom(m);
				temp.multiply(itHandler);
				newPol.add(temp);
			}
		}
		_monoms.clear();
		this.add(newPol);

		
	}
	
	/**
	 * Returns the size of the polynom, ie the number of elements it has
	 * @return monom size
	 */
	public int size()
	{
		return _monoms.size();
	}
	/**
	 * Reverses the functions. f(x) => -f(x)
	 */
	public void reverse()
	{
		for(Monom m: _monoms)
		{
			m.reverse();
		}
		this.sort();
	}
	@Override
	public boolean equals(Object p1) {
		
		Polynom ot = new Polynom();
		if(p1 instanceof Polynom_able)
		{
			ot = (Polynom)p1;
			ot = (Polynom) ot.copy();
		}
		
		ot.sort();
		
		ot.reduce();
		
		Iterator<Monom> it = ot.iteretor();
		
		if(ot.size() != this.size())
		{
			return false;
		}
		for(Monom m: _monoms)
		{
			if(!it.next().equals(m))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isZero() {
		if(_monoms.size() == 1)
		{
			if(_monoms.get(0).isZero())
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public double root(double x0, double x1, double eps) {

		double fx0 = f(x0);
		double midP = Math.min(x0, x1) + Math.abs(x1 - x0) / 2;
		double fMiddle = f(midP);

		if(f(x0)*f(x1)>0){
			System.err.println("runtimeException");
		}

		if (Math.abs(fMiddle) < eps) {
			return midP;
		}
		else if (fx0 * fMiddle > 0) { // both have the same sign
			return root(midP, x1, eps);
		}
		else {
			return root(x0, midP, eps);
		}
	}

	@Override
	public Polynom_able copy() {

		LinkedList<Monom> mono = new LinkedList();
		for(Monom m: _monoms)
		{
			Monom temp = new Monom(m);
			mono.add(temp);
		}
		return new Polynom(mono);
	}

	@Override
	public Polynom_able derivative() {

		for(Monom m: _monoms)
		{
			m.setCoefficient(m.get_coefficient()*m.get_power());
			m.setPower(m.get_power() - 1);
		}
		this.sort();
		this.reduce();
		return null;
	}

	@Override
	public double area(double x0, double x1, double eps) {

		int rectangles = (int) ((Math.max(x1, x0) - Math.min(x1, x0)) / eps);
		double sum = 0;
		for (int i = 0; i < rectangles; i++) {
			double height = f(x0);
			if (height<=0){
				x0 += eps;
				continue;
			}
			sum += eps * height;
			x0 += eps;
		}
		return sum;
	}

	@Override
	/**
	 * Returns an iterator over the monoms of a polynom
	 */
	public Iterator<Monom> iteretor() {
		Iterator<Monom> iter = _monoms.iterator();
		return iter;
	}
	@Override
	public void multiply(Monom m1) {

		Iterator<Monom> it = this.iteretor();

		while(it.hasNext())
		{
			it.next().multiply(m1);
		}
		this.reduce();
	}

	/**
	 * Sorts the polynom by power from biggest to smallest
	 */
	public void sort()
	{
		_monoms.sort(comp);
	}

	
	/**
	 * Reduces multiple expressions in the Polynom - e.g 3x + 3x becomes 6x
	 */

	public void reduce()
	{
		LinkedList<Monom> remove = new LinkedList();


		for(int i = 0; i < _monoms.size(); i++)
		{
			if(_monoms.get(i).get_coefficient() == 0)
			{
				remove.add(_monoms.get(i));
				continue;
			}
			for(int j=i; j<_monoms.size(); j++)
			{

				if( i != j && _monoms.get(i).get_power() == _monoms.get(j).get_power())
				{
					_monoms.get(i).add(_monoms.get(j));
					remove.add(_monoms.get(j));
				}
			}
		}
		_monoms.removeAll(remove);
		if(_monoms.isEmpty())
		{
			_monoms.add(Monom.ZERO);
		}
	}

/**
 * Converts the Polynom into a string
 */
	public String toString()
	{
		if(_monoms.size() == 1)
		{
			if(_monoms.get(0).isZero()) {
				return "0";		
			}
		}
		String str = "";
		boolean flag = false;
		for(Monom e: _monoms)
		{
			if(e.get_coefficient() > 0)
			{
				if(e.equals(_monoms.getFirst()))
				{
					str += e.toString() + " ";
				}
				else
				{
					str+= "+ " + e.toString() + " ";

				}
			}
			if(e.get_coefficient() < 0)
			{
				str+= e.toString() + " ";
			}
			flag = true;
		}
		
		return str;
	}

@Override
public function initFromString(String s) {
	
	
	return (function)new Polynom(s);
	
}

}
