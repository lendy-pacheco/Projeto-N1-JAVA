import java.util.Scanner;

public class REPL {
    private static String grav;

    public void iniciarREPL() { // Acesso aos comandos
        Fila gravacao = new Fila(10); // Fila da gravação
        Scanner scanner = new Scanner(System.in); // Inicializa o Scanner

        while (true) {
            System.out.println("Digite um comando:\n");
            System.out.printf("< ");
            grav = scanner.nextLine().toUpperCase(); // Usa a variável de instância correta

            if (grav.equals("VARS")) {
                System.out.println("é o vars\n"); //apagar depois
            } 
            else if (grav.equals("REC")) {
                Rec rec = new Rec();
                gravacao = rec.loop_de_gravação(gravacao); // chamada para começar a gravar
            } 
            else if (grav.equals("PLAY")) {
                if (gravacao.qIsEmpty()) {
                    System.out.println("Não há gravação para ser reproduzida\n");
                } 
                else {
                    Fila copia = copiarFila(gravacao); // Faz uma cópia da fila
                    Fila aux = copiarFila(copia); // Fila auxiliar para o laço

                    for (int i = 0; i < aux.size(); i++) { // Evita acessar índice fora do limite
                        Processador processador = new Processador();
                        String elemento = (String) copia.dequeue(); // Converte para string
                        processador.processarElemento(elemento); // Processa a string
                    }
                }
            } 
            else if (grav.equals("ERASE")) {
                gravacao = new Fila(10); // Reinicia a fila
                System.out.println("Gravação apagada.");
            } 
            else if (grav.equals("RESET")) {
                System.out.println("Resetando sistema...");
                // Aqui você pode definir ações para resetar o estado
            } 
            else if (grav.equals("EXIT")) {
                scanner.close();
                break; // Agora o loop será interrompido corretamente
            } 
            else {
                System.out.println("Comando inválido\n");
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

        
        while (!auxiliar.qIsEmpty()) {
            original.enqueue(auxiliar.dequeue()); // Reinsere os elementos na fila original
        }

        return copia; 
    }

    public static void voltaREC(String comando) { // vem de rec para 
        grav = comando; // atualiza a variável global sem criar um novo objeto
        new REPL().iniciarREPL(); // Inicia a REPL novamente
    }
}
