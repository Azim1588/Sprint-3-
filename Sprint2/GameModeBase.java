package sprint2;

import java.util.ArrayList;
import java.util.List;

public abstract class GameModeBase implements GameMode {
    protected char[][] grid;
    protected int boardSize;
    protected char currentPlayer;
    protected List<SOSSequence> lastMoveSequences;

    public GameModeBase(int boardSize) {
        this.boardSize = boardSize;
        this.grid = new char[boardSize][boardSize];
        this.currentPlayer = 'B';
        initBoard();
    }

    protected void initBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    @Override
    public boolean makeMove(int row, int col, char letter) {
        if (isValidMove(row, col)) {
            grid[row][col] = letter;
            lastMoveSequences = new ArrayList<>();
            boolean sosFormed = checkForSOS(row, col);
            if (sosFormed) {
                handleSOSFound();
            } else {
                switchPlayer();
            }
            return sosFormed;
        }
        return false;
    }

    protected boolean isValidMove(int row, int col) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize && grid[row][col] == ' ';
    }

    protected boolean checkForSOS(int row, int col) {
        boolean sosFound = false;
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, -1 } };
        GameButton.Direction[] lineDirections = { GameButton.Direction.HORIZONTAL, GameButton.Direction.VERTICAL,
                GameButton.Direction.DIAGONAL_RIGHT, GameButton.Direction.DIAGONAL_LEFT };

        for (int i = 0; i < directions.length; i++) {
            int dr = directions[i][0];
            int dc = directions[i][1];
            GameButton.Direction direction = lineDirections[i];
            SOSSequence sequence = checkDirection(row, col, dr, dc, direction);
            if (sequence != null) {
                sosFound = true;
                lastMoveSequences.add(sequence);
            }
        }
        return sosFound;
    }

    protected SOSSequence checkDirection(int row, int col, int dr, int dc, GameButton.Direction direction) {
        char current = grid[row][col];

        if (current == 'S' && getCell(row - dr, col - dc) == 'O' && getCell(row - 2 * dr, col - 2 * dc) == 'S') {
            return new SOSSequence(row - 2 * dr, col - 2 * dc, row - dr, col - dc, row, col, direction);
        }

        if (getCell(row - dr, col - dc) == 'S' && current == 'O' && getCell(row + dr, col + dc) == 'S') {
            return new SOSSequence(row - dr, col - dc, row, col, row + dr, col + dc, direction);
        }

        if (current == 'S' && getCell(row + dr, col + dc) == 'O' && getCell(row + 2 * dr, col + 2 * dc) == 'S') {
            return new SOSSequence(row, col, row + dr, col + dc, row + 2 * dr, col + 2 * dc, direction);
        }
        return null;
    }

    protected char getCell(int row, int col) {
        if (row >= 0 && row < boardSize && col >= 0 && col < boardSize) {
            return grid[row][col];
        } else {
            return ' ';
        }
    }

    @Override
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'B') ? 'R' : 'B';
    }

    @Override
    public List<SOSSequence> getLastMoveSequences() {
        return lastMoveSequences;
    }

    @Override
    public boolean isGameOver() {
        return isBoardFull();
    }

    protected boolean isBoardFull() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    protected abstract void handleSOSFound();

    public class SOSSequence {
        public int row1, col1;
        public int row2, col2;
        public int row3, col3;
        public GameButton.Direction direction;

        public SOSSequence(int row1, int col1, int row2, int col2, int row3, int col3, GameButton.Direction direction) {
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
            this.row3 = row3;
            this.col3 = col3;
            this.direction = direction;
        }
    }
}
