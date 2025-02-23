# Tugas Kecil 1 IF2211 Strategi Algoritma
### IQ Puzzler Pro Solver dengan Algoritma Brute Force
| Nama | NIM |
|------|-----|
| Naomi Risaka Sitorus | 13523122 |

## Deskripsi
IQ Puzzler Pro merupakan sebuah permainan papan yang dikembangkan oleh SmartGames dengan tujuan permainan mengisi penuh papan permainan dengan potongan puzzle yang tersedia. Setiap potongan puzzle dapat dirotasikan maupun dicerminkan untuk membantu penyusunan. Permainan dinyatakan selesai ketika papan terisi penuh dan semua potongan puzzle berhasil diletakkan pada papan. Program IQ Puzzler Pro Solver membantu mencari solusi permainan berdasarkan potongan puzzle yang diberikan dengan pendekatan brute force. 

## Struktur
```bash
│   README.md
│
├── bin
│   ├── Main.class
│   ├── Piece.class
│   └── PuzzleSolver.class
│
├── doc
│   └── Tucil1_K2_13523122_Naomi Risaka Sitorus.pdf
│
├── src
│   ├── Main.java
│   ├── Piece.java
│   └── PuzzleSolver.java
│
└── test
    ├── tc1.txt        ├── tc6.txt
    ├── tc1sol.txt     ├── tc6sol.txt
    ├── tc2.txt        ├── tc7.txt
    ├── tc2sol.txt     ├── tc7sol.txt
    ├── tc3.txt        ├── tc8.txt
    ├── tc3sol.txt     ├── tc9.txt
    ├── tc4.txt        ├── tc10.txt
    ├── tc4sol.txt     ├── tc10sol.png
    ├── tc5.txt        ├── tc11.txt
    ├── tc5sol.txt     └── tc11sol.png
```

## Cara Menjalankan
> [!NOTE]
> Untuk menjalankan program diperlukan IDE yang mendukung bahasa Java serta terinstalasi JDK 8 atau versi yang lebih baru.
1. Clone repository ini dengan menjalankan perintah di bawah ini pada terminal IDE:
   ```sh
   git clone https://github.com/naomirisaka/Tucil1_13523122.git
2. Buka folder hasil clone di IDE.
3. Pindah ke directory source code dengan:
   ```sh
   cd src
4. Jalankan program utama dengan:
   ```
   javac -d ../bin Main.java
   java -cp ../bin Main
   ```
   
## Cara Menggunakan
1. Setelah menjalankan program, masukkan nama file masukan permainan IQ Puzzler Pro beserta ekstensi .txt.
2. Tambahkan `../test/` di depan nama file apabila file disimpan di folder test.
3. Jika file masukan memiliki solusi, program akan menampilkan solusi tersebut di terminal. 
4. Masukkan pilihan Anda ketika program menawarkan untuk menyimpan solusi dalam bentuk file teks (.txt).
5. Masukkan pilihan Anda ketika program menawarkan untuk menyimpan solusi dalam bentuk file gambar (.png).
6. Jika Anda memilih untuk menyimpan file solusi, file akan disimpan di folder test.
7. Jika file masukan tidak memiliki solusi, program akan menampilkan pesan bahwa tidak ditemukan solusi dan program akan berakhir.
