
/**
 * Levent Eren
 * 
 * 2022/12/22
 * 
 * Purpose:
 * Town node that holds additional information such as name and distance and adjacency maps.
 * 
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Town implements Comparable<Town> {
    public String name;
    Float distance = Float.MAX_VALUE;
    List<Town> shortestPath = new LinkedList<Town>();
    Map<String, Float> adjTowns = new HashMap<>();

    /**
     * Constructor for the town node
     * 
     * @param name Name of the town
     */
    public Town(String name) {
        this.name = name;
    }

    /**
     * Method to add a road to link another town to this town
     * 
     * @param name     Name of the town being added
     * 
     * @param distance distance away the other town is
     */
    public void addRoad(String town, float distance) {
        adjTowns.put(town, distance);
    }

    /**
     * Method to return the adjacent towns key set
     * 
     * @return Returns key set
     */
    public Set<String> getAdjacentSet() {
        return adjTowns.keySet();
    }

    /**
     * Method that returns map of adjacent towns
     * 
     * @return return map of adjacent towns
     */
    public Map<String, Float> getAdjTowns() {
        return adjTowns;
    }

    /**
     * Method that finds the distance away an adjacent town is
     * 
     * @param key of the town being accessed
     * 
     * @return Returns the distance
     */
    public float findDistance(String key) {
        return adjTowns.get(key);
    }

    /**
     * To String method for the town
     */
    public String toString() {
        return " " + name + "(" + distance + ")";
    }

    /**
     * To use the comparitor
     */
    @Override
    public int compareTo(Town town) {
        return Float.compare(this.distance, town.distance);
    }
}
