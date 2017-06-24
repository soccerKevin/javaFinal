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
        campusPanel = new CampusPanel(new Point(0, 0), new Dimension(width(), height()));
        add(campusPanel);
    }

    public CampusPanel campusPanel(){
        return campusPanel;
    }

    private void mouseWheelListener(){
        addMouseWheelListener((e) -> {
            if(e.getUnitsToScroll() > 0){
                campusPanel.scaleUp();
            }else {
                campusPanel.scaleDown();
            }
        });
    }

    public int width(){ return getSize().width; }
    public int height(){ return getSize().height; }
}
