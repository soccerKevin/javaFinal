package com.zoo;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class AnimalPanel extends JPanel{
    private ArrayList<Animal> animals;

    public AnimalPanel(ArrayList<Animal> animals, Dimension size){
        this.animals = animals;
        setSize(size);
        setLayout(new GridLayout(animals.size(), 1));
        addAnimals();
    }

    private void addAnimals(){
        Iterator<Animal> animalsIterator = animals.iterator();
        while(animalsIterator.hasNext()){
            Animal animal = animalsIterator.next();
            add( new AnimalButton( new ImageIcon( animal.imagePath() ).getImage(), animal.name(), new Dimension(getSize().width, (int) getSize().height / animals.size() ) ) );
        }
    }

    private class AnimalButton extends JButton{
        private Image image;
        private Dimension size;

        public AnimalButton(Image image, String name, Dimension size){
            super(name);
            this.image = image;
            this.size = size;
            setSize(size);
        }

        public int width(){ return getSize().width; }
        public int height(){ return getSize().height; }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(image, 0, 0, width(), height(), this);
        }
    }
}
