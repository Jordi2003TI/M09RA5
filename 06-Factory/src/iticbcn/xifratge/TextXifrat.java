package iticbcn.xifratge;
import java.util.Arrays;

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
        try {
            return new String(dades, "UTF-8");
        } catch (Exception e) {
            return Arrays.toString(dades);
        }
    }

    
    
}
