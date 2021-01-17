import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Random;


public class MarkovGenerator {

    public static final int KEY_LENGTH = 3; 
    HashMap<ArrayList<String>, ArrayList<String>> table = new HashMap<ArrayList<String>, ArrayList<String>>();  

    private boolean readFile(String fileName) {     
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {         
            List<String> words = readWords(scanner);        
            buildMarkovTable(words);                        
        } catch (FileNotFoundException e) {                 
            return false;                               
        }
        return true;
    }

    private List<String> readWords(Scanner scanner) {       
        List<String> words = new ArrayList<>();                
        while(scanner.hasNext()) {                      
            String word = scanner.next();              
            word = word.replaceAll(",", "");       
            word = word.toLowerCase(Locale.ROOT);           
            words.add(word);                           
        }
        return words;                              
    }

    private void buildMarkovTable(List<String>words){    


        for(int i = 0; i < words.size(); i++) {  
            ArrayList<String> key = new ArrayList<>();  
            for (int j = 0; j < KEY_LENGTH; j++) {   
                key.add(words.get((i + j) % words.size()));        
            }
            String value = words.get((i + KEY_LENGTH)% words.size()); 
            if (table.get(key) == null) {      
                table.put(key, new ArrayList<>());  
            }
            table.get(key).add(value);      
        }
    }


    HashMap<ArrayList<String>, ArrayList<String>> getTable() {  
        return table;
    }


    public static void main(String[] args) {

        MarkovGenerator markovTableTest = new MarkovGenerator(); 
        boolean isFileReadSuccessful = markovTableTest.readFile("itwasthe");   

        ;        

        /* 1. Pull an arbitrary key out of the Markov table
           2. Pull a value at random from the ArrayList of values associated with that key
           3. Print the value
           4. Search for a new key, that consist of every word from the previous key except the first key,
              and with the value added to the end of the key.
           5. Print a value at random associated with this new key
           6. Repeat until a sufficient number of Markov words has been generated. */

        System.out.println("isFileReadSuccessful: " + isFileReadSuccessful); // prints out true/false

        Scanner scanner = new Scanner(System.in);       
        System.out.println("How many words?");          
        int n = scanner.nextInt();                      
        System.out.println(markovTableTest.generateText(n));  

    }

    public String generateText(int n) {
        List<String> words = new ArrayList<>();     
        while(n>0) {                                
            List<String> key = getRandomKey();         
            String value = getRandomValue(key);         
            words.add(value);                           
            n--;                                        
            if(n <= 0) {                                
              break;
            }
            key.add(value);                        
            key.remove(0);                      
            value = getRandomValue(key);               
            words.add(value);                       
            n--;                                
          }
          return String.join(" ", words);       
        }

    private static Random rand = new Random();
    private List<String> getRandomKey() {      
        List<List<String>> keys = new ArrayList<>(table.keySet());  
        int randIndex = rand.nextInt(keys.size());          
        return keys.get(randIndex);                       
    }

    private String getRandomValue(List<String> key) {  
        List<String> values = table.get(key);       
        int randIndex = rand.nextInt(values.size());        
        return values.get(randIndex);              
    }


}



