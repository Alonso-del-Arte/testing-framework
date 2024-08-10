/*
 * Copyright (C) 2021 Alonso del Arte
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
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

/**
 * A subclass of <code>Graphics</code> that does little besides log calls.
 * Though I haven't decided if I'll actually use any of the usual logging since
 * order of class loading is not a concern here. So I might opt for a more
 * direct way of querying what calls have been made.
 * <p>
 * I'm not sure if this is just reproducing <code>DebugGraphics</code>.
 * </p>
 * 
 * @author Alonso del Arte
 * @since 1.1
 */
public class MockGraphics extends Graphics {

    private static ArrayList<GraphicsCommandRecord> commandsList = new ArrayList<>();

    private Color currColor;

    private Font currFont;

    private FontMetrics currFontMetrics;

    private static final Font FIRST_FONT = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()[0];

    @Override
    public void clearRect(int x, int y, int width, int height) {
        // TODO: Write tests for this
    }

    @Override
    public void clipRect(int x, int y, int width, int height) {
        // TODO: Write tests for this
    }

    @Override
    public void copyArea(int x, int y, int width, int height, int dx, int dy) {
        // TODO: Write tests for this
    }

    @Override
    public Graphics create() {
        throw new UnsupportedOperationException("Sorry, not implemented");
    }

    @Override
    public void dispose() {
        // TODO: Write tests for this
    }

    @Override
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        // TODO: Write tests for this
    }

    @Override
    public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
        return true;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
        return true;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
        return true;
    }

    @Override
    public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
        return true;
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
            Color bgcolor, ImageObserver observer) {
        return true;
    }

    @Override
    public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2,
            ImageObserver observer) {
        return true;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        // TODO: Write tests for this
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        // TODO: Write tests for this
    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        // TODO: Write tests for this
    }

    @Override
    public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
        // TODO: Write tests for this
    }

    @Override
    public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
// TODO: Write tests for this
    }

    @Override
    public void drawString(AttributedCharacterIterator iterator, int x, int y) {
// TODO: Write tests for this
    }

    @Override
    public void drawString(String str, int x, int y) {
// TODO: Write tests for this
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
// TODO: Write tests for this
    }

    @Override
    public void fillOval(int x, int y, int width, int height) {
// TODO: Write tests for this
    }

    @Override
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
// TODO: Write tests for this
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
// TODO: Write tests for this
    }

    @Override
    public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
// TODO: Write tests for this
    }

// TODO: Write tests for this
    @Override
    public Shape getClip() {
        return null;
    }

// TODO: Write tests for this
    @Override
    public Rectangle getClipBounds() {
        return null;
    }

    @Override
    public Color getColor() {
        return Color.BLACK;// this.currColor;
    }

    @Override
    public Font getFont() {
        return null;// this.currFont;
    }

// TODO: Write tests for this
    @Override
    public FontMetrics getFontMetrics(Font f) {
        return null;
    }

    @Override
    public void setClip(int x, int y, int width, int height) {
// TODO: Write tests for this
    }

    @Override
    public void setClip(Shape clip) {
// TODO: Write tests for this
    }

    /**
     * Changes the color to be used for most drawing and filling operations.
     * 
     * @param c The new color. For example, <code>Color.GREEN</code>. Not required
     *          to be different from the current color.
     */
    @Override
    public void setColor(Color c) {
        this.currColor = c;
    }

    @Override
    public void setFont(Font f) {
// TODO: Write tests for this
    }

    @Override
    public void setPaintMode() {
// TODO: Write tests for this
    }

    @Override
    public void setXORMode(Color c) {
// TODO: Write tests for this
    }

    @Override
    public void translate(int x, int y) {
// TODO: Write tests for this
    }

    /**
     * Constructor. Note that this is the only public constructor. Color is black
     * and font is whatever font happens to be listed first by AWT's
     * <code>getAllFonts()</code>.
     */
    public MockGraphics() {
        this(Color.BLACK, FIRST_FONT);
    }

    /**
     * Constructor. Note that this constructor is package private. The font is
     * inferred to be whatever happens to be listed first by AWT's
     * <code>getAllFonts()</code>.
     * 
     * @param color The color to be returned by the first call to
     *              {@link #getColor()}, provided no call to
     *              {@link #setColor(java.awt.Color)} has been made before then.
     */
    MockGraphics(Color color) {
        this(color, FIRST_FONT);
    }

    /**
     * Constructor. Note that this constructor is package private. The color is
     * inferred to be black.
     * 
     * @param font The font to be returned by {@link #getFont()}, provided no call
     *             to {@link #setFont(java.awt.Font)} has been made before then.
     */
    MockGraphics(Font font) {
        this(Color.BLACK, font);
    }

    /**
     * Constructor. Note that this constructor is package private.
     * 
     * @param color The color to be returned by the first call to
     *              {@link #getColor()}, provided no call to
     *              {@link #setColor(java.awt.Color)} has been made before then.
     * @param font  The font to be returned by {@link #getFont()}, provided no call
     *              to {@link #setFont(java.awt.Font)} has been made before then.
     * @throws NullPointerException If <code>color</code> is null.
     */
    MockGraphics(Color color, Font font) {
        if (color == null || font == null) {
            String excMsg = "Neither color nor font can be null";
            throw new NullPointerException(excMsg);
        }
        this.currColor = color;
        this.currFont = font;
    }

}
