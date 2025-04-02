import java.util.Scanner;

public class Rec {
    public Fila loop_de_gravação(Fila gravacao) {
        System.out.println("iniciando gravação de até 10 elementos ... \n");
        Scanner scanner = new Scanner(System.in); // Criando o objeto Scanner não pode fecha-lo
        int count = 1; // Contador para gravação

        if (gravacao.qIsFull()){ //limpa caso queira gravar e novo
            while (!gravacao.qIsEmpty()) gravacao.dequeue();
        }

        while (!gravacao.qIsFull()) { // Empilha as entradas
            System.out.printf("gravando (%d/10)", count);
            System.out.print("< ");
            String grav = scanner.nextLine().toUpperCase(); // Input do usuário para gravar

            if (grav.equals("PLAY") || grav.equals("REC") || grav.equals("ERASE") || grav.equals("EXIT")) {
                System.out.println("Erro: comando inválido para gravação.");
            }
            else if (grav.equals("STOP")) { // Parar gravação
                gravacao.enqueue(grav);
                break;
            }
            else {
                count++;
                gravacao.enqueue(grav); // Coloca os comandos na fila de gravação
            }
        }
        


        //  NÃO FECHAR O SCANNER AQUI! O REPL ainda precisa usá-lo.
        return gravacao; // Retorna a fila gravada
    }
}
