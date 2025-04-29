import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RecommendationScreen extends JPanel {
    private Timer animationTimer;
    private String[] recommendations = {
            "The Hobbit - J.R.R. Tolkien",
            "Pride and Prejudice - Jane Austen",
            "1984 - George Orwell",
            "Harry Potter - J.K. Rowling",
            "To Kill a Mockingbird - Harper Lee",
            "Percy Jackson - Rick Riordan",
            "The Great Gatsby - F. Scott Fitzgerald"
    };

    private JLabel[] movingLabels;

    public RecommendationScreen(CardLayout cardLayout, JPanel mainPanel) {
        setBackground(Color.decode("#efe8f1"));
        setLayout(null);

        movingLabels = new JLabel[recommendations.length];
        Random rand = new Random();

        for (int i = 0; i < recommendations.length; i++) {
            JLabel recLabel = new JLabel(recommendations[i]);
            recLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
            recLabel.setForeground(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
            recLabel.setBounds(rand.nextInt(400), rand.nextInt(400), 300, 30);
            movingLabels[i] = recLabel;
            add(recLabel);
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 420, 600, 50);

        JButton restartButton = createButton("Restart");
        restartButton.addActionListener(e -> cardLayout.show(mainPanel, "welcomeScreen"));

        buttonPanel.add(restartButton);
        add(buttonPanel);

        animationTimer = new Timer(100, e -> animateLabels());
        animationTimer.start();
    }

    private void animateLabels() {
        for (JLabel label : movingLabels) {
            Point p = label.getLocation();
            label.setLocation((p.x + 5) % getWidth(), p.y);
        }
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.decode("#cfe5e5"));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.PLAIN, 16));
        return button;
    }
}
