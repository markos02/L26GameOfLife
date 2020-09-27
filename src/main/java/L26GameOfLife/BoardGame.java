package L26GameOfLife;


import java.util.Scanner;

public class BoardGame {

    private Board board;

    public BoardGame(Board board) {
        this.board = new Board(board.getSize());
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                this.board.setBoardState(i, j, board.getBoardStatexy(i, j));
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public int numberAliveNeighbors(int i, int j) {
        int counter = 0;
        int[] columns = new int[3];
        int[] rows = new int[3];

        columns[1] = i;

        if (i == 0) {
            columns[0] = board.getSize() - 1;
        } else {
            columns[0] = i - 1;
        }

        if (i == board.getSize() - 1) {
            columns[2] = 0;
        } else {
            columns[2] = i + 1;
        }

        rows[1] = j;

        if (j == 0) {
            rows[0] = board.getSize() - 1;
        } else {
            rows[0] = j - 1;
        }

        if (j == board.getSize() - 1) {
            rows[2] = 0;
        } else {
            rows[2] = j + 1;
        }

        for (int x : columns) {
            for (int y : rows) {
                if (board.getBoardStatexy(x, y) == true) {
                    counter++;
                }

            }
        }


        if (board.getBoardStatexy(i, j) == true) {
            counter--;
        }

        return counter;
    }

    public void nextGen() {
        Boolean[][] temp = new Boolean[board.getSize()][board.getSize()];

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getBoardStatexy(i, j)) {
                    if ((numberAliveNeighbors(i, j) < 2) || (numberAliveNeighbors(i, j) > 3)) {
                        temp[i][j] = false;
                    } else {
                        temp[i][j] = true;
                    }

                } else {
                    if (numberAliveNeighbors(i, j) == 3) {
                        temp[i][j] = true;
                    } else {
                        temp[i][j] = false;
                    }
                }

            }

        }


        for (int i = 0; i < board.getSize() - 1; i++) {
            for (int j = 0; j < board.getSize() - 1; j++) {
                board.setBoardState(i, j, temp[i][j]);
            }

        }
    }

    public void play() {
        System.out.println("How would you like to fill the board: ");
        System.out.println("1: User input - cell by cell");
        System.out.println("2: User input - string");
        System.out.println("3: Randomly");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                board.fillBoard();
                break;
            case 2:
                System.out.println("Write your string- " + Math.pow(board.getSize(), 2) + " characters: ");
                String string = input.next();
                board.fillBoard(string);
                break;
            case 3:
                board.fillRandom();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

        System.out.println("Start board:");
        System.out.println(board.toString());

        int i = 1;

        while (i != 0) {
            System.out.println("1- create next generation");
            System.out.println("0 - exit");
            i = input.nextInt();
            nextGen();
            System.out.println(System.lineSeparator() + System.lineSeparator() + "Current board state:");
            System.out.println(board.toString());

        }


    }
}

