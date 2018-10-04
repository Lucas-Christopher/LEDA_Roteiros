package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		BSTNode<T> right = (BSTNode<T>) node.getRight();

		if (parent != null && right != null) {
			if (parent.getRight().equals(node))
				parent.setRight(right);
			else
				parent.setLeft(right);
		}

		// Caso a direita do filho tenha um filho...
		node.setRight(right.getLeft());
		node.getRight().setParent(node);

		right.setParent(parent);

		right.setLeft(node);
		node.setParent(right);

		return right;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		BSTNode<T> parent = (BSTNode<T>) node.getParent();

		if (parent != null && left != null) {
			if (parent.getLeft().equals(node))
				parent.setLeft(left);
			else
				parent.setRight(left);
		}

		// Caso a esquerda do filho tenha um filho...
		node.setLeft(left.getRight());
		node.getLeft().setParent(node);

		left.setParent(parent);

		left.setRight(node);
		node.setParent(left);

		return left;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}