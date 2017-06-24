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
    private Point currentLocation;
    private UUID uuid;
    private double scale = 1;

    public Animal(String name, String imagePath){
        super();
        this.name = name;
        this.imagePath = imagePath;
        this.image = new ImageIcon( imagePath ).getImage();
        this.uuid = UUID.randomUUID();
        setBackground(new Color(0, 0, 0, 0));
        addMouseMotionListener(new MouseDragAdapter());
        addMouseListener(new MouseActionAdapter());
        setVisible(true);
    }

    public void setLocation(Point location){
        currentLocation = location;
        super.setLocation(location);
    }

    public void setScale(double scale){
        this.scale = scale;
        int w = (int) Math.round(scale * originalSize.width );
        int h = (int) Math.round(scale * originalSize.height );
        setSize(new Dimension(w, h));

        int x = (int) Math.round(scale * currentLocation.x);
        int y = (int) Math.round(scale * currentLocation.y);
        super.setLocation(new Point(x, y));
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

    public class MouseActionAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            currentLocation = getLocation();
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            currentLocation = getLocation();
        }

    }

    public class MouseDragAdapter extends MouseAdapter{
        @Override
        public void mouseDragged(MouseEvent e) {
            setLocation(e.getLocationOnScreen().x - 200, e.getLocationOnScreen().y - 100);
        }
    }
}
