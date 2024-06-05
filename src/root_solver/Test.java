package root_solver;

public class Test {

	public static void main(String[] args) {

		double x = -216;
		int p = 0;

		mostrarResultadoConMetodoNewton(x, p);
		System.out.println();
		mostrarResultadoConMath_root(x, p);

	}

	private static void mostrarResultadoConMetodoNewton(double radicando, int indice) {
		System.out.println("Método de Newton:");
		mostrarAproximacionRaizEnesima(radicando, indice, NewtonsMethodRootCalculator.nthRoot(radicando, indice));
	}

	private static void mostrarResultadoConMath_root(double radicando, int indice) {
		System.out.println("java.lang.Math.method:");
		mostrarAproximacionRaizEnesima(radicando,indice,
				nthRootConMathPow(radicando, indice));
	}

	private static void mostrarAproximacionRaizEnesima(double radicando, int indice, double resultadoAproximado) {
		if (Double.isNaN(resultadoAproximado)) {
			// Math.pow() devuelve NaN cuando la base es negativa y el exponente no es un entero
			System.out.println("No se la bancó y devolvió NaN");
			return;
		}
		System.out.println(
				"("+resultadoAproximado +")^"
				+ indice + " = " +
				Math.pow(resultadoAproximado, indice)
				+ " ≈ " + radicando
				);
	}

	private static double nthRootConMathPow(double x, int n) {
		if (n < 0) return 1.0d / nthRootConMathPow(x, -n);
		boolean nIsEven = (n & 1) == 0;
		if (x < 0) {
			if (nIsEven) return Double.NaN;
			else return -nthRootConMathPow(-x,n);
		}
		return Math.pow(x, 1d/n);

	}

}
