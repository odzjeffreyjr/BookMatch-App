import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookMatchApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BookMatch");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);

            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);

            WelcomeScreen welcomeScreen = new WelcomeScreen(cardLayout, mainPanel);
            GenreSelectionScreen genreScreen = new GenreSelectionScreen(cardLayout, mainPanel);
            AuthorSelectionScreen authorScreen = new AuthorSelectionScreen(cardLayout, mainPanel);
            TopicSelectionScreen topicScreen = new TopicSelectionScreen(cardLayout, mainPanel);
            StarSelectionScreen starScreen = new StarSelectionScreen(cardLayout, mainPanel);
            RecommendationScreen recommendationScreen = new RecommendationScreen(cardLayout, mainPanel);

            mainPanel.add(welcomeScreen, "welcomeScreen");
            mainPanel.add(genreScreen, "genreScreen");
            mainPanel.add(authorScreen, "authorScreen");
            mainPanel.add(topicScreen, "topicScreen");

            mainPanel.add(starScreen, "starScreen");
            mainPanel.add(recommendationScreen, "recommendationScreen");
            Runnable generateRecommendations = () -> {
                List<String> preferredAuthors = authorScreen.getPreferredAuthors();
                List<String> preferredGenres = genreScreen.getPreferredGenres();
                List<String> preferredTopics = topicScreen.getPreferredTopics();
                Integer minRating = starScreen.getSelectedStars();

                UserPreferences userPref = new UserPreferences(preferredGenres, preferredAuthors,
                        minRating, String.join(", ", preferredTopics));

                VectorSpaceModel vsm = new VectorSpaceModel(new ArrayList<>(), new ArrayList<>());
                List<Book> books = vsm.parseCSV("books.csv");
                List<Book> recommendations = vsm.recommendBooks(books, userPref);

                for (Book book : recommendations) {
                    System.out.println(book);
                }

                SwingUtilities.invokeLater(() -> {
                    recommendationScreen.setRecommendations(recommendations);
                });
            };

            LoadingScreen loadingScreen = new LoadingScreen(cardLayout, mainPanel, generateRecommendations);
            mainPanel.add(loadingScreen, "loadingScreen");

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}
