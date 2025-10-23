package iticbcn.xifratge;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;

public class XifradorAES implements Xifrador{
    
    public static final String ALGORISME_XIFRAT = "AES";

    public static final String ALGORISME_HASH = "SHA-256";

    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;

    private byte[] iv = new byte[MIDA_IV];

    private static final String CLAU = "TienezUnPelasitoDeQuesoNoHeComidoEnDozMinutoz";

    private IvParameterSpec iSpec;

    private byte[] xifraAES(String msg, String password)throws Exception{
        // Obtenemos los bytes de la String
        byte[] byteMsg = msg.getBytes();
        // Genera IV i part xifrada
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv); 

        // inicializamos el iSpeac
        InicializarParameterSpec();

        // generar hash
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hashbyte = digest.digest(password.getBytes("UTF-8"));

        // Ecryptar 
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        SecretKeySpec secretKey = new SecretKeySpec(hashbyte, ALGORISME_XIFRAT);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iSpec);

        // combinar vi mas mensaje cifrado 
        
        byte[] encrypted = cipher.doFinal(byteMsg);
        byte[] combinar = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, combinar, 0, iv.length);
        System.arraycopy(encrypted, 0, combinar, iv.length, encrypted.length);
        // return de iv+msgXifrat
        return combinar;
    }

    private String desxifraAES(byte[] bMsgXifrat, String password)throws Exception{
        if(bMsgXifrat == null || bMsgXifrat.length <= MIDA_IV){
            throw new IllegalArgumentException("Entrada inválida: no contiene IV + datos cifrados");
        }

        // Extraemos el IV 
        byte[] ivLocal = Arrays.copyOfRange(bMsgXifrat, 0, MIDA_IV);

        // Extraemos el resto del texto 
        byte[] encrypted = Arrays.copyOfRange(bMsgXifrat, MIDA_IV, bMsgXifrat.length);


        IvParameterSpec ivSpecLocal = new IvParameterSpec(ivLocal);

        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hashPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        SecretKeySpec secretKey = new SecretKeySpec(hashPassword, ALGORISME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpecLocal);

        byte[] descrytado = cipher.doFinal((encrypted));

        return new String(descrytado, StandardCharsets.UTF_8);
    }

    private IvParameterSpec InicializarParameterSpec() throws Exception{
    return iSpec = new IvParameterSpec(iv);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
        if (clau == null || clau.isEmpty()) {
            throw new ClauNoSuportada("La clau no pot estar buida");
        }

            byte[] resultat = xifraAES(msg, clau);
            return new TextXifrat(resultat);

        } catch (ClauNoSuportada e) {
            throw e;
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en el xifrat: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
        if (clau == null || clau.isEmpty()) {
            throw new ClauNoSuportada("La clau no pot estar buida");
        }

            return desxifraAES(xifrat.getBytes(), clau);

        } catch (ClauNoSuportada e) {
            throw e;
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en el desxifrat: " + e.getMessage());
        }
    }
}
