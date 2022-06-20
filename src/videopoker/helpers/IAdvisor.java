package videopoker.helpers;

import java.util.ArrayList;

import videopoker.Hand;

public interface IAdvisor {
    public ArrayList<Integer> getHoldList(String condition, Hand hand);
}
