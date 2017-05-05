package algoritmos_leda;

import sorting.AbstractSorting;
import util.Util;

//********** CLASSE CORRIGIDA!!! **********

public class SimultaneousRecursiveBubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (array != null && array.length > leftIndex && array.length > rightIndex && leftIndex < rightIndex
				&& leftIndex >= 0) {

			// Indice auxiliar para percorrer a matriz de tras pra frente.
			int auxFim = rightIndex;
			
			// Partindo do inicio, se "i" for maior que "i + 1", troca.
			for (int i = leftIndex; i < rightIndex; i++) {
				if (array[i].compareTo(array[i + 1]) > 0) 
					Util.swap(array, i, i + 1);
				
				if (auxFim > leftIndex && array[auxFim].compareTo(array[auxFim - 1]) < 0) 
					Util.swap(array, auxFim, auxFim - 1);
				
				auxFim--;
			}
			sort(array, leftIndex + 1, rightIndex - 1);
		}
	}
}
