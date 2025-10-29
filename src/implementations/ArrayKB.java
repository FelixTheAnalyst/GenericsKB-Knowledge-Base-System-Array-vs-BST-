package implementations;

import core.*;

public class ArrayKB implements KnowledgeBase{

    private KnowledgeBaseEntry[] entries;
    private int size; // keeping tracking number of records

    /**
     * Constructor for Array knowledge base.
     */
    public ArrayKB(){
        this.entries = new KnowledgeBaseEntry[100]; // intializes 100-lentgh empty array 
        this.size = 0; 
    }

    /**
     * Adds/Updates entry to the knowledge base.
     * 
     * @param term          The term.
     * @param sentence      The sentence associated with the term.
     * @param confidence    The confidence score of term/sentence combination.
     */
    @Override
    public void addOrUpdateEntry(String term, String sentence, double confidence){

        for(int i=0; i<size; i++) {
            if (entries[i].getTerm().equals(term))
                entries[i].update(sentence, confidence);
        }

        if (size >= entries.length)
            expandArray();

        entries[size++] = new KnowledgeBaseEntry(term, sentence, confidence);
    }

    /**
     * Search an item by its term.
     * 
     * @param term      The term
     * @return  Item associate with the term.
     */
    @Override
    public KnowledgeBaseEntry searchByTerm(String term){
        for (int i=0; i<size; i++) {
            if(entries[i].getTerm().equals(term))
                return entries[i];
        }

        return null;
    }

    /**
     * Search an item by term/sentence combination.
     * 
     * @param term      The term
     * @param sentence  The sentence associated with the term.
     * @return The confidence score of term/sentence combination.
     */
    @Override
    public double searchByTermAndSentence(String term, String sentence) {

        for (int i=0; i<size; i++) {
            if (entries[i].getTerm().equals(term) && entries[i].getSentence().equals(sentence))
                return entries[i].getConfidence();
        }

        return -1;
    }

    /**
     * Expands an array by a factor of 2.
     */
    public void expandArray(){

        KnowledgeBaseEntry[] newEntries = new KnowledgeBaseEntry[entries.length * 2];
        System.arraycopy(entries, 0, newEntries, 0, entries.length);
        entries = newEntries;

    }

} 