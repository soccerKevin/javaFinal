package com.codefellows;
import java.awt.*;

public class Circle extends Shape{
    private int radius;

    public Circle(int x, int y, int radius){
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void draw(Graphics canvas){
        canvas.setColor(color);

        if(isFilled){
            canvas.fillOval(x - radius + 1, y - radius + 1, radius * 2, radius * 2);
        }else{
            canvas.drawOval(x - radius + 1, y - radius + 1, radius * 2, radius * 2);
        }
    }
}
