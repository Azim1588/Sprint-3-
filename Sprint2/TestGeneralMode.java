package sprint2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestGeneralMode {
    private GeneralGameMode gameMode;

    @Before
    public void setUp() {
        gameMode = new GeneralGameMode(8);
    }

    @Test
    public void testMakeMove() {

        assertEquals("Initial player should be Blue ('B')", 'B', gameMode.getCurrentPlayer());
        assertEquals("Cell should be empty initially", ' ', gameMode.getCell(4, 4));

        boolean sosFormed = gameMode.makeMove(4, 4, 'O');

        assertEquals("Cell should have an 'O' after the move", 'O', gameMode.getCell(4, 4));
        assertFalse("No SOS should be formed", sosFormed);

        assertEquals("Blue score should be 0", 0, gameMode.getBlueScore());
        assertEquals("Red score should be 0", 0, gameMode.getRedScore());

        gameMode.switchPlayer();

        sosFormed = gameMode.makeMove(3, 3, 'S');

        assertEquals("Cell should have an 'S' after the move", 'S', gameMode.getCell(3, 3));

        assertEquals("Red score should still be 0", 0, gameMode.getRedScore());
    }
}