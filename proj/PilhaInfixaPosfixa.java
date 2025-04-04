package proj;
public class PilhaInfixaPosfixa {
    private int tamanho;
    private int topoPilha;
    private double[] e; // Agora armazena números

    public PilhaInfixaPosfixa(int tamanho) {
        this.tamanho = tamanho;
        this.e = new double[tamanho];
        this.topoPilha = -1;
    }

    public boolean isEmpty() {
        return this.topoPilha == -1;
    }

    public boolean isFull() {
        return this.topoPilha == this.e.length - 1;
    }

    public void push(double elemento) throws Exception {
        if (!this.isFull()) {
            this.e[++this.topoPilha] = elemento;
        } else {
            throw new Exception("Overflow - Estouro de Pilha");
        }
    }

    public double pop() throws Exception {
        if (!this.isEmpty()) {
            return this.e[this.topoPilha--];
        } else {
            throw new Exception("Underflow - Esvaziamento de Pilha");
        }
    }

    public double topo() throws Exception {
        if (!this.isEmpty()) {
            return this.e[this.topoPilha];
        } else {
            throw new Exception("Underflow - Esvaziamento de Pilha");
        }
    }

    public int sizeElements() {
        return topoPilha + 1;
    }
}
