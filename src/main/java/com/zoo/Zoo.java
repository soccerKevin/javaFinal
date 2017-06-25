package com.zoo;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

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
        createRegions();
        campusPanel.addTextListener((text) ->{
            JOptionPane optionPane = new JOptionPane(text,JOptionPane.YES_OPTION);
            JDialog dialog = optionPane.createDialog(text);
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        });

        zooFrame.add(animalPanel);
        zooFrame.add(scalingPanel);
        zooFrame.setVisible(true);
    }

    private void createRegions(){
        Region r1 = region1();
        campusPanel.addRegion(r1);

        Region r2 = region2();
        campusPanel.addRegion(r2);

        Region r3 = region3();
        campusPanel.addRegion(r3);

        Region r4 = region4();
        campusPanel.addRegion(r4);

        Region r5 = region5();
        campusPanel.addRegion(r5);

        Region r6 = region6();
        campusPanel.addRegion(r6);
    }

    private Region region1(){
        Region r = new Region("Blue Park");
        r.addPoint(0, 0);
        r.addPoint(300, 0);
        r.addPoint(340, 100);
        r.addPoint(150, 250);
        r.addPoint(80, 180);
        r.setColor(Color.BLUE);
        return r;
    }

    private Region region2(){
        Region r = new Region("Red Park");
        r.addPoint(500, 100);
        r.addPoint(750, 120);
        r.addPoint(850, 300);
        r.addPoint(600, 450);
        r.addPoint(400, 300);
        r.setColor(Color.RED);
        return r;
    }

    private Region region3(){
        Region r = new Region("Gray Park");
        r.addPoint(200, 400);
        r.addPoint(100, 600);
        r.addPoint(150, 700);
        r.addPoint(400, 600);
        r.addPoint(300, 500);
        r.setColor(Color.GRAY);
        return r;
    }

    private Region region4(){
        Region r = new Region("Pink Park");
        r.addPoint(1200, 1700);
        r.addPoint(1350, 1850);
        r.addPoint(1250, 1950);
        r.addPoint(1050, 1700);
        r.addPoint(1150, 1550);
        r.setColor(Color.PINK);
        return r;
    }

    private Region region5(){
        Region r = new Region("Magenta Park");
        r.addPoint(1200, 1200);
        r.addPoint(1500, 1300);
        r.addPoint(1400, 1500);
        r.addPoint(1300, 1600);
        r.addPoint(1100, 1400);
        r.setColor(Color.MAGENTA);
        return r;
    }

    private Region region6(){
        Region r = new Region("Yellow Park");
        r.addPoint(200, 1500);
        r.addPoint(500, 1400);
        r.addPoint(600, 1800);
        r.addPoint(300, 1850);
        r.addPoint(100, 1600);
        r.setColor(Color.YELLOW);
        return r;
    }
}
