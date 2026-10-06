package sequence;

public class Sequence {
    private char[] seqChars;

    public Sequence(String seqString) {
        seqChars = seqString.toCharArray();
    }

    public int length() {
        return seqChars.length;
    }
}
