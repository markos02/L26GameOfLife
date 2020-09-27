package L26GameOfLife;

import org.junit.Assert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board boardTest, boardTest2;

    @Nested
    class Create {
        @Test
        public void shouldNotCreateBoardWIthNegativeParameter() {
            IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
                boardTest = new Board(-1);
            });
            assertThat(exception.getMessage(), is("Invalid board size"));
        }

        @Test
        public void shouldNotCreateBoardWIthZeroParameter() {
            IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
                boardTest = new Board(0);
            });
            assertThat(exception.getMessage(), is("Invalid board size"));
        }


        @Test
        public void shouldCreateBoard2x2() {
            String expected = "+ - - +" + System.lineSeparator() +
                    "|     |" + System.lineSeparator() +
                    "|     |" + System.lineSeparator() +
                    "+ - - +";
            boardTest = new Board(2);
            assertEquals(expected, boardTest.toString());
            assertEquals(2, boardTest.getSize());
        }

        @Test
        public void shouldCreateBoard5x5() {
            String expected = "+ - - - - - +" + System.lineSeparator() +
                    "|           |" + System.lineSeparator() +
                    "|           |" + System.lineSeparator() +
                    "|           |" + System.lineSeparator() +
                    "|           |" + System.lineSeparator() +
                    "|           |" + System.lineSeparator() +
                    "+ - - - - - +";
            boardTest = new Board(5);
            assertEquals(expected, boardTest.toString());
            assertEquals(5, boardTest.getSize());
        }

        @Test
        public void shouldCreateBoard3x3() {
            String expected = "+ - - - +" + System.lineSeparator() +
                    "|       |" + System.lineSeparator() +
                    "|       |" + System.lineSeparator() +
                    "|       |" + System.lineSeparator() +
                    "+ - - - +";
            boardTest = new Board(3);
            assertEquals(expected, boardTest.toString());
            assertEquals(3, boardTest.getSize());
        }
    }

    @Nested
    class Set {

        @Test
        public void shouldSetBoardState() {
            boardTest = new Board(3);
            boardTest.setBoardState(1, 1, true);
            boolean expected = true;
            assertEquals(expected, boardTest.getBoardStatexy(1, 1));
            assertEquals(false, boardTest.getBoardStatexy(0, 1));
        }

    }

    @Nested
    class Fill {

        @Test
        public void shouldFillBoardWithAliveCells_Method1() {
            boardTest = new Board(3);
            String expected = "+ - - - +" + System.lineSeparator() +
                    "| O O O |" + System.lineSeparator() +
                    "| O O O |" + System.lineSeparator() +
                    "| O O O |" + System.lineSeparator() +
                    "+ - - - +";
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
            boardTest.fillBoard();
            assertEquals(expected, boardTest.toString());
        }

        @Test
        public void shouldFillBoardWithSomeAliveCells_Method1() {
            boardTest = new Board(3);
            String expected = "+ - - - +" + System.lineSeparator() +
                    "|   O   |" + System.lineSeparator() +
                    "| O   O |" + System.lineSeparator() +
                    "|     O |" + System.lineSeparator() +
                    "+ - - - +";
            String userInput = "0" +
                    "\n1" +
                    "\n0" +
                    "\n1" +
                    "\n3" +
                    "\n1" +
                    "\n67" +
                    "\n6" +
                    "\n1";
            System.setIn(new ByteArrayInputStream(userInput.getBytes()));
            boardTest.fillBoard();
            assertEquals(expected, boardTest.toString());
        }

        @Test
        public void shouldFillBoardWithAliveCells_Method2() {
            boardTest = new Board(3);
            String expected = "+ - - - +" + System.lineSeparator() +
                    "| O O O |" + System.lineSeparator() +
                    "| O O O |" + System.lineSeparator() +
                    "| O O O |" + System.lineSeparator() +
                    "+ - - - +";
            boardTest.fillBoard("111111111");
            assertEquals(expected, boardTest.toString());
        }

        @Test
        public void shouldFillBoardWithSomeAliveCells_Method2() {
            boardTest = new Board(3);
            String expected = "+ - - - +" + System.lineSeparator() +
                    "|   O   |" + System.lineSeparator() +
                    "| O   O |" + System.lineSeparator() +
                    "|     O |" + System.lineSeparator() +
                    "+ - - - +";
            String userInput = "0" +
                    "\n1" +
                    "\n0" +
                    "\n1" +
                    "\n3" +
                    "\n1" +
                    "\n67" +
                    "\n6" +
                    "\n1";
            boardTest.fillBoard("010101001");
            assertEquals(expected, boardTest.toString());
        }

        @Test
        public void shouldNotFillBoardWithTooShortString() {
            boardTest = new Board(3);
            IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
                boardTest.fillBoard("00110");
            });
            assertThat(exception.getMessage(), is("Invalid number of signs"));
        }

        @Test
        public void shouldNotFillBoardWithTooLongString() {
            boardTest = new Board(3);
            IllegalArgumentException exception = Assert.assertThrows(IllegalArgumentException.class, () -> {
                boardTest.fillBoard("00110111012");
            });
            assertThat(exception.getMessage(), is("Invalid number of signs"));
        }

        @Test
        public void fillShouldAcceptLetters() {
            boardTest = new Board(3);
            boardTest.fillBoard("0a00s1xyz");
            assertEquals(true, boardTest.getBoardStatexy(1, 2));
        }

        @Test
        public void shouldFillRandomly(){
            boardTest = new Board(5);
            boardTest.fillRandom();
            assertNotNull(boardTest);
        }
    }

    @Nested
    class MultipleObjects {
        @Test
        public void shouldCreateTwoBoardsWIthSameSize() {
            boardTest = new Board(3);
            boardTest2 = new Board(3);
            assertNotNull(boardTest);
            assertNotNull(boardTest2);
            assertNotEquals(boardTest, boardTest2);
        }

        @Test
        public void shouldCreateTwoBoardsWIthSameSize2() {
            boardTest = new Board(3);
            boardTest2 = new Board(3);
            boardTest.setBoardState(1, 1, true);
            assertNotNull(boardTest);
            assertNotNull(boardTest2);
            assertNotEquals(boardTest.toString(), boardTest2.toString());
        }
    }
}