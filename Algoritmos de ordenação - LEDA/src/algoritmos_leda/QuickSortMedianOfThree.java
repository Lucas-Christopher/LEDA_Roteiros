package algoritmos_leda;

import sorting.AbstractSorting;
import util.Util;

//********** CLASSE CORRIGIDA!!! **********

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na metade a esquerda e na metade a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= rightIndex || array == null || leftIndex < 0 || rightIndex >= array.length
				|| array.length <= 0)
			return;

		// Passo 1 e 2: Comparar e organizar os elementos, tal que: A[left] < A[center] < A[right].
		organizaMediana(array, leftIndex, rightIndex);
		
		// Passo 3: Adotar o A[centro] como o pivot.
		int pivot = (rightIndex + leftIndex) / 2;
		
		// Passo 4: Colocar o pivô na penúltima posição A[right-1].
		Util.swap(array, pivot, rightIndex - 1);
		
		partition(array, leftIndex, rightIndex);

	}

	// Passo 5: Aplicando o particionamento no vetor menor, de A[left+1] até A[right-1].
	private void partition(T[] array, int leftIndex, int rightIndex) {
		T pivot = array[rightIndex - 1];
		int i = leftIndex + 1;
		int j = rightIndex - 1;

		// Passo 6: Aplicando o algoritmo nas metades.
		while (i <= j) {
			// Metade a esquerda do pivot.
			while (array[i].compareTo(pivot) < 0) 
				i++;
			// Metade a direita do pivot.
			while (array[j].compareTo(pivot) > 0) 
				j--;
			
			if (i <= j) {
				Util.swap(array, i, j);
				i++;
				j--;
			}
		}

		if (i < rightIndex) 
			sort(array, i, rightIndex);
		
		if (leftIndex < j) 
			sort(array, leftIndex, j);
		
	}

	// Passo 1 e 2: Comparar e organizar os elementos, tal que: A[left] < A[center] < A[right]. 
	private void organizaMediana(T[] array, int leftIndex, int rightIndex) {
		
		int middleIndex = (rightIndex + leftIndex) / 2;
		
		if (array[leftIndex].compareTo(array[middleIndex]) > 0)
			Util.swap(array, leftIndex, middleIndex);
		
		if (array[middleIndex].compareTo(array[rightIndex]) > 0) 
			Util.swap(array, rightIndex, middleIndex);
		
		if (array[leftIndex].compareTo(array[middleIndex]) > 0) 
			Util.swap(array, leftIndex, middleIndex);
	}
}