# Desain Game 'Snake And Ladder'

## 1. Model, View, and Controller Design Pattern (MVC)


Program kami menggunakan 1 buah kelas View yaitu __GameDisplay__ dan yang berperan sebagai Model adalah semua kelas turunan dari kelas __GameObject__ yaitu semua objek yang didalamnya memiliki objek lain yang dapat dirender ke dalam __Canvas__ yang dimiliki oleh kelas __GameDisplay__. Controller dalam program kami terdiri dari 2 buah kelas, yaitu kelas __GameEngine__ dan kelas __GameStateManager__, dimana __GameEngine__ mengatur resource yang akan digunakan oleh kelas-kelas turunan __GameObject__ dan juga mengatur proses rendering ke __GameDisplay__, dan __GameStateManager__ mengatur kapan sebuah objek harus di render dan di update.

## 2. Iterator Design Pattern

### 2.a. PointToPointIterator, Iterasi antara Dua Titik

Ular dan tangga dalam program kami di hasilkan secara acak dengan menghubungkan 2 buah tile yang ada di papan permainan, untuk itu kami harus dapat berjalan per langkah dengan jarak tertentu untuk dapat menghasilkan bentuk yang unik seperti ular yang meliuk-liuk dan tangga yang memiliki anak tangga.

Oleh karena itu kami membuat sebuah kelas iterator, __PointToPointIterator__, yang mampu meng-iterasi sebuah garis, per jarak yang ditentukan, di antara dua buah tile dan juga menyesuaikan pertambahan jarak terhadap sumbu x dan sumbu y dengan memperhitungkan gradien/kemiringan garis.

### 2.b. DynamicLineIterator, Meng-iterasi Bentuk Garis

Pemain/Bidak dalam permainan ini haruslah dapat mengikuti bentuk dan arah dari tangga dan ular, jika harus berjalan melalui tangga maka bidak harus bergerak mengikuti bentuk dari tangga, dan jika melalui ular maka bidak harus bergerak mengikuti bentuk ular.

Oleh karena itu kami membuat kelas iterator, __DynamicLineIterator__ yang mampu meng-iterasi titik-titik yang ada didalam sebuah garis, yang lurus maupun bergelombang, agar pemain dapat berpindah ke titik-titik tersebut sehingga terlihat seperti mengikuti bentuk dari ular maupun tangga.


## 3. Kelas Board

Kelas __Board__ merupakan kelas yang spesial, sebagai kelas turunan dari kelas __GameObject__, kelas __Board__ tidak memiliki objek untuk dirender, namun kelas Board merupkan kelas yang terdiri dari komposisi kelas __Tile__. __Board__ mampu mempopulasikan Tile dan memberikan struktur kepada banyak __Tile__ untuk membentuk papan permainan, juga merender __Tile__ seakan-akan kelas __Tile__ merupakan kelas __Board__.

Kelas __Board__ juga memiliki ketergantungan lain, yaitu pada kelas __Player__, __Dice__, __Snake__, dan __Ladder__ agar mampu berfungsi sebagai mana semestinya. Namun tidak seperti ke kelas __Tile__ yang merupakan komposisi dari kelas __Board__, kelas __Player__, __Dice__, __Snake__, dan __Ladder__ tidak dirender oleh Kelas __Board__ melainkan hanya dipopulasikan saja agar mempunyai bentuk, ukuran, dan posisi yang benar di dalam __Board__ saat digunakan dalam permainan. Kelas __Board__ tidak bertanggung jawab terhadap proses rendering dan updating dari kelas-kelas yang bukan merupakan komposisi dari __Board__.

## 3. Observer Design Pattern

Program kami tidak menggunakan komponen-komponen yang disediakan Swing untuk membuat tombol, label, dan panel, melainkan kami membuat komponen-komponen sendiri agar komponen tersebut terlihat lebih cocok dengan desain UI kami.

Untuk alasan diatas kami harus dapat menyediakan fasilitas event Broadcasting dan event Listening, maka kami membuat sebuah interface bernama __GameEventBroadcaster__ dan __GameEventListener__. __GameEventListener__ berfungsi sebagai observer dan __GameEventBroadcaster__ berfungsi sebagai Subject, dengan mekanisme ini kami dapat memberikan kemampuan terhadap komponen-komponen yang kami buat untuk merespon terhadap sebuah event.

## 4. Organisasi Rendering dan Updating

Setiap __GameObject__ dalam program kami membutuhkan proses rendering jika ditambah dengan implementasi interface __GameObjectUpdate__ maka objek tersebut haruslah di update karena nilainya terus berubah sepanjang permainan. Program kami memiliki banyak __Tile__, __Button__, __Label__, __Snake__, __Ladder__ dll, yang harus dirender dan di update maka kami harus mengatur dimana dan kapan proses tersebut harus dilakukan.

Setiap __GameObject__ dimiliki oleh sebuah __GameState__. __GameState__ memiliki fungsi untuk merender, mengupdate, dan meneruskan event ke setiap __GameObject__ yang menjadi anggotanya. Jadi hanya saat sebuah __GameState__ tersebut aktif, maka __GameObject__ yang menjadi anggota __GameState__ tersebut di render dan di update.

## 5. State Design Pattern

Dengan adanya pembagian tanggung jawab atas proses rendering, updating, dan event-broadcasting, maka kami dapat mengendalikan bagaimana, kapan, dan terhadap objek apa proses tersebut harus dilakukan. Namun walaupun demikian kami masih memiliki 5 __GameState__ berbeda, dan jika secara langsung diserahkan kepada __GameEngine__ maka akan sulit untuk untuk mengontrol rendering, updating, dan event-broadcasting dari setiap GameState.

Untuk alasan diatas kami membuat sebuah Controller selain __GameEngine__ yaitu kelas statik __GameStateManager__ untuk menjembatani __GameEngine__ dimana __GameObject__ benar-benar di proses, dengan Model yang harus diproses. Seakan-akan __GameEngine__ hanya memproses sebuah objek saja yaitu __GameStateManager__, padahal sebenarnya __GameStateManager__ memberikan __GameState__ yang sedang aktif kepada __GameEngine__ untuk diproses, dan __GameState__ memproses semua anggota-anggotanya sehingga membentuk ___chain of responsibility___ yang mudah untuk di maintain dan di debug.

## 6. Data Binding

Dalam kasus tertentu beberapa objek harus dapat berkomunikasi agar pertukaran data antar objek dapat terjadi, namun terkadang sebuah objek tidak memiliki hubungan apapun sehingga untuk diperlukan perubahan yang dapat membuat kelas yang tidak memiliki hubungan menjadi saling ketergantungan.

Oleh karena itu kami membuat sebuah kelas yang khusus untuk mengangani sebuah kasus yang kami temui, yaitu ketika __GameStage__ yang mengatur giliran pemain harus dapat memberikan data kepada sebuah komponen Label yang menampilkan giliran pemain sekarang. Maka kami membuat dua interface bernama __DataBinder__ dan __DataBindAction__ yang memberikan kemampuan kepada __Label__ untuk mengubah data pada __Label__ sesuai dengan data yang ada pada __GameStage__, dengan begitu kami dapat menghubungkan __GameStage__ dan Label tanpa menyebabkan kedua kelas tersebut menjadi saling ketergantungan.

## 7. Pengaturan Gambar, Warna, dan Font

Program kami mengunakan gambar sebagai background, warna tertentu untuk objek tertentu dan juga font tertentu untuk memenuhi desain UI kami. Program haruslah mengambil gambar dan font dari file diluar program, dan warna yang kami gunakan terkadang tidak memiliki nama dan hanya berupa nomor RGB. Jika kami tidak membuat sebuah mekanisme untuk mengatur resource yang kami gunakan maka akan sulit untuk menentukan warna dan gambar yang dibutuhkan oleh objek tertentu dan dimana resource itu disimpan.

Oleh karena itu kami membuat sebuah kelas statik bernama __AssetManager__ yang memberi kemudahan bagi penulis program untuk mengenali suatu resource digunakan oleh siapa dan dipergunakan sebagai apakah resource tersebut, karena __AssetManager__ memungkinkan penulis program untuk dapat memberikan sebuah gambar, font, ataupun warna sebuah nama, dan dapat pula diakses berdasarkan nama yang diberikan.

## 8. Pengaturan Permainan dan UI

Desain UI program kami membutuhkan banyak konstanta untuk mengatur seberapa sebesar sebuah objek dan dimana sebuah objek dirender, serta membutuhkan konstanta untuk mengatur delay dan pengaturan-pengaturan Grafik seperti penggunaan antialiasing dan pengaturan banyaknya frame yang di render setiap detik (FPS), yang jika tidak di definisikan dalam sebuah konstanta akan menyebabkan kode program dipenuhi oleh angka-angka yang sulit ditentukan kegunaannya dan juga sulit untuk diubah saat runtime.

Untuk itu kami membuat sebuah kumpulan kelas statik yang menyimpan konstanta-konstanta yang kami butuhkan untuk menyimpan angka-angka penting yang kami gunakan dalam program, kumpulan kelas statik tersebut kami simpan didalam sebuah file bernama __GameSettings__. Dengan adanya __GameSettings__ kami dapat dengan mudah mengenali dan bahkan mengubah nilai-nilai saat runtime. Seperti halnya pengaturan antialiasing dan pengaturan FPS yang dapat diubah dan digunakan oleh kelas __GameEngine__ dan __SettingsState__.
