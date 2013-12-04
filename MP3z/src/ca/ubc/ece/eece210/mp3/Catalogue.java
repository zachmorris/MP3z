package ca.ubc.ece.eece210.mp3;

import java.util.ArrayList;

/**
 * Container class for all the albums and genres. Its main 
 * responsibility is to save and restore the collection from a file.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public final class Catalogue {
	//holds all our top level genres?
	//would it hurt if this was static?
	static ArrayList<Genre> mainGenres;
	
	/**
	 * Builds a new, empty catalogue.
	 */
	public Catalogue() {
		// TODO implement
	}

	/**
	 * Builds a new catalogue and restores its contents from the 
	 * given file.
	 * 
	 * @param fileName
	 *            the file from where to restore the library.
	 */
	public Catalogue(String fileName) {
		// TODO implement
	}

	/**
	 * Saved the contents of the catalogue to the given file.
	 * @param fileName the file where to save the library
	 */
	public void saveCatalogueToFile(String fileName) {
		// TODO implement
		//file i/o up in here
	}
	
	/**
	 * 
	 */
	public void recreateLibraryFromFile(String fileName){
		
	}
}