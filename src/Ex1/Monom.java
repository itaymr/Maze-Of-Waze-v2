
package Ex1;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		if(a == 0)
		{
			this.set_power(0);
		}
		else
		{
			this.set_power(b);
		}
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return derivative
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	/**
	 * Checks if another monom ot is equal to this one
	 * @param ot
	 * @return
	 */
	public boolean equals(Monom ot)
	{
		if(ot.get_coefficient() == _coefficient && ot.get_power() == _power)
		{
			return true;
		}
		return false;
	}

	public Monom(String s) {
		
		//2*x^2*x
		//2, x^2, x
		

		if(s.equals("0") || s.equals("-0"))
		{
			_power = 0;
			_coefficient = 0;
			return;
		}
		String str[];
		Monom m = new Monom(1, 1);
		if(s.contains("*"))
		{
			str = s.split("*");
			for(String st : str)
			{
				st = st.replaceAll("*", "");
				Monom m2 = new Monom(st);
				m.multiply(m2);
			
			}
			s = m.toString();		
			
		}
		if(s.contains("^"))
		{
			str = s.split("x", 2); // 3 3x 3x^2 3 "" 3 "" 3 ^2
			if(str[0].isEmpty())
			{
				str[0] = "1";
			}

			
			_power = Integer.parseInt(str[1].substring(1));


		}
		else if (s.contains("x"))
		{
			if(s.equals("x"))
			{
				this._coefficient = 1;
				this._power = 1;
				return;
			}
			if(s.equals("-x"))
			{
				this._coefficient = -1;
				this._power = 1;
				return;
			}
			str = s.split("x", 3); // 3 3x 3x^2 3 "" 3 "" 3 ^2
			_power = 1;


		}
		else
		{

			_power = 0;
			_coefficient = Double.parseDouble(s);
			return;
		}
		
		if(s.contains("-x"))
		{
			_coefficient = -1;
		}
		else
		{
		_coefficient = Double.parseDouble(str[0].substring(0, str[0].length()));
		}
		
	
	
	}
	
/**
 * Adds monom m to this monom, this can only apply for monoms with equal powers
 * @param m - the monom to add
 */
	public void add(Monom m) {
		
		if(m == null)
		{
			System.out.println("invalid monom");// throw error
		}
		else if(m._power != this._power)
		{
			System.out.println("unable to add different powers");
			return;
		}
		
		this._coefficient += m._coefficient;		
	
	}
	
	
	
	/**
	 * Multiplies this Monom by another monom d
	 * @param d
	 */
	public void multiply(Monom d) {
		
		if(d == null)
		{
			System.out.println("invalid monom");// throw error
		}
		if(d.equals(Monom.ZERO))
		{
			this._coefficient = 0;
			return;
		}
		this._power += d._power;
		this._coefficient *= d._coefficient;
		}
	
//	public Monom reverse()
//	{
//		return new Monom(this._coefficient*-1, this._power);
//	}
//	
	/**
	 * Reverses the Monom, ax^2 turns into -a
	 */
	public void reverse()
	{
		this._coefficient *= -1;
	}
	
	/**
	 * Converts the Monom to a string
	 */
	public String toString() {
		
		
		String ans = "";
		if(_coefficient == 0)
		{
			return "0";
		}

		if(_power == 0)
		{
			ans = ans + _coefficient;
		}
		else if(_power == 1)
		{
			if(_coefficient == 1)
			{
				ans += "x";
			}
			ans = ans + _coefficient + "x";
		}
		else
		{
			if(_coefficient == 1)
			{
				ans+= ans + "x^" + this._power;
			}
			else {
			ans = ans + this._coefficient + "x^" + this._power;
			}

		}
		return ans;
	}
	
	/**
	 * Substracts a Monom ot from this monom, can only be done for equal powers
	 * @param ot - the other Monom
	 */
	public void substract(Monom ot)
	{
		if(ot.get_power() != this.get_power())
		{
			System.out.println("Cannot be done");
			return;
		}
		ot._coefficient*=-1;
		this.add(ot);
		ot._coefficient*=-1;
	}
	/**
	 * Sets the coefficient of a monom
	 * @param d - the double to set as coefficient
	 */
	public void setCoefficient(double d)
	{
		this._coefficient = d;
	}
	/**
	 * Sets the power of the Monom
	 * @param pow - the intereget to set as the power
	 */
	public void setPower(int pow)
	{
		this._power = pow;
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	@Override
	public function initFromString(String s) {
		
		Monom m = new Monom(s);
		return m;
	}
	@Override
	public function copy() {
		
		return (function)new Monom(this);
		
	}

	
	
}
