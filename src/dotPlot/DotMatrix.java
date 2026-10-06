package dotPlot;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import sequence.*;

public class DotMatrix {
    private static final float TOP_LEFT_X = 50;
    private static final float TOP_LEFT_Y = 50;
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

        // TODO: Replace this loop with an actual system for initializing the cells with the provided sequence
        for (int i = 0; i < seq1.length(); i++) {
            for (int j = 0; j < seq2.length(); j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(TOP_LEFT_X, TOP_LEFT_Y, seq1.length() * Cell.SIDE_LENGTH, seq2.length() * Cell.SIDE_LENGTH);
        for (int i = 0; i < seq1.length(); i++) {
            for (int j = 0; j < seq2.length(); j++) {
                cells[i][j].render(g);
            }
        }
    }

    private class Cell {
        private static final float SIDE_LENGTH = 50;
        private String text;
        private int gx, gy;

        private Cell(String text, int gx, int gy) {
            this.text = text;
            this.gx = gx;
            this.gy = gy;
        }

        private Cell(int gx, int gy) {
            this("", gx, gy);
        }

        private void render(Graphics g) {
            g.setColor(Color.black);
            g.drawRect(TOP_LEFT_X + SIDE_LENGTH * gx, TOP_LEFT_Y + SIDE_LENGTH * gy, SIDE_LENGTH, SIDE_LENGTH);
        }
    }
}
