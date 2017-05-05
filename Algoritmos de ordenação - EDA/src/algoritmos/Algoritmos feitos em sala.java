package algoritmos;

public class Algoritmos_Sala {

	// BUBBLE SORT
	public void bubble(int[] nums) {

		boolean trocou;

		for (int i = 0; i < nums.length; i++) {
			trocou = false;
			for (int j = 0; i < nums.length - j - 1; j++) {
				if (nums[j] > nums[j + 1]) {
					int aux = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = aux;
					trocou = true;
				}
			}

			if (!trocou)
				return;
		}
	}

	// INSERTION SORT
	public static void insertionSort(int[] seq) {

		int i, j, atual;

		for (i = 1; i < seq.length; i++) {
			atual = seq[i];
			j = i;

			while ((j > 0) && (seq[j - 1] > atual)) {
				seq[j] = seq[j - 1];
				j = j - 1;
			}
			seq[j] = atual;
		}
	}

	public void insertion(int[] v) {

		for (int j = 1; j < v.length; j++) {
			int key = v[j];
			int i = j - 1;
			
			while (i >= 0 && v[i] > key) {
				v[i + 1] = v[i];
				i--;
			}

			v[i + 1] = key;

		}
	}

	// SELECTOIN SORT
	public static void selectionSort(int[] seq) {
		int i, j, minIndex, temp;
		int n = seq.length;

		for (i = 0; i < n - 1; i++) {
			minIndex = i;
			for (j = i + 1; j < n; j++)
				if (seq[j] < seq[minIndex])
					minIndex = j;
			if (minIndex != i) {
				temp = seq[i];
				seq[i] = seq[minIndex];
				seq[minIndex] = temp;
			}
		}
	}

	// PARTICIONA MANTENDO A ORDEM.
	private static void particiona(int[] array) {
		int pivot = array[0];
		int i = 0;

		for (int j = 1; j < array.length; j++) {
			if (array[j] <= pivot) {
				for (int k = j; k > i; k--) {
					swap(array, k, k - 1);
				}
				i += 1;
			}
		}
	}

	private static void swap(int[] array, int i, int j) {
		int aux = array[i];
		array[i] = array[j];
		array[j] = aux;
	}

	// BINARY SEARCH
	public static int busca(int[] vector, int left, int right, int n) {
		if (left > right)
			return -1;
		else {
			int mid = (left + right) / 2;
			if (vector[mid] == n)
				return mid;
			else if (n < vector[mid])
				return busca(vector, left, mid - 1, n);
			else
				return busca(vector, mid + 1, right, n);
		}

	}

	// QUICKY SORT
	public static void quickSort(int[] v, int ini, int fim) {
		if (ini < fim) {
			int pos_pivot = particiona2(v, ini, fim);
			quickSort(v, ini, pos_pivot - 1);
			quickSort(v, pos_pivot + 1, fim);
		}
	}

	public static int particiona2(int[] v, int ini, int fim) {

		int pivot = v[ini];
		int i = ini;

		for (int j = ini + 1; j <= fim; j++) {
			if (v[j] < pivot) {
				i += 1;
				swap2(v, i, j);
			}
		}

		// troca pivot (v[ini]) com i.
		swap(v, ini, i);

		return i;
	}

	public static void swap2(int[] v, int i, int j) {
		int aux = v[i];
		v[i] = v[j];
		v[j] = aux;
	}

	// MERGE SORT
	public static void mergeSort(int[] v, int ini, int fim) {

		if (ini < fim) {

			int med = (ini + fim) / 2;
			quickSort(v, ini, med);
			quickSort(v, med + 1, fim);
			merge(v, ini, med, fim);
		}
	}

	public static void merge(int[] v, int ini, int med, int fim) {

		int[] helper = new int[v.length];

		for (int i = 0; i < v.length; i++) {
			helper[i] = v[i];
		}

		int i = ini;
		int j = med + 1;
		int k = ini;

		while (i <= med && j <= fim) {
			if (helper[i] < helper[j]) {
				v[k] = helper[i];
				i++;
			} else {
				v[k] = helper[j];
				j++;
			}
			k++;
		}

		// primeira metade não foi toda consumida: fazer append de todos
		// os elementos da primeira metade
		while (i <= med) {
			v[k] = helper[i];
			k += 1;
			i += 1;
		}

		// segunda metade não foi toda consumida: fazer append de todos
		// os elementos da segunda metade
		while (j <= fim) {
			v[k] = helper[j];
			k += 1;
			j += 1;
		}
	}
}
