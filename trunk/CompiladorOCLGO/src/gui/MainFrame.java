package gui;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
final public class MainFrame extends Frame implements Handles {

  private volatile boolean choosing;

  private String fileName = "";
  private String dirName = "";
  
  private Button quit; 
  private Button generate;
  private Button specChoose; 
  private Button dirChoose;
  private JRadioButton lexica;
  private JRadioButton sintatica;
  private JRadioButton semantica;
  private ButtonGroup btg;
  private JPanel radioPanel;

  private TextField spec;
  private TextField dir;

  private TextField messages;
  private TextField oclSpecification;

  //private GeneratorThread thread;

  private OptionsDialog dialog;

  
  @SuppressWarnings("deprecation")
public MainFrame() {
    super("Compilador Ocl -> Go ");
    buildContent();
    
    addWindowListener( new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        quit();
      }
    });
    
    pack();
    show();
  }


  private void buildContent() {
    setBackground(SystemColor.control);

    generate   = new Button("Run");
    quit       = new Button("Quit");
    dirChoose  = new Button("Browse");
    dir        = new TextField(10);
    specChoose = new Button("Browse");
    spec       = new TextField(10);            
    messages   = new TextField(10000);
    oclSpecification = new TextField(10000);
    lexica = new JRadioButton("Léxica ", true);
    sintatica = new JRadioButton("Sintática ", false);
    semantica = new JRadioButton("Semântica ", false);
    btg = new ButtonGroup();
    radioPanel = new JPanel();

    messages.setEditable(false);
    Font font = messages.getFont();
    if (font != null)
      messages.setFont(new Font("Monospaced", font.getStyle(), font.getSize()));
    else
      messages.setFont(new Font("Monospaced", Font.PLAIN, 12));

    //Out.setGUIMode(messages);
    
    oclSpecification.setEditable(true);
    font = oclSpecification.getFont();
    if (font != null)
      oclSpecification.setFont(new Font("Monospaced", font.getStyle(), font.getSize()));
    else
      oclSpecification.setFont(new Font("Monospaced", Font.PLAIN, 12));
    
    generate.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        generate();
      }
    } );

    quit.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        quit();
      }
    } );

    specChoose.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        specChoose();
      }
    } );

    dirChoose.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dirChoose();
      }
    } );

    spec.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fileName = spec.getText();
        generate();
      }
    } );
    
    spec.addTextListener( new TextListener() {
      public void textValueChanged(TextEvent e) {
        fileName = spec.getText();
      }
    } );
    
    dir.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        dirName = dir.getText();
        generate();
      }
    } );
    
    dir.addTextListener( new TextListener() {
      public void textValueChanged(TextEvent e) {
        dirName = dir.getText();
      }
    } );
    
    btg.add(lexica);
    btg.add(sintatica);
    btg.add(semantica);
    
    radioPanel.setLayout(new GridLayout(3, 1));
    radioPanel.add(lexica);
    radioPanel.add(sintatica);
    radioPanel.add(semantica);
    
    radioPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Análise: "));
    
    GridPanel north = new GridPanel(4,10,10,10);
    north.setInsets( new Insets(5,8,10,10) );
      
    north.add( 3,1, quit);
    north.add( 3,2, generate);
    
    north.add( 0,0, BOTTOM, new Label("XML Model:"));
    north.add( 0,1, 2,1, spec);
    north.add( 2,1, specChoose);

    north.add( 0,2, BOTTOM, new Label("Output directory:"));
    north.add( 0,3, 2,1, dir);
    north.add( 2,3, dirChoose);
    
    north.add( 0, 4, BOTTOM, new Label("Ocl Specification: "));
    north.add( 0, 5, 2, 1, oclSpecification);
    
    north.add(0, 6, BOTTOM, new Label("Analysis Output:"));
    north.add(0, 7, 2, 1, messages);
 
    add("North", north);
    add(radioPanel);

    setEnabledAll(false);
  }

  @SuppressWarnings("deprecation")
protected void showOptions() {
    if (dialog == null) {
      dialog = new OptionsDialog(this);
    }
    dialog.show();
  }


  public Dimension getPreferredSize() {
    Dimension d = super.getPreferredSize();
    d.width = 800;
    d.height = 500;
    return d;
  }
  
  private void setEnabledAll(boolean enable) {
    quit.setEnabled( !enable );
    generate.setEnabled( !enable );
    dirChoose.setEnabled( !enable );
    dir.setEnabled( !enable );
    specChoose.setEnabled( !enable );
    spec.setEnabled( !enable );
  }

  private void generate() {
    // workaround for a weird AWT bug
   /* if (choosing) return;
   
    setEnabledAll(true);

    thread = new GeneratorThread(this, fileName, dirName);
    thread.start();*/
  } 

  public void generationFinished(boolean success) {
    setEnabledAll(false);
    
    /*if (success) 
      messages.append(Out.NL+"Generation finished successfully."+Out.NL);
    else
      messages.append(Out.NL+"Generation aborted."+Out.NL);*/
  }

  private void quit() {
    setVisible(false);
    System.exit(0);
  }
  
  @SuppressWarnings("deprecation")
private void dirChoose() {
    choosing = true;
    
    FileDialog d = new FileDialog(this, "Choose directory", FileDialog.LOAD);
    
    d.show();
    
    if (d.getDirectory() != null) {
      dir.setText( (new File(d.getDirectory())).getAbsolutePath() );
    }
    
    choosing = false;    
  }

  @SuppressWarnings("deprecation")
private void specChoose() {
    choosing = true;
    
    FileDialog d = new FileDialog(this, "Choose file XML", FileDialog.LOAD);
    
    d.setFile("*.xml");
    d.show();
    
    if (d.getFile() != null) {
      fileName = d.getDirectory()+d.getFile();
      dir.setText(d.getDirectory());
      spec.setText(fileName);
    }
    
    choosing = false;    
  }
    
}
