package dotPlot;

import sequence.*;

public class DotMatrix {
    private Sequence seq1, seq2;
    private Cell[][] cells;

    public DotMatrix(Sequence seq1, Sequence seq2) {
        this.seq1 = seq1;
        this.seq2 = seq2;
        cells = new Cell[seq1.length()][seq2.length()];
    }

    public DotMatrix(String seqStr1, String seqStr2, Class<? extends Sequence> seqType) {
        if (seqType.equals(NucleotideSequence.class)) {
            seq1 = new NucleotideSequence(seqStr1);
            seq2 = new NucleotideSequence(seqStr2);
        }
        else if (seqType.equals(AminoAcidSequence.class)) {
            seq1 = new AminoAcidSequence(seqStr1);
            seq2 = new AminoAcidSequence(seqStr2);
        }
        else {
            seq1 = new Sequence(seqStr1);
            seq2 = new Sequence(seqStr2);
        }
        cells = new Cell[seq1.length()][seq2.length()];
    }

    private static class Cell {
    }
}
