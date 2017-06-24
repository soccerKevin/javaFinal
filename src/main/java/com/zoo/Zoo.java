package com.zoo;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class Zoo {
    private static final int width = 1200;
    private static final int height = 800;
    private String name;
    private JFrame zooFrame;
    private AnimalPanel animalPanel;
    private CampusPanel campusPanel;
    private ScalingPanel scalingPanel;
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
        });

        scalingPanel = new ScalingPanel(new Point(100, 0), new Dimension(width - 100, height));
        campusPanel = scalingPanel.campusPanel();

        zooFrame.add(animalPanel);
        zooFrame.add(scalingPanel);
        zooFrame.setVisible(true);
    }
}
