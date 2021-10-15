package document;
import java.util.List;
/** 
 * A class that represents a text document
 * It does one pass through the document to count the number of syllables, words, 
 * and sentences and then stores those values.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class EfficientDocument extends Document { // Worst: O(n^2), Best: O(n^2) <countSyllables forces a second run, making n = n^2 >
	private int numWords;  // The number of words in the document
	private int numSentences;  // The number of sentences in the document
	private int numSyllables;  // The number of syllables in the document
	
	public EfficientDocument(String text) {
		super(text);
		processText();
	}
	/** 
	 * Take a string that either contains only alphabetic characters,
	 * or only sentence-ending punctuation.  Return true if the string
	 * contains only alphabetic characters, and false if it contains
	 * end of sentence punctuation.  
	 * 
	 * @param token The string to check
	 * @return true if tok is a word, false if it is punctuation. 
	 */
	private boolean isWord(String token) {
		return !(token.indexOf("!") >=0 || token.indexOf(".") >=0 || token.indexOf("?")>=0);
	}
	
    /** Passes through the text one time to count the number of words, syllables 
     * and sentences, and set the member variables appropriately.
     * Words, sentences and syllables are defined as described below. 
     */
	private void processText() { 
		// Call getTokens on the text to preserve separate strings that are 
		// either words or sentence-ending punctuation.  Ignore everything
		// That is not a word or a sentence-ending puctuation.
		// MAKE SURE YOU UNDERSTAND THIS LINE BEFORE YOU CODE THE REST
		// OF THIS METHOD.
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
		for (String token : tokens) {
			if (isWord(token)) {
				numWords += 1;
				numSyllables += countSyllables(token);
			} else {
				numSentences += 1;
			}
		}
		if (tokens.size() > 0) {
			String lastToken = tokens.get(tokens.size() - 1);
			lastToken = lastToken.replaceAll("[^?!.]", "");
			if (lastToken.isEmpty()) {
				numSentences += 1;
			}
		}
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * Check the examples in the main method below for more information. 
	 *  
	 * This method does NOT process the whole text each time it is called.  
	 * It returns information already stored in the EfficientDocument object.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences() {
		//TODO: write this method.  Hint: It's simple
		return numSentences;
	}

	/**
	 * Get the number of words in the document.
	 * A "word" is defined as a contiguous string of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z.  This method completely 
	 * ignores numbers when you count words, and assumes that the document does not have 
	 * any strings that combine numbers and letters. 
	 * 
	 * Check the examples in the main method below for more information.
	 * 
	 * This method does NOT process the whole text each time it is called.  
	 * It returns information already stored in the EfficientDocument object.
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords() {
		//TODO: write this method.  Hint: It's simple
	    return numWords;
	}

	/**
	 * Get the total number of syllables in the document (the stored text). 
	 * To calculate the the number of syllables in a word, it uses the following rules:
	 *       Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 *       
	 * Check the examples in the main method below for more information.
	 * 
	 * This method does NOT process the whole text each time it is called.  
	 * It returns information already stored in the EfficientDocument object.
	 * 
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables() {
        //TODO: write this method.  Hint: It's simple
        return numSyllables;
	}
	
	public static void main(String[] args){
		//EfficientDocument doc = new EfficientDocument("");
		
	    testCase(new EfficientDocument("This is a test.  How many??? Senteeeeeeeeeences are here... there should be 5!  Right?"), 16, 13, 5);
        testCase(new EfficientDocument(""), 0, 0, 0);
        testCase(new EfficientDocument("sentence, with, lots, of, commas.!  (And some poaren)).  The output is: 7.5."), 15, 11, 4);
        testCase(new EfficientDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2); 
        testCase(new EfficientDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		testCase(new EfficientDocument("Segue"), 2, 1, 1);
		testCase(new EfficientDocument("Sentence"), 2, 1, 1);
		testCase(new EfficientDocument("Sentences?!"), 3, 1, 1);
		testCase(new EfficientDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."), 32, 15, 1);
		
	}	
}