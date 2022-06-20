package videopoker.helpers;

import videopoker.Hand;

/**
 * Interface for card analizer.
 */
public interface ICardAnalizer {

    /**
     * This method is used to evaluate a hand and return the best advice given said hand.
     * <p>
     * The Hand <b>hand</b> is used to be evaluated and return the best advice. To evaluate a hand
     * it will pass through a series of private methods to identify the structure of a hand
     * according to the napes and numbers of the cards it contains.
     * 
     * @param hand        The hand to be evaluated
     */
    public String getPayTableResult(Hand hand);
}
