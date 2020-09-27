package L26GameOfLife;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to GAME OF LIFE" + System.lineSeparator());
        System.out.println("Choose size of the board: ");
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        Board board = new Board(size);

        BoardGame game1 = new BoardGame(board);
        game1.play();
    }
}

