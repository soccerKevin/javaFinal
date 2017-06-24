package com.zoo;
import java.awt.*;

public class Region extends Polygon{
    private Color color;

    public void setColor(Color color){
        this.color = color;
    }

    public void paint(Graphics g){
        g.setColor(color);
        g.fillPolygon(this);
    }
}
