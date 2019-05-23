import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;

public class RoboSim extends JFrame implements ActionListener {


    public class Coordinate extends Object {

        private int m_x;
        private int m_y;

        Coordinate( int x, int y )
        {
            m_x = x;
            m_y = y;
        }

        public int getX() {
            return m_x;
        }

        public int getY() {
            return m_y;
        }


        @Override
        public String toString() {
            return "(" + m_x + "," + m_y + ")";
        }

    }


    private static Border line = BorderFactory.createLineBorder(Color.gray);

    private final int XSize = 20;
    private final int YSize = 20;

    private float  gridValue[][] = new float[XSize][YSize];
    private JPanel gridCell[][]  = new JPanel[XSize][YSize];

    Vector<Coordinate> pathData = new Vector<Coordinate>();
    JList        list = null;

    JFileChooser fc;
    JMenuItem    openFileChoice;


    public static void main( String[] args )
    {
        RoboSim gui = new RoboSim();
        gui.setVisible( true );
    }


    public RoboSim()
    {
        super( "RoboSim" );
        setSize( 600, 450 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Create and draw grid.
        JPanel grid = new JPanel();
        grid.setLayout( new GridLayout(XSize, YSize) );

        for (int y=0; y < YSize ; y++) {
            for (int x=0; x < XSize ; x++) {
                gridCell[y][x] = new JPanel();
                gridCell[y][x].setBorder( line );
                gridCell[y][x].setBackground( Color.white );
                grid.add( gridCell[y][x] );
            }
        }

        // Create listbox.
        list = new JList( pathData );
        ListSelectionModel listSelectionModel = list.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener( new SharedListSelectionHandler() );

        JScrollPane scrollActionTable = new JScrollPane( list );
        scrollActionTable.setPreferredSize( new Dimension( 80, 350) );
        JPanel listboxPanel = new JPanel();
        listboxPanel.add( scrollActionTable );

        JSplitPane split = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT, true, listboxPanel, grid );
        split.setDividerLocation(100);
        add( split );


        // Create menu.
        fc = new JFileChooser();

        //  File menu.
        JMenu fileMenu = new JMenu( "File" );
        openFileChoice = new JMenuItem( "Open ...");
        fileMenu.add( openFileChoice );

        JMenuBar menuBar = new JMenuBar();
        menuBar.add( fileMenu );
        setJMenuBar( menuBar );

        openFileChoice.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
                    int returnVal = fc.showOpenDialog(RoboSim.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        readFile( file );
                    } else {
                    }
				}
			}
        );

    }


    void gridDraw( )
    {

        for (int y=0; y < YSize ; y++) {
             for (int x=0; x < XSize ; x++) {
                 float scale = (float) 1.0 - gridValue[y][x];
                 gridCell[y][x].setBackground( new Color(scale,scale,scale) );
             }
         }
    }

    class SharedListSelectionHandler implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent e) {
            for (int y=0; y < YSize ; y++) {
                for (int x=0; x < XSize ; x++) {
                    gridValue[y][x] = (float)0.0;                    
                }
            }
            for ( int n=0; n<list.getSelectedIndex(); ++n ) {
                Coordinate co = pathData.get(n);
                gridValue[co.getY()][co.getX()] = (float)0.25;
            }
            int curr = list.getSelectedIndex();
            if ( curr >= 0 ) {
                Coordinate co = pathData.get(curr);
                gridValue[co.getY()][co.getX()] = (float)1.0;
            }
            gridDraw();
        }
    }

    public void actionPerformed(ActionEvent e ) {

    }


    public void readFile( File fileIn )
    {
        // Note: need to parse input and error check.
        Scanner sc = null;
        int n=0;
        try
        {
            pathData.clear();
            sc = new Scanner( fileIn ); //.useDelimiter("\\s*[(,)\\s*]\\s*");
            while ( sc.hasNext() )
            {
                int x = sc.nextInt();
                if ( sc.hasNext() ) {
                    int y = sc.nextInt();
                    if ( ( x >= 0 && x < XSize ) && ( y >= 0 && y < YSize ) ) {
                        pathData.add( new Coordinate(x,y) );
                    }
                    else {
                        System.out.println( "Invalid input coordinate (" + x + "," + y +")" );
                        break;
                    }
                }
            }
            list.setListData( pathData );
            if ( !pathData.isEmpty() ) {
                list.setSelectedIndex(0);
            }            
            validate();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Did not find input file.");
        }
        finally
        {
            if (sc != null) sc.close();
        }
    }
}
