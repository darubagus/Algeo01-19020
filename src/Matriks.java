
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Matriks {
     /* ***** ATRIBUTE ***** */
     public int maxNBrsKol = 1; //panjang maksimum baris dan kolom matriks
     public float [][] Matriks = new float[maxNBrsKol][maxNBrsKol]; //inisialisasi matriks 1x1
     public int NBrsEff; /* banyaknya/ukuran baris yg terdefinisi */
	public int NKolEff; /* banyaknya/ukuran kolom yg terdefinisi */

     /* Matriks Yang terdefinisi memiliki indeks dari [0..NBrsEff-1][0..NKolEff-1] */

     /* ***** METHODS ***** */

     /* *** Konstruktor membentuk MATRIKS *** */
     Matriks(int NBrsEff, int NKolEff) {
          /* KAMUS */
          int i, j;

          /* ALGORITMA */
          // Ubah ukuran matriks
          while (NBrsEff > this.maxNBrsKol || NKolEff > this.maxNBrsKol) {
               this.doubleMatriks();
          }

          // Inisialisasi isi matriks
          this.NBrsEff = NBrsEff;
          this.NKolEff = NKolEff;

          for (i = 0; i < NBrsEff; i++) {
               for (j = 0; j < NKolEff; j++) {
                    this.Matriks[i][j] = 0;
               }
          }

     }

     /* *** KELOMPOK BACA/TULIS *** */
     /**
      * Baca Matriks I.S. Membaca matriks pada layar F.S. Matriks terdefinisi nilai
      * elemen efektifnya, berukuran NB x NK
      */
     void bacaMatriks() {
          int i, j;
          Scanner input = null; 
          
          try {
               input = new Scanner(System.in);
               System.out.print("Masukkan NBrs : ");
               int NBrsEff = input.nextInt();
               System.out.print("Masukkan NKol : ");
               int NKolEff = input.nextInt();

               while (NBrsEff > this.maxNBrsKol || NKolEff > this.maxNBrsKol) {
                    this.doubleMatriks();
               }
     
               this.NBrsEff = NBrsEff;
               this.NKolEff = NKolEff;

               System.out.println("Masukkan elemen matriks :");
               for (i = 0; i < this.NBrsEff; i++) {
                    for (j = 0; j < this.NKolEff; j++) {
                         this.Matriks[i][j] = input.nextFloat();
                    }
               }

          }
          catch (Exception e) {
               System.err.println("Error di bacaMatriks");
          }
          
     }

     /** Baca Matriks dari File Txt
      * I.S. File txt berisi Array Matriks
      * F.S. Terbaca Matriks dan disimpan dalam variabel */
     void bacaFileMatriks(String namaFile)
     {
          try {
          /* KAMUS */
          String namaFileDir = "./data/" + namaFile;
          File file = new File(namaFileDir);
          int NBrs = 0;
          int NKol = 0;
          int i,j; // Indeks

          Scanner matriks = new Scanner(file); // Digunakan untuk memindahkan matriks

          /* ALGORITMA */
          
          // Menghitung banyaknya baris matriks
          while (matriks.hasNextLine()) {
               NBrs++;
               matriks.nextLine();
          }
          matriks.close();

          matriks  = new Scanner(file);
          Scanner line = new Scanner(matriks.nextLine());
          // Menghitung banyaknya kolom baris matriks
          while (line.hasNextFloat()) {
               NKol++;
               line.nextFloat();
          }
          line.close();
          matriks.close();
          
          matriks  = new Scanner(file);
          
          // Cek apakah ukuran muat
          while (NBrs > this.maxNBrsKol || NKol > this.maxNBrsKol) {
               this.doubleMatriks();
          }

          this.NBrsEff = NBrs;
          this.NKolEff = NKol;

          // Isi Matriks
          for (i = 0; i < NBrs; i++) {
               for (j = 0; j < NKol; j++) {
                    this.Matriks[i][j] = matriks.nextFloat();
               }
          }
          matriks.close();

          } catch (FileNotFoundException e) {
               System.err.printf("Error: File \"%s\" tidak ditemukan\n",namaFile);
          }
     }

     /**
      * Tulis Matriks 
      * I.S. Matriks terdefinisi dan memiliki nilai 
      * F.S. Menampilkan matriks pada layar
      */
     void tulisMatriks() {
          // mencetak elemen-elemen matriks hingga indeks M,N
          int i, j;

          for (i = 0; i < this.NBrsEff; i++) {
               for (j = 0; j < this.NKolEff; j++) {
                    System.out.print(this.Matriks[i][j] + " ");
               }
               System.out.println();
          }
     }

     /**
      * Tulis Matriks 
      * I.S. Matriks terdefinisi dan memiliki nilai 
      * F.S. Menyimpan matriks pada suatu file
      */
     void tulisFileMatriks(String namaFile) {
          int i,j;
          String line;
          try {
               String namaFileDir = "./hasil/" + namaFile;
               FileWriter writeMatriks = new FileWriter(namaFileDir);

               for (i = this.GetFirstIdxBrs(); i <= this.GetLastIdxBrs(); i++) {
                    line = "";
                    for (j = this.GetFirstIdxKol(); j <= this.GetLastIdxKol(); j++) {
                         line += Float.toString(this.GetElmt(i, j));

                         if (j!=this.GetLastIdxKol()) {
                              line += " ";
                         }
                    }
                    if (i!=this.GetLastIdxBrs()) {
                         line += "\n";
                    }
                    writeMatriks.write(line); 
               }
               writeMatriks.close();
               System.out.println("Berhasil menyimpan matriks pada folder hasil, file \"" + namaFile + "\".");
             } catch (IOException e) {
               System.err.println("Terjadi error.");
               e.printStackTrace();
             }
     }

     /** SELEKTOR DAN GETTER **/

     /* Mengakses Elemen Matriks */
     float GetElmt(final int i, final int j) {
          return this.Matriks[i][j];
     }

     /* Set element matriks[i][j] dengan val */
     void SetElmt(final int i, final int j, final float value) {
          this.Matriks[i][j] = value;
     }

     /* Mengembalikan indeks baris pertama */
     int GetFirstIdxBrs() {
          return 0;
     }

     /* Mengembalikan indeks kolom pertama */
     int GetFirstIdxKol() {
          return 0;
     }

     /* Mengembalikan indeks baris terakhir */
     int GetLastIdxBrs(){
          return this.GetFirstIdxBrs()+this.NBrsEff-1;
     }

     /* Mengembalikan indeks kolom terakhir */
     int GetLastIdxKol(){
          return this.GetFirstIdxKol()+this.NKolEff-1;
     }

     /* Mengembalikan elemen diagonal! */
     float GetDiagonal(final int i) {
          return this.GetElmt(i,i);
     }

     int NBElmt() {
          return this.NBrsEff * this.NKolEff;
     }

     /* *** MEMORY MANAGEMENT *** */

     /**
      * Mengubah ukuran maksimum Baris dan Kolom Matriks menjadi 2x semula I.S.
      * Matriks Terdefinisi F.S. Panjang maksimum Baris dan Kolom Matriks menjadi 2x
      * semula Ukuran memory matriks menjadi 4x semula
      **/
     void doubleMatriks() {
          /* KAMUS */
          this.maxNBrsKol = this.maxNBrsKol * 2;
          float[][] NewMatriks = new float[this.maxNBrsKol][this.maxNBrsKol];

          /* ALGORITMA */
          NewMatriks = this.copyArrayMatriks();
          this.Matriks = NewMatriks;
     }

     /**
      * Mengubah ukuran maksimum Baris dan Kolom Matriks menjadi 2x semula I.S.
      * Matriks Terdefinisi dan panjang baris dan kolom maksumim 2x NBrsEff dan NKolEff
      * F.S. Panjang maksimum Baris dan Kolom Matriks menjadi 1/2x semula Ukuran
      * memory matriks menjadi 1/4x semula
      **/
     void halfMatriks() {
          if ((this.NBrsEff <= this.maxNBrsKol / 2) && (this.NKolEff <= this.maxNBrsKol / 2)) {
               /* KAMUS */
               this.maxNBrsKol = this.maxNBrsKol * 1 / 2;
               float[][] NewMatriks = new float[this.maxNBrsKol][this.maxNBrsKol];

               /* ALGORITMA */
               NewMatriks = this.copyArrayMatriks();
               this.Matriks = NewMatriks;
          } else {
               System.err.println("Matriks tidak bisa diperkecil");
          }
     }

     /** Mengcopy array matriks ke tempat lain */
     float[][] copyArrayMatriks() {
          /* KAMUS */

          final float[][] MHsl = new float[this.maxNBrsKol][this.maxNBrsKol];

          /* ALGORITMA */
          
          for (int i = 0; i < this.NBrsEff; i++) {
               for (int j = 0; j < this.NKolEff; j++) {
                    MHsl[i][j] = this.Matriks[i][j];
               }
          }
          return MHsl;
     }

     /** Mencopy matriks ke matriks lain */
     Matriks copyMatriks() {
          /* KAMUS */
          final Matriks MCopy = new Matriks(this.NBrsEff, this.NKolEff);
          /* ALGORITMA */
          MCopy.Matriks = this.copyArrayMatriks();
          MCopy.maxNBrsKol = this.maxNBrsKol;
          MCopy.NBrsEff = this.NBrsEff;
          MCopy.NKolEff = this.NKolEff;

          return MCopy;
     }

     /* *** KELOMPOK OPERASI PRIMITIF *** */

     void transpose () {
          //Membuat matriks transpose
          int i,j;
          int temp;
          Matriks MTemp;
          MTemp = new Matriks(this.NKolEff,this.NBrsEff);

          
          //Proses transpose
          for (i=this.GetFirstIdxBrs(); i<=this.GetLastIdxBrs(); i++){
               for (j=this.GetFirstIdxKol(); j<=this.GetLastIdxKol(); j++){
                    MTemp.SetElmt(j, i, this.GetElmt(i, j));
               }
          }
          
          temp = this.NBrsEff;
          this.NBrsEff = this.NKolEff;
          this.NKolEff = temp; 

          // mengembalikan isi Matriks asal
          for (i=this.GetFirstIdxBrs(); i<=this.GetLastIdxBrs(); i++){
               for (j=this.GetFirstIdxKol(); j<=this.GetLastIdxKol(); j++){
                    this.SetElmt(i, j, MTemp.GetElmt(i, j));
               }
          }
          
     }

     Matriks KaliMatriks(Matriks M1, Matriks M2){
          /* Prekondisi : Ukuran kolom efektif M1 = ukuran baris efektif M2 */
          /* Mengirim hasil perkalian matriks: salinan M1 * M2 */
          Matriks MRes = new Matriks(M1.NBrsEff, M2.NKolEff);
          float result;

          for (int i = MRes.GetFirstIdxBrs(); i <= MRes.GetLastIdxBrs(); i++){
               for (int j = MRes.GetFirstIdxKol(); j<=MRes.GetLastIdxKol(); j++){
                    
                    result = 0;
                    
                    for (int k = M1.GetFirstIdxKol(); k<=M1.GetLastIdxKol(); k++){
                         result += M1.GetElmt(i, k) * M2.GetElmt(k, j);
                    }
                    MRes.SetElmt(i, j, result);
               }
          }

          return MRes;
     }

        
     /*       KELOMPOK OPERASI OBE          */
     void PlusRow(final int origin, final int target, final float koef) {
     /*Melakukan operasi Rasal+(koef)*Rakhir */
          int j;

          for (j=this.GetFirstIdxKol(); j<=this.GetLastIdxKol(); j++) {
               this.SetElmt(target, j, (this.GetElmt(target, j)+(koef*this.Matriks[origin][j])));
          }
     }

     void SwapRow(final int origin, final int target) {
          /* Melakukan operasi pertukaran baris */
          int j;
          float temp;

          for (j=this.GetFirstIdxKol(); j<=this.GetLastIdxKol(); j++) {
               // elemen 
               temp = this.GetElmt(origin, j);
               this.SetElmt(origin, j, this.GetElmt(target, j));
               this.SetElmt(target, j, temp);
          }
     }

     void MakeOne(final int i, final float koef) {
          /* Membagi baris i dengan konstanta koef untuk membuat 1 utama */
          int j;
          for (j=this.GetFirstIdxKol(); j<=this.GetLastIdxKol(); j++){
               this.SetElmt(i, j, (this.GetElmt(i, j)/koef));
          }
     }

     /*       KELOMPOK ELIMINASI GAUSS DAN GAUSS-JORDAN         */
     void GaussElimination(){
          /* I.S Terdefinisi Augmented Matriks M */
          /* F.S Matriks M adalah sebuah matriks eselon baris */
          /*   1 2 3
               4 5 6     
               7 8 9     */
          int i = this.GetFirstIdxBrs();
          int j;
          int k;    // variable yang digunakan untuk mengecek baris setelahnya
          float koef;
          boolean flag;
          int indikatorDet = 1;

          // perulangan dari baris pertama-terakhir dan kolom pertama-sebelum terakhir karena merupakan matriks augmented
          for (j = this.GetFirstIdxKol(); ((i<=this.GetLastIdxBrs()) && (j < this.GetLastIdxKol())); j++){
               
               boolean NextProcess = true;        //indikator untuk lanjut ke proses berikutnya
               
               if (this.GetElmt(i, j) == 0){

                    k = i+1;
                    flag = false;
                    while (!flag && k <= this.GetLastIdxBrs()){
                         //lakukan perulangan sampai ditemukan elemen kolom j yang != 0
                         if (this.GetElmt(k, j)!=0){
                              flag = true;
                         } 
                         else {
                              k+=1;
                         }
                    }

                    //ketika ditemukan elemen != 0 di baris k, maka dilakukan pertukaran
                    if (flag){
                         this.SwapRow(i, k);
                         indikatorDet *= -1;
                    } 
                    else {
                         NextProcess = false;
                    }
               }

               if (NextProcess){
                    // proses pembuatan segitiga atas
                    this.MakeOne(i, this.GetElmt(i, j));
                    for (k=i+1; k <= this.GetLastIdxBrs(); k++){
                         koef = -(this.GetElmt(k, j) / this.GetElmt(i,j));
                         this.PlusRow(i,k, koef);
                    }
                    i+=1;
               }  
          }

     }

     /* I.S Terdefinisi Augmented Matriks M */
     /* F.S Matriks M adalah Matriks eselon baris tereduksi */
     void GJordanElimination() {
          /* I.S Terdefinisi Augmented Matriks M */
          /* F.S Matriks M adalah sebuah matriks eselon baris */
          /*   1 2 3
               4 5 6     
               7 8 9     */
          int i = this.GetFirstIdxBrs();
          int j;
          int k;    // variable yang digunakan untuk mengecek baris setelahnya
          float koef;
          boolean flag;
          // int indikatorDet = 1;

          // perulangan dari baris pertama-terakhir dan kolom pertama-sebelum terakhir karena merupakan matriks augmented
          for (j = this.GetFirstIdxKol(); ((i<=this.GetLastIdxBrs()) && (j < this.GetLastIdxKol())); j++){
               
               boolean NextProcess = true;        //indikator untuk lanjut ke proses berikutnya
               
               if (this.GetElmt(i, j) == 0){

                    k = i+1;
                    flag = false;
                    while (!flag && k <= this.GetLastIdxBrs()){
                         //lakukan perulangan sampai ditemukan elemen kolom j yang != 0
                         if (this.GetElmt(k, j)!=0){
                              flag = true;
                         } 
                         else {
                              k+=1;
                         }
                    }

                    //ketika ditemukan elemen != 0 di baris k, maka dilakukan pertukaran
                    if (flag){
                         this.SwapRow(i, k);
                         // indikatorDet *= -1;
                    } 
                    else {
                         NextProcess = false;
                    }
               }

               if (NextProcess){
                    // proses pembuatan segitiga atas bawah
                    this.MakeOne(i, this.GetElmt(i, j));
                    for (k=this.GetFirstIdxBrs(); k <= this.GetLastIdxBrs(); k++){
                         if (k!=i) {
                              koef = -(this.GetElmt(k, j) / this.GetElmt(i,j));
                              this.PlusRow(i,k, koef);
                         }
                         
                    }
                    i+=1;
               }  
          }

     }

     /* *** METODE DETERMINAN *** */
     float DeterminanKofaktor()
     /* Prekondisi: IsBujursangkar()
     Menghitung nilai determinan sebuah matriks */
     // Menggunakan metode Ekspansi Kofaktor
     {
          //KAMUS LOKAL
          Matriks MHasil;
          int j; //kolom matriks awal (digunakan baris 0 untuk menentukan determinan)
          int k,l; //indeks matriks minor
          int m,n; //indeks matriks awal yang akan di-assign ke elemen matriks minor
          float det;

          //ALGORITMA
          det = 0;
          
          if (this.NBElmt() == 1) { //basis
               det = this.GetElmt(0,0);
          }
          else { //rekurens
               for (j = this.GetFirstIdxKol(); j <= this.GetLastIdxKol(); j++) {
                    MHasil = new Matriks(this.NBrsEff-1, this.NKolEff-1);

                    //REDUKSI MATRIKS AWAL
                    m=1; //inisiasi indeks baris matriks awal yg akan diassign ke matriks minor
                    for (k = MHasil.GetFirstIdxBrs(); k <= MHasil.GetLastIdxBrs(); k++) {
                         n=0; //inisialisasi indeks kolom matriks awal yang akan diassign ke matriks minor
                         for (l = MHasil.GetFirstIdxKol(); l <= MHasil.GetLastIdxKol(); l++) {
                              if (l == j) {
                                   n = n+1;  //hal ini dilakukan agar kolom yang digunakan antara matriks utama dg
                                             //matriks yang diassign ke matriks minor tidak sama
                              }
                              //MHasil.GetElmt(k,l) = this.GetElmt(m,n);
                              MHasil.SetElmt(k, l, this.GetElmt(m, n));
                              n = n+1;
                         }
                         m = m+1;
                    }
                    if (j%2 == 0) {
                         det = det + this.GetElmt(0,j) * MHasil.DeterminanKofaktor();
                    }
                    else {
                         det = det + (-1 * this.GetElmt(0,j) * MHasil.DeterminanKofaktor());
                    }
               }
          }
          return det;
     }

     float DeterminanGauss(){
          /* I.S : IsBujurSangkar */
          /* F.S : Fungsi melakukan return nilai dari Determinan matriks M */
          int i = this.GetFirstIdxBrs();
          int j;
          int k;    // variable yang digunakan untuk mengecek baris setelahnya
          float koef;
          boolean flag;
          float indikatorDet = 1;
          Matriks Det = new Matriks(0,0);
          Det = this.copyMatriks();

          // perulangan dari baris pertama-terakhir dan kolom pertama-sebelum terakhir
          for (j = Det.GetFirstIdxKol(); ((i<=Det.GetLastIdxBrs()) && (j < Det.GetLastIdxKol())); j++){
               
               boolean NextProcess = true;        //indikator untuk lanjut ke proses berikutnya
               
               if (Det.GetElmt(i, j) == 0){

                    k = i+1;
                    flag = false;
                    while (!flag && k <= Det.GetLastIdxBrs()){
                         //lakukan perulangan sampai ditemukan elemen kolom j yang != 0
                         if (Det.GetElmt(k, j)!=0){
                              flag = true;
                         } 
                         else {
                              k+=1;
                         }
                    }

                    //ketika ditemukan elemen != 0 di baris k, maka dilakukan pertukaran
                    if (flag){
                         Det.SwapRow(i, k);
                         indikatorDet *= (-1);
                    } 
                    else {
                         NextProcess = false;
                    }
               }

               if (NextProcess){
                    // proses pembuatan segitiga atas
                    indikatorDet *= (Det.GetElmt(i, j));
                    Det.MakeOne(i, Det.GetElmt(i, j));
                    for (k=i+1; k <= Det.GetLastIdxBrs(); k++){
                         koef = -(Det.GetElmt(k, j) / Det.GetElmt(i,j));
                         Det.PlusRow(i,k, koef);
                    }
                    i+=1;
               }  
          }

          float Determinan = Det.GetElmt(Det.GetFirstIdxBrs(), Det.GetFirstIdxKol());

          for (int o = Det.GetFirstIdxBrs()+1 ; o <= Det.GetLastIdxBrs(); o++){
               Determinan *= Det.GetElmt(o, o);
          }

          return (Determinan*indikatorDet);
     }

     // ***** KELOMPOK MATRIKS INVERS *****//
     Matriks Kofaktor(){
     //I.S. Matriks terdefinisi, Matriks berbentuk bujursangkar
     //F.S. Terbentuk matriks kofaktor
          int i,j; //indeks matriks awal
          int k,l; //indeks matriks minor
          int m,n; //indeks matriks awal yang akan memasuki matriks minor
          Matriks MKofaktor;
          Matriks MMinor;
          MKofaktor = new Matriks(this.NBrsEff, this.NKolEff);
          MMinor = new Matriks(this.NBrsEff-1, this.NKolEff-1);

          for (i = this.GetFirstIdxBrs(); i<=this.GetLastIdxBrs(); i++) {
               for (j = this.GetFirstIdxKol(); j<= this.GetLastIdxKol(); j++) {
                    m = 0;
                    for (k = MMinor.GetFirstIdxBrs(); k<= MMinor.GetLastIdxBrs(); k++) {
                         n = 0;
                         if (m == i) {
                              m+=1;
                         }
                         for (l = MMinor.GetFirstIdxKol(); l <= MMinor.GetLastIdxKol(); l++) {
                              if (n == j) {
                                   n+=1;
                              } 
                              MMinor.SetElmt(k, l, this.GetElmt(m, n));
                              n+=1;
                         }
                         m+=1;
                    }
                    MKofaktor.SetElmt(i,j, MMinor.DeterminanKofaktor());
                    if ((i+j)%2 == 1 ) {
                         MKofaktor.SetElmt(i,j, -1*MKofaktor.GetElmt(i,j));
                    }
               }
          }
          return MKofaktor;
     }

     Matriks Adjoin()
     // I.S. Matriks Kofaktor tersedia
     // F.S. Matriks kofaktor telah di-transpose
     {
          Matriks MAdjoin;
          int i,j;
          MAdjoin = new Matriks(this.NBrsEff,this.NKolEff);
          for (i = this.GetFirstIdxBrs(); i <= this.GetLastIdxBrs(); i++) {
               for (j = this.GetFirstIdxKol(); j <= this.GetLastIdxKol(); j++) {
                    MAdjoin.SetElmt(i,j, this.GetElmt(j,i));
               }
          }
          return MAdjoin;
     }


     /*        KUMPULAN OPERASI INVERSE       */
     Matriks InversKofaktor()
     // F.S Terbentuk sebuah matriks invers dengan metode ekspansi kofaktor serta adjoin
     {
          Matriks MInvers;
          int i,j;
          float det;

          det = this.DeterminanKofaktor();

          if (det == 0) {
               MInvers = new Matriks(this.NBrsEff, this.NKolEff);
               System.out.println("Matriks tidak memiliki matriks balikan karena nilai determinannya = 0.");
          }

     
          else {
               MInvers = new Matriks(this.NBrsEff, this.NKolEff);
               MInvers = this.Kofaktor();
               MInvers = MInvers.Adjoin();
               for (i = this.GetFirstIdxBrs(); i<=this.GetLastIdxBrs(); i++) {
                    for (j = this.GetFirstIdxKol(); j <= this.GetLastIdxKol(); j++) {
                         MInvers.SetElmt(i,j, MInvers.GetElmt(i,j)/det);
                    }
               }
          }
          return MInvers;
     }

     Matriks InverseGaussJordan(){
          /* I.S Terdefinisi Matriks M bujur sangkar */
          /* F.S Terbentuk sebuah matriks invers dengan metode Gauss Jordan */
          float det = this.DeterminanGauss();
          Matriks temp = new Matriks(this.NBrsEff, (this.NKolEff)*2);
          Matriks Invers = new Matriks(this.NBrsEff, this.NKolEff);

          if (det == 0){
               System.out.println("Matriks tidak memiliki matriks balikan karena nilai determinannya = 0.");
          }
          else{
               //proses copy matriks ke temp
               for (int i = this.GetFirstIdxBrs(); i <= this.GetLastIdxBrs(); i++){
                    for (int j = this.GetFirstIdxKol(); j <= this.GetLastIdxKol(); j++){
                         temp.SetElmt(i, j, this.GetElmt(i, j));
                    }
               }
               //proses bikin augmented matriks identitas di temp
               for (int i = temp.GetFirstIdxBrs(); i<=temp.GetLastIdxBrs(); i++){
                    temp.SetElmt(i, this.GetLastIdxKol()+i+1, 1);
               }    

               temp.GJordanElimination();
               
               for (int i = temp.GetFirstIdxBrs(); i <= temp.GetLastIdxBrs(); i++){
                    
                    int k = this.GetFirstIdxKol();
                    
                    for (int j = (temp.GetLastIdxKol()/2)+1; j <= temp.GetLastIdxKol(); j++){
                         Invers.SetElmt(i, k, temp.GetElmt(i, j));
                         k+=1;
                    }
               }
                 
          }
          return Invers;
     }
}


