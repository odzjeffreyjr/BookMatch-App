import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GenreSelectionScreen extends JPanel {
    private ArrayList<JToggleButton> genreButtons = new ArrayList<>();

    private static final String[] genres = {
            "Fantasy", "Fiction", "Young Adult", "Romance", "Science Fiction",
            "Mystery", "Nonfiction", "Historical Fiction", "Horror", "Contemporary",
            "Thriller", "Classics", "Adventure", "Childrens", "Biography",
            "Crime", "Paranormal", "Graphic Novels", "Poetry", "History", "Picture Books"
    };

    public GenreSelectionScreen(CardLayout cardLayout, JPanel mainPanel) {
        setBackground(Color.decode("#efe8f1"));
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Select your favorite Genres:");
        header.setFont(new Font("Serif", Font.BOLD, 24));
        header.setForeground(Color.decode("#b1a6ab"));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        add(header, BorderLayout.NORTH);

        JPanel genresPanel = new JPanel(new GridLayout(7, 3, 10, 10));
        genresPanel.setBackground(Color.decode("#efe8f1"));

        for (String genre : genres) {
            JToggleButton button = new JToggleButton(genre);
            button.setBackground(Color.decode("#cfe5e5"));
            button.setFocusPainted(false);
            button.setFont(new Font("SansSerif", Font.PLAIN, 14));
            button.addActionListener(e -> {
                if (button.isSelected()) {
                    button.setBackground(Color.decode("#e3a898"));
                } else {
                    button.setBackground(Color.decode("#cfe5e5"));
                }
            });
            genreButtons.add(button);
            genresPanel.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(genresPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.decode("#efe8f1"));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton backButton = createButton("← Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "welcomeScreen"));
        JButton nextButton = createButton("Next →");
        nextButton.addActionListener(e -> {
            // TODO: Save selected genres if needed
            cardLayout.show(mainPanel, "authorScreen");
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
