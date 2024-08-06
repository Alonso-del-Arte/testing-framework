/*
 * Copyright (C) 2024 Alonso del Arte
 *
 * This program is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your option) any later 
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU General Public License along with 
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.testframe.api.graphics;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Random;

import org.testframe.api.Test;
import static org.testframe.api.Asserters.*;

public class GraphicsCommandRecordTest {

    private static final Font[] FONTS 
            = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

    private static final int TOTAL_NUMBER_OF_FONTS = FONTS.length;

    private static final Random RANDOM = new Random();

    @Test
    public void testGetCommandName() {
        System.out.println("getCommandName");
        String expected = "command" + RANDOM.nextInt();
        Color color = new Color(RANDOM.nextInt());
        Font font = FONTS[RANDOM.nextInt(TOTAL_NUMBER_OF_FONTS)];
        GraphicsCommandRecord record = new GraphicsCommandRecord(expected, 
                color, font);
        String actual = record.getCommandName();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testPrimaryConstructorRejectsNullCommandName() {
        Color color = new Color(RANDOM.nextInt());
        Font font = FONTS[0];
        try {
            GraphicsCommandRecord badRecord = new GraphicsCommandRecord(null, 
                    color, font);
            String msg = "Should not have been able to create " 
                    + badRecord.toString() + " with null command name";
            fail(msg);
        } catch (NullPointerException npe) {
            System.out.println("Null command name correctly caused NPE");
            System.out.println("\"" + npe.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception for null command name";
            fail(msg);
        }
    }
    
    @Test
    public void testPrimaryConstructorRejectsNullColor() {
        String cmd = "doSomething";
        Font font = FONTS[1];
        try {
            GraphicsCommandRecord badRecord = new GraphicsCommandRecord(cmd, 
                    null, font);
            String msg = "Should not have been able to create " 
                    + badRecord.toString() + " with null color";
            fail(msg);
        } catch (NullPointerException npe) {
            System.out.println("Null color correctly caused NPE");
            System.out.println("\"" + npe.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception for null color";
            fail(msg);
        }
    }
    
    @Test
    public void testPrimaryConstructorRejectsNullFont() {
        String cmd = "doSomething";
        Color color = new Color(RANDOM.nextInt());
        try {
            GraphicsCommandRecord badRecord = new GraphicsCommandRecord(cmd, 
                    color, null);
            String msg = "Should not have been able to create " 
                    + badRecord.toString() + " with null font";
            fail(msg);
        } catch (NullPointerException npe) {
            System.out.println("Null font correctly caused NPE");
            System.out.println("\"" + npe.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception for null font";
            fail(msg);
        }
    }
    
}
