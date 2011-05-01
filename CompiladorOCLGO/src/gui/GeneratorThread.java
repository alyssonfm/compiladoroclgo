package gui;

import java.io.File;


/**
 * Low priority thread for code generation (low priority 
 * that gui has time for screen updates)
 *
 * @author Alysson Filgueira, Delano Helio, Nata Venancio.
 *
 */
public class GeneratorThread extends Thread {

  /** there must be at most one instance of this Thread running */
	private static volatile boolean running = false;

	/** input file setting from GUI */
	String  inputFile;

  /** output directory */
  String outputDir;
  
  /** main UI component, likes to be notified when generator finishes */
  MainFrame parent;

	/**
	 * Create a new GeneratorThread, but do not run it yet.
	 * 
	 * @param parent      the frame, main UI component
	 * @param inputFile   input file from UI settings
	 * @param messages    where generator messages should appear
	 * @param outputDir   output directory from UI settings
	 */
  public GeneratorThread(MainFrame parent, String inputFile, 
                         String outputDir) {
    this.parent    = parent;
    this.inputFile = inputFile;
    this.outputDir = outputDir;
  }


	/**
	 * Run the generator thread. Only one instance of it can run at any time.
	 */
  public void run() {
  	if (running) {
  		Out.error(ErrorMessages.ALREADY_RUNNING);
			parent.generationFinished(false);
  	}
  	else {
  		running = true;
			setPriority(MIN_PRIORITY);    
			try {
        if (!outputDir.equals("")) {
          Options.setDir(outputDir);
        }
				Main.generate(new File(inputFile));
				Out.statistics();
				parent.generationFinished(true);
			}
			catch (Exception e) {
				Out.statistics();
				parent.generationFinished(false);
			}
      finally {
        running = false;
      }
  	}
  }
}
