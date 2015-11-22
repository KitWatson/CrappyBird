package com.kitwatson.crappybird;

import com.kitwatson.crappybird.graphics.Screen;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kit Watson on 21-Nov-15.
 */
public class Frame extends JFrame {

    public Frame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("CrappyBird");//
        setResizable(false);
        init();
    }

    public static void main(String[] args) {
        new Frame();
    }

    public void init() {

        setLayout(new GridLayout());
        Screen s = new Screen();
        add(s);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
