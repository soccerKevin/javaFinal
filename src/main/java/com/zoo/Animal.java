package com.zoo;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.UUID;

public class Animal extends JPanel{
    private String name, imagePath;
    private Image image;
    private Dimension originalSize = new Dimension(100, 100);
    private UUID uuid;
    private double scale = 1;

    public Animal(String name, String imagePath){
        super();
        this.name = name;
        this.imagePath = imagePath;
        this.image = new ImageIcon( imagePath ).getImage();
        this.uuid = UUID.randomUUID();
        setBackground(new Color(0, 0, 0, 0));
        addMouseMotionListener(new AnimalMouseAdapter());
        setVisible(true);
    }

    public void setScale(double scale){
        this.scale = scale;
        int w = (int) Math.round(scale * originalSize.width );
        int h = (int) Math.round(scale * originalSize.height );
        setSize(new Dimension(w, h));
    }

    public Animal softClone(){
        return new Animal(this.name, this.imagePath);
    }

    public UUID uuid(){ return this.uuid; }
    public String name(){ return name; }
    public String imagePath(){ return imagePath; }
    public int width(){ return getSize().width; }
    public int height(){ return getSize().height; }

    public void paintComponent(Graphics g){
        g.drawImage(image, 0, 0, width(), height(), this);
    }

    public class AnimalMouseAdapter extends MouseAdapter{
        @Override
        public void mouseDragged(MouseEvent e) {
            setLocation(e.getLocationOnScreen().x - 200, e.getLocationOnScreen().y - 100);
        }
    }
}
