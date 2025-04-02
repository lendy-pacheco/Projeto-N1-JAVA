public static void tratar_imput {
    // função
    public static boolean validaAtribuicaoVariavel(String grav) {
        // Verifica se a entrada contém o sinal de igual
        if (grav.contains("=")) {
            // Divide a entrada em duas partes: variável e valor
            String[] partes = grav.split("=");
            if (partes.length == 2) {
                // Remove espaços em branco ao redor das partes
                String variavel = partes[0].trim();//remove os espaços em branco da partes[0]
                String valor = partes[1].trim();

                // Verifica se a variável é uma única letra
                if (variavel.length() == 1 && Character.isLetter(variavel.charAt(0))) {
                    // Tenta converter o valor para um inteiro
                    try {
                        Integer.parseInt(valor); // converte para numero inteiro
                        return true;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
            }
        }
        return false;
}
}
