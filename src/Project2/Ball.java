package Project2;

import Utils.Constants;
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
    private int velocity = 5;
    private int directionX = 1;
    private int positionX = 50;

    public Ball ()
    {
        this.size = 100;
        this.color = Color.RED;
        this.velocity = 5;
        this.directionX = 1;
        this.positionX = 50;
    }

    public Ball ( Color color, int size )
    {
        this.color = color;
        this.size = size;
    }

    public void drawBall ( Graphics2D g2D )
    {
        g2D.setColor ( color );
        g2D.fillOval ( positionX, 100, size, size );
        keepOnScreenBounds ();
        moveBall ();
    }

    private void moveBall ()
    {
        positionX += velocity * directionX;
    }

    // keep ball in the screen bounds
    private void keepOnScreenBounds ()
    {

        if ( positionX <= 0
             || positionX + size >= Constants.WINDOW_WIDTH )
            invertDirectionX ();
    }

    public void invertDirectionX ()
    {
        this.directionX *= -1;
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

    public int getVelocity ()
    {
        return velocity;
    }

    public void setVelocity ( int velocity )
    {
        this.velocity = velocity;
    }

    public int getDirectionX ()
    {
        return directionX;
    }

    public void setDirectionX ( int directionX )
    {
        this.directionX = directionX;
    }

    public int getPositionX ()
    {
        return positionX;
    }

    public void setPositionX ( int positionX )
    {
        this.positionX = positionX;
    }

}
