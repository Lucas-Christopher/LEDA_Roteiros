package algorithms;

public class CountingSort {

	public static int achaMaior(int[] vector) {
		int maior = vector[0];
		for (int i = 0; i < vector.length; i++) {
			if (vector[i] > maior)
				maior = vector[i];
		}
		return maior;
	}

	// Para valores positivos...

	public static int[] countingSort(int[] array, int k) {

		int[] helper = new int[k]; // k eh o maior valor do array.

		// Frequencia
		for (int i = 0; i < array.length; i++) {
			helper[array[i] - 1] += 1;
			// array[i] - 1, pois lembre-se que estamos considerando arrays com
			// inteiros positivos.
		}

		// Cumulativa
		for (int i = 1; i < helper.length; i++) {
			helper[i] += helper[i - 1]; // Comeca de um e soma com o anterior.
		}

		int[] sorted = new int[array.length];

		for (int i = array.length - 1; i >= 0; i--) {

			// Usamos "array[i] - 1" como a posicao do array de helper que
			// tambem comeca de zero, por isso "-1".
			sorted[helper[array[i] - 1] - 1] = array[i];

			// Decrementamos um da posicao de helper.
			helper[array[i] - 1] -= 1;
		}

		return sorted;
	}

	// Para valores nao negativos...

	public static int[] countingTwo(int[] array, int k) {

		int[] count = new int[k + 1];

		// Frequencia
		for (int i = 0; i < array.length; i++)
			count[array[i]]++;

		// Cumulativa
		for (int i = 1; i < count.length; i++)
			count[i] += count[i - 1];

		int[] sorted = new int[array.length];

		// Ordenacao
		for (int i = array.length - 1; i >= 0; i--) {
			int x = array[i];
			int y = count[x];
			sorted[y - 1] = array[i];
			count[x]--;
		}

		// Array rearrumado
		for (int i = 0; i < array.length; i++)
			array[i] = sorted[i];

		return array;
	}

}