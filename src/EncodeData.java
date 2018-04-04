/**
 * EncodeData class
 *
 * @author Hieu Duong
 * @date 4/1/18
 */

public class EncodeData {
    private String letter;
    private String code;

    public EncodeData(String letter, String code) {
        this.letter = letter;
        this.code = code;
    }

    public String getLetter() {
        return letter;
    }

    public String getCode() {
        return code;
    }
}
