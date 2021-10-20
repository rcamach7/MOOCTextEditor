package spelling;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class NearbyWords implements SpellingSuggest {
	private static final int THRESHOLD = 1000;
	Dictionary dictionary;

	public NearbyWords (Dictionary dict) {
		this.dictionary = dict;
	}

	/** Return the list of Strings that are one modification away
	 * from the input string.  
	 * @param errorWord The original String
	 * @param wordsOnly controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String errorWord, boolean wordsOnly)  {
		   List<String> returnList = new ArrayList<String>();
		   
		   insertions(errorWord, returnList, wordsOnly);
		   substitution(errorWord, returnList, wordsOnly);
		   deletions(errorWord, returnList, wordsOnly);
		   return returnList;
	}
	
	/** Add to the currentList Strings that are one character mutation away
	 * from the input string.  
	 * @param wrongWord The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void substitution(String wrongWord, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for(int index = 0; index < wrongWord.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String
				StringBuffer sb = new StringBuffer(wrongWord);
				sb.setCharAt(index, (char)charCode);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(sb.toString()) && (!wordsOnly||dictionary.isWord(sb.toString())) && !wrongWord.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}
	
	/** Add to the currentList Strings that are one character insertion away
	 * from the input string.  
	 * @param wrongWord The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void insertions(String wrongWord, List<String> currentList, boolean wordsOnly ) {
		// Loop through each index of the word, as this is where our insertion will occur, at position 0  
		for (int index = 0; index < wrongWord.length() + 1; index++) {
			// This loop will go through each letter in the alphabet, to do all possible insertions at index
			for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {			
				StringBuilder generatedWord = new StringBuilder(wrongWord);
				generatedWord.insert(index, alphabet + "");
				
				// Here we will decide on adding, depending on what the parameter wants
				if (wordsOnly) {
					if (dictionary.isWord(generatedWord.toString())) {
						currentList.add(generatedWord.toString());
					}
				}
				if (!wordsOnly) {
					currentList.add(generatedWord.toString());
				}		
			}
		}
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param wrongWord The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void deletions(String wrongWord, List<String> currentList, boolean wordsOnly ) {
		for (int index = 0; index < wrongWord.length(); index++) {
			StringBuilder possibleWord = new StringBuilder(wrongWord);
			possibleWord.replace(index, index + 1, "");
			
			if (wordsOnly) {
				if (dictionary.isWord(possibleWord.toString())) {
					currentList.add(possibleWord.toString());
				}
			}
			if (!wordsOnly) {
				currentList.add(possibleWord.toString());
			}
		}	
	}

	/** Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param word The misspelled word
	 * @param numSuggestions is the maximum number of suggestions to return 
	 * @return the list of spelling suggestions
	 */
	@Override
	public List<String> suggestions(String word, int numSuggestions) {
		// initial variables
		List<String> queue = new LinkedList<String>();     // String to explore
		HashSet<String> visited = new HashSet<String>();   // to avoid exploring the same string multiple times
		List<String> returnList = new LinkedList<String>();   // words to return
		 
		// insert first node
		queue.add(word);
		visited.add(word);
					
		// TODO: Implement the remainder of this method, see assignment for algorithm
		while (!queue.isEmpty() && returnList.size() < numSuggestions) {
			String currentWord = queue.get(0);
			queue.remove(0);
			
			List<String> validWords = new ArrayList<String>();
			validWords = distanceOne(currentWord, false);
			
			
			for (String neighbor : validWords) {
				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					queue.add(neighbor);
					if (dictionary.isWord(neighbor)) {
						returnList.add(neighbor);
					}
				}
			}
			
		}
		return returnList;
	}	

   public static void main(String[] args) {

	   String word = "i";
	   // Pass NearbyWords any Dictionary implementation you prefer
	   Dictionary d = new DictionaryHashSet();
	   DictionaryLoader.loadDictionary(d, "data/dict.txt");
	   NearbyWords w = new NearbyWords(d);
	   List<String> l = w.distanceOne(word, true);
	   System.out.println("One away word Strings for for \""+word+"\" are:");
	   System.out.println(l+"\n");

	   word = "tailo";
	   List<String> suggest = w.suggestions(word, 10);
	   System.out.println("Spelling Suggestions for \""+word+"\" are:");
	   System.out.println(suggest);
	   
   }
}
