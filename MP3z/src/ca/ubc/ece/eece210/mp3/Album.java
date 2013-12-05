package ca.ubc.ece.eece210.mp3;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * @author Sathish Gopalakrishnan
 * 
 * This class contains the information needed to represent 
 * an album in our application.
 * 
 */

public final class Album extends Element {
	private String title;
	private String performer;
	private ArrayList<String> songlist = new ArrayList<String>();
	//parentGenre = unclassified; //TODO HOW DO UNCLASSIFIED
	
		
	/**
	 * Builds an album with the given title, performer and song list
	 * 
	 * @param title the title of the album
	 * @param performer the performer of the album
	 * @param songlist the ArrayList of songs in the album
	 */
	public Album(String title, String performer, ArrayList<String> songlist) {
		this.title = title;
		this.performer = performer;
		this.songlist = songlist;		
	}

	/**
	 * Builds an album from the string representation of the object. It is used
	 * when restoring an album from a file.
	 * 
	 * @param stringRepresentation the string representation
	 */
	public Album(String stringRepresentation) {
		int i, j;
		String genreName, temp, tempShort;
		Genre tempGenre, currentGenre = null;
		Album currentAlbum = this;
		
		this.title = stringRepresentation.split("<title>")[1].split("</title>")[0];
		
		temp = stringRepresentation;
		
		do{
			i = temp.lastIndexOf("<genre>");
			j = temp.indexOf("<genre>");
			
			tempShort = temp.substring(temp.lastIndexOf("<genre>"), 
					temp.lastIndexOf("</genre>"));
						
			genreName = tempShort.split("<genre>")[1].split("</genre>")[0];
		
			tempGenre = findGenre(genreName);
						
			if(currentAlbum != null){
				
				if(genreName != null && tempGenre != null){
					currentAlbum.addToGenre(tempGenre);										
				}
				else if(genreName != null && tempGenre == null){
					//create new genre
					currentAlbum.parentGenre = new Genre(genreName);
				}
				else{
					//this means that our string rep had no genre
					currentAlbum.parentGenre = null; //TODO make unclassified?					
				}				
			}
			if(currentGenre != null){
				
				if(genreName != null && tempGenre != null){
				
					tempGenre.addToGenre(currentGenre);					
				}
				else if(genreName != null && tempGenre == null){
					//create new genre
					currentGenre.parentGenre = new Genre(genreName);					
				}
				else{
					//this means that our string rep had no genre
					currentGenre.parentGenre = null; //TODO make unclassified?					
				}				
				currentGenre = tempGenre;				
			}
			if(currentAlbum != null && currentGenre == null){
				currentGenre = currentAlbum.parentGenre;
				currentAlbum = null;				
			}			
			temp = temp.substring(temp.indexOf("<genre>"), temp.lastIndexOf("<genre>"));			
		}while(j < i);
		
		this.performer = stringRepresentation.split("<performer>")[1].split("</performer>")[0];
		
		do{
			i = stringRepresentation.indexOf("<song>");
			j = stringRepresentation.lastIndexOf("<song>");
			
			this.songlist.add(stringRepresentation.split("<song>")[1].split("</song>")[0]);			
			
			stringRepresentation = stringRepresentation.substring(stringRepresentation.indexOf("</song>") + 7 );
		}while(i < j);		
	}

	/**
	 * Returns the string representation of the given album. The representation
	 * contains the title, performer and songlist, as well as all the genre
	 * that the album belongs to.
	 * 
	 * @return the string representation of the album
	 */
	public String getStringRepresentation() {
		
		Object[] songlistArrayed;
		songlistArrayed = songlist.toArray();
		Genre temp;
		int i;
		
		StringBuilder builder = new StringBuilder();
		
		temp = parentGenre;		
		
		while(temp != null){
			builder.insert(0, "<genre>" + temp.getName() + "</genre>\n");
			temp = temp.parentGenre;
			
		}		
		
		builder.insert(0, "<album>\n");
		
		builder.append("<title>" + title + "</title>\n");
		builder.append("<performer>" + performer + "</performer>\n");
		
		for(i = 0; i < songlistArrayed.length; i++){
			builder.append("<song>" + songlistArrayed[i] + "</song>\n");
		}
		builder.append("</album>");
		
		return(builder.toString());
	}

	/**
	 * Add the album to the given genre
	 * 
	 * @param genre
	 *            the genre to add the album to.
	 */
	public void addToGenre(Genre genre) {
		
		if(this.parentGenre == null){ 
			genre.addToGenre(this);		
			parentGenre = genre; 
		}			
	}
	
	/**
	 * Remove the album from the genre
	 * 
	 * @return removes the album from its genre
	 * 
	 */
	public void removeFromGenre() {
		
		if(parentGenre != null){
			parentGenre.removeFromGenre(this);
			this.parentGenre = null;
			
		}
	}

	/**
	 * Returns the genre that this album belongs to.
	 * 
	 * @return the genre that this album belongs to
	 */
	public Genre getGenre() {
		
		return parentGenre;
	}

	/**
	 * Returns the title of the album
	 * 
	 * @return the title
	 */
	public String getTitle() {

		return title;
	}

	/**
	 * Returns the performer of the album
	 * 
	 * @return the performer
	 */
	public String getPerformer() {
		
		return performer;
	}

	/**
	 * An album cannot have any children (it cannot contain anything).
	 */
	@Override
	public boolean hasChildren() {
		return false;
	}
	
	/**
	 * Finds the genre that an album belongs to, given the genre's name
	 * @param genreName
	 * @return the genre that the album belongs to, or null if none
	 * @throws NullPointerException if the name of the genre is null
	 */
	public static Genre findGenre(String genreName) throws NullPointerException{
		
		Stack<Genre> finderStack = new Stack<Genre>();
		Genre temp;
		
		if(Catalogue.mainGenres.isEmpty()){
			
			return null;			
		}
		
		finderStack.addAll(Catalogue.mainGenres);
		
		while(!finderStack.isEmpty()){
			
			temp = finderStack.pop();	
			
			if(temp.getName().equals(genreName)){				
				return temp;
			}			
			else{
				int i;				
				for(i = 0; i < temp.getChildren().size(); i++){					
					if(temp.getChildren().get(i).hasChildren() == true){						
						finderStack.add((Genre) temp.getChildren().get(i));						
					}					
				}				
			}			
		}				
		return null;
	}	
}
