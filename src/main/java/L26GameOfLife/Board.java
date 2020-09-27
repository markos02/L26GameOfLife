package L26GameOfLife;

import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private int size;
    private Boolean[][] boardState;


    public Board(int size) {

        if (size > 0) {
            this.size = size;
            this.boardState = new Boolean[size][size];
            for (int i = 0; i < size; i++) {
                Arrays.fill(boardState[i], Boolean.FALSE);
            }

        } else {
            throw new IllegalArgumentException(String.format("Invalid board size", size));
        }
    }


    public int getSize() {
        return size;
    }

    public Boolean getBoardStatexy(int i, int j) {
        return boardState[i][j];
    }

    public void setBoardState(int i, int j, Boolean state) {
        boardState[i][j] = state;
    }

    public void fillBoard() {
        System.out.println("If cell is alive press 1");
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.println("(" + i + ", " + j + "): ");
                if (input.nextInt() == 1) {
                    setBoardState(i, j, true);
                }
            }
        }
    }

    public void fillBoard(String boardStateString) {
        if (boardStateString.length() != size * size) {
            throw new IllegalArgumentException(String.format("Invalid number of signs", boardStateString));
        }
        ;
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (boardStateString.charAt(index) == '1') {
                    setBoardState(i, j, true);
                }
                index++;
            }
        }

    }

    public void fillRandom() {
        StringBuilder randomSequence = new StringBuilder();

        for (int i = 0; i < size * size; i++) {
            int random = (int) Math.round(Math.random());
            randomSequence.append(random);
        }

        String randomString = randomSequence.toString();
        fillBoard(randomString);
    }

    @Override
    public String toString() {
        StringBuilder boardRepresentation = new StringBuilder();

        boardRepresentation.append('+');
        for (int i = 0; i < size; i++) {
            boardRepresentation.append(" -");
        }
        boardRepresentation.append(" +");
        boardRepresentation.append(System.lineSeparator());

        for (int i = 0; i < size; i++) {
            boardRepresentation.append('|');
            for (int j = 0; j < size; j++) {
                if (boardState[i][j]) {
                    boardRepresentation.append(" O");
                } else {
                    boardRepresentation.append("  ");
                }
            }
            boardRepresentation.append(" |");
            boardRepresentation.append(System.lineSeparator());
        }

        boardRepresentation.append('+');
        for (int i = 0; i < size; i++) {
            boardRepresentation.append(" -");
        }
        boardRepresentation.append(" +");

        return boardRepresentation.toString();
    }

}

