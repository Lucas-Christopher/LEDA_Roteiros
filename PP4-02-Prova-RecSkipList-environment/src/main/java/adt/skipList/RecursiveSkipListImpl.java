package adt.skipList;

import java.util.ArrayList;
import adt.skipList.SkipListNode;

public class RecursiveSkipListImpl<T> extends SkipListImpl<T> {
	public RecursiveSkipListImpl(int maxLevel) {
		super(maxLevel);
	}

	/**
	 * A pesquisa implementada com recursao. O algoritmo deve seguir a ideia de
	 * caminhar na diagonal da skip list (tentando caminhar na horizontal sempre
	 * que possivel ou descer de nivel, caso contrario). Caso o noh nao perteça
	 * a skip list, deve-se retornar null.
	 */
	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> aux = this.root;
		return searchRecurvise(key, aux, maxHeight - 1);
	}

	private SkipListNode<T> searchRecurvise(int key, SkipListNode<T> node, int i) {
		if (node.forward[i] != null && node.forward[i].getKey() < key) {
			return searchRecurvise(key, node.forward[i], i);
		} else {
			i--;
			if (i >= 0)
				return searchRecurvise(key, node, i);
			i++;
		}
		if (node.forward[0].getKey() == key)
			return node.forward[0];
		else
			return null;
	}

	/**
	 * Metodo que calcula a altura da skip list usando recursao.
	 */
	@Override
	public int height() {
		if (size() == 0)
			return 0;

		else {
			SkipListNode<T> aux = this.root.forward[0];
			int height = 0;

			return heightRecursive(aux, height);
		}
	}

	private int heightRecursive(SkipListNode<T> aux, int height) {
		if (aux.equals(this.NIL))
			return height;
		
		if (height < aux.height()) {
			height = aux.height();
		}
		return heightRecursive(aux.getForward(0), height);
	}

	/**
	 * Retorna um array contendo os nohs da skip list por altura. Assim, os nohs
	 * com maior altura sao colocados no array primeiro (em ordem crescente, ou
	 * seja, da esquerda para a direita) depois os nohs imediatamente menores, e
	 * assim por diante. Por exemplo, considere a skip list com altura maxima 5
	 * e nohs internos dados por [(4,2),[8,3],(10,1),(15,2),(19,1)], onde cada
	 * par representa (key,height). O resultado deste metodo seria, neste caso,
	 * [(8,3),(4,2),(15,2),(10,1),(19,1)]. O metodo DEVE ser implementado usando
	 * recursao. Dica: pense numa forma de ir descendo pela skip list usando
	 * recursao e tambem caminhando horizontalmente pela skip list usando
	 * recursao. voce pode pensar em fzer dois metodos recursivos: um para
	 * caminhar verticalmente e outro para caminhar horizontalmente.
	 */
	public SkipListNode<T>[] toArrayByHeight() {
		ArrayList<SkipListNode<T>> list = new ArrayList<SkipListNode<T>>();
		
		for (int i = maxHeight; i > 0; i--)
			addNode(this.root.getForward(0), list, i);
		
		return list.toArray(new SkipListNode[list.size()]);

	}

	private void addNode(SkipListNode<T> node, ArrayList<SkipListNode<T>> list, int level) {
		if (node == this.NIL)
			return;
		
		if (level == super.maxHeight)
			return;
		
		if (node.height() == level)
			list.add(node);

		addNode(node.getForward(0), list, level);
	}
}
