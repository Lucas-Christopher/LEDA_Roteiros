package atividade;

import estrutura_dados.Fila;
import estrutura_dados.Pilha;

public class InvertePilhaComFila {
	
	Pilha pilha;
	Fila fila;
	
	public InvertePilhaComFila(int tamanho) {
		
		this.fila = new Fila(tamanho);
		this.pilha = new Pilha(tamanho);
	}
	
	public void invertePilha(Pilha pilha) {
		
		// Coloca tudo da pilha na fila. Assim, a fila será a pilha invertida.
		while (!pilha.isEmpty()) {
			this.fila.add(pilha.pop());
		}
		
		// Coloca tudo da fila na pilha.
		while (!this.fila.isEmpty()) {
			this.pilha.push(fila.remove());
		}
		return;
	}
	

}
