package activities;

import java.util.Scanner;

class StackWithArray {

	private int[] stack;
	private int top;
	private int capacity;

	public StackWithArray(int size) {
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
		int size = scan.nextInt();

		// Pilha instanciada.
		StackWithArray stack = new StackWithArray(size);
		scan.nextLine();

		// Opcao lida pelo usuario.
		String readOption = scan.nextLine();

		while (!readOption.equals("end")) {
			
		String[] aux = readOption.split(" ");

		String opcao = aux[0];
		int valor = 0;

		if (aux.length > 1) {
			opcao = aux[0];
			valor = Integer.parseInt(aux[1]);
		}
			switch (opcao) {
			
			case "push":
				stack.push(valor);
				break;

			case "pop":
				stack.pop();
				break;

			case "peek":
				stack.peek();
				break;

			case "print":
				stack.print();
				break;

			default:
				break;
			}
			
			readOption = scan.nextLine();
		}
	}
}