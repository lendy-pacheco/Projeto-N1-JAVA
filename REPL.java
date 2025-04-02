import java.util.Scanner;

public class REPL{
    public void iniciarREPL() { // acesso aos comandos
        Fila gravacao = new Fila(10); //fila da gravação
        Scanner scanner = new Scanner(System.in); // Inicializa o Scanner

       while (true){
        System.out.println("diga o q vc quer\n"); //apagar depois
        System.out.printf("< "); //apagar depois
        String grav = scanner.nextLine().toUpperCase();
        


        
        if(grav.equals("VARS")){
            System.out.println("é o vars\n");
    
        }
        else if (grav.equals("REC")){
            grav= "reset"; // reseta a variavel grav
            Rec  rec= new Rec();
            
            rec.loop_de_gravação(gravacao);
                

                
    
        }
        else if(grav.equals("PLAY")){
            Fila aux = new Fila(10);
            if(gravacao.qIsEmpty()){
                System.out.println("Não há gravação para ser reproduzida\n");
            }
            aux = gravacao;

            for (int i=0;i<=aux.size();i++){
              System.out.println(aux.dequeue());  
            }
        }

        else if (grav.equals("ERASE")){
    
        }
        else if (grav.equals("EXIT")){
            break;

        }
        else{
            System.out.println("comando inválido\n");
        }
    }
    scanner.close();
    }

    
    
 

    }

