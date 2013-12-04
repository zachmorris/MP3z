package ca.ubc.ece.eece210.mp3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class AlbumTest {

	@Test
	public void notReallyATest(){
		//alright, we have to find out how to call all the parent genres
		//just parent genre in a for loop, eh?
		Genre rock = new Genre("rock");
		Genre alternative = new Genre("alternative");
		Genre garage = new Genre("Garage");
		Album brothers = createTestAlbum();
		String temp;
		String tempGenreName;
		int i, j;
		
		rock.addToGenre(alternative);
		alternative.addToGenre(garage);
		brothers.addToGenre(garage);
		
		temp = brothers.getStringRepresentation();
		
		do{
			i = temp.indexOf("<genre>");
			j = temp.lastIndexOf("<genre>");
			
			//'ppears you need a catalogue to do such things
			//NOT ANYMORE (Kirby's gon steal your bitch)
			temp = temp.substring(temp.indexOf("</genre>") + 8 );
		}while(i < j);
		
		
	}
	
	@Test
	public void testAddGenreToGenre() {
		Genre rock = new Genre("Rock");
		Genre alternative = new Genre("Alternative Rock");
		
		rock.addToGenre(alternative);
		
		assertEquals(true, rock.children.contains(alternative));
	}
	
	@Test
	public void testAddAlbumToGenre() {
		Album brothers = createTestAlbum();
		
		Genre alternativeRock = new Genre("Alternative Rock");
		
		brothers.addToGenre(alternativeRock);			
		
		assertEquals(true, alternativeRock.children.contains(brothers));
	
	}
	
	@Test
	public void testRemoveAlbumFromGenre() {
		Album brothers = createTestAlbum();
		
		Genre alternativeRock = new Genre("Alternative Rock");
		
		brothers.addToGenre(alternativeRock);
		brothers.removeFromGenre();	
		
		
		assertEquals(false, alternativeRock.children.contains(brothers));
	
	}
	
	@Test
	public void testAlbumToString() {
		//make sure a created album has the correct string representation
				
		//TODO find out what correct string representation would look like
		assertEquals("Not yet sure", createTestAlbum().getStringRepresentation());
		
	}
	
	@Test
	public void testGetAlbumFromString() {
		//TODO should there be something file-y in here? Find out how to do file
		//I/O, I guess...
		fail("Not yet implemented");
	}
	
	@Test
	public void testGenreToString() {
		//TODO find correct string representation
		String correctString = new String("");
		Album brothers = createTestAlbum();
		Genre rock = new Genre("Rock");
		Genre alternativeRock = new Genre("Alternative Rock");
		
		rock.addChild(alternativeRock);
		//album.addgenre or genre.addChild?
		brothers.addToGenre(alternativeRock);
		
		
		assertEquals(correctString, rock.getStringRepresentation());
		
	}
	
	@Test
	public void testGenreFromString() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCatalogueToFile() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCatalogueFromFile() {
		fail("Not yet implemented");
	}
	
	/*
	 * Scenario 9: Write a test to verify the Genre inclusion rules
	 */
	@Test
	public void addAlbum() {
		fail("Not yet implemented");
	}
	
	/*
	 * Creates album suitable for test purposes
	 * 
	 * @return Album object suitable for testing
	 * 
	 */
	private Album createTestAlbum(){
		String brothersTitle = new String("Brothers");
		String blackKeys = new String("The Black Keys");
		ArrayList<String> brothersSongList = new ArrayList<String>();
		
		brothersSongList.add("Everlasting Light");
		brothersSongList.add("Next Girl");
		brothersSongList.add("Tighten Up");
		brothersSongList.add("Howling For You");
		brothersSongList.add("She's Long Gone");
		brothersSongList.add("Black Mud");
		brothersSongList.add("The Only One");
		brothersSongList.add("Too Afraid To Love You");
		brothersSongList.add("Ten Cent Pistol");
		brothersSongList.add("Sinister Kid");
		brothersSongList.add("The Go Getter");
		brothersSongList.add("I'm Not The One");
		brothersSongList.add("Unknown Brother");
		brothersSongList.add("Never Gonna Give You Up");
		brothersSongList.add("These Days");
	
		Album brothers = new Album(brothersTitle, blackKeys, brothersSongList);
		
		return brothers;
	}
	

}
