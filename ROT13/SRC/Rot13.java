import java.util.Scanner;

public class Rot13 {

    // Variables globales
    static final String abd = "aàábcdeèéfghiíìjklmnoòópqrstùúvwxyz";
    static final String ABD = "AÀÁBCDEÈÉFGHIÍÌJKLMNOÒÓPQRSTÙÚVWXYZ";

    static char[] abdArray = abd.toCharArray();
    static char[] ABDArray = ABD.toCharArray();

    public static void main(String[] args){
    //String[] listaPrueba = ["hola", "Jaume", "Apruebame", "Jordi Masip Ibañez"]; // para poder hacer las pruebas del profe

    // Cifrar String
    String cifradoString = xifratRot13("HolA que hace");
    System.out.println("Texto cifrado: " + cifradoString);

    String descifradoString = desxifratRot13(cifradoString);
    }

    public static String xifratRot13(String cadena){
        // Pasar A cifrado
        String cadenaCifradaMin = "";
        for(int i = 0; i < cadena.length(); i++){
            char c = cadena.charAt(i);
            for(int j = 0; j < abdArray.length; j++){ // Aprovechamos que son igual de largos
                if(c == abdArray[j]){
                    cadenaCifradaMin += abdArray[(j + 13 ) % abdArray.length];
                }else if(c == ABDArray[j]){
                    cadenaCifradaMin += ABDArray[(j + 13 ) % ABDArray.length];
                }else{
                    cadenaCifradaMin += c;
                }
            }
        }
        return cadenaCifradaMin;
    }

    public static String desxifratRot13(String cadena){

        return "";
    }
}
