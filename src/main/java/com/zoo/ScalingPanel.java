package com.zoo;
import javax.swing.*;
import java.awt.*;

public class ScalingPanel extends JPanel{
    private CampusPanel campusPanel;

    public ScalingPanel(Point topLeft, Dimension size){
        setLocation(topLeft);
        setSize(size);
        setOpaque(true);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(null);
        mouseWheelListener();
        setVisible(true);
        campusPanel = new CampusPanel(new Point(0, 0), new Dimension(2000, 2000));
        add(campusPanel);
    }

    public CampusPanel campusPanel(){
        return campusPanel;
    }

    private void mouseWheelListener(){
        addMouseWheelListener((e) -> {
            campusPanel.scale(e);
        });
    }

    public int width(){ return getSize().width; }
    public int height(){ return getSize().height; }
}
