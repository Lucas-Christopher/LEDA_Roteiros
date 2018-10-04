package algorithms;

import java.util.Arrays;

public class QuickySort {

	public static void quickSort(int[] vector, int ini, int fim) {
		if (ini < fim) {
			int pos_pivot = particiona(vector, ini, fim);
			quickSort(vector, ini, pos_pivot - 1);
			quickSort(vector, pos_pivot + 1, fim);
		}
	}

	public static int particiona(int[] vector, int ini, int fim) {

		int pivot = vector[ini];
		int i = ini;

		for (int j = ini + 1; j <= fim; j++) {
			if (vector[j] < pivot) {
				i++;
				swap(vector, i, j);
//				int index = j;
//				while (index > i) {
//					swap(vector, index-1, index);
//					index--;
//				}
//				i++;
			}
		}

		// troca pivot (v[ini]) com i.
		swap(vector, ini, i);

		return i;
	}

	public static void swap(int[] v, int i, int j) {
		int aux = v[i];
		v[i] = v[j];
		v[j] = aux;
	}
	
	public static void main(String a[]) {

		int[] vetor = { 3, 4, 9, 7, 2, 8, 1, 12 };
		quickSort(vetor, 0, vetor.length-1);
		System.out.println(Arrays.toString(vetor));
		
		int[] vetor2 = { 2, 3, 5, 7, 88, 4 };
		quickSort(vetor2, 0, vetor2.length-1);
		System.out.println(Arrays.toString(vetor2));
		
		int[] inputArray = { 45, 23, 11, 89, 77, 98, 4, 28, 65, 43 };
		quickSort(inputArray, 0, inputArray.length-1);
		System.out.println(Arrays.toString(inputArray));
	}

}
