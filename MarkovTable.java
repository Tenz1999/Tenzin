import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class MarkovTableVar {

    public static final int KEY_LENGTH = 3;          
    // constant. If key length is 2 set it to 2 for one fish , two fish, read fish and blue fish
   
   HashMap<ArrayList<String>, ArrayList<String>> table = new HashMap<ArrayList<String>, ArrayList<String>>();  
   // creating generics that takes in Array<String>

    private boolean readFile(String fileName) {             // private if/else method for readFile
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {         // start try method from new scanner
            List<String> words = readWords(scanner);        // reads each words one by one through readWords through each List of strings
            buildMarkovTable(words);                        // building the hashMap using words
        } catch (FileNotFoundException e) {                 // catch method that takes FilesNotFoundException
            return false;                                   // false statement
        }
        return true;
    }

    private List<String> readWords(Scanner scanner) {         // private List that takes String as generic
        List<String> words = new ArrayList<>();               // creating new arrayList to list of words
        while(scanner.hasNext()) {                            // while there is next word going on
            String word = scanner.next();                     // reads the words one by one
            word = word.replaceAll(",", "");                  // replacing comma to blank space
            word = word.toLowerCase(Locale.ROOT);             // Upper case words set to lower case
            words.add(word);                                  // add new words to the list of words
        }
        return words;                                         // While the scanner is out of while loop, return back words
    }

    private void buildMarkovTable(List<String> words){
        for(int i = 0; i < words.size(); i++) {                    // First for loop is check through size of the words
            ArrayList<String> key = new ArrayList<>();             // making new words to set it to ArrayList String
            for (int j = 0; j < KEY_LENGTH; j++) {                 // second (for loop) to check through constant keyLength = 3
                key.add(words.get((i + j) % words.size()));        // adds words to the key and formula is to wrap around to words                                 // add new
            }
            String value = words.get((i + KEY_LENGTH)% words.size()); // reads value after keyLengths(it was the) to one value (next word to key)
            if (table.get(key) == null) {                             // if key does not exist
                table.put(key, new ArrayList<>());                    // add new key and new ArrayList when key is null
            }
            table.get(key).add(value);                                // add value to that key
        }
    }


    HashMap<ArrayList<String>, ArrayList<String>> getTable() {        // to return getter to hashMap
        return table;
    }


    public static void main(String[] args) {

        MarkovTableVar markovTableTest = new MarkovTableVar();       // create a new MarkovTableVar and assign to markovTableTest
        Scanner scan = new Scanner(System.in);
        int keyLength = scan.nextInt();
        System.out.println("");
        boolean isFileReadSuccessful = markovTableTest.readFile("itwasthe");    // if read the file, true. else false
        System.out.println("isFileReadSuccessful: " + isFileReadSuccessful);    // prints out true/false
        for (Map.Entry<ArrayList<String>, ArrayList<String>> entry : markovTableTest.getTable().entrySet()){
            System.out.print(entry.getKey());                                   // gets the key - [the, period, was]
            System.out.print(":");                                              // prints :
            System.out.println(entry.getValue());                               // print out [in]
        }

    }


}



