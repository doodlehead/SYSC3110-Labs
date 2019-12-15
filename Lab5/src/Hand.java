import java.util.*;
import java.util.Map.Entry;

//import Card.Rank;

/**
 * A poker hand is a list of cards, which can be of some "kind" (pair, straight, etc.)
 * 
 */
public class Hand implements Comparable<Hand> {

    public enum Kind {HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, 
        FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH}

    private final List<Card> cards;

    /**
     * Create a hand from a string containing all cards (e,g, "5C TD AH QS 2D")
     */
    public Hand(String c) {
    	this.cards = new ArrayList<>();
    	
        String[] tokens = c.split(" ");
        for(String s: tokens) {
        	this.cards.add(new Card(s));
        }
    }
    
    public ArrayList<Card.Rank> getRanks() {
    	ArrayList<Card.Rank> ranks = new ArrayList<>();
    	for(Card card: this.cards) {
    		ranks.add(card.getRank());
    	}
    	return ranks;
    }
    
    /**
     * @returns true if the hand has n cards of the same rank
	 * e.g., "TD TC TH 7C 7D" returns True for n=2 and n=3, and False for n=1 and n=4
     */
    protected boolean hasNKind(int n) {
    	int count = 0;
    	HashMap<Card.Rank, Integer> occurences = new HashMap<>();
    	
    	for(Card card: this.cards) {
    		//Increment if it exists
    		if(occurences.containsKey(card.getRank())) {
    			occurences.put(card.getRank(), occurences.get(card.getRank()) + 1);
    		} else { //Otherwise put it in
    			occurences.put(card.getRank(), 1);
    		}
    	}
    	
    	for(Entry<Card.Rank, Integer> e: occurences.entrySet()) {
    		if(e.getValue() == n) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
	 * Optional: you may skip this one. If so, just make it return False
     * @returns true if the hand has two pairs
     */
    public boolean isTwoPair() {
    	return false;
    }   
    
    /**
     * @returns true if the hand is a straight 
     */
    public boolean isStraight() {
    	boolean r1 = true;
    	boolean r2 = false;
    	
    	ArrayList<Card> temp = new ArrayList<>(this.cards);
    	temp.sort((c1, c2) -> Integer.compare(c1.getRank().ordinal(), c2.getRank().ordinal()));
    	
    	//Check for low ace
    	if(temp.get(temp.size() - 1).getRank() == Card.Rank.ACE) {
    		//Hard code this YEET
    		r2 = temp.get(0).getRank() == Card.Rank.DEUCE
    				&& temp.get(1).getRank() == Card.Rank.THREE
    				&& temp.get(2).getRank() == Card.Rank.FOUR
    				&& temp.get(3).getRank() == Card.Rank.FIVE;
    	}
    	
    	for(int i=1; i < temp.size(); i++) {
    		if(temp.get(i).getRank().ordinal() - temp.get(i-1).getRank().ordinal() != 1) {
    			r1 = false;
    			break;
    		}
    	}

    	return r1 || r2;
    }
    
    /**
     * @returns true if the hand is a flush
     */
    public boolean isFlush() {
    	Card.Suit suit = this.cards.get(0).getSuit();
    	
    	for(int i=1; i < this.cards.size(); i++) {
    		if(this.cards.get(i).getSuit() != suit) {
    			return false;
    		}
    	}
    	return true;
    }
    
    @Override
    public int compareTo(Hand h) {
        //hint: delegate!
		//and don't worry about breaking ties
    	return Integer.compare(this.kind().ordinal(), h.kind().ordinal());
    }
    
    /**
	 * This method is already implemented and could be useful! 
     * @returns the "kind" of the hand: flush, full house, etc.
     */
    public Kind kind() {
        if (isStraight() && isFlush()) return Kind.STRAIGHT_FLUSH;
        else if (hasNKind(4)) return Kind.FOUR_OF_A_KIND; 
        else if (hasNKind(3) && hasNKind(2)) return Kind.FULL_HOUSE;
        else if (isFlush()) return Kind.FLUSH;
        else if (isStraight()) return Kind.STRAIGHT;
        else if (hasNKind(3)) return Kind.THREE_OF_A_KIND;
        else if (isTwoPair()) return Kind.TWO_PAIR;
        else if (hasNKind(2)) return Kind.PAIR; 
        else return Kind.HIGH_CARD;
    }
    
    public String toString() {
    	String ret = "";
    	for(Card c: this.cards) {
    		ret += c.toString() + " ";
    	}
    	return ret;
    }

}