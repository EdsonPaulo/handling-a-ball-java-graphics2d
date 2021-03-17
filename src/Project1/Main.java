package Project1;

import Utils.Constants;
import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author edsonpaulo
 */
public class Main extends JFrame
{

    public Main () throws HeadlessException
    {
        super ( "PROJECTO 1" );

        setSize ( Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT );
        setResizable ( false );
        setLayout ( null );
        setLocationRelativeTo ( null );
        setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );

        MainPanel mainPanel = new MainPanel ();
        mainPanel.setBounds ( 0, 0, Constants.WINDOW_WIDTH,
                              Constants.WINDOW_HEIGHT );
        mainPanel.setBackground ( Color.WHITE );

        add ( mainPanel );
        setVisible ( true );
    }

    public static void main ( String[] args ) throws Exception
    {
        new Main ();
    }
}
