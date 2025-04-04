package proj;
public class PilhaUniversal {
    private char[] dadosChar;
    private double[] dadosDouble;
    private int topoChar;
    private int topoDouble;

    public PilhaUniversal(int capacidade) {
        dadosChar = new char[capacidade];
        dadosDouble = new double[capacidade];
        topoChar = -1;
        topoDouble = -1;
    }

    // 🔹 Métodos para CHAR (operadores)
    public void push(char c) {
        dadosChar[++topoChar] = c;
    }

    public char popChar() {
        return dadosChar[topoChar--];
    }

    public char topoChar() {
        return dadosChar[topoChar];
    }

    public boolean isEmptyChar() {
        return topoChar == -1;
    }

    // 🔹 Métodos para DOUBLE (valores)
    public void push(double d) {
        dadosDouble[++topoDouble] = d;
    }

    public double popDouble() {
        return dadosDouble[topoDouble--];
    }

    public double topoDouble() {
        return dadosDouble[topoDouble];
    }

    public boolean isEmptyDouble() {
        return topoDouble == -1;
    }
}
