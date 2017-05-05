package algoritmos_leda;

import java.util.Arrays;

import sorting.AbstractSorting;

//********** CLASSE CORRIGIDA!!! **********

public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (array == null || leftIndex >= rightIndex || leftIndex < 0 || rightIndex < 0 || array.length < leftIndex
				|| array.length < rightIndex || array.length < 0 || rightIndex >= array.length)
			return;

		int middle = (rightIndex + leftIndex) / 2;

		sort(array, leftIndex, middle); // Parte esquerda do array.
		sort(array, middle + 1, rightIndex); // Parte direita do array.
		merge(array, leftIndex, middle, rightIndex);
	}

	private void merge(T[] array, int leftIndex, int middle, int rightIndex) {

		T[] helper = Arrays.copyOf(array, array.length);

		int i = leftIndex;
		int j = middle + 1;
		int k = leftIndex;

		// Comparacao nos indices do subarray e fazendo o merge.
		while (i <= middle && j <= rightIndex) {
			if (helper[i].compareTo(helper[j]) < 0) {
				array[k] = helper[i];
				i++;
			} else {
				array[k] = helper[j];
				j++;
			}
			k++;
		}

		// Caso se a primeira metade nao foi consumida...
		while (i <= middle) {
			array[k++] = helper[i++];
		}

		// Caso se a segunda metade nao foi consumida...
		while (j <= rightIndex) {
			array[k++] = helper[j++];
		}
	}
}
