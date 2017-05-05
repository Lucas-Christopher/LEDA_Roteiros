package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	private static final String LEFT = "Left";
	private static final String RIGHT = "Right";

	private void splay(BSTNode<T> node) {
		if (node == null || node.isEmpty() || node.equals(super.root))
			return;

		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		BSTNode<T> grandParent = (BSTNode<T>) parent.getParent();

		if (parent.equals(this.root)) {
			zigRotation(this.root);
			return;
		}

		else if (!node.getParent().equals(this.root)) {
			if (parent.equals(grandParent.getLeft()) && node.equals(parent.getLeft()))
				this.zigZigRotation(grandParent, LEFT);
			else if (parent.equals(grandParent.getRight()) && node.equals(parent.getRight()))
				this.zigZigRotation(grandParent, RIGHT);
			else if (parent.equals(grandParent.getLeft()) && node.equals(parent.getRight()))
				this.zigZagRotation(grandParent, LEFT);
			else
				this.zigZagRotation(grandParent, RIGHT);
		}
		splay(node);
	}

	private void zigRotation(BSTNode<T> node) {
		if (!node.getLeft().isEmpty() && node.getLeft().getParent().equals(node))
			this.root = Util.rightRotation(this.root);
		else
			this.root = Util.leftRotation(this.root);
	}

	private void zigZigRotation(BSTNode<T> node, String position) {
		if (position.equals(LEFT)) {

			BSTNode<T> leftSon = (BSTNode<T>) node.getLeft();
			Util.rightRotation(node);

			BSTNode<T> aux = Util.rightRotation(leftSon);
			if (aux.getParent() == null)
				this.root = aux;

		} else {
			BSTNode<T> rightSon = (BSTNode<T>) node.getRight();
			Util.leftRotation(node);

			BSTNode<T> aux = Util.leftRotation(rightSon);
			if (aux.getParent() == null)
				this.root = aux;
		}
	}

	private void zigZagRotation(BSTNode<T> node, String position) {
		if (position.equals(LEFT)) {
			Util.leftRotation((BSTNode<T>) node.getLeft());
			BSTNode<T> aux = Util.rightRotation(node);

			if (aux.getParent() == null)
				this.root = aux;

		} else {
			Util.rightRotation((BSTNode<T>) node.getRight());
			BSTNode<T> aux = Util.leftRotation(node);

			if (aux.getParent() == null)
				this.root = aux;
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> node = super.search(element);

		if (!node.isEmpty())
			splay(node);
		else
			splay((BSTNode<T>) node.getParent());

		return node;
	}

	@Override
	public void insert(T element) {
		if (element == null)
			return;

		super.insert(element);
		splay(super.search(element));
	}

	@Override
	public void remove(T element) {
		if (element == null)
			return;

		BSTNode<T> node = super.search(element);
		if (node.isEmpty())
			this.splay((BSTNode<T>) node.getParent());

		else {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			super.remove(node.getData());
			this.splay(parent);
		}
	}
	
}