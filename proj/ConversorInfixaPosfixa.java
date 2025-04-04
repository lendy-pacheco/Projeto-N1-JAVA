package proj;
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
            if (nomes[i] != null && nomes[i].equalsIgnoreCase(nome)) {
                return valores[i];
            }
        }
        throw new Exception("Variável não definida: " + nome);
    }

    public String converterParaPosfixa(String expressao, double[] valores, String[] nomes) {
        if (!verificarParenteses(expressao)) {
            return "Erro: Parênteses não balanceados";
        }

        PilhaUniversal pilhaOperadores = new PilhaUniversal(expressao.length());
        StringBuilder saida = new StringBuilder();

        try {
            for (int i = 0; i < expressao.length(); i++) {
                char caractere = expressao.charAt(i);

                if (caractere == ' ') continue;

                if (Character.isLetter(caractere)) {
                    double valor = obterValorVariavel(String.valueOf(caractere), nomes, valores);
                    saida.append(valor).append(" ");
                } else if (Character.isDigit(caractere)) {
                    StringBuilder numero = new StringBuilder();
                    while (i < expressao.length() && 
                          (Character.isDigit(expressao.charAt(i)) || expressao.charAt(i) == '.')) {
                        numero.append(expressao.charAt(i++));
                    }
                    i--;
                    saida.append(numero).append(" ");
                } else if (caractere == '(') {
                    pilhaOperadores.push(caractere);
                } else if (caractere == ')') {
                    while (!pilhaOperadores.isEmptyChar() && pilhaOperadores.topoChar() != '(') {
                        saida.append(pilhaOperadores.popChar()).append(" ");
                    }
                    if (!pilhaOperadores.isEmptyChar() && pilhaOperadores.topoChar() == '(') pilhaOperadores.popChar();
                } else if (precedencia(caractere) > 0) {
                    while (!pilhaOperadores.isEmptyChar() && precedencia(pilhaOperadores.topoChar()) >= precedencia(caractere)) {
                        saida.append(pilhaOperadores.popChar()).append(" ");
                    }
                    pilhaOperadores.push(caractere);
                } else {
                    return "Erro: Caractere inválido na expressão: " + caractere;
                }
            }

            while (!pilhaOperadores.isEmptyChar()) {
                saida.append(pilhaOperadores.popChar()).append(" ");
            }

            // Avaliação da pós-fixa
            String[] tokens = saida.toString().trim().split(" ");
            PilhaUniversal pilhaNumeros = new PilhaUniversal(tokens.length);

            for (String token : tokens) {
                if (token.matches("-?\\d+(\\.\\d+)?")) {
                    pilhaNumeros.push(Double.parseDouble(token));
                } else if (token.matches("[+\\-*/^]")) {
                    double b = pilhaNumeros.popDouble();
                    double a = pilhaNumeros.popDouble();
                    double res = 0;

                    switch (token) {
                        case "+": res = a + b; break;
                        case "-": res = a - b; break;
                        case "*": res = a * b; break;
                        case "/": res = b != 0 ? a / b : Double.NaN; break;
                        case "^": res = Math.pow(a, b); break;
                    }
                    pilhaNumeros.push(res);
                }
            }

            double resultadoFinal = pilhaNumeros.popDouble();
            return saida.toString().trim() + "\nResultado: " + resultadoFinal;

        } catch (Exception e) {
            return "Erro na conversão: " + e.getMessage();
        }
    }
}
