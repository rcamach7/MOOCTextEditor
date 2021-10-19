package textgen;

import java.util.Random;

public class App {

	public static void main(String[] args) {

		MarkovTextGeneratorLoL app = new MarkovTextGeneratorLoL(new Random());
		app.train("Look at the stars."
				+ "Look how they shine for you. "
				+ "And everything you do. "
				+ "Yeah, they were all yellow. "
				+ "I came along. "
				+ "I wrote a song for you. "
				+ "And all the things you do. "
				+ "And it was called Yellow. "
				+ "So, then I took my turn. "
				+ "What a thing to've done. "
				+ "And it was all yellow. "
				+ "Your skin. "
				+ "Oh yeah, your skin and bones. "
				+ "Turn in to something beautiful. "
				+ "Do you know. "
				+ "You know I love you so. "
				+ "You know I love you so. "
				+ "I swam across. "
				+ "I jumped across for you. "
				+ "What a thing to do. "
				+ "'Cause you were all yellow. "
				+ "I drew a line. "
				+ "I drew a line for you. "
				+ "What a thing to do. "
				+ "And it was all yellow. "
				+ "And your skin. "
				+ "Oh yeah, your skin and bones. "
				+ "Turn in to something beautiful. "
				+ "Do you know. "
				+ "For you, I'd bleed myself dry. "
				+ "For you, I'd bleed myself dry. "
				+ "It's true. "
				+ "Look how they shine for you. "
				+ "Look how they shine for you. "
				+ "Look how they shine for. "
				+ "Look how they shine for you. "
				+ "Look how they shine for you. "
				+ "Look how they shine. "
				+ "Look at the stars. "
				+ "Look how they shine for you. "
				+ "And all the things that you do.");
		System.out.println(app.generateText(250));
	}

}
