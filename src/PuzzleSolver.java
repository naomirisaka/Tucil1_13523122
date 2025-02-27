import java.util.*;

public class PuzzleSolver {
    // tries all possible configuration of the pieces
    public static long tryAllConfigurations(char[][] board, List<Piece> pieces, int N, int M, long triesAmt) {
        List<List<Piece>> allPermutations = generatePermutations(pieces);
        
        for (List<Piece> permutation : allPermutations) {
            triesAmt++; // incremented per iteration (permutation)
            clearBoard(board); // reset board for each iteration
            
            if (canPlaceAllPieces(board, permutation, N, M)) {
                return triesAmt; // all pieces are succesfully placed on the board
            }
        }
        return -triesAmt; // no solution found
    }
    
    // generates all possible permutations of the pieces
    private static List<List<Piece>> generatePermutations(List<Piece> pieces) {
        List<List<Piece>> permutations = new ArrayList<>();
        generatePermutationsHelper(pieces, 0, permutations);
        return permutations;
    }

    // helper function for generating permutations
    private static void generatePermutationsHelper(List<Piece> pieces, int index, List<List<Piece>> permutations) {
        if (index == pieces.size()) {
            permutations.add(new ArrayList<>(pieces));
            return;
        }

        for (int i = index; i < pieces.size(); i++) {
            swapPieces(pieces, index, i);
            generatePermutationsHelper(pieces, index + 1, permutations);
            swapPieces(pieces, index, i);
        }
    }

    // swaps two pieces in a list of pieces
    private static void swapPieces(List<Piece> pieces, int i, int j) {
        Piece temp = pieces.get(i);
        pieces.set(i, pieces.get(j));
        pieces.set(j, temp);
    }

    // resets the working board to empty
    private static void clearBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], ' ');
        }
    }

    // tries placing all pieces on the board
    private static boolean canPlaceAllPieces(char[][] board, List<Piece> pieces, int N, int M) {
        for (Piece piece : pieces) {
            boolean piecePlaced = false;
            List<Piece> allOrientations = piece.getAllOrientations();

            // tries all orientation of a piece
            for (Piece orientation : allOrientations) {
                // tries all position for placing a piece on the board 
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (canPlacePiece(board, orientation, i, j, N, M)) {
                            placePiece(board, orientation, i, j);
                            piecePlaced = true;
                            break;
                        }
                    }
                    if (piecePlaced) {
                        break;
                    }
                }
                if (piecePlaced) {
                    break;
                }
            }
            if (!piecePlaced) {
                return false;   // piece cannot be placed on the board
            }
        }
        return true; // all pieces are succesfully placed on the board
    }

    // checks if a piece can be placed on the board
    private static boolean canPlacePiece(char[][] board, Piece piece, int x, int y, int N, int M) {
        char[][] shape = piece.shape;
        int h = shape.length;
        int w = shape[0].length;

        // out of board bounds validation
        if (x + h > N || y + w > M){ 
            return false;
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (shape[i][j] != ' ' && board[x + i][y + j] != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // places a piece in the board
    private static void placePiece(char[][] board, Piece piece, int x, int y) {
        char[][] shape = piece.shape;
        int h = shape.length;
        int w = shape[0].length;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (shape[i][j] != ' ') {
                    board[x + i][y + j] = piece.letter;
                }
            }
        }
    }

    // checks whether the board is fully filled with pieces
    public static boolean isBoardFull(char[][] board) {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false; 
                }
            }
        }
        return true; 
    }
}