import java.util.*;

public class DescriptionVSM {
    private List<Document> documents;
    private Set<String> allTerms;

    public DescriptionVSM() {
        documents = new ArrayList<Document>();
        allTerms = new HashSet<String>();
    }

    public void addDocument(Document document) {
        documents.add(document);
        allTerms.addAll(document.getTermList());
    }

    public double[] documentToVector(Document document) {
        double[] vector = new double[allTerms.size()];
        int index = 0;


        // Convert the document into a vector based on term frequencies
        for (String term : allTerms) {
            vector[index] = document.getTermFrequency(term);
            index++;
        }


        return vector;
    }

    public double cosineSimilarity(Document doc1, Document doc2) {
        double[] vector1 = documentToVector(doc1);
        double[] vector2 = documentToVector(doc2);


        double dotProduct = 0;
        double magnitude1 = 0;
        double magnitude2 = 0;


        // Calculate the dot product and magnitudes of both vectors
        for (int i = 0; i < vector1.length; i++) {
            dotProduct += vector1[i] * vector2[i];
            magnitude1 += Math.pow(vector1[i], 2);
            magnitude2 += Math.pow(vector2[i], 2);
        }


        // If either magnitude is zero, return similarity as 0
        magnitude1 = Math.sqrt(magnitude1);
        magnitude2 = Math.sqrt(magnitude2);


        if (magnitude1 == 0 || magnitude2 == 0) {
            return 0;
        }

        // Return the cosine similarity
        return dotProduct / (magnitude1 * magnitude2);
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public Set<String> getAllTerms() {
        return allTerms;
    }

}
