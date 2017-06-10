package com.codefellows;
import java.awt.Graphics;

public class Rectangle extends Shape{
    private int width, height;

    public Rectangle(int x, int y, int width, int height){
        super(x, y);

        this.width = height;
        this.height = height;
    }

    @Override
    public void draw(Graphics canvas){
        canvas.setColor(color);
        if(isFilled){
            canvas.fillRect(x, y, width, height);
        }else{
            canvas.drawRect(x, y, width, height);
        }
    }
}
