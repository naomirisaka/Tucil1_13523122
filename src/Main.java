import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// check if M, N, P, and config type ada & valid
// validate piece letter unique (tidak ada huruf yang ngulang)
// kasi color on each piece

public class Main {
    public static void main(String[] args) {
        System.out.println("=================================================================================================================================================");
        System.out.println("\r\n" + 
        "   .___________    __________                    .__                 __________                  _________      .__                     \r\n" + 
        "   |   \\_____  \\   \\______   \\__ ________________|  |   ___________  \\______   _______  ____    /   _____/ ____ |  |___  __ ___________ \r\n" + 
        "   |   |/  / \\  \\   |     ___|  |  \\___   \\___   |  | _/ __ \\_  __ \\  |     ___\\_  __ \\/  _ \\   \\_____  \\ /  _ \\|  |\\  \\/ _/ __ \\_  __ \\\r\n" + 
        "   |   /   \\_/.  \\  |    |   |  |  //    / /    /|  |_\\  ___/|  | \\/  |    |    |  | \\(  <_> )  /        (  <_> |  |_\\   /\\  ___/|  | \\/\r\n" + 
        "   |___\\_____\\ \\_/  |____|   |____//_____ /_____ |____/\\___  |__|     |____|    |__|   \\____/  /_______  /\\____/|____/\\_/  \\___  |__|   \r\n" + 
        "            \\__>                       \\/     \\/         \\/                                          \\/                      \\/       \r\n" + 
        "");
        System.out.println("================================================================================================================================================");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama file puzzle: ");
        String filename = scanner.nextLine();

        while (!filename.endsWith(".txt")) {
            System.out.println("File harus berekstensi .txt");
            System.out.println();
            System.out.print("Masukkan nama file puzzle: ");
            filename = scanner.nextLine();
        }

        try {
            File file = new File(filename);
            try (Scanner fileScanner = new Scanner(file)) {
                //System.out.println("File '" + filename + "' berhasil dibaca.");
                readInputFile(fileScanner);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
        } finally {
            scanner.close();
        }
    }
    
    public static List<Piece> readInputFile(Scanner fileScanner) {
        int N = fileScanner.nextInt();
        int M = fileScanner.nextInt();
        int P = fileScanner.nextInt();
        fileScanner.nextLine(); 

        String config_type = fileScanner.nextLine(); // MASIH FOR DEFAULT ONLY
        Map<Character, List<String>> pieceShapes = new LinkedHashMap<>();

        while (fileScanner.hasNextLine()) {
            String pieceLine = fileScanner.nextLine().trim();
            if (pieceLine.length() == 0) continue; 

            char firstLetter = pieceLine.charAt(0);
            pieceShapes.putIfAbsent(firstLetter, new ArrayList<>());
            pieceShapes.get(firstLetter).add(pieceLine);
        }

        List<Piece> pieces = new ArrayList<>();
        for (Map.Entry<Character, List<String>> entry : pieceShapes.entrySet()) {
            char letter = entry.getKey();
            List<String> shapeLines = entry.getValue();
            pieces.add(new Piece(shapeLines, letter));
        }

        System.out.println("Dimensi papan: " + N + "x" + M);
        System.out.println("Tipe konfigurasi: " + config_type);
        System.out.println("Solusi:");
    
        return pieces;
    }
}

class Piece {
    char letter;
    char[][] shape;

    Piece(List<String> shapeLines, char letter) {
        this.letter = letter;
        int height = shapeLines.size();
        int width = 0;
        for (String line : shapeLines) {
            width = Math.max(width, line.length());
        }

        shape = new char[height][width];
        for (int i = 0; i < height; i++) {
            String line = shapeLines.get(i);
            for (int j = 0; j < width; j++) {
                shape[i][j] = (j < line.length()) ? line.charAt(j) : ' ';
            }
        }
    }
}