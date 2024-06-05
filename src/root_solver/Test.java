package root_solver;

public class Test {

	public static void main(String[] args) {

		double x = 216;
		int p = 3;

		mostrarResultadoConMetodoNewton(x, p);
		mostrarResultadoConMath_root(x, p);

		System.out.println(NewtonsMethodRootCalculator.nthRoot(x, p));
		System.out.println(NewtonsMethodRootCalculator.cbrt(x));

	}

	private static void mostrarResultadoConMetodoNewton(double radicando, int indice) {
		System.out.println("Método de Newton:");
		mostrarAproximacionRaizEnesima(radicando, indice, NewtonsMethodRootCalculator.nthRoot(radicando, indice));
	}

	private static void mostrarResultadoConMath_root(double radicando, int indice) {
		System.out.println("Math.pow():");
		mostrarAproximacionRaizEnesima(radicando, indice, Math.pow(radicando, 1d/(double) indice));
	}

	private static void mostrarAproximacionRaizEnesima(double radicando, int indice, double resultadoAproximado) {
		System.out.println(
				"("+resultadoAproximado +")^"
				+ indice + " = " +
				Math.pow(resultadoAproximado, indice)
				+ " ≈ " + radicando
				);
	}


}
