package algoritmos_leda;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/*
 * O CombSort ou algoritmo do pente, eh um algoritmo relativamente simples, onde utilza o principio de ordenacao por trocas.
 * O CombSort melhora o BubbleSort e compete com o QuickSort.
 * A ideia básica é eliminar as tartarugas ou pequenos valores próximos do final da lista,
 * já que em um bubble sort estes retardam a classificação tremendamente.
 * (Coelhos, grandes valores em torno do início da lista, não representam um problema no bubble sort).
 * 
 * Utilizamos o GAP, que eh a lacuna ou distancia de um elemento ao outro. No Bubble, essa distancia eh 1. No Comb, 
 * utilizamos uma constante que eh maior que 1, no caso, 1.3...
 *
 * O gap (intervalo) começa como o comprimento da lista a ser ordenada dividida pelo fator de encolhimento (em geral 1,3)
 * e a lista é ordenada com este valor (arredondado para um inteiro se for necessário) para o gap.
 * Então, a diferença é dividida pelo fator de encolhimento novamente, a lista é ordenada com este novo gap,
 * e o processo se repete até que a diferença seja de 1. 
 * 
 * Neste ponto, o Comb sort continua usando um espaço de 1 até que a lista esteja totalmente ordenada.
 * A fase final da classificação é, portanto, equivalente a um bubble sort, mas desta vez a maioria dos elementos "tartarugas" já foram tratados,
 * assim o bubble sort será eficiente.
 * 
 * 
 * GAP = (int) n(tamanho do intervalo de troca) / 1.3
 * Ou seja... 1º GAP = tamanho do vetor / 1.3
 * 2º GAP... GAP2 = GAP1 / 1.3 e assim por diante.
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