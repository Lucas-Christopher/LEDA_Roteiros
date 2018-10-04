package algorithms;

/*
 * A ideia aqui eh simples: Encontre o menor elemento do vetor e jogue-o
 * para a primeira posicao.
 */
public class SelectionSort {

	// // Acha o menor valor do vetor e coloca-o na primeira posicao do array.
	public static void selectionStep(int[] vetor) {

		int menor = vetor[0];
		int indexMenor = 0;
		for (int i = 1; i < vetor.length; i++) {

			if (vetor[i] < vetor[indexMenor])
				indexMenor = i;
		}

		int aux = menor;
		menor = vetor[indexMenor];
		vetor[indexMenor] = aux;

	}

	public void selectionSort(int[] vector) {

		for (int i = 0; i < vector.length - 1; i++) {
			/*
			 * Assumimos o "primeiro" elemento como sendo o menor. Após a primeira iteracao,
			 * andamos com o ponteiro e consideramos o elemento seguinte (i +1) ate o
			 * restante da lista para ordenar, jah que o primeiro jah estah ordenado.
			 */
			int indexMenor = i;

			for (int j = i + 1; j < vector.length; j++) {
				if (vector[j] < vector[indexMenor]) {
					indexMenor = j; // Neste instante, temos o menor valor do vetor.
				}
			}

			// Se i != indexMenor, significa que se ele eh igual, ele jah eh o
			// menor, entao, nao tem necessidade
			// de troca. Assim sendo, efetuamos a troca dos elementos.
			if (i != indexMenor) {
				int aux = vector[i];
				vector[i] = vector[indexMenor];
				vector[indexMenor] = aux;
			}
		}
	}

	public int[] getKLargest(int[] array, int k) {

		int[] statistics = new int[k];
		int j = 1;
		if (k < array.length) {
			// [1,4,6,8,9,12] - 6pos e 4 maiores.
			while (k > 0) {
				int valor = orderStatistics(array, array.length - j); // Maior estatistica de ordem;
				statistics[k - 1] = valor;
				j++;
				k--;
			}
			return statistics;
		}
		return null;
	}

	public int orderStatistics(int[] array, int k) {

		if (array == null || k < 0 || (k > array.length - 1))
			return 0;

		selectionSort(array);

		return array[k];
	}

	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
