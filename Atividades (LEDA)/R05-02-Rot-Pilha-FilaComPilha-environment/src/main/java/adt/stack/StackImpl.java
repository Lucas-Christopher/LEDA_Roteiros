package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		this.array = (T[]) new Object[size];
		this.top = -1;
	}

	@Override
	public T top() {
		if (this.isEmpty())
			return null;

		return this.array[this.top];
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return this.top == (this.array.length - 1);
	}

	@Override
	public void push(T element) throws StackOverflowException {

		if (this.isFull())
			throw new StackOverflowException();

		if (!this.isFull() && element != null) {
			this.top++;
			this.array[this.top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {

		if (this.isEmpty()) 
			throw new StackUnderflowException();

		T valueTop = this.top();
		this.top--;
		return valueTop;
	}
}