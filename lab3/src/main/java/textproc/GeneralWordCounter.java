package textproc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GeneralWordCounter {
    public static Map<String, Integer> countWordsFromFile(String filePath) {
        Map<String, Integer> wordCountMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split the line into words (whitespace as delimiter)

                for (String word : words) {
                    // Remove punctuation and convert to lowercase for better word matching
                    word = word.replaceAll("[^a-zA-Z]", "").toLowerCase();

                    if (!word.isEmpty()) {
                        // Update word frequency in the map
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordCountMap;
    }

    // public static void main(String[] args) {
    //     String filePath = "sample.txt"; // Replace with the path to your text file
    //     Map<String, Integer> wordCountMap = countWordsFromFile(filePath);

    //     // Print the word frequencies
    //     for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
    //         System.out.println(entry.getKey() + ": " + entry.getValue());
    //     }
    // }
}
