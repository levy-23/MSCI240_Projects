import java.io.PrintStream;
import java.util.List;

/*
 * Levent Eren
 * Assignment 2
 * 22/11/10
 * 
 * Description:
 * This program simulates a unique dodgeball between two teams consisting of throwers and dodgers and finds the MVP(s) at the end of the game.
 * Throwers individually throw balls to the dodgers to attempt to hit them and thus score one point.
 * Dodgers individually either get hit by, dodge, or catch the ball. 
 * Getting hit means the dodger recieved no points and goes to the bench. Both dodging and catching reward the dodger with one point. 
 * Catching the ball allows the dodge to return a player from the bench. 
 * The game ends when all the dodgers are on the bench. 
 * 
 * Input:
 * The main class will ask the user for the actions that make up the game. The teams are already built, the program will ask for inputs to make up the actions.
 * The program will cycle through asking for the following three inputs for each throw until the game is over.
 * 1. The name of the thrower which is currently throwing the ball. 2. The dodger which the thrower aims. 3. The reaction the dodger gives (hit, dodge, or catch).
 * Note: if the dodger catches the ball, the program will request an input for the player returning from the bench. 
 * These inputs will repeat until the game is over
 * 
 * Output:
 * The program will output the players on each team, their points, and if they are on the bench. 
 * This output will be presented at the begining and after each completed throw. 
 * At the end of the game the program will output the MVP(s) and the top score of the game. 
 * 
 * @author Levent Eren
 */
public class DodgeballManager {
	private DodgeballNode throwerList;
	private DodgeballNode dodgerList;
	private DodgeballNode benchList;

	public DodgeballManager(List<String> throwers, List<String> dodgers) {

		// Throw exception if invalid lists
		if (throwers.isEmpty() || dodgers.isEmpty() || throwers.get(0) == null || dodgers.get(0) == null) {
			throw new IllegalArgumentException();
		}

		throwerList = buildList(throwerList, throwers);
		dodgerList = buildList(dodgerList, dodgers);

	}

	/*
	 * This method builds and returns a list of nodes given which list, and the
	 * names.
	 * 
	 * @param type, identify which of three lists (DodgeballNode)
	 * 
	 * @param names, player names to fill out the list (List<String>)
	 * 
	 * @return (DodgeballNode)
	 */
	public DodgeballNode buildList(DodgeballNode type, List<String> names) {
		int size = names.size();
		type = new DodgeballNode(names.get(0));
		DodgeballNode current = type;
		for (int i = 1; i < size; i++) {
			current.next = new DodgeballNode(names.get(i));
			current = current.next;
		}
		return type;
	}

	///////////////////////
	// Boolean Accessors //
	///////////////////////

	/*
	 * This method returns whether the inputed name is a thrower
	 * 
	 * @param name, name to check (String)
	 * 
	 * @return (boolean)
	 */
	public boolean isThrower(String name) {
		return isPlayer(throwerList, name);
	}

	/*
	 * This method returns whether the inputed name is an active dodger
	 * 
	 * @param name, name to check (String)
	 * 
	 * @return (boolean)
	 */
	public boolean isActiveDodger(String name) {
		return isPlayer(dodgerList, name);
	}

	/*
	 * This method returns whether the inputed name is a benched player
	 * 
	 * @param name, name to check (String)
	 * 
	 * @return (boolean)
	 */
	public boolean isBenchedPlayer(String name) {
		return isPlayer(benchList, name);
	}

	/*
	 * This method returns whether the inputed name is in the given list
	 * 
	 * @param type, identify which of three lists (DodgeballNode)
	 * 
	 * @param name, name to check (String)
	 * 
	 * @return (boolean)
	 */
	public boolean isPlayer(DodgeballNode type, String name) {
		DodgeballNode current = type;
		if (type == null)
			return false;
		while (current.next != null) {
			if (current.name.equals(name)) {
				return true;
			}
			current = current.next;
		}
		if (current.name.equals(name)) {
			return true;
		}
		return false;
	}

	/////////////////////
	// Count Accessors //
	/////////////////////

	/*
	 * This method returns the number of benched players
	 * 
	 * @return (int)
	 */
	public int nBenchedPlayers() {
		return activePlayers(benchList);
	}

	/*
	 * This method returns the number of active dodgers
	 * 
	 * @return (int)
	 */
	public int nActiveDodgers() {
		return activePlayers(dodgerList);
	}

	/*
	 * This method returns the number of players in a given list
	 * 
	 * @param type, identify which of three lists (DodgeballNode)
	 * 
	 * @return (int)
	 */
	public int activePlayers(DodgeballNode type) {
		DodgeballNode current = type;
		int n = 0;
		if (type != null) {
			n++;
		} else {
			return 0;
		}
		while (current.next != null) {
			n++;
			current = current.next;
		}
		return n;
	}

	/*
	 * This is a method to find the node object of the list given the player name
	 * 
	 * @param type, identify which of three lists (DodgeballNode)
	 * 
	 * @param player, identify player name (String)
	 * 
	 * @return (DodgeballNode)
	 */
	public DodgeballNode findPlayer(DodgeballNode type, String player) {
		DodgeballNode current = type;
		if (current.name.equals(player)) {
			return current;
		}
		while (current.next != null) {
			if (current.name.equals(player)) {
				return current;
			}
			current = current.next;
		}
		return current;
	}

	/*
	 * This is a method to add hit dodger onto end of bench
	 * 
	 * @param type, identify player (DodgeballNode)
	 */
	public void appendBench(DodgeballNode player) {
		// if bench is empty, bench is only player
		if (benchList == null) {
			benchList = player;
		} else {
			DodgeballNode current = benchList;
			// get to end of list
			while (current.next != null) {
				current = current.next;
			}
			// add at the end
			current.next = player;
		}
	}

	////////////////
	// Main Logic //
	////////////////

	/*
	 * This method performs the dodge action in the game. The dodger recieves a
	 * point.
	 * 
	 * @param thowerName, the name of the thrower throwing the ball (String)
	 * 
	 * @param dodgerName, the name of the dodger that dodger the ball (String)
	 */
	public void dodge(String throwerName, String dodgerName) {
		// Check if error in input
		if (throwerName == null || dodgerName == null || !isThrower(throwerName) || !isActiveDodger(dodgerName)
				|| throwerName.equals("") || dodgerName.equals("")) {
			throw new IllegalArgumentException();
		}
		// Increase score of Dodger
		DodgeballNode currentDodger = findPlayer(dodgerList, dodgerName);
		currentDodger.score++;
	}

	/*
	 * This method performs the hit action in the game. The dodger is sent to the
	 * bench and the thrower recieves a point.
	 * 
	 * @param thowerName, the name of the thrower throwing the ball (String)
	 * 
	 * @param dodgerName, the name of the dodger gets hit with the ball (String)
	 */
	public void hit(String throwerName, String dodgerName) {
		// Check if error in input
		if (throwerName == null || dodgerName == null || !isThrower(throwerName) || !isActiveDodger(dodgerName)
				|| throwerName.equals("") || dodgerName.equals("")) {
			throw new IllegalArgumentException();
		}
		// Increase score of thrower
		DodgeballNode currentThrower = findPlayer(throwerList, throwerName);
		currentThrower.score++;
		// remove from dodgers and add to bench
		DodgeballNode prev = dodgerList;
		DodgeballNode current = dodgerList;
		// if dodger is at begining no need store previous node
		if (current.name.equals(dodgerName)) {
			appendBench(current);
			// remove dodger from dodgers list by skipping this node, unless last then just
			// remove
			if (dodgerList.next != null) {
				dodgerList = dodgerList.next;
			} else {
				dodgerList = null;
			}
			current.next = null;
		} else {
			while (current.next != null) {
				// update current node before previous node to keep previous node stored
				current = current.next;
				if (current.name.equals(dodgerName)) {
					appendBench(current);
					// remove dodger from dodgers list by skipping this node, unless last then just
					// remove
					if (current.next != null) {
						prev.next = current.next;
					} else {
						prev.next = null;
					}
					current.next = null;
				}
				// now update previous node
				prev = current;
			}
		}
	}

	/*
	 * This method performs the catch action in the game. The dodger recieves a
	 * point.
	 * 
	 * @param thowerName, the name of the thrower throwing the ball (String)
	 * 
	 * @param dodgerName, the name of the dodger catches the ball (String)
	 */
	public void catchBall(String throwerName, String dodgerName) {
		// Check if error in input
		if (throwerName == null || dodgerName == null || !isThrower(throwerName) || !isActiveDodger(dodgerName)
				|| throwerName.equals("") || dodgerName.equals("")) {
			throw new IllegalArgumentException();
		}
		// Increase score of catcher
		DodgeballNode currentDodger = findPlayer(dodgerList, dodgerName);
		currentDodger.score++;
	}

	/*
	 * This method performs the catch action in the game with returning a player.
	 * The selected player on the bench returns to the dodgers, and the dodger that
	 * caught the ball recieves a point.
	 * 
	 * @param thowerName, the name of the thrower throwing the ball (String)
	 * 
	 * @param dodgerName, the name of the dodger catches the ball (String)
	 * 
	 * @param benchBackName, the name of the player returning from the bench
	 * (String)
	 */
	public void catchBall(String throwerName, String dodgerName, String benchBackName) {
		// Check if error in input
		if (throwerName == null || dodgerName == null || benchBackName == null || !isBenchedPlayer(benchBackName)
				|| !isThrower(throwerName) || !isActiveDodger(dodgerName)
				|| throwerName.equals("") || dodgerName.equals("")) {
			throw new IllegalArgumentException();
		}
		// Increase score of catcher
		DodgeballNode currentDodger = findPlayer(dodgerList, dodgerName);
		currentDodger.score++;
		// Add player back from the bench after catcher and save the rest of the
		// throwers in a temp pointer
		DodgeballNode backBench = findPlayer(benchList, benchBackName);
		DodgeballNode temp = null;
		if (currentDodger.next == null) {
			currentDodger.next = backBench;
		} else {
			temp = currentDodger.next;
			currentDodger.next = backBench;
		}
		// remove returned player from bench
		DodgeballNode currentB = benchList;
		DodgeballNode prevB = benchList;
		// if player is at begining no need store previous node
		if (currentB.name.equals(benchBackName)) {
			// remove player from bench list by skipping this node, unless last then just
			// remove
			if (benchList.next != null) {
				benchList = benchList.next;
			} else {
				benchList = null;
			}
		} else {
			while (currentB.next != null) {
				// update current node before previous node to keep previous node stored
				currentB = currentB.next;
				if (currentB.name.equals(benchBackName)) {
					// remove player from bench list by skipping this node, unless last then just
					// remove
					if (currentB.next != null) {
						prevB.next = currentB.next;
					} else {
						prevB.next = null;
					}
					currentB.next = null;
				}
				// now update previous node
				prevB = currentB;
			}
		}

		// add the rest of the throwers after returned player
		currentDodger.next.next = temp;
	}

	///////////////////
	// Main Printing //
	///////////////////

	/*
	 * This method prints the throwers to the selected stream.
	 * 
	 * @param stream, selected stream for the printed output (PrintSteam)
	 */
	public void printThrowers(PrintStream stream) {
		printList(throwerList, stream);
	}

	/*
	 * This method prints the dodgers to the selected stream.
	 * 
	 * @param stream, selected stream for the printed output (PrintSteam)
	 */
	public void printDodgers(PrintStream stream) {
		printList(dodgerList, stream);
	}

	/*
	 * This method prints the benched players to the selected stream.
	 * 
	 * @param stream, selected stream for the printed output (PrintSteam)
	 */
	public void printBench(PrintStream stream) {
		printList(benchList, stream);

	}

	/*
	 * This method prints the players in the selected list to the selected stream
	 * 
	 * @param type, identify which of three lists (DodgeballNode)
	 * 
	 * @param stream, selected stream for the printed output (PrintSteam)
	 */
	public void printList(DodgeballNode type, PrintStream stream) {
		DodgeballNode current = type;
		String str = "";
		if (!(type == null)) {
			str = current.name + " " + current.score;
			while (current.next != null) {
				current = current.next;
				str += ", " + current.name + " " + current.score;
			}
			stream.print(str);
		} else {
			stream.print("empty");
		}
	}

	//////////////////
	// Printing MVP //
	//////////////////

	/*
	 * This method prints the MVP(s) of the game to the selected stream
	 * 
	 * @param stream, selected stream for the printed output (PrintSteam)
	 */
	public void printMVP(PrintStream stream) {
		// TODO
		String MVPS = "";
		int topScore = 0;
		DodgeballNode currentT = throwerList;
		DodgeballNode currentB = benchList;

		// Check MVP in thrower list

		// Check first becuase fence post
		if (currentT.score > topScore) {
			// if new max, overwrite MVPS and score
			topScore = currentT.score;
			MVPS = currentT.name + " " + topScore;
		} else if (currentT.score == topScore) {
			// if share max, add onto MVPS. Turn on boolean for final print with good
			// grammar
			MVPS += ", " + currentT.name + " " + topScore;
		}
		// check the rest of the list
		while (currentT.next != null) {
			currentT = currentT.next;
			if (currentT.score > topScore) {
				topScore = currentT.score;
				MVPS = currentT.name + " " + topScore;
			} else if (currentT.score == topScore) {
				MVPS += ", " + currentT.name + " " + topScore;
			}
		}

		// do same for bench list. no dodgers list because it should be empty
		if (currentB.score > topScore) {
			topScore = currentB.score;
			MVPS = currentB.name + " " + topScore;
		} else if (currentB.score == topScore) {
			MVPS += ", " + currentB.name + " " + topScore;
		}
		while (currentB.next != null) {
			currentB = currentB.next;
			if (currentB.score > topScore) {
				topScore = currentB.score;
				MVPS = currentB.name + " " + topScore;
			} else if (currentB.score == topScore) {
				MVPS += ", " + currentB.name + " " + topScore;
			}
		}

		// if multiple mvps then print with good grammar
		stream.print(MVPS);

	}

	///////////
	// BONUS //
	///////////
	public void printSortedScores(PrintStream stream) {
		// TODO
	}
}
