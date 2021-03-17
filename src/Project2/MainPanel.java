package Project2;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author edsonpaulo
 */
public class MainPanel extends JPanel implements Runnable, ChangeListener,
                                                 ActionListener
{

    private Ball ball;
    private JSlider ballSizeControl;
    private JSlider ballVelocityControl;
    private JButton ballColorControl;
    private JPanel tools;

    public MainPanel ()
    {
        setLayout ( new BorderLayout () );
        initComponents ();
        addComponents ();

        Thread mainThread = new Thread ( this );
        mainThread.start ();
    }

    public void initComponents ()
    {
        ball = new Ball ();
        tools = new JPanel ();
        ballSizeControl = new JSlider ( 50, 700, 50 );
        ballSizeControl.addChangeListener ( this );
        ballVelocityControl = new JSlider ( 1, 20, 1 );
        ballVelocityControl.addChangeListener ( this );
        ballColorControl = new JButton ( "Change Ball Color" );
        ballColorControl.addActionListener ( this );
    }

    public void addComponents ()
    {
        tools.add ( new JLabel ( "Ball Size: " ) );
        tools.add ( ballSizeControl );
        tools.add ( new JLabel ( " Ball Color: " ) );
        tools.add ( ballColorControl );
        tools.add ( new JLabel ( " Ball Velocity: " ) );
        tools.add ( ballVelocityControl );
        this.add ( tools, BorderLayout.NORTH );
    }

    @Override
    public void run ()
    {
        while ( true )
            try
            {
                repaint ();
                Thread.sleep ( 8 );
            }
            catch ( InterruptedException ex )
            {
                Logger.getLogger ( MainPanel.class.getName () ).log (
                        Level.SEVERE, null, ex );
            }
    }

    @Override
    public void paintComponent ( Graphics g )
    {
        super.paintComponent ( g );
        Graphics2D g2D = ( Graphics2D ) g.create ();

        g2D.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                               RenderingHints.VALUE_ANTIALIAS_ON );
        ball.drawBall ( g2D );
    }

    @Override
    public void stateChanged ( ChangeEvent e )
    {
        if ( e.getSource () == ballSizeControl )
            this.ball.setSize ( ballSizeControl.
                    getValue () );
        else if ( e.getSource () == ballVelocityControl )
            this.ball.setVelocity ( ballVelocityControl.
                    getValue () );
        repaint ();

    }

    @Override
    public void actionPerformed ( ActionEvent e )
    {
        if ( e.getSource () == ballColorControl )
        {
            this.ball.setColor ( JColorChooser.showDialog ( this,
                                                            "Select a color",
                                                            ball.getColor () ) );
            repaint ();
        }
    }
}
