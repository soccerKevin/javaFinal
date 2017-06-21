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
    private String name;
    private JFrame campusFrame;
    private AnimalPanel animalPanel;
    private ArrayList<Animal> animals = new ArrayList(10);

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
        if ( animals.size() > 0 ) return;
        ArrayList<Map> animalHashes = animalsFromFile();
        Iterator ai = animalHashes.iterator();

        while(ai.hasNext()){
            Map animal = (Map) ai.next();
            String name = (String) animal.get("name");
            String icon = String.format("assets/images/%s" , animal.get("icon"));
            Animal a = new Animal(name, icon);
            animals.add(a);
        }
    }

    private ArrayList<Map> animalsFromFile(){
        YamlReader animalFile = null;
        ArrayList<Map> animals = null;
        try {
            animalFile = new YamlReader(new FileReader("./yaml/animals.yaml"));
            animals = (ArrayList<Map>) ((Map) animalFile.read()).get("animals");
        }catch(Exception e){
            System.out.println(e);
        }
        return animals;
    }
}
