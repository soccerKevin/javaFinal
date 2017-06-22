package com.zoo;
import javax.swing.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Hashtable;
import java.nio.file.Paths;
import com.esotericsoftware.yamlbeans.*;

public class Zoo {
    private static final int width = 500;
    private static final int height = 400;
    private String name;
    private JFrame campusFrame;
    private AnimalPanel animalPanel;
    private ArrayList<Animal> animals = new ArrayList(10);

    public Zoo(String name){
        this.name = name;
        this.animals = AnimalFactory.createAnimals();

        campusFrame = new JFrame(name);
        campusFrame.setSize(width, height);

        animalPanel = new AnimalPanel(animals, 100, height);

        campusFrame.add(animalPanel);
        campusFrame.setVisible(true);
    }
}
