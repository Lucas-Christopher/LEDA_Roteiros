package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> queue;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.queue = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element == null) return;
		
		if (this.isFull())
			throw new QueueOverflowException();

		this.queue.insert(element);
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty())
			throw new QueueUnderflowException();

		T element = ((DoubleLinkedListImpl<T>) this.queue).getHead().getData();
		this.queue.removeFirst();
		return element;
	}

	@Override
	public T head() {
		return ((DoubleLinkedListImpl<T>) this.queue).getHead().getData();
	}

	@Override
	public boolean isEmpty() {
		return (this.size == 0);
	}

	@Override
	public boolean isFull() {
		return (this.queue.size() == this.size);
	}

}