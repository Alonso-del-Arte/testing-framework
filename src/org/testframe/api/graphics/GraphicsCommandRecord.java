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
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

/**
 * Base class for classes that record commands to <code>Graphics</code> 
 * instances. However, it's not abstract and may be used for a few commands.
 * @author Alonso del Arte
 * @since 1.1
 */
public class GraphicsCommandRecord {

    private final String commName;
    
    private final Color currColor;
    
    private final Font currFont;
    
    /**
     * Tells whether this record is of a getter command or not.
     * @return True if the command name starts with "get" (case sensitive), 
     * false otherwise.
     */
    public boolean isGetterCommand() {
        return this.commName.startsWith("get");
    }
    
    // TODO: Write tests for this
    public boolean isSetterCommand() {
        return false;
    }
    
    // TODO: Write tests for this
    public boolean isDrawingCommand() {
        return false;
    }
    
    // TODO: Write tests for this
    public boolean isFillingCommand() {
        return false;
    }
    
    /**
     * Retrieves the name of the command.
     * @return The name of the command that was passed to the constructor. For 
     * example, "setColor".
     */
    public String getCommandName() {
        return this.commName;
    }
    
    /**
     * Retrieves the current color.
     * @return The color that was passed to the constructor. For example, lime 
     * green.
     */
    public Color getCurrentColor() {
        return this.currColor;
    }
    
    /**
     * Retrieves the current font.
     * @return The font that was passed to the constructor. For example, 
     * 12-point Times New Roman italic.
     */
    public Font getCurrentFont() {
        return this.currFont;
    }
    
    // TODO: Write tests for this
    /**
     * Constructor. Use this for any of the following commands: 
     * <code>create()</code>, <code>dispose()</code>, <code>getClip()</code>, 
     * <code>getClipBounds()</code>, <code>getColor()</code>, 
     * <code>getFont()</code>, <code>getFontMetrics()</code>, 
     * <code>setColor()</code>, <code>setFont()</code>, 
     * <code>setPaintMode()</code>, <code>setXORMode()</code>.
     * @param name The name of the command. For example, "create". Must not be 
     * null.
     * @param color The current color, the one that <code>getColor()</code> from 
     * the <code>Graphics</code> instance would return. However, when recording 
     * a <code>setColor()</code> command, send the new color that is being set. 
     * Must not be null.
     * @param font The current font, the one that <code>getFont()</code> from 
     * the <code>Graphics</code> instance would return. However, when recording 
     * a <code>setFont()</code> command, send the new font that is being set. 
     * Must not be null.
     * @throws NullPointerException If any of the parameters is null.
     */
    public GraphicsCommandRecord(String name, Color color, Font font) {
        if (name == null || color == null || font == null) {
            String excMsg = "Command name, color, font must not be null";
            throw new NullPointerException(excMsg);
        }
        this.commName = name;
        this.currColor = color;
        this.currFont = font;
    }
    
    /**
     * Records a command involving a {@code Shape} parameter.
     */
    public static class WithShape extends GraphicsCommandRecord {
        
        private final Shape commShape;
        
        /**
         * Retrieves the shape that this record was instantiated with.
         * @return The shape that this record was instantiated with.
         */
        public Shape getShape() {
            return this.commShape;
        }
        
        /**
         * Constructor. Use this for either {@code getClip()} or the version of 
         * the {@link setClip()} command that takes a {@code Shape} object.
         * @param name The name of the command. For example, "setClip". Must not 
         * be null.
         * @param color The current color. For example, {@code Color.BLACK}. 
         * Must not be null.
         * @param font The current font. For example, 12-point Arial. Must not 
         * be null.
         * @param shape The shape, such as for example an instance of {@code 
         * RoundRectangle2D}. Must not be null.
         * @throws NullPointerException If any of the parameters is null.
         */
        public WithShape(String name, Color color, Font font, Shape shape) {
            super(name, color, font);
            if (shape == null) {
                String excMsg = "Shape must not be null";
                throw new NullPointerException(excMsg);
            }
            this.commShape = shape;
        }
        
    }
    
    /**
     * Records a <code>Graphics</code> command with <i>x</i> and <i>y</i> 
     * parameters.
     */
    public static class WithXAndY extends GraphicsCommandRecord {
        
        private final int commX, commY;
        
        /**
         * Retrieves the <i>x</i> and <i>y</i> parameters that this record was 
         * constructed with.
         * @return The <i>x</i> and <i>y</i> parameters bundled into a 
         * <code>Point</code> object.
         */
        public Point getPoint() {
            return new Point(this.commX, this.commY);
        }
        
        /**
         * Constructor. Use this for the <code>translate()</code> command.
         * @param name The name of the command. For example, "translate". Must 
         * not be null.
         * @param color The current color. For example, <code>Color.RED</code>. 
         * Must not be null.
         * @param font The current font. For example, 12-point Courier. Must not 
         * be null.
         * @param x The <code>x</code> parameter.
         * @param y The <code>y</code> parameter.
         * @throws NullPointerException If <code>name</code>, <code>color</code> 
         * or <code>font</code> is null.
         */
        public WithXAndY(String name, Color color, Font font, int x, int y) {
            super(name, color, font);
            this.commX = x;
            this.commY = y;
        }
        
    }
    
    /**
     * Records a <code>drawString()</code> command to a <code>Graphics</code> 
     * instance. The command includes <i>x</i> and <i>y</i> parameters.
     * <p>Maybe this can also be used for <code>drawBytes()</code> or 
     * <code>drawChars()</code>. But given that the former is not recommended on 
     * account of being limited to ASCII characters and the latter takes a 
     * <code>char</code> array that can easily be converted to a 
     * <code>String</code> instance, I just don't see much need for recording 
     * either of those two commands like I see a need to record 
     * <code>drawString()</code> commands.</p>
     */
    public static class WithString extends WithXAndY {
        
        private final String commText;
        
        /**
         * Retrieves the text this object was constructed with.
         * @return The text this object was constructed with.
         */
        public String getText() {
            return this.commText;
        }
        
        /**
         * Constructor. Use this for the version of <code>drawString()</code> 
         * that a <code>String</code> parameter.
         * @param name The name of the command. For example, "drawString". Must 
         * not be null.
         * @param color The current color. For example, 
         * <code>Color.YELLOW</code>. Must not be null.
         * @param font The current font. For example, 12-point Georgia. Must not 
         * be null.
         * @param x The <code>x</code> parameter.
         * @param y The <code>y</code> parameter.
         * @param text The text to write to the <code>Graphics</code> context. 
         * Must not be null.
         * @throws NullPointerException If <code>name</code>, 
         * <code>color</code>, <code>font</code> or <code>text</code> is null.
         */
        public WithString(String name, Color color, Font font, int x, int y, 
                String text) {
            super(name, color, font, x, y);
            if (text == null) {
                String excMsg = "Iterator must not be null";
                throw new NullPointerException(excMsg);
            }
            this.commText = text;
        }
        
    }
    
    public static class WithAttributedCharacterIterator extends WithXAndY {
        
//        private final AttributedCharacterIterator commCharIter;
        
        // TODO: Write test for this
        public AttributedCharacterIterator getCharacterIterator() {
            return null;
        }
        
        // TODO: Write tests for this
        /**
         * Constructor. Use this for the version of <code>drawString()</code> 
         * that takes an <code>AttributedCharacterIterator</code>.
         * @param name The name of the command. For example, "create". Must not 
         * be null.
         * @param color The current color, the one that <code>getColor()</code> 
         * from the <code>Graphics</code> instance would return. Must not be 
         * null.
         * @param font The current font, the one that <code>getFont()</code> 
         * from the <code>Graphics</code> instance would return. However, when 
         * recording a <code>setFont()</code> command, send the new font that is 
         * being set. Must not be null.
         * @param x The <code>x</code> parameter.
         * @param y The <code>y</code> parameter.
         * @param iterator The character iterator with the attributes.
         * @throws NullPointerException If <code>name</code>, 
         * <code>color</code>, <code>font</code> or <code>iterator</code> is 
         * null.
         */
        public WithAttributedCharacterIterator(String name, Color color, 
                Font font, int x, int y, AttributedCharacterIterator iterator) {
            super(name, color, font, x, y);
            if (iterator == null) {
                String excMsg = "Iterator must not be null";
                throw new NullPointerException(excMsg);
            }
//            this.commCharIter = iterator;
        }
    }
    
    /**
     * Records a <code>Graphics</code> command that takes four integer 
     * parameters. The first two integer parameters are <i>x</i> and <i>y</i>. 
     * The second two parameters can either be another <i>x</i> and <i>y</i> 
     * pair or width and height parameters for a dimension.
     */
    public static class WithSecondXAndY extends WithXAndY {
        
        private final int comm2ndX, comm2ndY;
        
        /**
         * Retrieves the second point. This is meant for the {@code drawLine()} 
         * command.
         * @return The second point that was passed to the constructor. For 
         * example, (477, 888).
         */
        public Point getSecondPoint() {
            return new Point(this.comm2ndX, this.comm2ndY);
        }
        
        /**
         * Retrieves the dimension. This is meant for the {@code drawOval()} and 
         * {@code drawRect()} commands.
         * @return The dimension that was passed to the constructor. For 
         * example, (477, 888).
         */
        public Dimension getDimension() {
            return new Dimension(this.comm2ndX, this.comm2ndY);
        }
        
        /**
         * Constructor. Use this for any of the following commands: 
         * {@code drawLine()}, {@code drawOval()}, {@code drawRect()}, and the 
         * version of {@code setClip()} that takes four integers.
         * @param name The name of the command. For example, "drawLine". Must 
         * not be null.
         * @param color The current color. For example, {@code Color.BLUE}. Must 
         * not be null.
         * @param font The current font. For example, 12-point Zapf Dingbats. 
         * Must not be null.
         * @param x The {@code x1} parameter.
         * @param y The {@code y1} parameter.
         * @param dx The {@code x2} or {@code width} parameter.
         * @param dy The {@code y2} or {@code height} parameter.
         * @throws NullPointerException If {@code name}, {@code color} or {@code 
         * font} is null.
         */
        public WithSecondXAndY(String name, Color color, Font font, int x, 
                int y, int dx, int dy) {
            super(name, color, font, x, y);
            this.comm2ndX = dx;
            this.comm2ndY = dy;
        }
        
    }
    
    public static class WithImage extends WithSecondXAndY {
        
        private final Image commImage;
        
        private final ImageObserver commObserver;
        
        private final Color commBackgroundColor;
        
        /**
         * Retrieves the image that was passed to the constructor.
         * @return The image that was passed to the constructor.
         */
        public Image getImage() {
            return this.commImage;
        }
        
        /**
         * Retrieves the observer that was passed to the constructor.
         * @return The observer that was passed to the constructor.
         */
        public ImageObserver getObserver() {
            return this.commObserver;
        }
        
        /**
         * Retrieves the background color that was passed to the constructor.
         * @return The background color that was passed to the constructor. For 
         * example, light gray.
         */
        public Color getBackgroundColor() {
            return this.commBackgroundColor;
        }
        
        // TODO: Write tests for this
        /**
         * Primary constructor.
         * @param name
         * @param color
         * @param font
         * @param image The image to be drawn. Must not be null.
         * @param observer The observer to keep track of the image. Must not be 
         * null.
         * @param bgColor The background color. May be null.
         * @param x
         * @param y
         * @param dx
         * @param dy
         * @param sx1
         * @param sy1
         * @param sx2
         * @param sy2 
         */
        public WithImage(String name, Color color, Font font, Image image, 
                ImageObserver observer, Color bgColor, int x, int y, int dx, 
                int dy, int sx1, int sy1, int sx2, int sy2) {
            super(name, color, font, x, y, dx, dy);
            if (image == null || observer == null) {
                String excMsg = "Image, observer must not be null";
                throw new NullPointerException(excMsg);
            }
            this.commImage = image;
            this.commObserver = observer;
            this.commBackgroundColor = bgColor;
        }
        
    }
    
}
