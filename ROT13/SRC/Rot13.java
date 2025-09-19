/*
 * M9 Jaume 
 * DIA: 18/09/2025
 * Jordi Masip Ibañez
 * Es un ejercicio que esta pensado para poder cifrar cadenas de String conservando los espacios y caracteres que no sean letras. 
 * Tenemos que poder descifrar tambien el mismo mensaje que hemos cifrado
 */

public class Rot13 {

    // Variables globales
    static final String abd = "aàábcdeèéfghiíìjklmnoòópqrstùúvwxyz";
    static final String ABD = "AÀÁBCDEÈÉFGHIÍÌJKLMNOÒÓPQRSTÙÚVWXYZ";

    static char[] abdArray = abd.toCharArray();
    static char[] ABDArray = ABD.toCharArray();

    public static void main(String[] args){
    String[] listaPrueba = {"hola", "Jaume", "Apruebame", "Jordi Masip Ibañez", "Tengo 22 años"}; // para poder hacer las pruebas del profe


    for(String element : listaPrueba){
        String cifrado = xifratRot13(element);
        System.out.println("El texto cidrado es " + cifrado);
        String desCifrado = desxifratRot13(cifrado);
        System.out.println("El texto descifrado " + desCifrado);
        System.out.println();
    }
    /* 
    // Cifrar String
    String cifradoString = xifratRot13("HolA que hace ??? ¡¡¡¡ ...");
    System.out.println("Texto cifrado: " + cifradoString);

    String descifradoString = desxifratRot13(cifradoString);
    System.out.println("Texto descifrado " + descifradoString);
    */
    }

    public static String xifratRot13(String cadena){
        // Pasar A cifrado
        String cadenaCifradaMin = "";
        for(int i = 0; i < cadena.length(); i++){
            char c = cadena.charAt(i);
            boolean encontrado = false;
            for(int j = 0; j < abdArray.length; j++){ // Aprovechamos que son igual de largos
                if(c == abdArray[j]){
                    cadenaCifradaMin += abdArray[(j + 13 ) % abdArray.length]; // Sirve para poder reinciar el indice de la array a traves de un calculo
                    encontrado = true;
                    break;
                }else if(c == ABDArray[j]){
                    cadenaCifradaMin += ABDArray[(j + 13 ) % ABDArray.length];
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

    public static String desxifratRot13(String cadena){

        String cadenaCifradaMin = "";
        for(int i = 0; i < cadena.length(); i++){
            char c = cadena.charAt(i);
            boolean encontrado = false;
            for(int j = 0; j < abdArray.length; j++){ 
                if(c == abdArray[j]){
                    cadenaCifradaMin += abdArray[(j - 13 + abdArray.length ) % abdArray.length]; // Ponemos que sume la array porque sino nos da un valor negativo y esto haria que nunca lo eocntrarse
                    encontrado = true;
                    break;
                }else if(c == ABDArray[j]){
                    cadenaCifradaMin += ABDArray[(j - 13 + ABDArray.length) % ABDArray.length];
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
}
