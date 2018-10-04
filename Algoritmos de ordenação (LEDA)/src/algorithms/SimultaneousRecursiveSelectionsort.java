package algorithms;

import sorting.AbstractSorting;
import util.Util;

public class SimultaneousRecursiveSelectionsort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (array != null && array.length > leftIndex && array.length > rightIndex && leftIndex < rightIndex
				&& leftIndex >= 0) {
			
			int menorIndex = leftIndex; // Menor atual.
			
			// Para o menor elemento...
			for (int i = leftIndex; i <= rightIndex; i++) {
				if (array[i].compareTo(array[menorIndex]) < 0) {
					menorIndex = i; // Novo menor.
				}
			}
			
			if (menorIndex != leftIndex) 
				Util.swap(array, leftIndex, menorIndex); // Atualiza menor.
			
			int maiorIndex = rightIndex; // Maior atual.
			
			// Para o maior elemento...
			for (int j = rightIndex; j >= leftIndex; j--) {
				if (array[j].compareTo(array[maiorIndex]) > 0) {
					maiorIndex = j; // Novo maior.
				}
			}
			
			if (maiorIndex != rightIndex)
				Util.swap(array, rightIndex, maiorIndex); // Atualiza maior.
			
			sort(array, leftIndex + 1, rightIndex - 1); // Chama recursivamente com subarray menor.
		}
	}
}