package sprint2;

public class GeneralGameMode extends GameModeBase {
    private int blueScore;
    private int redScore;

    public GeneralGameMode(int boardSize) {
        super(boardSize);
        this.blueScore = 0;
        this.redScore = 0;
    }

    @Override
    protected void handleSOSFound() {
        if (currentPlayer == 'B') {
            blueScore++;
        } else {
            redScore++;
        }
        switchPlayer();
    }

    @Override
    public int getBlueScore() {
        return blueScore;
    }

    @Override
    public int getRedScore() {
        return redScore;
    }
}
