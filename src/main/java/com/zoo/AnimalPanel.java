package com.zoo;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class AnimalPanel extends JPanel{
    private ArrayList<Animal> animals;
    private ArrayList<PanelListener> panelListeners;

    public AnimalPanel(ArrayList<Animal> animals, Dimension size){
        this.animals = animals;
        this.panelListeners = new ArrayList(10);
        setSize(size);
        setLayout(new GridLayout(animals.size(), 1));
        addAnimals();
    }

    public void addPanelListener(PanelListener panelListener){
        panelListeners.add(panelListener);
    }

    private void addAnimals(){
        Iterator<Animal> animalsIterator = animals.iterator();
        while(animalsIterator.hasNext()){
            Animal animal = animalsIterator.next();
            add( new AnimalButton( animal, new Dimension(getSize().width, (int) getSize().height / animals.size() ) ) );
        }
    }

    private class AnimalButton extends JButton{
        private Animal animal;
        private Image image;
        private Dimension size;

        public AnimalButton(Animal animal, Dimension size){
            super(animal.name());
            this.animal = animal;
            this.size = size;
            setSize(size);
            addActionListener((e) -> {
                Iterator li = panelListeners.iterator();

                while(li.hasNext()){
                    PanelListener listener = (PanelListener) li.next();
                    listener.animalClicked(animal.softClone());
                }
            });
        }

        public Image image(){
            if(image != null) return image;
            return this.image = new ImageIcon( animal.imagePath() ).getImage();
        }

        public int width(){ return getSize().width; }
        public int height(){ return getSize().height; }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(image(), 0, 0, width(), height(), this);
        }
    }
}
