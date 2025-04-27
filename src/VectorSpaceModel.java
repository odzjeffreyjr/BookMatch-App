import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class VectorSpaceModel {
    private List<String> genreList;
    private List<String> authorList;

    public VectorSpaceModel(List<String> genreList, List<String> authorList) {
        this.genreList = genreList;
        this.authorList = authorList;
    }

    public List<String> getGenreList() {
        return genreList;
    }

    private double getDescriptionSimilarity(String bookDesc, String userDesc){
        Document bookDescription = new Document(bookDesc);
        Document userDescription = new Document(userDesc);
        DescriptionVSM vsm = new DescriptionVSM();
        return vsm.cosineSimilarity(bookDescription, userDescription);
    }

    // Create vector for specific book
    private double[] vectorizeBook(Book book, String userDesc) {
        double[] vector = new double[genreList.size() + authorList.size() + 2];

        // Add genres to vector
        for (String genre: book.getGenres()) {
            int genreIndex = genreList.indexOf(genre);
            if (genreIndex != -1) {
                vector[genreIndex] = 1;
            }
        }

        // Add authors to vector
        int authorIndex = authorList.indexOf(book.getAuthor());
        if (authorIndex != -1) {
            vector[genreList.size() + authorIndex] = 1;
        }

        // Add description similarity to vector
        double descSimilarity = getDescriptionSimilarity(book.getDescription(), userDesc);
        vector[genreList.size() + authorList.size()] = descSimilarity;

        // Add average rating to vector
        vector[genreList.size() + authorList.size() + 1] = book.getAverageRating() / 5.0;

        return vector;
    }

    // Create vector from user preferences
    private double[] vectorizePreferences(UserPreferences prefs) {
        double[] vector = new double[genreList.size() + authorList.size() + 2];

        // Add genres to vector
        for (String genre: prefs.getPreferredGenres()) {
            int index = genreList.indexOf(genre);
            if (index != -1) {
                vector[index] = 1;
            }
        }

        // Add authors to vector
        for (String author: prefs.getPreferredAuthors()) {
            int index = authorList.indexOf(author);
            if (index != -1) {
                vector[genreList.size() + index] = 1;
            }
        }

        // Add description similarity to vector (1 for user since we already compared in the
        // descriptionVSM)
        vector[genreList.size() + authorList.size()] = 1;
        vector[genreList.size() + authorList.size() + 1] = prefs.getMinRating() / 5.0;
        return vector;
    }

    private double cosSimilarity(double[] v1, double[] v2) {
        double dot = 0.0;
        double mag1 = 0.0;
        double mag2 = 0.0;
        for (int i = 0; i < v1.length; i++) {
            dot += v1[i] * v2[i];
            mag1 += v1[i] * v1[i];
            mag2 += v2[i] * v2[i];
        }

        return dot/(Math.sqrt(mag1) * Math.sqrt(mag2));
    }

    public List<Book> parseCSV(String filename) {
        List<Book> books = new ArrayList<>();
        HashSet<String> genreSet = new HashSet<>();
        HashSet<String> authors = new HashSet<>();
        String currentRawLine = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            reader.readLine(); // skip header
            String line;
            while ((line = reader.readLine()) != null) {
                currentRawLine = line;
                if (line.trim().isEmpty()) continue;

                StringBuilder fullLine = new StringBuilder(line);

                // keep reading until the number of quotes is even
                while (countQuotes(fullLine.toString()) % 2 != 0) {
                    String nextLine = reader.readLine();
                    if (nextLine == null) {
                        throw new RuntimeException("Unexpected end of file while reading a book entry near: " + currentRawLine);
                    }
                    fullLine.append("\n").append(nextLine); // careful to append newline!
                }

                String[] fields = fullLine.toString().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (fields.length < 8) {
                    throw new RuntimeException("Badly formatted book entry near: " + currentRawLine);
                }

                // Now safe to parse
                String title = fields[1].trim();
                String author = fields[2].trim();
                String description = fields[3].trim();

                String genreString = fields[4].trim();
                if (genreString.startsWith("[") && genreString.endsWith("]")) {
                    genreString = genreString.substring(1, genreString.length() - 1);
                }
                List<String> genres = Arrays.asList(genreString.split(",\\s*'?"));

                double avgRating = Double.parseDouble(fields[5].trim().replace(",", "")); // fix commas in numbers

                Book book = new Book(title, author, description, genres, avgRating);
                books.add(book);

                genreSet.addAll(genres);
                authors.add(author);
            }
            genreList.addAll(genreSet);
            authorList.addAll(authors);
            return books;

        } catch (Exception e) {
            System.out.println("Error parsing near: " + currentRawLine);
            throw new RuntimeException(e);
        }
    }

    // helper method
    private int countQuotes(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '"') count++;
        }
        return count;
    }

    public List<Book> recommendBooks(List<Book> books, UserPreferences prefs) {
        double[] userVector = vectorizePreferences(prefs);
        Map<Book, Double> scores = new HashMap<>();

        for (Book book: books) {
            double[] bookVector = vectorizeBook(book, prefs.getDescription());
            double similarity = cosSimilarity(userVector, bookVector);
            scores.put(book, similarity);
        }

        return scores.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .limit(5)
                .collect(Collectors.toList());
    }

}
