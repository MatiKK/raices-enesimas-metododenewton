package root_solver;

/**
 * El método de Newton es un algoritmo que se usa para hallar las raíces de una
 * función real. Esta clase presenta la solución de encontrar la enésima raíz de 
 * un número {@code S} utilizando dicho método, planteando la ecuación
 * <p>
 * <blockquote>f(x) = x<sup>n</sup> - S </blockquote>
 * <p>
 * y hallando el valor {@code Z} tal que {@code f(Z) = 0}, que sería finalmente
 * {@code Z = nthrt(S)}.
 * <p>
 * Es un método iterativo donde el resultado que se busca es z<sub>n</sub>
 * <p>
 * donde z<sub>n+1</sub> = z<sub>n</sub> - f(z<sub>n</sub>)/f'(z<sub>n</sub>)
 * y z<sub>0</sub> es un valor inicial estimado
 */
public final class NewtonsMethodRootCalculator {

	private NewtonsMethodRootCalculator() {
	}

	/**
	 * Deben haber varias iteraciones
	 * por la incapacidad de encontrar un valor inicial
	 * relativamente cercano a la raíz a buscar
	 */
	private static final int NEWTON_METHOD_ITERATIONS = 20;
	private static final double UNDEFINED = Double.NaN;

	/**
	 * return floor(log2(floor(x)/n))
	 * bit shift operators para reemplazar
	 * multiplicaciones/divisiones por 2
	 */
	private static double bestApproximation(double x, int n) {
		int result = 1;
		int ix = (int) x;
		while ((ix = ix >> n) != 0) {
			result = result << 1;
		}
		return result;
	}

	// return x ** n, donde x != 0 && n un numero natural
	// (innecesario chequear por ahora)
	private static double raisedToIntegerExponent(double x, int n) {
		double result = 1;
		for (int i = 0; i < n; i++) result *= x;
		return result;
	}

	/**
	 * valor neutral quiere decir que x**n = x para cualquier n natural
	 * (solo se cumple para |x| = {0,1})
	 */
	private static boolean isNeutralValue(double x) {
		return x == -0.0d || x == -1.0d || x ==  0.0d || x ==  1.0d;
	}

	private static boolean isNegative(double x) {
		return x < 0;
	}

	/**
	 * Calcula la raíz enésima de {@code x}.
	 * <ul> Casos especiales:
	 * <li> Si {@code n == 0}, el resultado es {@code NaN}.
	 * <li> Si {@code n < 0}. el resultado es <sup>n</sup>{@code \u221Ax = }
	 * {@code 1/}<sup>-n</sup>{@code \u221Ax}.
	 * <li> Si {@code x < 0} y {@code n} es par, el resultado es {@code NaN}.
	 * <li> Si {@code x < 0} y {@code n} es impar, el resultado es
	 * 
	 * <sup>n</sup>{@code \u221Ax = -} <sup>n</sup>{@code \u221A-x}.
	 * </ul>
	 * 
	 * @param x valor para calcular su raíz enésima
	 * @param n índice de la raíz a calcular
	 * @return la raíz enésima de {@code x}: <sup>n</sup>{@code \u221Ax}
	 */
	public static double nthRoot(double x, int n) {
		if (n < 0) return 1.0d / nthRoot(x, -n); // x**(-1/a) = 1/x**(1/a)
		if (n == 0) return UNDEFINED;
		boolean nIsEven = (n & 1) == 0;
		if (isNegative(x)) {
			if (nIsEven) return UNDEFINED;
			else return -nthRoot(-x,n); // si el indice es impar, nthrt(x) = -nthrt(-x)
		}
		if (n == 1 || isNeutralValue(x)) return x;
		if (n == 2) return sqrtUnchecked(x);
		if (n == 3) return cbrtUnchecked(x);

		double initial_value = bestApproximation(x, n);
		double result = initial_value;

		// evitar evaluaciones cada iteración
		double c = (1.0d / n);
		int n_1 = n - 1;

		for (int i = 0; i < NEWTON_METHOD_ITERATIONS; i++)
			// f(z) = z^N - x, f'(z) = N*z^(N-1)
			// z_n+1 = z_n - ((z_n)^N - x) / (N * (z_n)^(N-1))
			result = c *(n_1 * result + x / (raisedToIntegerExponent(result, n_1)));
		return result;
	}

	/**
	 * Calcula la raíz cuadrada de {@code x}.
	 * @param x valor para calcular su raíz cuadrada
	 * @return la raíz cuadrada de {@code x}: {@code \u221Ax}.
	 * Si {@code x < 0}, el resultado is {@code NaN}.
	 */
	public static double sqrt(double x) {
		if (isNegative(x)) return UNDEFINED;
		if (isNeutralValue(x)) return x;
		return sqrtUnchecked(x);
	}

	private static double sqrtUnchecked(double x) {
		double initial_value = bestApproximation(x, 2);
		double result = initial_value;
		for (int i = 0; i < NEWTON_METHOD_ITERATIONS; i++)
			// f(z) = z^2 - x, f'(z) = 2*z
			// z_n+1 = z_n - ((z_n)^2 - x) / (2 * z_n)
			// z_n+1 = (1/2) * (z_n + x/z_n)
			result = (1.0d / 2.0d) * (result + x / result);
		return result;
	}

	/**
	 * Calcula la raíz cúbica de {@code x}.
	 * @param x valor para calcular su raíz cúbica
	 * @return la raíz cúbica de {@code x}: {@code \u221Bx}
	 */
	public static double cbrt(double x) {
		if (isNeutralValue(x)) return x;
		return cbrtUnchecked(x);
	}

	private static double cbrtUnchecked(double x) {
		double initial_value = bestApproximation(x, 3);
		double result = initial_value;
		for (int i = 0; i < NEWTON_METHOD_ITERATIONS; i++)
			// f(z) = z^3 - x, f'(z) = 3*z^2
			// z_n+1 = z_n - ((z_n)^3 - x) / (3 * (z_n)^2)
			// z_n+1 = (1/3) * (2*z_n + x/(z_n)^2)
			result = (1.0d / 3.0d) * (2.0d * result + x / (result * result));
		return result;
	}
}
