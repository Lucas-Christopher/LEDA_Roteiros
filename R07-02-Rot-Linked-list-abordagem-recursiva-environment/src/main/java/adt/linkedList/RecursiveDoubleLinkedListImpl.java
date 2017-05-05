//package adt.linkedList;
//
//public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {
//
//	protected RecursiveDoubleLinkedListImpl<T> previous;
//
//	public RecursiveDoubleLinkedListImpl() {
//	}
//
//	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
//			RecursiveDoubleLinkedListImpl<T> previous) {
//		super(data, next);
//		this.previous = previous;
//	}
//
//	// Insert Last.
//	@Override
//	public void insert(T element) {
//		if (element == null)
//			return;
//
//		if (this.data != null)
//			this.next.insert(element);
//
//		else {
//			this.setData(element);
//			this.setNext(new RecursiveSingleLinkedListImpl<>());
//			this.setPrevious(this);
//
//			if (this.previous == null) {
//				this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
//				previous.setNext(this);
//			}
//		}
//	}
//
//	@Override
//	public void insertFirst(T element) {
//		if (element == null)
//			return;
//
//		RecursiveDoubleLinkedListImpl<T> secondNode = new RecursiveDoubleLinkedListImpl<T>(this.getData(),
//				super.getNext(), this.getPrevious());
//
//		this.setData(element);
//		this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
//		this.setNext(secondNode);
//		secondNode.setPrevious(this);
//
//	}
//
//	@Override
//	public void removeFirst() {
//		if (this.isEmpty())
//			return;
//
//		if (super.next.getNext() == null)
//			super.setData(null);
//
//		else {
//			this.setData(super.next.getData());
//			this.setNext(super.next.getNext());
//		}
//	}
//
//	@Override
//	public void removeLast() {
//		if (!isEmpty()) {
//			// Se o proximo do node eh NIL, ele e o last.
//			if (this.getNext().isEmpty()) {
//				this.setData(this.getNext().getData());
//				// this.setNext(this.getNext().getNext());
//			} else {
//				((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
//			}
//		}
//	}
//
//	@Override
//	public void remove(T element) {
//		if (super.isEmpty() || element == null)
//			return;
//
//		if (this.data.equals(element))
//			this.removeFirst();
//		else
//			this.getNext().remove(element);
//	}
//
//	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
//		return previous;
//	}
//
//	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
//		this.previous = previous;
//	}
//}
package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<T>());
				this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());

			} else {
				if (this.next.isEmpty()) {
					this.next.setData(element);
					this.next.setNext(new RecursiveDoubleLinkedListImpl<T>());
					this.getNext().setPrevious(this);
				} else {
					this.getNext().insert(element);
				}
			}
		}

	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			RecursiveDoubleLinkedListImpl<T> segundo = new RecursiveDoubleLinkedListImpl<T>(this.data, this.next,
					this.previous);
			this.setData(element);
			this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
			this.setNext(segundo);
			segundo.setPrevious(this);

		}

	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (this.data.equals(element)) {
				removeFirst();
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {

			this.setData(this.next.getData());

			this.setNext(this.next.getNext());
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.getNext().isEmpty()) {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			} else {
				this.getNext().removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

	@Override
	public RecursiveDoubleLinkedListImpl<T> getNext() {
		return (RecursiveDoubleLinkedListImpl<T>) this.next;
	}
}
