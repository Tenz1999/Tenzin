import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Random;


public class MarkovGenerator {

    public static final int KEY_LENGTH = 3; // constant. If key length is 2 set it to 2 for one fish , two fish, read fish and blue fish
    HashMap<ArrayList<String>, ArrayList<String>> table = new HashMap<ArrayList<String>, ArrayList<String>>();  // crating generics that takes

    private boolean readFile(String fileName) {     // private if/else method for readFile
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {         // start try method from new scanner
            List<String> words = readWords(scanner);        // reads each words one by one through readWords through each List of strings
            buildMarkovTable(words);                        // building the hashMap using words
        } catch (FileNotFoundException e) {                 // catch method that takes FilesNotFoundException
            return false;                                   // false statement
        }
        return true;
    }

    private List<String> readWords(Scanner scanner) {       // private List that takes String as generic
        List<String> words = new ArrayList<>();                 // creating new arrayList to list of words
        while(scanner.hasNext()) {                      // while there is next word going on
            String word = scanner.next();               // reads the words one by one
            word = word.replaceAll(",", "");        // replacing comma to blank space
            word = word.toLowerCase(Locale.ROOT);           // Upper case words set to lower case
            words.add(word);                            // add new words to the list of words
        }
        return words;                               // While the scanner is out of while loop, return back words
    }

    private void buildMarkovTable(List<String>words){    // Creating random Key


        for(int i = 0; i < words.size(); i++) {  // First for loop is check through size of the words
            ArrayList<String> key = new ArrayList<>();  // making new words to set it to ArrayList String
            for (int j = 0; j < KEY_LENGTH; j++) {   // second (for loop) to check through constant keyLength = 3
                key.add(words.get((i + j) % words.size()));        // adds words to the key and formula is to wrap around to words                                 // add new
            }
            String value = words.get((i + KEY_LENGTH)% words.size()); // reads value after keyLengths(it was the) to one value (next word to key)
            if (table.get(key) == null) {       // if key does not exist
                table.put(key, new ArrayList<>());  // add new key and new ArrayList when key is null
            }
            table.get(key).add(value);      // add value to that key
        }
    }


    HashMap<ArrayList<String>, ArrayList<String>> getTable() {  // to return getter to hashMap
        return table;
    }


    public static void main(String[] args) {

        MarkovGenerator markovTableTest = new MarkovGenerator();  // create a new MarkovTableVar and assign to markovTableTest
        boolean isFileReadSuccessful = markovTableTest.readFile("itwasthe");    // if read the file, true. else false

        ;         // print out the random value in console

        /* 1. Pull an arbitrary key out of the Markov table
           2. Pull a value at random from the ArrayList of values associated with that key
           3. Print the value
           4. Search for a new key, that consist of every word from the previous key except the first key,
              and with the value added to the end of the key.
           5. Print a value at random associated with this new key
           6. Repeat until a sufficient number of Markov words has been generated. */

        System.out.println("isFileReadSuccessful: " + isFileReadSuccessful); // prints out true/false

        Scanner scanner = new Scanner(System.in);       // create a user input
        System.out.println("How many words?");          // on console " How many words? "
        int n = scanner.nextInt();                      // reads from user input to int n
        System.out.println(markovTableTest.generateText(n));  // prints markovTableTest that generateText to variable n

    }

    public String generateText(int n) {
        List<String> words = new ArrayList<>();     // create a words into the new ArrayList
        while(n>0) {                                // while the number of words is greater than 0
            List<String> key = getRandomKey();         // generate a random key
            String value = getRandomValue(key);         // generate a random value
            words.add(value);                           // add words to the list of words
            n--;                                        // reduces number of words one by one and prints out words according to user input
            if(n <= 0) {                                // break the while loop if number of words is less than 0
                break;
            }
            key.add(value);                         // add value at the end of associated key
            key.remove(0);                      // remove the first key
            value = getRandomValue(key);                // get random value from the key
            words.add(value);                       // add value to the list of words
            n--;                                // reduces number of words one by one and prints out twice the words according to user input
        }
        return String.join(" ", words);         // return or prints out words(that is generated from break loop) connected by space
    }

    private static Random rand = new Random();
    private List<String> getRandomKey() {       // create private random key
        List<List<String>> keys = new ArrayList<>(table.keySet());  // create new ArrayList that contains keySet
        int randIndex = rand.nextInt(keys.size());           // generate random integer less than key size(limit)
        return keys.get(randIndex);                       // return key that is generated randomly
    }

    private String getRandomValue(List<String> key) {   // create private random value
        List<String> values = table.get(key);       // get list of Value from the key
        int randIndex = rand.nextInt(values.size());        // generate random integer less than value size(limit)
        return values.get(randIndex);               // return value that is generated randomly
    }


}



