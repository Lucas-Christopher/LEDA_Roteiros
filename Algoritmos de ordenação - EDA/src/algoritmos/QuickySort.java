package algoritmos;

import java.util.Arrays;
import java.util.Random;

public class QuickySort {

	// QUICKY SORT
	public static void quickSort(int[] v, int ini, int fim) {
		if (ini < fim) {
			int pos_pivot = particiona(v, ini, fim);
			quickSort(v, ini, pos_pivot - 1);
			quickSort(v, pos_pivot + 1, fim);
		}
	}

	public static int particiona(int[] v, int ini, int fim) {

		int pivot = v[ini];
		int i = ini;

		for (int j = ini + 1; j <= fim; j++) {
			if (v[j] < pivot) {
//				i++;
//				swap(v, i, j);
				int index = j;
				while (index > i) {
					swap(v, index-1, index);
					index--;
				}
				i++;
			}
		}

		// troca pivot (v[ini]) com i.
//		swap(v, ini, i);

		return i;
	}

	public static void swap(int[] v, int i, int j) {
		int aux = v[i];
		v[i] = v[j];
		v[j] = aux;
	}
	
//	public static int[] geraArray(int quantidade) {
//		int[] array = new int[quantidade];
//		Random random = new Random();
//		for (int i = 0; i < quantidade; i++) {
//		   array[i] = random.nextInt();
//		}
//		return array;
//	}
	
	
	public static void main(String a[]) {

//		int[] inputArray = geraArray(4000000);
//		
//		long inicio = System.currentTimeMillis();
//		quickSort(inputArray, 0, inputArray.length-1);
//		long fim = System.currentTimeMillis();
//		
//		long total = fim - inicio;
//		System.out.println("QUICKY: " + total);
		
//		int[] vetor = { 23, 42, 4, 16, 8, 15 };
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
