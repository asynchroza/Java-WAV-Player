package com.mihailbozhilov;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class filesPlayer {
    static public List<File> mp3_list = new ArrayList<File>();
    static String getFilePath;
    public File fileToBePlayed;

    audioPlayer ap = new audioPlayer();

    public void addToList(File f) {
        if (mp3_list.size() == 0) {
            mp3_list.add(f);
        } else {
            mp3_list.add(f);
        }
    }

    boolean audioFilePlaying = false;

    public void initPlayer(List<File> fileList, int trackNumber) {
        try {
            fileToBePlayed = fileList.get(trackNumber);
            getFilePath = fileToBePlayed.toString();
            ap.audioPlayerBuilder();
            ap.play();
            audioFilePlaying = true;

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void playStopPlayer(){
        try{
            if(audioFilePlaying){
                ap.pause();
                audioFilePlaying = false;
            }
            else
            {
                ap.play();
                audioFilePlaying = true;
            }
        } catch(Exception e){
            System.out.println(e.toString());
        }
    };

    public void removePlayer(){
        try{
            ap.stop();
        } catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public boolean mp3HasNext(AtomicInteger count){
        return count.get() < mp3_list.size();
    }

    public boolean mp3HasPrev(AtomicInteger count) {
        try{
            File f = mp3_list.get(count.get() - 2);
            return true;
        } catch(Exception e){
            return false;
        }
    }}

