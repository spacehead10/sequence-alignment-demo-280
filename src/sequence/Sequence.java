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
            System.out.println("\u001b[33mCaution: Requested letter at out-of-bounds index " + index + " of sequence " +
                    seqString + ", which may or may not be intended behavior.\u001b[0m");
            return "-";
        }
        return "" + seqChars[index];
    }

    public int length() {
        return seqChars.length;
    }
}
