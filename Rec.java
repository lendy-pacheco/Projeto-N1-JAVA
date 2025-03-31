import java.util.Scanner;
public class Rec {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Criando o objeto Scanner
        Fila comando = new Fila(10);
        

        while(!comando.qIsFull()){
            String grav= scanner.nextLine().toUpperCase();

            if(grav == "STOP"){
                break;
            }
            else{
                comando.enqueue(grav); //coloca os comandos na fila
                transformar.vetor(grav);
            }

        }
        scanner.close();
    }
    
}
