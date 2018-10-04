package adt.linkedList;

import java.util.Arrays;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.head = new DoubleLinkedListNode<T>();
		this.last = (DoubleLinkedListNode<T>) head;
	}

	@Override
	public void insertFirst(T element) {
		if (element == null) return;

		// Node = data, next, previous.
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) this.head,
				new DoubleLinkedListNode<T>());

		((DoubleLinkedListNode<T>) this.head).previous = newHead;

		if (this.isEmpty())
			this.last = newHead;

		this.head = newHead;

	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			this.head = this.head.getNext();

			// Aponta o last para NIL.
			if (this.isEmpty())
				this.last = new DoubleLinkedListNode<T>();

			// Aponta previous para NIL.
			((DoubleLinkedListNode<T>) this.head).previous = new DoubleLinkedListNode<T>();
		}
	}

	@Override
	public void removeLast() {
		if (!this.last.isNIL()) {
			this.last = this.last.getPrevious();

			if (last.isNIL())
				this.head = this.last;

			this.last.next = new DoubleLinkedListNode<T>();
		}
	}

	@Override
	public void insert(T element) {
		if (element == null) return;
		
		// Node = data, next, previous.
		DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(),
				this.last);

		this.last.next = newLast;

		if (last.isNIL()) // A lista estah vazia.
			this.head = newLast;

		this.last = newLast;

	}

	@Override
	public void remove(T element) {
		if (element == null) return; 
		
		if (head.getData() == (element))
			removeFirst();

		else if (last.getData() == (element))
			removeLast();

		else {
			SingleLinkedListNode<T> aux = this.head;

			while (!aux.isNIL() && !aux.data.equals(element))
				aux = aux.next;

			if (!aux.isNIL()) {
				((DoubleLinkedListNode<T>) aux).previous.next = aux.getNext();
				((DoubleLinkedListNode<T>) aux.next).previous = ((DoubleLinkedListNode<T>) aux).getPrevious();
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	// ******************** MY METHODS ************************
	
	public T optimizedSearch(T element) {
		DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.getHead();
		DoubleLinkedListNode<T> auxLast = this.getLast();

		if (element != null) {
			while (auxHead != auxLast && auxHead.getNext() != auxLast && !auxHead.getData().equals(element)
					&& !auxLast.getData().equals(element)) {
				
				auxHead = (DoubleLinkedListNode<T>) auxHead.getNext();
				auxLast = auxLast.getPrevious();
			}
			if (auxHead.getData().equals(element))
				return auxHead.getData();
			if (auxLast.getData().equals(element))
				return auxLast.getData();
		}
		return null;
	}

	public DoubleLinkedListImpl<T> inverteLinkedList(DoubleLinkedListImpl<T> list) {
		T[] array = (T[]) new Object[list.size()];
		DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) list.getHead();
		int i = 0;

		while (!aux.isNIL()) {
			array[i] = aux.getData();
			aux = (DoubleLinkedListNode<T>) aux.getNext();
			i++;
		}

		list = new DoubleLinkedListImpl<>();
		for (int j = array.length - 1; j >= 0; j--) {
			list.insert(array[j]);
		}
		return list;
	}

	public static void main(String[] args) {
		DoubleLinkedListImpl<Integer> lista = new DoubleLinkedListImpl<>();
		lista.insert(3);
		lista.insert(4);
		lista.insert(0);
		lista.insert(43);
		lista.insert(-94);
		lista.insert(0);
		
		System.out.println(lista.optimizedSearch(0));
		System.out.println(lista.optimizedSearch(-94));
		System.out.println(lista.optimizedSearch(88));

		System.out.println(Arrays.toString(lista.toArray()));
		System.out.println(Arrays.toString(lista.inverteLinkedList(lista).toArray()));
	}

}