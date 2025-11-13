import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.*;

public class Hashes {
    public long npass = 0; 
    public static void main(String[] args) throws Exception {
    String salt = "qpoweiruañslkdfjz";
    String pw = "aaabF!";
    Hashes h = new Hashes();
    String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                         h.getPBKDF2AmbSalt(pw, salt) };
    String pwTrobat = null;
    String[] algoritmes = {"SHA-512", "PBKDF2"};
    for (int i = 0; i < aHashes.length; i++) {
        System.out.printf("=====================================\n");
        System.out.printf("Algorisme: %s\n", algoritmes[i]);
        System.out.printf("Hash: %s\n", aHashes[i]);
        System.out.printf("-------------------------------------\n");
        System.out.printf("-- Inici de força bruta ---\n");

        long t1 = System.currentTimeMillis();
        pwTrobat = h.forcaBruta(algoritmes[i], aHashes[i], salt);
        long t2 = System.currentTimeMillis();

        System.out.printf("Pass : %s\n", pwTrobat);
        System.out.printf("Provats: %d\n", h.npass);
        System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
        System.out.printf("-------------------------------------\n\n");
    }
}


    public String getSHA512AmbSalt(String pw, String salt) throws NoSuchAlgorithmException{
        // Implementamos el algoritmo de SHA-512 
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        // Concatenamos las dos strings importante en el orden que ponemos y lo convertimos en byte[] usando UTF_8
        String combinarPwSalt = salt + pw;
        byte[] input = combinarPwSalt.getBytes(StandardCharsets.UTF_8);
        // Calculamos el Hash SHA-512 
        byte[] digest = md.digest(input);
        // HexFormat Formatea los bytes en String hexadecimal 
        HexFormat hex = HexFormat.of();
        // Convierte el byte[] en hash a una cadena hex
        return hex.formatHex(digest);
    }

    public String getPBKDF2AmbSalt(String pw, String salt){
        try{
            int iteraciones = 10;
            int KeyLengh = 512;
            // Convetimos la contaseña en char 
            char[] pwdChars = pw.toCharArray();
            // Convertimos la sal a byte con UTF_8: PBDKF2 es un salto en bytes.
            byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);
            // Crea las especificaciones para el PBDKD2
            KeySpec spec = new PBEKeySpec(pwdChars, saltBytes, iteraciones, KeyLengh);
            // Obtenemos la clave derivada usadno PBKDF2 con HMAC-SHA512
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            // Generamos la clave segun el spec
            byte[] key = skf.generateSecret(spec).getEncoded();
            // Instancia para comvertir los bytes a hex
            HexFormat hex = HexFormat.of();

            return hex.formatHex(key);
        }catch (Exception e) {
            throw new RuntimeException("Error PBKDF2: " + e.getMessage(), e);
        }
    }
    public String forcaBruta(String alg, String hash, String salt) throws NoSuchAlgorithmException{
        final char[] charset = "abcdefABCDEF1234567890!".toCharArray();
        this.npass = 0;

        for(int i = 0; i < charset.length; i++){
            String s = "" + charset[i];
            this.npass++;
            if (matchHash(alg, s, hash, salt)) return s;
        }

        // Lonfitud de 2
        for (int i0 = 0; i0 < charset.length; i0++) {
            String s0 = "" + charset[i0];
            for (int i1 = 0; i1 < charset.length; i1++) {
                String s1 = s0 + charset[i1];
                this.npass++;
                if (matchHash(alg, s1, hash, salt)) return s1;
            }
        }

        // Longitud de 3
        for (int i0 = 0; i0 < charset.length; i0++) {
             String s0 = "" + charset[i0];
            for (int i1 = 0; i1 < charset.length; i1++) {
                String s1 = s0 + charset[i1];
                for (int i2 = 0; i2 < charset.length; i2++) {
                    String s2 = s1 + charset[i2];
                    this.npass++;
                    if (matchHash(alg, s2, hash, salt)) return s2;
                }
            }
        }

        // Longitud de 4

        for (int i0 = 0; i0 < charset.length; i0++) {
            String s0 = "" + charset[i0];
            for (int i1 = 0; i1 < charset.length; i1++) {
                String s1 = s0 + charset[i1];
                for (int i2 = 0; i2 < charset.length; i2++) {
                    String s2 = s1 + charset[i2];
                    for (int i3 = 0; i3 < charset.length; i3++) {
                        String s3 = s2 + charset[i3];
                        this.npass++;
                        if (matchHash(alg, s3, hash, salt)) return s3;
                    }
                }
            }
        }
         // longitud 5
        for (int i0 = 0; i0 < charset.length; i0++) {
            String s0 = "" + charset[i0];
            for (int i1 = 0; i1 < charset.length; i1++) {
                String s1 = s0 + charset[i1];
                for (int i2 = 0; i2 < charset.length; i2++) {
                    String s2 = s1 + charset[i2];
                    for (int i3 = 0; i3 < charset.length; i3++) {
                        String s3 = s2 + charset[i3];
                        for (int i4 = 0; i4 < charset.length; i4++) {
                            String s4 = s3 + charset[i4];
                            this.npass++;
                            if (matchHash(alg, s4, hash, salt)) return s4;
                        }
                    }
                }
            }
        }
        // longitud 6
        for (int i0 = 0; i0 < charset.length; i0++) {
            String s0 = "" + charset[i0];
            for (int i1 = 0; i1 < charset.length; i1++) {
                String s1 = s0 + charset[i1];
                for (int i2 = 0; i2 < charset.length; i2++) {
                    String s2 = s1 + charset[i2];
                    for (int i3 = 0; i3 < charset.length; i3++) {
                        String s3 = s2 + charset[i3];
                        for (int i4 = 0; i4 < charset.length; i4++) {
                            String s4 = s3 + charset[i4];
                            for (int i5 = 0; i5 < charset.length; i5++) {
                                String s5 = s4 + charset[i5];
                                this.npass++;
                                if (matchHash(alg, s5, hash, salt)) return s5;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    public String getInterval(long t1, long t2){
        long diff = t2 - t1;
        long hours = diff / (1000 * 60 * 60);
        diff -= hours * (1000 * 60 * 60);
        long minutes = diff / (1000 * 60);
        diff -= minutes * (1000 * 60);
        long seconds = diff / 1000;
        long millis = diff % 1000;
        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis);
    }

    public boolean matchHash(String alg, String candidate, String targetHash, String salt) throws NoSuchAlgorithmException {
    String candidateHash;
    if ("SHA-512".equalsIgnoreCase(alg)) {
        candidateHash = getSHA512AmbSalt(candidate, salt);
    } else if ("PBKDF2".equalsIgnoreCase(alg)) {
        candidateHash = getPBKDF2AmbSalt(candidate, salt);
    } else {
        throw new IllegalArgumentException("Algorisme desconegut: " + alg);
    }
    return candidateHash.equalsIgnoreCase(targetHash);
}
}
