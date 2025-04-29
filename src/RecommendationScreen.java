import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RecommendationScreen extends JPanel {
    private final JPanel cardsPanel;
    private final JScrollPane scrollPane;

    public RecommendationScreen(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#efe8f1"));

        cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBackground(Color.decode("#efe8f1"));

        scrollPane = new JScrollPane(cardsPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.decode("#efe8f1"));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.decode("#efe8f1"));

        JButton backButton = createButton("← Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "starScreen"));
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setRecommendations(List<Book> recommendations) {
        cardsPanel.removeAll();

        for (Book book : recommendations) {
            JPanel card = createBookCard(book);
            cardsPanel.add(card);
            cardsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // spacing
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private JPanel createBookCard(Book book) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#b1a6ab")),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setBackground(Color.decode("#f2e7de"));

        // Ensure it expands horizontally
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea title = createWrappedText("📖 " + book.getTitle(), new Font("Serif", Font.BOLD, 18), "#b1a6ab");
        JTextArea description = createWrappedText("📔 " + book.getDescription(), new Font("SansSerif", Font.PLAIN, 14), "#000000");

        JLabel author = new JLabel("✍️ " + book.getAuthor());
        JLabel genre = new JLabel("🏷️ " + book.getGenres());

        for (JLabel label : new JLabel[]{author, genre}) {
            label.setFont(new Font("SansSerif", Font.PLAIN, 14));
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
        }

        // Star rating
        JPanel starPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        starPanel.setBackground(Color.decode("#f2e7de"));
        starPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        int fullStars = (int) Math.round(book.getAverageRating());
        for (int i = 0; i < 5; i++) {
            JLabel star = new JLabel(i < fullStars ? "★" : "☆");
            star.setFont(new Font("SansSerif", Font.PLAIN, 18));
            star.setForeground(Color.ORANGE);
            starPanel.add(star);
        }

        // Build card
        card.add(title);
        card.add(Box.createVerticalStrut(4));
        card.add(author);
        card.add(genre);
        card.add(Box.createVerticalStrut(4));
        card.add(starPanel);
        card.add(Box.createVerticalStrut(4));
        card.add(description);

        return card;
    }

    private JTextArea createWrappedText(String text, Font font, String hexColor) {
        JTextArea area = new JTextArea(text);
        area.setFont(font);
        area.setForeground(Color.decode(hexColor));
        area.setWrapStyleWord(true);
        area.setLineWrap(true);
        area.setOpaque(false);
        area.setEditable(false);
        area.setFocusable(false);
        area.setAlignmentX(Component.LEFT_ALIGNMENT);
        area.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        return area;
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
