public class Processador {
    private String variavelNome;
    private int variavelValor;

    public void processarElemento(Object elemento) {
        if (!(elemento instanceof String)) {
            System.out.println("Erro: O elemento não é uma String.");
            return;
        }

        String comando = ((String) elemento).trim(); // Converte Object para String e remove espaços

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
            calcularExpressao(comando);
        } 
        else {
            System.out.println("Entrada desconhecida: " + comando);
        }
    }

    private void atribuirVariavel(String expressao) {
        String[] partes = expressao.split("=");

        if (partes.length == 2) {
            variavelNome = partes[0].trim();
            String valor = partes[1].trim();

            try {
                variavelValor = Integer.parseInt(valor);
                System.out.println("Variável atribuída: " + variavelNome + " = " + variavelValor);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Valor inválido para variável.");
            }
        } else {
            System.out.println("Erro: Atribuição inválida.");
        }
    }

    private boolean isPalavraReservada(String palavra) {
        String[] palavrasReservadas = {"PLAY", "EXIT", "ERASE", "REC", "VARS","RESET"};
        for (String reservada : palavrasReservadas) { // ":" =Ele percorrerá todos os elementos de uma Coleção.
            if (palavra.equalsIgnoreCase(reservada)) {
                return true;
            }
        }
        return false;
    }

    private boolean isExpressaoMatematica(String expressao) {
        return expressao.matches(".*[a-zA-Z]+.*") && expressao.matches(".*[0-9+\\-*/()]+.*");
    }

    private void calcularExpressao(String expressao) {
        System.out.println("Expressão matemática detectada: " + expressao);
    }
}
