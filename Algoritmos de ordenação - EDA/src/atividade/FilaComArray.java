package atividade;

import java.util.Scanner;

public class FilaComArray {

	private int[] queue;
	private int head, tail, capacity;

	public FilaComArray(int size) {

		this.queue = new int[size];
		this.head = -1;
		this.tail = -1;
		this.capacity = size;
	}

	public boolean isEmpty() {
		return (this.head == -1 && this.tail == -1);
	}

	public boolean isFull() {
		return (this.tail + 1) % this.capacity == this.head;
	}

	public void enqueue(int element) {

		if (this.isFull()) {
			System.out.println("full");
			return;
		}

		if (this.isEmpty()) {
			this.head++;
			this.tail++;
			this.queue[this.head] = element;
		} else {
			this.tail = (this.tail + 1) % this.capacity;
			this.queue[this.tail] = element;
		}
	}

	public void dequeue() {

		if (this.isEmpty()) {
			System.out.println("empty");
			return;
		}

		if (this.head == this.tail) {
			this.head = -1;
			this.tail = -1;

		} else {
			this.head = ((this.head + 1) % this.capacity);
		}
	}

	public void print() {

		if (this.isEmpty()) {
			System.out.println("empty");
			return;
		}

		String out = "";

		for (int i = this.head; i <= this.tail; i++) {
			out += " " + this.queue[i];
		}
		System.out.println(out.replaceFirst(" ", ""));
	}

	public void head() {

		if (this.isEmpty()) {
			System.out.println("empty");
			return;
		}

		System.out.println(this.queue[this.head]);
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int tamanho = scan.nextInt();

		FilaComArray fila = new FilaComArray(tamanho);
		scan.nextLine();

		String opcaoLida = scan.nextLine();

		while (!opcaoLida.equals("end")) {

			String[] quebra = opcaoLida.split(" ");
			String opcao = quebra[0];
			int valor = 0;

			if (quebra.length > 1) 
				valor = Integer.parseInt(quebra[1]);
			
			switch (opcao) {
			
			case "add":
				fila.enqueue(valor);
				break;
			
			case "remove":
				fila.dequeue();
				break;
			
			case "element":
				fila.head();
				break;
			
			case "print":
				fila.print();
				break;
				
			default: break;
			
			}
			
			opcaoLida = scan.nextLine();
		}
	}
}