package fr.ans.rmac;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class DiskFileExplorerTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DiskFileExplorerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DiskFileExplorerTest.class );
    }
    /**
     * Rigourous Test :-)
     */
/*
    public void testApp()
    {
    	  String pathToExplore = "C:\\Users\\flabatie\\Documents\\ENV-ANS\\Comptage-Certificats\\dossiers";
    	  DiskFileExplorer diskFileExplorer = new DiskFileExplorer(pathToExplore, true);
    	  Long start = System.currentTimeMillis();
    	  diskFileExplorer.list();
    	  System.out.println("----------");
    	  System.out.println("Analyse de " + pathToExplore + " en " + (System.currentTimeMillis() - start) + " mses");
    	  System.out.println(diskFileExplorer.dircount + " dossiers");
    	 
    	  assertTrue( true );
    }
    /**
     * Rigourous Test :-)
     */
  /*
    public void testUnzip()
    {
    	  String pathToExplore = "C:\\Users\\flabatie\\Documents\\ENV-ANS\\Comptage-Certificats\\dossiers-compresses";
    	  DiskFileExplorer diskFileExplorer = new DiskFileExplorer(pathToExplore, true);
    	  
    	  diskFileExplorer.unzip();
  
    	 
    	  assertTrue( true );
    }*/
        /**
     * Rigourous Test :-)
     */
  /*
	    public void testListAndVerifyType()
	    {
	    	  String pathToExplore = "C:\\Users\\flabatie\\Documents\\ENV-ANS\\Comptage-Certificats\\dossiers";
	    	  DiskFileExplorer diskFileExplorer = new DiskFileExplorer(pathToExplore, true);
	    	  
	    	  
	    	  diskFileExplorer.listAndVerifyType();
	  
	    	 
	    	  assertTrue( true );
	    }
	   
    /**
     * Rigourous Test :-)
     */
	    /*
    public void testComplet()
    {
	  String pathToExplore = "C:\\Users\\flabatie\\Documents\\ENV-ANS\\Comptage-Certificats\\dossier-compresse";
	  DiskFileExplorer diskFileExplorer = new DiskFileExplorer(pathToExplore, true);
	  Long start = System.currentTimeMillis();
	  diskFileExplorer.unzip();
	 
	  diskFileExplorer.list();
	  diskFileExplorer.listAndVerifyType();
	  System.out.println("----------");
	  System.out.println("Analyse de " + pathToExplore + " en " + (System.currentTimeMillis() - start) + " mses");
	  System.out.println(diskFileExplorer.dircount + " dossiers");
	  System.out.println(diskFileExplorer.filecount + " fichiers");
    }*/
    
}
