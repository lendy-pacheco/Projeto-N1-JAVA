import java.util.HashMap;

public class Processador {
    private HashMap<String, Double> variaveis = new HashMap<>(); // Mapeia nomes de variáveis para seus valores

    public void processarElemento(Object elemento) {
        if (!(elemento instanceof String)) {
            System.out.println("Erro: O elemento não é uma String.");
            return;
        }

        String comando = ((String) elemento).trim(); // Remove espaços extras

        // 1️⃣ Verificar se é uma atribuição de variável (exemplo: x = 10)
        if (comando.contains("=")) {
            atribuirVariavel(comando);
        } 
        // 2️⃣ Verificar se é uma palavra reservada
        else if (isPalavraReservada(comando)) {
            System.out.println("Palavra reservada detectada: " + comando);
        } 
        // 3️⃣ Verificar se é uma expressão matemática com variáveis (exemplo: (a+b)*c)
        else if (isExpressaoMatematica(comando)) {
            converter(comando);
            
            //String expressaoPosfixa = ConversorInfixaPosfixa.converterParaPosfixa(comando);
            ConversorInfixaPosfixa conversor = new ConversorInfixaPosfixa();
            String expressaoPosfixa = conversor.converterParaPosfixa(comando);
            System.out.println(expressaoPosfixa);
        } 
        else {
            System.out.println("Entrada desconhecida: " + comando);
        }
    }

    private void atribuirVariavel(String expressao) {
        String[] partes = expressao.split("=");

        if (partes.length == 2) {
            String nomeVariavel = partes[0].trim(); // Nome da variável
            String valor = partes[1].trim(); // Valor da variável

            try {
                double valorConvertido;
                if (valor.contains(".")) { 
                    valorConvertido = Double.parseDouble(valor); // Converte para double
                } else {
                    valorConvertido = Integer.parseInt(valor); // Converte para int, mas armazena como double
                }

                variaveis.put(nomeVariavel, valorConvertido); // Armazena a variável
                System.out.println("Variável atribuída: " + nomeVariavel + " = " + valorConvertido);

            } catch (NumberFormatException e) {
                System.out.println("Erro: Valor inválido para variável.");
            }
        } else {
            System.out.println("Erro: Atribuição inválida.");
        }
    }

    private boolean isPalavraReservada(String palavra) {
        String[] palavrasReservadas = {"PLAY", "EXIT", "ERASE", "REC", "VARS", "RESET"};
        for (String reservada : palavrasReservadas) { // ":" = Ele percorre todos os elementos da coleção.
            if (palavra.equalsIgnoreCase(reservada)) { // Compara ignorando maiúsculas e minúsculas
                return true;
            }
        }
        return false;
    }

    private boolean isExpressaoMatematica(String expressao) {
        return expressao.matches(".*[a-zA-Z]+.*") && expressao.matches(".*[0-9+\\-*/()]+.*");
    }

    private void converter(String expressao) {
        System.out.println("Expressão matemática detectada: " + expressao);
        //String expressaoPosfixa = ConversorInfixaPosfixa.converterParaPosfixa(comando);
        // Aqui você pode implementar um parser para calcular expressões com variáveis armazenadas
    }

    public void imprimirVariaveis() {
        System.out.println("Variáveis armazenadas: " + variaveis);
    }
}
