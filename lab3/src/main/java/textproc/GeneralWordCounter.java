package textproc;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.Set;

public class GeneralWordCounter implements TextProcessor {
    private Set<String> nonCountedWords = null;
    private Map<String, Integer> map = new TreeMap<String, Integer>();

    GeneralWordCounter(Set<String> nonCountedWords) {
        this.nonCountedWords = nonCountedWords;
    }

    public List<Map.Entry<String, Integer>> getWordList() {
        return List.copyOf(map.entrySet());
    }

    @Override
    public void process(String word) {
        if (!nonCountedWords.contains(word))
            map.put(word, map.getOrDefault(word, 0) + 1);
    }

    @Override
    public void report() {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
