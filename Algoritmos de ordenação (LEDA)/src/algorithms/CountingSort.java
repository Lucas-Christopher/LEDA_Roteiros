package algorithms;

import sorting.AbstractSorting;

public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		if (array == null || leftIndex > rightIndex || leftIndex < 0
				|| rightIndex < 0 || array.length < rightIndex || array.length == 0)
			return;

		if (leftIndex < rightIndex) {

			// Calcula-se o maior elemento do array.
			int maior = encontraMaior(array, leftIndex, rightIndex);

			// Cria um array auxiliar com indices ate o do maior
			// elemento(incluso).
			int[] contadores = new int[maior + 1];

			// Frequencia dos elementos.
			for (int i = leftIndex; i <= rightIndex; i++) {
				contadores[array[i]]++;
			}

			// Somatorio do array auxiliar.
			for (int i = 1; i < contadores.length; i++) {
				contadores[i] += contadores[i - 1];
			}

			// Array utilizado para a ordenacao.
			Integer sorted[] = new Integer[array.length];

			// Passo de ordenacao.
			for (int i = rightIndex; i >= leftIndex; i--) {
				sorted[contadores[array[i]] - 1] = array[i];
				contadores[array[i]]--;
			}

			// Copiando os valores de "sorted" para o array original.
			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = sorted[i];
			}
		}
	}

	// Encontra o maior elemento no array com o intervalo passado.
	public int encontraMaior(Integer[] array, int leftIndex, int rightIndex) {

		Integer maior = array[leftIndex];

		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(maior) > 0) {
				maior = array[i];
			}
		}

		return maior.intValue();
	}

}