/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * JFlex 1.4.3                                                             *
 * Copyright (C) 1998-2009  Gerwin Klein <lsf@jflex.de>                    *
 * All rights reserved.                                                    *
 *                                                                         *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License. See the file      *
 * COPYRIGHT for more information.                                         *
 *                                                                         *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the           *
 * GNU General Public License for more details.                            *
 *                                                                         *
 * You should have received a copy of the GNU General Public License along *
 * with this program; if not, write to the Free Software Foundation, Inc., *
 * 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA                 *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package gui;
 
import java.io.*;
import java.util.*;
import gui.MainFrame;


/**
 * This is the main class of JFlex controlling the scanner generation process. 
 * It is responsible for parsing the commandline, getting input files,
 * starting up the GUI if necessary, etc. 
 *
 */
public class Main {
  
  final public static String version = "";

  /**
   * Generates a scanner for the specified input file.
   *
   * @param inputFile  a file containing a lexical specification
   *                   to generate a scanner for.
   */
  public static void generate(File inputFile) {

    Out.resetCounters();

    Timer totalTime = new Timer();
    Timer time = new Timer();
      
    FileReader inputReader = null;
    
    totalTime.start();      

    try {  
      Out.println(ErrorMessages.READING, inputFile.toString());
      inputReader = new FileReader(inputFile);
    }
    catch (FileNotFoundException e) {
      Out.error(ErrorMessages.CANNOT_OPEN, inputFile.toString());
    }
  }

  public static Vector parseOptions(String argv[]) {
    Vector files = new Vector();

    for (int i = 0; i < argv.length; i++) {

      if ( argv[i].equals("-d") || argv[i].equals("--outdir") ) { //$NON-NLS-1$ //$NON-NLS-2$
        if ( ++i >= argv.length ) {
          Out.error(ErrorMessages.NO_DIRECTORY); 
        }
        Options.setDir(argv[i]);
        continue;
      }

      if ( argv[i].equals("--skel") || argv[i].equals("-skel") ) { //$NON-NLS-1$ //$NON-NLS-2$
        if ( ++i >= argv.length ) {
          Out.error(ErrorMessages.NO_SKEL_FILE);
        }

        Options.setSkeleton(new File(argv[i]));
        continue;
      }

      if ( argv[i].equals("-jlex") || argv[i].equals("--jlex") ) { //$NON-NLS-1$ //$NON-NLS-2$
        Options.jlex = true;
        continue;
      }

      if ( argv[i].equals("-v") || argv[i].equals("--verbose") || argv[i].equals("-verbose") ) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        Options.verbose = true;
        Options.progress = true;
        continue;
      }

      if ( argv[i].equals("-q") || argv[i].equals("--quiet") || argv[i].equals("-quiet") ) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        Options.verbose = false;
        Options.progress = false;
        continue;
      }

      if ( argv[i].equals("--dump") || argv[i].equals("-dump") ) { //$NON-NLS-1$ //$NON-NLS-2$
        Options.dump = true;
        continue;
      }

      if ( argv[i].equals("--time") || argv[i].equals("-time") ) { //$NON-NLS-1$ //$NON-NLS-2$
        Options.time = true;
        continue;
      }

      if ( argv[i].equals("--version") || argv[i].equals("-version") ) { //$NON-NLS-1$ //$NON-NLS-2$
        Out.println(ErrorMessages.THIS_IS_JFLEX, version); 
      }

      if ( argv[i].equals("--dot") || argv[i].equals("-dot") ) { //$NON-NLS-1$ //$NON-NLS-2$
        Options.dot = true;
        continue;
      }

      if ( argv[i].equals("--help") || argv[i].equals("-h") || argv[i].equals("/h") ) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        printUsage();
      }

      if ( argv[i].equals("--info") || argv[i].equals("-info") ) { //$NON-NLS-1$ //$NON-NLS-2$
        Out.printSystemInfo();
      }
      
      if ( argv[i].equals("--nomin") || argv[i].equals("-nomin") ) { //$NON-NLS-1$ //$NON-NLS-2$
        Options.no_minimize = true;
        continue;
      }

      if ( argv[i].equals("--pack") || argv[i].equals("-pack") ) { //$NON-NLS-1$ //$NON-NLS-2$
        Options.gen_method = Options.PACK;
        continue;
      }

      if ( argv[i].equals("--table") || argv[i].equals("-table") ) { //$NON-NLS-1$ //$NON-NLS-2$
        Options.gen_method = Options.TABLE;
        continue;
      }

      if ( argv[i].equals("--switch") || argv[i].equals("-switch") ) { //$NON-NLS-1$ //$NON-NLS-2$
        Options.gen_method = Options.SWITCH;
        continue;
      }
      
      if ( argv[i].equals("--nobak") || argv[i].equals("-nobak") ) { //$NON-NLS-1$ //$NON-NLS-2$
        Options.no_backup = true;
        continue;
      }
      
      if ( argv[i].startsWith("-") ) { //$NON-NLS-1$
        Out.error(ErrorMessages.UNKNOWN_COMMANDLINE, argv[i]);
        printUsage();
      }

      // if argv[i] is not an option, try to read it as file 
      File f = new File(argv[i]);
      if ( f.isFile() && f.canRead() ) 
        files.addElement(f);      
      else {
        Out.error("Sorry, couldn't open \""+f+"\""); //$NON-NLS-2$
      }
    }

    return files;
  }


  public static void printUsage() {
    Out.println(""); //$NON-NLS-1$
    Out.println("Usage: jflex <options> <input-files>");
    Out.println("");
    Out.println("Where <options> can be one or more of");
    Out.println("-d <directory>   write generated file to <directory>");
    Out.println("--skel <file>    use external skeleton <file>");
    Out.println("--switch");
    Out.println("--table");
    Out.println("--pack           set default code generation method");
    Out.println("--jlex           strict JLex compatibility");
    Out.println("--nomin          skip minimization step");
    Out.println("--nobak          don't create backup files");
    Out.println("--dump           display transition tables"); 
    Out.println("--dot            write graphviz .dot files for the generated automata (alpha)");
    Out.println("--verbose");
    Out.println("-v               display generation progress messages (default)");
    Out.println("--quiet");
    Out.println("-q               display errors only");
    Out.println("--time           display generation time statistics");
    Out.println("--version        print the version number of this copy of jflex");
    Out.println("--info           print system + JDK information");
    Out.println("--help");
    Out.println("-h               print this message");
    Out.println("");
    Out.println(version); 
    Out.println("Have a nice day!");
  }


  public static void generate(String argv[]) {
    Vector files = parseOptions(argv);

    if (files.size() > 0) {
      for (int i = 0; i < files.size(); i++) 
        generate((File) files.elementAt(i));          
    }
    else {
      new MainFrame();
    }    
  }


  /**
   * Starts the generation process with the files in <code>argv</code> or
   * pops up a window to choose a file, when <code>argv</code> doesn't have
   * any file entries.
   *
   * @param argv the commandline.
   */
  public static void main(String argv[]) {
    try {
      generate(argv);
    }
    catch (Exception e) {
      Out.statistics();
      System.exit(1);
    }
  }
}
