package root_solver;

public class Test {

	public static void main(String[] args) {

		double x;
		int p;
		double result1;
		double result2;

		x = 1000;
		p = -3;

		result1 = NewtonsMethodRootCalculator.nthRoot(x, p);
		result2 = Math.pow(x,1d/p);

		System.out.println(result1 + "^" + p + " = " + x);
		System.out.println(Math.pow(result1, p) == x);

		System.out.println(result2 + "^" + p + " = " + x);
		System.out.println(Math.pow(result2, p) == x);

	}

}
