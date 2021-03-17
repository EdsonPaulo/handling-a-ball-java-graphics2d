package Project1;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author EdsonPaulo
 */
public class Ball
{

    private int size;
    private Color color;

    public Ball ()
    {
        size = 100;
        color = Color.RED;
    }

    public Ball ( Color color, int size )
    {
        this.color = color;
        this.size = size;
    }

    public Ball ( Graphics2D g2D, Color color, int size, int x, int y )
    {
        this.color = color;
        this.size = size;
        drawBall ( g2D, x, y );
    }

    public void drawBall ( Graphics2D g2D, int x, int y )
    {
        g2D.setColor ( color );
        g2D.fillOval ( x, y, size, size );
    }

    public int getSize ()
    {
        return size;
    }

    public void setSize ( int size )
    {
        this.size = size;
    }

    public Color getColor ()
    {
        return color;
    }

    public void setColor ( Color color )
    {
        this.color = color;
    }

}
