public class ConversorInfixaPosfixa {
    private int precedencia(char operador) {
        if (operador == '+' || operador == '-') return 1;
        if (operador == '*' || operador == '/') return 2;
        if (operador == '^') return 3;
        return -1;
    }

    public boolean verificarParenteses(String expressao) {
        int contador = 0;
        for (char caractere : expressao.toCharArray()) {
            if (caractere == '(') contador++;
            else if (caractere == ')') {
                contador--;
                if (contador < 0) return false;
            }
        }
        return contador == 0;
    }

    private double obterValorVariavel(String nome, String[] nomes, double[] valores) throws Exception {
        for (int i = 0; i < nomes.length; i++) {
            if (nomes[i] != null && nomes[i].equals(nome)) {
                return valores[i];
            }
        }
        throw new Exception("Variável não definida: " + nome);
    }

    public String converterParaPosfixa(String expressao, double[] valores, String[] nomes) {
        if (!verificarParenteses(expressao)) {
            return "Erro: Parênteses não balanceados";
        }
        
        PilhaInfixaPosfixa pilha = new PilhaInfixaPosfixa(expressao.length());
        String saida = "";
        
        try {
            for (int i = 0; i < expressao.length(); i++) {
                char caractere = expressao.charAt(i);
                
                if (caractere == ' ') continue;
                
                if (Character.isLetter(caractere)) {
                    try {
                        double valor = obterValorVariavel(String.valueOf(caractere), nomes, valores);
                        saida += valor + " ";
                    } catch (Exception e) {
                        return "Erro na conversão: " + e.getMessage();
                    }
                } else if (caractere == '(') {
                    pilha.push(caractere);
                } else if (caractere == ')') {
                    while (!pilha.isEmpty() && pilha.topo() != '(') {
                        saida += pilha.pop() + " ";
                    }
                    if (!pilha.isEmpty() && pilha.topo() == '(') pilha.pop();
                } else if (precedencia(caractere) > 0) {
                    while (!pilha.isEmpty() && precedencia(pilha.topo()) >= precedencia(caractere)) {
                        saida += pilha.pop() + " ";
                    }
                    pilha.push(caractere);
                } else {
                    return "Erro: Caractere inválido na expressão: " + caractere;
                }
            }
            
            while (!pilha.isEmpty()) {
                saida += pilha.pop() + " ";
            }
        } catch (Exception e) {
            return "Erro na conversão: " + e.getMessage();
        }
        
        return saida.trim();
    }
}
