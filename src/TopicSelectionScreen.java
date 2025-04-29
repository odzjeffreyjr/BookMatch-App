import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TopicSelectionScreen extends JPanel {
    private JTextArea topicTextArea;
    private final Set<String> preferredTopics= new HashSet<>();

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
            if (topicTextArea.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please type at least one topic to continue.", "Topic Required", JOptionPane.WARNING_MESSAGE);
            } else {
                String[] authors = topicTextArea.getText().split(",");
                preferredTopics.clear();
                for (String author : authors) {
                    author = author.trim();
                    if (author.isEmpty()) {
                        continue;
                    }
                    preferredTopics.add(author);
                }
                if (preferredTopics.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please type at least one topic to continue.", "Topic Required", JOptionPane.WARNING_MESSAGE);
                } else {
                    cardLayout.show(mainPanel, "starScreen");
                }
            }
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

    public java.util.List<String> getPreferredTopics() {
        return new ArrayList<>(preferredTopics);
    }
}
