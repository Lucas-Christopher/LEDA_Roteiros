package adt.bst.extended;

import java.util.ArrayList;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class FullRecursiveBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements FullRecursiveBST<T> {

	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> maximum() {
		if (super.isEmpty())
			return null;
		else
			return maximumRecursive(super.getRoot());
	}

	private BSTNode<T> maximumRecursive(BSTNode<T> node) {
		if (node.getRight().isEmpty())
			return node;
		else
			return maximumRecursive((BSTNode<T>) node.getRight());
	}

	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> minimum() {
		if (super.isEmpty())
			return null;
		else
			return minimumRecursive(super.getRoot());
	}

	private BSTNode<T> minimumRecursive(BSTNode<T> node) {
		if (node.getLeft().isEmpty())
			return node;
		else
			return minimumRecursive((BSTNode<T>) node.getLeft());
	}

	/**
	 * Sobrescreva este metodo usando recursao. Quando um no tem filho a direita
	 * entao o sucessor sera o minimum do filho a direita. Caso contrario o
	 * sucessor sera o primeiro ascendente a ter um valor maior.
	 */
	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = super.search(element);

		if (node.isEmpty())
			return null;

		else if (!node.getRight().isEmpty())
			return this.minimumRecursive((BSTNode<T>) node.getRight());

		else
			return sucessorRecursive(node);

	}

	private BSTNode<T> sucessorRecursive(BSTNode<T> node) {
		if (node.getParent() == null)
			return null;

		else if (!node.getParent().getRight().equals(node))
			return (BSTNode<T>) node.getParent();

		else
			return sucessorRecursive((BSTNode<T>) node.getParent());
	}

	/**
	 * Sobrescreva este metodo usando recursao. Quando um no tem filho a
	 * esquerda entao o predecessor sera o maximum do filho a esquerda. Caso
	 * contrario o predecessor sera o primeiro ascendente a ter um valor menor.
	 */
	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = super.search(element);

		if (node.isEmpty())
			return null;

		else if (!node.getLeft().isEmpty())
			return this.maximumRecursive((BSTNode<T>) node.getLeft());

		else
			return predecessorRecursive(node);
	}

	private BSTNode<T> predecessorRecursive(BSTNode<T> node) {
		if (node.getParent() == null)
			return null;

		else if (!node.getParent().getLeft().equals(node))
			return (BSTNode<T>) node.getParent();

		else
			return predecessorRecursive((BSTNode<T>) node.getParent());
	}

	@Override
	public T[] elementsAtDistance(int k) {
		if (k < 0)
			return (T[]) new Comparable[0];

		List<T> result = new ArrayList<T>();

		result = this.elementsAtDistance(result, this.getRoot(), k);

		return result.toArray((T[]) new Comparable[result.size()]);
	}

	private List<T> elementsAtDistance(List<T> result, BSTNode<T> node, int k) {
		if (!node.isEmpty()) {
			if (k == 0)
				result.add(node.getData());
			else {
				elementsAtDistance(result, (BSTNode<T>) node.getLeft(), k - 1);
				elementsAtDistance(result, (BSTNode<T>) node.getRight(), k - 1);
			}
		}
		return result;
	}
}
