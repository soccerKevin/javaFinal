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
    private ArrayList<Region> regions = new ArrayList(30);
    private ArrayList<TextListener> textListeners = new ArrayList(10);
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

    public void addTextListener(TextListener listener){
        textListeners.add(listener);
    }

    public void addRegion(Region region){
        regions.add(region);
        region.setScale(scale);
        repaint();
    }

    public void addAnimal(Animal animal){
        animal.addParent(this);
        animals.add(animal);
        add(animal);
        animal.setScale(scale);
    }

    public void scale(MouseWheelEvent e){
        if(e.getUnitsToScroll() > 0) {
            scale += scrollScale;
        }else {
            if (scale == .4) return;
            scale -= scrollScale;
            if (scale < .4) scale = .4;
        }
        scaleSize();
        scaleAnimals();
        scaleRegions();
        repaint();
    }

    public void displayAnimalRegion(Animal animal){
        String text;
        Region region = animalRegion(animal);
        if(region != null) {
            text = "The " + animal.name() + " is in the " + region.name();
        }else{
            text = "The " + animal.name() + " is not in a region";
        }
        displayText(text);
    }

    private void displayText(String text){
        Iterator ti = textListeners.iterator();
        while(ti.hasNext()){
            ((TextListener) ti.next()).display(text);
        }
    }

    public Region animalRegion(Animal animal){
        Point animalCenter = animal.center();
        Iterator ri  = regions.iterator();
        while(ri.hasNext()){
            Region region = (Region) ri.next();
            boolean inRegion = region.contains(animalCenter);
            if(inRegion) return region;
        }
        return null;
    }

    private void scaleRegions(){
        Iterator ai = regions.iterator();

        while(ai.hasNext()){
            ((Region) ai.next()).setScale(scale);
        }
    }

    private void scaleAnimals(){
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

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Iterator ri = regions.iterator();
        while(ri.hasNext()){
            ((Region) ri.next()).paintComponent(g);
        }
    }

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
            int newX = currentLocation.x - dx;
            int newY = currentLocation.y - dy;
            newX = Math.min(0, newX);
            newY = Math.min(0, newY);
            newX = Math.max(newX, -(width() - parentSize.width) );
            newY = Math.max(newY, -(height() - parentSize.height) );
            superSetLocation(new Point(newX, newY));
        }
    }
}
