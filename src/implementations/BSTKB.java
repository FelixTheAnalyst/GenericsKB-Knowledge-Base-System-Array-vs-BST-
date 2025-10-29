package implementations;

import core.*;

public class BSTKB implements KnowledgeBase{

    private BSTNode root;
    

    /**
     * Represents a Binary Search Tree node.
     */
    private class BSTNode{

        KnowledgeBaseEntry entry;
        BSTNode left;
        BSTNode right;

        public BSTNode(KnowledgeBaseEntry entry){
            this.entry = entry;
            this.left = null;
            this.right = null;
        }

    }

    /**
     * Constructor for Binary Search Tree implementation.
     */
    public BSTKB(){
        root = null;
    }

    /**
     * Adds entry to the knowledge base.
     * 
     * @param term          The term.
     * @param sentence      The sentence associated with the term.
     * @param confidence    The confidence score of term/sentence combination.
     */
    @Override
    public void addOrUpdateEntry(String term, String sentence, double confidence) {
        root = addOrUpdateEntry(root, new KnowledgeBaseEntry(term, sentence, confidence));
    }

    /**
     * Recursive helper method for adding/updating entries.
     * 
     * @param node      The BST node.
     * @param entry     An item in the knowledge base
     * @return  Full knowledge base.
     */
    private BSTNode addOrUpdateEntry(BSTNode node, KnowledgeBaseEntry entry) {
        
        if (node == null)
            return new BSTNode(entry);

        int cmp = entry.getTerm().compareTo(node.entry.getTerm());

        if (cmp == 0) 
            node.entry.update(entry.getSentence(), entry.getConfidence());
        else if (cmp < 0) 
            node.left = addOrUpdateEntry(node.left, entry);
        else
            node.right = addOrUpdateEntry(node.right, entry);

        return node;

    }

    /**
     * Search an item by its term.
     * 
     * @param term      The term
     * @return  Item associate with the term.
     */
    @Override
    public KnowledgeBaseEntry searchByTerm(String term) {

        BSTNode node = searchByTerm(root, term);

        return (node != null) ? node.entry : null;

    }

    /**
     * Recursive helper method for searching items by term.
     * 
     * @param node      The BST node.
     * @param term      The term.
     * @return  Item from knowledge base or null, otherwise.
     */
    private BSTNode searchByTerm(BSTNode node, String term) {

        if (node == null)
            return null;
        
        int cmp = term.compareTo(node.entry.getTerm());

        if (cmp == 0)
            return node;
        else if (cmp < 0)
            return searchByTerm(node.left, term);
        else 
            return searchByTerm(node.right, term);
        
        
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
        KnowledgeBaseEntry entry = searchByTerm(term);

        if (entry != null && entry.getSentence().equals(sentence))
            return entry.getConfidence();
        
        return -1;
    }

}