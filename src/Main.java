import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;

public class Main {
    static int N, M; // board size (M x N)

    // ANSI color codes for CLI output
    private static Map<Character, String> pieceColors = new HashMap<>();
    private static final String[] ANSI_COLORS = {  
        "\u001B[31m",  // red
        "\u001B[32m",  // green
        "\u001B[33m",  // yellow
        "\u001B[34m",  // blue
        "\u001B[35m",  // magenta
        "\u001B[36m",  // cyan
        "\u001B[91m",  // bright red
        "\u001B[92m",  // bright green
        "\u001B[93m",  // bright yellow
        "\u001B[94m",  // bright blue
        "\u001B[95m",  // bright magenta
        "\u001B[96m",  // bright cyan
        "\u001B[103m",  // bright background yellow
        "\u001B[90m",  // dark gray
        "\u001B[97m",  // white
        "\u001B[30m",  // black
        "\u001B[41m",  // background red
        "\u001B[42m",  // background green
        "\u001B[43m",  // background yellow
        "\u001B[44m",  // background blue
        "\u001B[45m",  // background magenta
        "\u001B[46m",  // background cyan
        "\u001B[104m",  // bright background blue
        "\u001B[100m", // bright background black
        "\u001B[101m", // bright background red
        "\u001B[102m"  // bright background green
    };
    private static final String ANSI_RESET = "\u001B[0m";
    
    // RGB color codes for image output
    private static final Map<Character, Color> COLOR_MAP = new HashMap<>();
    static {                                           // closest colors to the ANSI ones
        COLOR_MAP.put('A', new Color(255, 0, 0));    // red
        COLOR_MAP.put('B', new Color(0, 255, 0));    // green
        COLOR_MAP.put('C', new Color(255, 255, 0));  // yellow
        COLOR_MAP.put('D', new Color(0, 0, 255));    // blue
        COLOR_MAP.put('E', new Color(255, 0, 255));  // magenta
        COLOR_MAP.put('F', new Color(0, 255, 255));  // cyan
        COLOR_MAP.put('G', new Color(250, 85, 85));  // bright red
        COLOR_MAP.put('H', new Color(85, 255, 85));  // bright green
        COLOR_MAP.put('I', new Color(255, 255, 85)); // bright yellow
        COLOR_MAP.put('J', new Color(85, 85, 255));  // bright blue
        COLOR_MAP.put('K', new Color(255, 85, 255)); // bright magenta
        COLOR_MAP.put('L', new Color(85, 255, 255)); // bright cyan
        COLOR_MAP.put('M', new Color(255, 255, 85)); // bright background yellow
        COLOR_MAP.put('N', new Color(85, 85, 85));   // dark gray
        COLOR_MAP.put('O', new Color(200, 200, 200));// white 
        COLOR_MAP.put('P', new Color(0, 0, 0));      // black
        COLOR_MAP.put('Q', new Color(255, 0, 0));    // background red
        COLOR_MAP.put('R', new Color(0, 255, 0));    // background green
        COLOR_MAP.put('S', new Color(255, 255, 0));  // background yellow
        COLOR_MAP.put('T', new Color(0, 0, 255));    // background blue
        COLOR_MAP.put('U', new Color(255, 0, 255));  // background magenta
        COLOR_MAP.put('V', new Color(0, 255, 255));  // background cyan
        COLOR_MAP.put('W', new Color(0, 0, 128));    // bright background blue
        COLOR_MAP.put('Q', new Color(85, 85, 85));   // bright background black
        COLOR_MAP.put('Y', new Color(255, 85, 85));  // bright background red
        COLOR_MAP.put('Z', new Color(85, 255, 85));  // bright background green
    }

    public static void main(String[] args) {
        System.out.println("=============================================================================================================================================");
        System.out.println("\r\n" +             
        "   .___________    __________                    .__                 __________                  _________      .__                     \r\n" + 
        "   |   \\_____  \\   \\______   \\__ ________________|  |   ___________  \\______   _______  ____    /   _____/ ____ |  |___  __ ___________ \r\n" + 
        "   |   |/  / \\  \\   |     ___|  |  \\___   \\___   |  | _/ __ \\_  __ \\  |     ___\\_  __ \\/  _ \\   \\_____  \\ /  _ \\|  |\\  \\/ _/ __ \\_  __ \\\r\n" + 
        "   |   /   \\_/.  \\  |    |   |  |  //    / /    /|  |_\\  ___/|  | \\/  |    |    |  | \\(  <_> )  /        (  <_> |  |_\\   /\\  ___/|  | \\/\r\n" + 
        "   |___\\_____\\ \\_/  |____|   |____//_____ /_____ |____/\\___  |__|     |____|    |__|   \\____/  /_______  /\\____/|____/\\_/  \\___  |__|   \r\n" + 
        "            \\__>                       \\/     \\/         \\/                                          \\/                      \\/       \r\n" + 
        "");
        System.out.println("=============================================================================================================================================");
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Selamat datang di program IQ Puzzle Pro Solver!");
        System.out.println("Program ini akan membantu Anda menyelesaikan IQ Pro Puzzle.");
        System.out.println();
        System.out.print("Masukkan nama file puzzle yang ingin diselesaikan: ");
        String inputFileName = scanner.nextLine();

        // empty file name validation
        while (inputFileName.isEmpty()) { 
            System.out.println("Nama file tidak boleh kosong");
            System.out.println();
            System.out.print("Masukkan nama file puzzle yang ingin diselesaikan: ");
            inputFileName = scanner.nextLine();
        }

        // file extension validation
        while (!inputFileName.endsWith(".txt")) { 
            System.out.println("File harus berekstensi .txt");
            System.out.println();
            System.out.print("Masukkan nama file puzzle yang ingin diselesaikan: ");
            inputFileName = scanner.nextLine();
        }

        try {
            File file = new File(inputFileName);
            Scanner fileScanner = new Scanner(file);
            try {
                List<Piece> pieces = readInputFile(fileScanner);    
                assignColors(pieces);
                PuzzleSolver.placePieces(pieces, N, M, scanner);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                return; 
            }
            fileScanner.close();
        
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
        }
        
        scanner.close();
    }

    // reads input file and validate its contents
    private static List<Piece> readInputFile(Scanner fileScanner) throws Exception {
        // N (number of rows) validation 
        if (!fileScanner.hasNextInt()) {  
            throw new Exception("File tidak memiliki nilai N.");
        } 
        N = fileScanner.nextInt();
        if (N <= 0) {
            throw new Exception("N harus bernilai lebih besar dari 0.");
        }
        
        // M (number of cols) validation
        if (!fileScanner.hasNextInt()) {
            throw new Exception("File tidak memiliki nilai M.");
        }
        M = fileScanner.nextInt();
        if (M <= 0) {
            throw new Exception("M harus bernilai lebih besar dari 0.");
        }

        // P (number of pieces) validation
        if (!fileScanner.hasNextInt()) {
            throw new Exception("File tidak memiliki nilai P.");
        }
        int P = fileScanner.nextInt();  // number of pieces
        if (P <= 0) {
            throw new Exception("P harus bernilai lebih besar dari 0.");
        }

        // configuration type validation 
        fileScanner.nextLine(); 
        if (!fileScanner.hasNextLine()) {
            throw new Exception("File tidak memiliki tipe konfigurasi.");
        }
        String configType = fileScanner.nextLine().trim(); // configuration type
        if (configType.isEmpty()) {
            throw new Exception("Tipe konfigurasi tidak boleh kosong.");
        }
        if (!configType.equalsIgnoreCase("default")) {
            throw new Exception("Tipe konfigurasi yang tersedia adalah 'default'.");
        }

        // pieces reading and validation
        Map<Character, List<String>> pieceShapes = new LinkedHashMap<>();
        Set<Character> usedLetters = new HashSet<>(); 
        Character currPiece = null;

        while (fileScanner.hasNextLine()) {
            String pieceLine = fileScanner.nextLine(); // Keep leading spaces
            if (pieceLine.trim().isEmpty()) { 
                currPiece = null; 
                continue;
            }

            char firstLetter = Character.toUpperCase(pieceLine.trim().charAt(0));  // set all letters to uppercase
            for (char c : pieceLine.trim().toCharArray()) {
                // alphabet validation 
                if (!Character.isLetter(c) && c != ' ') { 
                    throw new Exception("Terdapat baris yang mengandung karakter bukan alphabet.");
                }
                if (c != firstLetter && c != ' ') {
                    throw new Exception("Terdapat baris yang mengandung lebih dari satu huruf.");
                }
            }

            // unique letter validation
            if (currPiece != null && firstLetter != currPiece && usedLetters.contains(firstLetter)) {
                throw new Exception("Huruf '" + firstLetter + "' digunakan oleh lebih dari satu piece yang terpisah.");
            }

            usedLetters.add(firstLetter); 
            pieceShapes.putIfAbsent(firstLetter, new ArrayList<>());
            pieceShapes.get(firstLetter).add(pieceLine); // Add the whole line including spaces
            currPiece = firstLetter; 
        }

        // P validation according to the number of pieces read
        if (pieceShapes.size() != P) {
            throw new Exception("Jumlah piece tidak sesuai dengan nilai P.");
        }

        List<Piece> pieces = new ArrayList<>();
        for (Map.Entry<Character, List<String>> entry : pieceShapes.entrySet()) {
            pieces.add(new Piece(entry.getValue(), entry.getKey()));
        }

        return pieces;
    }
   
    // assigns colors to pieces based on their letters
    private static void assignColors(List<Piece> pieces) {
        for (Piece piece : pieces) {
            char letter = piece.letter;
            if (!pieceColors.containsKey(letter)) {
                int index = letter - 'A'; // letter to index conversion for color assignment
                if (index >= 0 && index < ANSI_COLORS.length) {
                    pieceColors.put(letter, ANSI_COLORS[index]); 
                } else {
                    pieceColors.put(letter, ANSI_RESET);
                }
            }
        }
    }
    
    // displays solution board in CLI 
    public static void printBoard(char[][] board) {
        System.out.println();
        System.out.println("Solusi ditemukan.");
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    System.out.print(cell);
                } else {
                    System.out.print(pieceColors.get(cell) + cell + ANSI_RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // saves solution board as a text file
    public static void saveSolution(char[][] board, Scanner scanner) {
        System.out.println();
        System.out.print("Apakah Anda ingin menyimpan solusi dalam bentuk teks? (ya/tidak) ");
        String saveOpt = scanner.nextLine();

        // save option validation
        while (!(saveOpt.equalsIgnoreCase("ya") || saveOpt.equalsIgnoreCase("tidak") || saveOpt.isEmpty())) {
            System.out.println();
            System.out.print("Apakah Anda ingin menyimpan solusi dalam bentuk teks? (ya/tidak) ");
            saveOpt = scanner.nextLine();
        }

        if (saveOpt.equalsIgnoreCase("ya")){
            System.out.print("Masukkan nama file output: ");
            String outputFileName = scanner.nextLine();

            File directory = new File("../test");
            if (!directory.exists()) {
                directory.mkdirs();  
            }

            // empty file name validation
            while (outputFileName.isEmpty()) {
                System.out.println("Nama file tidak boleh kosong");
                System.out.println();
                System.out.print("Masukkan nama file output: ");
                outputFileName = scanner.nextLine();
            }

            // file extension validation
            while (!outputFileName.endsWith(".txt")) {
                System.out.println("File harus berekstensi .txt");
                System.out.println();
                System.out.print("Masukkan nama file output: ");
                outputFileName = scanner.nextLine();
            }
            
            outputFileName = "../test/" + outputFileName;
            
            // writes solution to a text file
            try (PrintWriter writer = new PrintWriter(new File(outputFileName))) {
                for (int i = 0; i < board.length; i++) {
                    writer.print(new String(board[i])); 
                    if (i < board.length - 1) {
                        writer.println(); 
                    }
                }
                System.out.println("Solusi disimpan ke " + outputFileName);
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } 
    }

    // saves solution board as an image file
    public static void saveSolutionAsImage(char[][] board, Scanner scanner) {
        System.out.println();
        System.out.print("Apakah Anda ingin menyimpan solusi dalam bentuk gambar? (ya/tidak) ");
        String saveOpt = scanner.nextLine();
        
        // save option validation
        while (!(saveOpt.equalsIgnoreCase("ya") || saveOpt.equalsIgnoreCase("tidak") || saveOpt.isEmpty())) {
            System.out.println();
            System.out.print("Apakah Anda ingin menyimpan solusi dalam bentuk gambar? (ya/tidak) ");
            saveOpt = scanner.nextLine();
        }

        if (saveOpt.equalsIgnoreCase("ya")) {
            System.out.print("Masukkan nama file output gambar: ");
            String outputFileName = scanner.nextLine();

            // empty file name validation
            while (outputFileName.isEmpty()) {
                System.out.println("Nama file tidak boleh kosong");
                System.out.println();
                System.out.print("Masukkan nama file output gambar: ");
                outputFileName = scanner.nextLine();
            }

            // file extension validation
            while (!outputFileName.endsWith(".png")) {
                System.out.println("File harus berekstensi .png");
                System.out.println();
                System.out.print("Masukkan nama file output gambar: ");
                outputFileName = scanner.nextLine();
            }

            outputFileName = "../test/" + outputFileName;

            // creates image file for the solution board
            int cellSize = 50; 
            int width = board[0].length * cellSize;
            int height = board.length * cellSize;

            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();

            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setFont(new Font("Arial", Font.BOLD, cellSize / 2));
            FontMetrics fm = graphics.getFontMetrics();

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    char cell = board[i][j];
                    // cell background color
                    if (cell != ' ') {
                        graphics.setColor(COLOR_MAP.getOrDefault(cell, Color.BLACK));
                        graphics.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    }

                    // cell border 
                    graphics.setColor(Color.BLACK);
                    graphics.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);

                    // cell text (piece letter)
                    if (cell != ' ') {
                        String text = String.valueOf(cell);
                        int textWidth = fm.stringWidth(text);
                        int textHeight = fm.getAscent();

                        // text color based on cell background color
                        if (graphics.getColor().getRed() + graphics.getColor().getGreen() + graphics.getColor().getBlue() < 382) {
                            graphics.setColor(Color.WHITE);
                        } else {
                            graphics.setColor(Color.BLACK);
                        }

                        // text positioning
                        int textX = j * cellSize + (cellSize - textWidth) / 2;
                        int textY = i * cellSize + (cellSize + textHeight) / 2 - 5;
                        graphics.drawString(text, textX, textY);
                    }
                }
            }
            graphics.dispose();

            try {
                File directory = new File("../test");
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                ImageIO.write(image, "png", new File(outputFileName));
                System.out.println("Gambar solusi disimpan ke " + outputFileName);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}