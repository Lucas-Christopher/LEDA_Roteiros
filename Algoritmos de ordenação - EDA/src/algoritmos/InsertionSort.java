package algoritmos;

import java.util.Arrays;
import java.util.Random;

public class InsertionSort {

	/*
	 * O Sort Insertion apresenta a seguinte ideia: Dividir o array em duas
	 * partes: Uma ordenada e outra nao ordenada. A cada rodada (iteracao) do
	 * algoritmo, passamos um elemento da parte nao ordenada para a parte jah
	 * classificada (ordenada), ateh que todo o array esteja ordenado.
	 */
	public static void insertionSort(int[] array) {

		/*
		 * Inicia-se em 1, pois o primeiro elemento tah ordenado.
		 */
		for (int i = 1; i < array.length; i++) {
			int atual = array[i]; // Elemento com o apontador atual.
			int j = i - 1; // Antercessores.

			/*
			 * Maior ou igual a zero, pois quando j = 0, ele acessa o primeiro
			 * elemento.
			 */
			while (j >= 0 && array[j] > atual) {
				array[j + 1] = array[j];
				array[j] = atual;
				j--; // Sempre decremento para pegar o antercessor.
			}
		}
	}
	
	// [1, 2, 4, 3, 5, 6, 7, 8, 11]
	// [1, 8, 4, 5, 9]
	public static void insereOrdenadoImpostor(int[] array) {
		int i = 0;
		int aux = 0;
		
		while (i < array.length - 1) {
			if (array[i] > array[i+1])
				aux = i + 1;
			else
				aux = i;
			i++;
		}
		if (aux < array.length && array[aux] > array[aux+1]) {
			while (array[aux] > array[aux+1]) {
				swap(array, aux, aux+1);
				aux++;
			}
		}
		else {
			while (aux >= 0 && array[aux] < array[aux-1]) {
				swap(array, aux, aux-1);
				aux--;
			}
		}
	}

	private static void swap(int[] array, int i, int j) {
		int aux = array[i];
		array[i] = array[j];
		array[j] = aux;
	}

	public static void main(String[] args) {

		int[] a = {1, 2, 4, 3, 6, 7, 9};
		insereOrdenadoImpostor(a);
		System.out.println(Arrays.toString(a));
		
		int[] b = {1, 8, 4, 7, 9};
		insereOrdenadoImpostor(b);
		System.out.println(Arrays.toString(b));
//		int[] vetor = { 23, 42, 4, 16, 8, 15 };
//		insertionSort(vetor);
//		insertion(vetor, 0, vetor.length);
//		System.out.println(Arrays.toString(vetor));
		
//		int[] vetor2 = { 2, 3, 5, 7, 88, 4 };
//		insertionSort(vetor2);
//		insertion(vetor2, 0, vetor2.length);
//		System.out.println(Arrays.toString(vetor2));
		
//		int[] vetor3 = { 45, 23, 11, 89, 77, 98, 4, 28, 65, 43 };
//		insertionSort(vetor3);
//		insertion(vetor3, 0, vetor3.length);
//		System.out.println(Arrays.toString(vetor3));
		
	}

}
