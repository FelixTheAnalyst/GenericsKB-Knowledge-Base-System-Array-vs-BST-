package core;

/**
 * Basic methods required for each implementation.
 */
public interface KnowledgeBase {

    /**
     * Adds/Updates entry to the knowledge base.
     * 
     * @param term          The term.
     * @param sentence      The sentence associated with the term.
     * @param confidence    The confidence score of term/sentence combination.
     */
    void addOrUpdateEntry(String term, String sentence, double confidence);

    /**
     * Search an item by its term.
     * 
     * @param term      The term
     * @return  Item associate with the term.
     */
    KnowledgeBaseEntry searchByTerm(String term);

    /**
     * Search an item by term/sentence combination.
     * 
     * @param term      The term
     * @param sentence  The sentence associated with the term.
     * @return The confidence score of term/sentence combination.
     */
    double searchByTermAndSentence(String term, String sentence);

}