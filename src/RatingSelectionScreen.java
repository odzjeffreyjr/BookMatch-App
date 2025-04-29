import javax.swing.*;
import java.awt.*;

public class RatingSelectionScreen extends JPanel {
    private JSlider ratingSlider;

    public RatingSelectionScreen(CardLayout cardLayout, JPanel mainPanel) {
        setBackground(Color.decode("#efe8f1"));
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Minimum Book Rating (1-5):");
        header.setFont(new Font("Serif", Font.BOLD, 24));
        header.setForeground(Color.decode("#b1a6ab"));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        add(header, BorderLayout.NORTH);

        ratingSlider = new JSlider(1, 5, 3);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        ratingSlider.setBackground(Color.decode("#efe8f1"));
        ratingSlider.setForeground(Color.decode("#b1a6ab"));
        ratingSlider.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JPanel sliderPanel = new JPanel();
        sliderPanel.setOpaque(false);
        sliderPanel.add(ratingSlider);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton backButton = createButton("← Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "topicScreen"));
        JButton nextButton = createButton("Next →");
        nextButton.addActionListener(e -> cardLayout.show(mainPanel, "starScreen"));

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        add(sliderPanel, BorderLayout.CENTER);
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
