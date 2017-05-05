package algoritmos_leda;

import sorting.AbstractSorting;

//********** CLASSE CORRIGIDA!!! **********

public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		if (array == null || array.length == 0 || leftIndex > rightIndex || leftIndex < 0 || rightIndex < 0
				|| array.length < rightIndex)
			return;

		if (leftIndex < rightIndex) {

			// Identifica o menor elemento do intervalo dado para ordenacao.
			int menor = encontraMenor(array, leftIndex, rightIndex);
			// Identifica o menor elemento do intervalo dado para ordenacao.
			int maior = encontraMaior(array, leftIndex, rightIndex);

			int shift = menor;

			// Tamanho do array que sera usado para a contagem de ocorrencias.
			int tamanho = (maior - menor) + 1;

			int[] contadores = new int[tamanho];

			// Contagem de ocorrencias.
			for (int i = leftIndex; i <= rightIndex; i++) {
				contadores[array[i] - shift]++;
			}

			// Cumulativa da frequencia.
			for (int i = 1; i < contadores.length; i++) {
				contadores[i] += contadores[i - 1];
			}

			// Array utilizado para ordenacao.
			Integer sorted[] = new Integer[array.length];

			for (int i = rightIndex; i >= leftIndex; i--) {
				sorted[contadores[array[i] - shift] - 1] = array[i];
				contadores[array[i] - shift]--;
			}

			// Copia os elementos do array "sorting" para o original.
			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = sorted[i];
			}
		}
		
		/* Os tres ultimos "for"s poderiam ser substituidos por:
		  
		 // substitui as ocorrencias no array de origem
		  
  			for (int i = 0, j = leftIndex; i < count.length; i++) {
  			while (count[i] > 0) {
  				array[j++] = i + menorElemento;
  				count[i]--;
  			}
  		}*/
		
	}

	private int encontraMenor(Integer[] array, int leftIndex, int rightIndex) {
		Integer menor = array[leftIndex];

		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(menor) < 0) {
				menor = array[i];
			}
		}
		return menor.intValue();
	}

	private int encontraMaior(Integer[] array, int leftIndex, int rightIndex) {
		Integer maior = array[leftIndex];

		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i].compareTo(maior) > 0) {
				maior = array[i];
			}
		}
		return maior.intValue();
	}

}
