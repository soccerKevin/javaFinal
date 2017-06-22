package com.zoo;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

public class Zoo {
    private static final int width = 800;
    private static final int height = 600;
    private String name;
    private JFrame campusFrame;
    private AnimalPanel animalPanel;
    private ZooPanel zooPanel;
    private ArrayList<Animal> animals = new ArrayList(10);

    public Zoo(String name){
        this.name = name;
        this.animals = AnimalFactory.createAnimals();

        campusFrame = new JFrame(name);
        campusFrame.setSize(width, height);

        animalPanel = new AnimalPanel(animals, 100, 80 * animals.size());
        zooPanel = new ZooPanel(new Point(100, 0), new Dimension(width - 100, height));
        zooPanel.addAnimal(animals.get(0));

        campusFrame.add(animalPanel);
        campusFrame.add(zooPanel);
        campusFrame.setVisible(true);
    }
}
