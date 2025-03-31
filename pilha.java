public class Pilha {
    private int[] pilha;
    private int capacidade;
    private int topo;

    // 🔹 Construtor sem parâmetro (tamanho padrão 10)
    public Pilha() {
        this(10); // Chama o outro construtor com tamanho 10
    }

    // 🔹 Construtor com tamanho definido
    public Pilha(int tamanho) {
        this.capacidade = tamanho;
        this.pilha = new int[capacidade];
        this.topo = -1; // Indica pilha vazia
    }

    // 🔹 Verifica se a pilha está vazia
    public boolean isEmpty() {
        return topo == -1;
    }

    // 🔹 Verifica se a pilha está cheia
    public boolean isFull() {
        return topo == capacidade - 1;
    }

    // 🔹 Adiciona um elemento no topo da pilha
    public void push(int e) {
        if (isFull()) {
            System.out.println("Erro: A pilha está cheia!");
            return;
        }
        pilha[++topo] = e; // Incrementa topo e adiciona o elemento
    }

    // 🔹 Remove e retorna o elemento do topo da pilha
    public int pop() {
        if (isEmpty()) {
            System.out.println("Erro: A pilha está vazia!");
            return -1;
        }
        return pilha[topo--]; // Retorna o elemento do topo e decrementa o índice
    }

    // 🔹 Retorna o elemento do topo sem remover
    public int topo() {
        if (isEmpty()) {
            System.out.println("Erro: A pilha está vazia!");
            return -1;
        }
        return pilha[topo];
    }

    // 🔹 Retorna o número de elementos armazenados na pilha
    public int sizeElements() {
        return topo + 1;
    }
}

