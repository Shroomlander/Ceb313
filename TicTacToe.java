import java.util.Scanner;

public class TicTacToe {
    private enum Cell { X, O, EMPTY }
    
    private Cell[][] board;
    private boolean player1Turn;
    
    public TicTacToe() {
        board = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Cell.EMPTY;
            }
        }
        player1Turn = true;
    }
    
    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        
        while (!gameOver) {
            printBoard();
            
            int row, col;
            if (player1Turn) {
                System.out.print("Player 1 (X) - Enter row and column (1-3): ");
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
            } else {
                System.out.print("Player 2 (O) - Enter row and column (1-3): ");
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
            }
            
            if (isValidMove(row, col)) {
                makeMove(row, col);
                if (checkWin()) {
                    gameOver = true;
                    System.out.println("Player " + (player1Turn ? "1 (X)" : "2 (O)") + " wins!");
                } else if (isDraw()) {
                    gameOver = true;
                    System.out.println("It's a draw!");
                } else {
                    player1Turn = !player1Turn;
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        
        scanner.close();
    }
    
    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == Cell.EMPTY;
    }
    
    private void makeMove(int row, int col) {
        board[row][col] = player1Turn ? Cell.X : Cell.O;
    }
    
    private boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != Cell.EMPTY && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return true;
            }
        }
        
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != Cell.EMPTY && board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
                return true;
            }
        }
        
        // Check diagonals
        if (board[0][0] != Cell.EMPTY && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return true;
        }
        if (board[0][2] != Cell.EMPTY && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return true;
        }
        
        return false;
    }
    
    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Cell.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }
}