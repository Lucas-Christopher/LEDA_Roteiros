package algoritmos;

import java.util.Arrays;
import java.util.Random;

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
			// Below, step sorts the left side of the array : Abaixo, a etapa ordena o lado esquerdo do array.
			doMergeSort(lowerIndex, middle);
			// Below, step sorts the right side of the array : Abaixo, a etapa classifica o lado direido do array.
			doMergeSort(middle + 1, higherIndex);
			// Now merge both sides : Agora mescle ambos os lados.
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
		
		// Copy the smallest values from either the left or the right side back
		// to the array origin : Copia os valores menores do lado esquerdo ou direito para trás
		// para o array original.
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
		
		// Copy the rest of the left side of the array into the target array: Copia o resto do lado esquerdo
		// do array no array destino.
		while (i <= middle) {
			array[k] = tempArray[i];
			k++;
			i++;
		}
		
		
		// Copy the rest of the right side of the array into the target array: Copia o resto do lado direito
		// do array no array destino.
		while (j <= higherIndex) {
			array[k++] = tempArray[j++];
		}
	}
	
	public static int[] geraArray(int quantidade) {
		int[] array = new int[quantidade];
		Random random = new Random();
		for (int i = 0; i < quantidade; i++) {
			array[i] = random.nextInt();
		}
		return array;
	}
	
	
	public static void main(String a[]) {
		
//		int[] inputArray = geraArray(4000000);
//		MergeSort merge = new MergeSort();
//		
//		long inicio = System.currentTimeMillis();
//		merge.mergeSort(inputArray);
//		long fim = System.currentTimeMillis();
//		
//		long total = fim - inicio;
//		System.out.println("MERGE: " + total);
		
		MergeSort merge = new MergeSort();
		
		int[] vetor = { 23, 42, 4, 16, 8, 15 };
		merge.mergeSort(vetor);
		System.out.println(Arrays.toString(vetor));
		
		int[] vetor2 = { 2, 3, 5, 7, 88, 4 };
		merge.mergeSort(vetor2);
		System.out.println(Arrays.toString(vetor2));
		
		int[] inputArray = { 45, 23, 11, 89, 77, 98, 4, 28, 65, 43 };
		merge.mergeSort(inputArray);
		System.out.println(Arrays.toString(inputArray));
		
	}
}
