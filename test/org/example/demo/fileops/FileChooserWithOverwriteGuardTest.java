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

import testframe.api.AfterAllTests;
import testframe.api.AfterEachTest;
import testframe.api.BeforeAllTests;
import testframe.api.Test;
import static testframe.api.Asserters.*;

public class FileChooserWithOverwriteGuardTest {

    private static final Random RANDOM = new Random();
    
    private static final String TEMP_DIR_PATH 
            = System.getProperty("java.io.tmpdir");
    
    private File createdByTest;
    
    private File createdBySetUpClass;
    
    /**
     * Sets up a text file to already exist prior to the tests. The file is 
     * placed in the user's temporary directory and given a filename consisting 
     * of "ExistingFile" followed by a pseudorandom number and the *.txt file 
     * extension.
     */
    @BeforeAllTests
    public void setUpClass() {
        int number = RANDOM.nextInt();
        String filename = TEMP_DIR_PATH + File.separatorChar + "ExistingFile" 
                + number + ".txt";
        this.createdBySetUpClass = new File(filename);
        try (FileWriter writer = new FileWriter(this.createdBySetUpClass)) {
            System.out.println("Successfully created " 
                    + this.createdBySetUpClass.getName());
            writer.write("This message was placed by setUpClass()");
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        String otherFilename = TEMP_DIR_PATH + File.separatorChar 
                + "PlaceholderToAvoidNPE.txt";
        this.createdByTest = new File(otherFilename);
    }
    
    /**
     * Test of the approveSelection procedure of the 
     * FileChooserWithOverwriteGuard class. If the file does not already exist, 
     * there should not be any confirmation to overwrite an existing file.
     */
    @Test
    public void testApproveSelection() {
        System.out.println("approveSelection");
        String filename = TEMP_DIR_PATH + File.separatorChar + "NewFile" 
                + RANDOM.nextInt() + ".txt";
        this.createdByTest = new File(filename);
        String preMsg = this.createdByTest.getName() 
                + " should not already exist";
        assert !this.createdByTest.exists() : preMsg;
        MockFileChooser chooser = new MockFileChooser(JOptionPane.YES_OPTION);
        chooser.setSelectedFile(this.createdByTest);
        int expected = JFileChooser.APPROVE_OPTION;
        int actual = chooser.showSaveDialog(null);
        if (expected == actual) {
            try (FileWriter writer = new FileWriter(this.createdByTest)) {
                writer.write("This message placed by testApproveSelection()");
            } catch (IOException ioe) {
                String errMsg = "IOException should not have occurred";
                throw new AssertionError(errMsg, ioe);
            }
        } else {
            String msg = "Save dialog should give JFileChooser.APPROVE_OPTION";
            fail(msg);
        }
        String msg = "No confirmation to overwrite needed for new file";
        assert !chooser.mockResponseHasBeenGiven() : msg;
    }
    
    /**
     * Another test of the approveSelection procedure of the 
     * FileChooserWithOverwriteGuard class. If the file already exists and the 
     * user asks that it not be overwritten, it should not be overwritten.
     */
    @Test
    public void testRejectSelectionForExistingFile() {
        String preMsg = "Existing file should already exist";
        assert this.createdBySetUpClass.exists() : preMsg;
        JFileChooser chooser = new MockFileChooser(JOptionPane.CANCEL_OPTION);
        chooser.setSelectedFile(this.createdBySetUpClass);
        int expected = JFileChooser.CANCEL_OPTION;
        int actual = chooser.showSaveDialog(null);
        if (actual == JFileChooser.APPROVE_OPTION) {
            try (FileWriter writer = new FileWriter(this.createdBySetUpClass)) {
                writer.write("This time, the user rejected the overwrite.\n");
                writer.write("This message should not have been written.");
                fail("Should not have been able to overwrite after cancel");
            } catch (IOException ioe) {
                String errMsg = "IOException should not have occurred";
                throw new AssertionError(errMsg, ioe);
            }
        }
        assertEquals(actual, expected);
    }
    
    /**
     * Another test of the approveSelection procedure of the 
     * FileChooserWithOverwriteGuard class. If the file already exists and the 
     * user confirms that it may be overwritten, it should be overwritten.
     */
    @Test
    public void testApproveSelectionForExistingFile() {
        String preMsg = "Existing file should already exist";
        assert this.createdBySetUpClass.exists() : preMsg;
        MockFileChooser chooser = new MockFileChooser(JOptionPane.YES_OPTION);
        chooser.setSelectedFile(this.createdBySetUpClass);
        int expected = JFileChooser.APPROVE_OPTION;
        int actual = chooser.showSaveDialog(null);
        if (expected == actual) {
            try (FileWriter writer = new FileWriter(this.createdBySetUpClass)) {
                writer.write("This time, the user approved the overwrite.\n");
                writer.write("This message placed by approve overwrite test");
            } catch (IOException ioe) {
                String errMsg = "IOException should not have occurred";
                throw new AssertionError(errMsg, ioe);
            }
        } else {
            String msg = "User-approved overwrite should have occurred";
            fail(msg);
        }
        String msg = "User should have been asked to approve overwrite";
        assert chooser.mockResponseHasBeenGiven() : msg;
    }
    
    private void reportFileContents(File file) {
        System.out.println(file.getName() + " has the following text:");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                System.out.println("\"" + scanner.nextLine() + "\"");
            }
        } catch (FileNotFoundException fnfe) {
            String excMsg = "Somehow can't find file " + file.getAbsolutePath();
            throw new RuntimeException(excMsg, fnfe);
        }
    }
    
    /**
     * Reports on the contents of the existing file, and the new file if it has 
     * been created by now.
     */
    @AfterEachTest
    public void tearDown() {
        if (this.createdByTest.exists()) {
            this.reportFileContents(this.createdByTest);
        }
        this.reportFileContents(this.createdBySetUpClass);
    }
    
    /**
     * Deletes the existing file and the new file. Also reports that those 
     * deletions have taken place.
     */
    @AfterAllTests
    public void tearDownClass() {
        this.createdByTest.delete();
        System.out.println("Successfully deleted " 
                + this.createdByTest.getAbsolutePath());
        this.createdBySetUpClass.delete();
        System.out.println("Successfully deleted " 
                + this.createdBySetUpClass.getAbsolutePath());
    }
        
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
        public void approveSelection() {
            this.returnValue = JFileChooser.APPROVE_OPTION;
            super.approveSelection();
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
