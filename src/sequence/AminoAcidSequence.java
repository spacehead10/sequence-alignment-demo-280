package sequence;

public class AminoAcidSequence extends Sequence {
    public AminoAcidSequence(String seqString) {
        super(seqString);
//        for (char n : seqString.toCharArray()) {
//            if (!isValidAminoAcid(n)) {
//                throw new IllegalArgumentException("'" + n + "' is not one of the 20 main amino acids.");
//            }
//        }
    }

    private static boolean isValidAminoAcid(char aminoAcid) {
        throw new UnsupportedOperationException("Unfinished code"); // TODO: Write this method
    }
}
