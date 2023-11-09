package textproc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor{
	private Map<String, Integer> map = new TreeMap<String, Integer>();
	// En collection utan duplikanter
	private Set<String> nonCountedWords;
	
	public GeneralWordCounter(Set<String> nonCountedWords) {
		this.nonCountedWords = nonCountedWords;
	}
	

	public void process(String word) {
		if(map.containsKey(word)) {
			map.replace(word, map.get(word) + 1);
		}else if(!nonCountedWords.contains(word)) {
			map.put(word, 1);
		}
	}
	
	public void report() {
	       Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
	        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);

	        // Create a custom comparator to sort entries by values (and then by keys if values are equal)
	        Comparator<Map.Entry<String, Integer>> comparator = (a, b) -> {
	            int valueComparison = b.getValue().compareTo(a.getValue());
	            if (valueComparison != 0) {
	                return valueComparison;  // Först efter antal förekomster
	            } else {
	                return a.getKey().compareTo(b.getKey()); // Sedan i bokstavsordning
	            }
	        };

	        wordList.sort(comparator);
		for(int i = 0; i < 10; i++) {
			System.out.println("En av de fem vanligaste: " + wordList.get(i));
		}
	}
	
    public List<Map.Entry<String, Integer>> getWordList(){

        Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);

        return wordList;

    }
}
