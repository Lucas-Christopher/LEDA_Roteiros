package algorithms;

public class InsertionSort {

	/*
	 * O Insertion Sort apresenta a seguinte ideia: Dividir o array em duas partes:
	 * Uma ordenada e outra nao ordenada. A cada rodada (iteracao) do algoritmo,
	 * passamos um elemento da parte nao ordenada para a parte jah classificada
	 * (ordenada), ateh que todo o array esteja ordenado.
	 */
	public static void insertionSort(int[] array) {

		/*
		 * Inicia-se em 1, pois o primeiro elemento tah ordenado.
		 */
		for (int i = 1; i < array.length; i++) {
			int atual = array[i]; // Elemento com o apontador atual.
			int j = i - 1; // Antercessores.

			/*
			 * Maior ou igual a zero, pois quando j = 0, ele acessa o primeiro elemento.
			 */
			while (j >= 0 && array[j] > atual) {
				array[j + 1] = array[j];
				array[j] = atual;
				j--; // Sempre decremento para pegar o antercessor.
			}
		}
	}

	public void insereOrdenadoImpostor(int[] array) {
		int i = 0;
		int aux = 0;

		while (i < array.length - 1) {
			if (array[i] > array[i + 1])
				aux = i + 1;
			else
				aux = i;
			i++;
		}
		
		if (aux < array.length && array[aux] > array[aux + 1]) {
			while (array[aux] > array[aux + 1]) {
				swap(array, aux, aux + 1);
				aux++;
			}
		} else {
			while (aux >= 0 && array[aux] < array[aux - 1]) {
				swap(array, aux, aux - 1);
				aux--;
			}
		}
	}

	private static void swap(int[] array, int i, int j) {
		int aux = array[i];
		array[i] = array[j];
		array[j] = aux;
	}

}
