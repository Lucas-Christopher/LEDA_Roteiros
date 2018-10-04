package activities;

import data_structure.Queue;
import data_structure.Stack;

public class InvertStackWithQueue {
	
	Stack stack;
	Queue queue;
	
	public InvertStackWithQueue(int size) {
		
		this.queue = new Queue(size);
		this.stack = new Stack(size);
	}
	
	public void invertStack(Stack stack) {
		
		// Coloca tudo da pilha na fila. Assim, a fila será a pilha invertida.
		while (!stack.isEmpty()) {
			this.queue.add(stack.pop());
		}
		
		// Coloca tudo da fila na pilha.
		while (!this.queue.isEmpty()) {
			this.stack.push(queue.remove());
		}
		return;
	}

}
