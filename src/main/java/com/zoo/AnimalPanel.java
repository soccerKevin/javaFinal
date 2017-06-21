package com.zoo;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class AnimalPanel extends JPanel{
    private ArrayList<Animal> animals;
    private int width, height;

    public AnimalPanel(ArrayList<Animal> animals, int width, int height){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.animals = animals;
        this.width = width;
        this.height = height;
        addAnimals();
    }

    private void addAnimals(){
        Iterator<Animal> animalsIterator = animals.iterator();
        while(animalsIterator.hasNext()){
            Animal animal = animalsIterator.next();
            add( new AnimalButton( new ImageIcon( animal.imagePath() ).getImage(), animal.name(), width, (int) height / animals.size() ) );
        }
    }

    private class AnimalButton extends JButton{
        private Image image;
        private int width, height;

        public AnimalButton(Image image, String name, int width, int height){
            super(name);
            this.image = image;
            this.width = width;
            this.height = height;
            setSize(new Dimension(width, height));
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(image, 0, 0, width, height, this);
        }
    }
}
