package com.zoo;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.*;

import java.awt.event.MouseEvent;
import javax.swing.*;

public class  CampusPanel extends JPanel{
    private ArrayList<Animal> animals = new ArrayList(30);
    private double scale = 1;
    private double scrollScale = .01;
    private Dimension originalSize, parentSize;
    private Point currentLocation, originalMouseLocation;

    public CampusPanel(Point topLeft, Dimension size, Dimension parentSize){
        originalSize = size;
        this.parentSize = parentSize;
        setLocation(topLeft);
        setSize(size);
        setOpaque(true);
        setBackground(Color.GREEN);
        setLayout(null);
        addMouseMotionListener(new MouseDragAdapter());
        addMouseListener(new MouseActionAdapter());
        setVisible(true);
    }

    public void addAnimal(Animal animal){
        animal.addParent(this);
        animals.add(animal);
        add(animal);
    }

    public void scale(MouseWheelEvent e){
        if(e.getUnitsToScroll() > 0) {
            scale += scrollScale;
        }else {
            if (scale == 1) return;
            scale -= scrollScale;
            if (scale < 1) scale = 1;
        }
        System.out.println(e.getX());
        e.getY();
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

    public void setLocation(Point location){
        currentLocation = location;
        super.setLocation(location);
    }

    private void superSetLocation(Point location){
        super.setLocation(location);
    }

    public int width(){ return getSize().width; }
    public int height(){ return getSize().height; }

    public class MouseActionAdapter extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {
            currentLocation = getLocation();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            currentLocation = getLocation();
            originalMouseLocation = e.getLocationOnScreen();
        }
    }

    public class MouseDragAdapter extends MouseAdapter{
        @Override
        public void mouseDragged(MouseEvent e) {
            int dx = originalMouseLocation.x - e.getLocationOnScreen().x;
            int dy = originalMouseLocation.y - e.getLocationOnScreen().y;
            int newX = Math.min(0, currentLocation.x - dx);
            int newY = Math.min(0, currentLocation.y - dy);
            newX = Math.max(newX, -(width() - parentSize.width) );
            newY = Math.max(newY, -(height() - parentSize.height) );
            superSetLocation(new Point(newX, newY));
        }
    }
}
