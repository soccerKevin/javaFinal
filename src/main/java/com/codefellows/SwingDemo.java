package com.codefellows;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingDemo{
  private JFrame mainFrame;
  private JPanel controlPanel;
  private JLabel headerLabel, statusLabel;

  public SwingDemo(){
    mainFrame = new JFrame("Code Fellows Swing Demo");
    mainFrame.setSize(500, 400);
    mainFrame.setLayout(new GridLayout(3, 1));

    mainFrame.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        System.exit(0);
      }
    });

    headerLabel = new JLabel("Control in action: Button", JLabel.CENTER);
    headerLabel.setFont(new Font("Ariel", Font.PLAIN, 40));

    statusLabel = new JLabel("", JLabel.CENTER);
    statusLabel.setSize(350, 100);

    mainFrame.add(headerLabel);
    mainFrame.add(statusLabel);
    mainFrame.add(controlPanel());
    mainFrame.setVisible(true);
  }

  public JPanel controlPanel(){
    controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());

    JButton okButton = new JButton("ok");
    okButton.setActionCommand("Ok");
    okButton.addActionListener(new ButtonClickListener());

    JButton submitButton = new JButton("submit");
    okButton.setActionCommand("Submit");
    okButton.addActionListener(new ButtonClickListener());

    JButton cancelButton = new JButton("cancel");
    okButton.setActionCommand("Cancel");
    okButton.addActionListener(e -> {
      statusLabel.setText("Cancelled");
    });

    controlPanel.add(okButton);
    controlPanel.add(submitButton);
    controlPanel.add(cancelButton);

    return controlPanel;
  }

  private class ButtonClickListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e){
      String command = e.getActionCommand();

      if(command.equals("Ok")){
        statusLabel.setText("Ok Button Clicked. YAY!");
      }else if(command.equals("Submit")){
        statusLabel.setText("Submitted");
      }else{
        statusLabel.setText("Something else");
      }
    }
  }
}
