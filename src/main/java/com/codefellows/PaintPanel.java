package com.codefellows;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PaintPanel extends JPanel{
    private InputPanel inputPanel;
    private List<Shape> shapes = new ArrayList<>();

    public PaintPanel(InputPanel inputPanel){
        this.inputPanel = inputPanel;
        setPreferredSize(new Dimension(750, 0));
        setBackground(Color.WHITE);

        MouseInput mouseInput = new MouseInput();

        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for(Shape s : shapes){ s.draw(g); }
    }

    private void addShape(Point initialPoint, Point finalPoint){
        Shape shape = null;

        int dx = finalPoint.x - initialPoint.x;
        int dy = finalPoint.y - initialPoint.y;

        if(inputPanel.getShapeType() == ShapeType.CIRCLE){
            int radius = (int) Math.round( Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)) );

            shape = new Circle(initialPoint.x, initialPoint.y, radius);
        }else if(inputPanel.getShapeType() == ShapeType.RECTANGLE){
            shape = new Rectangle(initialPoint.x, initialPoint.y, dx + 1, dy + 1);
        }else if(inputPanel.getShapeType() == ShapeType.LINE){
            shape = new Line(initialPoint.x, initialPoint.y, dx + 1, dy + 1);
        }

        shapes.add(shape);
    }

    private class MouseInput extends MouseAdapter{
        Point initialPoint;

        @Override
        public void mousePressed(MouseEvent e){
            initialPoint = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e){
            addShape(initialPoint, e.getPoint());
            repaint();
        }
    }
}
