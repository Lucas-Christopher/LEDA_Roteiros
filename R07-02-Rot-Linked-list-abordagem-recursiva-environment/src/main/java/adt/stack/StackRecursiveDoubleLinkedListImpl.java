package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> stack;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.stack = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element == null)
			return;

		if (this.isFull())
			throw new StackOverflowException();

		this.stack.insertFirst(element);

	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty())
			throw new StackUnderflowException();

		T element = this.top();
		stack.removeFirst();
		return element;

	}

	@Override
	public T top() {
		if (!this.isEmpty())
			return ((RecursiveDoubleLinkedListImpl<T>) this.stack).getData();
		else
			return null;
	}

	@Override
	public boolean isEmpty() {
		return this.stack.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack.size() == this.size;
	}

}