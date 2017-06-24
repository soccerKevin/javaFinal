package com.zoo;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class Zoo {
    private static final int width = 800;
    private static final int height = 600;
    private String name;
    private JFrame zooFrame;
    private AnimalPanel animalPanel;
    private CampusPanel campusPanel;
    private ArrayList<Animal> animals = new ArrayList(10);

    public Zoo(String name) {
        this.name = name;
        this.animals = AnimalFactory.createAnimals();

        zooFrame = new JFrame(name);
        zooFrame.setSize(new Dimension(width, height));
        zooFrame.setLayout(null);

        animalPanel = new AnimalPanel(animals, new Dimension(100, 80 * animals.size()));
        animalPanel.addPanelListener((animal) -> {
                animal.setLocation(new Point(100, 100));
            campusPanel.addAnimal(animal);
            campusPanel.repaint();
        });
        campusPanel = new CampusPanel(new Point(100, 0), new Dimension(width - 100, height));

        zooFrame.add(animalPanel);
        zooFrame.add(campusPanel);
        zooFrame.setVisible(true);
    }
}
