package com.codefellows;
import java.awt.*;

public class Line extends Shape{
    private Point p1, p2;
    private int width;
    private int height;

    public Line(int x, int y, int width, int height){
        super(x, y);
        this.p1 = new Point(x, y);
        this.p2 = new Point(x + width - 1, y + height - 1);

        this.width = width;
        this.height = height;
    }

    public Line(Point p1, Point p2){
        super((int) p1.getX(), (int) p1.getY());
        this.width = (int)(p2.getX() - p1.getX());
        this.height = (int)(p2.getY() - p1.getY());
    }

    @Override
    public void draw(Graphics canvas){
        canvas.setColor(color);
        canvas.drawLine(x, y, x + width -1, y + height -1);
    }
}
