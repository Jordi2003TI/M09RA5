package iticbcn.xifratge;

public class XifradorRotX implements Xifrador{

    // Variables globales
    static final String abd = "aàábcdeèéfghiíìjklmnoòópqrstùúvwxyzçñ";
    static final String ABD = "AÀÁBCDEÈÉFGHIÍÌJKLMNOÒÓPQRSTÙÚVWXYZÇÑ";
    static final int numeroMaxCifrado = abd.length();
    int contador = 0;

    char[] abdArray = abd.toCharArray();
    char[] ABDArray = ABD.toCharArray();
    
    public  String xifratRotX(String cadena, int desplaçament){
        // Pasar A cifrado
        String cadenaCifradaMin = "";
        for(int i = 0; i < cadena.length(); i++){
            char c = cadena.charAt(i);
            boolean encontrado = false;
            for(int j = 0; j < abdArray.length; j++){ // Aprovechamos que son igual de largos
                if(c == abdArray[j]){
                    cadenaCifradaMin += abdArray[(j + desplaçament ) % abdArray.length]; // Sirve para poder reinciar el indice de la array a traves de un calculo
                    encontrado = true;
                    break;
                }else if(c == ABDArray[j]){
                    cadenaCifradaMin += ABDArray[(j + desplaçament ) % ABDArray.length];
                    encontrado = true;
                    break;
                }
            }
            // Cuando hay un espcacio o un caracter difrente a una de las letras
            if(!encontrado){
                cadenaCifradaMin += c;
            }
            
        }
        return cadenaCifradaMin;
    }

    public String desxifratRotX(String cadena, int desplaçament){

        String cadenaCifradaMin = "";
        for(int i = 0; i < cadena.length(); i++){
            char c = cadena.charAt(i);
            boolean encontrado = false;
            for(int j = 0; j < abdArray.length; j++){ 
                if(c == abdArray[j]){
                    cadenaCifradaMin += abdArray[(j - desplaçament + abdArray.length ) % abdArray.length]; // Ponemos que sume la array porque sino nos da un valor negativo y esto haria que nunca lo eocntrarse
                    encontrado = true;
                    break;
                }else if(c == ABDArray[j]){
                    cadenaCifradaMin += ABDArray[(j - desplaçament + ABDArray.length) % ABDArray.length];
                    encontrado = true;
                    break;
                }
            }
            // Cuando hay un espcacio o un caracter difrente a una de las letras
            if(!encontrado){
                cadenaCifradaMin += c;
            }
            
        }
        return cadenaCifradaMin;
    }

    public void forcaBrutaRotX(String cadenaXifrada){
        String cadenaCifradaMin = "";
        for(int i = 0; i < cadenaXifrada.length(); i++){
            char c = cadenaXifrada.charAt(i);
            boolean encontrado = false;
            for(int j = 0; j < abdArray.length; j++){ 
                if(c == abdArray[j]){
                    cadenaCifradaMin += abdArray[(j - contador + abdArray.length ) % abdArray.length]; // Ponemos que sume la array porque sino nos da un valor negativo y esto haria que nunca lo eocntrarse
                    encontrado = true;
                    break;
                }else if(c == ABDArray[j]){
                    cadenaCifradaMin += ABDArray[(j - contador + ABDArray.length) % ABDArray.length];
                    encontrado = true;
                    break;
                }
            }
            // Cuando hay un espcacio o un caracter difrente a una de las letras
            if(!encontrado){
                cadenaCifradaMin += c;
            }
            
        }
        
        System.out.println();
        System.out.printf("(%d)->%s", contador,cadenaCifradaMin);
        if(contador >= numeroMaxCifrado){
            contador = 0;
        }else{
            contador++;
        }
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        throw new UnsupportedOperationException("Unimplemented method 'xifra'");
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        throw new UnsupportedOperationException("Unimplemented method 'desxifra'");
    }
}