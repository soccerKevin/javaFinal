package com.codefellows;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel{
    private JButton rectangleButton, circleButton, lineButton, colorButton, penButton, clearButton, quitButton;
    private JCheckBox filledBox;
    private JLabel colorLabel;
    private ShapeType shapeType;
    private Color currentColor;
    private ColoredSquare coloredSquare;

    public InputPanel() {
        rectangleButton = new JButton("Rectangle");
        circleButton = new JButton("Circle");
        lineButton = new JButton("Line");
        colorButton = new JButton("Color...");
        penButton = new JButton("Pen");
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");

        filledBox = new JCheckBox("Filled", false);
        colorLabel = new JLabel("Current Color: ");

        shapeType = ShapeType.RECTANGLE;
        currentColor = Color.BLUE;

        coloredSquare = new ColoredSquare(currentColor);

        rectangleButton.addActionListener(e -> {
            shapeType = ShapeType.RECTANGLE;
        });

        circleButton.addActionListener(e -> {
            shapeType = ShapeType.CIRCLE;
        });

        lineButton.addActionListener(e -> {
            shapeType = ShapeType.LINE;
        });

        colorButton.addActionListener(e -> {
            currentColor = JColorChooser.showDialog(this, "Choose a Color", currentColor);
            coloredSquare.updateColor(currentColor);
        });

        penButton.addActionListener(e -> {
            shapeType = ShapeType.PEN;
        });

        quitButton.addActionListener(e ->{
            System.exit(0);
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 250, 15));

        rectangleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        circleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        lineButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        filledBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        penButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        coloredSquare.setAlignmentX(Component.CENTER_ALIGNMENT);
        clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension dim = new Dimension(5, 5);

        add(rectangleButton);
        add(new Box.Filler(dim, dim, dim));

        add(circleButton);
        add(new Box.Filler(dim, dim, dim));

        add(lineButton);
        add(new Box.Filler(dim, dim, dim));

        add(filledBox);
        add(new Box.Filler(dim, dim, dim));

        add(colorButton);
        add(new Box.Filler(dim, dim, dim));

        add(colorLabel);
        add(new Box.Filler(dim, dim, dim));

        add(coloredSquare);
        add(new Box.Filler(dim, dim, dim));

        add(penButton);
        add(new Box.Filler(dim, dim, dim));

        add(clearButton);
        add(new Box.Filler(dim, dim, dim));

        add(quitButton);
    }

    public JButton getClearButton(){
        return clearButton;
    }

    public boolean isFilledBoxChecked() {
        return filledBox.isSelected();
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }
}
