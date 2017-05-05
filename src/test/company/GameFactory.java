package test.company;
import test.company.exceptions.GameTypeNotSupportedException;

/**
 * Created by pratap on 4/23/17.
 */
public class GameFactory {
    public AbstractBowlingGame getGame(String type, int nPlayers) throws GameTypeNotSupportedException{
        AbstractBowlingGame game = null;
        if (type.equals(Constants.STANDARD)) {
            game = new BowlingGame(nPlayers, 2, 2, 1, 10, 5, 3, 10);
        } else {
            throw new GameTypeNotSupportedException(type);
        }
        return game;
    }
}
