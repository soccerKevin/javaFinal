package com.zoo;
import java.awt.*;
import javax.swing.*;
import java.util.UUID;

public class Animal extends JPanel{
    private String name, imagePath;
    private Image image;
    private UUID uuid;

    public Animal(String name, String imagePath){
        super();
        this.name = name;
        this.imagePath = imagePath;
        this.image = new ImageIcon( imagePath ).getImage();
        this.uuid = UUID.randomUUID();
    }

    public Animal softClone(){
        return new Animal(this.name, this.imagePath);
    }

    public UUID uuid(){ return this.uuid; }
    public String name(){
        return name;
    }
    public String imagePath(){ return imagePath; }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getSize().width, getSize().height, this);
    }
}
