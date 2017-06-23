package com.zoo;
import java.awt.*;
import javax.swing.*;
import java.util.UUID;

public class Animal extends JPanel{
    private String name, imagePath;
    private Image image;
    private UUID uuid;
    private double scale = 1;

    public Animal(String name, String imagePath){
        super();
        this.name = name;
        this.imagePath = imagePath;
        this.image = new ImageIcon( imagePath ).getImage();
        this.uuid = UUID.randomUUID();
    }

    public void setScale(double scale){
        this.scale = scale;
    }

    public Animal softClone(){
        return new Animal(this.name, this.imagePath);
    }

    public UUID uuid(){ return this.uuid; }
    public String name(){
        return name;
    }
    public String imagePath(){ return imagePath; }
    public int width(){ return getSize().width; }
    public int height(){ return getSize().height; }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int w = Math.round((float) scale * width());
        int h = Math.round((float) scale * height());
        g.drawImage(image, 0, 0, w, h, this);
    }
}
