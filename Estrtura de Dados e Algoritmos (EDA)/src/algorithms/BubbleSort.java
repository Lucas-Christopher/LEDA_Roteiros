package algorithms;

/*
 * O Bubble Sort apresenta uma ideia trivial: Compare os dois primeiros elementos
 * consecutivos e troque os elementos de forma que o menor esteja a esquerda. Repita
 * n vezes ateh que o vetor esteja totalmente ordenado.
 */
public class BubbleSort {
	
	/*
	 * O Bubble Step eh um algoritmo no qual apenas encontra o maior elemento de
	 * um vetor e coloca-o na ultima posicao.
	 */
	public void bubbleStep(int[] lista) {

		int i = 0;
		while (i < lista.length - 1) {
			if (lista[i] > lista[i + 1]) {

				// int aux = array[i+1];
				// array[i+1] = array[i];
				// array[i] = aux;

				int aux = lista[i];
				lista[i] = lista[i + 1];
				lista[i + 1] = aux;
			}

			i++;
		}
	}

	public void bubbleSort(int[] vector) {

		for (int i = 0; i < vector.length - 1; i++) {
			for (int j = 0; j < vector.length - 1; j -= i - 1) {
				if (vector[j] > vector[j + 1]) {
					int aux = vector[j + 1];
					vector[j + 1] = vector[j];
					vector[j] = aux;
				}
			}
		}
	}

	public void bubbleMaroto(int[] array, int start, int end) {
		
		if (start < end) {
			for (int i = start; i < end; i++) {
				for (int j = start; j < end - i; j++) {
					if (array[j] > array[j+1]) 
						swap(array, j+1, j);
				}
			}
		}
	}
	
	public static void swap(int[] array, int i, int j) {
		int aux = array[i];
		array[i] = array[j];
		array[j] = aux;
	}
	
}