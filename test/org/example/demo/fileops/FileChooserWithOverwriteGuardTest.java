package org.example.demo.fileops;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import static testframe.api.Asserters.*;
import testframe.api.Test;

public class FileChooserWithOverwriteGuardTest {

    /**
     * Extends {@link FileChooserWithOverwriteGuard} so that the confirmation 
     * dialog box response can be mocked. Then the user running the tests 
     * doesn't have to watch out and click on some dialog box that comes up. 
     * Only {@link FileChooserWithOverwriteGuard#getConfirmationResponse()}, 
     * <code>JFileChooser.cancelSelection()</code> and 
     * <code>JFileChooser.showSaveDialog()</code> are overridden.
     */
    private static class MockFileChooser extends FileChooserWithOverwriteGuard {
        
        private static final long serialVersionUID = 4552602532748197897L;
        
        static final int KEEP_SAVE_DIALOG_UP = 127;
        
        private final int mockResponse;
        
        boolean mockResponseGiven = false;
        
        /**
         * The return value. This does not hide <code>JFileChooser</code>'s 
         * <code>returnValue</code> field because that one's private, and thus 
         * inaccessible to this class. If that was protected instead, this field 
         * would be unnecessary.
         */
        int returnValue = KEEP_SAVE_DIALOG_UP;
        
        /**
         * Overrides the superclass so that a Yes, No or Cancel response is 
         * given without a <code>JOptionPane</code> coming up. This way we don't 
         * have to worry about how to click on that through the test program.
         * @return Whatever response code was assigned through the constructor.
         */
        @Override
        int getConfirmationResponse() {
            switch (this.mockResponse) {
                case JOptionPane.YES_OPTION:
                    this.returnValue = JFileChooser.APPROVE_OPTION;
                    break;
                case JOptionPane.NO_OPTION:
                    this.returnValue = KEEP_SAVE_DIALOG_UP;
                    break;
                case JOptionPane.CANCEL_OPTION:
                case JOptionPane.CLOSED_OPTION:
                    this.returnValue = JFileChooser.CANCEL_OPTION;
                    break;
                default:
                    this.returnValue = JFileChooser.ERROR_OPTION;
            }
            this.mockResponseGiven = true;
            return this.mockResponse;
        }
        
        /**
         * Indicates whether or not the mock response has been given.
         * @return False if {@link #getConfirmationResponse()} has never been 
         * called on this instance, true if it has been called even just once.
         */
        boolean mockResponseHasBeenGiven() {
            return this.mockResponseGiven;
        }
        
        @Override
        public void cancelSelection() {
            super.cancelSelection();
            this.returnValue = JFileChooser.CANCEL_OPTION;
        }
        
        /**
         * Does not actually show a save dialog. But it does call 
         * <code>approveSelection()</code> and hopefully that does check whether 
         * the file exists already or not.
         * @param parent Should normally be an actual parent component, but for 
         * testing purposes should perhaps preferably be null.
         * @return A <code>JFileChooser</code> option constant according to the 
         * chosen <code>JOptionPane</code> response code.
         */
        @Override
        public int showSaveDialog(Component parent) {
            this.approveSelection();
            return this.returnValue;
        }

        /**
         * Sole constructor. The superclass constructor is called implicitly. 
         * This constructor has nothing to add other than setting the response 
         * code. Even though one mock file chooser can be used for multiple 
         * files, it is perhaps best to construct a new mock file chooser for 
         * each test.
         * @param code The response code this mock file chooser's {@link
         * #getConfirmationResponse()} will always return.
         */
        MockFileChooser(int code) {
            this.mockResponse = code;
        }
        
    }
    
    /**
     * This is like the previous mock file chooser, but meant to be run in a 
     * thread. Use this to test what happens when the user says no to 
     * overwriting an existing file.
     */
    private static class ThreadableMockFileChooser extends MockFileChooser 
            implements Runnable {
        
        private static final long serialVersionUID = 4552602532748197898L;
        
        volatile Object result;
        
        /**
         * Simulates showing a save dialog that checks if the file already 
         * exists. If that's the case, this simulates that the dialog stays up 
         * until either a yes or cancel response is given.
         */
        @Override
        public void run() {
            int response = this.showSaveDialog(null);
            this.result = "Response is " + response;
            System.out.println("Response has been set");
        }
        
        /**
         * Does not actually show a save dialog. But it does call 
         * <code>approveSelection()</code> and hopefully that does check whether 
         * the file exists already or not. This will simulate that the dialog 
         * stays up until the user gives either a yes or cancel response.
         * @param parent Should normally be an actual parent component, but for 
         * testing purposes should perhaps preferably be null.
         * @return A <code>JFileChooser</code> option constant according to the 
         * chosen <code>JOptionPane</code> response code.
         */
        @Override
        public int showSaveDialog(Component parent) {
            while (this.returnValue == MockFileChooser.KEEP_SAVE_DIALOG_UP) {
                this.approveSelection();
            }
            return this.returnValue;
        }

        /**
         * Sole constructor. The superclass constructor is called implicitly. 
         * This constructor has nothing to add other than passing up the 
         * response code to mock up to the superclass constructor.
         * @param code The response code this mock file chooser's {@link
         * #getConfirmationResponse()} will always return.
         */
        ThreadableMockFileChooser(Object resultHolder, int code) {
            super(code);
            this.result = resultHolder;
        }
        
    }
    
}
