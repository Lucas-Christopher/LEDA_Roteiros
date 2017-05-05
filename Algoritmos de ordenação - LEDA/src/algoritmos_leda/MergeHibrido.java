package algoritmos_leda;

import java.util.Arrays;
import sorting.AbstractSorting;

//********** CLASSE CORRIGIDA!!! **********

public class MergeHibrido<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (array == null || leftIndex > rightIndex || leftIndex < 0 || rightIndex < 0 || array.length < rightIndex
				|| array.length < leftIndex || array.length <= 0)
			return;

		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;

		if (rightIndex - leftIndex >= 0 && (rightIndex - leftIndex + 1) <= SIZE_LIMIT) {
			insertion(array, leftIndex, rightIndex);
			INSERTIONSORT_APPLICATIONS++;
		}
		else {
			mergeSort(array, leftIndex, rightIndex);
			MERGESORT_APPLICATIONS++;
		}
	}

	private void mergeSort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {

			int middle = leftIndex + (rightIndex - leftIndex) / 2;

			mergeSort(array, leftIndex, middle);
			mergeSort(array, middle + 1, rightIndex);
			merge(array, leftIndex, middle, rightIndex);
		}
	}

	private void merge(T[] array, int leftIndex, int middle, int rightIndex) {

		T[] helper = Arrays.copyOf(array, array.length);

		int i = leftIndex;
		int j = middle + 1;
		int k = leftIndex;

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

		while (i <= middle) {
			array[k++] = helper[i++];
		}
		while (j <= rightIndex) {
			array[k++] = helper[j++];
		}
	}

	private void insertion(T[] array, int leftIndex, int rightIndex) {
		
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			T atual = array[i];
			int j = i - 1;

			while (j >= leftIndex && array[j].compareTo(atual) > 0) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = atual;
		}
	}
}