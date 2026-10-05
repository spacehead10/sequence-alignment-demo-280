package core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.SlickException;

import java.io.FileNotFoundException;

public class Media {
    //fonts here

    //images here

    public static void loadImages() {
        try {
        }
        catch (Exception e) {
            System.err.println("Image not found!");
        }
    }

    public static final int LEFT = 0;
    public static final int TOP = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 2;

    /**
     * Draws aligned text.
     * @param str String to draw
     * @param x Position based on your chosen alignment
     * @param y Position based on your chosen alignment
     * @param xAlign Media.LEFT, Media.CENTER, or Media.RIGHT
     * @param yAlign Media.TOP, Media.CENTER, or Media.BOTTOM
     * @param font Font to use
     * @param g Pass the graphics object your drawing method is using
     */
    public static void drawAlignedString(String str, float x, float y, int xAlign, int yAlign, TrueTypeFont font, Graphics g) {
        float adjustedX, adjustedY;
        switch (xAlign) {
            case LEFT:
                adjustedX = x;
                break;
            case CENTER:
                adjustedX = x - font.getWidth(str) / 2f;
                break;
            case RIGHT:
                adjustedX = x - font.getWidth(str);
                break;
            default:
                return;
        }
        switch (yAlign) {
            case TOP:
                adjustedY = y;
                break;
            case CENTER:
                adjustedY = y - font.getHeight() / 2f;
                break;
            case BOTTOM:
                adjustedY = y - font.getHeight();
                break;
            default:
                return;
        }
        g.setFont(font);
        g.drawString(str, adjustedX, adjustedY);
    }

    public static void drawShadowedString(String str, float x, float y, int xAlign, int yAlign, TrueTypeFont font, Graphics g) {
        g.setColor(Color.black);
        drawAlignedString(str, x - 2, y - 2, xAlign, yAlign, font, g);
        g.setColor(Color.white);
        drawAlignedString(str, x, y, xAlign, yAlign, font, g);
    }
}
