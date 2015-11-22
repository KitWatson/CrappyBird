package com.kitwatson.crappybird.entities;

import java.awt.*;

/**
 * Created by Kit Watson on 21-Nov-15.
 */
public abstract class GamePiece {

    protected int xCoor, yCoor, width, height;

    public GamePiece(int xCoor, int yCoor, int width, int height) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.fillRect(xCoor, yCoor, width, height);
    }

    public int getXCoor() {
        return this.xCoor;
    }

    public void setXCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public int getYCoor() {
        return this.yCoor;
    }

    public void setYCoor(int yCoor) {
        this.yCoor = yCoor;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
