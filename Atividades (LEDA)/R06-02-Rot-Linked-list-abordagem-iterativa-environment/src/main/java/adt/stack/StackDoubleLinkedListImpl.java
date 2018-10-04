package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> stack;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.stack = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element == null) return;
		
		if (this.isFull())
			throw new StackOverflowException();

		this.stack.insertFirst(element);
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty())
			throw new StackUnderflowException();

		T element = ((DoubleLinkedListImpl<T>) this.stack).getLast().getData();
		this.stack.removeFirst();
		this.size--;
		return element;
	}

	@Override
	public T top() {
		if (!this.isEmpty())
			return this.stack.toArray()[0];
		
		return null;
	}

	@Override
	public boolean isEmpty() {
		return (this.stack.size() == 0);
	}

	@Override
	public boolean isFull() {
		return (this.stack.size() == this.size);
	}

}