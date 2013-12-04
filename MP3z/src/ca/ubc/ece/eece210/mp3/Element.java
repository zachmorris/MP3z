package ca.ubc.ece.eece210.mp3;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class to represent an entity in the catalogue. The element (in this
 * implementation) can either be an album or a genre.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public abstract class Element {
	//arrayList or LinkedList here?
	ArrayList<Element> children = new ArrayList<Element>();
	Genre parentGenre;// = new Genre("unclassified");  -  should this be a thing?
	//I think automatically set to unclassified might be a bad idea. what about 
	//top level stuff, like rock and/or roll? PARENT GENRE UNCLASSIFIED
	//what about setting it at the album level?
	//sounds good
	//sounds bad
	
	/**
	 * Returns all the children of this entity. They can be albums or
	 * genres. In this particular application, only genres can have
	 * children. Therefore, this method will return the albums or genres
	 * contained in this genre.
	 * 
	 * @return the children
	 */
	public List<Element> getChildren() {
		// TODO implement this
		if(this.hasChildren() != false){
			return children;
		}
		
		return null;
	}

	/**
	 * Adds a child to this entity. Basically, it is adding an album or genre
	 * to an existing genre
	 * 
	 * @param b
	 *            the entity to be added.
	 * @throws IllegalArgumentException 
	 * 			  if the parameter b is a leaf object (ie an Album)            
	 * 
	 */
	protected void addChild(Element b) throws IllegalArgumentException{
		// TODO implement this (should throw an exception if trying to add
		// to a leaf object);
		//leaf has no children? so we should check that the thing we
		//are adding from (ie, this) has no kids
		if(this.hasChildren() == false){
			throw new IllegalArgumentException();
		}
		
		children.add(b);
	}
	
	/**
	 * Removes a child from this entity. Removes an album or genre from an
	 * existing genre
	 * 
	 * @param b 
	 * 			the entity to be be removed
	 * @throws IllegalArgumentException 
	 * 			  if the parameter b is a leaf object (ie an Album)            
	 * 
	 */
	protected void removeChild(Element b) throws IllegalArgumentException{
		//TODO implement this
		//if the thing to which we are trying to add a kid can't have kids,
		//then we throw the exception
		//aggro crag wait for you is he's
		if(this.hasChildren() == false){
			throw new IllegalArgumentException();
		}
		
		children.remove(b);
	}

	/**
	 * Abstract method to determine if a given entity can (or cannot) contain
	 * any children.
	 * 
	 * @return true if the entity can contain children, or false otherwise.
	 */
	public abstract boolean hasChildren();
}