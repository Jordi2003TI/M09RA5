import java.util.Scanner;

public class Rot13 {

    // Variables globales
    final String abd = "aàábcdeèéfghiíìjklmnoòópqrstùúvwxyz";
    final String ABD = "AÀÁBCDEÈÉFGHIÍÌJKLMNOÒÓPQRSTÙÚVWXYZ";

    char[] abdArray = abd.toCharArray();
    char[] ABDArray = ABD.toCharArray();

    public static void main(String[] args){
    
    System.out.println("Introduce un texto");
    Scanner sc = new Scanner(System.in);
    String cadena = sc.nextLine();

    String cifradoString = xifratRot13(cadena);
    System.out.println("Texto cifrado: " + cifradoString);
    String descifradoString = desxifratRot13(cadena);
    }

    public static String xifratRot13(String cadena){
        String cadenaCifrada = "";
        for(int i = 0; i < cadena.length(); i++){
            char c = cadena.charAt(i);
            for(int j = 0; j < abdArray.length; j++){
                if(c == abdArray[j]){
                    int posicion = (j + 13) % abdArray.length;
                    cadenaCifrada += abdArray[posicion % abdArray.length];
                }
            }
        }
        return cadenaCifrada;
    }

    public static String desxifratRot13(String cadena){
        return "";
    }
}
