package proj;
public class TratamentoDeString {

    // Método estático para verificar se um caractere é uma letra maiúscula
    public static boolean ehLetraMaiuscula(char caractere) {
      // Retornará True se caractere estiver contido no intervalo de letras maiúsculas, caso contrário retorna False
      return caractere >= 'A' && caractere <= 'Z';
    }
  
    // Método estático para converter uma string em um inteiro
    public static int stringParaInteiro(String str) throws NumberFormatException {
      int resultado = 0;
      for (int i = 0; i < str.length(); i++) {
          // Obtém o caractere atual
          char c = str.charAt(i);
  
          // Verifica se o caractere está entre '0' e '9'
          if (c < '0' || c > '9') {
              throw new NumberFormatException("Caractere inválido encontrado: " + c);
          }
          
          // Multiplica o resultado atual por 10 para deslocar os dígitos para a esquerda
          // Adiciona o valor numérico do caractere atual ao resultado
          resultado = resultado * 10 + (c - '0');
      }
      return resultado;
    }
  
  }