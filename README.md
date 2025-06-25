BookMatch-App is a Java-based book recommendation system that helps users find books tailored to their preferences in genre, author, topic, and rating. 
It supports both a command-line interface (CLI) and a graphical user interface (GUI) built with Swing.

---
Features

* Takes user input for:
  * Genres
  * Authors
  * Topics/keywords
  * Minimum star rating
* Matches preferences using a custom Vector Space Model (VSM).
* Recommends top 5 books based on user preferences.

---
Entry Points

1. CLI Mode: Run this to use the terminal-based recommender:
```bash
java src/Main
```
2. GUI Mode: Launch the interactive graphical application with:
```bash
java src/BookMatchApp
```

---
How It Works

BookMatch uses a Vector Space Model (VSM) to compute similarity between a user’s preferences and each book in the dataset. The core steps are:

1. Data Parsing
  * `books.csv` is parsed using `VectorSpaceModel.parseCSV()`, which extracts title, author, description genre(s), and average rating.

2. Vectorization

  a. **Book Vectors** (`vectorizeBook`): Each book is encoded into a numerical vector:
    * One-hot encoding for genres
    * One-hot encoding for author
    * Cosine similarity between book and user descriptions
    * Normalized average rating
  
  b. **User Vector** (`vectorizePreferences`): The user’s preferences are similarly encoded.
    * One-hot encoding for selected genres and authors
    * A fixed value of 1.0 for description similarity (since we compare it with the book description later)
    * Normalized minimum acceptable rating

3. Similarity Calculation
  * Cosine similarity is computed between each book vector and the user vector using `cosSimilarity`.
  * Books with higher similarity scores are better matches for the user.

4. Recommendation
  * Books are sorted by similarity score.
  * The top 5 books are returned using `recommendBooks()`.

---
Contributors

Developed by Shrishti Roy, Surabi Sharma, Jeffrey Oduman
