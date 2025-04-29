import javax.swing.*;
import java.awt.*;

class StarRatingPanel extends JPanel {
    private final int maxStars = 5;
    private final double rating;

    public StarRatingPanel(double rating) {
        this.rating = rating;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setOpaque(false);
        addStars();
    }

    private void addStars() {
        int fullStars = (int) rating;
        boolean hasHalfStar = (rating - fullStars) >= 0.5;

        for (int i = 0; i < fullStars; i++) {
            add(createStarLabel("★", Color.ORANGE));
        }
        if (hasHalfStar) {
            add(createStarLabel("⯪", Color.ORANGE)); // Optional: replace with better half-star
        }
        for (int i = fullStars + (hasHalfStar ? 1 : 0); i < maxStars; i++) {
            add(createStarLabel("☆", Color.LIGHT_GRAY));
        }
    }

    private JLabel createStarLabel(String star, Color color) {
        JLabel label = new JLabel(star);
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        label.setForeground(color);
        return label;
    }
}
