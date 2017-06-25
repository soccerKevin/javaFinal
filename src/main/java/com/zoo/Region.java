package com.zoo;
import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

public class Region extends JPanel{
    private Color color;
    private double scale;
    private Polygon polygon = new Polygon();

    public Region(){
        super();
        setVisible(true);
    }


    public void setScale(double scale){
        this.scale = scale;
    }

    public void addPoint(int width, int height){
        polygon.addPoint(width, height);
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void paintComponent(Graphics g){
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform saveTransform = g2d.getTransform();

        try {
            AffineTransform scaleMatrix = new AffineTransform();
            scaleMatrix.scale(scale, scale);
            g2d.setTransform(scaleMatrix);
            g2d.fillPolygon(polygon);
        } finally {
            g2d.setTransform(saveTransform);
        }
    }
}
