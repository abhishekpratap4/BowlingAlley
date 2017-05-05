package test.company;

import test.company.exceptions.GameTypeNotSupportedException;

import java.util.*;

/**
 * Created by pratap on 4/23/17.
 */
public class Evaluator {

    List<Player> playerList;
    AbstractBowlingGame bowlingGame = null;

    public static void main(String[] args) {
        Evaluator evaluator = new Evaluator();
        Scanner sc = new Scanner(System.in);
        evaluator.initialize(sc);
        evaluator.computeScores(sc);
        evaluator.getWinner();
    }

    public void initialize(Scanner sc) {
        int numberOfPlayers = Integer.parseInt(sc.nextLine());
        GameFactory factory = new GameFactory();
        try {
            bowlingGame = factory.getGame("Standard", numberOfPlayers);
        } catch (GameTypeNotSupportedException e) {
            // handle exception
            System.exit(0);
        }

        if (bowlingGame != null) {
            playerList = new ArrayList<>();
            for (int i = 0; i < bowlingGame.getNumberOfPlayers(); i++) {
                playerList.add(new Player("Player" + (i + 1)));
            }
        }
    }

    public void computeScores(Scanner sc) {
        bowlingGame.evaluate(bowlingGame, playerList, sc);
    }

    private void getWinner() {
        playerList.sort((o1, o2) -> o1.score < o2.score ? -1 : o1.score == o2.score ? 0 : 1);
        System.out.println("Winner is :" + playerList.get(playerList.size() - 1).name + " -> " + playerList.get(playerList.size() - 1).score);
    }
}
