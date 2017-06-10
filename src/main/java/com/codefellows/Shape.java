package com.codefellows;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
    protected int x, y;
    protected boolean isFilled;
    protected Color color;

    public Shape(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setIsFilled(boolean isFilled){
        this.isFilled = isFilled;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public abstract void draw(Graphics canvas);
}
