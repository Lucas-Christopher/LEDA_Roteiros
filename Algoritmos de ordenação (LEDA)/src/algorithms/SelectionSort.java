package algorithms;

import sorting.AbstractSorting;
import util.Util;

public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (array != null && leftIndex < rightIndex && array.length > leftIndex
				&& array.length > rightIndex && leftIndex >= 0) {
			
			for (int i = leftIndex; i <= rightIndex; i++) {
				
				/*
				 * Assumimos o "primeiro" elemento como sendo o menor. Apos a
				 * primeira iteracao, andamos com o ponteiro e consideramos o
				 * elemento seguinte (i +1) ate o restante da lista para
				 * ordenar, jah que o primeiro jah estah ordenado.
				 */
				int indexMenor = i; // Menor parcial.

				for (int j = i + 1; j <= rightIndex; j++) {
					if (array[j].compareTo(array[indexMenor]) < 0) {
						indexMenor = j; // Neste instante, temos o menor valor
						// do vetor.
					}
				}

				// Se i != indexMenor, significa que se ele eh igual, ele jah eh
				// o menor, entao, nao tem necessidade de troca. Assim sendo,
				// efetuamos a troca dos elementos.
				
				if (i != indexMenor) {
					Util.swap(array, i, indexMenor);
				}
			}
		}
	}
}
