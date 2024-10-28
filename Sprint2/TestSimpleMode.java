package sprint2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestSimpleMode {
    private SimpleGameMode gameMode;

    @Before
    public void setUp() {
        gameMode = new SimpleGameMode();
    }

    @Test
    public void testMakeMove() {

        assertEquals("Initial player should be Blue ('B')", 'B', gameMode.getCurrentPlayer());
        assertEquals("Cell should be empty initially", ' ', gameMode.getCell(1, 1));

        boolean sosFormed = gameMode.makeMove(1, 1, 'S');

        assertEquals("Cell should have an 'S' after the move", 'S', gameMode.getCell(1, 1));
        assertFalse("No SOS should be formed yet", sosFormed);

        gameMode.switchPlayer();

        sosFormed = gameMode.makeMove(0, 0, 'O');

        assertEquals("Cell should have an 'O' after the move", 'O', gameMode.getCell(0, 0));
    }
}
