package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em
 * suas funcionalidades e possui um metodo de ordenar um array dado como
 * parametro, retornando o resultado do percurso desejado que produz o array
 * ordenado.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;

	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {

		if (!super.isEmpty())
			super.root = new BSTNode.Builder().data(null).left(null).right(null).parent(null).build();

		for (int i = 0; i < array.length; i++)
			this.insert(array[i]);

		return super.order();
	}

	@Override
	public T[] reverseOrder() {
		T[] array = (T[]) new Comparable[this.size()];

		if (!this.isEmpty())
			reverseOrder(this.root, array);

		return array;
	}

	private void reverseOrder(BSTNode<T> node, T[] array) {
		if (!node.isEmpty()) {
			reverseOrder((BSTNode<T>) node.getRight(), array);

			int i = 0;
			while (array[i] != null)
				i++;

			array[i] = node.getData();
			reverseOrder((BSTNode<T>) node.getLeft(), array);
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	@Override
	public void insert(T element) {
		if (element == null)
			return;
		else
			insert(super.root, element);
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (this.comparator.compare(node.getData(), element) > 0)
				insert((BSTNode<T>) node.getLeft(), element);
			else
				insert((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element == null)
			return null;

		return search(super.root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty())
			return new BSTNode<>();

		else {
			if (this.comparator.compare(node.getData(), element) > 0)
				return search((BSTNode<T>) node.getLeft(), element);
			else if (this.comparator.compare(node.getData(), element) < 0)
				return search((BSTNode<T>) node.getRight(), element);
			else
				return node;
		}
	}

}
