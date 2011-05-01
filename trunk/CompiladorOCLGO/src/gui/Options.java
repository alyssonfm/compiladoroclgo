package gui;

import java.io.File;
import java.io.IOException;

public class Options {

  /** If true, additional verbose debug information is produced
   *  This is a compile time option */
  public final static boolean DEBUG = false;

	/** code generation method: maximum packing */
	final public static int PACK   = 0;
	/** code generation method: traditional */
	final public static int TABLE  = 1;
	/** code generation method: switch statement */
	final public static int SWITCH = 2;


	/** output directory */
	private static File directory;
  /** strict JLex compatibility */
  public static boolean jlex;
  /** don't run minimization algorithm if this is true */
  public static boolean no_minimize; 
  /** don't write backup files if this is true */
  public static boolean no_backup; 
  /** default code generation method */
  public static int gen_method;
  /** If false, only error/warning output will be generated */
  public static boolean verbose;
  /** If true, progress dots will be printed */
  public static boolean progress;
  /** If true, jflex will print time statistics about the generation process */
  public static boolean time;
  /** If true, jflex will write graphviz .dot files for generated automata */
  public static boolean dot;
  /** If true, you will be flooded with information (e.g. dfa tables).  */
  public static boolean dump;

	static { setDefaults();	}


  /**
   * @return the output directory
   */
  public static File getDir() {    
    return directory;
  }

  /**
   * Set output directory
   * 
   * @param dirName the name of the directory to write output files to
   */
  public static void setDir(String dirName) {
  	setDir(new File(dirName)); 
  }
  

	/**
	 * Set output directory
	 * 
	 * @param d  the directory to write output files to
	 */
  public static void setDir(File d) {
    if ( d.isFile() ) {
      Out.error("Error: \""+d+"\" is not a directory.");
    }
    
    if ( !d.isDirectory() && !d.mkdirs() ) {
      Out.error("Error: couldn't create directory \""+d+"\"");
    }
  
    directory = d;
  }

  /**
   * Sets all options back to default values. 
   */
  public static void setDefaults() {
  	directory = null;
    jlex = false;
		no_minimize = false;
		no_backup = false;
		gen_method = Options.PACK;    
    verbose = true;
    progress = true;
    time = false;
    dot = false;
    dump = false;
    Skeleton.readDefault();
  }

  public static void setSkeleton(File skel) {
    try {
		Skeleton.readSkelFile(skel);
	} catch (IOException e) {
		e.printStackTrace();
	}
  }   
}
