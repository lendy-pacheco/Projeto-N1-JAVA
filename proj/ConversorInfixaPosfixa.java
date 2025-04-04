package proj;

public class ConversorInfixaPosfixa {

    // retorna o nivel de precedencia de cada operador
    private int precedencia(char operador) {
        if (operador == '+' || operador == '-') return 1;
        if (operador == '*' || operador == '/') return 2;
        if (operador == '^') return 3;
        return -1;
    }

    // verifica se os parenteses da expressao estao balanceados
    public boolean verificarParenteses(String expressao) {
        int contador = 0;
        for (char caractere : expressao.toCharArray()) {
            if (caractere == '(') contador++;
            else if (caractere == ')') {
                contador--;
                if (contador < 0) return false; // fecha antes de abrir
            }
        }
        return contador == 0;    // true se todos os parenteses forem fechados corretamente
    }

    // busca o valor de uma variavel entre os nomes fornecidos
    /*private double obterValorVariavel(String nome, String[] nomes, double[] valores) throws Exception {
        for (int i = 0; i < nomes.length; i++) {
            if (nomes[i] != null && nomes[i].equalsIgnoreCase(nome)) {
                return valores[i];
            }
            
        }
        throw new Exception("variavel nao definida: " + nome);
    }*/

    private double obterValorVariavel(String nome, String[] nomes, double[] valores) throws Exception {
        nome = nome.toUpperCase(); // Converte para maiúscula antes de buscar
        for (int i = 0; i < nomes.length; i++) {
            if (nomes[i] != null && nomes[i].equals(nome)) { // Verifica sem `equalsIgnoreCase()`
                if (Double.isNaN(valores[i])) { // Se o valor for NaN, considera não definido
                    throw new Exception("Variável '" + nome + "' não foi definida.");
                }
                return valores[i]; 
            }
        }
        throw new Exception("Variável '" + nome + "' não foi definida.");
    }
    

    // metodo principal que converte a expressao infixa para posfixa
    public String converterParaPosfixa(String expressao, double[] valores, String[] nomes) {
        if (!verificarParenteses(expressao)) {
            return "erro: parenteses nao balanceados";
        }

        PilhaUniversal pilhaOperadores = new PilhaUniversal(expressao.length());
        StringBuilder saida = new StringBuilder();

        try {
            for (int i = 0; i < expressao.length(); i++) {
                char caractere = expressao.charAt(i);

                if (caractere == ' ') continue; // ignora espacos

                // se for letra, busca o valor da variavel e adiciona na saida
                if (Character.isLetter(caractere)) {
                    double valor = obterValorVariavel(String.valueOf(caractere), nomes, valores);
                    saida.append(valor).append(" ");
                }

                // se for digito, monta o numero completo e adiciona na saida
                else if (Character.isDigit(caractere)) {
                    StringBuilder numero = new StringBuilder();
                    while (i < expressao.length() && 
                          (Character.isDigit(expressao.charAt(i)) || expressao.charAt(i) == '.')) {
                        numero.append(expressao.charAt(i++));
                    }
                    i--; // volta um caractere
                    saida.append(numero).append(" ");
                }

                // se for abre parenteses, empilha
                else if (caractere == '(') {
                    pilhaOperadores.push(caractere);
                }

                // se for fecha parenteses, desempilha ate abrir
                else if (caractere == ')') {
                    while (!pilhaOperadores.isEmptyChar() && pilhaOperadores.topoChar() != '(') {
                        saida.append(pilhaOperadores.popChar()).append(" ");
                    }
                    if (!pilhaOperadores.isEmptyChar() && pilhaOperadores.topoChar() == '(')
                        pilhaOperadores.popChar(); // remove o '('
                }

                // se for operador, desempilha operadores de maior ou igual precedencia
                else if (precedencia(caractere) > 0) {
                    while (!pilhaOperadores.isEmptyChar() && 
                           precedencia(pilhaOperadores.topoChar()) >= precedencia(caractere)) {
                        saida.append(pilhaOperadores.popChar()).append(" ");
                    }
                    pilhaOperadores.push(caractere);
                }

                // caractere nao reconhecido
                else {
                    return "erro: caractere invalido na expressao: " + caractere;
                }
            }

            // esvazia o restante da pilha de operadores
            while (!pilhaOperadores.isEmptyChar()) {
                saida.append(pilhaOperadores.popChar()).append(" ");
            }

            // divide a expressao posfixa em tokens separados por espaco
            String[] tokens = saida.toString().trim().split(" ");
            PilhaUniversal pilhaNumeros = new PilhaUniversal(tokens.length);
            // cria uma pilha para armazenar os numeros durante a avaliacao

            // percorre cada token da expressao posfixa
            for (String token : tokens) {
                // se o token for um numero (inteiro ou decimal), empilha
                if (token.matches("-?\\d+(\\.\\d+)?")) {
                    pilhaNumeros.push(Double.parseDouble(token)); // empilha numeros
                
                // se o token for um operador (+, -, *, /, ^)
                } else if (token.matches("[+\\-*/^]")) {
                    // desempilha os dois ultimos numeros
                    double b = pilhaNumeros.popDouble();
                    double a = pilhaNumeros.popDouble();
                    double res = 0;

                    // aplica a operacao de acordo com o operador
                    switch (token) {
                        case "+": res = a + b; break;
                        case "-": res = a - b; break;
                        case "*": res = a * b; break;
                        case "/": 
                        if (b == 0) {
                            throw new ArithmeticException("Erro: divisão por zero.");
                         }
                          res = a / b;
                          break;
                        case "^": res = Math.pow(a, b); break;
                    }

                    pilhaNumeros.push(res); // empilha o resultado
                }
            }

            double resultadoFinal = pilhaNumeros.popDouble();
            return saida.toString().trim() + "\nresultado: " + resultadoFinal;

        } catch (Exception e) {
            return "erro na conversao: " + e.getMessage();
        }
    }
}
