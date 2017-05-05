package test.company;

import java.util.List;
import java.util.Scanner;

/**
 * Created by pratap on 4/23/17.
 */
public abstract class AbstractBowlingGame implements IBowlingGame {

    public static final String GAME_TYPE = Constants.STANDARD;

    private int numberOfPlayers;
    private int numberOfSets;
    private int eachSetRound;
    private int pinDownPoint;
    private int strikeExtraPoint;
    private int spareExtraPoint;
    private int maxFinalSetRound;
    private int numberOfPins;

    public int getMaxFinalSetRound() {
        return maxFinalSetRound;
    }

    public int getNumberOfPins() {
        return numberOfPins;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    public int getEachSetRound() {
        return eachSetRound;
    }

    public int getPinDownPoint() {
        return pinDownPoint;
    }

    public int getStrikeExtraPoint() {
        return strikeExtraPoint;
    }

    public int getSpareExtraPoint() {
        return spareExtraPoint;
    }

    public AbstractBowlingGame() {

    }

    public AbstractBowlingGame(int nPlayers, int nSets, int nRound, int point, int strikePoint, int sparePoint, int maxRound, int nPins) {
        numberOfPlayers = nPlayers;
        numberOfSets = nSets;
        eachSetRound = nRound;
        pinDownPoint = point;
        strikeExtraPoint = strikePoint;
        spareExtraPoint = sparePoint;
        maxFinalSetRound = maxRound;
        numberOfPins = nPins;
    }

    public abstract void evaluate(AbstractBowlingGame bowlingGame, List<Player> playerList, Scanner sc);
}
