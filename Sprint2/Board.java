package sprint2;

import java.awt.*;
import javax.swing.*;
import java.util.List;

public class Board {
    private String currentPlayer;
    private int boardSize;
    private GUI gui;
    private boolean isSimpleMode;
    private GameMode gameMode;

    public Board(GUI gui) {
        this.gui = gui;

        gui.getBtnNewGame().addActionListener(e -> {
            isSimpleMode = gui.getSimpleGame().isSelected();
            generateGameBoard();
        });
    }

    private void generateGameBoard() {
        if (isSimpleMode) {
            if (boardSize != 3) {
                boardSize = 3;
                JOptionPane.showMessageDialog(gui, "Invalid board size. Defaulting to 3x3.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            gameMode = new SimpleGameMode();
        } else {
            try {
                boardSize = Integer.parseInt(gui.getBoardSizeField().getText());
                if (boardSize < 3 || boardSize > 8) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                boardSize = 8;
                JOptionPane.showMessageDialog(gui, "Invalid board size. Defaulting to 8x8.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            gameMode = new GeneralGameMode(boardSize);
        }

        currentPlayer = "Blue";

        gui.getBoardPanel().removeAll();
        gui.getBoardPanel().setLayout(new GridLayout(boardSize, boardSize));

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                GameButton button = new GameButton(" ");
                button.setFont(new Font("Arial", Font.BOLD, 35));
                int finalRow = row;
                int finalCol = col;
                button.addActionListener(e -> handleButtonClick(button, finalRow, finalCol));
                gui.getBoardPanel().add(button);
            }
        }

        gui.getBoardPanel().revalidate();
        gui.getBoardPanel().repaint();

        gui.updateTurnLabel(currentPlayer);
        gui.updateScores(0, 0);
    }

    private void handleButtonClick(GameButton button, int row, int col) {
        if (button.getText().equals(" ")) {
            char selectedLetter = getCurrentSelectedLetter();

            button.setText(String.valueOf(selectedLetter));
            button.setForeground(currentPlayerColor());

            boolean sosFormed = gameMode.makeMove(row, col, selectedLetter);

            List<GameModeBase.SOSSequence> sosSequences = gameMode.getLastMoveSequences();

            if (sosFormed) {
                drawSOSLines(sosSequences);

                if (!isSimpleMode) {
                    int blueScore = gameMode.getBlueScore();
                    int redScore = gameMode.getRedScore();
                    gui.updateScores(blueScore, redScore);
                } else {
                    JOptionPane.showMessageDialog(gui, "Player " + currentPlayer + " wins!", "Game Over",
                            JOptionPane.INFORMATION_MESSAGE);
                    disableBoard();
                    return;
                }
            }

            switchPlayer();
            gui.updateTurnLabel(currentPlayer);

            if (gameMode.isGameOver()) {
                if (isSimpleMode) {
                    JOptionPane.showMessageDialog(gui, "Game Over! It's a draw.", "Game Over",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int blueScore = gameMode.getBlueScore();
                    int redScore = gameMode.getRedScore();
                    String winner;
                    if (blueScore > redScore) {
                        winner = "Blue wins!";
                    } else if (redScore > blueScore) {
                        winner = "Red wins!";
                    } else {
                        winner = "It's a draw!";
                    }
                    JOptionPane.showMessageDialog(gui, winner, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                }
                disableBoard();
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.equals("Blue") ? "Red" : "Blue";
    }

    private char getCurrentSelectedLetter() {
        char selectedLetter;
        if (currentPlayer.equals("Blue")) {
            selectedLetter = gui.getBlueS().isSelected() ? 'S' : 'O';
        } else {
            selectedLetter = gui.getRedS().isSelected() ? 'S' : 'O';
        }
        System.out.println("Current Player: " + currentPlayer + ", Selected Letter: " + selectedLetter);
        return selectedLetter;
    }

    private Color currentPlayerColor() {
        return currentPlayer.equals("Blue") ? Color.BLUE : Color.RED;
    }

    private void drawSOSLines(List<GameModeBase.SOSSequence> sequences) {
        for (GameModeBase.SOSSequence sequence : sequences) {
            GameButton firstButton = getButtonAt(sequence.row1, sequence.col1);
            GameButton secondButton = getButtonAt(sequence.row2, sequence.col2);
            GameButton thirdButton = getButtonAt(sequence.row3, sequence.col3);

            if (firstButton != null && secondButton != null && thirdButton != null) {
                Color color = currentPlayerColor();
                GameButton.Line line = new GameButton.Line(color, sequence.direction);
                firstButton.addLine(line);
                secondButton.addLine(line);
                thirdButton.addLine(line);

                firstButton.repaint();
                secondButton.repaint();
                thirdButton.repaint();
            }
        }
    }

    private GameButton getButtonAt(int row, int col) {
        if (row >= 0 && row < boardSize && col >= 0 && col < boardSize) {
            int index = row * boardSize + col;
            Component component = gui.getBoardPanel().getComponent(index);
            if (component instanceof GameButton) {
                return (GameButton) component;
            }
        }
        return null;
    }

    private void disableBoard() {
        Component[] components = gui.getBoardPanel().getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                component.setEnabled(false);
            }
        }
    }
}
