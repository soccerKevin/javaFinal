package com.zoo;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.*;
import javax.swing.*;

public class ZooPanel extends JPanel{
    private ArrayList<Animal> animals = new ArrayList(30);

    public ZooPanel(Point topLeft, Dimension size){
        setLocation(topLeft);
        setSize(size);
        setOpaque(true);
        setBackground(Color.BLUE);
        setVisible(true);
    }

    public void addAnimal(Animal animal){
        animals.add(animal);
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Iterator ai = animals.iterator();

        while(ai.hasNext()){
            Animal animal = (Animal) ai.next();
            animal.paintComponent(g);
        }
    }
}
