package com.mihailbozhilov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;

public class errorMessages {

    public File tempDir;

    public File getTempDir(){
        return tempDir;
    }

    public void noDirectory(){
        JFrame exception_frame = new JFrame();
        exception_frame.setTitle("Error");
        exception_frame.setSize(300, 100);
        exception_frame.setResizable(false);
        SpringLayout layout = new SpringLayout();
        exception_frame.setLayout(layout);
        exception_frame.getContentPane();
        exception_frame.setVisible(true);


        JButton button = new JButton("choose");
        JLabel label = new JLabel("Please, choose a directory!");
        label.setPreferredSize(new Dimension(200,50));
        layout.putConstraint(SpringLayout.NORTH, button, 0,SpringLayout.SOUTH, label);
        layout.putConstraint(SpringLayout.WEST, button, 95, SpringLayout.EAST, exception_frame);
        layout.putConstraint(SpringLayout.WEST, label, 50, SpringLayout.EAST, exception_frame);
        exception_frame.add(label);
        button.setPreferredSize(new Dimension(110,20));
        exception_frame.add(button);



        fileChooser fc = new fileChooser();
        button.addActionListener(e->{
            fc.fc();
            tempDir = fc.fileDirectory;
            System.out.println(tempDir);
            exception_frame.dispatchEvent(new WindowEvent(exception_frame,WindowEvent.WINDOW_CLOSING));
        });


    }
}
