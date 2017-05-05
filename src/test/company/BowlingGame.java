package test.company;

import java.util.List;
import java.util.Scanner;

/**
 * Created by pratap on 4/23/17.
 */
public class BowlingGame extends AbstractBowlingGame {

    public static final String GAME_TYPE = Constants.STANDARD;

    //Map<Player, Integer> scoreMap

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

    public BowlingGame(int nPlayers, int nSets, int nRound, int point, int strikePoint, int sparePoint, int maxRound, int nPins) {
        numberOfPlayers = nPlayers;
        numberOfSets = nSets;
        eachSetRound = nRound;
        pinDownPoint = point;
        strikeExtraPoint = strikePoint;
        spareExtraPoint = sparePoint;
        maxFinalSetRound = maxRound;
        numberOfPins = nPins;
    }

    public void evaluate(AbstractBowlingGame bowlingGame, List<Player> playerList, Scanner sc) {
        int count = 0;
        for (int set = 1; set <= bowlingGame.getNumberOfSets(); set++) {
            for (int player = 0; player < playerList.size(); player++) {
                if (set == bowlingGame.getNumberOfSets())
                    count = 0;
                int remainingPins = bowlingGame.getNumberOfPins();
                boolean flag = false;
                for (int round = 1; round <= bowlingGame.getEachSetRound() && count < 3; ) {
                    String point = sc.nextLine();
                    Player gamePlayer = playerList.get(player);
                    gamePlayer.scoreList.add(point);
                    int pins;
                    if (point.equals(Constants.STRIKE)) {
                        pins = bowlingGame.getNumberOfPins();
                        if (pins > remainingPins) {
                            System.out.println("Invalid input");
                            System.exit(0);
                        } else {
                            gamePlayer.score += pins * bowlingGame.getPinDownPoint();
                        }
                        gamePlayer.score += bowlingGame.getStrikeExtraPoint();
                        if (set != bowlingGame.getNumberOfSets()) {
                            remainingPins = 0;
                            flag = true;
                            round++;
                        } else if (set == bowlingGame.getNumberOfSets()) {
                            round = 1;
                            remainingPins = bowlingGame.getNumberOfPins();
                            count++;
                        }

                    } else if (round == 2 && point.equals(Constants.SPARE)) {
                        int previousScore = Integer.parseInt(gamePlayer.scoreList.get(gamePlayer.scoreList.size() - 2));
                        pins = bowlingGame.getNumberOfPins() - (previousScore / bowlingGame.getPinDownPoint());
                        if (pins > remainingPins) {
                            System.out.println("Invalid input");
                            System.exit(0);
                        } else {
                            gamePlayer.score += pins * bowlingGame.getPinDownPoint();
                        }
                        gamePlayer.score += bowlingGame.getSpareExtraPoint();
                        if (set != bowlingGame.getNumberOfSets()) {
                            remainingPins = 0;
                            round++;
                        } else {
                            remainingPins = bowlingGame.getNumberOfPins();
                            round = 2;
                            if (count == 3)
                                flag = true;
                            else
                                count++;
                        }
                    } else {
                        try {
                            gamePlayer.score += Integer.parseInt(point) * bowlingGame.getPinDownPoint();
                        } catch (Exception e) {
                            System.out.println("Invalid input");
                            System.exit(0);
                        }
                        remainingPins -= Integer.parseInt(point);
                        round++;
                        if (set == bowlingGame.getNumberOfSets()) {
                            count++;
                        }
                    }

                    printRoundResult(playerList);
                    if (flag)
                        break;
                }
            }
        }
    }

    private void printRoundResult(List<Player> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            System.out.print(playerList.get(i).name + ":");
            System.out.print(playerList.get(i).scoreList);
            System.out.print(" -> " + playerList.get(i).score);
            System.out.println();
        }
    }
}
