import java.util.List;

public class Book {
    private String author;
    private List<String> genres;
    private double averageRating;
    private String title;
    private String description;

    public Book(String title, String author, String description, List<String> genres,
                double avgRating) {
        this.author = author;
        this.title = title;
        this.averageRating = avgRating;
        this.genres = genres;
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }


    public double getAverageRating() {
        return averageRating;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String bookInfo = "";
        bookInfo += "Title: " + title;
        bookInfo += "\n   Author: " + author;
        bookInfo += "\n   Genre: ";
        String genreLine = genres.toString(); // turn list into a string
        genreLine = genreLine.replace("[", "").replace("]", "")
                .replace("'", "").replace("\"", "").trim();
        bookInfo += genreLine;
        bookInfo += "\n   Description: " + description;
        bookInfo += "\n   Rating: " + averageRating;

        return bookInfo;
    }
}

