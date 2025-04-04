package proj;
import java.util.Scanner;

public class REPL {
    private double[] variaveis = new double[26]; // Para armazenar valores das variáveis A-Z
    private boolean[] variaveisDefinidas = new boolean[26]; // Controle de variáveis definidas
    private static String grav;

    public void iniciarREPL() { // Acesso aos comandos
        Fila gravacao = new Fila(10); // Fila da gravação
        Scanner scanner = new Scanner(System.in); // Inicializa o Scanner
        System.out.println("Digite um comando:\n");

        while (true) {
            
            System.out.printf("< ");
            grav = scanner.nextLine().toUpperCase(); // Usa a variável de instância correta

            if (grav.equals("VARS")) {
                imprimirVariaveis(); // Método para exibir as variáveis definidas
            } 
            else if (grav.equals("REC")) {
                Rec rec = new Rec();
                gravacao = rec.loop_de_gravação(gravacao); // Chamada para começar a gravar
            } 
            else if (grav.equals("PLAY")) {
                if (gravacao.qIsEmpty()) {
                    System.out.println("Não há gravação para ser reproduzida\n");
                } 
                else {
                    Fila copia = copiarFila(gravacao); // Faz uma cópia da fila
                    Fila aux = copiarFila(copia); // Fila auxiliar para o laço

                    Processador processador = new Processador(); // Cria UMA instância para manter estado
                    while (!copia.qIsEmpty()) {
                        String elemento = (String) copia.dequeue();
                        processador.processarElemento(elemento);
                    }
                }
            }
            else if (grav.equals("RESET")) {
                resetarVariaveis(); // Método para resetar as variáveis corretamente
            } 
            else if (grav.equals("ERASE")) {
                gravacao = new Fila(10); // Reinicia a fila
                System.out.println("Gravação apagada.");
            } 
            else if (grav.equals("EXIT")) {
                scanner.close();
                break; // Agora o loop será interrompido corretamente
            } 
            else if (grav.matches("^[A-Z]=\\d+(\\.\\d+)?$")) { // Verifica se é uma atribuição de variável (ex: A=10 ou B=2.5)
                definirVariavel(grav);
            } 
            else if (grav.equals("EXPRESSAO MATEMATICA INFIXA")) {
                System.out.println("Insira a expressão \n<");
                String expressao = scanner.nextLine().toUpperCase();
            
                ConversorInfixaPosfixa CONV = new ConversorInfixaPosfixa();
            
                // Criar vetores compatíveis com o conversor
                String[] nomes = new String[26];
                double[] valores = new double[26];
            
                for (int i = 0; i < 26; i++) {
                    nomes[i] = String.valueOf((char) ('A' + i)); // A, B, C...
                    valores[i] = variaveisDefinidas[i] ? variaveis[i] : Double.NaN; // usa NaN se não estiver definida
                }
            
                // Agora passa para o conversor
                String resultado = CONV.converterParaPosfixa(expressao, valores, nomes);
                System.out.println(resultado);
            }
        }
    }

    public static Fila copiarFila(Fila original) {
        Fila copia = new Fila(original.size()); // Nova fila com a mesma capacidade
        Fila auxiliar = new Fila(original.size()); // Auxiliar para preservar a fila original

        // Copiar elementos mantendo a ordem
        while (!original.qIsEmpty()) {
            String elemento = (String) original.dequeue(); // Converte para String
            copia.enqueue(elemento); // Adiciona na cópia
            auxiliar.enqueue(elemento); // Adiciona na auxiliar
        }

        // Restaurar a fila original
        while (!auxiliar.qIsEmpty()) {
            original.enqueue(auxiliar.dequeue()); // Reinsere os elementos na fila original
        }

        return copia; 
    }

    public static void voltaREC(String comando) { 
        grav = comando; // Atualiza a variável global sem criar um novo objeto
        new REPL().iniciarREPL(); // Inicia a REPL novamente
    }

    private void definirVariavel(String input) { // Define variável A-Z com um valor numérico
        char variavel = input.charAt(0); // Obtém a variável (ex: 'A')
        
        if (variavel < 'A' || variavel > 'Z') { // Verifica se é uma letra válida
            System.out.println("Erro: Nome de variável inválido.");
            return;
        }

        String valorString = input.split("=")[1].trim(); // Obtém o valor após '='
        try {
            int valor = Integer.parseInt(valorString); // Converte para inteiro
            int index = variavel - 'A'; // Obtém índice correspondente (A=0, B=1, ..., Z=25)
            variaveis[index] = valor;
            variaveisDefinidas[index] = true;
            System.out.println(variavel + " = " + valor);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Valor inválido.");
        }
    }

    private void resetarVariaveis() { // Reinicializa as variáveis sem criar NullPointerException
        for (int i = 0; i < 26; i++) {
            variaveis[i] = 0;
            variaveisDefinidas[i] = false;
        }
        System.out.println("Variáveis resetadas com sucesso!\n");
    }

    private void imprimirVariaveis() { // Mostra todas as variáveis definidas
        System.out.println("Variáveis definidas:");
        for (int i = 0; i < 26; i++) {
            if (variaveisDefinidas[i]) {
                System.out.println((char) ('A' + i) + " = " + variaveis[i]);
            }
        }
    }
}
