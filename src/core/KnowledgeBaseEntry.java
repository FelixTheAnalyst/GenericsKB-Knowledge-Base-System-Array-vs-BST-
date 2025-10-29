package core;

/**
 * Represents elements of each item in knowledge base.
 */
public class KnowledgeBaseEntry {

  private String term;
  private String sentence;
  private double confidence;
  
  /**
   * Constructor for KnowledgeBaseEntry
   * 
   * @param term          The term.
  * @param sentence      The sentence associated with the term.
  * @param confidence    The confidence score of term/sentence combination.
   */
  public KnowledgeBaseEntry(String term, String sentence, double confidence) {

    this.term = term;
    this.sentence = sentence;
    this.confidence = confidence;

  }

  /**
   * Get method for term of an item
   * 
   * @return The term
   */
  public String getTerm(){
    return term;
  }

  /**
   * Get method for sentence of an item
   * 
   * @return The sentence
   */
  public String getSentence(){
    return sentence;
  }

  /**
   * Get method for confidence of an item
   * 
   * @return The confidence score
   */
  public double getConfidence(){
    return confidence;
  }

  /**
   * Compares the confidence scores original item and that given by the user. 
   * 
   * @param sentence    The sentence from the user.
   * @param confidence  The confidence score from the user.
   */
  public void update(String sentence, double confidence){
    if (confidence > this.confidence) {
      this.sentence = sentence;
      this.confidence = confidence;
    }

  }

  /**
   * String output for each entry.
   */
  public String toString(){
    return term + ": \"" + sentence + "\" (Confidence: " + confidence + ")";
  } 
} 