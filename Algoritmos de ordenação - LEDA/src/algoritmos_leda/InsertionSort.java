package algoritmos_leda;

import sorting.AbstractSorting;

//********** CLASSE CORRIGIDA!!! **********

/*
 * O Sort Insertion apresenta a seguinte ideia: Dividir o array em duas
 * partes: Uma ordenada e outra nao ordenada. A cada rodada (iteracao) do
 * algoritmo, passamos um elemento da parte nao ordenada para a parte jah
 * classificada (ordenada), ateh que todo o array esteja ordenado.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (array != null && leftIndex < rightIndex && array.length > leftIndex && array.length > rightIndex
				&& leftIndex >= 0) {
			// Primeiro elemento como ordenado.
			for (int i = leftIndex + 1; i <= rightIndex; i++) {
				T atual = array[i]; // Elemento com o apontador atual.
				int j = i - 1; // Antercessores.

				/*
				 * Maior ou igual a zero, pois quando j = 0, ele acessa o
				 * primeiro elemento.
				 */
				while (j >= leftIndex && array[j].compareTo(atual) > 0) {
					array[j + 1] = array[j];
					array[j] = atual; 
					j--; // Sempre decremento para pegar o antercessor.
				}
			}
		}
	}
}
