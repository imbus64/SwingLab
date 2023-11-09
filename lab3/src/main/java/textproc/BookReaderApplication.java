package textproc;

public class BookReaderApplication {
    public static void main(String[] args) {
        GeneralWordCounter counter = new GeneralWordCounter();
        new BookReaderController(counter);
    }
}
