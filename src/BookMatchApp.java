import javax.swing.*;
import java.awt.*;

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
            RatingSelectionScreen ratingScreen = new RatingSelectionScreen(cardLayout, mainPanel);
            StarSelectionScreen starScreen = new StarSelectionScreen(cardLayout, mainPanel);
            RecommendationScreen recommendationScreen = new RecommendationScreen(cardLayout, mainPanel);

            mainPanel.add(welcomeScreen, "welcomeScreen");
            mainPanel.add(genreScreen, "genreScreen");
            mainPanel.add(authorScreen, "authorScreen");
            mainPanel.add(topicScreen, "topicScreen");

            mainPanel.add(ratingScreen, "ratingScreen");
            mainPanel.add(starScreen, "starScreen");
            mainPanel.add(recommendationScreen, "recommendationScreen");

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}
