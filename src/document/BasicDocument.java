package document;

/** 
 * A naive implementation of the Document abstract class. 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document { // Best & Worst O(n^2) but before reducing it, it's 2(n^2) so definitely slower but still linear
	/** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 */
	public BasicDocument(String text) {
		super(text);
	}
	
	@Override
	public int getNumWords() {
		if (getText().isEmpty()) {
			return 0;
		} else {
			String onlyWords = getText().replaceAll("[^a-zA-Z]", " ").trim();
			String[] wordsList = onlyWords.split("\\s+");
			
			return wordsList.length;
		}
	}
	
	@Override
	public int getNumSentences() {
		if (getText().isEmpty()) {
			return 0;
		} else {
			String document = getText().trim();
			String[] sentences = document.split("(\\.+)|(\\?+)|(\\!+)");
			
			int nonSentences = 0;
			for (String sentence : sentences) {
				sentence = sentence.trim();
				if (sentence.isEmpty()) {
					nonSentences += 1;
				}
			}
			return sentences.length -  nonSentences;
		}
	}
	
	
	/**
	 * Get the total number of syllables in the document (the stored text). 
	 * To count the number of syllables in a word, it uses the following rules:
	 *       Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 *       
	 * Check the examples in the main method below for more information.  
	 * 
	 * This method should process the entire text string each time it is called.  
	 * 
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables() {
	    //TODO: Implement this method in week 2.  See the Module 2 support videos 
        // if you need help.  And note that there is no need to use a regular
		// expression for the syllable counting.  We recommend you implement 
		// the helper function countSyllables in Document.java using a loop, 
		// and then call it here on each word.'
		String onlyWords = getText().replaceAll("[^a-zA-Z]", " ").trim().toLowerCase();
		String[] wordsList = onlyWords.split("\\s+");

		int totalSyllables = 0;
		for (String word : wordsList) {
			if (word.isEmpty()) {
				continue;
			} else {
				totalSyllables += countSyllables(word);
			}
		}
        return totalSyllables;
	}

	
	public static void main(String[] args) {
		/* Each of the test cases below uses the method testCase.  The first 
		 * argument to testCase is a Document object, created with the string shown.
		 * The next three arguments are the number of syllables, words and sentences 
		 * in the string, respectively.  You can use these examples to help clarify 
		 * your understanding of how to count syllables, words, and sentences.
		 */
		BasicDocument doc = new BasicDocument("How are you?");
		System.out.println( doc.getFleschScore() );
		System.out.println(String.format("Number of words, sentences, and syllables: %s, %s, %s.", doc.getNumWords(), doc.getNumSentences(), doc.getNumSyllables()));
		
//		testCase(new BasicDocument("This is a test.  How many???  Senteeeeeeeeeences are here... there should be 5!  Right?"), 16, 13, 5);
//		testCase(new BasicDocument(""), 0, 0, 0);
//		testCase(new BasicDocument("sentence, with, lots, of, commas.!  (And some poaren)).  The output is: 7.5."), 15, 11, 4);
//		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
//		testCase(new BasicDocument("Here is a series of test sentences. Your program should find 3 sentences, 33 words, and 49 syllables. Not every word will have the correct amount of syllables (example, for example) but most of them will."), 49, 33, 3);
//		testCase(new BasicDocument("Segue"), 2, 1, 1);
//		testCase(new BasicDocument("Sentence"), 2, 1, 1);
//		testCase(new BasicDocument("Sentences?!"), 3, 1, 1);
//		testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."), 32, 15, 1);
	}
}
