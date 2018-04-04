/**
 * Main class
 *
 * @author Hieu Duong
 * @date 4/1/18
 */

public class Main {
    public static void main(String[] args){

        String originalString = "A_DEAD_DAD_CEDED_A_BAD_BABE_A_BEADED_ABACA_BED";

        HuffCodes hCode = new HuffCodes();
        hCode.constructEncoding(originalString);

        System.out.println("Original String: "+originalString);

        String encodedString = hCode.encode(originalString);
        System.out.println("Encoded: "+encodedString);

        String decodedString = hCode.decode(encodedString);
        System.out.println("Decoded: "+decodedString);
    }


}
