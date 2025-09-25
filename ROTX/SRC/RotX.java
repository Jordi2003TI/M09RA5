/*
 * M9 Jaume 
 * DIA: 18/09/2025
 * Jordi Masip Ibañez
 * Es un ejercicio que esta pensado para poder cifrar cadenas de String conservando los espacios y caracteres que no sean letras. 
 * Tenemos que poder descifrar tambien el mismo mensaje que hemos cifrado
 */

public class RotX {

    // Variables globales
    static final String abd = "aàábcdeèéfghiíìjklmnoòópqrstùúvwxyzçñ";
    static final String ABD = "AÀÁBCDEÈÉFGHIÍÌJKLMNOÒÓPQRSTÙÚVWXYZÇÑ";
    static final int numeroMaxCifrado = 39;

    static char[] abdArray = abd.toCharArray();
    static char[] ABDArray = ABD.toCharArray();

    public static void main(String[] args){
    String[] listaPrueba = {"hola", "Jaume?", "Apruebame", "Jordi Masip Ibañez", "Tengo 22 años"}; // para poder hacer las pruebas del profe
    String[] listaPruebaDescifrar = {"hola", "Láuoé?", "Ctúugècóg", "Òswhm Qexmú Mfedib", "Çìtmù 22 éèùz"};
    String[] listaXifrat = {"Òswhm Qexmú Mfedib"};
    System.out.println("Xifrat\n------");
    for(int i = 0; i < listaPrueba.length; i++){
        for(int y = i; y < listaPrueba.length; y++){
            System.out.printf("(%d)%-30s \t => %-30s\n", i*2, listaPrueba[y], xifratRotX(listaPrueba[y], (i*2)));
            break;
        }

    }
    System.out.println();
    System.out.println("Desxifrat\n------");
    for(int i = 0; i < listaPruebaDescifrar.length; i++){
        for(int y = i; y < listaPruebaDescifrar.length; y++){
            System.out.printf("(%d)%-30s \t => %-30s\n", i*2, listaPruebaDescifrar[y], desxifratRotX(listaPruebaDescifrar[y], (i*2)));
            break;
        }

    }
    System.out.println();
    for(int i = 0; i <= numeroMaxCifrado; i++){
        for(int y = 0; y < listaXifrat.length; y++){
            
        }
    }
    }

    public static String xifratRotX(String cadena, int desplaçament){
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

    public static String desxifratRotX(String cadena, int desplaçament){

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

    public static void forcaBrutaRotX(String cadenaXifrada){
        
    }
}
