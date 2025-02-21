import java.util.*;

public class PuzzleSolver {
    static long triesAmt = 0;

    // places pieces on the board using pure brute force method
    public static boolean placePieces(List<Piece> pieces, int N, int M, Scanner scanner) {
        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], ' ');
        }

        long startTime = System.nanoTime();
        boolean solutionFound = tryAllConfigurations(board, pieces, N, M);
        long endTime = System.nanoTime();

        if (solutionFound) {
            Main.printBoard(board);
        } 

        long duration = (endTime - startTime) / 1000000; // duration in ms
        System.out.println("Jumlah kasus yang ditinjau: " + triesAmt);
        System.out.println("Waktu eksekusi: " + duration + " ms");
        
        if (solutionFound) {
            Main.saveSolution(board, scanner);
            Main.saveSolutionAsImage(board, scanner); 
        } 
        
        return solutionFound;
    }

    // tries all possible configuration of the pieces
    public static boolean tryAllConfigurations(char[][] board, List<Piece> pieces, int N, int M) {
        List<List<Piece>> allPermutations = generatePermutations(pieces);
        
        for (List<Piece> permutation : allPermutations) {
            triesAmt++; // incremented per iteration (permutation)
            clearBoard(board); // reset board for each iteration
            
            if (tryPlaceAllPieces(board, permutation, N, M)) {
                return true;
            }
        }
        
        return false;
    }
    
    // resets the working board to empty
    public static void clearBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], ' ');
        }
    }

    // generates all possible permutations of the pieces
    public static List<List<Piece>> generatePermutations(List<Piece> pieces) {
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

    // tries placing all pieces on the board
    public static boolean tryPlaceAllPieces(char[][] board, List<Piece> pieces, int N, int M) {
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
}