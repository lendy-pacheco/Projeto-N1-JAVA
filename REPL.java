import java.util.Scanner;

public class REPL {
    public void iniciarREPL() { // Acesso aos comandos
        Fila gravacao = new Fila(10); // Fila da gravação
        Scanner scanner = new Scanner(System.in); // Inicializa o Scanner

        while (true) {
            System.out.println("diga o q vc quer\n"); // apagar depois
            System.out.printf("< "); 
            String grav = scanner.nextLine().toUpperCase();

            if (grav.equals("VARS")) {
                System.out.println("é o vars\n"); //apagar depois
            } 
            else if (grav.equals("REC")) {
                Rec rec = new Rec();
                gravacao = rec.loop_de_gravação(gravacao);         // chamada para começar a gravar
            } 
            else if (grav.equals("PLAY")) {
                if (gravacao.qIsEmpty()) {
                    System.out.println("Não há gravação para ser reproduzida\n");
                } 
                else {
                    Fila copia = copiarFila(gravacao);             // Faz uma cópia da fila

                    for (int i = -8; i <= copia.size(); i++) {
                        Processador processa = new Processador();
                        String j=copia.dequeue()
                        processa.processarElemento(copia.dequeue());   //processa para analisar a string
                    }
                }
            } 
            else if (grav.equals("ERASE")) {
                gravacao = new Fila(10);              // Reinicia a fila
                System.out.println("Gravação apagada.");
            } 
            else if (grav.equals("EXIT")) {
                break;
            } 
            else {
                System.out.println("comando inválido\n");
            }
        }
        scanner.close();
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
    
        return copia; // Retorna a cópia
    }
}
