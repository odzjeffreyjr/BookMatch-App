import java.util.*;


public class Document{

    private HashMap<String, Integer> termFrequency;
    private String description;

    public Document(String description) {
        this.description = description;
        termFrequency = new HashMap<String, Integer>();
        processDescription();
    }

    private void processDescription() {
        // Split the description into words and process each word
        String[] words = description.split("\\s+");


        for (String word : words) {
            String filteredWord = word.replaceAll("[^A-Za-z0-9]", "").toLowerCase();


            if (!filteredWord.isEmpty()) {
                // Increment term frequency for the word
                termFrequency.put(filteredWord, termFrequency.getOrDefault(filteredWord, 0) + 1);
            }
        }
    }

    public double getTermFrequency(String word) {
        return termFrequency.getOrDefault(word, 0);
    }

    public Set<String> getTermList() {
        return termFrequency.keySet();
    }
}





