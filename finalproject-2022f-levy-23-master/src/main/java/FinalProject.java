
/**
 * Levent Eren
 * 
 * Final Project
 * 
 * 2022/12/22
 * 
 * Purpose:
 * The program creates a basic GIS system for a region called "Flatland". 
 * This system finds the minimum distance between two town for weighted and unweighted roads.
 * 
 * 
 * Input:
 * Text file with the following structure: 
 * <n names of nodes, each on their own line as a string>
 * <m edges, each on their own line, given as "node_name1 node_name2 edge_weight">
 * <q queries, each on their own line, given as "source_name target_name">
 * 
 * Output:
 * Outputs the following sentances for the number of queries:
 * Minimum turns from <source_name> to <target_name>: <integer value of the minimum number of turns>
 * Minimum distance from <source_name> to <target_name>: <float value of the minimum distance to 1 decimal point>
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FinalProject {

    private static Map<String, Town> adjacencyList;
    private static Map<String, Integer> turns;
    private static Map<String, Float> distances;
    private static Map<String, String> parents;

    /**
     * The main method of the program. Reads in input from a file and then prints
     * the solved minimum distances.
     * 
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        // Input the file
        Scanner s = new Scanner(new File("test2.txt"));

        // Initialize the required maps
        turns = new HashMap<>();
        distances = new HashMap<>();
        parents = new HashMap<>();
        adjacencyList = new TreeMap<String, Town>();

        // Add the towns
        int numTowns = s.nextInt();
        for (int i = 0; i < numTowns; i++) {
            addTown(s.next());
        }

        // Add the roads
        int numRoads = s.nextInt();
        String garbage = s.nextLine();
        for (int i = 0; i < numRoads; i++) {
            String inputRoad = s.nextLine();
            Scanner roadScanner = new Scanner(inputRoad);
            roadScanner.useDelimiter(" ");
            String town1 = roadScanner.next();
            String town2 = roadScanner.next();
            float distance = roadScanner.nextFloat();
            getTown(town1).addRoad(town2, distance);
            getTown(town2).addRoad(town1, distance);
        }

        // Shortest path
        int numQueries = s.nextInt();
        garbage = s.nextLine();
        for (int i = 0; i < numQueries; i++) {
            String inputRoad = s.nextLine();
            Scanner roadScanner = new Scanner(inputRoad);
            roadScanner.useDelimiter(" ");
            String town1 = roadScanner.next();
            String town2 = roadScanner.next();
            System.out.printf("%nMinimum turns from %s to %s: %d", town1, town2, minTurns(town1, town2));
            System.out.printf("%nMinimum distance from %s to %s: %.1f", town1, town2, minDistance(town1, town2));
        }
    }

    /**
     * Method to add towns into the graph
     * 
     * @param town The name of the town being added
     */
    public static void addTown(String town) {
        // Throw exception for town already added
        if (hasTown(town)) {
            throw new IllegalArgumentException(town + " is already added");
        }
        adjacencyList.put(town, new Town(town));
    }

    /**
     * Method to check if the towns is in the graph
     * 
     * @param town The name of the town being checked
     * 
     * @return Returns whether the town is already in the graph
     */
    public static boolean hasTown(String town) {
        return adjacencyList.containsKey(town);
    }

    /**
     * Method to get the Town object of the given town name
     * 
     * @param name The name of the town being returned
     * 
     * @return Returns the town object
     */
    public static Town getTown(String name) {
        return adjacencyList.get(name);
    }

    /**
     * Method to initialize some maps
     * 
     * @param mapName The name of the map being initialized
     * 
     */
    public static void mapName(Map name) {
        for (String town : adjacencyList.keySet()) {
            name.put(town, -1);
            parents.put(town, null);
        }
    }

    /**
     * Method to get the minimum turns between two towns
     * 
     * @param source      The starting town
     * 
     * @param destination The destination town
     * 
     * @return Returns the minimum number of turns
     */
    public static int minTurns(String source, String destination) {
        // Initilise the maps
        mapName(turns);

        // Add the source to the queue for the breadth first search
        Queue<String> q = new LinkedList<>();
        turns.put(source, 0);
        q.add(source);

        // Perform the breadth first search
        while (!q.isEmpty()) {
            String u = q.remove();

            for (String v : adjacencyList.get(u).getAdjacentSet()) {
                if (turns.get(v) == -1) {
                    turns.put(v, turns.get(u) + 1);
                    parents.put(v, u);
                    q.add(v);
                }
            }
        }

        return turns.get(destination) - 1;// minus 1 because not counting edges, counting turn. First edge is not a
                                          // turn.
    }

    /**
     * Method to get the minimum distance between two towns
     * 
     * @param source      The starting town
     * 
     * @param destination The destination town
     * 
     * @return Returns the minimum distance
     */
    public static float minDistance(String source, String destination) {
        // Initilise the maps
        for (String town : adjacencyList.keySet()) {
            distances.put(town, (float) -1);
            parents.put(town, null);
        }

        // Add the source to the priority queue for the dijkstra algorithm
        PriorityQueue<Town> q = new PriorityQueue<Town>();
        distances.put(source, (float) 0);
        Town sourceTown = adjacencyList.get(source); // Need to store Town object since they are getting compared
        q.add(sourceTown);

        // Perform the algorithm
        while (!q.isEmpty()) {
            Town old = q.remove();
            String oldN = old.name;
            for (String v : old.getAdjacentSet()) {
                float val = distances.get(oldN) + old.findDistance(v);
                if (distances.get(v) == (float) -1 || distances.get(v) > (val)) { // Also need to check if another path
                                                                                  // is shorter despite if already
                                                                                  // visited
                    distances.put(v, val);
                    parents.put(v, oldN);
                    Town newT = adjacencyList.get(v);
                    newT.distance = val; // Need to update distance before adding to queue to properly compare
                    q.add(newT);
                }

            }
        }

        return distances.get(destination);
    }

}
