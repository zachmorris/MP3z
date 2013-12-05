package ca.ubc.ece.eece210.mp3;

import java.util.ArrayList;



/**
 * Represents a genre (or collection of albums/genres).
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public final class Genre extends Element {
	String name;
	
	/**
	 * Creates a new genre with the given name.
	 * 
	 * @param name the name of the genre.
	 */
	public Genre(String name) {
		this.name = name;
		//until a genre is classified as a subgenre, it is a mainGenre
		Catalogue.mainGenres.add(this);
	}

	/**
	 * Restores a genre from its given string representation.
	 * 
	 * @param stringRepresentation
	 * @return a new genre with references to children (albums, sub-genres)
	 * and the parentGenre
	 */
	public static Genre restoreCollection(String stringRepresentation) {
		// TODO implement
		return null;
	}

	/**
	 * Returns the string representation of a genre
	 * 
	 * @return String with references to children (albums, sub-genres) and
	 * the parentGenre
	 */
	public String getStringRepresentation() {
		//TODO implement
		
		Genre temp;
		int i;
		//can't cast? we're hooped!
		//
		//Not by a long shot.
		//well, I can't believe that fucking worked
		Element[] a = new Element[children.size()];
		Element[] orpheus = children.toArray(a);
		Genre greg;
		String stringy;
		
		
		StringBuilder builder = new StringBuilder();
		
		temp = parentGenre;		
		
		while(temp != null){
			builder.insert(0, "<parentGenre>" + temp.getName() + "</parentGenre>\n");
			temp = temp.parentGenre;
			
		}		
		
		builder.insert(0, "<genre>\n");
		
		builder.append("<name>" + name + "</name>\n");
		builder.append(childrenToString(orpheus));
		builder.append("</genre>");
		
		return(builder.toString());

	}

	/**
	 * Adds the given album or genre to this genre
	 * 
	 * @param b the element to be added to the collection.
	 * 
	 * @return adds the element b to this genre
	 */
	public void addToGenre(Element b) {
	
		if(b.hasChildren() == true){
			//classified as a subgenre means removal from the mainGenres
			Catalogue.mainGenres.remove(b);
			b.parentGenre = this;
		}
		this.addChild(b);
	}
	
	
	
	/**
	 * Removes the given album or genre from this genre
	 * 
	 * @param b the element to be removed from the collection
	 * 
	 * @return removes the element b from this genre
	 */
	public void removeFromGenre(Element b){
		this.removeChild(b);
		b.parentGenre = null; //TODO is this the correct default of a thingy
	}
	
	/**
	 * Returns the string name of the genre
	 * 
	 * @return the name of this genre
	 */
	public String getName(){
		return name;
	}
	
	public String childrenToString(Element[] orpheus){
		StringBuilder builder = new StringBuilder();
		int i;
		
		
		
		for(i = 0; i < orpheus.length; i++){
			if(!orpheus[i].hasChildren()){
				builder.append("<album>" + orpheus[i] + "</album>\n");
			}
			else{
				builder.append("<sub-genre>" + orpheus[i] + "</sub-genre>\n");
				Element[] a = new Element[orpheus[i].children.size()];
				Element[] eurydice = orpheus[i].children.toArray(a);
				builder.append(childrenToString(eurydice));
			}
		}
		
		
		return(builder.toString());
		//well, it technically works. not quite the readability I'd like, though

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