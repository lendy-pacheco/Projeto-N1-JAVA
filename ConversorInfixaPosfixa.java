public class ConversorInfixaPosfixa {
    // Define a precedência dos operadores
    private int precedencia(char operador) {
        if (operador == '+' || operador == '-') {
            return 1;
        } else if (operador == '*' || operador == '/') {
            return 2;
        } else if (operador == '^') {
            return 3;
        } else {
            // Retornar -1 quando não for operador
            return -1;
        }
    }

    // Método que converte uma expressão infixa para posfixa
    public String converterParaPosfixa(String expressao) {
        PilhaInfixaPosfixa pilha = new PilhaInfixaPosfixa(expressao.length());
        String saida = "";
        String erro = null;

        for (int i = 0; i < expressao.length(); i++) {
            char caractere = expressao.charAt(i);

            // Ignorar espaços em branco
            if (caractere == ' ') {
                continue;
            }

            // Se o caractere for uma variável (assumindo TratamentoDeString.ehLetraMaiuscula existe e funciona)
            if (TratamentoDeString.ehLetraMaiuscula(caractere)) {
                saida += caractere;
            }
            // Se o caractere for um '(', empilha
            else if (caractere == '(') {
                try {
                    pilha.push(caractere);
                } catch (Exception e) {
                    erro = "Erro ao empilhar '(': " + e.getMessage();
                    return "Erro na conversão";
                }
            }
            // Se o caractere for um ')', desempilha até encontrar '('
            else if (caractere == ')') {
                try {
                    while (!pilha.isEmpty() && pilha.topo() != '(') {
                        saida += pilha.pop();
                    }
                    if (!pilha.isEmpty() && pilha.topo() == '(') {
                        pilha.pop(); // Remove o '(' da pilha
                    } else {
                        return "Erro: Parênteses não balanceados";
                    }
                } catch (Exception e) {
                    erro = "Erro ao desempilhar ')': " + e.getMessage();
                    return "Erro na conversão";
                }
            }
            // Se for um operador
            else if (precedencia(caractere) > 0) {
                try {
                    // 1. Enquanto a pilha não estiver vazia e houver no seu topo um operador com prioridade maior ou igual ao encontrado
                    while (!pilha.isEmpty() && precedencia(pilha.topo()) >= precedencia(caractere)) {
                        // Desempilhe o operador e copie-o na saída
                        saida += pilha.pop();
                    }
                    // 2. Empilhe o operador encontrado
                    pilha.push(caractere);
                } catch (Exception e) {
                    erro = "Erro ao manipular operador '" + caractere + "': " + e.getMessage();
                    return "Erro na conversão";
                }
            } else {
                // Caractere desconhecido
                return "Erro: Caractere inválido na expressão: " + caractere;
            }
        }

        // Desempilha o que restou na pilha
        try {
            while (!pilha.isEmpty()) {
                saida += pilha.pop();
            }
        } catch (Exception e) {
            erro = "Erro ao desempilhar final: " + e.getMessage();
            return "Erro na conversão";
        }

        return saida;
    }
}