import java.util.Scanner;

public class main{
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      System.out.print("< ");
      String comando = scanner.nextString();

      if(comando == "VARS"){
        comando com_vars = Vars(comando);
      }
      else if(comando == "RESET"){
        comando com_reset = Reset(comando);
      }
      else if(comando == "REC"){
        comando com_rec = Rec(comando);
      }
      else if(comando == "STOP"){
        string com_stop = Stop(comando);
      }
      else if(comando == "PLAY"){
        string com_play = Play(comando);
      }
      else if(comando == "ERASE"){
        string com_erase = Erase(comando);
      }
      else if(comando == "EXIT"){
        comando com_exit = Exit(comando);
      }
      else{
        //Criar exception para comando não existente
      }
      

      scanner.close();
    }

    static String Vars(String comando){
      
    }

    static String Reset(String comando){

    }

    static String Rec(String comando){

    }

    static String Stop(String comando){
      
    }

    static String Play(String comando){
      
    }

    static String Erase(String comando){
      
    }

    static String Exit(String comando){
      
    }
}
}
