
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.math.BigInteger; 
import java.io.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList; 
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Project1 extends JFrame 
{
        
    
    public Project1()
    {
        super( "Eliptic Curve Cryptographie" );
        
        this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        // Construction et injection de la barre d'outils
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.add( this.createToolBar(), BorderLayout.NORTH );
        contentPane.add(this.createhelp(),BorderLayout.WEST);
        contentPane.add(this.createalgo(),BorderLayout.EAST);
        contentPane.add(this.createkey(),BorderLayout.SOUTH);
        ellipticcurve ec = new ellipticcurve(5,5,7);
        contentPane.add(new courbe(ec),BorderLayout.CENTER);

        
        this.setSize( 1000, 800);
        this.setLocationRelativeTo( null );
     // Construction et injection de la barre de menu
        this.setJMenuBar( this.createMenuBar() );
    }
    private JPanel createhelp()
    {
        JPanel helpBar  = new JPanel ();
        helpBar.setPreferredSize(new Dimension(250, 150));
        JMenuBar mm = new JMenuBar();
        mm.setPreferredSize(new Dimension(250,30 ));
        helpBar.setBackground(Color.WHITE);

        JButton btnLeft = new JButton( "Help" );
        JButton jj = new JButton( "Guide" );
        mm.add(btnLeft);
        mm.add(jj);
        helpBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        helpBar.add(mm);
        return helpBar;
    }
    class ellipticcurve
    { 
        Integer a ; 
        Integer b ; 
        Integer p;
        ellipticcurve(Integer x,Integer y ,Integer z){
        a=z ; 
        b=y ; 
        p=z ; 
    }
    boolean good(Integer x ,Integer y)
    { 
        /*Integer c=(((((((x%p)(x%p))%p)(x%p))%p+((a%p)(x%p))%p)%p)+(b%p))%p; 
        Integer d=((y%p)(y%p))%p;*/
        Integer c= ((x*x*x)+(a*x)+b)%p;
        Integer d=(y*y)%p; 
        return (c-d)%p==0 ; 

    }
    Vector<Integer> constructpointsx(){ 
    Vector<Integer> abs =new Vector<Integer>(); 
 

    for(Integer x=0 ; x<p; x++){ 
    for(Integer y=0; y<p; y++){ 
    if(good(y,x)){ abs.add(x);  }} 
    }
    return abs;
 
    }
    Vector<Integer> constructpointsy(){ 
 
    Vector<Integer> cor =new Vector<Integer>();

    for(Integer x=0 ; x<p; x++){ 
    for(Integer y=0 ; y<p; y++){ 
    if(good(y,x)){ cor.add(y);  }} 
    }
    return cor;
    }
    }
    class courbe extends JComponent
    { 
        private static final long serialVersionUID=1222;
        private Vector<Integer> X; 
        private Vector<Integer> Y; 
        private Integer p; 
        public courbe(ellipticcurve ecc){ 
        super(); 
        X=ecc.constructpointsx(); 
        Y=ecc.constructpointsy();
        p=ecc.p; 
    }

    protected void paintComponent(Graphics g)
    { 
        super.paintComponent(g); 
        g.setColor(Color.GRAY); 
        g.fillRect(0,0,getWidth()*2/3,getHeight()); 
        g.setColor(Color.RED); 
        g.drawLine(0,getHeight()-2,getWidth()*2/3,getHeight()-2);
        g.drawLine(3,0,3,getHeight());

        g.setColor(Color.BLACK);
        for(int i=1; i<p;i++){ 
        g.drawLine(0,topixely(i),topixelx(p),topixely(i)); } 
        for(int i=1; i<p;i++){ 
        g.drawLine(topixelx(i),topixely(0),topixelx(i),topixely(p));  } 
        g.setColor(Color.RED);
        for(int i=0;i<=p;i++){String str=Integer.toString(i);  g.drawString(str,topixelx(i),topixely(0)-3); 
   } 
        for(int i=0; i<=p; i++){String str=Integer.toString(i);  g.drawString(str,topixelx(0)+5,topixely(i));  }
        for(int i=0; i<X.size();i++){


        g.drawRect(topixelx(X.get(i)),topixely(Y.get(i)),4,4);System.out.print(X.get(i));}
        for(int i=0; i<X.size();i++){System.out.print(Y.get(i));}


  }


private int topixely(double x){ 
return (int)(((p-x)/p)*getHeight()); }
private int topixelx(double x){ 
return (int)((x/p)*getWidth()*2/3); 
}}
 

    private JPanel createalgo()
    {
        JPanel algoBar  = new JPanel (new FlowLayout());
        algoBar.setPreferredSize(new Dimension(150, 150));
        algoBar.setBackground(new Color(150,150,150));
        Font  f4  = new Font(Font.DIALOG_INPUT,  Font.BOLD, 20);

        JLabel btnLeft = new JLabel( "Algorithm" );
        btnLeft.setFont(f4);
        JButton jj = new JButton( "Diffie_Hellmen" );
        jj.setPreferredSize(new Dimension(150,25));
        JButton jj1 = new JButton( "RSA" );
        jj1.setPreferredSize(new Dimension(150,25));
        JButton jj2 = new JButton( "AES" );
        jj2.setPreferredSize(new Dimension(150,25));
        JButton jj3 = new JButton( "MV" );
        jj3.setPreferredSize(new Dimension(150,25));
        algoBar.add(btnLeft);
        algoBar.add(jj);
        algoBar.add(jj3);
        algoBar.add(jj2);
        algoBar.add(jj1);
        return algoBar;
    }
    private JPanel createkey()
    {
        JPanel keyBar  = new JPanel (new FlowLayout());
        keyBar.setBackground(new Color(150,150,0));
        keyBar.setPreferredSize(new Dimension(100,100));

        JLabel btnLeft = new JLabel( "Algorithm" );
    
        keyBar.add(btnLeft);
     
        return keyBar;
    }

    /* Methode de construction de la barre de menu */
    private JMenuBar createMenuBar() 
    {  

        // La barre de menu à proprement parler
        JMenuBar menuBar = new JMenuBar();

        // Définition du menu déroulant "File" et de son contenu
        JMenu mnuFile = new JMenu( "File" );
        mnuFile.setMnemonic( 'F' );

        JMenuItem mnuNewFile = new JMenuItem( "New File" );
        mnuNewFile.setIcon( new ImageIcon( "new.png" ) );
        mnuNewFile.setMnemonic( 'N' );
        mnuNewFile.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK) );
        mnuNewFile.addActionListener( this::mnuNewListener );
        mnuFile.add(mnuNewFile);

        mnuFile.addSeparator();

        JMenuItem mnuOpenFile = new JMenuItem( "Open File ..." );
        mnuOpenFile.setIcon( new ImageIcon( "open.png" ) );
        mnuOpenFile.setMnemonic( 'O' );
        mnuOpenFile.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK) );
        mnuFile.add(mnuOpenFile);

        JMenuItem mnuSaveFile = new JMenuItem( "Save File ..." );
        mnuSaveFile.setIcon( new ImageIcon( "save.png" ) );
        mnuSaveFile.setMnemonic( 'S' );
        mnuSaveFile.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK) );
        mnuFile.add(mnuSaveFile);

        JMenuItem mnuSaveFileAs = new JMenuItem( "Save File As ..." );
        mnuSaveFileAs.setIcon( new ImageIcon( "save_as.png" ) );
        mnuSaveFileAs.setMnemonic( 'A' );
        mnuFile.add(mnuSaveFileAs);

        mnuFile.addSeparator();

        JMenuItem mnuExit = new JMenuItem( "Exit" );
        mnuExit.setIcon( new ImageIcon( "exit.png" ) );
        mnuExit.setMnemonic( 'x' );
        mnuExit.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK) );
        mnuFile.add(mnuExit);
        
        menuBar.add(mnuFile);
        
        // Définition du menu déroulant "Edit" et de son contenu
        JMenu mnuEdit = new JMenu( "Edit" );
        mnuEdit.setMnemonic( 'E' );
        
        JMenuItem mnuUndo = new JMenuItem( "Undo" );
        mnuUndo.setIcon( new ImageIcon( "undo.png" ) );
        mnuUndo.setMnemonic( 'U' );
        mnuUndo.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK) );
        mnuEdit.add(mnuUndo);
        
        JMenuItem mnuRedo = new JMenuItem( "Redo" );
        mnuRedo.setIcon( new ImageIcon( "redo.png" ) );
        mnuRedo.setMnemonic( 'R' );
        mnuRedo.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK) );
        mnuEdit.add(mnuRedo);
        
        mnuEdit.addSeparator();
        
        JMenuItem mnuCopy = new JMenuItem( "Copy" );
        mnuCopy.setIcon( new ImageIcon( "copy.png" ) );
        mnuCopy.setMnemonic( 'C' );
        mnuCopy.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK) );
        mnuEdit.add(mnuCopy);
        
        JMenuItem mnuCut = new JMenuItem( "Cut" );
        mnuCut.setIcon( new ImageIcon( "cut.png" ) );
        mnuCut.setMnemonic( 't' );
        mnuCut.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK) );
        mnuEdit.add(mnuCut);
        
        JMenuItem mnuPaste = new JMenuItem( "Paste" );
        mnuPaste.setIcon( new ImageIcon( "paste.png" ) );
        mnuPaste.setMnemonic( 'P' );
        mnuPaste.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK) );
        mnuEdit.add(mnuPaste);

        menuBar.add(mnuEdit);

        // Définition du menu déroulant "Help" et de son contenu
        JMenu mnuHelp = new JMenu( "Help" );
        mnuHelp.setMnemonic( 'H' );
        
        menuBar.add( mnuHelp );
        
        return menuBar;
    }
     /* Méthode de construction de la barre d'outils */
    private JToolBar createToolBar()
    {

        // La barre d'outils à proprement parler
        JToolBar toolBar = new JToolBar();

        JButton btnNew = new JButton( new ImageIcon( "new.png") );
        btnNew.setToolTipText( "New File (CTRL+N)" );
        btnNew.addActionListener( this::btnNewListener );
        toolBar.add( btnNew );

        JButton btnSave = new JButton( new ImageIcon( "save.png" ) );
        btnSave.setToolTipText( "Save (CTRL+S)" );
        toolBar.add( btnSave );

        JButton btnSaveAs = new JButton( new ImageIcon( "save_as.png" ) );
        btnSaveAs.setToolTipText( "Save As..." );
        toolBar.add( btnSaveAs );

        toolBar.addSeparator();

        JButton btnCopy = new JButton( new ImageIcon( "copy.png") );
        btnCopy.setToolTipText( "Copy (CTRL+C)" );
        toolBar.add( btnCopy );

        JButton btnCut = new JButton( new ImageIcon( "cut.png") );
        btnCut.setToolTipText( "Cut (CTRL+X)" );
        toolBar.add( btnCut );

        JButton btnPaste = new JButton( new ImageIcon( "paste.png") );
        btnPaste.setToolTipText( "Paste (CTRL+V)" );
        toolBar.add( btnPaste );

        toolBar.addSeparator();

        JButton btnExit = new JButton( new ImageIcon( "exit.png") );
        btnExit.setToolTipText( "Exit (ALT+F4)" );
        toolBar.add( btnExit );
        return toolBar;
    
    }

    public void mnuNewListener( ActionEvent event ) 
    {
        JOptionPane.showMessageDialog( this, "Button clicked !" );
    }

    private void btnNewListener( ActionEvent event )
    {
        JOptionPane.showMessageDialog( this, "Button clicked !" );
    }
    
    
    public static void main( String[] args ) throws Exception 
    {

        
        UIManager.setLookAndFeel( new NimbusLookAndFeel() );  
        new Project1().setVisible( true );
        
    }
}       