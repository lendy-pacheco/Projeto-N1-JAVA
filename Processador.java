public class Processador {
    private static final int MAX_VARS = 100; 
    private String[] nomesVariaveis = new String[MAX_VARS]; // armazena os nomes das variaveis
    private double[] valoresVariaveis = new double[MAX_VARS]; // armazena os valores das variaveis
    private int contadorVariaveis = 0; 

    public void processarElemento(Object elemento) {
        if (!(elemento instanceof String)) {
            System.out.println("erro: o elemento nao e uma string.");
            return;
        }

        String comando = ((String) elemento).trim(); // remove espacos extras

        // verifica se e uma atribuicao de variavel
        if (comando.contains("=")) {
            atribuirVariavel(comando);
        } 
        // verifica se e uma palavra reservada
        else if (isPalavraReservada(comando)) {
            System.out.println("palavra reservada detectada: " + comando);
        } 
        // verifica se e uma expressao matematica com variaveis
        else if (isExpressaoMatematica(comando)) {
            converter(comando);
        } 
        else {
            System.out.println("entrada desconhecida: " + comando);
        }
    }

    private void atribuirVariavel(String expressao) {
        String[] partes = expressao.split("=");

        if (partes.length == 2) {
            String nomeVariavel = partes[0].trim(); // nome da variavel
            String valor = partes[1].trim(); // valor da variavel

            try {
                double valorConvertido;
                if (valor.contains(".")) { 
                    valorConvertido = Double.parseDouble(valor); // converte para double
                } else {
                    valorConvertido = Integer.parseInt(valor); // converte para int, mas armazena como double
                }

                armazenarVariavel(nomeVariavel, valorConvertido);
                System.out.println("variavel atribuida: " + nomeVariavel + " = " + valorConvertido);

            } catch (NumberFormatException e) {
                System.out.println("erro: valor invalido para variavel.");
            }
        } else {
            System.out.println("erro: atribuicao invalida.");
        }
    }

    private void armazenarVariavel(String nome, double valor) {
        for (int i = 0; i < contadorVariaveis; i++) {
            if (nomesVariaveis[i].equals(nome)) { // atualiza variavel existente
                valoresVariaveis[i] = valor;
                return;
            }
        }
        
        if (contadorVariaveis < MAX_VARS) { // armazena nova variavel
            nomesVariaveis[contadorVariaveis] = nome;
            valoresVariaveis[contadorVariaveis] = valor;
            contadorVariaveis++;
        } else {
            System.out.println("erro: limite de variaveis atingido.");
        }
    }

    private boolean isPalavraReservada(String palavra) {
        String[] palavrasReservadas = {"PLAY", "EXIT", "ERASE", "REC", "VARS", "RESET","EXPRESSAO MATEMATICA INFIXA"};
        if (palavra=="ERASE"||palavra=="VARS"){
          REPL.voltaREC(palavra);
        }
        for (String reservada : palavrasReservadas) { 
            if (palavra.equalsIgnoreCase(reservada)) { 
                return true;
            }
        }
        return false;
    }

    private boolean isExpressaoMatematica(String expressao) {
        // verifica se contem letras (para identificar variaveis) e operadores ou numeros
        return expressao.matches(".*[a-zA-Z]+.*") && expressao.matches(".*[0-9+\\-*/()]+.*");
    }

    private void converter(String expressao) {
        System.out.println("expressao matematica detectada: " + expressao);
        ConversorInfixaPosfixa conversor = new ConversorInfixaPosfixa();
        String saida = conversor.converterParaPosfixa(expressao,valoresVariaveis,nomesVariaveis); // converte expressao para notacao posfixa
        System.out.println(saida);
    }
}
