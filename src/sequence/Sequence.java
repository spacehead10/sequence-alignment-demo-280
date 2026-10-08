package sequence;

public class Sequence {
    private char[] seqChars;
    private String seqString;

    public Sequence(String seqString) {
        seqChars = seqString.toCharArray();
        this.seqString = seqString;
    }

    public String letterAt(int index) {
        if (index < 0 || index >= seqChars.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for sequence \"" + seqString +
                    "\"");
        }
        return "" + seqChars[index];
    }

    public int length() {
        return seqChars.length;
    }
}
