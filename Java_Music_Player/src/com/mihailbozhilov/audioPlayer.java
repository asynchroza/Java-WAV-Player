package com.mihailbozhilov;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class audioPlayer {
    Long currentFrame;
    Clip clip;
    String status;

    AudioInputStream audioInputStream;

    public audioPlayer() {}

    public void audioPlayerBuilder() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
    audioInputStream = AudioSystem.getAudioInputStream(new File(filesPlayer.getFilePath).getAbsoluteFile());
    clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    clip.loop(0);
    }

    static boolean inLineChecker;

    Runnable r = new Runnable() {
        @Override
        public void run() {
            clip.addLineListener(event -> {
                try{
                    if(!inLineChecker){
                    rmvPlayerClip();
                    status = "Switching States";
                    }
                } catch (Exception e){
                    System.out.println(e.toString());;
                }
            });
        }
    };

    public void play(){
        if(!Objects.equals(status, "Switching States")){
            clip.start();
        }
            status = "Playing";
        try{
            Thread thread = new Thread(r);
            thread.start();
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public void pause(){
        if(status.equals("Paused")) {
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "Paused";
    }

    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
        clip.flush();
    }

    public void getSetInLineChecker(boolean state){
        inLineChecker = state;
    }

    public void rmvPlayerClip(){
        filesPlayer fp = new filesPlayer();
        try{
           clip.stop();
           clip.flush();
           if(fp.mp3HasNext(frame.trackToBePlayed)){
               int changedVar = frame.trackToBePlayed.get();
               filesPlayer.getFilePath = filesPlayer.mp3_list.get(changedVar).toString();
               audioPlayerBuilder();
               frame.trackToBePlayed.getAndIncrement();
           } else {
               filesPlayer.getFilePath = filesPlayer.mp3_list.get(0).toString();
               audioPlayerBuilder();
           }

        } catch(Exception e){
            System.out.println(e.toString());
        }
    } }


