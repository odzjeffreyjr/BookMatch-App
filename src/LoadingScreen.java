import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.util.Objects;

public class LoadingScreen extends JPanel {
    private boolean taskStarted = false;

    public LoadingScreen(CardLayout cardLayout, JPanel mainPanel, Runnable backgroundTask) {
        setBackground(Color.decode("#efe8f1"));
        setLayout(new BorderLayout());

        JLabel loadingLabel = new JLabel("Generating your recommendations...");
        loadingLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingLabel.setForeground(Color.decode("#b1a6ab"));
        add(loadingLabel, BorderLayout.NORTH);

        JLabel loadingIcon = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("loading.gif"))));
        loadingIcon.setHorizontalAlignment(SwingConstants.CENTER);
        add(loadingIcon, BorderLayout.CENTER);

        // Start task only when component becomes visible
        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing() && !taskStarted) {
                taskStarted = true;
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() {
                        backgroundTask.run();
                        return null;
                    }

                    @Override
                    protected void done() {
                        cardLayout.show(mainPanel, "recommendationScreen");
                    }
                }.execute();
            } else if (!isShowing()) {
                taskStarted = false;
            }
        });
    }
}
