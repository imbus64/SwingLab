
// Läser av ett enstaka ord
public class SingleWordCounter implements TextProcessor {
	private String word;
	private int n;

	public SingleWordCounter(String word) {
		this.word = word;
		n = 0;
	}

	// Om w är samam som objektets ord, n++
	public void process(String w) {
		if (w.equals(word)) {
			n++;
		}
	}

	// Skriv ut mängden ord som är .equals word
	public void report() {
		System.out.println(word + ": " + n);
	}

}
