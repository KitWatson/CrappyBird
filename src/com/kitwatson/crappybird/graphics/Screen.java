package com.kitwatson.crappybird.graphics;

import com.kitwatson.crappybird.entities.Bird;
import com.kitwatson.crappybird.entities.Floor;
import com.kitwatson.crappybird.entities.Pillar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Kit Watson on 21-Nov-15.
 */
public class Screen extends JPanel implements Runnable {

    public final int WIDTH = 800, HEIGHT = 800;
    private int delay = 20, floorHeight = 120, speed = 5, yMotion = 0, score, highscore;
    private Thread thread;
    private boolean running = false;
    private Random r;
    private Bird bird = new Bird(WIDTH / 2, HEIGHT / 4, 20, 20);
    private Floor floorTop = new Floor(0, HEIGHT - floorHeight, WIDTH, 10);
    private Floor floorBottom = new Floor(0, HEIGHT - floorHeight + 10, WIDTH, floorHeight - 10);
    private Font fontBoldAriel32 = new Font("Ariel", Font.BOLD, 32);

    private long lastTime = System.currentTimeMillis();
    private List<Pillar> pillars = new ArrayList<>();

    public Screen() {
        setFocusable(true);
        Key key = new Key();
        Mouse mouse = new Mouse();
        addKeyListener(key);
        addMouseListener(mouse);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        r = new Random();

        start();
    }

    public void tick() {
        if (System.currentTimeMillis() - lastTime > delay) {
            lastTime = System.currentTimeMillis();

            if (bird.getYCoor() < 0 || bird.getYCoor() + bird.getHeight() > HEIGHT - floorHeight) {
                dead();
            }

            Rectangle birdRect = new Rectangle(bird.getXCoor(), bird.getYCoor(), bird.getWidth(), bird.getHeight());
            for (int i = 0; i < pillars.size(); i++) {
                Pillar pillar = pillars.get(i);
                if (birdRect.intersects(new Rectangle(pillar.getXCoor(), pillar.getYCoor(), pillar.getWidth(), pillar.getHeight()))) {
                    dead();
                }
                if (pillar.getYCoor() == 0 && Math.abs(pillar.getXCoor() + pillar.getWidth() - bird.getXCoor() + bird.getWidth() / 2) == 0) {
                    score++;
                }
                pillar.setXCoor(pillar.getXCoor() - speed);
            }

            for (int i = 0; i < pillars.size(); i++) {
                Pillar pillar = pillars.get(i);
                if (pillar.getXCoor() < 0 - pillar.getWidth()) {
                    pillars.remove(pillar);
                    addPillar(false);
                }
            }

            if (yMotion < 10) {
                yMotion += 1;
            }

            bird.setYCoor(bird.getYCoor() + yMotion);
        }
    }

    public void paint(Graphics g) {

        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLUE);
        floorTop.draw(g);
        g.setColor(Color.YELLOW);
        floorBottom.draw(g);

        for (Pillar pillar : pillars) {
            pillar.draw(g);
        }

        bird.draw(g);

        Text.shadowText(g, "Score: " + score, 10, HEIGHT - 45, Color.CYAN, fontBoldAriel32);
        Text.shadowText(g, "Highscore: " + highscore, WIDTH - 250, HEIGHT - 45, Color.CYAN, fontBoldAriel32);

    }

    public void start() {

        pillars.clear();

        addPillar(true);
        addPillar(true);

        running = true;
        score = 0;
        if (thread == null) {
            thread = new Thread(this, "Game Loop");
            thread.start();
        }

    }

    public void flap() {
        yMotion = -20;
    }

    private void dead() {

        bird.setYCoor(HEIGHT / 4);
        if (score > highscore) {
            highscore = score;
        }
        start();

    }

    private void addPillar(boolean newGame) {
        int gapSpace = 300;
        int width = 50;
        int bottomPillarHeight = 50 + r.nextInt(300);

        int xBottom = newGame ? WIDTH + width + pillars.size() * 300 : pillars.get(pillars.size() - 1).getXCoor() + 600;
        int yBottom = HEIGHT - bottomPillarHeight - floorHeight;
        pillars.add(new Pillar(xBottom, yBottom, width, bottomPillarHeight));

        int xTop = newGame ? WIDTH + width + (pillars.size() - 1) * 300 : pillars.get(pillars.size() - 1).getXCoor();
        int yTop = 0;
        pillars.add(new Pillar(xTop, yTop, width, HEIGHT - bottomPillarHeight - gapSpace));
    }

    public void run() {

        while (running) {
            repaint();
            tick();
        }

    }

    private class Key implements KeyListener {

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                flap();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

    }

    private class Mouse implements MouseListener {


        @Override
        public void mousePressed(MouseEvent e) {
            flap();
        }

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
