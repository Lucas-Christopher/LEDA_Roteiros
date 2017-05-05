package estrutura_dados;

public class Fila {

    private int[] queue;
    private int capacity, head, tail;

    // FIFO: First in, first out.
    public Fila(int capacidade) {
        this.capacity = capacidade;
        this.head = -1;
        this.tail = -1;
        this.queue = new int[capacidade];
    }

    public boolean isEmpty() {
        return (this.head == -1 && this.tail == -1);
    }

    public boolean isFull() {
        return ((this.tail + 1) % capacity) == this.head; 
    }

    // OPERATIONS:
    
    public void add(int n) {

        if (isFull()) throw new RuntimeException("Is Full");
        if (isEmpty()) {
            this.head = 0;
            this.tail = 0;
            this.queue[0] = n;
        } else {
                tail = (tail + 1) % capacity;
                this.queue[tail] = n;
        }

    }

    public int remove() {
        
        if (isEmpty())
            throw new RuntimeException("QueuIsEmptyException");

        int value = queue[head];
        
        if (this.head == this.tail) {
            this.head = -1;
            this.tail = -1;
        } else {
            this.head = ((this.head + 1) % capacity);
        }
        return value; 
        
    }
    
    public int head() {
        if (this.isEmpty())
            throw new RuntimeException("QueuIsEmptyException");
        return this.queue[head];
    }    

}