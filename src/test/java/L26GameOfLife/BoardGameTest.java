package L26GameOfLife;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class BoardGameTest {

    private Board emptyBoard3x3, emptyBoard5x5;
    BoardGame testGame, testGame2, ThreeInARow, ThreeInaColumn;

    @AfterEach
    void tearDown() {
        emptyBoard3x3 = null;
        emptyBoard5x5 = null;
        testGame = null;
    }

    @BeforeEach
    void setUp() {

        emptyBoard3x3 = new Board(3);
        emptyBoard5x5 = new Board(5);

        ThreeInARow = new BoardGame(emptyBoard5x5);
        ThreeInARow.getBoard().fillBoard("0000000000011100000000000");

        ThreeInaColumn = new BoardGame(emptyBoard5x5);
        ThreeInaColumn.getBoard().fillBoard("0000000100001000010000000");

    }

    @Nested
    class Create {
        @Test
        public void shouldCreateGAmeWithBoard3x3() {
            testGame = new BoardGame(emptyBoard3x3);
            assertEquals(3, testGame.getBoard().getSize());
        }

        @Test
        public void shouldCreateTwoEmptyGamesWithBoard3x3() {
            testGame = new BoardGame(emptyBoard3x3);
            testGame2 = new BoardGame(emptyBoard3x3);
            assertNotNull(testGame);
            assertNotNull(testGame2);
            assertEquals(testGame.getBoard().toString(), testGame2.getBoard().toString());
            assertNotEquals(testGame, testGame2);
        }

        @Test
        public void shouldCreateTwoDifferentGamesWithBoard3x3() {
            testGame = new BoardGame(emptyBoard3x3);
            testGame2 = new BoardGame(emptyBoard3x3);
            String userInput = "1" +
                    "\n1" +
                    "\n1" +
                    "\n1" +
                    "\n1" +
                    "\n1" +
                    "\n1" +
                    "\n1" +
                    "\n1";
            System.setIn(new ByteArrayInputStream(userInput.getBytes()));
            testGame.getBoard().fillBoard();
            assertNotEquals(testGame.getBoard().toString(), testGame2.getBoard().toString());
        }
    }


    @Test
    public void shouldCalculateNumberLivingNeighbors() {
        assertEquals(0, ThreeInARow.numberAliveNeighbors(0,0));
        assertEquals(0, ThreeInARow.numberAliveNeighbors(0,1));
        assertEquals(0, ThreeInARow.numberAliveNeighbors(0,2));
        assertEquals(0, ThreeInARow.numberAliveNeighbors(0,3));
        assertEquals(0, ThreeInARow.numberAliveNeighbors(0,4));
        assertEquals(1, ThreeInARow.numberAliveNeighbors(1,0));
        assertEquals(2, ThreeInARow.numberAliveNeighbors(1,1));
        assertEquals(3, ThreeInARow.numberAliveNeighbors(1,2));
        assertEquals(2, ThreeInARow.numberAliveNeighbors(1,3));
        assertEquals(1, ThreeInARow.numberAliveNeighbors(1,4));
        assertEquals(1, ThreeInARow.numberAliveNeighbors(2,0));
        assertEquals(1, ThreeInARow.numberAliveNeighbors(2,1));
        assertEquals(2, ThreeInARow.numberAliveNeighbors(2,2));
        assertEquals(1, ThreeInARow.numberAliveNeighbors(2,3));
        assertEquals(1, ThreeInARow.numberAliveNeighbors(2,4));
        assertEquals(1, ThreeInARow.numberAliveNeighbors(3,0));
        assertEquals(2, ThreeInARow.numberAliveNeighbors(3,1));
        assertEquals(3, ThreeInARow.numberAliveNeighbors(3,2));
        assertEquals(2, ThreeInARow.numberAliveNeighbors(3,3));
        assertEquals(1, ThreeInARow.numberAliveNeighbors(3,4));
        assertEquals(0, ThreeInARow.numberAliveNeighbors(4,0));
        assertEquals(0, ThreeInARow.numberAliveNeighbors(4,1));
        assertEquals(0, ThreeInARow.numberAliveNeighbors(4,2));
        assertEquals(0, ThreeInARow.numberAliveNeighbors(4,3));
        assertEquals(0, ThreeInARow.numberAliveNeighbors(4,4));

    }

    @Test
    public void shouldCreateNewGen() {
        ThreeInARow.nextGen();
        assertEquals(ThreeInARow.getBoard().toString(), ThreeInaColumn.getBoard().toString());
        testGame = new BoardGame(new Board(4));
        testGame.getBoard().fillBoard("0000111000011110");
        System.out.println(testGame.getBoard().toString());
        testGame.nextGen();
        System.out.println(testGame.getBoard().toString());
    }

}