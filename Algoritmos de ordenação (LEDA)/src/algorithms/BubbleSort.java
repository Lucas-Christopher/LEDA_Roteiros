package algorithms;

import sorting.AbstractSorting;
import util.Util;

public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex < rightIndex && array.length > leftIndex
				&& array.length > rightIndex && leftIndex >= 0) {

			// Do inicio ate o fim.
			for (int i = leftIndex; i <= rightIndex; i++) {

				// Do novo tamanho menos "i", pois assim consumimos iteracoes (dada por "i").

				for (int j = leftIndex; j < rightIndex + leftIndex - i; j++) {
					if (array[j].compareTo(array[j + 1]) > 0) {
						Util.swap(array, j + 1, j);
					}
				}
			}
		}
	}
}
