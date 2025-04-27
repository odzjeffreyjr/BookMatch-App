import java.util.List;

public class UserPreferences {
    private List<String> preferredGenres;
    private List<String> preferredAuthors;
    private double minRating;
    private String description;

    public UserPreferences(List<String> preferredGenres, List<String> preferredAuthors,
                           double minRating, String description) {
        this.preferredGenres = preferredGenres;
        this.preferredAuthors = preferredAuthors;
        this.minRating = minRating;
        this.description = description;
    }

    public List<String> getPreferredGenres() {
        return preferredGenres;
    }
    public List<String> getPreferredAuthors() {
        return preferredAuthors;
    }
    public double getMinRating() {
        return minRating;
    }

    public String getDescription() {
        return description;
    }
}
