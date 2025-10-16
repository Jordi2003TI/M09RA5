    /*
    * 
    * 
    * 
    */

    import javax.crypto.*;
    import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.security.*;

    public class AES {

        public static final String ALGORISME_XIFRAT = "AES";

        public static final String ALGORISME_HASH = "SHA-256";

        public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

        private static final int MIDA_IV = 16;

        private static byte[] iv = new byte[MIDA_IV];

        private static final String CLAU = "TienezUnPelasitoDeQuesoNoHeComidoEnDozMinutoz";

        private static IvParameterSpec iSpec;


        public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Ágora illa Òtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

            System.out.println("---------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }

    private static byte[] xifraAES(String msg, String password)throws Exception{
        // Obtenemos los bytes de la String
        byte[] byteMsg = msg.getBytes();
        // Genera IV i part xifrada
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv); 

        // inicializamos el iSpeac
        InicializarParameterSpec();

        // generar hash
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hashbyte = digest.digest(password.getBytes());

        // Ecryptar 
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        SecretKeySpec secretKey = new SecretKeySpec(hashbyte, ALGORISME_XIFRAT);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iSpec);

        // combinar vi mas mensaje cifrado 
        
        byte[] encrypted = cipher.doFinal(byteMsg);
        byte[] combinar = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, combinar, 0, iv.length);
        System.arraycopy(encrypted, 0, combinar, iv.length, encrypted.length);
        return combinar;
    }

    private static String desxifraAES(byte[] bMsgXifrat, String password){
        return "Hola";
    }

    private static IvParameterSpec InicializarParameterSpec() throws Exception{
    return iSpec = new IvParameterSpec(iv);
    }

    }
