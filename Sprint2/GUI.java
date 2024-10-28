package sprint2;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private JLabel boardSizeLabel;
    private JTextField boardSizeField;
    private JButton btnReplay, btnNewGame;
    private JRadioButton simpleGame, generalGame;

    private JRadioButton blueHuman, blueS, blueO, blueComputer;
    private JRadioButton redHuman, redS, redO, redComputer;
    private JCheckBox recordGameCheckBox;
    private JLabel currentTurnLabel;
    private JLabel blueScoreLabel;
    private JLabel redScoreLabel;
    private JPanel boardPanel;

    public GUI() {
        setTitle("SOS Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(new JLabel("SOS"));
        simpleGame = new JRadioButton("Simple game", true);
        generalGame = new JRadioButton("General game");

        ButtonGroup topPanelGroup = new ButtonGroup();
        topPanelGroup.add(simpleGame);
        topPanelGroup.add(generalGame);

        boardSizeLabel = new JLabel("Board size ");
        boardSizeField = new JTextField("8", 2);

        topPanel.add(simpleGame);
        topPanel.add(generalGame);
        topPanel.add(boardSizeLabel);
        topPanel.add(boardSizeField);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel bluePlayerPanel = new JPanel();
        bluePlayerPanel.setLayout(new BoxLayout(bluePlayerPanel, BoxLayout.Y_AXIS));
        bluePlayerPanel.setBorder(BorderFactory.createTitledBorder("Blue player"));
        blueHuman = new JRadioButton("Human", true);
        blueS = new JRadioButton("S", true);
        blueO = new JRadioButton("O");
        blueComputer = new JRadioButton("Computer");

        ButtonGroup bluePlayerGroup = new ButtonGroup();
        bluePlayerGroup.add(blueHuman);
        bluePlayerGroup.add(blueComputer);

        ButtonGroup blueSOGroup = new ButtonGroup();
        blueSOGroup.add(blueS);
        blueSOGroup.add(blueO);

        JPanel blueSubPanel = new JPanel();
        blueSubPanel.setLayout(new BoxLayout(blueSubPanel, BoxLayout.Y_AXIS));
        blueSubPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        blueSubPanel.add(blueS);
        blueSubPanel.add(blueO);

        bluePlayerPanel.add(blueHuman);
        bluePlayerPanel.add(blueSubPanel);
        bluePlayerPanel.add(blueComputer);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(bluePlayerPanel, gbc);

        boardPanel = new JPanel();
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        boardPanel.setPreferredSize(new Dimension(400, 400));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.8;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(boardPanel, gbc);

        JPanel redPlayerPanel = new JPanel();
        redPlayerPanel.setLayout(new BoxLayout(redPlayerPanel, BoxLayout.Y_AXIS));
        redPlayerPanel.setBorder(BorderFactory.createTitledBorder("Red player"));

        redHuman = new JRadioButton("Human", true);
        redS = new JRadioButton("S", true);
        redO = new JRadioButton("O");
        redComputer = new JRadioButton("Computer");

        ButtonGroup redPlayerGroup = new ButtonGroup();
        redPlayerGroup.add(redHuman);
        redPlayerGroup.add(redComputer);

        ButtonGroup redSOGroup = new ButtonGroup();
        redSOGroup.add(redS);
        redSOGroup.add(redO);

        JPanel redSubPanel = new JPanel();
        redSubPanel.setLayout(new BoxLayout(redSubPanel, BoxLayout.Y_AXIS));
        redSubPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        redSubPanel.add(redS);
        redSubPanel.add(redO);

        redPlayerPanel.add(redHuman);
        redPlayerPanel.add(redSubPanel);
        redPlayerPanel.add(redComputer);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(redPlayerPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        recordGameCheckBox = new JCheckBox("Record game");
        currentTurnLabel = new JLabel("Current turn: Blue");
        blueScoreLabel = new JLabel("Blue Score: 0");
        redScoreLabel = new JLabel("Red Score: 0");
        btnReplay = new JButton("Replay");
        btnNewGame = new JButton("New Game");

        bottomPanel.add(recordGameCheckBox);
        bottomPanel.add(currentTurnLabel);
        bottomPanel.add(blueScoreLabel);
        bottomPanel.add(redScoreLabel);
        bottomPanel.add(btnReplay);
        bottomPanel.add(btnNewGame);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void updateTurnLabel(String player) {
        currentTurnLabel.setText("Current turn: " + player);
    }

    public void updateScores(int blueScore, int redScore) {
        blueScoreLabel.setText("Blue Score: " + blueScore);
        redScoreLabel.setText("Red Score: " + redScore);
    }

    public JTextField getBoardSizeField() {
        return boardSizeField;
    }

    public JButton getBtnNewGame() {
        return btnNewGame;
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    public JRadioButton getBlueS() {
        return blueS;
    }

    public JRadioButton getBlueO() {
        return blueO;
    }

    public JRadioButton getBlueHuman() {
        return blueHuman;
    }

    public JRadioButton getBlueComputer() {
        return blueComputer;
    }

    public JRadioButton getRedS() {
        return redS;
    }

    public JRadioButton getRedO() {
        return redO;
    }

    public JRadioButton getRedHuman() {
        return redHuman;
    }

    public JRadioButton getRedComputer() {
        return redComputer;
    }

    public JRadioButton getSimpleGame() {
        return simpleGame;
    }

    public JRadioButton getGeneralGame() {
        return generalGame;
    }
}
