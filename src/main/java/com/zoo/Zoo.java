package com.zoo;
import javax.swing.*;
import java.util.ArrayList;

public class Zoo {
    private String name;
    private JFrame campusFrame;
    private AnimalPanel animalPanel;
    private ArrayList<Animal> animals;

    public Zoo(String name){
        this.name = name;
        createAnimals();

        campusFrame = new JFrame(name);
        campusFrame.setSize(400, 300);

        animalPanel = new AnimalPanel(animals);

        campusFrame.add(animalPanel);
        campusFrame.setVisible(true);
    }

    private void createAnimals(){
        this.animals = new ArrayList(5);
        this.animals.add(new Animal("Zebra"));
        this.animals.add(new Animal("Aligator"));
        this.animals.add(new Animal("Giraffe"));
        this.animals.add(new Animal("Rabbit"));
        this.animals.add(new Animal("Tiger"));
    }
}
