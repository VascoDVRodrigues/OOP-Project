package videopoker.helpers;

import java.util.ArrayList;

import videopoker.Hand;

/**
 * Interface for the advisor.
 */

public interface IAdvisor {
    /**
     * It returns an array of the indexes of the cards that should be held.
     * 
     * @param condition The advice that results in the best odds of success of the given hand.
     * @param hand the hand you're evaluating
     * @return The holdList is being returned.
     */
    public ArrayList<Integer> getHoldList(String condition, Hand hand);
}
