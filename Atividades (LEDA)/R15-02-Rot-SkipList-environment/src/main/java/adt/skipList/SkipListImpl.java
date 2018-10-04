package adt.skipList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	@Override
	public void insert(int key, T newValue, int height) {
		if (height < 0 || newValue == null)
			return;

		SkipListNode[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> aux = this.root;

		// Descendo os niveis da lista para encontrar o lugar da insercao.
		// Pesquisa o local.
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (aux.forward[i] != null && aux.forward[i].getKey() < key)
				aux = aux.forward[i];
			update[i] = aux; // Guarda o caminho.
		}
		aux = aux.forward[0];

		if (aux.getKey() == key)
			aux.setValue(newValue);
		else {
			if (height > this.maxHeight) {
				for (int i = this.maxHeight; i < height; i++)
					update[i] = this.root;

				this.maxHeight = height;
			}
			aux = new SkipListNode<T>(key, height, newValue);

			// Altera os ponteiros.
			for (int i = 0; i < height; i++) {
				aux.forward[i] = update[i].forward[i];
				update[i].forward[i] = aux;
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> aux = this.root;

		// Pesquisa o local.
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (aux.forward[i] != null && aux.forward[i].getKey() < key)
				aux = aux.forward[i];
			update[i] = aux; // Guarda o caminho.
		}
		aux = aux.forward[0];

		if (aux.getKey() == key) {
			// Ajeita os ponteiros.
			for (int i = 0; i < this.maxHeight; i++) {
				if (update[i].forward[i] != aux)
					break;
				update[i].forward[i] = aux.forward[i];
			}
		}
	}

	@Override
	public int height() {
		if (size() == 0)
			return 0;

		else {
			SkipListNode<T> aux = this.root.forward[0];

			int height = 0;

			while (!aux.equals(this.NIL)) {
				if (height < aux.height())
					height = aux.height();
				aux = aux.forward[0];
			}
			return height;
		}
	}

	@Override
	public SkipListNode<T> search(int key) {
//		SkipListNode<T> aux = this.root;
//		return searchRecurvise(key, aux, maxHeight - 1);
		
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> aux = this.root;

		// Pesquisa o local.
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (aux.forward[i] != null && aux.forward[i].getKey() < key)
				aux = aux.forward[i];
			update[i] = aux; // Guarda o caminho.
		}
		aux = aux.forward[0];

		if (aux.getKey() == key)
			return aux;
		else
			return null;
	}

//	private SkipListNode<T> searchRecurvise(int key, SkipListNode<T> node, int i) {
//		
//		if (node.forward[i] != null && node.forward[i].getKey() < key) {
//			return searchRecurvise(key, node.forward[i], i);
//		}
//		else {
//			i--;
//			if (i >= 0)
//				return searchRecurvise(key, node, i);
//			i++;
//		}
//		if (node.forward[0].getKey() == key)
//			return node.forward[0];
//		else
//			return null;
//	}

	@Override
	public int size() {
		int level = 0;
		int size = 0;

		SkipListNode<T> aux = this.root;

		while (aux.forward[level] != this.NIL) {
			aux = aux.forward[level];
			size++;
		}
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		int size = this.size() + 2; // Incluindo nos sentinelas.

		SkipListNode<T>[] result = new SkipListNode[size];
		SkipListNode<T> aux = this.root;

		for (int i = 0; i < size; i++) {
			result[i] = aux;
			aux = aux.forward[0];
		}
		return result;
	}
	
	// **** MY METODS ****
	
	// Elementos por altura.
	public SkipListNode<T>[] elementsByHeight(int level) {
		ArrayList<SkipListNode<T>> list = new ArrayList<SkipListNode<T>>();
		
		addNode(this.root.getForward(0), list, level);
		
		return list.toArray(new SkipListNode[list.size()]);
	}
	
	private void addNode(SkipListNode<T> node, ArrayList<SkipListNode<T>> list, int level) {
		if (node == this.NIL)
			return;
		
		if (node.height() == level)
			list.add(node);
		
		addNode(node.getForward(0), list, level);
	}
	
	public SkipListNode<T>[] order() {
		ArrayList<SkipListNode<T>> result = new ArrayList<>();
		
		for (int i = this.maxHeight; i >= 0; i++)
			addNode(this.root.getForward(0), result, this.maxHeight--);
			
		return result.toArray(new SkipListNode[this.size()]);
	}
	
	// Skip otima:
	public boolean isLevelOk() {
		int levelZero = this.size();
		return isLevelOk(this.root, 0, levelZero);
	}

	private boolean isLevelOk(SkipListNode<T> node, int height, int sizeAnterior) {
		if (height == this.maxHeight) return true;

		int sizeNivelAtual = 0;
		while (node.getForward(height + 1) != NIL) {
			node = node.getForward(height + 1);
			sizeNivelAtual++;
		}

		if (sizeNivelAtual == 0)
			return true;

		if (sizeNivelAtual != sizeAnterior / 2)
			return false;

		return isLevelOk(root, height + 1, sizeNivelAtual);

	}
}