package iticbcn.xifrat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic {
    static final String ABD = "AÀÁBCDEÈÉFGHIÍÌJKLMNOÒÓPQRSTÙÜÚVWXYZÇÑ"; 
    static final char[] ABDARRAY = ABD.toCharArray();
    private static char[] ArraypPermutada;

    static Random random;
    static long clauSecreta = 1234;    

    public static String xifraPoliAlfa(String cadena){
        String cifrada = "";
        for(int i = 0; i < cadena.length(); i++){
            boolean EsLetra= false;
            char c = cadena.charAt(i);
            permutaAlfabet();
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
    
    public static String desxifraPoliAlfa(String cadena){
        String cifrada = "";
        for(int i = 0; i < cadena.length(); i++){
            boolean EsLetra= false;
            char c = cadena.charAt(i);
            permutaAlfabet();
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

    public static void initRandom(long clave){
        random = new Random(clave);
    }

    // es radom es un parametro del shuffle 
    public static void permutaAlfabet(){
        List<Character> lista = new ArrayList<>();
        for(char element : ABDARRAY){
            lista.add(element);
        }

        // para poder mezclar la array
        Collections.shuffle(lista, random);

        ArraypPermutada = new char[ABDARRAY.length];
        for(int i = 0; i < ABDARRAY.length; i++){
            ArraypPermutada[i] = lista.get(i);
        }
    }
}
