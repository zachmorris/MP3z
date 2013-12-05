package ca.ubc.ece.eece210.mp3;

import static org.junit.Assert.*;

import java.util.*;
import java.io.*;

import org.junit.Test;

public class AlbumTest {

	@Test
	public void notReallyATest() throws FileNotFoundException{
		//multivariable genre testing
		//works with multiple genres
		String genreName;
		
		StringBuilder builder = new StringBuilder();
		String temp;
		String temp2;
		Album currentAlbum;
		Genre currentGenre;
		Genre tempGenre = null;
		Genre rock = new Genre("Rock");
		Genre alternative = new Genre("Alternative Rock");
		Genre garage = new Genre("Garage Rock");
		Album brothers = createTestAlbum();
		
		rock.addToGenre(alternative);
		alternative.addToGenre(garage);
		System.out.println("garage's dad: " + garage.parentGenre);
		System.out.println("alternative dad: " + alternative.parentGenre);
		System.out.println("rock's dad: " + rock.parentGenre);
		
		int i, j;
		
		Scanner input = new Scanner(new File("test data/brothers.txt"));
		
		while(input.hasNextLine()){
			builder.append(input.nextLine());
		}
		
		temp = builder.toString();
		
		
		currentAlbum = brothers;
		currentGenre = null;
		
		//how good is your game
		//so we need to iterate through the genres and test each individually
		do{
			
			i = temp.lastIndexOf("<genre>");
			j = temp.indexOf("<genre>");
			
		
			temp2 = temp.substring(temp.lastIndexOf("<genre>"), temp.lastIndexOf("</genre>"));
			System.out.println("current genre's string: " + temp2);
			
			genreName = temp2.split("<genre>")[1].split("</genre>")[0];
			System.out.println("currently looking for: " + genreName);
			
			tempGenre = brothers.findGenre(genreName);
			System.out.println("we found this one: " + tempGenre.getName());
			
			if(currentAlbum != null){
				//I think we need to do a little more here
				if(genreName != null && tempGenre != null){
					currentAlbum.addToGenre(tempGenre);
					System.out.println("Album has been added to temp genre");
					
				}
				else if(genreName != null && tempGenre == null){
					//create new genre
					currentAlbum.parentGenre = new Genre(genreName);
					System.out.println("We created a new genre! " + currentAlbum.parentGenre.getName());
				}
				else{
					//this means that our string rep had no genre
					currentAlbum.parentGenre = null; //TODO make unclassified?
					System.out.println("we weren't given a thingy :(");
				}
				System.out.println("passing the torch");
			}
			if(currentGenre != null){
				//I think we need to do a little more here
				
				if(genreName != null && tempGenre != null){
					//who is adding who here
					tempGenre.addToGenre(currentGenre);
					System.out.println(tempGenre.getName() + " is now the parent of " + currentGenre.getName());
				}
				else if(genreName != null && tempGenre == null){
					//create new genre
					currentGenre.parentGenre = new Genre(genreName);
					System.out.println("created a new genre: " + currentGenre.parentGenre.getName());
				}
				else{
					//this means that our string rep had no genre
					currentGenre.parentGenre = null; //TODO make unclassified?
					System.out.println("no parent genre, I think?");
				}
				
				//I think I'm too focused to see what this means
				//needs break plox
				//please kill me for saying plox
				currentGenre = tempGenre;
				System.out.println("currentgenre's parent: " + currentGenre.parentGenre);
				
			}
			if(currentAlbum != null && currentGenre == null){
				currentGenre = currentAlbum.parentGenre;
				currentAlbum = null;				
			}
			System.out.println("at the end of the day, currentGenre = " + currentGenre);
			
			temp = temp.substring(temp.indexOf("<genre>"), temp.lastIndexOf("<genre>"));
			
		}while(j < i);
		
		System.out.println("garage's dad: " + garage.parentGenre);
		System.out.println("alternative dad: " + alternative.parentGenre);
		System.out.println("rock's dad: " + rock.parentGenre);
		System.out.println("brothers parent genre: " + brothers.parentGenre.getName());
		System.out.println("that parentGenre: " + brothers.parentGenre.parentGenre.getName());
		System.out.println("and so on: " + brothers.parentGenre.parentGenre.parentGenre.getName());
		input.close();
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
		
		System.out.println("testGetAlbum output: " + brothersTwo.getGenre().getName());
		fail("incomplete");
		
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
