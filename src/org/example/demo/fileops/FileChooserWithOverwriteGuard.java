package org.example.demo.fileops;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileChooserWithOverwriteGuard extends JFileChooser {

    private static final long serialVersionUID = 4552602532748197888L;

    private static final String QUESTION 
            = "Do you want to overwrite the existing file?";

    int getConfirmationResponse() {
        return JOptionPane.showConfirmDialog(this, QUESTION, 
                this.getSelectedFile().getName() + " already exists", 
                JOptionPane.YES_NO_CANCEL_OPTION);
    }

    // TODO: Write tests for this
    @Override
    public void approveSelection() {
        this.getConfirmationResponse();
        this.cancelSelection();
    }

}
