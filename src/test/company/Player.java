package test.company;

import java.util.*;

/**
 * Created by pratap on 4/23/17.
 */
public class Player {
    String name;
    int score;
    List<String> scoreList;

    Player(String pName) {
        name = pName;
        score = 0;
        scoreList = new ArrayList<>();
    }
}
