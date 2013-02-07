package nl.hanze.t12.mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class FieldView extends JPanel
{
    private final int GRID_VIEW_SCALING_FACTOR = 6;

    private int gridWidth, gridHeight;
    private int xScale, yScale;
    Dimension size;
    private Graphics g;
    private Image fieldImage;

    /**
     * Create a new FieldView component.
     */
    public FieldView(int height, int width, Model model)
    {
//        super(model);
    	gridHeight = height;
        gridWidth = width;
        size = new Dimension(0, 0);
    }

    /**
     * Tell the GUI manager how big we would like to be.
     */
    public Dimension getPreferredSize()
    {
        return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                             gridHeight * GRID_VIEW_SCALING_FACTOR);
    }

    /**
     * Prepare for a new round of painting. Since the component
     * may be resized, compute the scaling factor again.
     */
    public void preparePaint()
    {
        if(! size.equals(getSize())) {  // if the size has changed...
            size = getSize();
            fieldImage = this.createImage(size.width, size.height);
            g = fieldImage.getGraphics();

            xScale = size.width / gridWidth;
            if(xScale < 1) {
                xScale = GRID_VIEW_SCALING_FACTOR;
            }
            yScale = size.height / gridHeight;
            if(yScale < 1) {
                yScale = GRID_VIEW_SCALING_FACTOR;
            }
        }
    }
    
    /**
     * Paint on grid location on this field in a given color.
     */
    public void drawMark(int x, int y, Color color)
    {
    	g.setColor(color);
        g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);
    }

    /**
     * The field view component needs to be redisplayed. Copy the
     * internal image to screen.
     */
    public void paintComponent(Graphics g)
    {
        if(fieldImage != null) {
            Dimension currentSize = getSize();
            if(size.equals(currentSize)) {
                g.drawImage(fieldImage, 0, 0, null);
            }
            else {
                // Rescale the previous image.
                g.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
            }
        }
    }
}
