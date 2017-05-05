package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode.Builder().data(null).left(null).right(null).parent(null).build();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	protected int height(BSTNode<T> node) {
		if (node.isEmpty())
			return -1;

		int leftHeight = height((BSTNode<T>) node.getLeft());
		int rigthHeight = height((BSTNode<T>) node.getRight());

		return 1 + Math.max(leftHeight, rigthHeight);

	}

	@Override
	public BSTNode<T> search(T element) {
		if (element == null)
			return null;

		return search(this.root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty())
			return new BSTNode<>();

		else {
			if (node.getData().compareTo(element) > 0)
				return search((BSTNode<T>) node.getLeft(), element);
			else if (node.getData().compareTo(element) < 0)
				return search((BSTNode<T>) node.getRight(), element);
			else
				return node;
		}
	}

	@Override
	public void insert(T element) {
		if (element == null)
			return;
		else
			insert(this.root, element);
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (node.getData().compareTo(element) > 0)
				insert((BSTNode<T>) node.getLeft(), element);
			else
				insert((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (this.isEmpty())
			return null;

		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.getRight().isEmpty())
			return node;
		else
			return maximum((BSTNode<T>) node.getRight()) ;
	}

	@Override
	public BSTNode<T> minimum() {
		if (this.isEmpty())
			return null;

		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.getLeft().isEmpty())
			return node;
		
		return minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);

		if (node.isEmpty())
			return null;

		else if (!node.getRight().isEmpty()) {
			return this.minimum((BSTNode<T>) node.getRight());
		} else {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			while (parent != null && parent.getRight().equals(node)) {
				node = parent;
				parent = (BSTNode<T>) node.getParent();
			}
			return parent;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);

		if (node.isEmpty())
			return null;

		else if (!node.getLeft().isEmpty()) {
			return this.maximum((BSTNode<T>) node.getLeft());
		}

		else {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			while (parent != null && parent.getLeft().equals(node)) {
				node = parent;
				parent = (BSTNode<T>) node.getParent();
			}
			return parent;
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (node.isEmpty()) {
			return;
		} else {
			// FOLHA
			if (node.isLeaf()) {
				node.setData(null);
			}
			// UM FILHO
			else if (node.getLeft().isEmpty() || node.getRight().isEmpty()) {

				if (node.getParent() != null) {
					if (!node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {
					if (node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
					} else {
						this.root = (BSTNode<T>) node.getLeft();
					}
					this.root.setParent(null);
				}
				// DOIS FILHOS
			} else {
				T suc = sucessor(node.getData()).getData();
				remove(suc);
				node.setData(suc);
			}
		}
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];

		if (!this.isEmpty()) {
			preOrder(this.root, array);
		}
		return array;
	}

	private void preOrder(BSTNode<T> node, T[] array) {

		if (!node.isEmpty()) {

			int i = 0;
			while (array[i] != null)
				i++;

			array[i] = node.getData();

			if (!node.getLeft().isEmpty())
				preOrder((BSTNode<T>) node.getLeft(), array);

			if (!node.getRight().isEmpty())
				preOrder((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];

		if (!this.isEmpty()) {
			order(this.root, array);
		}
		return array;
	}

	private void order(BSTNode<T> node, T[] array) {
		if (!node.isEmpty()) {

			if (!node.getLeft().isEmpty())
				order((BSTNode<T>) node.getLeft(), array);

			int i = 0;
			while (array[i] != null)
				i++;

			array[i] = node.getData();

			if (!node.getRight().isEmpty())
				order((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];

		if (!this.isEmpty()) {
			postOrder(this.root, array);
		}

		return array;
	}

	private void postOrder(BSTNode<T> node, T[] array) {

		if (!node.isEmpty()) {

			if (!node.getLeft().isEmpty())
				preOrder((BSTNode<T>) node.getLeft(), array);

			if (!node.getRight().isEmpty())
				preOrder((BSTNode<T>) node.getRight(), array);

			int i = 0;
			while (array[i] != null)
				i++;

			array[i] = node.getData();
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(this.root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}
}