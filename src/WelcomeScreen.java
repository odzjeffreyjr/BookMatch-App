import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class WelcomeScreen extends JPanel {
    private Image backgroundImage;

    public WelcomeScreen(CardLayout cardLayout, JPanel mainPanel) {
        try {
            backgroundImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("welcome.jpg"))).getImage();
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }

        setLayout(new BorderLayout(20, 20));
        setOpaque(false);

        JLabel welcomeLabel = new JLabel("Welcome to BookMatch!");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 50));
        welcomeLabel.setForeground(Color.decode("#ffffff"));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton nextButton = createButton("Next →");
        nextButton.addActionListener(e -> cardLayout.show(mainPanel, "genreScreen"));
        buttonPanel.add(nextButton);

        add(welcomeLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
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
