
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long time1 = System.nanoTime(); // Nuvarande värde av systems tid i nanosekunder
		ArrayList<TextProcessor> wordList = new ArrayList<>();
		Set<String> nonCountedWords = new HashSet<String>();
		
		// Lägger till alla TextProcessors
		wordList.add(new SingleWordCounter("nils"));
		wordList.add(new SingleWordCounter("norge"));
		wordList.add(new MultiWordCounter(REGIONS));
		wordList.add(new GeneralWordCounter(nonCountedWords));
		
		Scanner scanner = new Scanner(new File("nilsholg.txt"));
		scanner.findWithinHorizon("\uFEFF", 1);
		scanner.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		Scanner undantag = new Scanner(new File("undantagsord.txt"));
		// Scanner hoppar över mellanrum
		undantag.useDelimiter(" ");
		
		// Lägger till undantagen i nonCountedWords
		while(undantag.hasNext()) {
			String word = undantag.next().toLowerCase();
			nonCountedWords.add(word);
		}
		
		while (scanner.hasNext()) {
			// Tar ordet, sparar det i word, gör om det till lowercase
			String word = scanner.next().toLowerCase();
			for (int i = 0; i < wordList.size(); i++) {
				// Kollar varje ord med varje typ av TextProcessor
				wordList.get(i).process(word);
			}
		}

		scanner.close();
		undantag.close();
		
		// Går igenom hela wordlist och får alla att skriva ut sina ord
		for(int i = 0; i < wordList.size(); i++) {
			wordList.get(i).report();
		}
		
		// Skriver ut tiden som passerat
		long time2 = System.nanoTime();
		System.out.println("Tid: " + (time2 - time1)/ + 1000000000.0 + " sekunder");
	}
}