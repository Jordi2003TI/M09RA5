package iticbcn.xifratge;
import java.util.Arrays;
import java.util.Base64;

public class TextXifrat {
    private byte[] dades;

    public TextXifrat(byte[] dades){
        this.dades = dades;
    }

    public byte[] getBytes(){
        return dades;
    }

    @Override
    public String toString() {
         return Base64.getEncoder().encodeToString(dades);
    }

    
    
}
