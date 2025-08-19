import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

public class App {

    private static final Set<String> PALAVRAS_RESERVADAS = 
    new HashSet<>(Arrays.asList("int", "if", "float", "while", "return"));
    
    private static final Set<Character> DELIMITADORES = 
    new HashSet<>(Arrays.asList('{', '}', '(', ')', '+'));

    private static boolean ehpalavraReservada(String token){
        return PALAVRAS_RESERVADAS.contains(token);
    }

    private static boolean ehIdentificador(String token){
        if (token == null || token.isEmpty()) return false;

        char c0 = token.charAt(0);
        if (!(Character.isLetter(c0) || c0=='_')) return false;
        
        for(int i = 1; i<token.length(); i++){
            char c = token.charAt(i);
            if(!(Character.isLetterOrDigit(c) || c=='_')) return false;
        }

        return true;
        
    }

    private static boolean ehNumero(String token){
        if (token == null || token.isEmpty()) return false;
        for(int i = 0; i < token.length(); i++){
            if(!Character.isDigit(token.charAt(i))) return false;
        }

        return true;
    }

    private static void analiseLexica(String codigo){
        int i = 0;
        int n = codigo.length();

        while (i<n) {
            char c = codigo.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            if(Character.isLetter(c) || c == '_' || Character.isDigit(c)){
                int j=i;

                while (j<n) {
                    char cj = codigo.charAt(j);
                    if (Character.isLetterOrDigit(cj) || cj == '_') j++;
                    else break;
                }

                String token = codigo.substring(i, j);

                if(ehpalavraReservada(token)){
                    System.out.printf("Token: %s -> Palavra Reservada \n", token);
                }else if (ehIdentificador(token)) {
                    System.out.printf("Token: %s -> Identificador\n", token);
                }else if (ehNumero(token)) {
                    System.out.printf("Token: %s -> Numero\n", token);
                }else{
                    System.out.printf("Token: %s -> Token desconhecido\n", token);
                }
                
                i=j;
                continue;
            }
            
            if(DELIMITADORES.contains(c)){
                System.out.printf("Token: %c -> Delimitador ou Operador \n", c);
                i++;
                continue;
            }

        }
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("Desenvolvendo um analisador lexicio");

        String codigo = "int while return x = 10; if (x>5{x=x+1})";
        analiseLexica(codigo);
    }
}
