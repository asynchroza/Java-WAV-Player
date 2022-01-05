package com.mihailbozhilov;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;



public class frame extends JFrame {

    static AtomicInteger trackToBePlayed = new AtomicInteger();

    frame(){
        JFrame frame = new JFrame(); //creating the JFrame
        this.setTitle("Music Player"); //name of the program
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit on close
        this.setSize(500,120); //setting the size
        this.setResizable(false); //non-res
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout); //set layout
        Container contentPane = frame.getContentPane();

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logo.png")));
        this.getContentPane().setBackground(new Color(0,0,0));


        JPanel btnsPanel = new JPanel();
        btnsPanel.setBackground(Color.black);
        btnsPanel.setPreferredSize(new Dimension(500,60));
        layout.putConstraint(SpringLayout.NORTH, btnsPanel, 0, SpringLayout.SOUTH, frame);
        this.add(btnsPanel);

        JButton playBtn = new JButton();
        JButton backBtn = new JButton();
        JButton forwardBtn = new JButton();
        playBtn.setPreferredSize(new Dimension(50,50));
        backBtn.setPreferredSize(new Dimension(50,50));
        forwardBtn.setPreferredSize(new Dimension(50,50));

        //setting the icons for the buttons
        Icon playBtnIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logos/play_stop_rollover.png")).getScaledInstance(50,50, Image.SCALE_SMOOTH));
        playBtn.setIcon(playBtnIcon);
        Icon backBtnIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logos/back_new.png")).getScaledInstance(50,50, Image.SCALE_SMOOTH));
        backBtn.setIcon(backBtnIcon);
        Icon forwardBtnIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logos/forward_new.png")).getScaledInstance(50,50, Image.SCALE_SMOOTH));
        forwardBtn.setIcon(forwardBtnIcon);


        playBtn.setBorderPainted(false);
        playBtn.setOpaque(false);
        playBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setOpaque(false);
        backBtn.setContentAreaFilled(false);
        forwardBtn.setBorderPainted(false);
        forwardBtn.setOpaque(false);
        forwardBtn.setContentAreaFilled(false);

        playBtn.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logos/play_stop_clicked.png")).getScaledInstance(50,50,Image.SCALE_SMOOTH)));
        backBtn.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logos/back_clicked.png")).getScaledInstance(50,50,Image.SCALE_SMOOTH)));
        forwardBtn.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logos/forward_clicked.png")).getScaledInstance(50,50,Image.SCALE_SMOOTH)));

        playBtn.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logos/play_stop_new.png")).getScaledInstance(50,50,Image.SCALE_SMOOTH)));
        backBtn.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logos/back_rollover.png")).getScaledInstance(50,50,Image.SCALE_SMOOTH)));
        forwardBtn.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/logos/forward_rollover.png")).getScaledInstance(50,50,Image.SCALE_SMOOTH)));



        btnsPanel.add(backBtn);
        btnsPanel.add(playBtn);
        btnsPanel.add(forwardBtn);

        JPanel otherFunctions = new JPanel();
        otherFunctions.setBackground(Color.black);
        otherFunctions.setPreferredSize(new Dimension(60, 30));
        layout.putConstraint(SpringLayout.NORTH, otherFunctions, 5, SpringLayout.SOUTH, btnsPanel);
        layout.putConstraint(SpringLayout.WEST, otherFunctions, 440, SpringLayout.SOUTH, contentPane);
        this.add(otherFunctions);


        JButton addToQueue = new JButton();
        JButton addToFolder = new JButton();
        addToQueue.setPreferredSize(new Dimension(20,20));
        addToFolder.setPreferredSize(new Dimension(20,20));

        //otherBtns icons setting up

        addToQueue.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/otherIcons/addToQueue_mainIcon.png")).getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        addToFolder.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/otherIcons/importDIr_mainIcon.png")).getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        addToQueue.setBorderPainted(false);
        addToQueue.setOpaque(false);
        addToQueue.setContentAreaFilled(false);
        addToFolder.setBorderPainted(false);
        addToFolder.setOpaque(false);
        addToFolder.setContentAreaFilled(false);
        addToQueue.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/otherIcons/addToQueue_rolloverIcon.png")).getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        addToFolder.setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/otherIcons/importDIr_rolloverIcon.png")).getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        addToQueue.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/otherIcons/addToQueue_clicked.png")).getScaledInstance(20,20,Image.SCALE_SMOOTH)));
        addToFolder.setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/otherIcons/addToFolder_clicked.png")).getScaledInstance(20,20,Image.SCALE_SMOOTH)));

        fileChooser fc = new fileChooser();

        addToFolder.addActionListener(e->fc.fc());
        addToQueue.addActionListener(e-> fc.addToQueue(fc.getDirFile()));

        filesPlayer fp = new filesPlayer();

        AtomicBoolean playerHasBeenInit = new AtomicBoolean(false);


        audioPlayer ap = new audioPlayer();

        Runnable r = () -> ap.getSetInLineChecker(false);



        playBtn.addActionListener(e->{

            ap.getSetInLineChecker(true);
            if(filesPlayer.mp3_list.size() == 0){
                fc.addToQueue(fc.getDirFile());
            } else {
                if (!playerHasBeenInit.get()) {
                    fp.initPlayer(filesPlayer.mp3_list, trackToBePlayed.get());
                    trackToBePlayed.getAndIncrement();
                    playerHasBeenInit.set(true);
                } else {
                    ap.getSetInLineChecker(true);
                    fp.playStopPlayer();
                }
            }
            provokeLineChecker(r);
        });

        forwardBtn.addActionListener(e -> {

            ap.getSetInLineChecker(true);
            if(fp.mp3HasNext(trackToBePlayed)){
                try{
                    fp.removePlayer();
                    fp.initPlayer(filesPlayer.mp3_list, trackToBePlayed.get());
                    trackToBePlayed.getAndIncrement();

                    playerHasBeenInit.set(true);
                } catch(Exception e1){
                    System.out.println(e1.toString());
                }
            } else {
                fp.removePlayer();
                trackToBePlayed.set(0);
                fp.initPlayer(filesPlayer.mp3_list,trackToBePlayed.get());
                trackToBePlayed.getAndIncrement();
                playerHasBeenInit.set(true);
            }
            provokeLineChecker(r);
        });

        backBtn.addActionListener(e->{

            ap.getSetInLineChecker(true);
            if(fp.mp3HasPrev(trackToBePlayed)){
                try{
                    fp.removePlayer();
                    trackToBePlayed.getAndDecrement();
                    fp.initPlayer(filesPlayer.mp3_list,trackToBePlayed.get() - 1);

                    playerHasBeenInit.set(true);

                } catch(Exception e2){
                    System.out.println(e2.toString());
                }
            } else {
                int list_length = filesPlayer.mp3_list.size();
                fp.removePlayer();
                fp.initPlayer(filesPlayer.mp3_list, list_length-1);
                trackToBePlayed.set(list_length);
                playerHasBeenInit.set(true);
            }
            provokeLineChecker(r);
        });

        otherFunctions.add(addToQueue);
        otherFunctions.add(addToFolder);

        this.setVisible(true);
    }

    public void provokeLineChecker(Runnable r){
            try{
                Thread t = new Thread(r);
                t.start();
                Thread.sleep(50);

            } catch(Exception e2){
                System.out.println(e2.toString());
            }
        }
    }


