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
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import util.LoggerGerador;
import util.XMIParserBasic;

@SuppressWarnings("serial")
final public class MainFrame extends Frame implements Handles {

  private String fileName = "";
  
  private Button quit; 
  private Button generate;
  private Button specChoose; 
  private Button dirChoose;
  private Button compile;
  
  private JRadioButton lexica;
  private JRadioButton sintatica;
  private JRadioButton semantica;
  private ButtonGroup btg;
  private JPanel radioPanel;

  private TextField spec;
  private TextField dir;

  private TextArea messages;
  private TextArea oclSpecification;
  
  private Label labXml;
  private Label labSpecification;
  private Label labAnalysis;

private JRadioButton geracao;

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
    compile	   = new Button("Compilar");
    specChoose = new Button("Browse");
    spec       = new TextField(10);
    dir        = new TextField(10);
    messages   = new TextArea(10000, 100);
    oclSpecification = new TextArea(1000, 100);
    lexica = new JRadioButton("Léxica ", false);
    sintatica = new JRadioButton("Sintática ", false);
    semantica = new JRadioButton("Semântica ", false);
    geracao = new JRadioButton("Geracao ", true);
    btg = new ButtonGroup();
    radioPanel = new JPanel();
    labAnalysis = new Label("Analysis Output:");
    new Label("Output directory:");
    labSpecification = new Label("Ocl Specification: ");
    labXml = new Label("XML Model:");

    messages.setEditable(false);
    Font font = messages.getFont();
    if (font != null)
      messages.setFont(new Font("Monospaced", font.getStyle(), font.getSize()));
    else
      messages.setFont(new Font("Monospaced", Font.PLAIN, 12));
    
    oclSpecification.setEditable(true);
    font = oclSpecification.getFont();
    if (font != null)
      oclSpecification.setFont(new Font("Monospaced", font.getStyle(), font.getSize()));
    else
      oclSpecification.setFont(new Font("Monospaced", Font.PLAIN, 12));
    
    generate.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  messages.setText("");
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

    spec.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  if (spec.getText() == null) {
			messages.append("Erro");
		} else{
			fileName = spec.getText();
		}        
      }
    } );
    
    spec.addTextListener( new TextListener() {
      public void textValueChanged(TextEvent e) {
        fileName = spec.getText();
      }
    } );
    
    compile.addActionListener(new ActionListener() {		
		public void actionPerformed(ActionEvent e) {
			messages.setText("");
			compile();
		}
	});
      
    btg.add(lexica);
    btg.add(sintatica);
    btg.add(semantica);
    btg.add(geracao);
    
    radioPanel.setLayout(new GridLayout(3, 1));
    radioPanel.add(lexica);
    radioPanel.add(sintatica);
    radioPanel.add(semantica);
    radioPanel.add(geracao);
    
    radioPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Análise: "));
    
    GridPanel north = new GridPanel(3,3,10,10);
    north.setInsets( new Insets(5,8,10,10) );
    
    GridPanel other = new GridPanel(3,4,10,10);
      
    north.add( 0,0, BOTTOM, labXml);
    north.add( 0,1, 2,1, spec);
    north.add( 2,1, specChoose);
    
    other.add( 0, 0, BOTTOM, labSpecification);
    other.add( 0, 1, 2, 1, oclSpecification);
    
    other.add(0, 2, BOTTOM, labAnalysis);
    other.add(0, 3, 2, 1, messages);
    
    other.add(2, 1, radioPanel);
    other.add(2, 3, compile);
    north.add(2,0, quit);
    north.add(2,2, generate);
 
    add("North", north);
    add(other);

    setEnabledAll(false);
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
  
private void compile(){
	if (XMIParserBasic.getPath() == null) {
		messages.append("Erro, escolha o arquivo XML ...");
	}else{
		try{
			Runtime.getRuntime().exec("c:\\Go\\bin\\8g files\\out\\code.go");
			Runtime.getRuntime().exec("c:\\Go\\bin\\8l files\\out\\code.8");
			//Runtime.getRuntime().exec("\\files\\out\\8.out.exe");
		}catch (IOException e){
			e.printStackTrace();
		}
	}
  }

  private void generate() {  
	  if (XMIParserBasic.getPath() == null) {
		messages.append("Erro, escolha o arquivo XML ...");
	} else {
		String file = "temp.txt";
		  FileWriter x;
		  try {
			x = new FileWriter(file,false);
			x.write(oclSpecification.getText());
			x.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  if(lexica.isSelected()) {
			  messages.append("########  Iniciando Analise Léxica  ########\n");
			  messages.append(Main.analiseLexica(file).toString());
			  messages.append("########  Finalizada Analise Léxica ########\n");
		  }
		  else if(sintatica.isSelected()) {
			  messages.append("########  Iniciando Analise Sintática  ########\n");
			  messages.append(Main.analiseSintatica(file, false).toString());
			  messages.append("########  Finalizada Analise Sintática ########\n");
		  }
		  else if(semantica.isSelected()){
			  messages.append("########  Iniciando Analise Semântica  ########\n");
			  messages.append(Main.analiseSemantica(file, false).toString());
			  messages.append("########  Finalizada Analise Semântica ########\n");
		  }
		  else if(geracao.isSelected()){
			  LoggerGerador log = Main.geracaoCodigo(file, false);
			  messages.append("########  Iniciando Geracao Codigo  ########\n");
			  messages.append(log.toString() + "\n");
			  messages.append("########  Finalizada Geracao Codigo ########\n");
			  try {
				BufferedWriter out = new BufferedWriter(new FileWriter("files\\out\\code.go", false));
				out.write(log.getCode());
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
	}
	  
  } 

  private void quit() {
    setVisible(false);
    System.exit(0);
  }

  @SuppressWarnings("deprecation")
private void specChoose() {
    FileDialog d = new FileDialog(this, "Choose file XML", FileDialog.LOAD);
    
    d.show();
    
    if (d.getFile() != null) {
      fileName = d.getDirectory()+d.getFile();
      spec.setText(fileName);
      XMIParserBasic.setPath(fileName);
    } else
    {
    	messages.append("Erro, escolha o arquivo XML ...");
    }    
  }
    
}
