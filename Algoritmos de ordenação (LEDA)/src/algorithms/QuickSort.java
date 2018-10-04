package algorithms;

import sorting.AbstractSorting;
import util.Util;

public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (array == null || leftIndex > rightIndex || leftIndex < 0 || rightIndex < 0 || array.length < rightIndex
				|| array.length < rightIndex || array.length <= 0)
			return;

		// Se o array tiver mais de um elemento, escolha o pivot.
		if (leftIndex < rightIndex) {

			// Dividir: Escolha do pivot.
			int pos_pivot = particiona(array, leftIndex, rightIndex);
			
			// Conquistar: Ordenar cada subarray recusivamente.
			// Combinar: O que eh trivial.
			
			sort(array, leftIndex, pos_pivot - 1); // Menores que o pivot.
			sort(array, pos_pivot + 1, rightIndex); // Maiores que o pivot.
		}
	}

	private int particiona(T[] array, int start, int end) {

		// Vamos eleger o pivot como o primeiro elemento do subarray.
		T pivot = array[start];
		int i = start;

		for (int j = start + 1; j <= end; j++) {
			if (array[j].compareTo(pivot) < 0) {
				i++;
				
				/*
				 * *** Para manter a estabilidade... ***
				int index = j;
				while (index > i) {
					Util.swap(array, index, index - 1);
					index--;
				}*/
				
				Util.swap(array, i, j); // Troca i com j.
			}
		}
		// Troca pivot, que tah no inicio, com i.
		Util.swap(array, start, i);
		return i;
	}
}
