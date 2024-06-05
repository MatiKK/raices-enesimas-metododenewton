package root_solver;

public class Test {

	public static void main(String[] args) {

		double x = -216;
		int p = 3;

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
		mostrarAproximacionRaizEnesima(radicando, indice, Math.pow(radicando, 1d/indice));
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


}
