package com.zoo;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;

public class AnimalPanel extends JPanel{
    private ArrayList<Animal> animals;

    public AnimalPanel(ArrayList<Animal> animals){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.animals = animals;
        addAnimals();
    }

    private void addAnimals(){
        Iterator<Animal> animalsIterator = animals.iterator();
        while(animalsIterator.hasNext()){
            Animal animal = animalsIterator.next();
            add(new JButton(animal.name()));
        }
    }
}
