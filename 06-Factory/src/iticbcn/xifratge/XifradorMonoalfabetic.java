package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {
    static final String ABD = "AÀÁBCDEÈÉFGHIÍÌJKLMNOÒÓPQRSTÙÚVWXYZÇÑ"; 
    static final char[] ABDARRAY = ABD.toCharArray();
    private char[] ArraypPermutada;

    public XifradorMonoalfabetic(){
        this.ArraypPermutada = permutaAlfabet(ABDARRAY);
    }


     // Para permutar un alfabeto
    public char[] permutaAlfabet(char[] alfabeto){
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
    public String xifraMonoAlfa(String cadena){
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
    public String desxifraMonoAlfa(String cadena){
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
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("no suporta clau != null");
        }
        try {
            String resultado = xifraMonoAlfa(msg);
            return new TextXifrat(resultado.getBytes("UTF-8")); // <-- UTF-8 explícito
        } catch (Exception e) {
            throw new RuntimeException(e); // nunca debería pasar
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("no suporta clau != null");
        }
        try {
            return desxifraMonoAlfa(new String(xifrat.getBytes(), "UTF-8")); // <-- UTF-8 explícito
        } catch (Exception e) {
            throw new RuntimeException(e); // nunca debería pasar
        }
    }
}
