package com.codefellows;

import javax.swing.*;
import java.awt.*;

public class Paint {
    public static void main(String[] args){
        JFrame frame = new JFrame("Paint");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        InputPanel inputPanel = new InputPanel();
        frame.add(inputPanel);

        frame.pack();
        frame.setVisible(true);
    }
}
