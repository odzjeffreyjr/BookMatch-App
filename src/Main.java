import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Welcome message
        System.out.println("Welcome to the Book Recommender!");
        System.out.println("Please provide your preferences, and we will recommend the top 5 " +
                "books that best match your interests.");


        // Prompt for preferred authors
        System.out.print("\nEnter your preferred authors (comma separated): ");
        String authorsInput = scanner.nextLine();
        List<String> preferredAuthors = new ArrayList<>();
        Collections.addAll(preferredAuthors, authorsInput.split(",\\s*"));



        // Prompt for preferred genres
        System.out.print("Enter your preferred genres (comma separated): ");
        String genresInput = scanner.nextLine();
        List<String> preferredGenres = new ArrayList<>();
        Collections.addAll(preferredGenres, genresInput.split(",\\s*"));


        // Prompt for preferred topics/keywords
        System.out.print("Enter your preferred book topics (comma separated): ");
        String description = scanner.nextLine();

        // Prompt for minimum rating
        System.out.print("Enter your minimum acceptable rating (e.g., 1-5): ");
        double minRating = scanner.nextDouble();

        // Close scanner
        scanner.close();

        UserPreferences userPref = new UserPreferences(preferredGenres, preferredAuthors,
                minRating, description);

        VectorSpaceModel vsm = new VectorSpaceModel(new ArrayList<>(), new ArrayList<>());
        List<Book> books = vsm.parseCSV("books.csv");
        List<Book> recommendations = vsm.recommendBooks(books, userPref);

        for (int i = 0; i < recommendations.size(); i++) {
            Book book = recommendations.get(i);
            System.out.println((i + 1) + ". " + book);
        }
    }
}