package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	public void insert(T element) {
		if (element == null)
			return;
		else {
			super.insert(element);
			BSTNode<T> node = search(element);
			rebalanceUp(node);
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node == null)
			return -1;

		if (!node.isEmpty())
			return height((BSTNode<T>) node.getLeft()) - super.height((BSTNode<T>) node.getRight());

		return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node == null || node.isEmpty())
			return;

		// Balance do node.
		int balance = calculateBalance(node);

		// Se tah desequilibrado a esquerda...
		if (balance > 1) {
			// Filho desequilibrado a direita: ROTACAO DUPLA A DIREITA
			if (calculateBalance((BSTNode<T>) node.getLeft()) < 0)
				this.leftRotation((BSTNode<T>) node.getLeft());

			this.rightRotation(node);
		}

		// Se tha desequilibrado a direita...
		else if (balance < -1){
			// Filho desequilibrado a esquerda: ROTACAO DUPLA A ESQUERDA
			if (calculateBalance((BSTNode<T>) node.getRight()) > 0)
				this.rightRotation((BSTNode<T>) node.getRight());

			this.leftRotation(node);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node == null)
			return;
		else {
			rebalance(node);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}

	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {
		if (node == null)
			return;

		BSTNode<T> aux = Util.leftRotation(node);
		if (this.root.equals(node))
			this.root = aux;
	}

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {
		if (node == null)
			return;

		BSTNode<T> aux = Util.rightRotation(node);
		if (this.root.equals(node))
			this.root = aux;
	}
}