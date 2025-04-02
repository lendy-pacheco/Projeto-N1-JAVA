public class Fila {
    private Object[] fila;
    private int capacidade;
    private int ini;
    private int fim;
    private int tamanho;

    // 🔹 Construtor que inicia a fila vazia
    public Fila(int capacidade) {
        this.capacidade = capacidade;
        this.fila = new Object[capacidade];
        this.ini = 0;
        this.fim = -1;
        this.tamanho = 0;
    }

    // 🔹 Verifica se a fila está vazia
    public boolean qIsEmpty() {
        return tamanho == 0;
    }

    // 🔹 Verifica se a fila está cheia
    public boolean qIsFull() {
        return tamanho == capacidade;
    }

    // 🔹 Insere um elemento no final da fila
    public void enqueue(Object e) {
        if (qIsFull()) {
            System.out.println("Erro: A fila está cheia!");
            return;
        }
        fim = (fim + 1) % capacidade;
        fila[fim] = e;
        tamanho++;
    }

    // 🔹 Remove o elemento do início da fila e retorna seu valor
    public Object dequeue() {
        /*if (qIsEmpty()) {
            System.out.println("Erro: A fila está vazia!");
            return null;
        }*/
        Object removido = fila[ini];
        ini = (ini + 1) % capacidade;
        tamanho--;
        return removido;
    }

    // 🔹 Retorna o elemento no início da fila (sem remover)
    public Object front() {
        if (qIsEmpty()) {
            System.out.println("Erro: A fila está vazia!");
            return null;
        }
        return fila[ini];
    }

    // 🔹 Retorna o elemento no final da fila (sem remover)
    public Object rear() {
        if (qIsEmpty()) {
            System.out.println("Erro: A fila está vazia!");
            return null;
        }
        return fila[fim];
    }

    // 🔹 Retorna o número de elementos na fila
    public int size() {
        return tamanho;
    }
}
