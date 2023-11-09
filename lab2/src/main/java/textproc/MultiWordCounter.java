
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

// Läser av flera ord
public class MultiWordCounter implements TextProcessor{
	// En Map är en lista av value-pairs
	private Map<String, Integer> map = new HashMap<String, Integer>();
	
	// Tar in en String-array, sätter varje ord och ett startnummer (nbr) = 0
	public MultiWordCounter(String [] a) {
		for(String word : a) {
			this.map.put(word, 0);
		}
	}
	
	// Kollar efter ordet (word) om det finns nbr++ på ordet i map
	public void process(String word) {
		if(map.containsKey(word)) {
			int nbr = map.get(word);
			// Uppdaterar ordet, ökar dess nbr
			map.replace(word, nbr += 1);
		}
	}
	
	// Skriver ut varje 
	public void report() {
		for(String name : map.keySet()) {
			System.out.println(name + ": " + map.get(name));
		}
	}
	
}
