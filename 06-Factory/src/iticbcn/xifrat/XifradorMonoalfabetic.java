package iticbcn.xifrat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic {
    static final String ABD = "AÀÁBCDEÈÉFGHIÍÌJKLMNOÒÓPQRSTÙÚVWXYZÇÑ"; 
    static final char[] ABDARRAY = ABD.toCharArray();
    private static char[] ArraypPermutada;
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
