package ca.ubc.ece.eece210.mp3;



/**
 * Represents a genre (or collection of albums/genres).
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public final class Genre extends Element {
	//or should we have an array list here?
	//if we have children in Element, we don't need it here
	String name;
	
	/**
	 * Creates a new genre with the given name.
	 * 
	 * @param name
	 *            the name of the genre.
	 */
	public Genre(String name) {
		this.name = name;
	}

	/**
	 * Restores a genre from its given string representation.
	 * 
	 * @param stringRepresentation
	 */
	public static Genre restoreCollection(String stringRepresentation) {
		// TODO implement
		return null;
	}

	/**
	 * Returns the string representation of a genre
	 * 
	 * @return
	 */
	public String getStringRepresentation() {
		Object[] songlistArrayed;
		songlistArrayed = songlist.toArray();
		int i;
		
		StringBuilder builder = new StringBuilder();
		builder.append("<album>\n");
		builder.append("<title>" + title + "</title>\n");
		builder.append("<performer>" + performer + "</performer>\n");
		
		for(i = 0; i < songlistArrayed.length; i++){
			builder.append("<song>" + songlistArrayed[i] + "</song>\n");
		}
		builder.append("</album>");
		
		return(builder.toString());
		return null;
	}

	/**
	 * Adds the given album or genre to this genre
	 * 
	 * @param b
	 *            the element to be added to the collection.
	 */
	public void addToGenre(Element b) {
		//if the genre doesn't have a parent already
		//if the album doesn't have a genre already
		if(b.hasChildren() == true){
			b.parentGenre = this;
		}
		this.addChild(b);
	}
	
	
	
	/**
	 * Removes the given album or genre from this genre
	 * 
	 * @param b
	 *  		  the element to be removed from the collection
	 */
	public void removeFromGenre(Element b){
		// where does removeChild(b); go?
		this.removeChild(b);
	}
	
	/**
	 * Returns the string name of the genre
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns true, since a genre can contain other albums and/or
	 * genres.
	 */
	@Override
	public boolean hasChildren() {
		return true;
	}
}