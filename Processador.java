public class Processador {
    private String variavelNome;
    private int variavelValor;

    public void processarElemento(Fila elemento) {
        //  Remover espaços vazios
        elemento = elemento.trim();

        //  verificar se é uma atribuição de variável (exemplo: x = 10)
        if (elemento.contains("=")) {
            atribuirVariavel(elemento);
        } 
        // 3️⃣ Verificar se é uma palavra reservada
        else if (isPalavraReservada(elemento)) {
            System.out.println("Palavra reservada detectada: " + elemento);
        } 
        // 4️⃣ Verificar se é uma expressão matemática com variáveis (exemplo: (a+b)*c)
        else if (isExpressaoMatematica(elemento)) {
            calcularExpressao(elemento);
        } 
        else {
            System.out.println("Entrada desconhecida: " + elemento);
        }
    }

    // 5️⃣ Verificar se é uma atribuição de variável (exemplo: x = 10)
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

    // 6️⃣ Verificar se é uma palavra reservada
    private boolean isPalavraReservada(String palavra) {
        String[] palavrasReservadas = {"PLAY", "ERASE", "REC", "EXIT", "PRINT"};
        for (String reservada : palavrasReservadas) {
            if (palavra.equalsIgnoreCase(reservada)) {
                return true;
            }
        }
        return false;
    }

    // 7️⃣ Verificar se é uma expressão matemática com variáveis (exemplo: (a+b)*c)
    private boolean isExpressaoMatematica(String expressao) {
        return expressao.matches(".*[a-zA-Z]+.*") && expressao.matches(".*[0-9+\\-*/()]+.*");
    }

    private void calcularExpressao(String expressao) {
        System.out.println("Expressão matemática detectada: " + expressao);
        // Aqui você pode implementar um avaliador de expressões matemáticas
    }
}
