import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.builder.Builder;

/*
 * Levent Eren
 * 
 * Assignment 3
 * 
 * 2022/12/1
 * 
 * Purpose:
 * The program sorts artist music data using three different data structures: ArrayList, Binary Search Tree, Hash Map
 * 
 * Input:
 * `gradle run --args "STRATEGY N_ARTISTS MAX_SONGS"` in the terminal where:
 * STRATEGY is one of "list", "tree", "hash" (required)
 * N_ARTISTS is an integer specifying the number of top artists used in the analysis (optional, defaults to 20)
 * MAX_SONGS is an integer specifying the number of songs used in the analysis (optional, defaults to the entire list)
 * 
 * Output:
 * Outputs the time it took the chosen strategy took given the number of entries
 * Then outputs the list of of the top N_ARTISTS artists in decreasing order
 */
public class Program {

        public static final String SONG_DATA_FILENAME = "charts.csv";

        /**
         * This constant will allow you to either read the data into
         * local memory (_your_ code will be faster), or to use persistent
         * storage (_your_ code will be slower, but uses a smaller
         * amount of memory).
         */
        public static final boolean READ_INTO_MEMORY = true;

        /**
         * This constant will allow you to display the header
         * information for the data files (i.e., what columns have
         * what names). Set this to false before handing in the
         * assignment.
         */
        // TODO: adjust this constant as necessary
        public static final boolean SHOW_HEADERS = false;

        /**
         * When testing, you may want to choose a smaller portion of
         * the dataset. This number lets you limit it to only the
         * first MAX_ENTRIES. Setting this over 3.5 million will get
         * all the data. IMPORTANT NOTE: this will ONLY have an
         * effect when READ_INTO_MEMORAY is true
         */
        // TODO: adjust this constant as necessary
        public static final int DEFAULT_MAX_SONGS = Integer.MAX_VALUE;
        public static int max_songs = DEFAULT_MAX_SONGS;

        public static final int DEFAULT_N_ARTISTS = 20;

        /**
         * This constant represents the folder that contains the
         * data. You should not have to adjust this.
         */
        public static final String DATA_DIRECTORY = "data";

        /**
         * This static field manages timing in your code. You can
         * and should reuse it to time your code.
         */
        public static Stopwatch stopwatch = new Stopwatch();

        /**
         * CSV Record constants
         */
        private static final String ARTIST_VARIABLE_NAME = "artists";

        /**
         * The main method of the program
         * 
         * @param args the arguments to this main program provided
         *             on the command line (none)
         * @throws IOException when the data files cannot be read
         *                     properly
         */
        public static void main(String[] args) throws IOException {

                Path dir = Paths.get(DATA_DIRECTORY);

                // this program requires an argument
                if (args.length == 0) {
                        // no argument found, print usage
                        System.out.println("ERROR: No arguments provided.");
                        printUsage();
                } else {
                        // arguments found, process them and go through main program logic

                        // read in the first argument: strategy (list, tree, or hash)
                        String strategy = args[0].trim().toLowerCase();

                        // read in the second argument if provided (top n to print out)
                        int nArtists = DEFAULT_N_ARTISTS;
                        if (args.length >= 2) {
                                nArtists = Integer.parseInt(args[1]);
                        }

                        // read in the third argument if provided (number of song entries)
                        if (args.length >= 3) {
                                max_songs = Integer.parseInt(args[2]);
                        }

                        // read in data from data file
                        Iterable<CSVRecord> songData = readData(
                                        dir.resolve(SONG_DATA_FILENAME), "song chart entries",
                                        READ_INTO_MEMORY, false);

                        // apply the appropriate strategy
                        if (strategy.equals("list")) {
                                findTopUsingArrayList(songData, nArtists);
                        } else if (strategy.equals("tree")) {
                                findTopUsingTreeMap(songData, nArtists);
                        } else if (strategy.equals("hash")) {
                                findTopUsingHashMap(songData, nArtists);
                        } else {
                                // error: strategy incorrect. Print usage
                                System.out.println("ERROR: \"" + args[0] + "\"" + "not recognized as a strategy.");
                                printUsage();
                        }

                }

        }

        /**
         * Method to find top artists using a map implemented with an ArrayLIst
         * 
         * @param allSongs the Iterable object that lets you
         *                 iterate over all of the data
         * @param n        the number of users to report the top N
         *                 for
         */
        public static void findTopUsingArrayList(
                        Iterable<CSVRecord> allSongs, int n) {
                // start the stopwatch
                stopwatch.start();
                int numEntries = 0;
                int numMissing = 0;
                ArrayList<ArtistCount> list = new ArrayList<ArtistCount>();
                // iterate through the rows
                for (CSVRecord record : allSongs) {
                        numEntries++;
                        if (record.isSet(ARTIST_VARIABLE_NAME)) {
                                String[] artists = parseRawArtistList(record.get(ARTIST_VARIABLE_NAME));
                                // iterate through the artists mentioned in the song
                                for (int i = 0; i < artists.length; i++) {
                                        ArtistCount aCount = new ArtistCount(artists[i], 1);
                                        // if list is not empty, if so add new artist count
                                        if (list.size() > 0) {
                                                int exists = -1;
                                                // check if this artist count already exists
                                                for (int j = 0; j < list.size(); j++) {
                                                        if (list.get(j).equals(aCount))
                                                                exists = j; // since index in array list is required,
                                                                            // cannot use boolean instead "-1" is
                                                                            // "false"
                                                }
                                                if (exists != -1) { // if exists increase count
                                                        list.get(exists).increment();
                                                } else {
                                                        list.add(aCount); // if doesn't exist add new
                                                }
                                        } else {
                                                list.add(aCount);
                                        }
                                }

                                System.out.println();
                        } else {
                                numMissing++;
                        }
                }
                // sort in decreasing order
                Collections.sort(list);
                Collections.reverse(list);
                String s = "";
                // makes sure there are enough artists, if not only output available ones not
                // top 20
                int limit = n;
                if (list.size() < n)
                        limit = list.size();
                // remove first 20
                for (int i = 0; i < limit; i++) {
                        s += "\n   ~ " + list.get(i);
                }
                // stop watch now that method over and now only left is to output the result
                stopwatch.stop();
                System.out.println("ArrayList took " + stopwatch.getElapsedSeconds() + " seconds to process "
                                + max_songs + " entries.");
                System.out.println("The List of The Top " + n + " Artists: " + s);
        }

        /**
         * Method to find top artists using a map implemented with a TreeMap
         * 
         * @param allSongs the Iterable object that lets you
         *                 iterate over all of the data
         * @param n        the number of users to report the top N
         *                 for
         */
        public static void findTopUsingTreeMap(
                        Iterable<CSVRecord> allSongs, int n) {
                // start the stopwatch
                stopwatch.start();
                int numEntries = 0;
                int numMissing = 0;
                TreeMap<String, ArtistCount> map = new TreeMap<>();
                // iterate through the rows
                for (CSVRecord record : allSongs) {
                        numEntries++;
                        if (record.isSet(ARTIST_VARIABLE_NAME)) {
                                String[] artists = parseRawArtistList(record.get(ARTIST_VARIABLE_NAME));
                                // iterate through the artists mentioned in the song
                                for (int i = 0; i < artists.length; i++) {
                                        // if key does not exists add new entry
                                        if (!map.containsKey(artists[i])) {
                                                map.put(artists[i], new ArtistCount(artists[i], 1));
                                        } else { // else increment count and add back
                                                ArtistCount aCount = map.get(artists[i]);
                                                aCount.increment();
                                                map.put(artists[i], aCount);
                                        }
                                }

                                System.out.println();
                        } else {
                                numMissing++;
                        }
                }
                // populate priority queue
                PriorityQueue<ArtistCount> pq = new PriorityQueue<ArtistCount>(Collections.reverseOrder());
                for (String a : map.keySet()) {
                        pq.add(map.get(a));
                }
                String s = "";
                // makes sure there are enough artists, if not only output available ones not
                // top 20
                int limit = n;
                if (map.size() < n)
                        limit = map.size();
                // remove from max heap
                for (int i = 0; i < limit; i++) {
                        s += "\n   ~ " + pq.remove();
                }
                // stop watch now that method over and now only left is to output the result
                stopwatch.stop();
                System.out.println("Binary Search Tree took " + stopwatch.getElapsedSeconds() + " seconds to process "
                                + max_songs + " entries.");
                System.out.println("The List of The Top " + n + " Artists: " + s);
        }

        /**
         * Method to find top artists using a map implemented with a HashMap
         * 
         * @param allSongs the Iterable object that lets you
         *                 iterate over all of the data
         * @param n        the number of users to report the top N
         *                 for
         */
        public static void findTopUsingHashMap(
                        Iterable<CSVRecord> allSongs, int n) {

                // start the stopwatch
                Stopwatch watch = new Stopwatch();
                watch.start();
                int numEntries = 0;
                int numMissing = 0;
                HashMap<String, ArtistCount> map = new HashMap<>();
                // iterate through the rows
                for (CSVRecord record : allSongs) {
                        numEntries++;
                        if (record.isSet(ARTIST_VARIABLE_NAME)) {
                                String[] artists = parseRawArtistList(record.get(ARTIST_VARIABLE_NAME));
                                // iterate through the artists mentioned in the song
                                for (int i = 0; i < artists.length; i++) {
                                        // if key does not exists add new entry
                                        if (!map.containsKey(artists[i])) {
                                                map.put(artists[i], new ArtistCount(artists[i], 1));
                                        } else { // else increment count and add back
                                                ArtistCount aCount = map.get(artists[i]);
                                                aCount.increment();
                                                map.put(artists[i], aCount);
                                        }
                                }

                                System.out.println();
                        } else {
                                numMissing++;
                        }
                }
                // populate priority queue
                PriorityQueue<ArtistCount> pq = new PriorityQueue<ArtistCount>(Collections.reverseOrder());
                for (String a : map.keySet()) {
                        pq.add(map.get(a));
                }
                String s = "";
                // makes sure there are enough artists, if not only output available ones not
                // top 20
                int limit = n;
                if (map.size() < n)
                        limit = map.size();
                // remove from max heap
                for (int i = 0; i < limit; i++) {
                        s += "\n   ~ " + pq.remove();
                }
                // stop watch now that method over and now only left is to output the result
                stopwatch.stop();
                System.out.println("HashMap took " + stopwatch.getElapsedSeconds() + " seconds to process " + max_songs
                                + " entries.");
                System.out.println("The List of The Top " + n + " Artists: " + s);
        }

        /**
         * This method reads the data into an Iterable object.
         * 
         * @param path           the path of the file to read from
         * @param description    the description to use when
         *                       reporting the type of data
         * @param readIntoMemory true if the data should be read
         *                       into memory (it takes a lot of
         *                       memory!), false if the Iterable
         *                       object should just go through the
         *                       file.
         * @param printHeader    true if this method should print
         *                       the header information (i.e., which
         *                       column has what name).
         * @return an Iterable object with all of the data in
         *         CSVRecord objects
         * @throws IOException if the file could not be read
         */
        public static Iterable<CSVRecord> readData(Path path,
                        String description, boolean readIntoMemory,
                        boolean printHeader) throws IOException {
                stopwatch.reset();
                stopwatch.start();

                CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader()
                                .parse(new FileReader(path.toFile()));
                Map<String, Integer> headerMap = parser.getHeaderMap();
                Iterable<CSVRecord> iterable = () -> parser.iterator();

                if (readIntoMemory) {
                        List<CSVRecord> list2 = StreamSupport
                                        .stream(iterable.spliterator(), false)
                                        .limit(max_songs).collect(Collectors.toList());

                        System.out.printf(
                                        "Finished reading %,d %s into memory in %f seconds.\n",
                                        list2.size(), description,
                                        stopwatch.getElapsedSeconds());

                        iterable = list2;
                }

                if (printHeader) {
                        System.out.println("Data available:");
                        for (String key : headerMap.keySet()) {
                                int value = headerMap.get(key);
                                System.out.printf("\t%d = %s\n", value, key);
                        }
                }

                return iterable;
        }

        /**
         * printUsage
         * 
         * Prints out the usage instructions to standard out
         */
        public static void printUsage() {
                System.out.println("USE: \'gradle run --args \"STRATEGY N_ARTISTS MAX_SONGS\"\'");
                System.out.println("\twhere:");
                System.out.println("\t\tSTRATEGY is one of \"list\", \"tree\", \"hash\" (required)");
                System.out.println(
                                "\t\tN_ARTISTS is an integer specifying the number of songs used in the analysis (optional, 20)");
                System.out.println(
                                "\t\tMAX_SONGS is an integer specifying the number of songs used in the analysis (optional, defaults to the entire list)");
                System.out.println("\texample:");
                System.out.println("\t\tgradle run --args \"hash 20 50000\"");
        }

        /**
         * parseRawArtistList
         * 
         * Helper function to handle a list of artists in the CSV file
         * 
         * @param rawArtistList a string in the format "['ARTIST1', 'ARTIST2',
         *                      'ARTIST3']"
         * @return an array of strings representing the full set of artist names (e.g.,
         *         {"ARTIST1", "ARTIST2", "ARTIST3"})
         */
        private static String[] parseRawArtistList(String rawArtistList) {
                int rawArtistNameLength = rawArtistList.length();
                return rawArtistList.substring(2, rawArtistNameLength - 2).split("\', \'");
        }
}
