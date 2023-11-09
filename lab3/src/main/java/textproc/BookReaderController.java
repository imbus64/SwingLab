package textproc;

import javax.swing.JFrame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Consumer;

public class BookReaderController {
    public BookReaderController(GeneralWordCounter counter) {
        SwingUtilities.invokeLater(() -> createWindow(counter, "EnRiktigBoi", 100, 300));
    }

    private static enum SortOrder {
        ALPHA, FREQ
    }

    private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
        ImageIcon icon = new ImageIcon("icon.png");
        // Create a map of words and their counts
        Map<String, Integer> wordCountMap = getWords();

        // Create a JFrame (a window)
        JFrame frame = new JFrame("EnRiktigBoi");
        frame.setIconImage(icon.getImage());

        // Create a JPanel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a DefaultListModel to hold the words and counts
        DefaultListModel<String> wordListModel = new DefaultListModel<>();
        JList<String> wordList = new JList<>(wordListModel);

        // Populate the wordListModel from the wordCountMap
        wordCountMap.forEach((word, count) -> wordListModel.addElement(word + " (" + count + ")"));

        // Create a JScrollPane and add the JList to it
        JScrollPane scrollPane = new JScrollPane(wordList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create a JPanel for the buttons
        JPanel buttonPanel = new JPanel();

        // Create buttons for sorting
        JButton sortAlphabeticallyButton = new JButton("Sort Alphabetically");
        JButton sortByFrequencyButton = new JButton("Sort by Frequency");
        JButton searchButton = new JButton("Search");

        // Create a search box (JTextField)
        JTextField searchField = new JTextField(20);

        // Lambdas are stored in variables of the following types:
        // Use Supplier if it takes nothing, but returns something.
        // Use Consumer if it takes something, but returns nothing.
        // Use Callable if it returns a result and might throw (most akin to Thunk in general CS terms).
        // Use Runnable if it does neither and cannot throw
        Runnable searchFilter = () -> {
            String searchTerm = searchField.getText().toLowerCase();
            List<String> filteredWords = wordCountMap.keySet().stream()
                    .filter(word -> word.toLowerCase().contains(searchTerm))
                    .collect(Collectors.toList());
            updateWordListModel(wordListModel, wordCountMap, filteredWords);
        };

        Consumer<SortOrder> sort = (sortOrder) -> {
            List<String> sortedWords = wordCountMap.keySet().stream()
                    .sorted((a, b) -> {
                        if (sortOrder == SortOrder.ALPHA) return a.compareTo(b);
                        else return wordCountMap.get(b) - wordCountMap.get(a);
                    })
                    .collect(Collectors.toList());
            updateWordListModel(wordListModel, wordCountMap, sortedWords);
        };

        // Add lambdas (handlers, "action listeners") to the search button to filter the list
        searchButton.addActionListener(e -> searchFilter.run());
        searchField.addActionListener(e -> searchFilter.run());
        sortAlphabeticallyButton.addActionListener(e -> sort.accept(SortOrder.ALPHA));
        sortByFrequencyButton.addActionListener(e -> sort.accept(SortOrder.FREQ));


        // Add components to the button panel
        buttonPanel.add(sortAlphabeticallyButton);
        buttonPanel.add(sortByFrequencyButton);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);

        // Add the button panel to the top of the main panel
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        frame.add(panel);

        // Set the default close operation for the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame
        frame.setSize(800, 300);
        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame

        // Make the frame visible
        frame.setVisible(true);
    }

    public static Map<String, Integer> getWords() {
        // Create a map of words and their counts
        Map<String, Integer> wordCountMap = new HashMap<>();
        wordCountMap.put("Apple", 5);
        wordCountMap.put("Banana", 8);
        wordCountMap.put("Cherry", 3);
        wordCountMap.put("Date", 6);
        wordCountMap.put("Grape", 9);
        wordCountMap.put("Lemon", 4);
        wordCountMap.put("Orange", 7);
        wordCountMap.put("Peach", 2);
        return wordCountMap;
    }

    private static void updateWordListModel(DefaultListModel<String> wordListModel, Map<String, Integer> wordCountMap,
            List<String> words) {
        wordListModel.clear();
        words.forEach(word -> wordListModel.addElement(word + " (" + wordCountMap.get(word) + ")"));
    }
}
