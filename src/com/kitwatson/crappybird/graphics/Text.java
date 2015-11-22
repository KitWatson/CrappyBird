package com.kitwatson.crappybird.graphics;

import java.awt.*;

/**
 * Created by Kit Watson on 22-Nov-15.
 */
public class Text {

    public static void shadowText(Graphics g, String string, int x, int y, Color textColor) {
        g.setColor(Color.BLACK);
        g.drawString(string, x + 1, y + 1);
        g.setColor(textColor);
        g.drawString(string, x, y);
    }

    public static void shadowText(Graphics g, String string, int x, int y, Color textColor, Font font) {
        g.setFont(font);
        shadowText(g, string, x, y, textColor);
    }

}
