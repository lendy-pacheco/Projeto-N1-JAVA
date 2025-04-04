public class Processador {
    private static final int MAX_VARS = 100; 
    private String[] nomesVariaveis = new String[MAX_VARS]; // armazena os nomes das variáveis
    private double[] valoresVariaveis = new double[MAX_VARS]; // armazena os valores das variáveis
    private int contadorVariaveis = 0; 

    public void processarElemento(Object elemento) {
        if (!(elemento instanceof String)) {
            System.out.println("Erro: O elemento não é uma String.");
            return;
        }

        String comando = ((String) elemento).trim(); //remove espaços extras

        // verificar se é uma atribuição de variável
        if (comando.contains("=")) {
            atribuirVariavel(comando);
        } 
        // verificar se é uma palavra reservada
        else if (isPalavraReservada(comando)) {
            System.out.println("Palavra reservada detectada: " + comando);
        } 
        // verificar se é uma expressão matemática com variáveis 
        else if (isExpressaoMatematica(comando)) {
            converter(comando);
        } 
        else {
            System.out.println("Entrada desconhecida: " + comando);
        }
    }

    private void atribuirVariavel(String expressao) {
        String[] partes = expressao.split("=");

        if (partes.length == 2) {
            String nomeVariavel = partes[0].trim(); // nome da variável
            String valor = partes[1].trim(); // valor da variável

            try {
                double valorConvertido;
                if (valor.contains(".")) { 
                    valorConvertido = Double.parseDouble(valor); // converte para double
                } else {
                    valorConvertido = Integer.parseInt(valor); // converte para int, mas armazena como double
                }

                armazenarVariavel(nomeVariavel, valorConvertido);
                System.out.println("Variável atribuída: " + nomeVariavel + " = " + valorConvertido);

            } catch (NumberFormatException e) {
                System.out.println("Erro: Valor inválido para variável.");
            }
        } else {
            System.out.println("Erro: Atribuição inválida.");
        }
    }

    private void armazenarVariavel(String nome, double valor) {
        for (int i = 0; i < contadorVariaveis; i++) {
            if (nomesVariaveis[i].equals(nome)) { // atualiza variável existente
                valoresVariaveis[i] = valor;
                return;
            }
        }
        
        if (contadorVariaveis < MAX_VARS) { // armazena nova variável com array duplo
            nomesVariaveis[contadorVariaveis] = nome;
            valoresVariaveis[contadorVariaveis] = valor;
            contadorVariaveis++;
        } else {
            System.out.println("Erro: Limite de variáveis atingido.");
        }
    }

    private boolean isPalavraReservada(String palavra) {
        String[] palavrasReservadas = {"PLAY", "EXIT", "ERASE", "REC", "VARS", "RESET"};
        for (String reservada : palavrasReservadas) { 
            if (palavra.equalsIgnoreCase(reservada)) { 
                return true;
            }
        }
        return false;
    }

    private boolean isExpressaoMatematica(String expressao) {
        return expressao.matches(".[a-zA-Z]+.") && expressao.matches(".[0-9+\\-/()]+.*");
    }

    private void converter(String expressao) {
        System.out.println("Expressão matemática detectada: " + expressao);
        ConversorInfixaPosfixa conversor = new ConversorInfixaPosfixa();
        String saida = conversor.converterParaPosfixa(expressao);
        System.out.println(saida);
        // implementação futura para converter expressões matemáticas
    }

    
}