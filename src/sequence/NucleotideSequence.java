package sequence;

public class NucleotideSequence extends Sequence {
    public NucleotideSequence(String seqString) {
        super(seqString);
        for (char n : seqString.toCharArray()) {
            if (!isValidNucleotide(n)) {
                throw new IllegalArgumentException("'" + n + "' is not one of the 4 DNA nucleotides.");
            }
        }
    }

    private static boolean isValidNucleotide(char nucleotide) {
        return switch (nucleotide) {
            case 'a', 'A' -> true;
            case 't', 'T' -> true;
            case 'g', 'G' -> true;
            case 'c', 'C' -> true;
            default -> false;
        };
    }
}
