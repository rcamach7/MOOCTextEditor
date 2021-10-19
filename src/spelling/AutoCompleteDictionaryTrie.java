package spelling;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {
    private TrieNode root;
    private int size;
    
    public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
	}
		
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word) {
		TrieNode currentTriePosition = root;
		word = word.toLowerCase();
		boolean isNewWord = false;
		
		char[] brokenDownWord = word.toCharArray();
		for (int i = 0 ; i  < brokenDownWord.length; i++) {
			// If the child i'th position of word is null inside current TrieNode - then initiate and set one.
			if (currentTriePosition.getChild(brokenDownWord[i]) == null) {
				currentTriePosition.insert(brokenDownWord[i]);
				isNewWord = true;
			}
			// Go inside the TrieNode just created, and then repeat the process for the next i'th character in word. 
			currentTriePosition = currentTriePosition.getChild(brokenDownWord[i]);
		}
		// Now that we are inside of the last TrieNode of word, we can set this one to true, because it represents a word.
		// Before however, we will indicate this will become a new word and inrease size.
		if (!currentTriePosition.endsWord()) {
			size++;
		}
		currentTriePosition.setEndsWord(true);
		if (isNewWord) {
			return true;
		} 
		return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size() {
	    return size;
	}
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) {
		TrieNode search = root;
		s = s.toLowerCase();
		
		char[] letter = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			if (search.getChild(letter[i]) == null) {
				return false;
			}
			search = search.getChild(letter[i]);
		}
		
		if (search.endsWord()) {
			return true;
		} 
		return false;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) {
    	 List<String> completions = new ArrayList<String>();
    	 TrieNode search = root;
    	 
    	 prefix = prefix.toLowerCase();
    	 char[] letters = prefix.toCharArray();
    	 
    	 for (int i = 0; i < prefix.length(); i++) {
    		 search = search.getChild(letters[i]);
    	 }
    	 if (search == null) {
    		 return completions;
    	 }
    	 
    	 // At this point, we are in as deep as where the prefix has sent us. 
    	 // Now we need to look beyond this TrieNode, and find all the valid nodes underneath. 
    	 Queue<TrieNode> myQueue = new LinkedList<TrieNode>();
    	 myQueue.add(search);
    	 while (!myQueue.isEmpty()) {
    		 TrieNode currentTrie = myQueue.remove();
    		 if (currentTrie.endsWord()) {
    			 if (completions.size() == numCompletions) {
    				 return completions;
    			 }
    			 completions.add(currentTrie.getText());
    		 }
    		 if (currentTrie != null) {
    			 for (char c : currentTrie.getValidNextCharacters()) {
    				 myQueue.add(currentTrie.getChild(c));
    			 }
    		 }
    	 }
         return completions;
     }

 	public void printTree() {
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr) {
 		if (curr == null) 
 			return;
 		
 		//System.out.println(curr.getText());
 		if (curr.endsWord()) {
 			System.out.println(curr.getText());
 		}
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
}