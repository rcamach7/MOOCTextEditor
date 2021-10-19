package spelling;

import java.util.TreeSet;

/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary {
   private TreeSet<String> dictionary;
	
    public DictionaryBST() {
    	dictionary = new TreeSet<String>();
    }
    
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	word = word.toLowerCase();
    	if (!dictionary.contains(word)) {
    		dictionary.add(word);
    		return true;
    	}
    	return false;
    }

    /** Return the number of words in the dictionary */
    public int size() {
    	return dictionary.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	s = s.toLowerCase();
    	if (dictionary.contains(s)) {
    		return true;
    	}
        return false;
    }

}
