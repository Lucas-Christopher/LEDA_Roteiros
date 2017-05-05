package algoritmos_leda;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/*
 * O CombSort ou algoritmo do pente, eh um algoritmo relativamente simples, onde utilza o principio de ordenacao por trocas.
 * O CombSort melhora o BubbleSort e compete com o QuickSort.
 * A ideia b�sica � eliminar as tartarugas ou pequenos valores pr�ximos do final da lista,
 * j� que em um bubble sort estes retardam a classifica��o tremendamente.
 * (Coelhos, grandes valores em torno do in�cio da lista, n�o representam um problema no bubble sort).
 * 
 * Utilizamos o GAP, que eh a lacuna ou distancia de um elemento ao outro. No Bubble, essa distancia eh 1. No Comb, 
 * utilizamos uma constante que eh maior que 1, no caso, 1.3...
 *
 * O gap (intervalo) come�a como o comprimento da lista a ser ordenada dividida pelo fator de encolhimento (em geral 1,3)
 * e a lista � ordenada com este valor (arredondado para um inteiro se for necess�rio) para o gap.
 * Ent�o, a diferen�a � dividida pelo fator de encolhimento novamente, a lista � ordenada com este novo gap,
 * e o processo se repete at� que a diferen�a seja de 1. 
 * 
 * Neste ponto, o Comb sort continua usando um espa�o de 1 at� que a lista esteja totalmente ordenada.
 * A fase final da classifica��o �, portanto, equivalente a um bubble sort, mas desta vez a maioria dos elementos "tartarugas" j� foram tratados,
 * assim o bubble sort ser� eficiente.
 * 
 * 
 * GAP = (int) n(tamanho do intervalo de troca) / 1.3
 * Ou seja... 1� GAP = tamanho do vetor / 1.3
 * 2� GAP... GAP2 = GAP1 / 1.3 e assim por diante.
 */

public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (array != null && array.length > leftIndex && array.length > rightIndex && leftIndex < rightIndex
				&& leftIndex >= 0) {

			double fatorEnconlhimento = 1.3;

			int gap = rightIndex - leftIndex;

			boolean swapped = true;

			while (gap > 1 || swapped) {
				if (gap > 1)
					// Se gap == 1, o laco usa a estrategia do BubbleSort.
					gap = (int) (gap / fatorEnconlhimento);

				int i = leftIndex;
				swapped = false;

				while (i + gap <= rightIndex) {
					// Compara o primeiro elemento com o
					if (array[i].compareTo(array[i + gap]) > 0) {
						Util.swap(array, i, i + gap);
						swapped = true;
					}
					i++;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		CombSort<Integer> c = new CombSort<>();
		Integer[] vetor = { 23, 42, 4, 16, 8, 15 };
		c.sort(vetor, 0, vetor.length-1);
		System.out.println(Arrays.toString(vetor));
		
//		Integer[] vetor2 = { 2, 3, 5, 7, 88, 4 };
//		c.sort(vetor2, 0, vetor2.length-1);
//		System.out.println(Arrays.toString(vetor2));
//		
//		Integer[] vetor3 = { 45, 23, 11, 89, 77, 98, 4, 28, 65, 43 };
//		c.sort(vetor3, 0, vetor3.length-1);
//		System.out.println(Arrays.toString(vetor3));
		
	}
}