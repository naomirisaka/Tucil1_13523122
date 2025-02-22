import java.util.ArrayList;
import java.util.List;

class Piece {
    char letter;
    char[][] shape;

    // constructor
    public Piece(List<String> shapeLines, char letter) {
        this.letter = letter;
        int height = shapeLines.size();
        int width = 0;

        // piece width based on the longest line
        for (String line : shapeLines) {
            width = Math.max(width, line.length());
        }

        // matrix initialization for piece shape
        shape = new char[height][width];
        for (int i = 0; i < height; i++) {
            String line = shapeLines.get(i);
            for (int j = 0; j < width; j++) {
                if (j < line.length()) {
                    shape[i][j] = line.charAt(j);
                } else {
                    shape[i][j] = ' ';
                }
            }
        }
    }

    // gets all possible orientations of a piece
    public List<Piece> getAllOrientations() {
        List<Piece> orientations = new ArrayList<>();
        orientations.add(this);  // og orientation

        // rotations
        Piece rotated1 = rotatePiece();
        Piece rotated2 = rotated1.rotatePiece();
        Piece rotated3 = rotated2.rotatePiece();

        // flips
        Piece flipped = flipPiece();
        Piece flipped1 = flipped.rotatePiece();
        Piece flipped2 = flipped1.rotatePiece();
        Piece flipped3 = flipped2.rotatePiece();

        orientations.add(rotated1);
        orientations.add(rotated2);
        orientations.add(rotated3);
        orientations.add(flipped);
        orientations.add(flipped1);
        orientations.add(flipped2);
        orientations.add(flipped3);

        return orientations;
    }

    // rotates a piece 90 degrees clockwise
    private Piece rotatePiece() {
        int h = shape.length;
        int w = shape[0].length;
        char[][] rotated = new char[w][h];

        // piece adjustment after rotating
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                rotated[i][j] = ' ';
            }
        }

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (shape[i][j] != ' ') {
                    rotated[j][h - 1 - i] = shape[i][j];
                }
            }
        }

        return new Piece(charArrayToList(rotated), letter);
    }

    // flips a piece horizontally
    private Piece flipPiece() {
        int h = shape.length;
        int w = shape[0].length;
        char[][] flipped = new char[h][w];

        // piece adjustment after flipping
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                flipped[i][j] = shape[i][w - 1 - j];
            }
        }

        return new Piece(charArrayToList(flipped), letter);
    }

    // converts a char matrix to a list of strings
    private List<String> charArrayToList(char[][] arr) {
        List<String> list = new ArrayList<>();
        for (char[] row : arr) {
            list.add(new String(row));
        }
        return list;
    }

    // prints a piece
    public void printPiece() {
        for (char[] row : shape) {
            System.out.println(new String(row));
        }
        System.out.println();
    }
}
