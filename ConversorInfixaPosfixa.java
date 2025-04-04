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

    public boolean verificarParenteses(String expressao) {
        int contador = 0; // Conta os parênteses abertos
    
        for (char caractere : expressao.toCharArray()) {
            if (caractere == '(') {
                contador++; // Abriu um parêntese
            } else if (caractere == ')') {
                contador--; // Fechou um parêntese
                if (contador < 0) {
                    return false; // Fechou mais do que abriu
                }
            }
        }
    
        return contador == 0; // Se for 0, está balanceado, senão está errado
    }

    // Método que converte uma expressão infixa para posfixa
    public String converterParaPosfixa(String expressao) {
        if(!verificarParenteses(expressao)){
            return "Erro: Parenteses não balanceados";
        }

        PilhaInfixaPosfixa pilha = new PilhaInfixaPosfixa(expressao.length());
        String saida = "";

        try{
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
                    pilha.push(caractere);
                }
                // Se o caractere for um ')', desempilha até encontrar '('
                else if (caractere == ')') {
                    while (!pilha.isEmpty() && pilha.topo() != '(') {
                        saida += pilha.pop();
                    }
                    if (!pilha.isEmpty() && pilha.topo() == '(') {
                        pilha.pop(); // Remove o '(' da pilha
                    } 
                }            
                // Se for um operador
                else if (precedencia(caractere) > 0) {
                    // 1. Enquanto a pilha não estiver vazia e houver no seu topo um operador com prioridade maior ou igual ao encontrado
                    while (!pilha.isEmpty() && precedencia(pilha.topo()) >= precedencia(caractere)) {
                        // Desempilhe o operador e copie-o na saída
                        saida += pilha.pop();
                    }
                    // 2. Empilhe o operador encontrado
                    pilha.push(caractere);
                }
                else {
                    // Caractere desconhecido
                    return "Erro: Caractere inválido na expressão: " + caractere;
                }
            }

            // Desempilha o que restou na pilha
        
            while (!pilha.isEmpty()) {
                saida += pilha.pop();
            }
    
        } catch (Exception e) {
            return "Erro na conversão: " + e.getMessage();
        }
        return saida;
    }   
}