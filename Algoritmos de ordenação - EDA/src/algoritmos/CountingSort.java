package algoritmos;

import java.util.Arrays;

public class CountingSort {

	public static int achaMaior(int[] vector) {
		int maior = vector[0];
		for (int i = 0; i < vector.length; i++) {
			if (vector[i] > maior)
				maior = vector[i];
		}
		return maior;
	}

	// PARA VALORES POSITIVOS.
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

	// PARA VALORES NAO NEGATIVOS.
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

	public static void main(String[] args) {

		int[] vetor = { 1, 8, 3, 0, 4, 6, 7, 2, 5 };
//		System.out.println(Arrays.toString(countingSort(vetor, achaMaior(vetor))));
		System.out.println();
		System.out.println(Arrays.toString(countingTwo(vetor, achaMaior(vetor))));
		System.out.println();

		int[] vetor2 = { 2, 3, 0, 5, 4, 8, 4 };
//		System.out.println(Arrays.toString(countingSort(vetor2, achaMaior(vetor2))));
		System.out.println();
		System.out.println(Arrays.toString(countingTwo(vetor2, achaMaior(vetor2))));
		System.out.println();

		int[] array = { 4, 3, 45, 23, 11, 89, 0, 4, 28, 65, 43 };
//		System.out.println(Arrays.toString(countingSort(array, achaMaior(array))));
		System.out.println();
		System.out.println(Arrays.toString(countingTwo(array, achaMaior(array))));

	}

}