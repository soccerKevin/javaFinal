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

        this.inputPanel.getClearButton().addActionListener(e ->{
            shapes.clear();
            repaint();
        });
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
            int x = Math.min(initialPoint.x, finalPoint.x);
            int y = Math.min(initialPoint.y, finalPoint.y);

            shape = new Rectangle(x, y, Math.abs(dx) + 1, Math.abs(dy) + 1);
        }else if(inputPanel.getShapeType() == ShapeType.LINE ||
                inputPanel.getShapeType() == ShapeType.PEN){
            shape = new Line(initialPoint.x, initialPoint.y, dx + 1, dy + 1);
        }

        shapes.add(shape);

        shape.setColor(inputPanel.getCurrentColor());
        shape.setIsFilled(inputPanel.isFilledBoxChecked());
    }

    private class MouseInput extends MouseAdapter{
        Point initialPoint;

        @Override
        public void mousePressed(MouseEvent e){
            initialPoint = e.getPoint();
            addShape(initialPoint, initialPoint);
        }

        @Override
        public void mouseDragged(MouseEvent e){
            if(inputPanel.getShapeType() != ShapeType.PEN) {
                shapes.remove(shapes.size() - 1);
            }

            addShape(initialPoint, e.getPoint());
            repaint();

            if(inputPanel.getShapeType() == ShapeType.PEN){
                initialPoint = e.getPoint();
            }
        }
    }
}
