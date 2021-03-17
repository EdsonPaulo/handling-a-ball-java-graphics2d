package Project3;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author edsonpaulo
 */
public class MainPanel extends JPanel implements Runnable, ActionListener,
                                                 MouseListener
{

    private Ball selectedBall = null;
    private JButton btnKillBall;
    private JButton btnHideBall;
    private JButton btnShowBall;
    private JButton btnStopBall;
    private JPanel tools;
    private ArrayList<Ball> ballList;

    public MainPanel ()
    {
        setLayout ( new BorderLayout () );
        initComponents ();
        addComponents ();

        Thread mainThread = new Thread ( this );
        mainThread.start ();
    }

    public void hideBall ( Ball ball )
    {
        ball.setVisible ( false );
    }

    public void showBall ( Ball ball )
    {
        ball.setVisible ( true );
    }

    public void killBall ( Ball ball )
    {
        ballList.remove ( ball );
    }

    public void stopBall ( Ball ball )
    {
        if ( ball.getVelocity () > 0 )
            ball.setVelocity ( 0 );
        else
            ball.setVelocity ( 3 );
    }

    public void initComponents ()
    {
        tools = new JPanel ();

        btnKillBall = new JButton ( "MATAR" );
        btnKillBall.addActionListener ( this );

        btnHideBall = new JButton ( "ESCONDER" );
        btnHideBall.addActionListener ( this );

        btnShowBall = new JButton ( "MOSTRAR" );
        btnShowBall.addActionListener ( this );

        btnStopBall = new JButton ( "PARAR" );
        btnStopBall.addActionListener ( this );

        ballList = new ArrayList<> ();
        generateBall ( 4 );
    }

    public void addComponents ()
    {
        tools.add ( btnKillBall );
        tools.add ( btnHideBall );
        tools.add ( btnShowBall );
        tools.add ( btnStopBall );

        this.add ( tools, BorderLayout.NORTH );
    }

    private void generateBall ( int numBall )
    {
        int currentPosYBall = 100;

        while ( numBall > 0 )
        {
            Ball ball = new Ball ( currentPosYBall );
            ballList.add ( ball );
            currentPosYBall += 150;
            numBall--;
        }
    }

    private void getBallOnSelectedPoint ( Point clickedPoint )
    {
        ballList.forEach ( ( currentBall ) ->
        {
            Rectangle c = new Rectangle (
                    currentBall.getPositionX (),
                    currentBall.getPositionY (),
                    currentBall.getSize (),
                    currentBall.getSize () );
            Rectangle b = new Rectangle (
                    ( int ) clickedPoint.getX (),
                    ( int ) clickedPoint.getY (),
                    1, 1 );
            if ( b.intersects ( c ) ) this.selectedBall = currentBall;
        } );
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

        ballList.forEach ( ( currentBall ) ->
        {
            if ( currentBall.isVisible () )
                currentBall.drawBall ( g2D );
        } );
    }

    @Override
    public void actionPerformed ( ActionEvent e )
    {
        if ( e.getSource () == btnStopBall && selectedBall != null )
            stopBall ( selectedBall );
        else if ( e.getSource () == btnHideBall && selectedBall != null )
            hideBall ( selectedBall );
        else if ( e.getSource () == btnShowBall && selectedBall != null )
            showBall ( selectedBall );
        else if ( e.getSource () == btnKillBall && selectedBall != null )
            killBall ( selectedBall );
    }

    @Override
    public void mouseClicked ( MouseEvent e )
    {
        getBallOnSelectedPoint ( e.getPoint () );
    }

    @Override
    public void mousePressed ( MouseEvent e )
    {
    }

    @Override
    public void mouseReleased ( MouseEvent e )
    {
    }

    @Override
    public void mouseEntered ( MouseEvent e )
    {
    }

    @Override
    public void mouseExited ( MouseEvent e )
    {
    }
}
