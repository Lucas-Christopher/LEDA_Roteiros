package estrutura_dados;

public class Pilha {

	private int[] stack;
	private int capacity;
	private int top;

	// LIFO: Last in, first out.
	public Pilha(int capacity) {

		this.stack = new int[capacity];
		this.top = -1; // Empty stack.
		this.capacity = capacity;

	}

	// If the stack is empty, return true.
	public boolean isEmpty() {
		return this.top == -1;
	}

	// If the top is equal to the capacity size minus one (due to the size of
	// the vector), return true.
	public boolean isFull() {
		return this.top == (capacity - 1);
	}
	
	// OPERATIONS:

	public void push(int value) {

		if (this.isFull())
			throw new RuntimeException("FullStackException");
		this.top += 1;
		this.stack[top] = value;
	}

	public int pop() {
		if (this.isEmpty())
			throw new RuntimeException("EmptyStackException");
		
		int valueTop = this.stack[top];
		top -= 1;
		return valueTop;
	}

	public int peak() {
		if (this.isEmpty())
			throw new RuntimeException("EmptyStackException");
		return this.stack[top];
	}

	public int size() {
		return this.top + 1;
	}
	
	public static void main(String[] args) {
        Pilha pilha = new Pilha(5);
        assert pilha.isEmpty();
        assert !pilha.isFull();
        assert pilha.size() == 0;
    
        pilha.push(10);
        assert pilha.peak() == 10;
        assert pilha.size() == 1;
        assert !pilha.isEmpty();
    
        pilha.push(5);
        assert pilha.size() == 2;

        assert pilha.pop() == 5;

        pilha.push(7);
        assert pilha.peak() == 7;
        pilha.pop();

        assert !pilha.isEmpty();
    }

}
