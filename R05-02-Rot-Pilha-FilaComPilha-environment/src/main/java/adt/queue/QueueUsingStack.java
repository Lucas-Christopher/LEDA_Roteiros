package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size); // Pilha de entrada.
		stack2 = new StackImpl<T>(size); // Pilha de saida.
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull())
			throw new QueueOverflowException();

		if (element != null && !stack1.isFull()) {
			try {
				this.stack1.push(element); // Adiciona o elemento.
			} catch (StackOverflowException exception) {
				exception.printStackTrace();
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty())
			throw new QueueUnderflowException();

		// Passo 1: Quando for remover um elemento de stack1 (pilha de entrada),
		// passamos tudo de stack1 para stack2 (pilha de saida).
		permutar(this.stack1, this.stack2);

		// Passo 2: Apos feito, retiramos o ultimo elemento a ser adicionado na
		// stack2 e adicionamos de volta tudo para a stack1.

		T removido = null;

		try {
			removido = this.stack2.pop(); // Removido.
		} catch (StackUnderflowException exception) {
			exception.printStackTrace();
		}

		// Adicionando tudo novamente a pilha de entrada.
		this.permutar(this.stack2, this.stack1);

		return removido;
	}

	// Adiciona na pilha de saida os elementos da pilha de entrada,
	// pegando do topo.
	private void permutar(Stack<T> pilhaEntrada, Stack<T> pilhaSaida) throws QueueUnderflowException {

		while (!pilhaEntrada.isEmpty()) {
			try {
				pilhaSaida.push(pilhaEntrada.pop());
			} catch (StackUnderflowException exception) {
				throw new QueueUnderflowException();
			} catch (StackOverflowException exception) {
				exception.printStackTrace();
			}
		}
	}

	@Override
	public T head() {
		T head;

		try {
			permutar(this.stack1, this.stack2);
			head = stack2.top();
			permutar(this.stack2, this.stack1);
		} catch (QueueUnderflowException exception) {
			return null;
		}
		return head;
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}