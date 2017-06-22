package com.zoo;

import com.esotericsoftware.yamlbeans.YamlReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class AnimalFactory {
    public static ArrayList<Animal> createAnimals(){
        ArrayList<Map> animalHashes = animalsFromFile();
        ArrayList<Animal> animals = new ArrayList(animalHashes.size());
        Iterator ai = animalHashes.iterator();

        while(ai.hasNext()){
            Map animal = (Map) ai.next();
            String name = (String) animal.get("name");
            String icon = String.format("assets/images/%s" , animal.get("icon"));
            Animal a = new Animal(name, icon);
            animals.add(a);
        }
        return animals;
    }

    private static ArrayList<Map> animalsFromFile(){
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
