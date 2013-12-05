package ca.ubc.ece.eece210.mp3;

import static org.junit.Assert.*;

import java.util.*;
import java.io.*;

import org.junit.Test;

public class AlbumTest {

	@Test
	public void notReallyATest() throws FileNotFoundException{
	
	
	}
	
	@Test
	public void testAlbumToString() {
		Album brothers = createTestAlbum();
		Genre rock = new Genre("Rock");
		Genre alternative = new Genre("Alternative Rock");
		Genre garage = new Genre("Garage Rock");
		
		rock.addToGenre(alternative);
		alternative.addToGenre(garage);
		brothers.addToGenre(garage);
		
		System.out.println(brothers.getStringRepresentation());
		//compare visually with the brothers test data
	}
	
	@Test
	public void testGetAlbumFromString() throws FileNotFoundException{
		//TODO implement with multiple genres
		Scanner input = new Scanner(new File("test data/brothers.txt"));
		StringBuilder temp = new StringBuilder();
		Genre rock = new Genre("Rock");
		Genre alternative = new Genre("Alternative Rock");
		Genre garage = new Genre("Garage Rock");
				
		rock.addToGenre(alternative);
		alternative.addToGenre(garage);
		
		
		while(input.hasNextLine()){
			temp.append(input.nextLine());
		}
		
		Album brothersTwo = new Album(temp.toString());
		
		assertEquals("Garage Rock", brothersTwo.parentGenre.getName());
		assertEquals("Brothers", brothersTwo.getTitle());
		assertEquals("The Black Keys", brothersTwo.getPerformer());		
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
