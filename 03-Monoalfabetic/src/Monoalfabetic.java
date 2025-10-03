/* 
 * Es un ejercicio que consiste en permutar un alfabeto para luego poder cifrar un mensaje, se debe de conservar todo aquello que no sea un letra 
 * Jordi Masip Ibañez
 *  03/10/2025
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic{
    static final String ABD = "AÀÁBCDEÈÉFGHIÍÌJKLMNOÒÓPQRSTÙÚVWXYZÇÑ"; 
    static final char[] ABDARRAY = ABD.toCharArray();
    private static char[] ArraypPermutada;
    public static void main(String[] args) {

        ArraypPermutada = permutaAlfabet(ABDARRAY);

        String[] pruebas = {"Hola me llamo Jordi y tengo 23 años", "123  toca la pared", "nomo de yardin"};
        
        System.out.println("Alfabeto normal ");
        for(int i = 0; i < ABDARRAY.length; i++){
            Character c = ABDARRAY[i];
            if(i <= ABDARRAY.length-2){
                System.out.print(c + ", ");
            }else{
                System.out.print(c);
                System.out.println();
            }
            
        }
        System.out.println("Alfabeto permutado");
        for(int i = 0; i < ArraypPermutada.length; i++){
            Character c = ArraypPermutada[i];
            if(i <= ArraypPermutada.length-2){
                System.out.print(c + ", ");
            }else{
                System.out.print(c);
                System.out.println();
            }
            
        }
        // For para puebas 
        for(int i = 0; i < pruebas.length; i++){
            System.out.println();
            String prueba = pruebas[i];
            String xifrat = xifraMonoAlfa(prueba);
            System.out.println("MENSAJE CIFRADO: " + xifrat);
            String desXifrat = desxifraMonoAlfa(xifrat);
            System.out.println("MENSAJE DESCIFRADO: " + desXifrat);
            
        }

    }
    // Para permutar un alfabeto
    public static char[] permutaAlfabet(char[] alfabeto){
        List<Character> lista = new ArrayList<>();
        for(char element : alfabeto){
            lista.add(element);
        }

        // para poder mezclar la array
        Collections.shuffle(lista);

        char[] charPemutado = new char[alfabeto.length];
        for(int i = 0; i < alfabeto.length; i++){
            charPemutado[i] = lista.get(i);
        }
        return charPemutado;
    }
    // 
    public static String xifraMonoAlfa(String cadena){
        String cifrada = "";
        for(int i = 0; i < cadena.length(); i++){
            boolean EsLetra= false;
            char c = cadena.charAt(i);
            for(int y = 0; y < ABDARRAY.length; y++){
                if(Character.toUpperCase(c) == ABDARRAY[y]){
                    if(Character.isLowerCase(c)){
                        cifrada += Character.toLowerCase(ArraypPermutada[y]);
                        EsLetra = true;
                    }else{
                        cifrada += Character.toUpperCase(ArraypPermutada[y]);
                        EsLetra = true;
                    }          
                }
            }
            if(!EsLetra){
                cifrada += c;
                continue;
            }
        }
        return cifrada;
    }
    // ABDARRAY // ArraypPermutada
    public static String desxifraMonoAlfa(String cadena){
        String cifrada = "";
        for(int i = 0; i < cadena.length(); i++){
            boolean EsLetra= false;
            char c = cadena.charAt(i);
            for(int y = 0; y < ArraypPermutada.length; y++){
                if(Character.toUpperCase(c) == ArraypPermutada[y]){
                    if(Character.isLowerCase(c)){
                        cifrada += Character.toLowerCase(ABDARRAY[y]);
                        EsLetra = true;
                    }else{
                        cifrada += Character.toUpperCase(ABDARRAY[y]);
                        EsLetra = true;
                    }          
                }
            }
            if(!EsLetra){
                cifrada += c;
                continue;
            }
        }
        return cifrada;
    }
}