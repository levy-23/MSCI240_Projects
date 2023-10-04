
/**
 * TestDodgeballManager
 * A test class that tests DodgeballManager
 * 
 * Uses JUnit 5
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;

public class TestDodgeballManager {

	DodgeballManager dm1; // a dodgeballmanager to run fresh for each test

	@BeforeEach // runs before each unit test to set up a fresh DodgeballManager
	void setupCase1() {
		LinkedList<String> throwers = new LinkedList<String>();
		throwers.add("Walt");
		throwers.add("Skyler");
		throwers.add("Jesse");
		LinkedList<String> dodgers = new LinkedList<String>();
		dodgers.add("Jane");
		dodgers.add("Hank");
		dodgers.add("Saul");
		dm1 = new DodgeballManager(throwers, dodgers);
	}

	DodgeballManager dm2; // a dodgeballmanager to run fresh for each test

	@BeforeEach // runs before each unit test to set up a fresh DodgeballManager
	void setupCase2() {
		LinkedList<String> throwers = new LinkedList<String>();
		throwers.add("Walt");
		throwers.add("Skyler");
		throwers.add("Jesse");
		throwers.add("Homelander");
		throwers.add("Stormfront");
		throwers.add("Starlight");
		LinkedList<String> dodgers = new LinkedList<String>();
		dodgers.add("Jane");
		dodgers.add("Hank");
		dodgers.add("Saul");
		dodgers.add("MM");
		dodgers.add("Frenchie");
		dodgers.add("Butcher");
		dm2 = new DodgeballManager(throwers, dodgers);
	}

	@Test
	/*
	 * testDodgeballManager_Constructor_Typical()
	 * 
	 * Purpose: Check a Typical case for the constructor
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * activeDodgers: 3
	 * benchedPlayers: 0
	 * throwers: "Walt 0, Skyler 0, Jesse 0"
	 * dodgers: "Jane0, Hank 0, Saul 0"
	 * bench: "empty"
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_Constructor_Typical() {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
		ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(stream);
		PrintStream ps2 = new PrintStream(stream2);
		PrintStream ps3 = new PrintStream(stream3);

		assertEquals(dm1.nActiveDodgers(), 3);
		assertEquals(dm1.nBenchedPlayers(), 0);

		dm1.printThrowers(ps);
		assertEquals(stream.toString(), "Walt 0, Skyler 0, Jesse 0");
		dm1.printDodgers(ps2);
		assertEquals(stream2.toString(), "Jane 0, Hank 0, Saul 0");
		dm1.printBench(ps3);
		assertEquals(stream3.toString(), "empty");
	}

	@Test
	/*
	 * testDodgeballManager_Constructor_Extreme()
	 * 
	 * Purpose: Check an Extreme case for the constructor
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse", "Homelander", "Stromfront", "Starlight"
	 * dodgers: "Jane", "Hank", "Saul", "MM", "Frenchie", "Butcher"
	 * 
	 * output:
	 * activeDodgers: 6
	 * benchedPlayers: 0
	 * throwers:
	 * "Walt 0, Skyler 0, Jesse 0, Homelander 0, Stormfront 0, Starlight 0"
	 * dodgers: "Jane0, Hank 0, Saul 0, MM 0, Frenchie 0, Butcher 0"
	 * bench: "empty"
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_Constructor_Extreme() {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
		ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(stream);
		PrintStream ps2 = new PrintStream(stream2);
		PrintStream ps3 = new PrintStream(stream3);

		assertEquals(dm2.nActiveDodgers(), 6);
		assertEquals(dm2.nBenchedPlayers(), 0);

		dm2.printThrowers(ps);
		assertEquals(stream.toString(), "Walt 0, Skyler 0, Jesse 0, Homelander 0, Stormfront 0, Starlight 0");
		dm2.printDodgers(ps2);
		assertEquals(stream2.toString(), "Jane 0, Hank 0, Saul 0, MM 0, Frenchie 0, Butcher 0");
		dm2.printBench(ps3);
		assertEquals(stream3.toString(), "empty");
	}

	@Test
	/*
	 * testDodgeballManager_Constructor_Failure()
	 * 
	 * Purpose: Check a Failure case for the constructor
	 * 
	 * input:
	 * throwers: ""
	 * dodgers: ""
	 * 
	 * output:
	 * 
	 * Exception expected:
	 * IlligalArgumentException
	 */
	public void testDodgeballManager_Constructor_Failure() {
		LinkedList<String> throwers = new LinkedList<String>();
		LinkedList<String> dodgers = new LinkedList<String>();
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			DodgeballManager dmEmpty = new DodgeballManager(throwers, dodgers);
		});
	}

	@Test
	/*
	 * testDodgeballManager_printThrowers_Typical()
	 * 
	 * Purpose: Check a Typical case for the printThrowers method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * throwers: "Walt 0, Skyler 0, Jesse 0"
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_printThrowers_Typical() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();

		PrintStream ps = new PrintStream(stream);

		dm1.printThrowers(ps);
		assertEquals(stream.toString(), "Walt 0, Skyler 0, Jesse 0");
	}

	@Test
	/*
	 * testDodgeballManager_PrintThrowers_Extreme()
	 * 
	 * Purpose: Check an Extrme case for the printThrowers method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse", "Homelander", "Stromfront", "Starlight"
	 * dodgers: "Jane", "Hank", "Saul", "MM", "Frenchie", "Butcher"
	 * 
	 * output:
	 * throwers:
	 * "Walt 0, Skyler 0, Jesse 0, Homelander 0, Stormfront 0, Starlight 0"
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_printThrowers_Extreme() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();

		PrintStream ps = new PrintStream(stream);

		dm2.printThrowers(ps);
		assertEquals(stream.toString(), "Walt 0, Skyler 0, Jesse 0, Homelander 0, Stormfront 0, Starlight 0");
	}

	@Test
	/*
	 * testDodgeballManager_printDodgers_Typical()
	 * 
	 * Purpose: Check a Typical case for the printDodgers method
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * dodgers: "Jane 0, Hank 0, Saul 0"
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_printDodgers_Typical() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();

		PrintStream ps = new PrintStream(stream);

		dm1.printDodgers(ps);
		assertEquals(stream.toString(), "Jane 0, Hank 0, Saul 0");
	}

	@Test
	/*
	 * testDodgeballManager_PrintDodgers_Extreme()
	 * 
	 * Purpose: Check an Extrme case for the printDodgers method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse", "Homelander", "Stromfront", "Starlight"
	 * dodgers: "Jane", "Hank", "Saul", "MM", "Frenchie", "Butcher"
	 * 
	 * output:
	 * dodgers: "Jane 0, Hank 0, Saul 0, MM 0, Frenchie 0, Butcher 0"
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_printDodgers_Extreme() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();

		PrintStream ps = new PrintStream(stream);

		dm2.printDodgers(ps);
		assertEquals(stream.toString(), "Jane 0, Hank 0, Saul 0, MM 0, Frenchie 0, Butcher 0");
	}

	@Test
	/*
	 * testDodgeballManager_isThrower_Typical()
	 * 
	 * Purpose: Check a Typical case for the isThrower method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * boolean: true
	 * boolean: true
	 * boolean: true
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_isThrower_Typical() {
		assertTrue(dm1.isThrower("Walt"));
		assertTrue(dm1.isThrower("Skyler"));
		assertTrue(dm1.isThrower("Jesse"));
	}

	@Test
	/*
	 * testDodgeballManager_isThrower_Failure()
	 * 
	 * Purpose: Check a Failure case for the isThrower method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * boolean: false
	 * boolean: false
	 * boolean: false
	 * boolean: false
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_isThrower_Failure() {
		assertFalse(dm1.isThrower("Jane"));
		assertFalse(dm1.isThrower("Hank"));
		assertFalse(dm1.isThrower("Saul"));
		assertFalse(dm1.isThrower("walt"));
		assertFalse(dm1.isThrower("Jim"));
	}

	@Test
	/*
	 * testDodgeballManager_isActiveDodger_Typical()
	 * 
	 * Purpose: Check a Typical case for the isActiveDodger method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * boolean: true
	 * boolean: true
	 * boolean: true
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_isActiveDodger_Typical() {
		assertTrue(dm1.isActiveDodger("Jane"));
		assertTrue(dm1.isActiveDodger("Hank"));
		assertTrue(dm1.isActiveDodger("Saul"));
	}

	@Test
	/*
	 * testDodgeballManager_isActiveDodger_Extreme()
	 * 
	 * Purpose: Check an Extreme case for the isActiveDodger method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * boolean: true
	 * boolean: false
	 * boolean: true
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_isActiveDodger_Extreme() {
		dm1.hit("Walt", "Hank");
		assertTrue(dm1.isActiveDodger("Jane"));
		assertFalse(dm1.isActiveDodger("Hank"));
		assertTrue(dm1.isActiveDodger("Saul"));
	}

	@Test
	/*
	 * testDodgeballManager_isActiveDodger_Failure()
	 * 
	 * Purpose: Check a Failure case for the isActiveDodger method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * boolean: false
	 * boolean: false
	 * boolean: false
	 * boolean: false
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_isActiveDodger_Failure() {
		assertFalse(dm1.isActiveDodger("Walt"));
		assertFalse(dm1.isActiveDodger("Skyler"));
		assertFalse(dm1.isActiveDodger("Jesse"));
		assertFalse(dm1.isActiveDodger("jane"));
		assertFalse(dm1.isActiveDodger("Jim"));
	}

	@Test
	/*
	 * testDodgeballManager_isBenchedPlayer_Typical()
	 * 
	 * Purpose: Check a Typical case for the isBenchedPlayer method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * boolean: true
	 * boolean: true
	 * boolean: true
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_isBenchedPlayer_Typical() {
		dm1.hit("Walt", "Hank");
		dm1.hit("Walt", "Jane");
		assertTrue(dm1.isBenchedPlayer("Jane"));
		assertTrue(dm1.isBenchedPlayer("Hank"));
	}

	@Test
	/*
	 * testDodgeballManager_isBenchedPlayer_Failure()
	 * 
	 * Purpose: Check a Failure case for the isBenchedPlayer method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * boolean: false
	 * boolean: false
	 * boolean: false
	 * boolean: false
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_isBenchedPlayer_Failure() {
		dm1.hit("Walt", "Jane");
		assertFalse(dm1.isBenchedPlayer("Walt"));
		assertFalse(dm1.isBenchedPlayer("Skyler"));
		assertFalse(dm1.isBenchedPlayer("Jesse"));
		assertFalse(dm1.isBenchedPlayer("Hank"));
		assertFalse(dm1.isBenchedPlayer("Saul"));
		assertFalse(dm1.isBenchedPlayer("jane"));
		assertFalse(dm1.isBenchedPlayer("Jim"));
	}

	@Test
	/*
	 * testDodgeballManager_nBenchedPlayers_Typical()
	 * 
	 * Purpose: Check a Typical case for the nBenchedPlayers method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * int: 0
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_nBenchedPlayers_Typical() {
		assertEquals(dm1.nBenchedPlayers(), 0);
	}

	@Test
	/*
	 * testDodgeballManager_nBenchedPlayers_Failure()
	 * 
	 * Purpose: Check a Failure case for the isThrower method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * int: 3
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_nBenchedPlayers_Failure() {
		dm1.hit("Walt", "Hank");
		dm1.hit("Walt", "Jane");
		dm1.hit("Walt", "Saul");
		assertFalse(dm1.nBenchedPlayers() != 3);
	}

	@Test
	/*
	 * testDodgeballManager_nActiveDodgers_Typical()
	 * 
	 * Purpose: Check a Typical case for the nActiveDodgers method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * int: 3
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_nActiveDodgers_Typical() {
		assertEquals(dm1.nActiveDodgers(), 3);
	}

	@Test
	/*
	 * testDodgeballManager_nActiveDodgers_Failure()
	 * 
	 * Purpose: Check a Failure case for the nActiveDodgers method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * 
	 * output:
	 * int: 0
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_nActiveDodgers_Failure() {
		dm1.hit("Walt", "Hank");
		dm1.hit("Walt", "Jane");
		dm1.hit("Walt", "Saul");
		assertFalse(dm1.nActiveDodgers() != 0);
	}

	@Test
	/*
	 * testDodgeballManager_Dodge_Typical()
	 * 
	 * Purpose: Check a Typical case for the dodge method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * dodge("Walt,"Saul")
	 * dodge("Skyler,"Hank")
	 * 
	 * output:
	 * throwers: "Walt 0, Skyler 0, Jesse 0"
	 * dodgers: "Jane 0, Hank 1, Saul 1"
	 * bench: "empty"
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_Dodge_Typical() {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
		ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(stream);
		PrintStream ps2 = new PrintStream(stream2);
		PrintStream ps3 = new PrintStream(stream3);

		dm1.dodge("Walt", "Saul");
		dm1.dodge("Skyler", "Hank");

		dm1.printThrowers(ps);
		assertEquals(stream.toString(), "Walt 0, Skyler 0, Jesse 0");
		dm1.printDodgers(ps2);
		assertEquals(stream2.toString(), "Jane 0, Hank 1, Saul 1");
		dm1.printBench(ps3);
		assertEquals(stream3.toString(), "empty");
	}

	@Test
	/*
	 * testDodgeballManager_Dodge_Failure()
	 * 
	 * Purpose: Check a Failure case for the dodge method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * dodge(null,"Jane")
	 * 
	 * output:
	 * 
	 * Exception expected:
	 * IlligalArgumentException
	 */
	public void testDodgeballManager_Dodge_Failure() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			dm1.dodge(null, "Jane");
		});
	}

	@Test
	/*
	 * testDodgeballManager_Hit_Typical()
	 * 
	 * Purpose: Check a Typical case for the hit method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * hit("Walt,"Saul")
	 * hit("Skyler,"Hank")
	 * 
	 * output:
	 * throwers: "Walt 1, Skyler 1, Jesse 0"
	 * dodgers: "Jane 0"
	 * bench: "Saul 0, Hank 0"
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_Hit_Typical() {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
		ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(stream);
		PrintStream ps2 = new PrintStream(stream2);
		PrintStream ps3 = new PrintStream(stream3);

		dm1.hit("Walt", "Saul");
		dm1.hit("Skyler", "Hank");

		dm1.printThrowers(ps);
		assertEquals(stream.toString(), "Walt 1, Skyler 1, Jesse 0");
		dm1.printDodgers(ps2);
		assertEquals(stream2.toString(), "Jane 0");
		dm1.printBench(ps3);
		assertEquals(stream3.toString(), "Saul 0, Hank 0");
	}

	@Test
	/*
	 * testDodgeballManager_Hit_Failure()
	 * 
	 * Purpose: Check a Failure case for the hit method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * hit("","Hank")
	 * 
	 * output:
	 * 
	 * Exception expected:
	 * IlligalArgumentException
	 */
	public void testDodgeballManager_Hit_Failure() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			dm1.hit("", "Hank");
		});
	}

	@Test
	/*
	 * testDodgeballManager_CatchBall_Typical()
	 * 
	 * Purpose: Check a Typical case for the catchBall method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * catch("Jesse,"Hank")
	 * 
	 * output:
	 * throwers: "Walt 1, Skyler 1, Jesse 0"
	 * dodgers: "Jane 0, Hank 1, Saul 0"
	 * bench: "emoty"
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_CatchBall_Typical() {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
		ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(stream);
		PrintStream ps2 = new PrintStream(stream2);
		PrintStream ps3 = new PrintStream(stream3);

		dm1.catchBall("Jesse", "Hank");

		dm1.printThrowers(ps);
		assertEquals(stream.toString(), "Walt 0, Skyler 0, Jesse 0");
		dm1.printDodgers(ps2);
		assertEquals(stream2.toString(), "Jane 0, Hank 1, Saul 0");
		dm1.printBench(ps3);
		assertEquals(stream3.toString(), "empty");
	}

	@Test
	/*
	 * testDodgeballManager_CatchBall_Failure()
	 * 
	 * Purpose: Check another Failure case for the catchBall method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * catch("Jesse","Tim")
	 * 
	 * output:
	 * 
	 * Exception expected:
	 * IlligalArgumentException
	 */
	public void testDodgeballManager_CatchBall_Failure() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			dm1.hit("Jesse", "Tim");
		});
	}

	@Test
	/*
	 * testDodgeballManager_CatchBallReturn_Typical()
	 * 
	 * Purpose: Check a Typical case for the catchBall method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * hit("Walt","Jane")
	 * catch("Jesse,"Hank","Jane")
	 * 
	 * output:
	 * throwers: "Walt 1, Skyler 1, Jesse 0"
	 * dodgers: "Jane 0, Hank 1, Saul 0"
	 * bench: "emoty"
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_CatchBall_TypicalReturn() {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
		ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(stream);
		PrintStream ps2 = new PrintStream(stream2);
		PrintStream ps3 = new PrintStream(stream3);

		dm1.hit("Walt", "Jane");
		dm1.catchBall("Jesse", "Hank", "Jane");

		dm1.printThrowers(ps);
		assertEquals(stream.toString(), "Walt 1, Skyler 0, Jesse 0");
		dm1.printDodgers(ps2);
		assertEquals(stream2.toString(), "Hank 1, Jane 0, Saul 0");
		dm1.printBench(ps3);
		assertEquals(stream3.toString(), "empty");
	}

	@Test
	/*
	 * testDodgeballManager_PrintMVP_Typical()
	 * 
	 * Purpose: Check a Typical case for the printMVP method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * hit("Walt","Jane")
	 * hit("Walt","Hank")
	 * hit("Walt","Saul")
	 * 
	 * output:
	 * String: "Walt 3"
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_printMVP_Typical() {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(stream);

		dm1.hit("Walt", "Jane");
		dm1.hit("Walt", "Hank");
		dm1.hit("Walt", "Saul");

		dm1.printMVP(ps);
		assertEquals(stream.toString(), "Walt 3");
	}

	@Test
	/*
	 * testDodgeballManager_PrintMVP_Typical()
	 * 
	 * Purpose: Check a Typical case for the printMVP method
	 * 
	 * input:
	 * throwers: "Walt", "Skyler", "Jesse"
	 * dodgers: "Jane", "Hank", "Saul"
	 * dodge("Walt","Jane")
	 * dodge("Skyler","Hank")
	 * dodge("Saul","Saul")
	 * hit("Walt","Jane")
	 * hit("Skyler","Hank")
	 * hit("Saul","Saul")
	 * 
	 * output:
	 * String: "Walt 1, Skyler 1, Jesse 1, Jane 1, Hank 1, Saul 1"
	 * 
	 * Exception expected: N/A
	 */
	public void testDodgeballManager_printMVP_Extreme() {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(stream);

		dm1.dodge("Walt", "Jane");
		dm1.dodge("Skyler", "Hank");
		dm1.dodge("Jesse", "Saul");
		dm1.hit("Walt", "Jane");
		dm1.hit("Skyler", "Hank");
		dm1.hit("Jesse", "Saul");

		dm1.printMVP(ps);
		assertEquals(stream.toString(), "Walt 1, Skyler 1, Jesse 1, Jane 1, Hank 1, Saul 1");
	}
}