package atividade;

import java.util.Scanner;

public class Parenteses {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		// String de entrada
		String string = scan.nextLine();

		Pilha pilha = new Pilha(string.length());

		// Array com caracteres da string.
		String[] array = string.split("");

		for (int i = 0; i < array.length; i++) {
			
			if (array[i].equals("(") || array[i].equals("[")) {
				pilha.push(array[i]);
			}
			else {
				if (!pilha.isEmpty()) {
					pilha.pop();
				} else {
					System.out.println("N");
					return;
				}
			}
		}
		
		if (pilha.isEmpty())
			System.out.println("S");
		else
			System.out.println("N");
	}
}

class Pilha {

	private String[] stack;
	private int top;

	public Pilha(int size) {
		this.stack = new String[size];
		this.top = -1;
	}

	public boolean isEmpty() {
		return this.top == -1;
	}

	public void push(String value) {

		this.top++;
		this.stack[this.top] = value;
	}

	public int peek() {
		return this.top;
	}

	public String pop() {

		if (this.isEmpty())
			System.out.println("N");

		String removed = this.stack[this.top];
		--this.top;

		return removed;
	}
}
