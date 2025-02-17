import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// check if M, N, and P ada & valid, piece letter unique

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
    
    // hasnt worked yet
    public static void readInputFile(Scanner fileScanner) {
        int N = fileScanner.nextInt(); // rows
        int M = fileScanner.nextInt(); // columns
        int P = fileScanner.nextInt(); // num of pieces
        fileScanner.nextLine();

        String config_type = fileScanner.nextLine();

        // default config
        if (config_type.equalsIgnoreCase("default")) {
            Set<Character> uniquePieces = new HashSet<>();

            for (int i = 0; i < P; i++) {
                String piece = fileScanner.nextLine().trim();
                char pieceLetter = piece.charAt(0); 
                uniquePieces.add(pieceLetter);
            }

            System.out.println("Dimensi papan: " + N + "x" + M);
            System.out.println("Tipe konfigurasi: " + config_type);
            System.out.println("Solusi:");
    
            for (char pieceLetter : uniquePieces) {
                System.out.println("Piece " + pieceLetter);
            }
        }
    }
}