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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testframe.api.Test;
import static org.testframe.api.Asserters.*;

/**
 * Tests of the MockGraphics class.
 * @author Alonso del Arte
 */
public class MockGraphicsTest {

    private static final Font[] FONTS 
            = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();

    private static final int TOTAL_NUMBER_OF_FONTS = FONTS.length;

    private static final Random RANDOM = new Random();
    
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        Graphics g = new MockGraphics();
        Color expected = new Color(RANDOM.nextInt());
        g.setColor(expected);
        Color actual = g.getColor();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetFont() {
        System.out.println("getFont");
        Graphics g = new MockGraphics();
        Font expected = FONTS[RANDOM.nextInt(TOTAL_NUMBER_OF_FONTS)];
        g.setFont(expected);
        Font actual = g.getFont();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testPrimaryConstructor() {
        int rgb = RANDOM.nextInt();
        Color color = new Color(rgb);
        Font font = FONTS[FONTS.length - 1];
        Graphics g = new MockGraphics(color, font);
        assertEquals(color, g.getColor());
        assertEquals(font, g.getFont());
    }
      
    @Test
    public void testAuxiliaryInferredColorConstructor() {
        Font font = FONTS[RANDOM.nextInt(TOTAL_NUMBER_OF_FONTS)];
        Graphics g = new MockGraphics(font);
        Color expected = Color.BLACK;
        Color actual = g.getColor();
        assertEquals(expected, actual);
        assertEquals(font, g.getFont());
    }
    
    @Test
    public void testAuxiliaryInferredFontConstructor() {
        int rgb = RANDOM.nextInt();
        Color color = new Color(rgb);
        Graphics g = new MockGraphics(color);
        Font expected = FONTS[0];
        Font actual = g.getFont();
        assertEquals(expected, actual);
        assertEquals(color, g.getColor());
    }
    
    @Test
    public void testAuxiliaryInferredFontAndColorConstructor() {
        Graphics g = new MockGraphics();
        Color expColor = Color.BLACK;
        Color actColor = g.getColor();
        Font expFont = FONTS[0];
        Font actFont = g.getFont();
        assertEquals(expColor, actColor);
        assertEquals(expFont, actFont);
    }
    
    @Test
    public void testConstructorRejectsNullColor() {
        int index = RANDOM.nextInt(TOTAL_NUMBER_OF_FONTS);
        Font font = FONTS[index];
        String msg = "Constructor should reject null color";
        Throwable t = assertThrows(() -> {
            Graphics badGraphics = new MockGraphics(null, font);
            System.out.println("Should not have been able to create " 
                    + badGraphics.toString() + " with null color and " 
                    + font.getFontName());
        }, NullPointerException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isEmpty() : "Exception message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }
    
    @Test
    public void testConstructorRejectsNullFont() {
        int rgb = RANDOM.nextInt();
        Color color = new Color(rgb);
        String msg = "Constructor should reject null font";
        Throwable t = assertThrows(() -> {
            Graphics badGraphics = new MockGraphics(color, null);
            System.out.println("Should not have been able to create " 
                    + badGraphics.toString() + " with color " + color.toString()
                    + " and null font");
        }, NullPointerException.class, msg);
        String excMsg = t.getMessage();
        assert excMsg != null : "Exception message should not be null";
        assert !excMsg.isEmpty() : "Exception message should not be empty";
        System.out.println("\"" + excMsg + "\"");
    }
    
}
