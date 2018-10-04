package adt.linkedList;

import java.util.Arrays;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if (this.isEmpty())
			return 0;
		
		else
			return 1 + this.getNext().size();
	}

	@Override
	public T search(T element) {
		if (element == null || this.isEmpty()) return null;
		
		if (this.getData().equals(element))
			return element;
		else
			return this.getNext().search(element);
	}

	@Override
	public void insert(T element) {
		if (element == null) return;
		
		if (this.isEmpty()) {
			this.data = element;
			this.next = new RecursiveSingleLinkedListImpl<T>();
		}
		else
			this.next.insert(element);
	}

	@Override
	public void remove(T element) {
		if (element == null || this.isEmpty()) return;
		
		if (this.data.equals(element)) {
			this.setData(this.next.getData());
			this.setNext(this.next.getNext());
		}
		else
			this.next.remove(element);
	}

	@Override
	public T[] toArray() {
		int i = 0;
		T[] array = (T[]) new Object[this.size()];
		
		if (this.isEmpty())
			return array;
		
		this.toArray(array, this, i);
		return array;
	}

	private void toArray(T[] array, RecursiveSingleLinkedListImpl<T> node, int i) {
		if (!node.isEmpty()) {
			array[i] = node.getData();
			this.toArray(array, node.getNext(), ++i);
		}
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}
	
	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}
	
	// ********************** MY METODS *****************
	public int indexOf(T element) {
		RecursiveSingleLinkedListImpl<T> aux = this;
		int index = 0;

		return indexOfRecursive(element, aux, index);
//		while (!aux.isEmpty() && !aux.getData().equals(element)) {
//			aux = aux.getNext();
//			index++;
//		}
//		if (!aux.isEmpty())
//			return index;
//		else
//			return -1;

	}
	
	private int indexOfRecursive(T element, RecursiveSingleLinkedListImpl<T> aux, int index) {
		if (aux.isEmpty())
			return -1;
		else if (aux.getData().equals(element))
			return index;
		else
			return indexOfRecursive(element, aux.getNext(), ++index);
		
	}
	
	public int lastIndexOf(T element) {
		RecursiveSingleLinkedListImpl<T> aux = this;
		int lastIndex = -1; // Considerando que nao existe.
		int i = 0;
		
		while (!aux.isEmpty()) {
			if (aux.getData().equals(element)) {
				lastIndex = i;
			}
			aux = aux.getNext();
			i++;
		}
		return lastIndex;
	}
	
	public void removeByIndex(int index) {
		RecursiveSingleLinkedListImpl<T> aux = this;
		RecursiveSingleLinkedListImpl<T> previous = new RecursiveDoubleLinkedListImpl<>();
		int i = 0;
		
		while (!aux.isEmpty() && i < index) {
			previous = aux;
			aux = aux.getNext();
			i++;
		}
		if (i == index && !aux.isEmpty()) {
			previous.next = aux.next;
		}
	}
	
	public RecursiveSingleLinkedListImpl<T> inverteLinkedList(RecursiveSingleLinkedListImpl<T> list) {
		T[] array = (T[]) new Object[list.size()];
		RecursiveSingleLinkedListImpl<T> aux = this;
		int i = 0;
		
		while (!aux.isEmpty()) {
			array[i] = aux.getData();
			aux = aux.getNext();
			i++;
		}
		
		list = new RecursiveSingleLinkedListImpl<>();
		for (int j = array.length - 1; j >= 0; j--) {
			list.insert(array[j]);
		}
		return list;
	}
	
	public static void main(String[] args) {
		RecursiveSingleLinkedListImpl<Integer> lista = new RecursiveSingleLinkedListImpl<>();
		lista.insert(3);
		lista.insert(4);
		lista.insert(0);
		lista.insert(43);
		lista.insert(-94);
		lista.insert(0);
		
		System.out.println(Arrays.toString(lista.toArray()));
		System.out.println(Arrays.toString(lista.inverteLinkedList(lista).toArray()));
	}

}