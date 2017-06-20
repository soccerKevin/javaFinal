package com.zoo;

public class Animal {
    private String name, imagePath;

    public Animal(String name, String imagePath){
        this.name = name;
        this.imagePath = imagePath;
    }

    public String name(){
        return name;
    }

    public String imagePath(){
        return imagePath;
    }
}
