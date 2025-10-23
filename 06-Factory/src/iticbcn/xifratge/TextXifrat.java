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
        return "TextXifrat [dades=" + Arrays.toString(dades) + "]";
    }

    
    
}
