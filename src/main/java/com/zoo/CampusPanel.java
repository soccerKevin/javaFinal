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
    private Dimension originalSize;

    public CampusPanel(Point topLeft, Dimension size){
        setLocation(topLeft);
        originalSize = size;
        setSize(size);
        setOpaque(true);
        setBackground(Color.GREEN);
        setLayout(null);
        setVisible(true);
    }

    public void addAnimal(Animal animal){
        animals.add(animal);
        add(animal);
    }

    public void scaleUp(){
        scale += scrollScale;
        rescale();
    }

    public void scaleDown(){
        if(scale == 1) return;
        scale -= scrollScale;
        if(scale < 1) scale = 1;
        rescale();
    }

    private void rescale(){
        scaleSize();
        rescaleAnimals();
        repaint();
    }

    private void rescaleAnimals(){
        Iterator ai = animals.iterator();

        while(ai.hasNext()){
            ((Animal) ai.next()).setScale(scale);
        }
    }

    private void scaleSize(){
        int w = Math.round((float) (scale * originalSize.width));
        int h = Math.round((float) (scale * originalSize.height));
        setSize(new Dimension(w, h));
    }

    public int width(){ return getSize().width; }
    public int height(){ return getSize().height; }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//        g2.translate(width() / 2, height() / 2);
//        g2.scale(scale, scale);
//        g2.translate( width() / -2, height() / -2);


    }
}
