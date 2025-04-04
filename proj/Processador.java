package proj;

public class Processador {
    private static final int MAX_VARS = 100;
    private String[] nomesVariaveis = new String[MAX_VARS];
    private double[] valoresVariaveis = new double[MAX_VARS];
    private int contadorVariaveis = 0;

    public void processarElemento(Object elemento) {
        if (!(elemento instanceof String)) {
            System.out.println("Erro: o elemento não é uma String.");
            return;
        }

        String comando = ((String) elemento).trim();

        if (comando.contains("=")) {
            atribuirVariavel(comando);
        } else if (isPalavraReservada(comando)) {
            if (comando.equals("VARS")) {
                System.out.println("Variáveis definidas:");
                for (int i = 0; i < contadorVariaveis; i++) {
                    if (nomesVariaveis[i] != null) {
                        System.out.println(nomesVariaveis[i] + " = " + valoresVariaveis[i]);
                    }
                }
            }
        } else if (isExpressaoMatematica(comando)) {
            converter(comando);
        } else {
            System.out.println("Entrada desconhecida: " + comando);
        }
    }

    private void atribuirVariavel(String expressao) {
        String[] partes = expressao.split("=");

        if (partes.length == 2) {
            String nomeVariavel = partes[0].trim();
            String valor = partes[1].trim();

            try {
                double valorConvertido;
                if (valor.contains(".")) {
                    valorConvertido = Double.parseDouble(valor);
                } else {
                    valorConvertido = Integer.parseInt(valor);
                }

                armazenarVariavel(nomeVariavel, valorConvertido);
                System.out.println("Variável atribuída: " + nomeVariavel + " = " + valorConvertido);

            } catch (NumberFormatException e) {
                System.out.println("Erro: valor inválido para variável.");
            }
        } else {
            System.out.println("Erro: atribuição inválida.");
        }
    }

    private void armazenarVariavel(String nome, double valor) {
        for (int i = 0; i < contadorVariaveis; i++) {
            if (nomesVariaveis[i].equals(nome)) {
                valoresVariaveis[i] = valor;
                return;
            }
        }

        if (contadorVariaveis < MAX_VARS) {
            nomesVariaveis[contadorVariaveis] = nome;
            valoresVariaveis[contadorVariaveis] = valor;
            contadorVariaveis++;
        } else {
            System.out.println("Erro: limite de variáveis atingido.");
        }
    }

    private boolean isPalavraReservada(String palavra) {
        String[] palavrasReservadas = {"PLAY", "EXIT", "ERASE", "REC", "VARS", "RESET", "EXPRESSAO MATEMATICA INFIXA"};
        
        for (String reservada : palavrasReservadas) {
            if (palavra.equalsIgnoreCase(reservada)) {
                return true;
            }
        }
        return false;
    }

    private boolean isExpressaoMatematica(String expressao) {
        return expressao.matches(".*[a-zA-Z0-9+\\-*/()]+.*");
    }

    private void converter(String expressao) {
        System.out.println("Expressão matemática detectada: " + expressao);
        ConversorInfixaPosfixa conversor = new ConversorInfixaPosfixa();
        String saida = conversor.converterParaPosfixa(expressao, valoresVariaveis, nomesVariaveis);
        System.out.println(saida);
    }
}
