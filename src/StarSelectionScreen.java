import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StarSelectionScreen extends JPanel {
    private JLabel[] stars = new JLabel[5];
    private int selectedStars = 3;

    public StarSelectionScreen(CardLayout cardLayout, JPanel mainPanel) {
        setBackground(Color.decode("#efe8f1"));
        setLayout(new BorderLayout(10, 10));

        JLabel header = new JLabel("Select Minimum Star Rating:");
        header.setFont(new Font("Serif", Font.BOLD, 24));
        header.setForeground(Color.decode("#b1a6ab"));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        add(header, BorderLayout.NORTH);

        JPanel starsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        starsPanel.setOpaque(false);

        for (int i = 0; i < 5; i++) {
            JLabel star = new JLabel("☆");
            star.setFont(new Font("SansSerif", Font.PLAIN, 50));
            star.setForeground(Color.decode("#e3a898"));
            final int starIndex = i;
            star.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedStars = starIndex + 1;
                    updateStars();
                }
            });
            stars[i] = star;
            starsPanel.add(star);
        }
        updateStars();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton backButton = createButton("← Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "topicScreen"));
        JButton nextButton = createButton("Next →");
        nextButton.addActionListener(e -> cardLayout.show(mainPanel, "loadingScreen"));

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        add(starsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void updateStars() {
        for (int i = 0; i < stars.length; i++) {
            if (i < selectedStars) {
                stars[i].setText("★");
            } else {
                stars[i].setText("☆");
            }
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

    public Integer getSelectedStars() {
        return Integer.valueOf(selectedStars);
    }
}
