package sprint2;

import java.util.List;

public interface GameMode {
    boolean makeMove(int row, int col, char letter);

    char getCurrentPlayer();

    int getBlueScore();

    int getRedScore();

    boolean isGameOver();

    void switchPlayer();

    List<GameModeBase.SOSSequence> getLastMoveSequences();
}
