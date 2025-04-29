import javax.swing.*;
import java.awt.*;

public class AuthorSelectionScreen extends JPanel {
    private JTextArea authorTextArea;

    public AuthorSelectionScreen(CardLayout cardLayout, JPanel mainPanel) {
        setBackground(Color.decode("#efe8f1"));
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Who are your favorite authors?");
        header.setFont(new Font("Serif", Font.BOLD, 24));
        header.setForeground(Color.decode("#b1a6ab"));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        add(header, BorderLayout.NORTH);

        authorTextArea = new JTextArea();
        authorTextArea.setLineWrap(true);
        authorTextArea.setWrapStyleWord(true);
        authorTextArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        authorTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#cfe5e5")),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        JScrollPane scrollPane = new JScrollPane(authorTextArea);
        scrollPane.getViewport().setBackground(Color.decode("#f2e7de"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton backButton = createButton("← Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "genreScreen"));
        JButton nextButton = createButton("Next →");
        nextButton.addActionListener(e -> {
            // You can save authors here if you want
            cardLayout.show(mainPanel, "topicScreen");
        });

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        add(scrollPane, BorderLayout.CENTER);
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
