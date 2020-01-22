package Ex1;

public class ComplexFunction implements complex_function {


	private Operation op;
	private function left;
	private function right;
	private Polynom pol;
	
	

	public ComplexFunction(ComplexFunction f)
	{
		this.left = f.left;
		this.right = f.right;
		op = f.getOp();
	}
	public ComplexFunction(String s) {
		pol = new Polynom();
		left = pol.initFromString(s);
		op = Operation.None;
	}

	public ComplexFunction(String string, Polynom p1, Polynom p2) {
		left = p1.initFromString(p1.toString());
		right = p2.initFromString(p2.toString());
		if(string == "plus")
		{
			op = Operation.Plus;
		}
		if(string == "min")
		{
			op = Operation.Min;
		}
		if(string == "max")
		{
			op = Operation.Max;
		}
		if(string == "comp")
		{
			op = Operation.Comp;
		}
		if(string == "divid")
		{
			op = Operation.Divid;
		}

	}
	public ComplexFunction(String string, Polynom polynom, ComplexFunction cf3) {
		right = polynom;
		left = cf3;
		
		if(string == "plus")
		{
			op = Operation.Plus;
		}
		if(string == "min")
		{
			op = Operation.Min;
		}
		if(string == "max")
		{
			op = Operation.Max;
		}
		if(string == "comp")
		{
			op = Operation.Comp;
		}
		if(string == "divid")
		{
			op = Operation.Divid;
		}
	}
	public ComplexFunction(Polynom p3) {
		op = Operation.None;
		left = p3;
		right = null;
	}
	public ComplexFunction(function copy) {
		
		//copy can be polynom or complecxfunction
		if(copy instanceof Polynom)
		{
			left = copy;
			right = null;
			return;
		}
		left = new ComplexFunction(((ComplexFunction) copy).left());
		right = new ComplexFunction(((ComplexFunction) copy).right());

		
	}
	@Override
	public double f(double x) {
		if(left == null)
		{
			return 0;
		}
		else if(right == null)
		{
			return left.f(x);
		}

		else if(op == Operation.Plus)
		{
			return left.f(x) + right.f(x);
		}
		else if(op == Operation.Divid)
		{
			return left.f(x) /  right.f(x);
		}
		else if(op == Operation.Max)
		{
			return Math.max(left.f(x),right.f(x));
		}
		else if(op == Operation.Min)
		{
			return Math.min(left.f(x),right.f(x));
		}
		else if( op == Operation.Comp)
		{
			return left.f(right.f(x));
		}
		else
		{
			return left.f(x) * right.f(x);
		}


	}

	@Override
	public function initFromString(String s) {

		return new ComplexFunction(s);
	}

	public function copy() {
		ComplexFunction cp = new ComplexFunction(this);
		return cp;
	}

	@Override
	public void plus(function f1) {
		temp(f1);
		op = Operation.Plus;


	}

	public void temp(function f1)
	{
		if(right == null)
		{
			right = f1;
		}
		else
		{
			left = new ComplexFunction(this);
		}
	}
	@Override
	public void mul(function f1) {
		temp(f1);
		op = Operation.Times;

	}

	@Override
	public void div(function f1) {
		temp(f1);
		op = Operation.Divid;

	}

	@Override
	public void max(function f1) {
		temp(f1);
		op = Operation.Max;

	}

	@Override
	public void min(function f1) {
		temp(f1);
		op = Operation.Comp;

	}

	@Override
	public void comp(function f1) {
		temp(f1);
		op = Operation.Comp;
	}

	@Override
	public function left() {
		return left;
	}

	@Override
	public function right() {
		return right;
	}

	@Override
	public Operation getOp() {
		return op;
	}

}
