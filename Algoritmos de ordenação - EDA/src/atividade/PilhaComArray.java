package atividade;

import java.util.Scanner;

class PilhaComArray {

	private int[] stack;
	private int top;
	private int capacity;

	public PilhaComArray(int size) {
		this.stack = new int[size];
		this.capacity = size;
		this.top = -1;
	}

	public boolean isEmpty() {
		return this.top == -1;
	}

	public boolean isFull() {
		return this.top == (this.capacity - 1);
	}

	public void push(int value) {
		if (this.isFull()) {
			System.out.println("full");
			return;
		}

		this.top++;
		this.stack[this.top] = value;
	}

	public void pop() {

		if (this.isEmpty()) {
			System.out.println("empty");
			return;
		}
		--this.top;
	}

	public void peek() {

		if (this.isEmpty()) {
			System.out.println("empty");
			return;
		}

		System.out.println(this.stack[this.top]);
	}

	public void print() {

		if (this.isEmpty())
			System.out.print("empty");

		String retorno = "";

		for (int i = 0; i <= this.top; i++) {
			retorno += " " + this.stack[i];
		}
		System.out.println(retorno.replaceFirst(" ", ""));
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int tamanho = scan.nextInt();

		// Pilha instanciada.
		PilhaComArray pilha = new PilhaComArray(tamanho);
		scan.nextLine();

		// Opcao lida pelo usuario.
		String opcaoLida = scan.nextLine();

		while (!opcaoLida.equals("end")) {
			
		String[] entrada = opcaoLida.split(" ");

		String opcao = entrada[0];
		int valor = 0;

		if (entrada.length > 1) {
			opcao = entrada[0];
			valor = Integer.parseInt(entrada[1]);
		}
			switch (opcao) {
			
			case "push":
				pilha.push(valor);
				break;

			case "pop":
				pilha.pop();
				break;

			case "peek":
				pilha.peek();
				break;

			case "print":
				pilha.print();
				break;

			default:
				break;
			}
			
			opcaoLida = scan.nextLine();
		}
	}
}