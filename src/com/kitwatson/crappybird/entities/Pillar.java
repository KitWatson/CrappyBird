package com.kitwatson.crappybird.entities;

import java.awt.*;

/**
 * Created by Kit Watson on 21-Nov-15.
 */
public class Pillar extends GamePiece {

    public Pillar(int xCoor, int yCoor, int width, int height) {
        super(xCoor, yCoor, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        super.draw(g);
    }

}
