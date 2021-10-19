package spelling;

public class App {
	private TrieNode root = new TrieNode();

	public static void main(String[] args) {
		App app = new App();
		app.test();
		
	}
	
	public void test() {
		addWord("dream");
		addWord("dreamer");
		addWord("dreaming");
		printNode(root);
		
		//System.out.println(root.getValidNextCharacters());
	}
	
	public void addWord(String word) {
		TrieNode currentTriePosition = root;

		char[] brokenDownWord = word.toCharArray();
		for (int i = 0 ; i  < brokenDownWord.length; i++) {
			// If the child i'th position of word is null inside current TrieNode - then initiate and set one.
			if (currentTriePosition.getChild(brokenDownWord[i]) == null) {
				currentTriePosition.insert(brokenDownWord[i]);
			}
			// Go inside the TrieNode just created, and then repeat the process for the next i'th character in word. 
			currentTriePosition = currentTriePosition.getChild(brokenDownWord[i]);
		}
		// Now that we are inside of the last TrieNode of word, we can set this one to true, because it represents a word.
		currentTriePosition.setEndsWord(true);
	}
	
 	public void printNode(TrieNode curr) {
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
// 		if (curr.endsWord()) {
// 			System.out.print(" this represents a word.\n");
// 		}
// 			
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}

}
