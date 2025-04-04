package proj;
import java.util.Scanner;

public class Rec {
    public Fila loop_de_gravação(Fila gravacao) {
        System.out.println("iniciando gravação de até 10 elementos ... \n");
        Scanner scanner = new Scanner(System.in); // Criando o objeto Scanner não pode fecha-lo
        int count = 1; // Contador para gravação

        if (!gravacao.qIsEmpty()){ //limpa caso queira gravar e novo
             System.out.println("Esvazie a Gravação antes de gravar outra!!\n");
        }

        while (!gravacao.qIsFull()) { // Empilha as entradas
            System.out.printf("gravando (%d/10)", count);
            System.out.print("< ");
            String grav = scanner.nextLine().toUpperCase(); // Input do usuário para gravar
            System.out.println(grav);

            if (grav.equals("PLAY") || grav.equals("REC") || grav.equals("ERASE") || grav.equals("EXIT")||grav.equals("EXPRESSAO MATEMATICA INFIXA")) {
                System.out.println("Erro: comando inválido para gravação.");
            }
            else if (grav.equals("STOP")) { // Parar gravação
                break;
            }
            else if (grav.equals("VARS")|| grav.equals("RESET") || grav.equals("EXIT")){
                gravacao.enqueue(grav);
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
