import java.util.Scanner;
public class Rec {
    public  Fila loop_de_gravação(Fila gravacao) {
        System.out.println("iniciando gravação de até 10 elementos ... \n");
        Scanner scanner = new Scanner(System.in); // Criando o objeto Scanner
        int count=1; //contador para gravação

        while(!gravacao.qIsFull()){ // empilha as entradadas
            System.out.printf("gravando (%d/10)", count);
            System.out.print("< ");
            String grav= scanner.nextLine().toUpperCase(); // imput do usuario para gravar

            if (grav.equals("PLAY") || grav.equals("REC") || grav.equals("ERASE") || grav.equals("EXIT")) {
                System.out.println("Erro: comando inválido para gravação.");
            }

            else if(grav.equals("STOP")){ //parar gravação
                break;
            }
            else{
                count++;
                gravacao.enqueue(grav); //coloca os comandos na fila gravação
               // transformar.vetor(grav);
            }

        }
        scanner.close();
        REPL repl = new REPL(); // cria obj repl
        repl.iniciarREPL();// volta ao menu REPL

        return(gravacao);
        
    }
    
}
