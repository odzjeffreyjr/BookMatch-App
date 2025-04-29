import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JPanel {
    public WelcomeScreen(CardLayout cardLayout, JPanel mainPanel) {
        setBackground(Color.decode("#efe8f1"));
        setLayout(new BorderLayout(20, 20));

        JLabel welcomeLabel = new JLabel("Welcome to BookMatch!");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.decode("#b1a6ab"));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);
        JButton nextButton = createButton("Next →");
        nextButton.addActionListener(e -> cardLayout.show(mainPanel, "genreScreen"));

        buttonPanel.add(nextButton);

        add(welcomeLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
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
