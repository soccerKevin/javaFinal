package com.zoo;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Region extends Polygon {
    private Color color;
    private double scale = 1;
    private String name;
    private ArrayList<Point> points = new ArrayList(10);

    public Region(String name){
        super();
        this.name = name;
    }

    public String name(){
        return name;
    }

    public void addPoint(Point p){
        points.add(p);
        int x = (int) Math.round(scale * p.x);
        int y = (int) Math.round(scale * p.y);
        super.addPoint(x, y);
    }

    public void setScale(double scale){
        this.scale = scale;
        super.reset();

        Iterator pi = points.iterator();
        while(pi.hasNext()){
            Point point = (Point) pi.next();
            int x = (int) Math.round(scale * point.x);
            int y = (int) Math.round(scale * point.y);
            super.addPoint(x, y);
        }
    }

    public void addPoint(int x, int y){
        addPoint(new Point(x, y));
    }

    public void reset(){
        points = new ArrayList<>(10);
        reset();
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void paintComponent(Graphics g){
        g.setColor(color);
        g.fillPolygon(this);
    }
}
