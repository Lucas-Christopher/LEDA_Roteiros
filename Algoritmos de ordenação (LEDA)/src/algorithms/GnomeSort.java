package algorithms;

import sorting.AbstractSorting;
import util.Util;

/*
 * Algoritmo que utiliza a estrategia do BubbleSort quanto as comparacoes adjacentes, mas que leva
 * o elemento a ser ordenado a sua posicao correta, utilizando o "shift" do InsertionSort.
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (array != null && array.length > leftIndex && array.length > rightIndex
				&& leftIndex < rightIndex && leftIndex >= 0) {

			int i = leftIndex;
			int j = i + 1;

			while (i < j && j <= rightIndex) {

				// Troca com o "shift" - estrategia do Insertion - e retorna para o inicio para
				// comparar os elementos adjacentes - estrategia do Bubble.

				while (i >= leftIndex && array[i].compareTo(array[j]) > 0) {
					Util.swap(array, i, j);
					i--;
					j--;
				}
				i++;
				j++;
			}
		}
	}
}
