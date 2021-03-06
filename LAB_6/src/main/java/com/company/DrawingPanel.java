package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY());
                repaint();
            } //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }

    private void drawShape(int x, int y) {
        int radius = (int) (Math.random() * 100); //generate a random number
        int sides = frame.configPanel.getSidesField(); //get the value from UI (in ConfigPanel)
        boolean culoareRandom = frame.configPanel.getColor();
        float r = 0f, g = 0f, b = 0f;
        if (culoareRandom) {
            r = (float) Math.random();
            g = (float) Math.random();
            b = (float) Math.random();
        }
        Color color = new Color(r, g, b, .5f); //create a transparent random Color.
        graphics.setColor(color);
        boolean polygon = frame.configPanel.getShape();
        if (polygon)
            graphics.fill(new RegularPolygon(x, y, radius, sides));
        else
            graphics.fill(new Ellipse2D.Double(x-radius/2, y-radius/2, radius, radius)); // folosim x/y-radius/2 pentru ca locul unde apasam sa fie centrul cercului
    }

    @Override
    public void update(Graphics g) {
    } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
