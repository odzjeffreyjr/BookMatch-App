import javax.swing.*;
import java.awt.*;

public class TopicSelectionScreen extends JPanel {
    private JTextArea topicTextArea;

    public TopicSelectionScreen(CardLayout cardLayout, JPanel mainPanel) {
        setBackground(Color.decode("#efe8f1"));
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("What topics interest you?");
        header.setFont(new Font("Serif", Font.BOLD, 24));
        header.setForeground(Color.decode("#b1a6ab"));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        add(header, BorderLayout.NORTH);

        topicTextArea = new JTextArea();
        topicTextArea.setLineWrap(true);
        topicTextArea.setWrapStyleWord(true);
        topicTextArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
        topicTextArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#cfe5e5")),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        JScrollPane scrollPane = new JScrollPane(topicTextArea);
        scrollPane.getViewport().setBackground(Color.decode("#f2e7de"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton backButton = createButton("← Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "authorScreen"));
        JButton nextButton = createButton("Next →");
        nextButton.addActionListener(e -> {
            // Save topics here if needed
            cardLayout.show(mainPanel, "ratingScreen");
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
