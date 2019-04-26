package algorithms;

/*
 * 1 - Dividir (trivial): O vetor em duas partes iguais até que V.length == 1.
   2 - Conquistar: Ordena recursivamente cada sub-vetor.
   3 - Combinar: Merge! Combinar dois vetores ordenados em um único vetor ordenado. 
 */

public class MergeSort {

	private int[] array;
	private int[] tempArray;
	private int length;

	public void mergeSort(int inputArray[]) {
		this.array = inputArray;
		this.length = inputArray.length;
		this.tempArray = new int[length];
		doMergeSort(0, length - 1);
	}

	private void doMergeSort(int lowerIndex, int higherIndex) {

		if (lowerIndex < higherIndex) {
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
			// Abaixo, a etapa ordena o lado esquerdo do array.
			doMergeSort(lowerIndex, middle);
			// Abaixo, a etapa classifica o lado direido do array.
			doMergeSort(middle + 1, higherIndex);
			// Agora mescle ambos os lados.
			mergeParts(lowerIndex, middle, higherIndex);
		}
	}

	private void mergeParts(int lowerIndex, int middle, int higherIndex) {

		for (int i = lowerIndex; i <= higherIndex; i++) {
			tempArray[i] = array[i];
		}

		int i = lowerIndex;
		int j = middle + 1;
		int k = lowerIndex;

		// Copia os valores menores do lado esquerdo ou direito para trás para o array
		// original.
		while (i <= middle && j <= higherIndex) {
			if (tempArray[i] <= tempArray[j]) {
				array[k] = tempArray[i];
				i++;
			} else {
				array[k] = tempArray[j];
				j++;
			}
			k++;
		}

		// Copia o resto do lado esquerdo do array no array destino.
		while (i <= middle) {
			array[k] = tempArray[i];
			k++;
			i++;
		}

		// Copia o resto do lado direito do array no array destino.
		while (j <= higherIndex) {
			array[k++] = tempArray[j++];
		}
	}

}
