package com.mihailbozhilov;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class fileChooser {
    public File fileDirectory;

    public void fc(){
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showSaveDialog(null);
        this.fileDirectory = fc.getSelectedFile();
    }

    errorMessages errMsg = new errorMessages();


    public File getDirFile(){return fileDirectory;}

    public void addToQueue(File dir){
        if(errMsg.tempDir == null) {
            System.out.println("Current Directory is not yet set!");
        } else {
            if(fileDirectory == null)
            {
            dir = errMsg.getTempDir();
            } else {
                dir = fileDirectory;
            }
        }

        if(dir == null)
        {
            errMsg.noDirectory();
        }
        else
        {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV Files", "wav");
            JFileChooser atq_FC = new JFileChooser();
            atq_FC.setFileFilter(filter);
            atq_FC.setCurrentDirectory(dir);
            atq_FC.setFileSelectionMode(JFileChooser.FILES_ONLY);
            atq_FC.showSaveDialog(null);
            filesPlayer files_player = new filesPlayer();
            System.out.println("Adding to queue: " + atq_FC.getSelectedFile());
            files_player.addToList(atq_FC.getSelectedFile());
        }
    }

}
