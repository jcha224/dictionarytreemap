 package treemap;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;
    
/**
    Program to read in strings from a file
    and insert them into a dictionary.
    
    @author Donald Chinn, Monika Sobolewska
    @version 2016

    Flags:
      -b  binary search tree
      -v  AVL tree
      -t  trie
      -j  Java built-in class TreeMap
      -x  do nothing
*/
public class DictionaryApp {
    
    // static variables used to identify the
    // data structure/algorithm to use
    // (based on the command line argument)
    private static final int NOALG = 0;
    private static final int useBST = 1;
    private static final int useAVL = 2;
    private static final int useTrie = 3;
    private static final int useJavaTreeMap = 4;
    private static final int useNothing = 7;
        
    /**
     * Return the next word in a file.
     * @param file  the file to read from
     * @return  the next word in the file of length >= 3, or null if there is no next word
     * A word is defined here as a consecutive sequence of alphanumeric characters.
     */
    private static String getWord(Scanner file) {
       String word = null;
       while (file.hasNext()) {
           word = file.next();
           word = word.replaceAll("\\W", "").toLowerCase();
           if (word.length() >= 3)
               break;
       }
       if (word != null && word.length() < 3 ) word = null;
       return word;
       
    }
    
    /**
     * The driver method for the word counting application.
     */
    public static void main(String[] args) {
        boolean error = false;

        // timer variables
        long totalTime = 0;
        long startTime = 0;
        long finishTime = 0;
        int numInsertions = 0;

        int whichAlgorithm = NOALG;
    
        Scanner infile = null;
        
          MyTreeMap<String, Integer> bst = new BinarySearchTree<String, Integer>();
          TrieTree<Character, Integer> trie = new TrieTree<Character, Integer>();
 		  MyTreeMap<String, Integer> avl = new AvlTree<String, Integer>();
        TreeMap<String, Integer> javaTreeMap = new TreeMap<String, Integer>();
    
        // Handle command line arguments.
        // Usage: -[bvtjx] input_filename
        // Options:
        //   -b  use a standard binary search tree
        //   -v  use the recursive implementation of an AVL tree
        //   -t  trie
        //   -j  Java built-in class TreeMap

        //   -x  no data structure (just read in the file)
        
        if ((args.length < 2) || (args.length > 2)) {
            System.out.println("Argument usage: -[bvsrjx] infile");
            error = true;
        } else {
            // figure out which option was chosen
            if (args[0].charAt(0) == '-') {
                switch (args[0].charAt(1)) {
                    case 'b':
                        whichAlgorithm = useBST;
                        break;
    
                    case 'v':
                        whichAlgorithm = useAVL;
                        break;
    
                    case 't':
                        whichAlgorithm = useTrie;
                        break;
                    
                    case 'j':
                        whichAlgorithm = useJavaTreeMap;
                        break;
                    
                    case 'x':
                        whichAlgorithm = useNothing;
                        break;
            
                    default:
                        System.out.print ("Usage: ");
                        System.out.println("-" + args[0].charAt(1) + " is not a valid option.");
                        error = true;
                        break;
                }
    
                // Get the input filename
                try {
                    infile = new Scanner(Paths.get(args[1]));
                }
                catch (IOException ioexception) {
                  System.out.println("Error: Could not open " + args[1] + ".");
                  error = true;
                }
            } else {
                System.out.println("Argument usage: -[bvrjx] filename");
                error = true;
            }
        }
    
        if (!error) {
    
            String currentWord;
            Integer numTimes;   // the number of times a key has been encountered so far
    
            // start the timer
            Date startDate = new Date();
            startTime = startDate.getTime();
          
            while ((currentWord = DictionaryApp.getWord(infile)) != null) {
                switch (whichAlgorithm) {
                    case useBST:
                        numTimes = bst.get(currentWord);
                        if(numTimes == null) {
                        	numTimes = 0;
                        }
                        numTimes++;
                        bst.put(currentWord, numTimes);
                        numInsertions++;
                        break;
        
                    case useAVL:
                        numTimes = avl.get(currentWord);
                        if(numTimes == null) {
                        	numTimes = 0;
                        }
                        numTimes++;
                        avl.put(currentWord, numTimes);
                        numInsertions++;
                        break;
        
                    case useTrie:
                    	Character[] temp = toCharArr(currentWord);
                    	numTimes = trie.get(temp);
                    	if(numTimes == null) {
                    		numTimes = 0;
                    	}
                    	numTimes++;
                    	trie.put(temp, numTimes);
                    	numInsertions++;
                        break;
                        
                    case useJavaTreeMap:
                        numTimes = javaTreeMap.get(currentWord);
                        if(numTimes == null)
                            numTimes = 0;
                        numTimes++;
                        javaTreeMap.put(currentWord, numTimes);
                        numInsertions++;
                        break;
                          
                    case useNothing:
                        // do nothing
                        numInsertions++;
                        break;
                }
            }
            
            // stop the insertion timer
            Date finishDate = new Date();
            finishTime = finishDate.getTime();
            totalTime += (finishTime - startTime);

            
            System.out.println("** Results for " + args[0]  + " option on file ");
            System.out.println("    " + args[1]);
            System.out.println("Time to do insertions: ");
            System.out.println(totalTime + " ms.");
            System.out.println("Number of insertions: " + numInsertions);
            
            switch (useNothing) {
            	case useBST:
                    System.out.println(bst);
                    break;
                case useAVL:
                    System.out.println(avl);
                    break;
                case useTrie:
                    System.out.println(trie);
                    break;
                 
                case useJavaTreeMap:
                    System.out.println(javaTreeMap);
                    break;
                case useNothing:
                    // do nothing
                    break;
            }        
    
            // stop the timer
            Date finishWrite = new Date();
            finishTime = finishWrite.getTime();
            totalTime += (finishTime - startTime);

            System.out.println("Time to do insertions and print the tree: ");
            System.out.println(totalTime + " ms.");    
        }
    
        infile.close();
    
        System.out.println("DictionaryApp is done. **");
    }
    
    /**
     * Helper method to convert string to Character array.
     * @param theString the string
     * @return the Character array
     */
    private static Character[] toCharArr(String theString) {
    	if(theString == null)
    		return null;
    	Character[] arr = new Character[theString.length()];
    	for(int i = 0; i < theString.length(); i++) {
    		arr[i] = new Character(theString.charAt(i));
    	}
    	return arr;
    }
    
}
