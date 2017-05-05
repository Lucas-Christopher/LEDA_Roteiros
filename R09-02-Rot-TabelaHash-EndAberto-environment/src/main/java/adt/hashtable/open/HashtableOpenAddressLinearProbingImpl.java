package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	private int getHash(T element, int probe) {
		return ((HashFunctionLinearProbing<T>) super.getHashFunction()).hash(element, probe);
	}

	@Override
	public void insert(T element) {
		if (element == null)
			return;

		int hashKey;
		for (int i = 0; i < super.capacity(); i++) {
			hashKey = this.getHash(element, i);

			if (super.table[hashKey] == null) {
				super.table[hashKey] = element;
				super.elements++;
				return;
			} else {
				if (super.table[hashKey] != null || super.table[hashKey].equals(element)) {
					super.COLLISIONS++;
				}
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element == null)
			return;

		if (!super.isEmpty()) {
			int hashKey;
			for (int i = 0; i < super.capacity(); i++) {
				hashKey = this.getHash(element, i);

				if (super.table[hashKey] != null && super.table[hashKey].equals(element)) {
					super.table[hashKey] = null;
					super.elements--;
					return;
				}
			}
		}
	}

	@Override
	public T search(T element) {
		if (element == null)
			return null;

		if (!super.isEmpty()) {
			int hashKey;
			for (int i = 0; i < super.capacity(); i++) {
				hashKey = this.getHash(element, i);

				if (super.table[hashKey] != null && super.table[hashKey].equals(element))
					return element;
			}
		}
		return null;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;

		if (!super.isEmpty()) {
			int hashKey;
			for (int i = 0; i < super.capacity(); i++) {
				hashKey = this.getHash(element, i);

				if (super.table[hashKey] != null && super.table[hashKey].equals(element)) {
					index = hashKey;
					break;
				}
			}
		}
		return index;
	}

}
