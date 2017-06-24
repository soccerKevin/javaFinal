package com.zoo;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class  CampusPanel extends JPanel{
    private ArrayList<Animal> animals = new ArrayList(30);
    private double scale = 1;

    public CampusPanel(Point topLeft, Dimension size){
        setLocation(topLeft);
        setSize(size);
        setOpaque(true);
        setBackground(Color.GREEN);
        setLayout(null);
        addMouseMotionListener(new CampusMouseListener());
        setVisible(true);
    }

    public void addAnimal(Animal animal){
        animals.add(animal);
        add(animal);
        repaint();
    }

    public Dimension scaledSize(){
        int w = Math.round((float) (scale * getSize().width));
        int h = Math.round((float) (scale * getSize().height));
        return new Dimension(w, h);
    }

    public int width(){ return getSize().width; }
    public int height(){ return getSize().height; }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.translate(width() / 2, height() / 2);
//        g2.scale(scale, scale);
//        g2.translate( width() / -2, height() / -2);

        Iterator ai = animals.iterator();

        while(ai.hasNext()){
            Animal animal = (Animal) ai.next();
            animal.setSize(new Dimension(((int) scale * 100), ((int) scale * 100) ));
            animal.setScale(scale);
        }
    }

    private class CampusMouseListener extends MouseAdapter{
//        public void mouseDragged(MouseEvent e){
//            System.out.println("campus dragged");
//        }
    }
}
