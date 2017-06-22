package com.zoo;
import java.util.UUID;

public class Animal {
    private String name, imagePath;
    private UUID uuid;

    public Animal(String name, String imagePath){
        this.name = name;
        this.imagePath = imagePath;
        this.uuid = UUID.randomUUID();
    }

    public UUID uuid(){
        return this.uuid;
    }

    public String name(){
        return name;
    }

    public String imagePath(){
        return imagePath;
    }
}
