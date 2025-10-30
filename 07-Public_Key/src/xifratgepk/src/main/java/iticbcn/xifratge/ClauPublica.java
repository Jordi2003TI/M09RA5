package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {
    public KeyPair generaParellClausRSA()throws Exception{
        try{
            // 1. Hacemos que nos genere las claves para el Algoritmo RSA
            KeyPairGenerator KeyGen = KeyPairGenerator.getInstance("RSA");
            // 2. Inicializamos con un tamaño 
            KeyGen.initialize(2048);

            //3. Hacemos que nos genere las dos claves y lo devolvemos 
            KeyPair parDeClaves = KeyGen.generateKeyPair();
            return parDeClaves;
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica)throws Exception{
        // 1. Creamos la array de bytes a partir del mensaje que quermeos cifrar 
        byte[] mensajeCifrado = msg.getBytes();

        // 2. Creamos el cipher pero esta vez usamos el algorismo RSA
        Cipher ch = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // 3. Inicializamos la encriptacion con el ENCRYP pero con la clave publica
        ch.init(Cipher.ENCRYPT_MODE, clauPublica);

        // 4. Ciframos en bytes el mensaje
        byte[] msgCifrado = ch.doFinal(mensajeCifrado);

        return msgCifrado;
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception{
        // 1 Hacemos en cipher con el algoritmo que quremos 
        Cipher desCh = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        // 2 Inicializamos en modo DECRYPT para poder descirfrar la array con la clave privada 
        desCh.init(Cipher.DECRYPT_MODE, ClauPrivada);

        // 3 Desciframos los bytes cifrados 
        byte[] mngDescifrado = desCh.doFinal(msgXifrat);
        
        return new String(mngDescifrado);
    }
    
}
