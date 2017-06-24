package com.zoo;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class  CampusPanel extends JPanel{
    private ArrayList<Animal> animals = new ArrayList(30);
    private double scale = 1;
    private double scrollScale = .01;

    public CampusPanel(Point topLeft, Dimension size){
        setLocation(topLeft);
        setSize(size);
        setOpaque(true);
        setBackground(Color.GREEN);
        setLayout(null);
        addMouseMotionListener(new CampusMouseListener());
        mouseWheelListener();
        setVisible(true);
    }

    private void mouseWheelListener(){
        addMouseWheelListener((e) -> {
            scale += (e.getUnitsToScroll() > 0 ? scrollScale : -scrollScale);
            repaint();
        });
    }

    public void addAnimal(Animal animal){
        animals.add(animal);
        add(animal);
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
            ((Animal) ai.next()).setScale(scale);
        }
    }

    private class CampusMouseListener extends MouseAdapter{
//        public void mouseDragged(MouseEvent e){
//            System.out.println("campus dragged");
//        }
    }
}
