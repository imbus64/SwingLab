package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BookReaderApplication {
    public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
            "halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
            "södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
            "öland", "östergötland" };

    public static void main(String[] args) {
        // ArrayList<TextProcessor> wordList = new ArrayList<>();
        GeneralWordCounter processor = new GeneralWordCounter(new HashSet<String>());
        Set<String> nonCountedWords = new HashSet<String>();

        // Lägger till alla TextProcessors
        // wordList.add(new SingleWordCounter("nils"));
        // wordList.add(new SingleWordCounter("norge"));
        // wordList.add(new MultiWordCounter(REGIONS));
        // wordList.add(new GeneralWordCounter(nonCountedWords));

        Scanner scanner = null;
        Scanner undantag = null;

        try {
            scanner = new Scanner(new File("nilsholg.txt"));
            undantag = new Scanner(new File("undantagsord.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Filen hittades inte");
            System.exit(1);
        }

        scanner.findWithinHorizon("\uFEFF", 1);
        scanner.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
        // Scanner hoppar över mellanrum
        undantag.useDelimiter(" ");

        // Lägger till undantagen i nonCountedWords
        undantag.forEachRemaining(word -> nonCountedWords.add(word.toLowerCase()));
        // while (undantag.hasNext()) {
        // String word = undantag.next().toLowerCase();
        // nonCountedWords.add(word);
        // }

        while (scanner.hasNext())
            processor.process(scanner.next().toLowerCase());
        // // Tar ordet, sparar det i word, gör om det till lowercase
        // String word = scanner.next().toLowerCase();
        // for (int i = 0; i < wordList.size(); i++) {
        // // Kollar varje ord med varje typ av TextProcessor
        // wordList.get(i).process(word);
        // }
        // }

        scanner.close();
        undantag.close();

        BookReaderController controller = new BookReaderController(processor);
    }
}
